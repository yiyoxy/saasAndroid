package com.herotculb.qunhaichat.weixin.accountset;

import java.util.List;
import java.util.Map;

import android.annotation.SuppressLint;
import android.os.Handler;
import cn.pedant.SweetAlert.SweetAlertDialog;

import com.beardedhen.androidbootstrap.BootstrapEditText;
import com.herotculb.qunhaichat.HomeActivity;
import com.herotculb.qunhaichat.R;
import com.herotculb.qunhaichat.dto.WeiXinDto;
import com.herotculb.qunhaichat.widget.LoadingDialog;

public class AccountSetHandler extends Handler{
	HomeActivity context;
	LoadingDialog dialog;
	
	public AccountSetHandler(HomeActivity context,
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
			 List<WeiXinDto> list=(List<WeiXinDto>) map.get("data");
			 WeiXinDto dto=list.iterator().next();
			 appid.setText(dto.getAppId().equals("null")?"":dto.getAppId());
			 appSecret.setText(dto.getAppSecret().equals("null")?"":dto.getAppSecret());
			 tokens.setText(dto.getTokens().equals("null")?"":dto.getTokens());
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
