package com.herotculb.qunhaichat.weixin.accountset;

import java.util.Map;

import android.annotation.SuppressLint;
import android.os.Handler;
import cn.pedant.SweetAlert.SweetAlertDialog;

import com.beardedhen.androidbootstrap.BootstrapEditText;
import com.herotculb.qunhaichat.HomeActivity;
import com.herotculb.qunhaichat.R;
import com.herotculb.qunhaichat.widget.LoadingDialog;

public class AccountSetUpdateHandler extends Handler{
	HomeActivity context;
	LoadingDialog dialog;
	
	public AccountSetUpdateHandler(HomeActivity context,
			LoadingDialog dialog) {
		super();
		this.context = context;
		this.dialog = dialog;
	}
	@SuppressLint("ResourceAsColor")
	@Override
	public void handleMessage(android.os.Message msg) {
		Map<String, Object> map = (Map<String, Object>) msg.obj;
		Boolean b=(Boolean) map.get("success");
		dialog.hide();
		dialog.dismiss();
	
		BootstrapEditText appid=(BootstrapEditText) context.findViewById(R.id.weixin_accountset_layout_appid);
		BootstrapEditText appSecret=(BootstrapEditText) context.findViewById(R.id.weixin_accountset_layout_AppSecret);
		BootstrapEditText tokens=(BootstrapEditText) context.findViewById(R.id.weixin_accountset_layout_tokens);
		
		if(b){
			 new SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE)
			 .setTitleText("成功")
             .setContentText((String) map.get("info"))
             .setConfirmText("确定")
             .showCancelButton(false)
             .setCancelClickListener(null)
             .setConfirmClickListener(null).show();
		}else{
			 new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
			 .setTitleText("失败")
             .setContentText((String) map.get("info"))
             .setConfirmText("确定")
             .showCancelButton(false)
             .setCancelClickListener(null)
             .setConfirmClickListener(null).show();
		}
	}
}
