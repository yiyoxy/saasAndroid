package com.herotculb.qunhaichat.messageinfo;

import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.ViewGroup;
import cn.pedant.SweetAlert.SweetAlertDialog;

import com.herotculb.qunhaichat.HomeActivity;
import com.herotculb.qunhaichat.homeactiviti.messageinfo.MessageSetManager;
import com.herotculb.qunhaichat.widget.LoadingDialog;

public class SendVipMessageHandler extends Handler {
	Activity context;
	ViewGroup view;
	LoadingDialog dialog;
	
	public SendVipMessageHandler(Activity context, LoadingDialog dialog) {
		super();
		this.context = context;
		this.dialog = dialog;
	}

	@Override
	public void handleMessage(android.os.Message msg) {
		super.handleMessage(msg);
		Map<String, Object> map = (Map<String, Object>)msg.obj;
		boolean b=(Boolean) map.get("success");
		dialog.hide();
		dialog.dismiss();
		if(b){
			
		}else{
			new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
            .setTitleText("出错了！")
            .setContentText((String)map.get("info"))
            .show();
		}
	}
}
