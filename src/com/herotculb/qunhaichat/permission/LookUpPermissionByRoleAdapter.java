package com.herotculb.qunhaichat.permission;

import java.util.List;

import android.app.Activity;
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
import com.herotculb.qunhaichat.dto.SoftPermissionDto;
import com.herotculb.qunhaichat.permission.operation.DeletePermissionToRoleHandler;
import com.herotculb.qunhaichat.permission.operation.DeletePermissionToRoleThread;
import com.herotculb.qunhaichat.widget.LoadingDialog;

public class LookUpPermissionByRoleAdapter extends BaseAdapter {
	private Activity  context;
	private List<SoftPermissionDto> list;
	private String roleId;
	
	public LookUpPermissionByRoleAdapter(Activity context,
			List<SoftPermissionDto> list, String roleId) {
		super();
		this.context = context;
		this.list = list;
		this.roleId = roleId;
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
				R.layout.permission_lookup_by_role_list, null);
		TextView text  = (TextView) gridLayout.findViewById(R.id.role_by_name);
		TextView text1  = (TextView) gridLayout.findViewById(R.id.role_by_marks);
		BootstrapButton delete =(BootstrapButton) gridLayout.findViewById(R.id.role_by_delete);
		text.setText("权限名："+dto.getFunctionName());
		text1.setText("备注："+dto.getMarks());
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
						DeletePermissionToRoleHandler handler = 
								new DeletePermissionToRoleHandler(context, dialog, sDialog);
						DeletePermissionToRoleThread thread = 
								new DeletePermissionToRoleThread(context, String.valueOf(dto.getId()), roleId, handler);
						thread.start();
                    }
                })
                .show();
			}
		});
		return gridLayout;
	}

}
