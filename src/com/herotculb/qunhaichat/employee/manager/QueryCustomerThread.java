package com.herotculb.qunhaichat.employee.manager;

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
public class QueryCustomerThread extends Thread {
	private Context context;
	private String nowpage;
	private String countNum;
	private String name;
	private Handler handler;
	
	public QueryCustomerThread(Context context, String nowpage, String countNum,
			String name, Handler handler) {
		super();
		this.context = context;
		this.nowpage = nowpage;
		this.countNum = countNum;
		this.name = name;
		this.handler = handler;
	}

	@Override
	public void run() {
		super.run();
		Looper.prepare();
		
		QNPermissionApiImpl qnpAPi=new QNPermissionApiImpl(context);
		List<Map<String, Object>> list=qnpAPi.queryCustemmer(nowpage, countNum, name);
		Map<String,Object> map=list.iterator().next();
		Message sendmsg = Message.obtain();  
        sendmsg.obj = map;   //利用Message.obj把子线程的handle传递给主线程。  
        handler.sendMessage(sendmsg);  
	}
	
}
