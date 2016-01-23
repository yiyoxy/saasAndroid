package com.herotculb.qunhaichat.permission;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TableLayout;
import android.widget.TextView;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.herotculb.qunhaichat.R;
import com.herotculb.qunhaichat.dto.SoftPermissionDto;
import com.herotculb.qunhaichat.permission.operation.AddPermissionToRoleHandler;
import com.herotculb.qunhaichat.permission.operation.AddPermissionToRoleThread;
import com.herotculb.qunhaichat.permission.window.LookUpPermissionByRoleWindow;
import com.herotculb.qunhaichat.permission.window.LookUpPermissionMainSubWindow;
import com.herotculb.qunhaichat.widget.LoadingDialog;

public class LookUpPermissionMainAdapter extends BaseAdapter {
	private Activity  context;
	private List<SoftPermissionDto> list;
	String groupId;
	String roleId;
	int layout;
	
	public LookUpPermissionMainAdapter(Activity context,
			List<SoftPermissionDto> list, String groupId, String roleId,
			int layout) {
		super();
		this.context = context;
		this.list = list;
		this.groupId = groupId;
		this.roleId = roleId;
		this.layout = layout;
	}

	@Override
	public int getCount() {
		// TODO 自动生成的方法存根
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO 自动生成的方法存根
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO 自动生成的方法存根
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO 自动生成的方法存根
		final SoftPermissionDto dto = (SoftPermissionDto) getItem(position);
		LayoutInflater inflater = LayoutInflater.from(context);
		TableLayout gridLayout = (TableLayout) inflater.inflate(
				layout, null);
		if(gridLayout.findViewById(R.id.permission_name) != null){
			TextView text  = (TextView) gridLayout.findViewById(R.id.permission_name);
			TextView text1  = (TextView) gridLayout.findViewById(R.id.permission_marks);
			BootstrapButton search =(BootstrapButton) gridLayout.findViewById(R.id.permission_search);
			BootstrapButton addAll =(BootstrapButton) gridLayout.findViewById(R.id.permission_add_all);
			text.setText("权限名："+dto.getFunctionName());
			text1.setText("备注："+dto.getMarks());
			search.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO 查看子权限
					Intent i = new Intent(context, LookUpPermissionMainSubWindow.class);  
					Bundle b = new Bundle();
					b.putString("id", dto.getId()+"");
					b.putString("groupId", groupId);
					b.putString("roleId", roleId);
					i.putExtras(b);  
					context.startActivityForResult(i,0);
					context.overridePendingTransition(R.anim.modal_in, R.anim.modal_out);
				}
			});
			addAll.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO 添加全部子权限
					LoadingDialog dialog = new LoadingDialog(context,"正在获取数据！");
					dialog.show();
					AddPermissionToRoleHandler handler = 
							new AddPermissionToRoleHandler(context, dialog);
					AddPermissionToRoleThread thread = 
							new AddPermissionToRoleThread(context, "true", 
									String.valueOf(dto.getId()), roleId, handler);
					thread.start();
				}
			});
		}else if(gridLayout.findViewById(R.id.permission_sub_name) != null){
			TextView text  = (TextView) gridLayout.findViewById(R.id.permission_sub_name);
			TextView text1  = (TextView) gridLayout.findViewById(R.id.permission_sub_marks);
			BootstrapButton add =(BootstrapButton) gridLayout.findViewById(R.id.permission_sub_add);
			text.setText("权限名："+dto.getFunctionName());
			text1.setText("备注："+dto.getMarks());
			add.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO 添加权限
					LoadingDialog dialog = new LoadingDialog(context,"正在获取数据！");
					dialog.show();
					AddPermissionToRoleHandler handler = 
							new AddPermissionToRoleHandler(context, dialog);
					AddPermissionToRoleThread thread = 
							new AddPermissionToRoleThread(context, "false", 
									String.valueOf(dto.getId()), roleId, handler);
					thread.start();
				}
			});
		}
		
		return gridLayout;
	}

}
