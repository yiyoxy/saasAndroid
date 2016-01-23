package com.herotculb.qunhaichat.weixin.gailan.huifuziyuanku;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.herotculb.qunhaichat.util.QNPermissionApiImpl;

public class AutoResendDeleteThread extends Thread {
	private Context context;
	private long id;
	private Handler handler;

	public AutoResendDeleteThread(Context context, long id, Handler handler) {
		super();
		this.context = context;
		this.id = id;
		this.handler = handler;
	}
	@Override
	public void run() {
		QNPermissionApiImpl qnpAPi=new QNPermissionApiImpl(context);
		List<Map<String, Object>> list=qnpAPi.deleteReSend(String.valueOf(id));
		Map<String,Object> map=list.iterator().next();
		Message sendmsg = Message.obtain();  
        sendmsg.obj = map;   //利用Message.obj把子线程的handle传递给主线程。  
        handler.sendMessage(sendmsg);  
	}
}