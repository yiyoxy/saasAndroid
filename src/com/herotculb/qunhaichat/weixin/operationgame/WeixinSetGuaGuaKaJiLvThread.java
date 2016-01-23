package com.herotculb.qunhaichat.weixin.operationgame;

import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.beardedhen.androidbootstrap.BootstrapEditText;
import com.herotculb.qunhaichat.R;
import com.herotculb.qunhaichat.util.QNPermissionApiImpl;

public class WeixinSetGuaGuaKaJiLvThread extends Thread {
	private Activity context;
	private String type;	
	private Handler handler;
	
	public WeixinSetGuaGuaKaJiLvThread(Activity context, String type,
			Handler handler) {
		super();
		this.context = context;
		this.type = type;
		this.handler = handler;
	}

	@Override
	public void run() {
		super.run();
		Looper.prepare();
		
		String value = "";
		
		if(type.equals("jilv")){
			BootstrapEditText jilv = (BootstrapEditText)
					context.findViewById(R.id.game_guaguaka_jilv_edit);
			value = jilv.getText().toString();
			type = "略";
		}else if(type.equals("score")){
			BootstrapEditText score = (BootstrapEditText)
					context.findViewById(R.id.game_guaguaka_score_edit);
			value = score.getText().toString();
		}
		QNPermissionApiImpl qnpAPi=new QNPermissionApiImpl(context);
		List<Map<String, Object>> list= qnpAPi.setguaguakaJilv(value, type);
		Map<String,Object> map=list.iterator().next();
		//获取数据成功
		Message sendmsg = Message.obtain();  
        sendmsg.obj = map;   //利用Message.obj把子线程的handle传递给主线程。  
        handler.sendMessage(sendmsg); 
	}
	
}
