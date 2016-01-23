package com.herotculb.qunhaichat.weixin.gailan.huifuziyuanku;

import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.beardedhen.androidbootstrap.BootstrapEditText;
import com.herotculb.qunhaichat.R;
import com.herotculb.qunhaichat.dto.WeiXinReSendDto;
import com.herotculb.qunhaichat.util.QNPermissionApiImpl;

public class AutoResendAddThread extends Thread {
	private Activity context;

	private Handler handler;
	private int type;
	public AutoResendAddThread(Activity context,  Handler handler,int type) {
		this.context=context;
		this.type=type;
		this.handler=handler;
	}
	@Override
	public void run() {
		QNPermissionApiImpl qnpAPi=new QNPermissionApiImpl(context);
//		/Map<String,Object> map=list.iterator().next();
		Map<String,Object> map=null;
		switch (type) {
		case WeiXinReSendDto.TEXT:
			BootstrapEditText nametext=(BootstrapEditText) context.findViewById(R.id.weixin_gailan_autoresoure_text_name);
			BootstrapEditText contenttext=(BootstrapEditText) context.findViewById(R.id.weixin_gailan_autoresoure_text_content);
			String name=nametext.getText().toString();
			String content=contenttext.getText().toString();
			List<Map<String, Object>> list=qnpAPi.addText(name, content);
			map=list.iterator().next();
			break;
		case WeiXinReSendDto.IMAGE:
			//图片
			BootstrapEditText nametext2=(BootstrapEditText) context.findViewById(R.id.weixin_gailan_autoresoure_image_name);
			BootstrapEditText contenttext2=(BootstrapEditText) context.findViewById(R.id.weixin_gailan_autoresoure_image_content);
			String name2=nametext2.getText().toString();
			String content2=contenttext2.getText().toString();
			List<Map<String, Object>> list2=qnpAPi.addImage(name2, content2);
			map=list2.iterator().next();
			break;
		case WeiXinReSendDto.IMAGE_TEXT:
			//图文
			BootstrapEditText nametext3=(BootstrapEditText) context.findViewById(R.id.weixin_gailan_autoresoure_imagetext_name);
			BootstrapEditText titletext3=(BootstrapEditText) context.findViewById(R.id.weixin_gailan_autoresoure_imagetext_title);
			BootstrapEditText marktext3=(BootstrapEditText) context.findViewById(R.id.weixin_gailan_autoresoure_imagetext_marks);
			BootstrapEditText imageLinktext3=(BootstrapEditText) context.findViewById(R.id.weixin_gailan_autoresoure_imagetext_imageLink);
			BootstrapEditText linktext3=(BootstrapEditText) context.findViewById(R.id.weixin_gailan_autoresoure_imagetext_link);
			String name3=nametext3.getText().toString();
			String title3=titletext3.getText().toString();
			String marks=marktext3.getText().toString();
			String image=imageLinktext3.getText().toString();
			String link=linktext3.getText().toString();
			List<Map<String, Object>> list3=qnpAPi.addImageText(name3, title3, marks, image, link);
			map=list3.iterator().next();
			break;
		case WeiXinReSendDto.MUSIC:
			//音乐
			BootstrapEditText nametext4=(BootstrapEditText) context.findViewById(R.id.weixin_gailan_autoresoure_music_name);
			BootstrapEditText titletext4=(BootstrapEditText) context.findViewById(R.id.weixin_gailan_autoresoure_music_title);
			BootstrapEditText marktext4=(BootstrapEditText) context.findViewById(R.id.weixin_gailan_autoresoure_music_marks);
			BootstrapEditText linkaddrtext4=(BootstrapEditText) context.findViewById(R.id.weixin_gailan_autoresoure_music_linkaddr);
			BootstrapEditText gaolinkaddrtext4=(BootstrapEditText) context.findViewById(R.id.weixin_gailan_autoresoure_music_gaolinkaddr);
			BootstrapEditText iamgetext4=(BootstrapEditText) context.findViewById(R.id.weixin_gailan_autoresoure_music_image);
			String name4=nametext4.getText().toString();
			String title4=titletext4.getText().toString();
			String marks4=marktext4.getText().toString();
			String linkaddr=linkaddrtext4.getText().toString();
			String gaolinkaddr=gaolinkaddrtext4.getText().toString();
			String iamge=iamgetext4.getText().toString();
			List<Map<String, Object>> list4=qnpAPi.addMusic(name4, title4, marks4, gaolinkaddr, iamge, linkaddr);
			map=list4.iterator().next();
			break;
		case WeiXinReSendDto.VIDEO:
			//视频
			BootstrapEditText nametext5=(BootstrapEditText) context.findViewById(R.id.weixin_gailan_autoresoure_video_name);
			BootstrapEditText titletext5=(BootstrapEditText) context.findViewById(R.id.weixin_gailan_autoresoure_video_title);
			BootstrapEditText marktext5=(BootstrapEditText) context.findViewById(R.id.weixin_gailan_autoresoure_video_marks);
			BootstrapEditText serviceId5=(BootstrapEditText) context.findViewById(R.id.weixin_gailan_autoresoure_video_content);
			
			String name5=nametext5.getText().toString();
			String title5=titletext5.getText().toString();
			String marks5=marktext5.getText().toString();
			String service5=serviceId5.getText().toString();
			
			List<Map<String, Object>> list5=qnpAPi.addVideo(name5, service5, title5, marks5);
			map=list5.iterator().next();
			break;
		case WeiXinReSendDto.VOICE:
			//语音
			BootstrapEditText nametext6=(BootstrapEditText) context.findViewById(R.id.weixin_gailan_autoresoure_voice_name);
			BootstrapEditText contenttext6=(BootstrapEditText) context.findViewById(R.id.weixin_gailan_autoresoure_voice_content);
			
			String name6=nametext6.getText().toString();
			String title6=contenttext6.getText().toString();
		
			
			List<Map<String, Object>> list6=qnpAPi.addVoice(name6, title6);
			map=list6.iterator().next();
			break;
		
		}
		Log.e("log1", "进入了线程");
		Message sendmsg = Message.obtain();  
        sendmsg.obj = map;   //利用Message.obj把子线程的handle传递给主线程。  
        handler.sendMessage(sendmsg);  
	}
}
