package com.herotculb.qunhaichat.goods.store.window;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.BootstrapEditText;
import com.herotculb.qunhaichat.R;
import com.herotculb.qunhaichat.goods.operationstore.GoodsAddStoreHouseHandler;
import com.herotculb.qunhaichat.goods.operationstore.GoodsAddStoreHouseThread;
import com.herotculb.qunhaichat.goods.operationstore.GoodsUpdateStoreHouseHandler;
import com.herotculb.qunhaichat.goods.operationstore.GoodsUpdateStoreHouseThread;
import com.herotculb.qunhaichat.widget.LoadingDialog;
/**
 * 仓库操作-创建新仓库
 * @author lkx
 *
 */
public class GoodsAddStoreHouseWindow extends Activity {
	private BootstrapButton enter;
	private BootstrapButton calcel;
	private BootstrapButton useridButton;
	private BootstrapButton clear;
		
	private BootstrapEditText id;
	private BootstrapEditText name;
	private BootstrapEditText address;
	private BootstrapEditText tel;
	private BootstrapEditText userid;
	private BootstrapEditText username;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 隐藏头
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.goods_add_store_house_window);
		enter =(BootstrapButton)findViewById(R.id.goods_store_house_content_enter);
		calcel = (BootstrapButton)findViewById(R.id.goods_store_house_content_cancel);
		useridButton =(BootstrapButton)findViewById(R.id.goods_store_house_userid_button);
		clear = (BootstrapButton)findViewById(R.id.goods_store_house_clear);
		id = (BootstrapEditText)findViewById(R.id.goods_store_house_id);
		name = (BootstrapEditText)findViewById(R.id.goods_store_house_name);
		address = (BootstrapEditText)findViewById(R.id.goods_store_house_address);
		tel = (BootstrapEditText)findViewById(R.id.goods_store_house_tel);
		userid = (BootstrapEditText)findViewById(R.id.goods_store_house_userid);
		username = (BootstrapEditText)findViewById(R.id.goods_store_house_username);
		Bundle bundle = getIntent().getExtras();
		Object idstr = (Object) bundle.get("id");
		Object namestr = (Object) bundle.get("name");
		Object addressstr = (Object) bundle.get("address");
		Object telstr = (Object) bundle.get("tel");
		Object useridstr = (Object) bundle.get("userid");
		Object usernamestr = (Object) bundle.get("username");
		if(idstr!=null&&!idstr.equals("")){
			id.setText(idstr.toString());
			name.setText(namestr.toString());
			address.setText(addressstr.toString());
			tel.setText(telstr.toString());
			userid.setText(useridstr.toString());
			username.setText(usernamestr.toString());
			enter.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO 自动生成的方法存根
					LoadingDialog dialog2 = new LoadingDialog(
							GoodsAddStoreHouseWindow.this, "正在获取数据");
					dialog2.show();
					GoodsUpdateStoreHouseHandler handler =  
					new GoodsUpdateStoreHouseHandler(GoodsAddStoreHouseWindow.this, dialog2);
					GoodsUpdateStoreHouseThread thread = 
							new GoodsUpdateStoreHouseThread(GoodsAddStoreHouseWindow.this, handler);
					thread.start();
				}
			});
		}else{
			enter.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO 自动生成的方法存根
					LoadingDialog dialog2 = new LoadingDialog(
							GoodsAddStoreHouseWindow.this, "正在获取数据");
					dialog2.show();
					GoodsAddStoreHouseHandler handler =  
							new GoodsAddStoreHouseHandler(GoodsAddStoreHouseWindow.this, dialog2);
					GoodsAddStoreHouseThread thread = 
							new GoodsAddStoreHouseThread(GoodsAddStoreHouseWindow.this, handler);
					thread.start();
				}
			});
		}
		
		calcel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				GoodsAddStoreHouseWindow.this.finish();
			}
		});
		
		useridButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 选择保管员
				Intent i = new Intent(GoodsAddStoreHouseWindow.this, GetUserInGroupWindow.class);  
				Bundle b = new Bundle(); 
				b.putString("classes", "com.herotculb.qunhaichat.goods.store.window.GoodsAddStoreHouseWindow");
				i.putExtras(b);  
				GoodsAddStoreHouseWindow.this.startActivityForResult(i,0);
				GoodsAddStoreHouseWindow.this.overridePendingTransition(R.anim.modal_in, R.anim.modal_out);
			}
		});
		clear.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 清空
				userid.setText("");
				username.setText("");
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
	        if(type.equals("select_username_group")){
	        	String userId = bundle.getString("userId");
	        	userid.setText(userId);
	        	String userName = bundle.getString("userName");
	        	username.setText(userName);
	        }
	 }
	
}
