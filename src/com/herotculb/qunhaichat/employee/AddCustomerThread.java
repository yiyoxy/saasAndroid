package com.herotculb.qunhaichat.employee;

import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.beardedhen.androidbootstrap.BootstrapEditText;
import com.herotculb.qunhaichat.R;
import com.herotculb.qunhaichat.goods.store.window.GoodsAddStoreHouseWindow;
import com.herotculb.qunhaichat.util.QNPermissionApiImpl;

public class AddCustomerThread extends Thread {
	private Activity context;
	private Handler handler;
	
	public AddCustomerThread(Activity context, Handler handler) {
		super();
		this.context = context;
		this.handler = handler;
	}

	@Override
	public void run() {
		super.run();
		Looper.prepare();
		
		BootstrapEditText usernametext =(BootstrapEditText) context.findViewById(R.id.customer_username);
		BootstrapEditText passwordtext =(BootstrapEditText) context.findViewById(R.id.customer_password);
		BootstrapEditText nametext =(BootstrapEditText) context.findViewById(R.id.customer_name);
		BootstrapEditText phonetext =(BootstrapEditText) context.findViewById(R.id.customer_phone);
		BootstrapEditText emailtext =(BootstrapEditText) context.findViewById(R.id.customer_email);
		BootstrapEditText useLogintext =(BootstrapEditText) context.findViewById(R.id.customer_useLogin);
		BootstrapEditText imagetext =(BootstrapEditText) context.findViewById(R.id.customer_image);
		BootstrapEditText idimagetext =(BootstrapEditText) context.findViewById(R.id.customer_idimage);
		BootstrapEditText stutetext =(BootstrapEditText) context.findViewById(R.id.customer_stute);
		BootstrapEditText idnumtext =(BootstrapEditText) context.findViewById(R.id.customer_idnum);
		BootstrapEditText sextext =(BootstrapEditText) context.findViewById(R.id.customer_sex);
		BootstrapEditText markstext =(BootstrapEditText) context.findViewById(R.id.customer_marks);
		BootstrapEditText addresstext =(BootstrapEditText) context.findViewById(R.id.customer_address);
		BootstrapEditText pricetext =(BootstrapEditText) context.findViewById(R.id.customer_price);
		
		String username = usernametext.getText().toString();
		String password = passwordtext.getText().toString();
		String name = nametext.getText().toString();
		String phone = phonetext.getText().toString();
		String email = emailtext.getText().toString();
		String useLogin = "true";
		if(useLogintext.getText().toString().equals("禁用")){
			useLogin = "false";
		}
		String image = imagetext.getText().toString();
		String idimage = idimagetext.getText().toString();
		if(image.equals("")){
			image = "略";
		}
		if(idimage.equals("")){
			idimage = "略";
		}
		String stute = stutetext.getText().toString();
		String idnum = idnumtext.getText().toString();
		String sex = "true";
		if(sextext.getText().toString().equals("女")){
			sex = "false";
		}
		String marks = markstext.getText().toString();
		String address = addresstext.getText().toString();
		String price = pricetext.getText().toString();
		
		QNPermissionApiImpl qnpAPi=new QNPermissionApiImpl(context);
		List<Map<String, Object>> list=qnpAPi.addCustemmer(username, password, name, phone,
				email, useLogin, image, idimage, stute, 
				idnum, sex, marks, address, price);
		Map<String,Object> map=list.iterator().next();
		Message sendmsg = Message.obtain();  
        sendmsg.obj = map;   //利用Message.obj把子线程的handle传递给主线程。  
        handler.sendMessage(sendmsg);  
	}
	
}
