package com.herotculb.qunhaichat.employee.manager;

import java.util.Date;
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
import com.herotculb.qunhaichat.dto.ConpanyUserDto;
import com.herotculb.qunhaichat.dto.GoodsSourceDto;
import com.herotculb.qunhaichat.goods.operationsource.GoodsDeleteSourceHandler;
import com.herotculb.qunhaichat.goods.operationsource.GoodsDeleteSourceThread;
import com.herotculb.qunhaichat.goods.source.window.GoodsAddSourceWindow;
import com.herotculb.qunhaichat.goods.source.window.GoodsQuerySourceLinkManWindow;
import com.herotculb.qunhaichat.util.DateUtil;
import com.herotculb.qunhaichat.widget.LoadingDialog;

public class QueryCustomerAdapter extends BaseAdapter {
	private Activity  context;
	private List<ConpanyUserDto> list;
	int layout;
	
	public QueryCustomerAdapter(Activity context, List<ConpanyUserDto> list,
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
		final ConpanyUserDto dto = (ConpanyUserDto) getItem(position);
		LayoutInflater inflater = LayoutInflater.from(context);
		TableLayout gridLayout = (TableLayout) inflater.inflate(
				layout, null);
		if(gridLayout.findViewById(R.id.query_customer_name)!=null){
			TextView name  = (TextView) gridLayout.findViewById(R.id.query_customer_name);
			TextView email = (TextView) gridLayout.findViewById(R.id.query_customer_email);
			TextView date = (TextView) gridLayout.findViewById(R.id.query_customer_date);
			TextView useLogin = (TextView) gridLayout.findViewById(R.id.query_customer_useLogin);
			TextView phone = (TextView) gridLayout.findViewById(R.id.query_customer_phone);
			TextView sex = (TextView) gridLayout.findViewById(R.id.query_customer_sex);
			BootstrapButton update =(BootstrapButton) gridLayout.findViewById(R.id.query_customer_update);
			name.setText("名称："+dto.getTrueName());
			email.setText("邮箱："+dto.getEmail());
			String dateStr = "";
			if(dto.getAccuntStartDate()!="null" && dto.getAccuntStartDate()!=null
					&& !dto.getAccuntStartDate().equals("")){
				Date date1 = new Date();
				Long adate = Long.parseLong(dto.getAccuntStartDate());
				date1.setTime(adate);
				dateStr = DateUtil.formatDateYYYY_MM_DD(date1);
			}
			date.setText("时间："+dateStr);
			String useStr = "可用";
			if(!dto.isUseLogin()){
				useStr = "不可用";
			}
			String sexStr = "男";
			if(!dto.isSex()){
				sexStr = "女";
			}
			useLogin.setText("可用："+useStr);
			phone.setText("电话："+dto.getPhone());
			sex.setText("性别："+sexStr);
			update.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO 修改
					Intent i = new Intent(context, LookCustomerInfoWindow.class);  
					Bundle b = new Bundle();
					b.putString("id", String.valueOf(dto.getId()));
					b.putString("username", dto.getUsername());
					b.putString("name", dto.getTrueName());
					b.putString("email", dto.getEmail());
					b.putString("sex", dto.isSex()+"");
					b.putString("price", dto.getPrice()+"");
					b.putString("address", dto.getAddress());
					b.putString("state", dto.getState());
					b.putString("idnum", dto.getIdNum());
					b.putString("useLogin", dto.isUseLogin()+"");
					b.putString("phone", dto.getPhone());
					b.putString("marks", dto.getMarks());
					b.putString("image", dto.getImage());
					b.putString("idimage", dto.getIdImage());
	                i.putExtras(b);  
	                context.startActivity(i);
	                context.overridePendingTransition(R.anim.modal_in, R.anim.modal_out);
				}
			});
		}
		return gridLayout;
	}

}
