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
import com.herotculb.qunhaichat.group.GetGroupCompanyUserHandler;
import com.herotculb.qunhaichat.group.GetGroupCompanyUserThread;
import com.herotculb.qunhaichat.group.window.CreateGroupWindow;
import com.herotculb.qunhaichat.homeactiviti.group.GroupManager;
import com.herotculb.qunhaichat.permission.LookUpRoleAllHandler;
import com.herotculb.qunhaichat.permission.LookUpRoleAllThread;
import com.herotculb.qunhaichat.view.listview.updown.XListView;
import com.herotculb.qunhaichat.view.listview.updown.XListView.IXListViewListener;
import com.herotculb.qunhaichat.widget.LoadingDialog;
/**
 * 选择角色
 * @author lkx
 *
 */
public class LookUpRoleAllManagerWindow extends Activity {
	private BootstrapButton query;
	private BootstrapButton cancle;
	private ListView list;	
	private BootstrapEditText groupName;
	private BootstrapEditText name;
	private BootstrapEditText groupId;
	String classes;
	String groupIdStr;
	String userId;
	int layout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 隐藏头
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.permission_lookup_role_manager_window);
		query =(BootstrapButton)findViewById(R.id.permission_lookup_role_manager_name_query);
		cancle =(BootstrapButton)findViewById(R.id.permission_lookup_role_manager_cancle);
		groupName = (BootstrapEditText)findViewById(R.id.permission_lookup_role_manager_gname);
		groupId = (BootstrapEditText)findViewById(R.id.permission_lookup_role_manager_id);
		list = (ListView)findViewById(R.id.permission_lookup_role_manager_list);
		layout = R.layout.permission_lookup_role_manager_list;
		Bundle bundle = getIntent().getExtras();
		groupIdStr = bundle.getString("groupId").toString();
		userId = bundle.getString("userId");
		classes = bundle.getString("classes");
		groupId.setText(groupIdStr);
		
		LoadingDialog dialog2 = new LoadingDialog(
				LookUpRoleAllManagerWindow.this, "正在获取数据");
		dialog2.show();
		LookUpRoleAllHandler handler = 
				new LookUpRoleAllHandler(LookUpRoleAllManagerWindow.this, list, dialog2,userId,groupIdStr,layout);
		LookUpRoleAllThread thread = 
				new LookUpRoleAllThread(LookUpRoleAllManagerWindow.this, groupIdStr, "", handler);
		thread.start();
		BootstrapButton query=(BootstrapButton) 
				LookUpRoleAllManagerWindow.this.findViewById(R.id.permission_lookup_role_manager_name_query);
		query.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO 查询
				BootstrapEditText nameedit=(BootstrapEditText) 
						LookUpRoleAllManagerWindow.this.findViewById(R.id.permission_lookup_role_manager_name);
				String namestr=nameedit.getText().toString();
				LoadingDialog dialog = new LoadingDialog(LookUpRoleAllManagerWindow.this, "正在获取数据");
				dialog.show();
				ListView view=(ListView) 
						LookUpRoleAllManagerWindow.this.findViewById(R.id.permission_lookup_role_manager_list);
				LookUpRoleAllHandler handler = 
						new LookUpRoleAllHandler(LookUpRoleAllManagerWindow.this, list, dialog,userId,groupIdStr,layout);
				LookUpRoleAllThread thread = 
						new LookUpRoleAllThread(LookUpRoleAllManagerWindow.this, groupIdStr, namestr, handler);
				thread.start();
			}
		});
		cancle.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 取消
				LookUpRoleAllManagerWindow.this.finish();
			}
		});
		
	}
	
		
}
