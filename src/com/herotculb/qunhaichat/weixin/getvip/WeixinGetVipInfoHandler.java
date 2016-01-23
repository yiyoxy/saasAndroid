package com.herotculb.qunhaichat.weixin.getvip;

import java.util.Map;

import android.content.Context;
import android.os.Handler;
import android.view.ViewGroup;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class WeixinGetVipInfoHandler extends Handler {
	Context context;
	ViewGroup view;
	public WeixinGetVipInfoHandler(Context context, ViewGroup view) {
		super();
		this.context = context;
		this.view = view;
	}
	@Override
	public void handleMessage(android.os.Message msg) {
		super.handleMessage(msg);
		Map<String, Object> map = (Map<String, Object>) msg.obj;
		boolean b=(Boolean) map.get("success");
		if(b){
			
		}else{
			new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
            .setTitleText("出错了！")
            .setContentText((String)map.get("info"))
            .show();
		}
	}
}
