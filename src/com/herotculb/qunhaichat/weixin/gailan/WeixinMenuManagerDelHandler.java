package com.herotculb.qunhaichat.weixin.gailan;

import java.util.Map;

import android.content.Context;
import android.os.Handler;
import android.view.View;
import cn.pedant.SweetAlert.SweetAlertDialog;

import com.herotculb.qunhaichat.widget.LoadingDialog;

public class WeixinMenuManagerDelHandler extends Handler {
	SweetAlertDialog sDialog;
	Context context;
	LoadingDialog dialog2;
	View pview;
	public WeixinMenuManagerDelHandler(SweetAlertDialog sDialog, Context context,LoadingDialog dialog2,View pview) {
		super();
		this.sDialog = sDialog;
		this.context = context;
		this.dialog2=dialog2;
		this.pview=pview;
	}
	@Override
	public void handleMessage(android.os.Message msg) {
		super.handleMessage(msg);
		Map<String, Object> map = (Map<String, Object>) msg.obj;
		
		dialog2.hide();
		dialog2.dismiss();
		boolean b=(Boolean) map.get("success");
		if(b){
			 sDialog.setTitleText("删除成功!")
             .setContentText("您删除成功了")
             .setConfirmText("确定")
             .showCancelButton(false)
             .setCancelClickListener(null)
             .setConfirmClickListener(null)
             .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
			 pview.performClick();
		}else{
			sDialog.setTitleText("删除失败!")
            .setContentText((String)map.get("info"))
            .setConfirmText("确定")
            .showCancelButton(false)
            .setCancelClickListener(null)
            .setConfirmClickListener(null)
            .changeAlertType(SweetAlertDialog.ERROR_TYPE);
		}
	}
}
