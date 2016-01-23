package com.herotculb.qunhaichat.goods.store;

import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Handler;
import android.view.View;
import cn.pedant.SweetAlert.SweetAlertDialog;

import com.beardedhen.androidbootstrap.BootstrapEditText;
import com.herotculb.qunhaichat.HomeActivity;
import com.herotculb.qunhaichat.R;
import com.herotculb.qunhaichat.dto.StoreHouseDto;
import com.herotculb.qunhaichat.view.listview.updown.XListView;
import com.herotculb.qunhaichat.widget.LoadingDialog;

public class GoodsQueryStoreHouseHandler extends Handler {
	Activity context;
	XListView view;
	LoadingDialog dialog;
	View viewp;
	int layout;
	
	public GoodsQueryStoreHouseHandler(Activity context, XListView view,
			LoadingDialog dialog, int layout) {
		super();
		this.context = context;
		this.view = view;
		this.dialog = dialog;
		this.layout = layout;
	}

	@Override
	public void handleMessage(android.os.Message msg) {
		super.handleMessage(msg);
		Map<String, Object> map = (Map<String, Object>)msg.obj;
		boolean b=(Boolean) map.get("success");
		dialog.hide();
		dialog.dismiss();
		view.stopRefresh();
		view.stopLoadMore();
		view.setRefreshTime("刚刚");
		BootstrapEditText nowpage= null;
		if(context.findViewById(R.id.goods_store_house_nowpage)!=null){
			nowpage=(BootstrapEditText) context.findViewById(R.id.goods_store_house_nowpage);
		}else{
			nowpage=(BootstrapEditText) context.findViewById(R.id.crm_store_house_nowpage);
		}
		if(b){
			List<StoreHouseDto> weiXinList = (List<StoreHouseDto>) map.get("data");
			GoodsQueryStoreHouseAdapter adapter = new GoodsQueryStoreHouseAdapter(context, weiXinList,layout);
			view.setAdapter(adapter);
			 int nowpagenum=Integer.parseInt(nowpage.getText().toString());
			 int pagenum=Integer.parseInt(map.get("pagenum").toString())+1;
			 if(nowpagenum>=pagenum){
				
				 nowpage.setText(String.valueOf(pagenum));
			 }
//			HomeActivity.setListViewHeightBasedOnChildren(view);
//			Log.e("-handleMessage---", "----");
		}else{
			new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
            .setTitleText("出错了！")
            .setContentText((String)map.get("info"))
            .show();
		}
	}
	
}
