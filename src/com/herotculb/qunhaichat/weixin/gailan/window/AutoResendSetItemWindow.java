package com.herotculb.qunhaichat.weixin.gailan.window;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.Spinner;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.BootstrapEditText;
import com.herotculb.qunhaichat.HomeActivity;
import com.herotculb.qunhaichat.R;
import com.herotculb.qunhaichat.weixin.gailan.huifushezhi.autoset.item.AutoResendSetItemHandler;
import com.herotculb.qunhaichat.weixin.gailan.huifushezhi.autoset.item.AutoResendSetItemThread;
import com.herotculb.qunhaichat.widget.LoadingDialog;

public class AutoResendSetItemWindow extends Activity {
	private BootstrapEditText idedit;
	private BootstrapButton add;
	private Spinner spinner;
	private long id;
	private ListView list;
	private BootstrapButton canel;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 隐藏头
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.weixin_gailan_autoresoure_item_window);
	    list=(ListView) findViewById(R.id.weixin_gailan_autoresoure_item_list);
		idedit=(BootstrapEditText) findViewById(R.id.weixin_gailan_autoresoure_item_id);
		add=(BootstrapButton) findViewById(R.id.weixin_gailan_autoresoure_item_query);
		canel=(BootstrapButton) findViewById(R.id.weixin_gailan_autoresoure_item_canel);
		Bundle bundle = getIntent().getExtras();
		id = bundle.getLong("id");
		Log.e("error", id+"");
		idedit.setText(String.valueOf(id));
		LoadingDialog dialog = new LoadingDialog(this, "正在获取数据");
		dialog.show();
		AutoResendSetItemHandler handler=new AutoResendSetItemHandler(AutoResendSetItemWindow.this,list,dialog);
		AutoResendSetItemThread thread=new AutoResendSetItemThread(this, id, handler);
		thread.start();
		canel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent( AutoResendSetItemWindow.this, HomeActivity.class);  
		        Bundle b = new Bundle();
		        b.putString("type", "cancel");
		        i.putExtras(b);
		        AutoResendSetItemWindow.this.setResult( AutoResendSetItemWindow.this.RESULT_OK, i);  
		        AutoResendSetItemWindow.this.finish();  			
			}
		});
		add.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(AutoResendSetItemWindow.this, AutoResendSetItemQueryWindow.class);  
				Bundle b = new Bundle();  
				b.putLong("id",Long.parseLong(idedit.getText().toString()));
				Log.e("error", idedit.getText().toString());
                i.putExtras(b); 
                AutoResendSetItemWindow.this.startActivityForResult(i, 0);  
                AutoResendSetItemWindow.this.overridePendingTransition(R.anim.modal_in, R.anim.modal_out);
			}
		});
		
	}
	//弹出框返回的内容在这里接受
		 protected void onActivityResult(int requestCode, int resultCode, Intent data) {  
		        super.onActivityResult(requestCode, resultCode, data);  
		        LoadingDialog dialog = new LoadingDialog(this, "正在获取数据");
				dialog.show();
				AutoResendSetItemHandler handler=new AutoResendSetItemHandler(AutoResendSetItemWindow.this,list,dialog);
				AutoResendSetItemThread thread=new AutoResendSetItemThread(this, id, handler);
				thread.start();
		 }
}
