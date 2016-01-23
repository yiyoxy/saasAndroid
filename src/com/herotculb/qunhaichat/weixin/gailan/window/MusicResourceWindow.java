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

public class MusicResourceWindow extends Activity {
	private BootstrapButton enter;
	private BootstrapButton cencal;
	private BootstrapEditText name;
	private BootstrapEditText title;
	private BootstrapEditText idedit;
	private BootstrapEditText marks;
	private BootstrapEditText linkaddr;
	private BootstrapEditText gaolinkaddr;
	private BootstrapEditText image;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 隐藏头
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.weixin_gailan_autoresoure_music_window);
		enter=(BootstrapButton) findViewById(R.id.weixin_gailan_autoresoure_music_content_enter);
		cencal=(BootstrapButton) findViewById(R.id.weixin_gailan_autoresoure_music_content_cancel);
		name=(BootstrapEditText) findViewById(R.id.weixin_gailan_autoresoure_music_name);
		title=(BootstrapEditText) findViewById(R.id.weixin_gailan_autoresoure_music_title);
		idedit=(BootstrapEditText) findViewById(R.id.weixin_gailan_autoresoure_music_id);
		marks=(BootstrapEditText) findViewById(R.id.weixin_gailan_autoresoure_music_marks);
		linkaddr=(BootstrapEditText) findViewById(R.id.weixin_gailan_autoresoure_music_linkaddr);
		gaolinkaddr=(BootstrapEditText) findViewById(R.id.weixin_gailan_autoresoure_music_gaolinkaddr);
		image=(BootstrapEditText) findViewById(R.id.weixin_gailan_autoresoure_music_image);
		Bundle bundle = getIntent().getExtras();
		Object idstr = bundle.get("id");
		if(idstr!=null&&!idstr.equals("")){
			Object namestr = bundle.get("name");
			Object descriptions =  bundle.get("description");
			Object titles = bundle.get("title");
			Object musicURLs =  bundle.get("musicURL");
			Object hQMusicUrls = bundle.get("hQMusicUrl");
			Object thumbMediaIds =  bundle.get("thumbMediaId");
			name.setText((String)namestr);
			title.setText((String)titles);
			idedit.setText((String)idstr);
			marks.setText((String)descriptions);
			linkaddr.setText((String)musicURLs);
			gaolinkaddr.setText((String)hQMusicUrls);
			image.setText((String)thumbMediaIds);
enter.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					LoadingDialog dialog2 = new LoadingDialog(
							MusicResourceWindow.this, "正在获取数据");
					dialog2.show();
					AutoResendUpdateHandler handler = new AutoResendUpdateHandler(MusicResourceWindow.this,WeiXinReSendDto.MUSIC,dialog2);
					AutoResendUpdateThread thread = new AutoResendUpdateThread(MusicResourceWindow.this , handler, WeiXinReSendDto.MUSIC);
					thread.start();
				}
			});
		}else{
			enter.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					LoadingDialog dialog2 = new LoadingDialog(
							MusicResourceWindow.this, "正在获取数据");
					dialog2.show();
					AutoResendAddHandler handler = new AutoResendAddHandler(MusicResourceWindow.this,WeiXinReSendDto.MUSIC,dialog2);
					AutoResendAddThread thread = new AutoResendAddThread(MusicResourceWindow.this , handler, WeiXinReSendDto.MUSIC);
					thread.start();
				}
			});
		}
		cencal.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent( MusicResourceWindow.this, HomeActivity.class);  
		        Bundle b = new Bundle();
		        b.putString("type", "cancel");
		        i.putExtras(b);
		        MusicResourceWindow.this.setResult( MusicResourceWindow.this.RESULT_OK, i);  
		        MusicResourceWindow.this.finish();  	
			}
		});
	}
}
