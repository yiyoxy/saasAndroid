package com.herotculb.qunhaichat.homeactiviti.util.window.goodselect;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.herotculb.qunhaichat.util.QNPermissionApiImpl;

public class ErWeiMaQueryGoodsThread extends Thread{
	private Context context;
	private Handler handler;
	private String codeid;

	public ErWeiMaQueryGoodsThread(Context context,Handler handler,String codeid) {
		super();
		this.context = context;
		this.handler = handler;
		this.codeid=codeid;
	}
	@Override
	public void run() {
		Looper.prepare();
		QNPermissionApiImpl qnpAPi=new QNPermissionApiImpl(context);
		List<Map<String, Object>> list=qnpAPi.queryGoods(codeid);
		Map<String,Object> map=list.iterator().next();
		boolean b=(Boolean) map.get("success");
		if(b){
			Message sendmsg = Message.obtain();  
            sendmsg.obj = map;   //利用Message.obj把子线程的handle传递给主线程。  
            handler.sendMessage(sendmsg); 
		}else{
			//获取数据失败
			Message sendmsg = Message.obtain();  
			 map.put("codeid", codeid);
            sendmsg.obj = map;   //利用Message.obj把子线程的handle传递给主线程。  
           
            handler.sendMessage(sendmsg);  
		}
	}
}
