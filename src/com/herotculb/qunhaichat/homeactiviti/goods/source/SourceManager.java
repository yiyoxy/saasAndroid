package com.herotculb.qunhaichat.homeactiviti.goods.source;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.BootstrapEditText;
import com.herotculb.qunhaichat.HomeActivity;
import com.herotculb.qunhaichat.R;
import com.herotculb.qunhaichat.goods.source.GoodsQuerySourceHandler;
import com.herotculb.qunhaichat.goods.source.GoodsQuerySourceThread;
import com.herotculb.qunhaichat.goods.source.window.GoodsAddSourceWindow;
import com.herotculb.qunhaichat.view.listview.updown.XListView;
import com.herotculb.qunhaichat.view.listview.updown.XListView.IXListViewListener;
import com.herotculb.qunhaichat.widget.LoadingDialog;

public class SourceManager {
	public HomeActivity home;
	public LinearLayout linearLayout;
	int layout;
	
	public SourceManager(HomeActivity home, LinearLayout linearLayout) {
		this.home = home;
		this.linearLayout = linearLayout;
		
		//仓库信息
		LinearLayout gridLayout = (LinearLayout) home.inflater.inflate(
				R.layout.goods_source_layout, null)
				.findViewById(R.id.goods_source_main);
		linearLayout.removeAllViews();
		linearLayout.addView(gridLayout);
		BootstrapEditText nowpage=(BootstrapEditText) SourceManager.this.home.findViewById(R.id.goods_source_nowpage);
		nowpage.setText("1");
		LoadingDialog dialog = new LoadingDialog(SourceManager.this.home, "正在获取数据");
		dialog.show();
		XListView view=(XListView) SourceManager.this.home.findViewById(R.id.goods_source_list);
		layout = R.layout.goods_source_list;
		view.setPullLoadEnable(true);
		view.setXListViewListener(new IXListViewListener() {
			
			@Override
			public void onRefresh() {
				// TODO 刷新
				BootstrapEditText nowpage=(BootstrapEditText) SourceManager.this.home.findViewById(R.id.goods_source_nowpage);
				BootstrapEditText nameedit=(BootstrapEditText) SourceManager.this.home.findViewById(R.id.goods_source_name);
				String namestr=nameedit.getText().toString();
				int nowpagenum=Integer.parseInt(nowpage.getText().toString());
				if(nowpagenum<=1){
					nowpagenum=1;
				}else{
					nowpagenum=nowpagenum-1;
				}
				nowpage.setText(String.valueOf(nowpagenum));
				LoadingDialog dialog = new LoadingDialog(SourceManager.this.home, "正在获取数据");
				dialog.show();
				XListView view=(XListView) SourceManager.this.home.findViewById(R.id.goods_source_list);
				GoodsQuerySourceHandler handler = 
						new GoodsQuerySourceHandler(SourceManager.this.home, view, dialog,layout);
				GoodsQuerySourceThread thread = 
						new GoodsQuerySourceThread(SourceManager.this.home, String.valueOf(nowpagenum), "10", namestr, handler);
				thread.start();
			}
			
			@Override
			public void onLoadMore() {
				// TODO 加载更多
				BootstrapEditText nowpage=(BootstrapEditText) SourceManager.this.home.findViewById(R.id.goods_source_nowpage);
				BootstrapEditText nameedit=(BootstrapEditText) SourceManager.this.home.findViewById(R.id.goods_source_name);
				String namestr=nameedit.getText().toString();
				int nowpagenum=Integer.parseInt(nowpage.getText().toString());
				nowpage.setText(String.valueOf(nowpagenum+1));
				LoadingDialog dialog = new LoadingDialog(SourceManager.this.home, "正在获取数据");
				dialog.show();
				XListView view=(XListView) SourceManager.this.home.findViewById(R.id.goods_source_list);
				GoodsQuerySourceHandler handler = 
						new GoodsQuerySourceHandler(SourceManager.this.home, view, dialog,layout);
				GoodsQuerySourceThread thread = 
						new GoodsQuerySourceThread(SourceManager.this.home, String.valueOf(nowpagenum+1), "10", namestr, handler);
				thread.start();
			}
		});
		GoodsQuerySourceHandler handler = 
				new GoodsQuerySourceHandler(SourceManager.this.home, view, dialog,layout);
		GoodsQuerySourceThread thread = 
				new GoodsQuerySourceThread(SourceManager.this.home, "1", "10", "", handler);
		thread.start();
		BootstrapButton query=(BootstrapButton) SourceManager.this.home.findViewById(R.id.goods_source_name_query);
		query.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 按电话号查询
				BootstrapEditText nowpage=(BootstrapEditText) SourceManager.this.home.findViewById(R.id.goods_source_nowpage);
				nowpage.setText("1");
				BootstrapEditText nameedit=(BootstrapEditText) SourceManager.this.home.findViewById(R.id.goods_source_name);
				String namestr=nameedit.getText().toString();
				LoadingDialog dialog = new LoadingDialog(SourceManager.this.home, "正在获取数据");
				dialog.show();
				XListView view=(XListView) SourceManager.this.home.findViewById(R.id.goods_source_list);
				GoodsQuerySourceHandler handler=new GoodsQuerySourceHandler(SourceManager.this.home,view,dialog,layout);
				GoodsQuerySourceThread thread=new GoodsQuerySourceThread(SourceManager.this.home,"1","10",namestr,handler);
				thread.start();
			}
		});
		BootstrapButton create=(BootstrapButton) SourceManager.this.home.findViewById(R.id.goods_create_source);
		create.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 创建新供应商
				Intent i = new Intent(SourceManager.this.home, GoodsAddSourceWindow.class);  
				Bundle b = new Bundle();  
                i.putExtras(b);  
                SourceManager.this.home.startActivityForResult(i,0);
                SourceManager.this.home.overridePendingTransition(R.anim.modal_in, R.anim.modal_out);
			}
		});
	}
	
}
