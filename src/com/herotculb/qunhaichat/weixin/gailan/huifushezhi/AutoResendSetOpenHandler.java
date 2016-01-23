package com.herotculb.qunhaichat.weixin.gailan.huifushezhi;

import java.util.Map;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.widget.ListView;
import cn.pedant.SweetAlert.SweetAlertDialog;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.herotculb.qunhaichat.HomeActivity;
import com.herotculb.qunhaichat.R;
import com.herotculb.qunhaichat.dto.WeiXinAutoReSendMenuDto;
import com.herotculb.qunhaichat.widget.LoadingDialog;

public class AutoResendSetOpenHandler extends Handler {
	HomeActivity context;
	ListView view;
	LoadingDialog dialog;
	int type;
	
	public AutoResendSetOpenHandler(HomeActivity autoResendSetWindow,int typestr, 
			LoadingDialog dialog) {
		super();
		this.context = autoResendSetWindow;
		this.view = view;
		this.dialog = dialog;
		this.type=typestr;
	}
	@SuppressLint("ResourceAsColor")
	@Override
	public void handleMessage(android.os.Message msg) {
		Map<String, Object> map = (Map<String, Object>) msg.obj;
		Boolean b=(Boolean) map.get("success");
		dialog.hide();
		dialog.dismiss();
		if(b){
			switch (type) {
			case WeiXinAutoReSendMenuDto.TYPE_TEXT:
				//文本
				BootstrapButton b0=(BootstrapButton) context.findViewById(R.id.weixin_gailan_autoResendSet_pager_text);
				b0.performClick();
				break;
			case WeiXinAutoReSendMenuDto.TYPE_IMAGE:
				//图片
				BootstrapButton b1=(BootstrapButton)context.findViewById(R.id.weixin_gailanautoResendSet_pager_image);
				b1.performClick();
				break;
			case WeiXinAutoReSendMenuDto.TYPE_LINK:
				//链接
				BootstrapButton b2=(BootstrapButton)context.findViewById(R.id.weixin_gailan_autoResendSet_pager_link);
				b2.performClick();
				break;
			case WeiXinAutoReSendMenuDto.TYPE_LOCATION:
				//位置
				BootstrapButton b3=(BootstrapButton)context.findViewById(R.id.weixin_gailan_autoResendSet_pager_location);
				b3.performClick();
				break;
			case WeiXinAutoReSendMenuDto.TYPE_VIDEO:
				//视频
				BootstrapButton b4=(BootstrapButton)context.findViewById(R.id.weixin_gailan_autoResendSet_pager_video);
				b4.performClick();
				break;
			case WeiXinAutoReSendMenuDto.TYPE_VOICE:
				//语音
				BootstrapButton b5=(BootstrapButton)context.findViewById(R.id.weixin_gailan_autoResendSet_pager_voice);
				b5.performClick();
				break;
			case WeiXinAutoReSendMenuDto.TYPE_EVENT:
				//事件
				BootstrapButton b6=(BootstrapButton)context.findViewById(R.id.weixin_gailan_autoResendSet_pager_event);
				b6.performClick();
				break;
			default:
				break;
			}
			new SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE).setTitleText("打开/关闭")
            .setContentText(String.valueOf(map.get("info")))
            .setConfirmText("确定")
            .showCancelButton(false)
            .setCancelClickListener(null)
            .setConfirmClickListener(null).show();
		}else{
			new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE).setTitleText("打开/关闭")
            .setContentText(String.valueOf(map.get("info")))
            .setConfirmText("确定")
            .showCancelButton(false)
            .setCancelClickListener(null)
            .setConfirmClickListener(null).show();
		}
	}
}
