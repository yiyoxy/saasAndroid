package com.herotculb.qunhaichat.homeactiviti.weixin.goods;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.herotculb.qunhaichat.HomeActivity;
import com.herotculb.qunhaichat.R;
import com.herotculb.qunhaichat.homeactiviti.util.window.goodselect.ErWeiMaQueryGoodsWindow;
import com.herotculb.qunhaichat.homeactiviti.util.window.goodselect.QueryGoodsWindow;
import com.herotculb.qunhaichat.widget.LoadingDialog;

public class WeiXinGoodsAddWindow extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 隐藏头
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.weixin_goods_layout_add_window);
		BootstrapButton query=(BootstrapButton) findViewById(R.id.weixin_goods_add_query);
		BootstrapButton erweima=(BootstrapButton) findViewById(R.id.weixin_goods_add_query_erweima);
		BootstrapButton enter=(BootstrapButton) findViewById(R.id.weixin_goods_add_enter);
		BootstrapButton back=(BootstrapButton) findViewById(R.id.weixin_goods_add_back);
		query.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(WeiXinGoodsAddWindow.this, QueryGoodsWindow.class);  
				Bundle b = new Bundle();  
				b.putString("class", "com.herotculb.qunhaichat.homeactiviti.weixin.goods.WeiXinGoodsAddWindow");
                i.putExtras(b);  
                startActivityForResult(i, 0);  
                overridePendingTransition(R.anim.modal_in, R.anim.modal_out);
			}
		});
		erweima.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(WeiXinGoodsAddWindow.this, ErWeiMaQueryGoodsWindow.class);  
				Bundle b = new Bundle();  
				b.putString("class", "com.herotculb.qunhaichat.homeactiviti.weixin.goods.WeiXinGoodsAddWindow");
                i.putExtras(b);  
               startActivityForResult(i, 0);  
                overridePendingTransition(R.anim.modal_in, R.anim.modal_out);
			}
		});
		enter.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				TextView name=(TextView) findViewById(R.id.weixin_goods_main_add_name);
	        	TextView types=(TextView) findViewById(R.id.weixin_goods_main_add_type);
	        	TextView model=(TextView) findViewById(R.id.weixin_goods_main_add_model);
	        	TextView tiaoma=(TextView) findViewById(R.id.weixin_goods_main_add_tiaoma);
	        	TextView price=(TextView) findViewById(R.id.weixin_goods_main_add_price);
	        	TextView inprice=(TextView) findViewById(R.id.weixin_goods_main_add_inprice);
	        	TextView score=(TextView) findViewById(R.id.weixin_goods_main_add_score);
	        	TextView pinyin=(TextView) findViewById(R.id.weixin_goods_main_add_pinyin);
	        	TextView image1=(TextView) findViewById(R.id.weixin_goods_main_add_image1);
	        	TextView image2=(TextView) findViewById(R.id.weixin_goods_main_add_image2);
	        	TextView image3=(TextView) findViewById(R.id.weixin_goods_main_add_image3);
	        	TextView image4=(TextView) findViewById(R.id.weixin_goods_main_add_image4);
	        	LoadingDialog dialog = new LoadingDialog(WeiXinGoodsAddWindow.this, "正在获取数据");
				dialog.show();
				WeiXinGoodsAddHandler handler=new WeiXinGoodsAddHandler(WeiXinGoodsAddWindow.this, dialog);
				WeiXinGoodsAddThread thread=new WeiXinGoodsAddThread(WeiXinGoodsAddWindow.this, handler,name.getText().toString(),types.getText().toString(),model.getText().toString(),tiaoma.getText().toString(),price.getText().toString(),inprice.getText().toString(),score.getText().toString(),pinyin.getText().toString(),image1.getText().toString(),image2.getText().toString(),image3.getText().toString(),image4.getText().toString());
				thread.start();
			}
		});
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(WeiXinGoodsAddWindow.this,HomeActivity.class);
				Bundle bun = new Bundle();
				bun.putString("type","cancel");
				i.putExtras(bun);
				WeiXinGoodsAddWindow.this.setResult(WeiXinGoodsAddWindow.RESULT_OK, i);
				WeiXinGoodsAddWindow.this.finish();
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
		        if(type.equals("goodsSelect")){
		        	TextView name=(TextView) findViewById(R.id.weixin_goods_main_add_name);
		        	TextView types=(TextView) findViewById(R.id.weixin_goods_main_add_type);
		        	TextView model=(TextView) findViewById(R.id.weixin_goods_main_add_model);
		        	TextView tiaoma=(TextView) findViewById(R.id.weixin_goods_main_add_tiaoma);
		        	TextView price=(TextView) findViewById(R.id.weixin_goods_main_add_price);
		        	TextView inprice=(TextView) findViewById(R.id.weixin_goods_main_add_inprice);
		        	TextView score=(TextView) findViewById(R.id.weixin_goods_main_add_score);
		        	TextView pinyin=(TextView) findViewById(R.id.weixin_goods_main_add_pinyin);
		        	TextView goodid=(TextView) findViewById(R.id.weixin_goods_main_add_goodid);
		        	 String namestr=(String) bundle.get("name");
		        	 String numstr=(String) bundle.get("num");
		        	 String pricestr=(String) bundle.get("price");
		        	 String typesstr=(String) bundle.get("types");
		        	 String tiaomastr=(String) bundle.get("tiaoma");
		        	 String idstr=(String) bundle.get("id");
		        	 String inpricestr=(String) bundle.get("inprice");
		        	 String pinyinastr=(String) bundle.get("pinyin");
		        	 String salesnumstr=(String) bundle.get("salesnum");
		        	 String scorestr=(String) bundle.get("score");
		        	 String totalinprice=(String) bundle.get("totalinprice");
		        	 String totalprice=(String) bundle.get("totalprice");
		        	 String modelstr=(String) bundle.get("model");
		        	 name.setText(namestr);
		        	 types.setText(typesstr);
		        	 model.setText(modelstr);
		        	 tiaoma.setText(tiaomastr==null?"":tiaomastr);
		        	 price.setText(pricestr);
		        	 inprice.setText(inpricestr);
		        	 score.setText(scorestr);
		        	 pinyin.setText(pinyinastr);
		        	 goodid.setText(idstr);
		        }
		        if(type.equals("goodsSelectnull")){
		        	TextView tiaoma=(TextView) findViewById(R.id.weixin_goods_main_add_tiaoma);
		        	 String tiaomastr=(String) bundle.get("codeid");
		        	 tiaoma.setText(tiaomastr==null?"":tiaomastr);
		        }
		 }
}
