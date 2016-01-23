package com.herotculb.qunhaichat.weixin.getgame.adapter;

import java.util.List;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TableLayout;
import android.widget.TextView;
import cn.pedant.SweetAlert.SweetAlertDialog;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.herotculb.qunhaichat.HomeActivity;
import com.herotculb.qunhaichat.R;
import com.herotculb.qunhaichat.dto.AwardsDto;
import com.herotculb.qunhaichat.weixin.operationgame.WeixinDeleteAwardsHandler;
import com.herotculb.qunhaichat.weixin.operationgame.WeixinDeleteAwardsThread;
import com.herotculb.qunhaichat.widget.LoadingDialog;
/**
 * 奖品库设置 Adapter
 * @author lkx
 *
 */
public class WeixinGetAwardsAdapter extends BaseAdapter {
	private HomeActivity  context;
	private List<AwardsDto> list;
	
	public WeixinGetAwardsAdapter(HomeActivity context, List<AwardsDto> list) {
		super();
		this.context = context;
		this.list = list;
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
		final AwardsDto dto = (AwardsDto) getItem(position);
		LayoutInflater inflater = LayoutInflater.from(context);
		TableLayout gridLayout = (TableLayout) inflater.inflate(
				R.layout.game_awards_list, null);
		TextView text  = (TextView) gridLayout.findViewById(R.id.gameAwardsContent);
		TextView text1 = (TextView) gridLayout.findViewById(R.id.gameAwardsMarks);
		BootstrapButton del =(BootstrapButton) gridLayout.findViewById(R.id.weixin_game_delete);
		del.setOnClickListener(new OnClickListener() {
			
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
						WeixinDeleteAwardsHandler handler = new WeixinDeleteAwardsHandler(context, dialog,sDialog);
						WeixinDeleteAwardsThread thread = new WeixinDeleteAwardsThread(context,dto.getId(), handler);
						thread.start();
                    }
                })
                .show();
			}
		});
		text.setText("奖品名："+dto.getContent());
		text1.setText("说明："+dto.getMarks());
		return gridLayout;
	}

}
