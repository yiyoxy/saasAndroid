package com.herotculb.qunhaichat.weixin.getauto;

import java.util.Map;

import android.content.Context;
import android.os.Handler;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class WeixinAutoLinkHandler extends Handler {
	SweetAlertDialog sDialog;
	Context context;
	public WeixinAutoLinkHandler(SweetAlertDialog sDialog, Context context) {
		super();
		this.sDialog = sDialog;
		this.context = context;
	}
	@Override
	public void handleMessage(android.os.Message msg) {
		super.handleMessage(msg);
		Map<String, Object> map = (Map<String, Object>) msg.obj;
	}
}
