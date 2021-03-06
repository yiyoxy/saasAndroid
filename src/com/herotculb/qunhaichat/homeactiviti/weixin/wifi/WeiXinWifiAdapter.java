package com.herotculb.qunhaichat.homeactiviti.weixin.wifi;

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
import com.herotculb.qunhaichat.dto.VWiFiDto;
import com.herotculb.qunhaichat.widget.LoadingDialog;

public class WeiXinWifiAdapter extends BaseAdapter{
	private Activity  context;
	private List<VWiFiDto> list;

	public WeiXinWifiAdapter(Activity context,List<VWiFiDto> list){
		this.context=context;
		this.list=list;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final VWiFiDto dto=(VWiFiDto) getItem(position);
		LayoutInflater inflater = LayoutInflater.from(context);
		TableLayout gridLayout = (TableLayout) inflater.inflate(
				R.layout.weixin_wifi_layout_item, null);
		TextView name=(TextView) gridLayout.findViewById(R.id.weixin_wifi_layout_item_name);
		TextView open=(TextView) gridLayout.findViewById(R.id.weixin_wifi_layout_item_open);
		TextView wifirigisterOpen=(TextView) gridLayout.findViewById(R.id.weixin_wifi_layout_item_wifirigisterOpen);
		TextView tokens=(TextView) gridLayout.findViewById(R.id.weixin_wifi_layout_item_tokens);
		TextView startDate=(TextView) gridLayout.findViewById(R.id.weixin_wifi_layout_item_startDate);
		TextView memory=(TextView) gridLayout.findViewById(R.id.weixin_wifi_layout_item_memory);
		TextView runDate=(TextView) gridLayout.findViewById(R.id.weixin_wifi_layout_item_runDate);
		BootstrapButton look=(BootstrapButton) gridLayout.findViewById(R.id.weixin_wifi_layout_item_look);
		BootstrapButton update=(BootstrapButton) gridLayout.findViewById(R.id.weixin_wifi_layout_item_update);
		BootstrapButton delete=(BootstrapButton) gridLayout.findViewById(R.id.weixin_wifi_layout_item_delete);
		BootstrapButton rigister=(BootstrapButton) gridLayout.findViewById(R.id.weixin_wifi_layout_item_rigister);
		name.setText(dto.getName());
		open.setText(dto.isUseUp()?"关闭":"开启");
		wifirigisterOpen.setText(dto.isWebRigister()?"会员注册已开启":"会员注册未开启");
		rigister.setText(dto.isWebRigister()?"关闭注册会员":"开启注册会员");
		tokens.setText(dto.getTokens());
		startDate.setText(dto.getWifidog_uptime());
		memory.setText(dto.getSys_memfree());
		runDate.setText(dto.getSys_uptime());
		look.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(context, LookWifiWindow.class);  
				Bundle b = new Bundle();  
                b.putString("id",dto.getId()+"");  
                i.putExtras(b);  
                context.startActivityForResult(i, 0);  
                context.overridePendingTransition(R.anim.modal_in, R.anim.modal_out);
			}
		});
		update.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(WeiXinWifiAdapter.this.context, AddWeixinWifiWindow.class);
				Bundle b = new Bundle();  
				b.putString("id", dto.getId()+"");
				b.putString("name", dto.getName()+"");
				b.putString("token", dto.getTokens()+"");
				b.putString("dirce", dto.isUseUp()+"");
                i.putExtras(b);  
                WeiXinWifiAdapter.this.context.startActivityForResult(i, 0);
                WeiXinWifiAdapter.this.context.overridePendingTransition(R.anim.modal_in, R.anim.modal_out);
			}
		});
		delete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
	                .setTitleText("删除")
	                .setContentText("你确定要删除此信息么")
	                .setCancelText("取消")
	                .setConfirmText("确定")
	                .showCancelButton(true)
	                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
	                    @Override
	                    public void onClick(SweetAlertDialog sDialog) {
	                        // reuse previous dialog instance, keep widget user state, reset them if you need
	                        sDialog.setTitleText("删除")
	                                .setContentText("删除已取消")
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
	                    	DeleteWeiXinWiFiHandler handler = new DeleteWeiXinWiFiHandler(context,dialog,sDialog);
	                    	DeleteWeiXinWifiThread thread = new DeleteWeiXinWifiThread(context,handler,dto.getId()+"");
	                		thread.start();
	                    }
	                })
	                .show();
			}
		});
		rigister.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				LoadingDialog dialog = new LoadingDialog(context, "正在获取数据");
        		dialog.show();
        		RigisterWifiHandler handler = new RigisterWifiHandler(context,dialog);
        		RigisterWifiThread thread = new RigisterWifiThread(context,handler,dto.getId()+"");
        		thread.start();
			}
		});
		return gridLayout;
	}
}
