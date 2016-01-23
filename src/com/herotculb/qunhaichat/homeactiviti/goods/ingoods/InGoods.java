package com.herotculb.qunhaichat.homeactiviti.goods.ingoods;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import cn.pedant.SweetAlert.SweetAlertDialog;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.herotculb.qunhaichat.HomeActivity;
import com.herotculb.qunhaichat.R;
import com.herotculb.qunhaichat.dto.OrdersItemDto;
import com.herotculb.qunhaichat.homeactiviti.goods.orderquery.QueryOrderWindow;
import com.herotculb.qunhaichat.homeactiviti.goods.outgoods.OutGoods;
import com.herotculb.qunhaichat.homeactiviti.util.window.goodselect.ErWeiMaQueryGoodsWindow;
import com.herotculb.qunhaichat.homeactiviti.util.window.goodselect.QueryGoodsWindow;
import com.herotculb.qunhaichat.print.PrintUtil;
import com.herotculb.qunhaichat.widget.LoadingDialog;

public class InGoods {
	public HomeActivity home;
	public LinearLayout linearLayout;
	public InGoods(HomeActivity home, LinearLayout linearLayout) {
		this.home = home;
		this.linearLayout = linearLayout;
		//仓库信息
		LinearLayout gridLayout = (LinearLayout) home.inflater.inflate(
				R.layout.good_ingoods, null)
				.findViewById(R.id.good_ingoods_main);
		linearLayout.removeAllViews();
		linearLayout.addView(gridLayout);
		BootstrapButton query=(BootstrapButton)InGoods.this.home.findViewById(R.id.good_ingoods_main_query);
		BootstrapButton erweima=(BootstrapButton)InGoods.this.home.findViewById(R.id.good_ingoods_main_erweima);
		BootstrapButton save=(BootstrapButton)InGoods.this.home.findViewById(R.id.good_ingoods_main_save);
		BootstrapButton ruke=(BootstrapButton)InGoods.this.home.findViewById(R.id.good_ingoods_main_ruku);
		BootstrapButton print=(BootstrapButton)InGoods.this.home.findViewById(R.id.good_ingoods_main_print);
		BootstrapButton neworder=(BootstrapButton)InGoods.this.home.findViewById(R.id.good_ingoods_main_newOrder);
		BootstrapButton queryOrder=(BootstrapButton)InGoods.this.home.findViewById(R.id.good_ingoods_main_query_order);
		queryOrder.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(InGoods.this.home, QueryOrderWindow.class);  
				Bundle b = new Bundle();  
				b.putString("class", "com.herotculb.qunhaichat.HomeActivity");
				b.putString("type", "inOrderQuery");
				b.putString("querytype", "inOrderQuery");
                i.putExtras(b);
                InGoods.this.home.startActivityForResult(i, 0);
                InGoods.this.home.overridePendingTransition(R.anim.modal_in, R.anim.modal_out);
			}
		});
		neworder.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				newOrder();
        		
			}
		});
		save.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(InGoods.this.home.isruku){
					 new SweetAlertDialog(InGoods.this.home, SweetAlertDialog.ERROR_TYPE)
					 .setTitleText("失败")
		             .setContentText("此订单已经入过库不可修改")
		             .setConfirmText("确定")
		             .showCancelButton(false)
		             .setCancelClickListener(null)
		             .setConfirmClickListener(null).show();
					 return ;
				}
				TextView idtext=(TextView) InGoods.this.home.findViewById(R.id.orderId);
				String id=idtext.getText().toString();
				if(id.trim().equals("")){
					id="0";
				}
				// TODO Auto-generated method stub
				StringBuffer jsonbuff=new StringBuffer();
				jsonbuff.append("[");
				for(int i=0;i<InGoods.this.home.goodstabledtoList.size();i++){
					if(i!=0){
						jsonbuff.append(",");
					}
					OrdersItemDto ord=InGoods.this.home.goodstabledtoList.get(i);
					jsonbuff.append("{");
					jsonbuff.append("'name':").append("'").append(ord.getGoodsName()).append("',");
					jsonbuff.append("'type':").append("'").append(ord.getGoodsType()).append("',");
					jsonbuff.append("'price':").append("'").append(ord.getPrice()).append("',");
					jsonbuff.append("'inprice':").append("'").append(ord.getPrice()).append("',");
					jsonbuff.append("'score':").append("'").append(ord.getScore()).append("',");
					jsonbuff.append("'spell':").append("'").append(ord.getSpell()).append("',");
					jsonbuff.append("'model':").append("'").append(ord.getGoodsModel()).append("',");
					jsonbuff.append("'goodRource':").append("'").append(ord.getGoodsSourceName()).append("',");
					jsonbuff.append("'storeHouse':").append("'").append(ord.getGoodsToStorehouseId()).append("',");
					jsonbuff.append("'marks':").append("'").append(ord.getMarks()).append("',");
					jsonbuff.append("'num':").append("'").append(ord.getGoodsNum()).append("',");
					jsonbuff.append("'countPrice':").append("'").append(ord.getCountPrice()).append("'");
					jsonbuff.append("}");
				}
				jsonbuff.append("]");
				LoadingDialog dialog = new LoadingDialog(InGoods.this.home, "正在获取数据");
				dialog.show();
				SubmitOrderHandler handler=new SubmitOrderHandler(InGoods.this.home,dialog,true);
				SubmitOrderThread thread=new SubmitOrderThread(InGoods.this.home,handler,jsonbuff.toString(),true,"0");
				thread.start();
			}
		});
		ruke.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(InGoods.this.home.isruku){
					 new SweetAlertDialog(InGoods.this.home, SweetAlertDialog.ERROR_TYPE)
					 .setTitleText("失败")
		             .setContentText("此订单已经入过库不可修改")
		             .setConfirmText("确定")
		             .showCancelButton(false)
		             .setCancelClickListener(null)
		             .setConfirmClickListener(null).show();
					 return ;
				}
				TextView idtext=(TextView) InGoods.this.home.findViewById(R.id.orderId);
				String id=idtext.getText().toString();
				if(id.trim().equals("")){
					id="0";
				}
				StringBuffer jsonbuff=new StringBuffer();
				jsonbuff.append("[");
				for(int i=0;i<InGoods.this.home.goodstabledtoList.size();i++){
					if(i!=0){
						jsonbuff.append(",");
					}
					OrdersItemDto ord=InGoods.this.home.goodstabledtoList.get(i);
					jsonbuff.append("{");
					jsonbuff.append("'name':").append("'").append(ord.getGoodsName()).append("',");
					jsonbuff.append("'type':").append("'").append(ord.getGoodsType()).append("',");
					jsonbuff.append("'price':").append("'").append(ord.getPrice()).append("',");
					jsonbuff.append("'inprice':").append("'").append(ord.getPrice()).append("',");
					jsonbuff.append("'score':").append("'").append(ord.getScore()).append("',");
					jsonbuff.append("'spell':").append("'").append(ord.getSpell()).append("',");
					jsonbuff.append("'model':").append("'").append(ord.getGoodsModel()).append("',");
					jsonbuff.append("'goodRource':").append("'").append(ord.getGoodsSourceName()).append("',");
					jsonbuff.append("'storeHouse':").append("'").append(ord.getGoodsToStorehouseId()).append("',");
					jsonbuff.append("'marks':").append("'").append(ord.getMarks()).append("',");
					jsonbuff.append("'num':").append("'").append(ord.getGoodsNum()).append("',");
					jsonbuff.append("'countPrice':").append("'").append(ord.getCountPrice()).append("'");
					jsonbuff.append("}");
				}
				jsonbuff.append("]");
				LoadingDialog dialog = new LoadingDialog(InGoods.this.home, "正在获取数据");
				dialog.show();
				SubmitOrderHandler handler=new SubmitOrderHandler(InGoods.this.home,dialog,false);
				SubmitOrderThread thread=new SubmitOrderThread(InGoods.this.home,handler,jsonbuff.toString(),false,"0");
				thread.start();
			}
		});
		print.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				PrintUtil print=PrintUtil.getPrintUtil(InGoods.this.home);
				if(!print.isLink()){
					print.linkPrint(null);
				}
				print.printText("\n\n");
				TextView scoreNum=(TextView) InGoods.this.home.findViewById(R.id.good_ingoods_scoreNum);
	     		String num=scoreNum.getText().toString();
	     		TextView scoreStr=(TextView) InGoods.this.home.findViewById(R.id.good_ingoods_scoreStr);
				if(scoreStr.getText().toString().trim().equals("")){
					 new SweetAlertDialog(InGoods.this.home, SweetAlertDialog.SUCCESS_TYPE)
					 .setTitleText("成功")
		             .setContentText("请入库订单之后才可以打印销售订单")
		             .setConfirmText("确定")
		             .showCancelButton(false)
		             .setCancelClickListener(null)
		             .setConfirmClickListener(null).show();
					 return ;
				}
				print.printText("品名          数量  单价  总价\n");
				for(int i=0;i<InGoods.this.home.goodstabledtoList.size();i++){
					OrdersItemDto ord=InGoods.this.home.goodstabledtoList.get(i);
					print.printText(ord.getGoodsName()+"  "+ord.getGoodsNum()+"  "+ord.getPrice()+"  "+ord.getCountPrice()+"\n");
				}
				TextView conpricettext=(TextView) InGoods.this.home.findViewById(R.id.good_ingoods_zongjia);
				print.printText(num+"\n");
				print.printText(conpricettext.getText().toString()+"\n");
				print.printText("积分序列号:"+scoreStr.getText().toString()+"\n");
				print.printText("请链接本店wifi下载本店会员app进行积分兑换。\n");
				print.printText("\n\n\n\n\n\n\n\n\n\n");
			}
		});
		query.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(InGoods.this.home.isruku){
					newOrder();
				}
				// TODO Auto-generated method stub
				Intent i = new Intent(InGoods.this.home, QueryGoodsWindow.class);  
				Bundle b = new Bundle();  
				b.putString("class", "com.herotculb.qunhaichat.HomeActivity");
				b.putString("type", "inOrderQuery");
                i.putExtras(b);  
                InGoods.this.home.startActivityForResult(i, 0);  
                InGoods.this.home.overridePendingTransition(R.anim.modal_in, R.anim.modal_out);
			}
		});
		erweima.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(InGoods.this.home.isruku){
					newOrder();
				}
				// TODO Auto-generated method stub
				Intent i = new Intent(InGoods.this.home, ErWeiMaQueryGoodsWindow.class);  
				Bundle b = new Bundle();  
				b.putString("class", "com.herotculb.qunhaichat.HomeActivity");
				b.putString("type", "inOrderQuery");
                i.putExtras(b);  
                InGoods.this.home.startActivityForResult(i, 0);  
                InGoods.this.home.overridePendingTransition(R.anim.modal_in, R.anim.modal_out);
			}
		});
	}
	public void newOrder(){
		TextView idtext=(TextView) InGoods.this.home.findViewById(R.id.orderId);
 		idtext.setText("0");
		InGoods.this.home.isruku=false;
		TableLayout datascroll=(TableLayout)InGoods.this.home.findViewById(R.id.good_ingoods_data_list);
		if(InGoods.this.home.goodstabledtoList!=null){
			InGoods.this.home.goodstabledtoList.clear();
			InGoods.this.home.showOrderList(datascroll, InGoods.this.home.goodstabledtoList, InGoods.this.home);
		}
		ScrollView scroll=(ScrollView)InGoods.this.home.findViewById(R.id.good_ingoods_list);

		
		TableRow manager=(TableRow)InGoods.this.home.findViewById(R.id.good_order_manager);
		manager.setVisibility(View.GONE);
		datascroll.setVisibility(View.VISIBLE);
		scroll.setVisibility(View.GONE);
		 BootstrapButton save=(BootstrapButton)InGoods.this.home.findViewById(R.id.good_ingoods_main_save);
			BootstrapButton ruke=(BootstrapButton)InGoods.this.home.findViewById(R.id.good_ingoods_main_ruku);
			
			save.setVisibility(View.VISIBLE);
			ruke.setVisibility(View.VISIBLE);
	}
}
