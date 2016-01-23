package com.herotculb.qunhaichat.crm.querychance.window;

import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.BootstrapEditText;
import com.herotculb.qunhaichat.R;
import com.herotculb.qunhaichat.crm.AddLinkManHandler;
import com.herotculb.qunhaichat.crm.AddLinkManThread;
import com.herotculb.qunhaichat.crm.querychance.UpdateLinkManHandler;
import com.herotculb.qunhaichat.crm.querychance.UpdateLinkManThread;
import com.herotculb.qunhaichat.util.DateUtil;
import com.herotculb.qunhaichat.widget.DateActivity;
import com.herotculb.qunhaichat.widget.LoadingDialog;

public class AddLinkManWindow extends Activity {
	private BootstrapButton enter;
	private BootstrapButton cancle;
	private BootstrapButton select;
	
	private BootstrapEditText id;
	private BootstrapEditText name;
	private BootstrapEditText phone;
	private BootstrapEditText position;
	private BootstrapEditText sex;
	private BootstrapEditText birthday;
	private BootstrapEditText mark;
	
	private String type;
	private String chanceId;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 隐藏头
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.crm_chance_add_linkman_window);
		enter = (BootstrapButton)findViewById(
				R.id.chance_add_linkman_content_enter);
		cancle = (BootstrapButton)findViewById(
				R.id.chance_add_linkman_content_cancel);
		select = (BootstrapButton)findViewById(
				R.id.chance_add_linkman_birthday_button);
		
		id = (BootstrapEditText)findViewById(R.id.chance_add_linkman_id);
		name = (BootstrapEditText)findViewById(R.id.chance_add_linkman_name);
		phone = (BootstrapEditText)findViewById(R.id.chance_add_linkman_phone);
		position = (BootstrapEditText)findViewById(R.id.chance_add_linkman_position);
		sex = (BootstrapEditText)findViewById(R.id.chance_add_linkman_sex);
		birthday = (BootstrapEditText)findViewById(R.id.chance_add_linkman_birthday);
		mark = (BootstrapEditText)findViewById(R.id.chance_add_linkman_mark);
		Bundle bundle = getIntent().getExtras();
		Object idstr = (Object) bundle.get("id");
		Object namestr = (Object) bundle.get("name");
		Object phonestr = (Object) bundle.get("phone");
		Object positionstr = (Object) bundle.get("position");
		Object sexstr = (Object) bundle.get("sex");
		Object birthdaystr = (Object) bundle.get("birthday");
		Object markstr = (Object) bundle.get("mark");
		Object typestr = (Object) bundle.get("type");
		chanceId = bundle.getString("chanceId");
		sex.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO 选择性别
				Intent i = new Intent(AddLinkManWindow.this,AddLinkmanSexWindow.class);
				Bundle b = new Bundle();
				b.putString("classes", "com.herotculb.qunhaichat.crm.querychance.window.AddLinkManWindow");
				i.putExtras(b);
				AddLinkManWindow.this.startActivityForResult(i, 0);
				AddLinkManWindow.this.overridePendingTransition(R.anim.modal_in, R.anim.modal_out);
			}
		});
		type = typestr.toString();
		if(type.equals("check") || type.equals("appoint")){
			name.setEnabled(false);
			phone.setEnabled(false);
			position.setEnabled(false);
			sex.setEnabled(false);
			birthday.setEnabled(false);
			mark.setEnabled(false);
		}
		if(idstr!=null&&!idstr.equals("")){
			id.setText(idstr.toString());
			name.setText(namestr.toString());
			phone.setText(phonestr.toString());
			position.setText(positionstr.toString());
			sex.setText(sexstr.toString());
			Date date = new Date();
			Long bdate = 0L;
			String birthdayDate = "";
			String day = birthdaystr.toString();
			Log.e("day--", birthdaystr.toString());
			if(day!="null" && !day.equals("null") && day != null){
				bdate = Long.parseLong(birthdaystr.toString());
				date.setTime(bdate);
				birthdayDate = DateUtil.formatDateYYYY_MM_DD(date);
			}
			birthday.setText(birthdayDate);
			mark.setText(markstr.toString());
			enter.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO 确认修改
					LoadingDialog dialog = new LoadingDialog(AddLinkManWindow.this,"正在保存数据！");
					dialog.show();
					UpdateLinkManHandler handler = 
							new UpdateLinkManHandler(AddLinkManWindow.this, dialog);
					UpdateLinkManThread thread = 
							new UpdateLinkManThread(AddLinkManWindow.this, handler);
					thread.start();
				}
			});
		}else{
			enter.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO 确认添加
					LoadingDialog dialog = new LoadingDialog(AddLinkManWindow.this,"正在保存数据！");
					dialog.show();
					AddLinkManHandler handler = 
							new AddLinkManHandler(AddLinkManWindow.this, dialog);
					AddLinkManThread thread = 
							new AddLinkManThread(AddLinkManWindow.this,chanceId, handler);
					thread.start();
				}
			});
		}
		
		select.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO 选择生日
				Intent i = new Intent(AddLinkManWindow.this, DateActivity.class);
				Bundle b = new Bundle(); 
				b.putString("type", "birthdayDate");
                i.putExtras(b);
                AddLinkManWindow.this.startActivityForResult(i, 0);
                AddLinkManWindow.this.overridePendingTransition(R.anim.modal_in, R.anim.modal_out);
			}
		});
		
		cancle.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO 取消
				AddLinkManWindow.this.finish();
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
	        if(type.equals("birthdayDate")){
	        	TextView birthdayDate = (TextView)findViewById(R.id.chance_add_linkman_birthday);
	        	birthdayDate.setText(bundle.getString("value"));
	        }
	        TextView sex = (TextView)findViewById(R.id.chance_add_linkman_sex);
        	if(type.equals("add_linkman_sex1")){
	        	sex.setText(bundle.getString("sex1Str"));
	        }
        	if(type.equals("add_linkman_sex2")){
        		sex.setText(bundle.getString("sex2Str"));
        	}        	
	 }
	
}
