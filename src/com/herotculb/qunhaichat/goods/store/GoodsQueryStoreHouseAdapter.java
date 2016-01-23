package com.herotculb.qunhaichat.goods.store;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TableLayout;
import android.widget.TextView;
import cn.pedant.SweetAlert.SweetAlertDialog;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.herotculb.qunhaichat.R;
import com.herotculb.qunhaichat.crm.notes.AddNotesItemWindow;
import com.herotculb.qunhaichat.dto.StoreHouseDto;
import com.herotculb.qunhaichat.goods.operationstore.GoodsDeleteStoreHouseHandler;
import com.herotculb.qunhaichat.goods.operationstore.GoodsDeleteStoreHouseThread;
import com.herotculb.qunhaichat.widget.LoadingDialog;

public class GoodsQueryStoreHouseAdapter extends BaseAdapter {
	private Activity  context;
	private List<StoreHouseDto> list;
	int layout;
	
	public GoodsQueryStoreHouseAdapter(Activity context,
			List<StoreHouseDto> list, int layout) {
		super();
		this.context = context;
		this.list = list;
		this.layout = layout;
	}

	@Override
	public int getCount() {
		// TODO 自动生成的方法存根
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO 自动生成的方法存根
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO 自动生成的方法存根
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO 自动生成的方法存根
		final StoreHouseDto dto = (StoreHouseDto) getItem(position);
		LayoutInflater inflater = LayoutInflater.from(context);
		TableLayout gridLayout = (TableLayout) inflater.inflate(
				layout, null);
		if(gridLayout.findViewById(R.id.store_house_name)!=null){
			TextView text  = (TextView) gridLayout.findViewById(R.id.store_house_name);
			TextView text1 = (TextView) gridLayout.findViewById(R.id.store_house_address);
			TextView text2 = (TextView) gridLayout.findViewById(R.id.store_house_manager_username);
			TextView text3 = (TextView) gridLayout.findViewById(R.id.store_house_tel);
			BootstrapButton update =(BootstrapButton) gridLayout.findViewById(R.id.store_house_update);
			BootstrapButton delete =(BootstrapButton) gridLayout.findViewById(R.id.store_house_delete);
			text.setText("名称："+dto.getName());
			text1.setText("地址："+dto.getAddress());
			text2.setText("管理人："+dto.getManagerUserName());
			text3.setText("电话："+dto.getTal());
			update.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO 修改
					LoadingDialog dialog = new LoadingDialog(context, "正在获取数据");
					dialog.show();
					GetStoreHouseHandler handler = 
							new GetStoreHouseHandler(context, dialog);
					GetStoreHouseThread thread = 
							new GetStoreHouseThread(context, String.valueOf(dto.getId()), handler);
					thread.start();
				}
			});
			delete.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
	                new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
	                .setTitleText("删除")
	                .setContentText("您确定删除此信息？")
	                .setCancelText("取消")
	                .setConfirmText("确定")
	                .showCancelButton(true)
	                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
	                    @Override
	                    public void onClick(SweetAlertDialog sDialog) {
	                        // reuse previous dialog instance, keep widget user state, reset them if you need
	                        sDialog.setTitleText("取消删除")
	                                .setContentText("您的删除已经取消")
	                                .setConfirmText("确定")
	                                .showCancelButton(false)
	                                .setCancelClickListener(null)
	                                .setConfirmClickListener(null)
	                                .changeAlertType(SweetAlertDialog.ERROR_TYPE);
	                    }
	                })
	                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
	                    @Override
	                    public void onClick(SweetAlertDialog sDialog) {
	                    	LoadingDialog dialog = new LoadingDialog(context, "正在获取数据");
	        				dialog.show();
	        				GoodsDeleteStoreHouseHandler handler = 
	        						new GoodsDeleteStoreHouseHandler(context, dialog,sDialog);
	        				GoodsDeleteStoreHouseThread thread = 
	        						new GoodsDeleteStoreHouseThread(context, handler, String.valueOf(dto.getId()));
	        				thread.start();
	                    }
	                })
	                .show();
				}
			});
		}else{
			TextView text  = (TextView) gridLayout.findViewById(R.id.crm_store_house_name);
			TextView text1 = (TextView) gridLayout.findViewById(R.id.crm_store_house_address);
			TextView text2 = (TextView) gridLayout.findViewById(R.id.crm_store_house_manager_username);
			TextView text3 = (TextView) gridLayout.findViewById(R.id.crm_store_house_tel);
			BootstrapButton select =(BootstrapButton) gridLayout.findViewById(R.id.crm_store_house_selected);
			text.setText("名称："+dto.getName());
			text1.setText("地址："+dto.getAddress());
			text2.setText("管理人："+dto.getManagerUserName());
			text3.setText("电话："+dto.getTal());
			select.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO 选择
					Intent i = new Intent(context, AddNotesItemWindow.class);  
			        Bundle b2 = new Bundle();
			        b2.putString("type", "selected_store");
			        b2.putString("storeName", dto.getName());
			        b2.putString("storeId", dto.getId()+"");
			        i.putExtras(b2);
			        context.setResult(context.RESULT_OK, i);
			        context.finish();
				}
			});
		}
		return gridLayout;
	}

}
