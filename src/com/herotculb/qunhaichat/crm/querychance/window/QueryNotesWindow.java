package com.herotculb.qunhaichat.crm.querychance.window;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.BootstrapEditText;
import com.herotculb.qunhaichat.R;
import com.herotculb.qunhaichat.crm.StartNotesHandler;
import com.herotculb.qunhaichat.crm.StartNotesThread;
import com.herotculb.qunhaichat.crm.querychance.QueryNotesHandler;
import com.herotculb.qunhaichat.crm.querychance.QueryNotesThread;
import com.herotculb.qunhaichat.view.listview.updown.XListView;
import com.herotculb.qunhaichat.view.listview.updown.XListView.IXListViewListener;
import com.herotculb.qunhaichat.widget.LoadingDialog;
/**
 * 查看开发记录的window
 * @author lkx
 *
 */
public class QueryNotesWindow extends Activity {
	private XListView list;
	private BootstrapButton create;
	private String chanceId;
	int layout;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 隐藏头
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.crm_query_notes_window);		
		BootstrapEditText nowpage=(BootstrapEditText) QueryNotesWindow.this.findViewById(R.id.crm_query_notes_nowpage);
		nowpage.setText("1");
		LoadingDialog dialog = new LoadingDialog(QueryNotesWindow.this, "正在获取数据");
		dialog.show();
		Bundle b = getIntent().getExtras();
		chanceId = b.getString("chanceId");
		list=(XListView) findViewById(R.id.crm_query_notes_list);
		layout = R.layout.crm_query_notes_list;
		list.setPullLoadEnable(true);
		list.setXListViewListener(new IXListViewListener() {
			
			@Override
			public void onRefresh() {
				// TODO 刷新
				BootstrapEditText nowpage=(BootstrapEditText) QueryNotesWindow.this.findViewById(R.id.crm_query_notes_nowpage);
				BootstrapEditText nameedit=(BootstrapEditText) QueryNotesWindow.this.findViewById(R.id.crm_query_notes_name);
				String namestr=nameedit.getText().toString();
				int nowpagenum=Integer.parseInt(nowpage.getText().toString());
				if(nowpagenum<=1){
					nowpagenum=1;
				}else{
					nowpagenum=nowpagenum-1;
				}
				nowpage.setText(String.valueOf(nowpagenum));
				LoadingDialog dialog = new LoadingDialog(QueryNotesWindow.this, "正在获取数据");
				dialog.show();
				XListView view=(XListView) QueryNotesWindow.this.findViewById(R.id.crm_query_notes_list);
				QueryNotesHandler handler = 
						new QueryNotesHandler(QueryNotesWindow.this, view, dialog,layout);
				QueryNotesThread thread = 
						new QueryNotesThread(QueryNotesWindow.this, String.valueOf(nowpagenum), "10", namestr, chanceId, handler);
				thread.start();
			}
			
			@Override
			public void onLoadMore() {
				// TODO 加载更多
				BootstrapEditText nowpage=(BootstrapEditText) QueryNotesWindow.this.findViewById(R.id.crm_query_notes_nowpage);
				BootstrapEditText nameedit=(BootstrapEditText) QueryNotesWindow.this.findViewById(R.id.crm_query_notes_name);
				String namestr=nameedit.getText().toString();
				int nowpagenum=Integer.parseInt(nowpage.getText().toString());
				nowpage.setText(String.valueOf(nowpagenum+1));
				LoadingDialog dialog = new LoadingDialog(QueryNotesWindow.this, "正在获取数据");
				dialog.show();
				XListView view=(XListView) QueryNotesWindow.this.findViewById(R.id.crm_query_notes_list);
				QueryNotesHandler handler = 
						new QueryNotesHandler(QueryNotesWindow.this, view, dialog,layout);
				QueryNotesThread thread = 
						new QueryNotesThread(QueryNotesWindow.this, String.valueOf(nowpagenum+1), "10", namestr, chanceId, handler);
				thread.start();
			}
		});
		QueryNotesHandler handler = 
				new QueryNotesHandler(QueryNotesWindow.this, list, dialog,layout);
		QueryNotesThread thread = 
				new QueryNotesThread(QueryNotesWindow.this, "1", "10", "", chanceId, handler);
		thread.start();
		BootstrapButton query=(BootstrapButton) QueryNotesWindow.this.findViewById(R.id.crm_query_notes_name_query);
		query.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 按电话号查询
				BootstrapEditText nowpage=(BootstrapEditText) QueryNotesWindow.this.findViewById(R.id.crm_query_notes_nowpage);
				nowpage.setText("1");
				BootstrapEditText nameedit=(BootstrapEditText) QueryNotesWindow.this.findViewById(R.id.crm_query_notes_name);
				String namestr=nameedit.getText().toString();
				LoadingDialog dialog = new LoadingDialog(QueryNotesWindow.this, "正在获取数据");
				dialog.show();
				XListView view=(XListView) QueryNotesWindow.this.findViewById(R.id.crm_query_notes_list);
				QueryNotesHandler handler = 
						new QueryNotesHandler(QueryNotesWindow.this, view, dialog,layout);
				QueryNotesThread thread = 
						new QueryNotesThread(QueryNotesWindow.this, "1", "10", namestr, chanceId, handler);
				thread.start();
			}
		});
		BootstrapButton cancle=(BootstrapButton) QueryNotesWindow.this.findViewById(R.id.crm_query_notes_name_cancle);
		cancle.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO 取消
				QueryNotesWindow.this.finish();
			}
		});
		create =(BootstrapButton) QueryNotesWindow.this.findViewById(R.id.crm_query_notes_new);
		create.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 创建新开发记录
				LoadingDialog dialog = new LoadingDialog(QueryNotesWindow.this,"正在获取数据!");
				dialog.show();
				StartNotesHandler handler = 
						new StartNotesHandler(QueryNotesWindow.this, dialog);
				StartNotesThread thread = 
						new StartNotesThread(QueryNotesWindow.this, chanceId, handler);
				thread.start();
			}
		});
		
	}
	
	//弹出框返回的内容在这里接受
	 protected void onActivityResult(int requestCode, int resultCode, Intent data) {  
	        super.onActivityResult(requestCode, resultCode, data);
	      //取出字符串  
	        if(data==null){
	        	return ;
	        }
	        Bundle bundle = data.getExtras();	        
	        if(bundle==null){
	        	return ;
	        }
	        String type=(String) bundle.get("type");
	        if(type.equals("add_source_notes")){
	        	BootstrapButton b = (BootstrapButton)findViewById(R.id.crm_query_notes_name_query);
	        	b.performClick();
	        }
	 }
}
