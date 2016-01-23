package com.herotculb.qunhaichat.crm.querychance;

import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import cn.pedant.SweetAlert.SweetAlertDialog;

import com.herotculb.qunhaichat.HomeActivity;
import com.herotculb.qunhaichat.R;
import com.herotculb.qunhaichat.crm.CreateChanceWindow;
import com.herotculb.qunhaichat.dto.ChanceListDto;
import com.herotculb.qunhaichat.widget.LoadingDialog;

public class GetChanceHandler extends Handler {
	Activity context;
	LoadingDialog dialog;
	String type;  //判断是否查看
	View viewp;
		
	public GetChanceHandler(Activity context, LoadingDialog dialog,
			String type) {
		super();
		this.context = context;
		this.dialog = dialog;
		this.type = type;
	}

	@Override
	public void handleMessage(android.os.Message msg) {
		super.handleMessage(msg);
		Map<String, Object> map = (Map<String, Object>)msg.obj;
		boolean b=(Boolean) map.get("success");
		ChanceListDto dto = (ChanceListDto)map.get("obj");
		dialog.hide();
		dialog.dismiss();
		if(b){
			Intent i = new Intent(context, CreateChanceWindow.class);  
			Bundle b1 = new Bundle();
			Log.e("type22--", type);
			b1.putString("type", type);
			b1.putString("id", String.valueOf(dto.getId()));
			b1.putString("name", dto.getCustomerName());
			b1.putString("address", dto.getCustomerAddress());
			b1.putString("category", dto.getCustomerType());
			b1.putString("chanceClass", dto.getCustomerLevel()+"");
			b1.putString("marks", dto.getCreateManMark());
			b1.putString("description", dto.getCreayeManName());
			b1.putString("userid", String.valueOf(dto.getNotesUserId()));
			b1.putString("username", dto.getNotesUserName());
			b1.putString("glass", dto.getState()+"");
            i.putExtras(b1);  
            context.startActivityForResult(i, 0);
            context.overridePendingTransition(R.anim.modal_in, R.anim.modal_out);
		}else{
			new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
            .setTitleText("出错了！")
            .setContentText((String)map.get("info"))
            .show();
		}
	}
	
}
