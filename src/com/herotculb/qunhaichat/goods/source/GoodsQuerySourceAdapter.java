package com.herotculb.qunhaichat.goods.source;

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
import com.herotculb.qunhaichat.crm.notes.window.CrmGoodsSourceLinkManWindow;
import com.herotculb.qunhaichat.dto.GoodsSourceDto;
import com.herotculb.qunhaichat.goods.operationsource.GoodsDeleteSourceHandler;
import com.herotculb.qunhaichat.goods.operationsource.GoodsDeleteSourceThread;
import com.herotculb.qunhaichat.goods.source.window.GoodsAddSourceWindow;
import com.herotculb.qunhaichat.goods.source.window.GoodsQuerySourceLinkManWindow;
import com.herotculb.qunhaichat.widget.LoadingDialog;

public class GoodsQuerySourceAdapter extends BaseAdapter {
	private Activity  context;
	private List<GoodsSourceDto> list;
	int layout;
	
	public GoodsQuerySourceAdapter(Activity context, List<GoodsSourceDto> list,
			int layout) {
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
		final GoodsSourceDto dto = (GoodsSourceDto) getItem(position);
		LayoutInflater inflater = LayoutInflater.from(context);
		TableLayout gridLayout = (TableLayout) inflater.inflate(
				layout, null);
		if(gridLayout.findViewById(R.id.source_name)!=null){
			TextView text  = (TextView) gridLayout.findViewById(R.id.source_name);
			TextView text1 = (TextView) gridLayout.findViewById(R.id.source_address);
			BootstrapButton linkMan =(BootstrapButton) gridLayout.findViewById(R.id.source_get_linkman);
			BootstrapButton update =(BootstrapButton) gridLayout.findViewById(R.id.source_update);
			BootstrapButton delete =(BootstrapButton) gridLayout.findViewById(R.id.source_delete);
			text.setText("名称："+dto.getName());
			text1.setText("地址："+dto.getAddress());
			linkMan.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO 查看联系人
					Intent i = new Intent(context, GoodsQuerySourceLinkManWindow.class);  
					Bundle b = new Bundle();
					b.putString("sourceid", String.valueOf(dto.getId()));
	                i.putExtras(b); 
	                context.startActivityForResult(i, 0);  
	                context.overridePendingTransition(R.anim.modal_in, R.anim.modal_out);
				}
			});
			update.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO 修改
					Intent i = new Intent(context, GoodsAddSourceWindow.class);  
					Bundle b = new Bundle();
					b.putString("id", String.valueOf(dto.getId()));
					b.putString("name", dto.getName());
					b.putString("address", dto.getAddress());
	                i.putExtras(b);  
	                context.startActivity(i);
	                context.overridePendingTransition(R.anim.modal_in, R.anim.modal_out);
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
							GoodsDeleteSourceHandler handler = 
									new GoodsDeleteSourceHandler(context, dialog,sDialog);
							GoodsDeleteSourceThread thread = 
									new GoodsDeleteSourceThread(context,String.valueOf(dto.getId()), handler);
							thread.start();
	                    }
	                })
	                .show();
				}
			});
		}else{
			TextView text  = (TextView) gridLayout.findViewById(R.id.crm_source_name);
			TextView text1 = (TextView) gridLayout.findViewById(R.id.crm_source_address);
			BootstrapButton linkMan =(BootstrapButton) gridLayout.findViewById(R.id.crm_source_get_linkman);
			BootstrapButton select =(BootstrapButton) gridLayout.findViewById(R.id.crm_source_selected_linkman);
			text.setText("名称："+dto.getName());
			text1.setText("地址："+dto.getAddress());
			linkMan.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO 查看联系人
					Intent i = new Intent(context, CrmGoodsSourceLinkManWindow.class);  
					Bundle b = new Bundle();
					b.putString("sourceid", String.valueOf(dto.getId()));
	                i.putExtras(b); 
	                context.startActivityForResult(i, 0);  
	                context.overridePendingTransition(R.anim.modal_in, R.anim.modal_out);
				}
			});
			select.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO 选中
					Intent i = new Intent(context, CrmGoodsSourceLinkManWindow.class);  
			        Bundle b2 = new Bundle();
			        b2.putString("type", "selected_goods_source");
			        b2.putString("goodsSourceName", dto.getName());
			        b2.putString("goodsSourceId", dto.getId()+"");
			        i.putExtras(b2);
			        context.setResult(context.RESULT_OK, i);
			        context.finish();
				}
			});
		}		
		return gridLayout;
	}

}
