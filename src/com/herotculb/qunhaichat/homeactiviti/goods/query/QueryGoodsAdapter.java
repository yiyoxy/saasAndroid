package com.herotculb.qunhaichat.homeactiviti.goods.query;

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
import com.herotculb.qunhaichat.crm.notes.AddNotesItemWindow;
import com.herotculb.qunhaichat.dto.GoodsTableDto;
import com.herotculb.qunhaichat.homeactiviti.goods.query.chart.ChartGoods;
import com.herotculb.qunhaichat.homeactiviti.goods.query.gooddata.GoodsData;
import com.herotculb.qunhaichat.homeactiviti.goods.query.goodinfo.GoodsInfo;
import com.herotculb.qunhaichat.homeactiviti.goods.query.updateprice.UpdateGoodsPrice;

public class QueryGoodsAdapter extends BaseAdapter{
	private Activity  context;
	private List<GoodsTableDto> list;
	int layout;
	
	public QueryGoodsAdapter(Activity context, List<GoodsTableDto> list,
			int layout) {
		super();
		this.context = context;
		this.list = list;
		this.layout = layout;
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
		// TODO Auto-generated method stub
		final GoodsTableDto dto=(GoodsTableDto) getItem(position);
		LayoutInflater inflater = LayoutInflater.from(context);
		TableLayout gridLayout = (TableLayout) inflater.inflate(
				layout, null);
		if(gridLayout.findViewById(R.id.query_goods_item_name)!=null){
			TextView name=(TextView) gridLayout.findViewById(R.id.query_goods_item_name);
			TextView num=(TextView) gridLayout.findViewById(R.id.query_goods_item_num);
			TextView price=(TextView) gridLayout.findViewById(R.id.query_goods_item_price);
			TextView model=(TextView) gridLayout.findViewById(R.id.query_goods_item_model);
			TextView type=(TextView) gridLayout.findViewById(R.id.query_goods_item_type);
			BootstrapButton changerPrice=(BootstrapButton) gridLayout.findViewById(R.id.query_goods_item_changerPrice);
			changerPrice.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent i = new Intent(QueryGoodsAdapter.this.context, UpdateGoodsPrice.class);  
					Bundle b = new Bundle();  
					b.putString("id", dto.getId()+"");
					b.putString("price", dto.getPrice()+"");
	                i.putExtras(b);  
	                QueryGoodsAdapter.this.context.startActivityForResult(i, 0);  
	                QueryGoodsAdapter.this.context.overridePendingTransition(R.anim.modal_in, R.anim.modal_out);
				}
			});
			BootstrapButton chart=(BootstrapButton) gridLayout.findViewById(R.id.query_goods_item_chart);
			chart.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent i = new Intent(QueryGoodsAdapter.this.context, ChartGoods.class);  
					Bundle b = new Bundle();  
					b.putString("id", dto.getId()+"");
	                i.putExtras(b);  
	                QueryGoodsAdapter.this.context.startActivityForResult(i, 0);  
	                QueryGoodsAdapter.this.context.overridePendingTransition(R.anim.modal_in, R.anim.modal_out);
				}
			});
			BootstrapButton log=(BootstrapButton) gridLayout.findViewById(R.id.query_goods_item_log);
			log.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent i = new Intent(QueryGoodsAdapter.this.context, GoodsInfo.class);  
					Bundle b = new Bundle();  
					b.putString("id", dto.getId()+"");
	                i.putExtras(b);  
	                QueryGoodsAdapter.this.context.startActivityForResult(i, 0);  
	                QueryGoodsAdapter.this.context.overridePendingTransition(R.anim.modal_in, R.anim.modal_out);
				}
			});
			BootstrapButton info=(BootstrapButton) gridLayout.findViewById(R.id.query_goods_item_info);
			info.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent i = new Intent(QueryGoodsAdapter.this.context, GoodsData.class);  
					Bundle b = new Bundle();  
					b.putString("id", dto.getId()+"");
	                i.putExtras(b);  
	                QueryGoodsAdapter.this.context.startActivityForResult(i, 0);  
	                QueryGoodsAdapter.this.context.overridePendingTransition(R.anim.modal_in, R.anim.modal_out);
				}
			});
			name.setText("品名:"+dto.getGoodsName());
			num.setText("数量:"+dto.getGoodsNum()+"");
			price.setText("售价:"+dto.getPrice()+"");
			model.setText("型号:"+dto.getGoodsModel());
			type.setText("单位:"+dto.getGoodsType());
		}else{
			TextView name=(TextView) gridLayout.findViewById(R.id.crm_query_goods_item_name);
			TextView num=(TextView) gridLayout.findViewById(R.id.crm_query_goods_item_num);
			TextView price=(TextView) gridLayout.findViewById(R.id.crm_query_goods_item_price);
			TextView model=(TextView) gridLayout.findViewById(R.id.crm_query_goods_item_model);
			TextView type=(TextView) gridLayout.findViewById(R.id.crm_query_goods_item_type);
			TextView score=(TextView) gridLayout.findViewById(R.id.crm_query_goods_item_score);
			BootstrapButton select=(BootstrapButton) gridLayout.findViewById(R.id.crm_query_goods_item_selected);
			name.setText("品名:"+dto.getGoodsName());
			num.setText("数量:"+dto.getGoodsNum()+"");
			price.setText("售价:"+dto.getPrice()+"");
			model.setText("型号:"+dto.getGoodsModel());
			type.setText("单位:"+dto.getGoodsType());
			score.setText("积分:"+dto.getScore());
			select.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO 选中
					Intent i = new Intent(context, AddNotesItemWindow.class);  
			        Bundle b2 = new Bundle();
			        b2.putString("type", "selected_goods");
			        b2.putString("goodsName", dto.getGoodsName());
			        b2.putString("goodsId", dto.getId()+"");
			        i.putExtras(b2);
			        context.setResult(context.RESULT_OK, i);
			        context.finish();
				}
			});
		}	
		
		return gridLayout;
	}

}
