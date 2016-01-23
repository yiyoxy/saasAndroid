package com.herotculb.qunhaichat.homeactiviti.weixin.goods;

import java.util.List;
import java.util.Map;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Handler;
import cn.pedant.SweetAlert.SweetAlertDialog;

import com.herotculb.qunhaichat.dto.WeiXinGoodsDto;
import com.herotculb.qunhaichat.view.listview.updown.XListView;
import com.herotculb.qunhaichat.widget.LoadingDialog;

public class WeiXinGoodsUpDownHandler extends Handler{
	Activity context;
	LoadingDialog dialog;
	XListView view;
	String b1;
	public WeiXinGoodsUpDownHandler(Activity context2,
			LoadingDialog dialog,XListView view,String b) {
		super();
		this.context = context2;
		this.dialog = dialog;
		this.view=view;
		this.b1=b;
	}
	@SuppressLint("ResourceAsColor")
	@Override
	public void handleMessage(android.os.Message msg) {
		Map<String, Object> map = (Map<String, Object>) msg.obj;
		Boolean b=(Boolean) map.get("success");
		dialog.hide();
		dialog.dismiss();
		if(b){
			 List<WeiXinGoodsDto> list=(List<WeiXinGoodsDto>) map.get("data");
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
