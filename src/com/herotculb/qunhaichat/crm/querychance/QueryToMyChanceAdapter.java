package com.herotculb.qunhaichat.crm.querychance;

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

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.herotculb.qunhaichat.R;
import com.herotculb.qunhaichat.crm.querychance.window.QueryLinkManWindow;
import com.herotculb.qunhaichat.crm.querychance.window.QueryNotesWindow;
import com.herotculb.qunhaichat.crm.querychance.window.SelectedNotesWindow;
import com.herotculb.qunhaichat.dto.ChanceListDto;
import com.herotculb.qunhaichat.widget.LoadingDialog;

public class QueryToMyChanceAdapter extends BaseAdapter {
	private Activity  context;
	private List<ChanceListDto> list;
	int layout;
	
	public QueryToMyChanceAdapter(Activity context, List<ChanceListDto> list,
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
		final ChanceListDto dto = (ChanceListDto) getItem(position);
		LayoutInflater inflater = LayoutInflater.from(context);
		TableLayout gridLayout = (TableLayout) inflater.inflate(
				layout, null);
		if(gridLayout.findViewById(R.id.my_chance_name)!=null){
			TextView text  = (TextView) gridLayout.findViewById(R.id.my_chance_name);
			TextView text1 = (TextView) gridLayout.findViewById(R.id.my_chance_state);
			TextView text2 = (TextView) gridLayout.findViewById(R.id.my_chance_username);
			TextView text3 = (TextView) gridLayout.findViewById(R.id.my_chance_create);
			BootstrapButton update =(BootstrapButton) gridLayout.findViewById(R.id.my_chance_update);
			BootstrapButton delete =(BootstrapButton) gridLayout.findViewById(R.id.my_chance_delete);
			BootstrapButton check =(BootstrapButton) gridLayout.findViewById(R.id.my_chance_check);
			BootstrapButton appoint =(BootstrapButton) gridLayout.findViewById(R.id.my_chance_appoint);
			BootstrapButton linkman =(BootstrapButton) gridLayout.findViewById(R.id.my_chance_linkman);
			BootstrapButton record =(BootstrapButton) gridLayout.findViewById(R.id.my_chance_record);
			text.setText("客户名称："+dto.getCustomerName());
			text1.setText("客户状态："+dto.getState());
			text2.setText("指派人："+dto.getNotesUserName());
			text3.setText("创建人："+dto.getCreayeManName());
			update.setVisibility(View.GONE);
			delete.setVisibility(View.GONE);
			appoint.setVisibility(View.GONE);
			check.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO 查看
					LoadingDialog dialog = new LoadingDialog(context, "正在获取数据");
					dialog.show();
					GetChanceHandler handler = 
							new GetChanceHandler(context, dialog, "check");
					GetChanceThread thread = 
							new GetChanceThread(context, dto.getId()+"", handler);
					thread.start();
				}
			});
			linkman.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO 联系人管理
					Intent i = new Intent(context, QueryLinkManWindow.class);  
					Bundle b = new Bundle();
					b.putString("chanceId", dto.getId()+"");
					i.putExtras(b);  
		            context.startActivityForResult(i, 0);
		            context.overridePendingTransition(R.anim.modal_in, R.anim.modal_out);
				}
			});
			record.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO 开发记录管理
					Intent i = new Intent(context, QueryNotesWindow.class);  
					Bundle b = new Bundle();
					b.putString("chanceId", dto.getId()+"");
					i.putExtras(b);  
		            context.startActivityForResult(i, 0);
		            context.overridePendingTransition(R.anim.modal_in, R.anim.modal_out);
				}
			});
		}else if(gridLayout.findViewById(R.id.selected_my_chance_name)!=null){
			TextView text  = (TextView) gridLayout.findViewById(R.id.selected_my_chance_name);
			text.setText("客户名称："+dto.getCustomerName());
			BootstrapButton check =(BootstrapButton) gridLayout.findViewById(R.id.selected_my_chance_check);
			check.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO 查看记录
					Intent i = new Intent(context, SelectedNotesWindow.class);  
					Bundle b = new Bundle();
					b.putString("chanceId", dto.getId()+"");
					i.putExtras(b);  
		            context.startActivityForResult(i, 0);
		            context.overridePendingTransition(R.anim.modal_in, R.anim.modal_out);
				}
			});
		}
		
		return gridLayout;
	}

}
