package com.herotculb.qunhaichat.weixin.gailan.huifushezhi.autoset.item;

import java.util.List;
import java.util.Map;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.widget.ListView;
import cn.pedant.SweetAlert.SweetAlertDialog;

import com.herotculb.qunhaichat.HomeActivity;
import com.herotculb.qunhaichat.dto.WeiXinAutoReSendItemDTO;
import com.herotculb.qunhaichat.weixin.gailan.window.AutoResendSetItemWindow;
import com.herotculb.qunhaichat.widget.LoadingDialog;

public class AutoResendSetItemHandler extends Handler{
	AutoResendSetItemWindow context;
	LoadingDialog dialog;
	ListView view;
	public AutoResendSetItemHandler(AutoResendSetItemWindow context, ListView view
			,LoadingDialog dialog) {
		super();
		this.context = context;
		this.dialog = dialog;
		this.view=view;
	}
	@SuppressLint("ResourceAsColor")
	@Override
	public void handleMessage(android.os.Message msg) {
		Map<String, Object> map = (Map<String, Object>) msg.obj;
		Boolean b=(Boolean) map.get("success");
		dialog.hide();
		dialog.dismiss();
		if(b){
			List<WeiXinAutoReSendItemDTO> list=(List<WeiXinAutoReSendItemDTO>) map.get("data");
			autoResendadapter adapter = new autoResendadapter(context,list);
			 view.setAdapter(adapter);
			 HomeActivity.setListViewHeightBasedOnChildren(view);
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
