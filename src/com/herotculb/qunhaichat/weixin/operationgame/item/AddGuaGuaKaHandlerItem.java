package com.herotculb.qunhaichat.weixin.operationgame.item;

import java.util.List;
import java.util.Map;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.widget.ListView;
import cn.pedant.SweetAlert.SweetAlertDialog;

import com.herotculb.qunhaichat.HomeActivity;
import com.herotculb.qunhaichat.dto.AwardsDto;
import com.herotculb.qunhaichat.weixin.operationgame.window.GameAddAwardsToGuaGuaKaQueryWindow;
import com.herotculb.qunhaichat.widget.LoadingDialog;

public class AddGuaGuaKaHandlerItem extends Handler{
	GameAddAwardsToGuaGuaKaQueryWindow context;
	ListView view;
	LoadingDialog dialog;
	String type;
	
	public AddGuaGuaKaHandlerItem(GameAddAwardsToGuaGuaKaQueryWindow context,
			ListView view, LoadingDialog dialog, String type) {
		super();
		this.context = context;
		this.view = view;
		this.dialog = dialog;
		this.type = type;
	}

	@SuppressLint("ResourceAsColor")
	@Override
	public void handleMessage(android.os.Message msg) {
		Map<String, Object> map = (Map<String, Object>) msg.obj;
		Boolean b=(Boolean) map.get("success");
		dialog.hide();
		dialog.dismiss();
		if(b){
			List<AwardsDto> weiXinList = (List<AwardsDto>) map
					.get("data");
			AddGuaGuaKaItemAdapter adapter = new AddGuaGuaKaItemAdapter(context, weiXinList,type);
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
