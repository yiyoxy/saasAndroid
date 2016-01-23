package com.herotculb.qunhaichat.weixin.gailan.window;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.BootstrapEditText;
import com.herotculb.qunhaichat.HomeActivity;
import com.herotculb.qunhaichat.R;
import com.herotculb.qunhaichat.dto.WeiXinReSendDto;
import com.herotculb.qunhaichat.weixin.gailan.huifuziyuanku.AutoResendAddHandler;
import com.herotculb.qunhaichat.weixin.gailan.huifuziyuanku.AutoResendAddThread;
import com.herotculb.qunhaichat.weixin.gailan.huifuziyuanku.AutoResendUpdateHandler;
import com.herotculb.qunhaichat.weixin.gailan.huifuziyuanku.AutoResendUpdateThread;
import com.herotculb.qunhaichat.widget.LoadingDialog;

public class VideoResourceWindow extends Activity {
	private BootstrapButton enter;
	private BootstrapButton cencal;
	private BootstrapEditText name;
	private BootstrapEditText content;
	private BootstrapEditText title;
	private BootstrapEditText marks;
	private BootstrapEditText idedit;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 隐藏头
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.weixin_gailan_autoresoure_video_window);
		enter=(BootstrapButton) findViewById(R.id.weixin_gailan_autoresoure_video_content_enter);
		cencal=(BootstrapButton) findViewById(R.id.weixin_gailan_autoresoure_video_content_cancel);
		idedit=(BootstrapEditText) findViewById(R.id.weixin_gailan_autoresoure_video_id);
		name=(BootstrapEditText) findViewById(R.id.weixin_gailan_autoresoure_video_name);
		content=(BootstrapEditText) findViewById(R.id.weixin_gailan_autoresoure_video_content);
		title=(BootstrapEditText) findViewById(R.id.weixin_gailan_autoresoure_video_title);
		marks=(BootstrapEditText) findViewById(R.id.weixin_gailan_autoresoure_video_marks);
		Bundle bundle = getIntent().getExtras();
		Object idstr = bundle.get("id");
		if(idstr!=null&&!idstr.equals("")){
			String id= (String) bundle.get("id");
			String names= (String) bundle.get("name");
			String descriptions= (String) bundle.get("description");
			String titles= (String) bundle.get("title");
			String mediaId= (String) bundle.get("mediaId");
			idedit.setText(id);
			name.setText(names);
			content.setText(mediaId);
			title.setText(titles);
			marks.setText(descriptions);
enter.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					LoadingDialog dialog2 = new LoadingDialog(
							VideoResourceWindow.this, "正在获取数据");
					dialog2.show();
					AutoResendUpdateHandler handler = new AutoResendUpdateHandler(VideoResourceWindow.this,WeiXinReSendDto.VIDEO,dialog2);
					AutoResendUpdateThread thread = new AutoResendUpdateThread(VideoResourceWindow.this , handler, WeiXinReSendDto.VIDEO);
					thread.start();
				}
			});
		}else{
			enter.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					LoadingDialog dialog2 = new LoadingDialog(
							VideoResourceWindow.this, "正在获取数据");
					dialog2.show();
					AutoResendAddHandler handler = new AutoResendAddHandler(VideoResourceWindow.this,WeiXinReSendDto.VIDEO,dialog2);
					AutoResendAddThread thread = new AutoResendAddThread(VideoResourceWindow.this , handler, WeiXinReSendDto.VIDEO);
					thread.start();
				}
			});
		}
		cencal.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent( VideoResourceWindow.this, HomeActivity.class);  
		        Bundle b = new Bundle();
		        b.putString("type", "cancel");
		        i.putExtras(b);
		        VideoResourceWindow.this.setResult( VideoResourceWindow.this.RESULT_OK, i);  
		        VideoResourceWindow.this.finish();  	
			}
		});
	}
}
