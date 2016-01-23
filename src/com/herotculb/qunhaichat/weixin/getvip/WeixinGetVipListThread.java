package com.herotculb.qunhaichat.weixin.getvip;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.herotculb.qunhaichat.util.QNPermissionApiImpl;

/**
 * 会员管理的 Thread 与主程序的通讯
 * @author Administrator
 *
 */
public class WeixinGetVipListThread extends Thread {
	private Context context;
	private Handler handler;
	public WeixinGetVipListThread(Context context, Handler handler) {
		super();
		this.context = context;
		this.handler = handler;
	}
	@Override
	public void run() {
		super.run();
		Looper.prepare();
		
		QNPermissionApiImpl qnpAPi=new QNPermissionApiImpl(context);
		List<Map<String, Object>> list=qnpAPi.getVipList();
		Map<String,Object> map=list.iterator().next();
		boolean b=(Boolean) map.get("success");
		if(b){
			//获取数据成功
			map.put("success", b);
			map.put("data", map.get("data"));
			Message sendmsg = Message.obtain();  
            sendmsg.obj = map;   //利用Message.obj把子线程的handle传递给主线程。  
            handler.sendMessage(sendmsg);  
		}else{
			//获取数据失败
			Message sendmsg = Message.obtain();  
            sendmsg.obj = map;   //利用Message.obj把子线程的handle传递给主线程。  
            handler.sendMessage(sendmsg);  
		}
	}
	
}
