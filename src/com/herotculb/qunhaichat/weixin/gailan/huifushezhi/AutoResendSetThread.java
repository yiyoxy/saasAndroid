package com.herotculb.qunhaichat.weixin.gailan.huifushezhi;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.herotculb.qunhaichat.dto.WeiXinAutoReSendMenuDto;
import com.herotculb.qunhaichat.util.QNPermissionApiImpl;

public class AutoResendSetThread extends Thread{
	private Context context;
	private int type;
	private Handler handler;
		
	public AutoResendSetThread(Context context, int type,Handler handler) {
		super();
		this.context = context;
		this.type = type;
		this.handler = handler;
	}
	@Override
	public void run() {
Looper.prepare();
		
		QNPermissionApiImpl qnpAPi=new QNPermissionApiImpl(context);
		List<Map<String, Object>> list=null;
		switch (type) {
		case WeiXinAutoReSendMenuDto.TYPE_EVENT:
			//事件
			list=qnpAPi.getAutoReSend_Event();
			break;
		case WeiXinAutoReSendMenuDto.TYPE_IMAGE:
			//图片	
			list=qnpAPi.getAutoReSend_Image();
			break;
		case WeiXinAutoReSendMenuDto.TYPE_LINK:
			//链接
			list=qnpAPi.getAutoReSend_Link();
			break;
		case WeiXinAutoReSendMenuDto.TYPE_LOCATION:
			//位置
			list=qnpAPi.getAutoReSend_Location();
			break;
		case WeiXinAutoReSendMenuDto.TYPE_TEXT:
			//文本
			list=qnpAPi.getAutoReSend_Text();
			break;
		case WeiXinAutoReSendMenuDto.TYPE_VIDEO:
			//视频
			list=qnpAPi.getAutoReSend_Video();
			break;
		case WeiXinAutoReSendMenuDto.TYPE_VOICE:
			//语音
			list=qnpAPi.getAutoReSend_Voice();
			break;
		default:
			break;
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
