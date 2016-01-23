package com.herotculb.qunhaichat.weixin.order;

import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.ViewGroup;
import cn.pedant.SweetAlert.SweetAlertDialog;

import com.herotculb.qunhaichat.HomeActivity;
import com.herotculb.qunhaichat.R;
import com.herotculb.qunhaichat.widget.LoadingDialog;

public class WeixinEnterOrderHandler extends Handler {
	Activity context;
	ViewGroup view;
	LoadingDialog dialog;
	
	public WeixinEnterOrderHandler(Activity context, LoadingDialog dialog) {
		super();
		this.context = context;
		this.view = view;
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
	        b2.putString("type", "weixin_enter_order");
	        i.putExtras(b2);
	        context.startActivityForResult(i, 0);
	        context.overridePendingTransition(R.anim.modal_in, R.anim.modal_out);
//	        context.finish();  
		}else{
			new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE).setTitleText("添加")
            .setContentText(String.valueOf(map.get("info")))
            .setConfirmText("确定")
            .showCancelButton(false)
            .setCancelClickListener(null)
            .setConfirmClickListener(null)
            .changeAlertType(SweetAlertDialog.ERROR_TYPE);
		}
	}
}
