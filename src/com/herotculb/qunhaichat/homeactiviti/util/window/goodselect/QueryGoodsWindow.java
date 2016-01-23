package com.herotculb.qunhaichat.homeactiviti.util.window.goodselect;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.BootstrapEditText;
import com.herotculb.qunhaichat.R;
import com.herotculb.qunhaichat.view.listview.updown.XListView;
import com.herotculb.qunhaichat.view.listview.updown.XListView.IXListViewListener;
import com.herotculb.qunhaichat.widget.LoadingDialog;
/**
 * 此窗口属于模糊查询窗口直接返回商品dto
 * 需要在intent中传入 返回的activityclass  如:
 * Intent i = new Intent(WeiXinGoodsAddWindow.this, QueryGoodsWindow.class);  
				Bundle b = new Bundle();  
				b.putString("class", "com.herotculb.qunhaichat.homeactiviti.weixin.goods.WeiXinGoodsAddWindow");
                i.putExtras(b);  
               startActivityForResult(i, 0);  
                overridePendingTransition(R.anim.modal_in, R.anim.modal_out);
 * @author Administrator
 *
 */
public class QueryGoodsWindow extends Activity {
	private Class classes;
	private String type;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 隐藏头
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.query_goods_window);
		Intent intent=getIntent();
		Bundle b=intent.getExtras();
		String classtype= b.getString("class");
		type= b.getString("type");
		Log.e("-------------", classtype);
		try {
			Class clazz = Class.forName(classtype);
			classes=clazz;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BootstrapEditText nowpage=(BootstrapEditText) findViewById(R.id.query_goods_nowpage);
		nowpage.setText("1");
		XListView view=(XListView) findViewById(R.id.queryGoods_list);
		view.setPullLoadEnable(true);
		view.setXListViewListener(new IXListViewListener() {
			
			@Override
			public void onRefresh() {
				// TODO Auto-generated method stub
				BootstrapEditText pinyin=(BootstrapEditText) findViewById(R.id.query_goods_pinyin);
				BootstrapEditText nowpage=(BootstrapEditText) findViewById(R.id.query_goods_nowpage);
				BootstrapEditText nameedit=(BootstrapEditText) findViewById(R.id.query_goods_name);
				String namestr=nameedit.getText().toString();
				int nowpagenum=Integer.parseInt(nowpage.getText().toString());
				if(nowpagenum<=1){
					nowpagenum=1;
				}else{
					nowpagenum=nowpagenum-1;
				}
				nowpage.setText(String.valueOf(nowpagenum));
				LoadingDialog dialog = new LoadingDialog(QueryGoodsWindow.this, "正在获取数据");
				dialog.show();
				XListView view=(XListView) findViewById(R.id.queryGoods_list);
				QueryGoodsHandler handler=new QueryGoodsHandler(QueryGoodsWindow.this,dialog,view,classes,QueryGoodsWindow.this.type);
				QueryGoodsThread thread=new QueryGoodsThread(QueryGoodsWindow.this,handler,namestr,pinyin.getText().toString(),String.valueOf(nowpagenum),"10");
				thread.start();
			}
			
			@Override
			public void onLoadMore() {
				// TODO Auto-generated method stub
				BootstrapEditText pinyin=(BootstrapEditText) findViewById(R.id.query_goods_pinyin);
				BootstrapEditText nowpage=(BootstrapEditText) findViewById(R.id.query_goods_nowpage);
				BootstrapEditText nameedit=(BootstrapEditText) findViewById(R.id.query_goods_name);
				String namestr=nameedit.getText().toString();
				int nowpagenum=Integer.parseInt(nowpage.getText().toString());
				Log.e("-------------------", nowpagenum+"");
				nowpagenum=nowpagenum+1;
				nowpage.setText(String.valueOf(nowpagenum));
				LoadingDialog dialog = new LoadingDialog(QueryGoodsWindow.this, "正在获取数据");
				dialog.show();
				XListView view=(XListView) findViewById(R.id.queryGoods_list);
				QueryGoodsHandler handler=new QueryGoodsHandler(QueryGoodsWindow.this,dialog,view,classes,QueryGoodsWindow.this.type);
				QueryGoodsThread thread=new QueryGoodsThread(QueryGoodsWindow.this,handler,namestr,pinyin.getText().toString(),String.valueOf(nowpagenum),"10");
				thread.start();
			}
		});
		BootstrapButton queryEnter=(BootstrapButton)findViewById(R.id.query_goods_query_enter);
		queryEnter.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				BootstrapEditText nowpage=(BootstrapEditText)findViewById(R.id.query_goods_nowpage);
				BootstrapEditText pinyin=(BootstrapEditText) findViewById(R.id.query_goods_pinyin);
				BootstrapEditText nameedit=(BootstrapEditText) findViewById(R.id.query_goods_name);
				String namestr=nameedit.getText().toString();
				nowpage.setText("1");
				LoadingDialog dialog = new LoadingDialog(QueryGoodsWindow.this, "正在获取数据");
				dialog.show();
				XListView view=(XListView) findViewById(R.id.queryGoods_list);
				QueryGoodsHandler handler=new QueryGoodsHandler(QueryGoodsWindow.this,dialog,view,classes,QueryGoodsWindow.this.type);
				QueryGoodsThread thread=new QueryGoodsThread(QueryGoodsWindow.this,handler,namestr,pinyin.getText().toString(),"1","10");
				thread.start();
			}
		});
	}
}
