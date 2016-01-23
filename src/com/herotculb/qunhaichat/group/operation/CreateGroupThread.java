package com.herotculb.qunhaichat.group.operation;

import java.util.List;
import java.util.Map;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.beardedhen.androidbootstrap.BootstrapEditText;
import com.herotculb.qunhaichat.R;
import com.herotculb.qunhaichat.group.window.CreateGroupWindow;
import com.herotculb.qunhaichat.util.QNPermissionApiImpl;

public class CreateGroupThread extends Thread {
	private CreateGroupWindow context;
	private Handler handler;
	private String groupId;
	public CreateGroupThread(CreateGroupWindow context, Handler handler,String groupId) {
		super();
		this.context = context;
		this.handler = handler;
		this.groupId = groupId;
	}

	@Override
	public void run() {
		super.run();
		Looper.prepare();
		
		BootstrapEditText nametext =(BootstrapEditText) context.findViewById(R.id.permission_group_name);
		BootstrapEditText markstext =(BootstrapEditText) context.findViewById(R.id.permission_group_marks);
		
		String groupName = nametext.getText().toString();
		String groupMarks = markstext.getText().toString();
		
		QNPermissionApiImpl qnpAPi=new QNPermissionApiImpl(context);
		List<Map<String, Object>> list=qnpAPi.getCreateGroup(groupId, groupName, groupMarks);
		Map<String,Object> map=list.iterator().next();
		Message sendmsg = Message.obtain();  
        sendmsg.obj = map;   //利用Message.obj把子线程的handle传递给主线程。  
        handler.sendMessage(sendmsg);  
	}
	
}
