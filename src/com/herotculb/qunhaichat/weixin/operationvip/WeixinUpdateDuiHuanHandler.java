package com.herotculb.qunhaichat.weixin.operationvip;

import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.ViewGroup;
import cn.pedant.SweetAlert.SweetAlertDialog;

import com.herotculb.qunhaichat.HomeActivity;
import com.herotculb.qunhaichat.weixin.operationvip.window.VipCreateDuiHuanWindowActivity;
import com.herotculb.qunhaichat.widget.LoadingDialog;

public class WeixinUpdateDuiHuanHandler extends Handler {
	VipCreateDuiHuanWindowActivity context;
	ViewGroup view;
	LoadingDialog dialog;
	
	public WeixinUpdateDuiHuanHandler(VipCreateDuiHuanWindowActivity context, LoadingDialog dialog) {
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
			Intent i = new Intent(context, HomeActivity.class);  
	        Bundle b2 = new Bundle();
	        b2.putString("type", "update_dui_huan");
	        i.putExtras(b2);
	        context.setResult(context.RESULT_OK, i);  
	        context.finish();
		}else{
			new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
            .setTitleText("出错了！")
            .setContentText((String)map.get("info"))
            .show();
		}
	}
}
