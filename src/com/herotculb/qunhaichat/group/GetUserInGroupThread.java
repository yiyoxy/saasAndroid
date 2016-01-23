package com.herotculb.qunhaichat.group;

import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.beardedhen.androidbootstrap.BootstrapEditText;
import com.herotculb.qunhaichat.R;
import com.herotculb.qunhaichat.util.QNPermissionApiImpl;

/**
 * 获得组数据
 * @author Administrator
 *
 */
public class GetUserInGroupThread extends Thread {
	private Activity context;
	private Handler handler;
	
	public GetUserInGroupThread(Activity context, Handler handler) {
		super();
		this.context = context;
		this.handler = handler;
	}

	@Override
	public void run() {
		super.run();
		Looper.prepare();
		BootstrapEditText groupText = null;
		if(context.findViewById(R.id.user_in_group_name)!=null){
			groupText = (BootstrapEditText)context.findViewById(R.id.user_in_group_name);
		}else{
			groupText = (BootstrapEditText)context.findViewById(R.id.crm_user_in_group_name);
		}
		String groupName = groupText.getText().toString();
		
		QNPermissionApiImpl qnpAPi=new QNPermissionApiImpl(context);
		List<Map<String, Object>> list=qnpAPi.getMyGroup(groupName);
		Map<String,Object> map=list.iterator().next();
		Message sendmsg = Message.obtain();  
        sendmsg.obj = map;   //利用Message.obj把子线程的handle传递给主线程。  
        handler.sendMessage(sendmsg);  
	}
	
}
