package com.herotculb.qunhaichat.weixin.getvip;

import java.util.List;
import java.util.Map;

import android.os.Handler;
import android.view.View;
import android.widget.ListView;
import cn.pedant.SweetAlert.SweetAlertDialog;

import com.herotculb.qunhaichat.HomeActivity;
import com.herotculb.qunhaichat.dto.VIPSetDto;
import com.herotculb.qunhaichat.weixin.getvip.adopter.WeixinGetVipListAdapter;
import com.herotculb.qunhaichat.widget.LoadingDialog;

public class WeixinGetVipListHandler extends Handler {
	HomeActivity context;
	ListView view;
	LoadingDialog dialog;
	View viewp;
	
	public WeixinGetVipListHandler(HomeActivity context, ListView view,
			LoadingDialog dialog, View viewp) {
		super();
		this.context = context;
		this.view = view;
		this.dialog = dialog;
		this.viewp = viewp;
	}

	@Override
	public void handleMessage(android.os.Message msg) {
		super.handleMessage(msg);
		Map<String, Object> map = (Map<String, Object>)msg.obj;
		boolean b=(Boolean) map.get("success");
		dialog.hide();
		if(b){
			List<VIPSetDto> weiXinList = (List<VIPSetDto>)map.get("data");
			WeixinGetVipListAdapter adapter = new WeixinGetVipListAdapter(context, weiXinList);
			view.setAdapter(adapter);
			HomeActivity.setListViewHeightBasedOnChildren(view);
		}else{
			new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
            .setTitleText("出错了！")
            .setContentText((String)map.get("info"))
            .show();
		}
	}
}
