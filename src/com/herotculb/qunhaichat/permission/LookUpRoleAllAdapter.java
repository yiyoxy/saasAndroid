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
import cn.pedant.SweetAlert.SweetAlertDialog;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.herotculb.qunhaichat.R;
import com.herotculb.qunhaichat.dto.RoleDto;
import com.herotculb.qunhaichat.permission.operation.AddRoleToConpanyUserHandler;
import com.herotculb.qunhaichat.permission.operation.AddRoleToConpanyUserThread;
import com.herotculb.qunhaichat.permission.operation.DeleteRoleHandler;
import com.herotculb.qunhaichat.permission.operation.DeleteRoleThread;
import com.herotculb.qunhaichat.permission.window.LookUpPermissionByRoleWindow;
import com.herotculb.qunhaichat.permission.window.LookUpRoleAllWindow;
import com.herotculb.qunhaichat.widget.LoadingDialog;

public class LookUpRoleAllAdapter extends BaseAdapter {
	private Activity  context;
	private List<RoleDto> list;
	String userId;
	String groupId;
	int layout;
	
	public LookUpRoleAllAdapter(Activity context, List<RoleDto> list,
			String userId, String groupId, int layout) {
		super();
		this.context = context;
		this.list = list;
		this.userId = userId;
		this.groupId = groupId;
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
		final RoleDto dto = (RoleDto) getItem(position);
		LayoutInflater inflater = LayoutInflater.from(context);
		TableLayout gridLayout = (TableLayout) inflater.inflate(
				layout, null);
		if(gridLayout.findViewById(R.id.role_name) != null){
			TextView text  = (TextView) gridLayout.findViewById(R.id.role_name);
			TextView text1  = (TextView) gridLayout.findViewById(R.id.role_marks);
			BootstrapButton lookPermission =(BootstrapButton) gridLayout.findViewById(R.id.role_look_permission);
			BootstrapButton delete =(BootstrapButton) gridLayout.findViewById(R.id.role_delete);
			text.setText("角色名："+dto.getName());
			text1.setText("备注："+dto.getMarks());
			lookPermission.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO 查看权限
					Intent i = new Intent(context, LookUpPermissionByRoleWindow.class);  
					Bundle b = new Bundle();
					b.putString("groupId", groupId);
					b.putString("roleId", dto.getId()+"");
	                i.putExtras(b);  
	                context.startActivityForResult(i,0);
	                context.overridePendingTransition(R.anim.modal_in, R.anim.modal_out);
				}
			});
			delete.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
	                new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
	                .setTitleText("删除")
	                .setContentText("您确定删除此信息？")
	                .setCancelText("取消")
	                .setConfirmText("确定")
	                .showCancelButton(true)
	                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
	                    @Override
	                    public void onClick(SweetAlertDialog sDialog) {
	                        // reuse previous dialog instance, keep widget user state, reset them if you need
	                        sDialog.setTitleText("取消删除")
	                                .setContentText("您的删除已经取消")
	                                .setConfirmText("确定")
	                                .showCancelButton(false)
	                                .setCancelClickListener(null)
	                                .setConfirmClickListener(null)
	                                .changeAlertType(SweetAlertDialog.ERROR_TYPE);
	                    }
	                })
	                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
	                    @Override
	                    public void onClick(SweetAlertDialog sDialog) {
	                    	LoadingDialog dialog = new LoadingDialog(context, "正在获取数据");
							dialog.show();
							DeleteRoleHandler handler = 
									new DeleteRoleHandler(context, dialog, sDialog);
							DeleteRoleThread thread = 
									new DeleteRoleThread(context, String.valueOf(dto.getId()), 
											String.valueOf(dto.getGroupid()), handler);
							thread.start();
	                    }
	                })
	                .show();
				}
			});
		}else if(gridLayout.findViewById(R.id.role_manager_name) != null){
			TextView text  = (TextView) gridLayout.findViewById(R.id.role_manager_name);
			TextView text1  = (TextView) gridLayout.findViewById(R.id.role_manager_marks);
			BootstrapButton add =(BootstrapButton) gridLayout.findViewById(R.id.role_manager_add);
			text.setText("角色名："+dto.getName());
			text1.setText("备注："+dto.getMarks());
			add.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO 添加
					LoadingDialog dialog = new LoadingDialog(context,"正在获取数据！");
					dialog.show();
					AddRoleToConpanyUserHandler handler = 
							new AddRoleToConpanyUserHandler(context, dialog);
					AddRoleToConpanyUserThread thread = 
							new AddRoleToConpanyUserThread(context, String.valueOf(dto.getId()), String.valueOf(dto.getGroupid()), userId, handler);
					thread.start();
				}
			});
		}
		
		return gridLayout;
	}

}
