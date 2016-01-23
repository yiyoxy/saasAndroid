package com.herotculb.qunhaichat.homeactiviti.goods.orderquery;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.herotculb.qunhaichat.util.QNPermissionApiImpl;

public class QueryOrderThread extends Thread{
	private Context context;
	private Handler handler;
	private String num;
	private String name;
	private String nowpage;
	private String countNum;
	private String endDate;
	private String startDate;
	private boolean isinOrder;
	private boolean isNum;
	public QueryOrderThread(Context context,Handler handler,String name,String num,String nowpage,String countNum,String endDate,String startDate,boolean isinOrder,boolean isNum) {
		super();
		this.context = context;
		this.handler = handler;
		this.num=num;
		this.name=name;
		this.nowpage=nowpage;
		this.countNum=countNum;
		this.endDate=endDate;
		this.startDate=startDate;
		this.isinOrder=isinOrder;
		this.isNum=isNum;
	}
	@Override
	public void run() {
		Looper.prepare();
		QNPermissionApiImpl qnpAPi=new QNPermissionApiImpl(context);
		List<Map<String, Object>> list=null;
		if(num.trim().equals("")){
			num="0";
		}
		if(isinOrder){
			list=qnpAPi.queryInOrder(num, name, endDate, startDate, nowpage, countNum,isNum);
		}else{
			list=qnpAPi.queryOrder(num, name, "0", endDate,startDate,  nowpage, countNum,isNum);
		}
		Map<String,Object> map=list.iterator().next();
		boolean b=(Boolean) map.get("success");
		if(b){
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
