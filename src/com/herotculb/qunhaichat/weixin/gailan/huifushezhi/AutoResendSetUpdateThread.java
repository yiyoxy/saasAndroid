package com.herotculb.qunhaichat.weixin.gailan.huifushezhi;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.herotculb.qunhaichat.dto.WeiXinAutoReSendMenuDto;
import com.herotculb.qunhaichat.util.QNPermissionApiImpl;

public class AutoResendSetUpdateThread extends Thread {
	private Context context;
	private int type;
	private Handler handler;
	private WeiXinAutoReSendMenuDto dto;
	public AutoResendSetUpdateThread(Context context, int type, Handler handler,WeiXinAutoReSendMenuDto dto) {
		super();
		this.context = context;
		this.type = type;
		this.handler = handler;
		this.dto=dto;
	}

	@Override
	public void run() {
		QNPermissionApiImpl qnpAPi=new QNPermissionApiImpl(context);
	
		Map<String,Object> map=null;
		switch (type) {
		case WeiXinAutoReSendMenuDto.TYPE_EVENT:
			List<Map<String, Object>> list=qnpAPi.updateAutoReSend_Event(String.valueOf(dto.getId()),dto.getName(), dto.getContent(), dto.getWeixin_events());
			map=list.iterator().next();
			break;
		case WeiXinAutoReSendMenuDto.TYPE_IMAGE:
			List<Map<String, Object>> list2=qnpAPi.updateAutoReSend_Image(String.valueOf(dto.getId()),dto.getName(), dto.getContent());
			map=list2.iterator().next();
			break;
		case WeiXinAutoReSendMenuDto.TYPE_LINK:
			List<Map<String, Object>> list3=qnpAPi.updateAutoReSend_Link(String.valueOf(dto.getId()),dto.getName(), dto.getContent());
			map=list3.iterator().next();
			break;
		case WeiXinAutoReSendMenuDto.TYPE_LOCATION:
			List<Map<String, Object>> list4=qnpAPi.updateAutoReSend_Location(String.valueOf(dto.getId()),dto.getName(), dto.getContent());
			map=list4.iterator().next();
			break;
		case WeiXinAutoReSendMenuDto.TYPE_TEXT:
			List<Map<String, Object>> list5=qnpAPi.updateAutoReSend_Text(String.valueOf(dto.getId()),dto.getName(), dto.getContent());
			map=list5.iterator().next();
			break;
		case WeiXinAutoReSendMenuDto.TYPE_VIDEO:
			List<Map<String, Object>> list6=qnpAPi.updateAutoReSend_Video(String.valueOf(dto.getId()),dto.getName(), dto.getContent());
			map=list6.iterator().next();
			break;
		case WeiXinAutoReSendMenuDto.TYPE_VOICE:
			List<Map<String, Object>> list7=qnpAPi.updateAutoReSend_Voice(String.valueOf(dto.getId()),dto.getName(), dto.getContent());
			map=list7.iterator().next();
			break;
		default:
			break;
		}
		Message sendmsg = Message.obtain();  
        sendmsg.obj = map;   //利用Message.obj把子线程的handle传递给主线程。  
        handler.sendMessage(sendmsg);  
	}
}