package com.herotculb.qunhaichat.weixin.gailan.window;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.BootstrapEditText;
import com.herotculb.qunhaichat.R;
import com.herotculb.qunhaichat.dto.WeiXinReSendDto;
import com.herotculb.qunhaichat.weixin.gailan.huifushezhi.item.AutoResendHandlerItem;
import com.herotculb.qunhaichat.weixin.gailan.huifushezhi.item.AutoResendThreadItem;
import com.herotculb.qunhaichat.widget.LoadingDialog;

public class AutoResendSetItemQueryWindow extends Activity {
	private BootstrapEditText idedit;
	private BootstrapButton add;
	private ListView list;
	private BootstrapButton text;
	private BootstrapButton image;
	private BootstrapButton image_text;
	private BootstrapButton voice;
	private BootstrapButton video;
	private BootstrapButton music;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 隐藏头
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.weixin_gailan_autoresoure_item_query_window);
		list=(ListView) findViewById(R.id.weixin_gailan_autoresoure_item_query_list);
		idedit=(BootstrapEditText) findViewById(R.id.weixin_gailan_autoresoure_item_query_id);
		text=(BootstrapButton) findViewById(R.id.weixin_gailan_autoresoure_item_query_text);
		image=(BootstrapButton) findViewById(R.id.weixin_gailan_autoresoure_item_query_image);
		image_text=(BootstrapButton) findViewById(R.id.weixin_gailan_autoresoure_item_query_imagetext);
		voice=(BootstrapButton) findViewById(R.id.weixin_gailan_autoresoure_item_query_voice);
		video=(BootstrapButton) findViewById(R.id.weixin_gailan_autoresoure_item_query_video);
		music=(BootstrapButton) findViewById(R.id.weixin_gailan_autoresoure_item_query_music);
		Bundle bundle = getIntent().getExtras();
		long id = bundle.getLong("id");
		idedit.setText(String.valueOf(id));
		text.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
            	LoadingDialog dialog = new LoadingDialog(AutoResendSetItemQueryWindow.this, "正在获取数据");
				dialog.show();
				AutoResendHandlerItem handler=new AutoResendHandlerItem(AutoResendSetItemQueryWindow.this, WeiXinReSendDto.TEXT, list, dialog);
				AutoResendThreadItem thread=new AutoResendThreadItem(AutoResendSetItemQueryWindow.this, WeiXinReSendDto.TEXT, handler);
				thread.start();
			}
		});
		image.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				LoadingDialog dialog = new LoadingDialog(AutoResendSetItemQueryWindow.this, "正在获取数据");
				dialog.show();
				AutoResendHandlerItem handler=new AutoResendHandlerItem(AutoResendSetItemQueryWindow.this, WeiXinReSendDto.IMAGE, list, dialog);
				AutoResendThreadItem thread=new AutoResendThreadItem(AutoResendSetItemQueryWindow.this, WeiXinReSendDto.IMAGE, handler);
				thread.start();
			}
		});
		image_text.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				LoadingDialog dialog = new LoadingDialog(AutoResendSetItemQueryWindow.this, "正在获取数据");
				dialog.show();
				AutoResendHandlerItem handler=new AutoResendHandlerItem(AutoResendSetItemQueryWindow.this, WeiXinReSendDto.IMAGE_TEXT, list, dialog);
				AutoResendThreadItem thread=new AutoResendThreadItem(AutoResendSetItemQueryWindow.this, WeiXinReSendDto.IMAGE_TEXT, handler);
				thread.start();
			}
		});
		video.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				LoadingDialog dialog = new LoadingDialog(AutoResendSetItemQueryWindow.this, "正在获取数据");
				dialog.show();
				AutoResendHandlerItem handler=new AutoResendHandlerItem(AutoResendSetItemQueryWindow.this, WeiXinReSendDto.VIDEO, list, dialog);
				AutoResendThreadItem thread=new AutoResendThreadItem(AutoResendSetItemQueryWindow.this, WeiXinReSendDto.VIDEO, handler);
				thread.start();
			}
		});
		voice.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				LoadingDialog dialog = new LoadingDialog(AutoResendSetItemQueryWindow.this, "正在获取数据");
				dialog.show();
				AutoResendHandlerItem handler=new AutoResendHandlerItem(AutoResendSetItemQueryWindow.this, WeiXinReSendDto.VOICE, list, dialog);
				AutoResendThreadItem thread=new AutoResendThreadItem(AutoResendSetItemQueryWindow.this, WeiXinReSendDto.VOICE, handler);
				thread.start();
			}
		});
		music.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				LoadingDialog dialog = new LoadingDialog(AutoResendSetItemQueryWindow.this, "正在获取数据");
				dialog.show();
				AutoResendHandlerItem handler=new AutoResendHandlerItem(AutoResendSetItemQueryWindow.this, WeiXinReSendDto.MUSIC, list, dialog);
				AutoResendThreadItem thread=new AutoResendThreadItem(AutoResendSetItemQueryWindow.this, WeiXinReSendDto.MUSIC, handler);
				thread.start();
			}
		});
	}
	
}
