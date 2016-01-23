package com.herotculb.qunhaichat.homeactiviti.weixin.mapset;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.herotculb.qunhaichat.HomeActivity;
import com.herotculb.qunhaichat.R;
import com.herotculb.qunhaichat.widget.LoadingDialog;

public class LoactionListActivity extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 隐藏头
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.weixin_mapset_all_layout);
		ListView list=(ListView) findViewById(R.id.weixin_mapset_all_layout_list);
		LoadingDialog dialog = new LoadingDialog(this, "正在获取数据");
		dialog.show();
		MapSetItemHandler handler=new MapSetItemHandler(this, dialog, list);
		MapSetItemTread tread=new MapSetItemTread(this, handler);
		tread.start();
		BootstrapButton button=(BootstrapButton) findViewById(R.id.weixin_mapset_all_layout_main_back);
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(LoactionListActivity.this,
						HomeActivity.class);
				Bundle b = new Bundle();
				b.putString("type", "cancel");
				i.putExtras(b);
				LoactionListActivity.this.setResult(RESULT_OK, i);
				LoactionListActivity.this.finish();
			}
		});
	}
}
