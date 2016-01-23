package com.herotculb.qunhaichat.weixin.getvip.adopter;

import java.util.List;

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
import com.herotculb.qunhaichat.HomeActivity;
import com.herotculb.qunhaichat.R;
import com.herotculb.qunhaichat.dto.VIPSetDto;
import com.herotculb.qunhaichat.weixin.operationvip.window.VipCreateClassWindowActivity;

public class WeixinGetVipListAdapter extends BaseAdapter {
	private HomeActivity  context;
	private List<VIPSetDto> list;
	
	public WeixinGetVipListAdapter(HomeActivity context, List<VIPSetDto> list) {
		super();
		this.context = context;
		this.list = list;
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
		final VIPSetDto dto = (VIPSetDto) getItem(position);
		LayoutInflater inflater = LayoutInflater.from(context);
		TableLayout gridLayout = (TableLayout) inflater.inflate(
				R.layout.vip_class_list, null);
		TextView text  = (TextView) gridLayout.findViewById(R.id.name);
		TextView text1 = (TextView) gridLayout.findViewById(R.id.marks);
		TextView text2 = (TextView) gridLayout.findViewById(R.id.vip_list_score);
		BootstrapButton update =(BootstrapButton) gridLayout.findViewById(R.id.weixin_vip_list_update);
		update.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				Intent i = new Intent(context, VipCreateClassWindowActivity.class);  
				Bundle b = new Bundle();  
				b.putString("id", String.valueOf(dto.getId()));
				b.putString("name", dto.getName());
				b.putString("marks", dto.getMarks());
				b.putString("score", String.valueOf(dto.getScore()));
                i.putExtras(b);  
                context.startActivityForResult(i,0);
                context.overridePendingTransition(R.anim.modal_in, R.anim.modal_out);
			}
		});
		text.setText("等级名称："+dto.getName());
		text1.setText("简介："+dto.getMarks());
		text2.setText("积分到达："+dto.getScore());
		return gridLayout;
	}

}
