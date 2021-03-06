package com.herotculb.qunhaichat.group;

import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Handler;
import android.view.View;
import android.widget.ListView;
import cn.pedant.SweetAlert.SweetAlertDialog;

import com.beardedhen.androidbootstrap.BootstrapEditText;
import com.herotculb.qunhaichat.R;
import com.herotculb.qunhaichat.dto.ConpanyGroupDto;
import com.herotculb.qunhaichat.goods.store.window.GetUserInGroupWindow;
import com.herotculb.qunhaichat.widget.LoadingDialog;

public class GetUserInGroupHandler extends Handler {
	Activity context;
	ListView view;
	LoadingDialog dialog;
	String classes;   
	String isGroup;   // 当值为"false"查看用户名，当值为"true"选中
	View viewp;
	int layout;
	
	public GetUserInGroupHandler(Activity context, ListView view,
			LoadingDialog dialog, String classes, String isGroup, int layout) {
		super();
		this.context = context;
		this.view = view;
		this.dialog = dialog;
		this.classes = classes;
		this.isGroup = isGroup;
		this.layout = layout;
	}

	@Override
	public void handleMessage(android.os.Message msg) {
		super.handleMessage(msg);
		Map<String, Object> map = (Map<String, Object>)msg.obj;
		boolean b=(Boolean) map.get("success");
		dialog.hide();
		dialog.dismiss();
//		BootstrapEditText nowpage=(BootstrapEditText) context.findViewById(R.id.goods_store_house_nowpage);
		if(b){
			List<ConpanyGroupDto> weiXinList = (List<ConpanyGroupDto>) map.get("data");
			GetUserInGroupAdapter adapter = new GetUserInGroupAdapter(context, weiXinList,classes,isGroup,layout);
			view.setAdapter(adapter);
		}else{
			new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
            .setTitleText("出错了！")
            .setContentText((String)map.get("info"))
            .show();
		}
	}
	
}
