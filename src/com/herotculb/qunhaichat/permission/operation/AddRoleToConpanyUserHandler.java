package com.herotculb.qunhaichat.permission.operation;

import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.ViewGroup;
import cn.pedant.SweetAlert.SweetAlertDialog;

import com.herotculb.qunhaichat.permission.window.LookUpRoleByUserWindow;
import com.herotculb.qunhaichat.widget.LoadingDialog;

public class AddRoleToConpanyUserHandler extends Handler {
	Activity context;
	ViewGroup view;
	LoadingDialog dialog;
	
	public AddRoleToConpanyUserHandler(Activity context, LoadingDialog dialog) {
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
			Intent i = new Intent(context, LookUpRoleByUserWindow.class);  
	        Bundle b2 = new Bundle();
	        b2.putString("type", "add_role_to_conpany_user");
	        i.putExtras(b2);
	        context.setResult(context.RESULT_OK, i);
	        context.finish();  
	     
		}else{
			new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE).setTitleText("添加积分")
            .setContentText(String.valueOf(map.get("info")))
            .setConfirmText("确定")
            .showCancelButton(false)
            .setCancelClickListener(null)
            .setConfirmClickListener(null)
            .changeAlertType(SweetAlertDialog.ERROR_TYPE);
		}
	}
}