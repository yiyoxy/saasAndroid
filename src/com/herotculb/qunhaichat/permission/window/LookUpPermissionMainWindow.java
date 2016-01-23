package com.herotculb.qunhaichat.permission.window;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.BootstrapEditText;
import com.herotculb.qunhaichat.R;
import com.herotculb.qunhaichat.permission.LookUpPermissionMainHandler;
import com.herotculb.qunhaichat.permission.LookUpPermissionMainThread;
import com.herotculb.qunhaichat.widget.LoadingDialog;
/**
 * 根据角色选权限
 * @author lkx
 *
 */
public class LookUpPermissionMainWindow extends Activity {
	private BootstrapButton query;
	private BootstrapButton cancle;
	private ListView list;	
	private BootstrapEditText groupName;
	private BootstrapEditText name;
	private BootstrapEditText groupId;
	String classes;
	String groupIdStr;
	String roleIdStr;
	int layout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 隐藏头
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.permission_lookup_main_window);
		query =(BootstrapButton)findViewById(R.id.permission_lookup_main_name_query);
		cancle =(BootstrapButton)findViewById(R.id.permission_lookup_main_cancle);
		groupName = (BootstrapEditText)findViewById(R.id.permission_lookup_main_gname);
		groupId = (BootstrapEditText)findViewById(R.id.permission_lookup_main_id);
		list = (ListView)findViewById(R.id.permission_lookup_main_list);
		layout = R.layout.permission_lookup_main_list;
		Bundle bundle = getIntent().getExtras();
		groupIdStr = bundle.getString("groupId").toString();
		roleIdStr = bundle.getString("roleId");
		groupId.setText(groupIdStr);
		
		LoadingDialog dialog2 = new LoadingDialog(
				LookUpPermissionMainWindow.this, "正在获取数据");
		dialog2.show();
		LookUpPermissionMainHandler handler = 
				new LookUpPermissionMainHandler(LookUpPermissionMainWindow.this, list, dialog2,groupIdStr,roleIdStr,layout);
		LookUpPermissionMainThread thread = 
				new LookUpPermissionMainThread(LookUpPermissionMainWindow.this, groupIdStr, "", "",handler);
		thread.start();
		BootstrapButton query=(BootstrapButton) 
				LookUpPermissionMainWindow.this.findViewById(R.id.permission_lookup_main_name_query);
		query.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO 查询
				BootstrapEditText nameedit=(BootstrapEditText) 
						LookUpPermissionMainWindow.this.findViewById(R.id.permission_lookup_main_name);
				String namestr=nameedit.getText().toString();
				LoadingDialog dialog = new LoadingDialog(LookUpPermissionMainWindow.this, "正在获取数据");
				dialog.show();
				ListView view=(ListView) 
						LookUpPermissionMainWindow.this.findViewById(R.id.permission_lookup_main_list);
				LookUpPermissionMainHandler handler = 
						new LookUpPermissionMainHandler(LookUpPermissionMainWindow.this, list, dialog,groupIdStr,roleIdStr,layout);
				LookUpPermissionMainThread thread = 
						new LookUpPermissionMainThread(LookUpPermissionMainWindow.this, groupIdStr, namestr,"", handler);
				thread.start();
			}
		});
		
		cancle.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 取消
				LookUpPermissionMainWindow.this.finish();
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
	        if(type.equals("add_role")){
	        	BootstrapButton b=(BootstrapButton) 
	    				LookUpPermissionMainWindow.this.findViewById(R.id.permission_lookup_main_name_query);
	    		b.performClick();
	        }
	 }
	
}
