package com.herotculb.qunhaichat.hr.qiandao;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.herotculb.qunhaichat.location.LocationService;
import com.herotculb.qunhaichat.util.QNPermissionApiImpl;
/**
 * 签到专用线程
 * 会先检测是否签过到
 * 如果签到则去签退
 * 如果签过到也签过退则提示
 * @author Administrator
 *
 */
public class MeetingThread extends Thread{
	Context content;
	Handler uihandler;
	String latitude;
	String longitude;
	public MeetingThread(Handler handler,Context content,String latitude,String longitude){
		uihandler=handler;
		this.content=content;
		this.latitude=latitude;
		this.longitude=longitude;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		Looper.prepare();
		QNPermissionApiImpl qnpAPi=new QNPermissionApiImpl(content);
		List<Map<String, Object>> list=qnpAPi.meeting("略", "test",latitude,longitude);
		Map<String,Object> map=list.iterator().next();
		if((Boolean)map.get("success")){
			if(Boolean.parseBoolean((String)map.get("isup"))){
				//未签过到

				list=qnpAPi.meeting("略", "up",latitude,longitude);
				map=list.iterator().next();
				map.put("info", map.get("info"));
				Message sendmsg = Message.obtain();  
	            sendmsg.obj = map;   //利用Message.obj把子线程的handle传递给主线程。  
	            uihandler.sendMessage(sendmsg);  
			}else{

				//已经签过到了
				list=qnpAPi.meeting("略", "down",latitude,longitude);
				map=list.iterator().next();
				map.put("info", map.get("info"));
				Message sendmsg = Message.obtain();  
	            sendmsg.obj = map;   //利用Message.obj把子线程的handle传递给主线程。  
	            content.stopService(new Intent(content,LocationService.class));
	            uihandler.sendMessage(sendmsg);	
			}
		}else{
			//已经签到和签退,或未设置时间
			Message sendmsg = Message.obtain();  
            sendmsg.obj = map;   //利用Message.obj把子线程的handle传递给主线程。  
            uihandler.sendMessage(sendmsg);  
		}
	}

}
