package com.herotculb.qunhaichat.goods.operationstore;

import java.util.List;
import java.util.Map;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.beardedhen.androidbootstrap.BootstrapEditText;
import com.herotculb.qunhaichat.R;
import com.herotculb.qunhaichat.goods.store.window.GoodsAddStoreHouseWindow;
import com.herotculb.qunhaichat.util.QNPermissionApiImpl;

public class GoodsUpdateStoreHouseThread extends Thread {
	private GoodsAddStoreHouseWindow context;
	private Handler handler;
	
	public GoodsUpdateStoreHouseThread(GoodsAddStoreHouseWindow context, Handler handler) {
		super();
		this.context = context;
		this.handler = handler;
	}

	@Override
	public void run() {
		super.run();
		Looper.prepare();
		
		BootstrapEditText idtext =(BootstrapEditText) context.findViewById(R.id.goods_store_house_id);
		BootstrapEditText nametext =(BootstrapEditText) context.findViewById(R.id.goods_store_house_name);
		BootstrapEditText addresstext =(BootstrapEditText) context.findViewById(R.id.goods_store_house_address);
		BootstrapEditText teltext =(BootstrapEditText) context.findViewById(R.id.goods_store_house_tel);
		BootstrapEditText useridtext =(BootstrapEditText) context.findViewById(R.id.goods_store_house_userid);
		
		String id = idtext.getText().toString();
		String name = nametext.getText().toString();
		String address = addresstext.getText().toString();
		String tel= teltext.getText().toString();
		String userid= useridtext.getText().toString();
		QNPermissionApiImpl qnpAPi=new QNPermissionApiImpl(context);
		List<Map<String, Object>> list=qnpAPi.updateStoreHouse(name, address, tel, userid, id);
		Map<String,Object> map=list.iterator().next();
		Message sendmsg = Message.obtain();  
        sendmsg.obj = map;   //利用Message.obj把子线程的handle传递给主线程。  
        handler.sendMessage(sendmsg);  
	}
	
}
