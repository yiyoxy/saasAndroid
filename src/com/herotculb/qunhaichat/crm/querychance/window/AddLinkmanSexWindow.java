package com.herotculb.qunhaichat.crm.querychance.window;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.herotculb.qunhaichat.R;
import com.herotculb.qunhaichat.crm.CreateChanceWindow;
/**
 * 查看联系人的window
 * @author lkx
 *
 */
public class AddLinkmanSexWindow extends Activity {
	private TextView sex1;
	private TextView sex2;
	private Class clazz;
	
	String sex1Str;
	String sex2Str;
	String classes;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 隐藏头
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.crm_add_linkman_sex_window);
		Bundle bundle = getIntent().getExtras();
		classes = bundle.getString("classes");
		try {
			Class clazz1 = clazz.forName(classes);
			clazz = clazz1;
		} catch (ClassNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		sex1 = (TextView)findViewById(R.id.add_linkman_sex1);
		sex2 = (TextView)findViewById(R.id.add_linkman_sex2);
		
		sex1Str = sex1.getText().toString();
		sex2Str = sex2.getText().toString();
		
		sex1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO 返回
				Intent i = new Intent(AddLinkmanSexWindow.this,clazz);
				Bundle b = new Bundle();
				b.putString("type", "add_linkman_sex1");
				b.putString("sex1Str", sex1Str);
				i.putExtras(b);
				AddLinkmanSexWindow.this.setResult(AddLinkmanSexWindow.RESULT_OK, i);
				AddLinkmanSexWindow.this.finish();
			}
		});
		sex2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO 返回
				Intent i = new Intent(AddLinkmanSexWindow.this,clazz);
				Bundle b = new Bundle();
				b.putString("type", "add_linkman_sex2");
				b.putString("sex2Str", sex2Str);
				i.putExtras(b);
				AddLinkmanSexWindow.this.setResult(AddLinkmanSexWindow.RESULT_OK, i);
				AddLinkmanSexWindow.this.finish();
			}
		});
	}
	
}
