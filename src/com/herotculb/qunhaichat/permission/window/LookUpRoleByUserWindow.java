package com.herotculb.qunhaichat.permission.window;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.BootstrapEditText;
import com.herotculb.qunhaichat.R;
import com.herotculb.qunhaichat.group.window.PermissionGetGroupCompanyUserWindow;
import com.herotculb.qunhaichat.permission.LookUpRoleByUserHandler;
import com.herotculb.qunhaichat.permission.LookUpRoleByUserThread;
import com.herotculb.qunhaichat.widget.LoadingDialog;
/**
 * 根据角色选权限
 * @author lkx
 *
 */
public class LookUpRoleByUserWindow extends Activity {
	private BootstrapButton query;
	private BootstrapButton add;
	private BootstrapButton cancle;
	private ListView list;	
	private BootstrapEditText groupName;
	private BootstrapEditText name;
	private BootstrapEditText groupId;
	String classes;
	String groupIdStr;
	String useridStr;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 隐藏头
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.permission_lookup_by_user_window);
		add =(BootstrapButton)findViewById(R.id.permission_lookup_by_user_name_add);
		cancle =(BootstrapButton)findViewById(R.id.permission_lookup_by_user_cancle);
		list = (ListView)findViewById(R.id.permission_lookup_by_user_list);
		Bundle bundle = getIntent().getExtras();
		groupIdStr = bundle.getString("groupId").toString();
		useridStr = bundle.getString("userId");
		
		LoadingDialog dialog2 = new LoadingDialog(
				LookUpRoleByUserWindow.this, "正在获取数据");
		dialog2.show();
		LookUpRoleByUserHandler handler = 
				new LookUpRoleByUserHandler(LookUpRoleByUserWindow.this, list, dialog2,useridStr);
		LookUpRoleByUserThread thread = 
				new LookUpRoleByUserThread(LookUpRoleByUserWindow.this, useridStr, groupIdStr, handler);
		thread.start();
		add.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 添加权限
				Intent i = new Intent(LookUpRoleByUserWindow.this, LookUpRoleAllManagerWindow.class);  
				Bundle b = new Bundle();
				b.putString("groupId", groupIdStr);
				b.putString("userId", useridStr);
                i.putExtras(b);  
                LookUpRoleByUserWindow.this.startActivityForResult(i,0);
                LookUpRoleByUserWindow.this.overridePendingTransition(R.anim.modal_in, R.anim.modal_out);
			}
		});
		cancle.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 取消
				LookUpRoleByUserWindow.this.finish();
			}
		});
		
	}
	
	//弹出框返回的内容在这里接受
	 protected void onActivityResult(int requestCode, int resultCode, Intent data) {  
	        super.onActivityResult(requestCode, resultCode, data); 
	        //取出字符串  
	        Log.e("rolebyuser--", "add_role_to_conpany_user");
	        if(data==null){
	        	return ;
	        }
	        Bundle bundle = data.getExtras();	        
	        if(bundle==null){
	        	return ;
	        }
	        String type=(String) bundle.get("type");
	        if(type.equals("add_role_to_conpany_user")){
	        	LoadingDialog dialog2 = new LoadingDialog(
	    				LookUpRoleByUserWindow.this, "正在获取数据");
	    		dialog2.show();
	    		LookUpRoleByUserHandler handler = 
	    				new LookUpRoleByUserHandler(LookUpRoleByUserWindow.this, list, dialog2,useridStr);
	    		LookUpRoleByUserThread thread = 
	    				new LookUpRoleByUserThread(LookUpRoleByUserWindow.this, useridStr, groupIdStr, handler);
	    		thread.start();	    		
	        }
	 }
	
}
