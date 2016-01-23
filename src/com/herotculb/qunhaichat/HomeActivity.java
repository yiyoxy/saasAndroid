package com.herotculb.qunhaichat;

import static android.view.Gravity.START;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import cn.pedant.SweetAlert.SweetAlertDialog;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.BootstrapEditText;
import com.herotculb.qunhaichat.activity.change.AjaxChangePasswordHandler;
import com.herotculb.qunhaichat.activity.change.AjaxChangePasswordThread;
import com.herotculb.qunhaichat.dto.ConpanyDTO;
import com.herotculb.qunhaichat.dto.GoodsTableDto;
import com.herotculb.qunhaichat.dto.OrdersItemDto;
import com.herotculb.qunhaichat.dto.PermissionLoadDTO;
import com.herotculb.qunhaichat.dto.UserDTO;
import com.herotculb.qunhaichat.dto.WeiXinAutoReSendMenuDto;
import com.herotculb.qunhaichat.dto.WeiXinReSendDto;
import com.herotculb.qunhaichat.employee.AddCustomerWindow;
import com.herotculb.qunhaichat.homeactiviti.QiandaoWindow;
import com.herotculb.qunhaichat.homeactiviti.crm.CrmManager;
import com.herotculb.qunhaichat.homeactiviti.crm.QueryAllChanceManager;
import com.herotculb.qunhaichat.homeactiviti.crm.QueryChanceManager;
import com.herotculb.qunhaichat.homeactiviti.crm.QueryMyCustomerManager;
import com.herotculb.qunhaichat.homeactiviti.crm.QueryToChanceManager;
import com.herotculb.qunhaichat.homeactiviti.employee.GetHuDongKongJianManager;
import com.herotculb.qunhaichat.homeactiviti.employee.MeetingManager;
import com.herotculb.qunhaichat.homeactiviti.employee.PerformanceManager;
import com.herotculb.qunhaichat.homeactiviti.employee.QueryCustomerManager;
import com.herotculb.qunhaichat.homeactiviti.goods.ingoods.DeleteOrderItem;
import com.herotculb.qunhaichat.homeactiviti.goods.ingoods.InGoods;
import com.herotculb.qunhaichat.homeactiviti.goods.ingoods.PriceAndNumListener;
import com.herotculb.qunhaichat.homeactiviti.goods.orderquery.LookOrderItemHandler;
import com.herotculb.qunhaichat.homeactiviti.goods.orderquery.LookOrderItemThread;
import com.herotculb.qunhaichat.homeactiviti.goods.outgoods.OutGoods;
import com.herotculb.qunhaichat.homeactiviti.goods.query.GoodsQuery;
import com.herotculb.qunhaichat.homeactiviti.goods.query.QueryGoodsAdapter;
import com.herotculb.qunhaichat.homeactiviti.goods.source.SourceManager;
import com.herotculb.qunhaichat.homeactiviti.goods.store.StoreManager;
import com.herotculb.qunhaichat.homeactiviti.group.GroupManager;
import com.herotculb.qunhaichat.homeactiviti.messageinfo.MessageSetManager;
import com.herotculb.qunhaichat.homeactiviti.messageinfo.ModelManager;
import com.herotculb.qunhaichat.homeactiviti.messageinfo.VipSendManager;
import com.herotculb.qunhaichat.homeactiviti.weixin.account.AccountSet;
import com.herotculb.qunhaichat.homeactiviti.weixin.approval.ImageManager;
import com.herotculb.qunhaichat.homeactiviti.weixin.duihuan.Duihuan;
import com.herotculb.qunhaichat.homeactiviti.weixin.duihuan.handler.HuoDongHandler;
import com.herotculb.qunhaichat.homeactiviti.weixin.duihuan.handler.JifenHandler;
import com.herotculb.qunhaichat.homeactiviti.weixin.duihuan.thread.HuodongThread;
import com.herotculb.qunhaichat.homeactiviti.weixin.duihuan.thread.jifenThread;
import com.herotculb.qunhaichat.homeactiviti.weixin.gailan.WeixinGailan;
import com.herotculb.qunhaichat.homeactiviti.weixin.game.GameManager;
import com.herotculb.qunhaichat.homeactiviti.weixin.goods.WeiXinGoods;
import com.herotculb.qunhaichat.homeactiviti.weixin.mapset.MapSet;
import com.herotculb.qunhaichat.homeactiviti.weixin.modelset.ModelSet;
import com.herotculb.qunhaichat.homeactiviti.weixin.order.OrderManager;
import com.herotculb.qunhaichat.homeactiviti.weixin.vip.VipManager;
import com.herotculb.qunhaichat.homeactiviti.weixin.wifi.WeiXinWiFiHandler;
import com.herotculb.qunhaichat.homeactiviti.weixin.wifi.WeiXinWifiThread;
import com.herotculb.qunhaichat.homeactiviti.weixin.wifi.WiXinWiFi;
import com.herotculb.qunhaichat.location.LocationService;
import com.herotculb.qunhaichat.location.MeetingTest;
import com.herotculb.qunhaichat.print.PrintUtil;
import com.herotculb.qunhaichat.view.listview.updown.XListView;
import com.herotculb.qunhaichat.view.listview.updown.XListView.IXListViewListener;
import com.herotculb.qunhaichat.weixin.gailan.wenzhang.WenZhangHandler;
import com.herotculb.qunhaichat.weixin.gailan.wenzhang.WenZhangSetThread;
import com.herotculb.qunhaichat.weixin.getvip.WeixinGetVipHandler;
import com.herotculb.qunhaichat.weixin.getvip.WeixinGetVipThread;
import com.herotculb.qunhaichat.widget.LoadingDialog;

/**
 * 主要用于获取用户权限
 * 
 * @author Administrator
 * 
 */
public class HomeActivity extends Activity{

	private DrawerArrowDrawable drawerArrowDrawable;
	public float offset;
	public boolean flipped;
	public ListView mLv;
	public Handler handler;
	public List<Map<String, String>> crmList;
	public List<Map<String, String>> hrList;
	public List<Map<String, String>> weixinList;
	public List<Map<String, String>> systemList;
	public List<Map<String, String>> messageList;
	public List<Map<String, String>> goodList;
	public LinearLayout linearLayout;
	public LayoutInflater inflater;
	public BootstrapButton qiandao;
	public RadioButton moreKuaijie;
	public RadioButton jiangpin;
	public RadioButton duanxin;
	public RadioButton wenzhang;
	public RadioButton jifen;
	public  List<OrdersItemDto> goodstabledtoList;
	public  String goodstabledtoListType;
	public boolean isruku=false;
	public PrintUtil print;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
			setContentView(R.layout.activity_home);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	/**
	 * 动态设置ListView的高度
	 * 
	 * @param listView
	 */
	public static void setListViewHeightBasedOnChildren(ListView listView) {
		if (listView == null)
			return;

		ListAdapter listAdapter = listView.getAdapter();
		if (listAdapter == null) {
			// pre-condition
			return;
		}

		int totalHeight = 0;
		for (int i = 0; i < listAdapter.getCount(); i++) {
			View listItem = listAdapter.getView(i, null, listView);
			listItem.measure(0, 0);
			totalHeight += listItem.getMeasuredHeight();
		}

		ViewGroup.LayoutParams params = listView.getLayoutParams();
		params.height = totalHeight
				+ (listView.getDividerHeight() * (listAdapter.getCount() - 1));
		listView.setLayoutParams(params);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if(mLv!=null){
			return ;
		}
		if(print==null){
			print=PrintUtil.getPrintUtil (this);
		}
		MeetingTest test=new MeetingTest(this);
		test.start();
		inflater = LayoutInflater.from(this);
		final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		final ImageView imageView = (ImageView) findViewById(R.id.drawer_indicator);
		final Resources resources = getResources();
		mLv = (ListView) findViewById(R.id.id_lv);
		moreKuaijie=(RadioButton)findViewById(R.id.radio_button4);
		jiangpin=(RadioButton)findViewById(R.id.radio_button0);
		duanxin=(RadioButton)findViewById(R.id.radio_button1);
		wenzhang=(RadioButton)findViewById(R.id.radio_button2);
		jifen=(RadioButton)findViewById(R.id.radio_button3);
		jiangpin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Duihuan duihuan=new Duihuan(HomeActivity.this,linearLayout);
			}
		});
		duanxin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// 短信管理
				Log.e("短信", "message");
				LinearLayout gridLayout = (LinearLayout) inflater.inflate(
						R.layout.weixin_item_menu_layout, null)
						.findViewById(R.id.weixin_item_menu_layout_List);
				linearLayout.removeAllViews();
				linearLayout.addView(gridLayout);
				ListView list = (ListView) linearLayout
						.findViewById(R.id.weixin_item_menu_layout_List_listView);
				String[] from = { "name" };
				int[] to = { R.id.menuItemName };
				SimpleAdapter adapter = new SimpleAdapter(
						HomeActivity.this, messageList, R.layout.menuitem,
						from, to);
				list.setAdapter(adapter);
				setListViewHeightBasedOnChildren(list);
//				drawer.closeDrawer(START);
				list.setOnItemClickListener(new OnItemClickListener() {
					@Override
					public void onItemClick(
							android.widget.AdapterView<?> parent,
							View view, int position, long id) {
						ListView listView = (ListView) parent;
						HashMap<String, String> map = (HashMap<String, String>) listView
								.getItemAtPosition(position);
						String tag = map.get("tag");
						if (tag.equals("message1")) {
							// 短信设置
							MessageSetManager msm = new MessageSetManager(HomeActivity.this, linearLayout);
						}
						if (tag.equals("message2")) {
							// 会员发信
							VipSendManager vsm = new VipSendManager(HomeActivity.this, linearLayout);
						}
						if (tag.equals("message3")) {
							// 短信模板
							ModelManager mm = new ModelManager(HomeActivity.this, linearLayout);
						}
						if (tag.equals("message4")) {
							// 历史信息
						}

					};
				});
			}
		});
		//更多快速 按钮
		moreKuaijie.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				init_kuaisu(drawer);
			}
		});
		//文章快速管理
		wenzhang.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				LinearLayout gridLayout = (LinearLayout) inflater.inflate(
						R.layout.kuaisu_wenzhang, null)
						.findViewById(R.id.kuaisu_wenzhang);
				linearLayout.removeAllViews();
				linearLayout.addView(gridLayout);
				drawer.closeDrawer(START);
				BootstrapEditText nowpage=(BootstrapEditText) findViewById(R.id.weixin_gailan_wenzhangManager_nowpage);
				nowpage.setText("1");
				LoadingDialog dialog = new LoadingDialog(HomeActivity.this, "正在获取数据");
				dialog.show();
				XListView view=(XListView)findViewById(R.id.weixin_gailan_wenzhang_list);
				view.setPullLoadEnable(true);
				view.setXListViewListener(new IXListViewListener() {
					
					@Override
					public void onRefresh() {
						// TODO Auto-generated method stub
						BootstrapEditText nowpage=(BootstrapEditText) findViewById(R.id.weixin_gailan_wenzhangManager_nowpage);
						BootstrapEditText nameedit=(BootstrapEditText) findViewById(R.id.weixin_gailan_wenzhangManager_name);
						String namestr=nameedit.getText().toString();
						int nowpagenum=Integer.parseInt(nowpage.getText().toString());
						if(nowpagenum<=1){
							nowpagenum=1;
						}else{
							nowpagenum=nowpagenum-1;
						}
						nowpage.setText(String.valueOf(nowpagenum));
						LoadingDialog dialog = new LoadingDialog(HomeActivity.this, "正在获取数据");
						dialog.show();
						XListView view=(XListView) findViewById(R.id.weixin_gailan_wenzhang_list);
						WenZhangHandler handler=new WenZhangHandler(HomeActivity.this,dialog,view);
						WenZhangSetThread thread=new WenZhangSetThread(HomeActivity.this,namestr,String.valueOf(nowpagenum),"10",handler);
						thread.start();
					}
					
					@Override
					public void onLoadMore() {
						// TODO Auto-generated method stub
						BootstrapEditText nowpage=(BootstrapEditText) findViewById(R.id.weixin_gailan_wenzhangManager_nowpage);
						BootstrapEditText nameedit=(BootstrapEditText) findViewById(R.id.weixin_gailan_wenzhangManager_name);
						String namestr=nameedit.getText().toString();
						int nowpagenum=Integer.parseInt(nowpage.getText().toString());
						nowpage.setText(String.valueOf(nowpagenum+1));
						LoadingDialog dialog = new LoadingDialog(HomeActivity.this, "正在获取数据");
						dialog.show();
						XListView view=(XListView) findViewById(R.id.weixin_gailan_wenzhang_list);
						WenZhangHandler handler=new WenZhangHandler(HomeActivity.this,dialog,view);
						WenZhangSetThread thread=new WenZhangSetThread(HomeActivity.this,namestr,String.valueOf(nowpagenum+1),"10",handler);
						thread.start();
					}
				});
				WenZhangHandler handler=new WenZhangHandler(HomeActivity.this,dialog,view);
				WenZhangSetThread thread=new WenZhangSetThread(HomeActivity.this,"","1","10",handler);
				thread.start();
				BootstrapButton query=(BootstrapButton) findViewById(R.id.weixin_gailan_wenzhangManager_name_query);
				query.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						BootstrapEditText nowpage=(BootstrapEditText) findViewById(R.id.weixin_gailan_wenzhangManager_nowpage);
						nowpage.setText("1");
						BootstrapEditText nameedit=(BootstrapEditText) findViewById(R.id.weixin_gailan_wenzhangManager_name);
						String namestr=nameedit.getText().toString();
						LoadingDialog dialog = new LoadingDialog(HomeActivity.this, "正在获取数据");
						dialog.show();
						XListView view=(XListView) findViewById(R.id.weixin_gailan_wenzhang_list);
						WenZhangHandler handler=new WenZhangHandler(HomeActivity.this,dialog,view);
						WenZhangSetThread thread=new WenZhangSetThread(HomeActivity.this,namestr,"1","10",handler);
						thread.start();
					}
				});
			}
		});
		//积分快速管理
		jifen.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				
			}
		});
		//签到按钮的事件
		qiandao=(BootstrapButton)findViewById(R.id.qiandao);
		qiandao.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(HomeActivity.this, QiandaoWindow.class);  
				Bundle b = new Bundle();  
                i.putExtras(b);
                HomeActivity.this.startActivityForResult(i, 0);
                HomeActivity.this.overridePendingTransition(R.anim.modal_in, R.anim.modal_out);
			}
		});
		linearLayout = (LinearLayout) findViewById(R.id.id_framelayout);
		drawerArrowDrawable = new DrawerArrowDrawable(resources);
		drawerArrowDrawable.setStrokeColor(resources
				.getColor(R.color.light_gray));
		imageView.setImageDrawable(drawerArrowDrawable);

		drawer.setDrawerListener(new DrawerLayout.SimpleDrawerListener() {
			@Override
			public void onDrawerSlide(View drawerView, float slideOffset) {
				offset = slideOffset;

				// Sometimes slideOffset ends up so close to but not quite 1 or
				// 0.
				if (slideOffset >= .995) {
					flipped = true;
					drawerArrowDrawable.setFlip(flipped);
				} else if (slideOffset <= .005) {
					flipped = false;
					drawerArrowDrawable.setFlip(flipped);
				}

				drawerArrowDrawable.setParameter(offset);
			}
		});

		imageView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (drawer.isDrawerVisible(START)) {
					drawer.closeDrawer(START);
				} else {
					drawer.openDrawer(START);
				}
			}
		});
		handler = new Handler() {
			@Override
			public void handleMessage(android.os.Message msg) {
				super.handleMessage(msg);
				Map<String, Object> map = (Map<String, Object>) msg.obj;
				
				boolean issuccess = (Boolean) map.get("success");
				if (issuccess) {
					// 获取成功
					init_kuaisu(drawer);
					boolean iscrm = (Boolean) map.get("crm");
					boolean hr = (Boolean) map.get("hr");
					boolean goods = (Boolean) map.get("goods");
					boolean weixin = (Boolean) map.get("weixin");
					boolean system = (Boolean) map.get("system");
					boolean messageInfo = (Boolean) map.get("messageInfo");
					UserDTO user = (UserDTO) map.get("user");
					List<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
					ConpanyDTO conpany = (ConpanyDTO) map.get("conpany");

					if (iscrm) {
						List<PermissionLoadDTO> pldcrm = (List<PermissionLoadDTO>) map
								.get("crmlist");
						HashMap<String, String> map2 = new HashMap<String, String>();
						String name = "客户管理";
						map2.put("name", name);
						map2.put("index", "1");
						data.add(map2);
						crmList = new ArrayList<Map<String, String>>();
						for (int i = 0; i < pldcrm.size(); i++) {
							PermissionLoadDTO pldcrmobj = pldcrm.get(i);
							Map<String, String> crmmap = new HashMap<String, String>();
							if (pldcrmobj.isIsuse()) {
								crmmap.put("name", pldcrmobj.getMarks());
								crmmap.put("tag", pldcrmobj.getName());
								crmmap.put("use",
										String.valueOf(pldcrmobj.isIsuse()));
								crmList.add(crmmap);
							}
						}
					}
					if (weixin) {
						List<PermissionLoadDTO> pldcrm = (List<PermissionLoadDTO>) map
								.get("weixintemlist");
						String name = "微信管理";
						HashMap<String, String> map2 = new HashMap<String, String>();
						map2.put("name", name);
						map2.put("index", "0");
						data.add(map2);
						weixinList = new ArrayList<Map<String, String>>();
						for (int i = 0; i < pldcrm.size(); i++) {
							PermissionLoadDTO pldcrmobj = pldcrm.get(i);
							Map<String, String> crmmap = new HashMap<String, String>();
							if (pldcrmobj.isIsuse()) {
								crmmap.put("name", pldcrmobj.getMarks());
								crmmap.put("tag", pldcrmobj.getName());
								crmmap.put("use",
										String.valueOf(pldcrmobj.isIsuse()));
								weixinList.add(crmmap);
							}
						}
					}
					if (goods) {
						List<PermissionLoadDTO> pldcrm = (List<PermissionLoadDTO>) map
								.get("goodslist");
						String name = "库存管理";
						HashMap<String, String> map2 = new HashMap<String, String>();
						map2.put("name", name);
						map2.put("index", "2");
						data.add(map2);
						goodList = new ArrayList<Map<String, String>>();
						for (int i = 0; i < pldcrm.size(); i++) {
							PermissionLoadDTO pldcrmobj = pldcrm.get(i);
							Map<String, String> crmmap = new HashMap<String, String>();
							if (pldcrmobj.isIsuse()) {
								crmmap.put("name", pldcrmobj.getMarks());
								crmmap.put("tag", pldcrmobj.getName());
								crmmap.put("use",
										String.valueOf(pldcrmobj.isIsuse()));
								goodList.add(crmmap);
							}
						}
					}
					if (hr) {
						List<PermissionLoadDTO> pldcrm = (List<PermissionLoadDTO>) map
								.get("hrlist");
						String name = "员工管理";
						HashMap<String, String> map2 = new HashMap<String, String>();
						map2.put("name", name);
						map2.put("index", "3");
						data.add(map2);
						hrList = new ArrayList<Map<String, String>>();
						for (int i = 0; i < pldcrm.size(); i++) {
							PermissionLoadDTO pldcrmobj = pldcrm.get(i);
							if (pldcrmobj.isIsuse()) {
								Map<String, String> crmmap = new HashMap<String, String>();
								crmmap.put("name", pldcrmobj.getMarks());
								crmmap.put("tag", pldcrmobj.getName());
								crmmap.put("use",
										String.valueOf(pldcrmobj.isIsuse()));
								hrList.add(crmmap);
							}
						}
					}
					if (system) {
						List<PermissionLoadDTO> pldcrm = (List<PermissionLoadDTO>) map
								.get("systemlist");
						String name = "系统管理";
						HashMap<String, String> map2 = new HashMap<String, String>();
						map2.put("name", name);
						map2.put("index", "5");
						data.add(map2);
						systemList = new ArrayList<Map<String, String>>();
						for (int i = 0; i < pldcrm.size(); i++) {
							PermissionLoadDTO pldcrmobj = pldcrm.get(i);
							Map<String, String> crmmap = new HashMap<String, String>();
							if (pldcrmobj.isIsuse()) {
								crmmap.put("name", pldcrmobj.getMarks());
								crmmap.put("tag", pldcrmobj.getName());
								crmmap.put("use",
										String.valueOf(pldcrmobj.isIsuse()));
								systemList.add(crmmap);
							}
						}
					}
					if (messageInfo) {
						List<PermissionLoadDTO> pldcrm = (List<PermissionLoadDTO>) map
								.get("messageInfolist");
						String name = "短信管理";
						HashMap<String, String> map2 = new HashMap<String, String>();
						map2.put("name", name);
						map2.put("index", "4");
						data.add(map2);
						messageList = new ArrayList<Map<String, String>>();
						for (int i = 0; i < pldcrm.size(); i++) {
							PermissionLoadDTO pldcrmobj = pldcrm.get(i);
							Map<String, String> crmmap = new HashMap<String, String>();
							if (pldcrmobj.isIsuse()) {
								crmmap.put("name", pldcrmobj.getMarks());
								crmmap.put("tag", pldcrmobj.getName());
								crmmap.put("use",
										String.valueOf(pldcrmobj.isIsuse()));
								messageList.add(crmmap);
							}
						}
					}
					String name = "账户管理";
					HashMap<String, String> map2 = new HashMap<String, String>();
					map2.put("name", name);
					map2.put("index", "6");
					data.add(map2);
					String name2 = "打印机设置";
					HashMap<String, String> map3 = new HashMap<String, String>();
					map3.put("name", name2);
					map3.put("index", "7");
					data.add(map3);
					String[] from = { "name" };
					int[] to = { R.id.menuItemName };
					SimpleAdapter adapter = new SimpleAdapter(
							HomeActivity.this, data, R.layout.menuitem, from,
							to);
					mLv.setAdapter(adapter);
				} else {
					Object login = map.get("login");
					if (login != null) {
						// 登录超时
					}
					// 其他错误
				}
			};
		};
		mLv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				ListView listView = (ListView) parent;
				HashMap<String, String> map = (HashMap<String, String>) listView
						.getItemAtPosition(position);
				String index = map.get("index");
				if (index.equals("-1")) {
					//快捷首页
					init_kuaisu(drawer);
				}
				if (index.equals("0")) {
					// 微信管理
					Log.e("微信", "message");
					LinearLayout gridLayout = (LinearLayout) inflater.inflate(
							R.layout.weixin_item_menu_layout, null)
							.findViewById(R.id.weixin_item_menu_layout_List);
					linearLayout.removeAllViews();
					linearLayout.addView(gridLayout);
					ListView list = (ListView) linearLayout
							.findViewById(R.id.weixin_item_menu_layout_List_listView);
					String[] from = { "name" };
					int[] to = { R.id.menuItemName };
					SimpleAdapter adapter = new SimpleAdapter(
							HomeActivity.this, weixinList, R.layout.menuitem,
							from, to);
					list.setAdapter(adapter);
					setListViewHeightBasedOnChildren(list);
					drawer.closeDrawer(START);
					list.setOnItemClickListener(new OnItemClickListener() {
						@Override
						public void onItemClick(
								android.widget.AdapterView<?> parent,
								View view, int position, long id) {
							ListView listView = (ListView) parent;
							HashMap<String, String> map = (HashMap<String, String>) listView
									.getItemAtPosition(position);
							String tag = map.get("tag");
							if (tag.equals("weixin1")) {
								WeixinGailan gailan=new WeixinGailan(HomeActivity.this,linearLayout);
							}
							if (tag.equals("weixin2")) {
								// 微信公众账号设置
								Log.e("---------------------", "weixin2");
								AccountSet accountSet=new AccountSet(HomeActivity.this,linearLayout);
							}
							if (tag.equals("weixin3")) {
								// 会员管理
								VipManager gailan=new VipManager(HomeActivity.this,linearLayout);
							}
							if (tag.equals("weixin4")) {
								//游戏
								GameManager game=new GameManager(HomeActivity.this,linearLayout);
							}
							if (tag.equals("weixin5")) {
								// 序列号兑换
								Log.e("---------------------", "weixin5");
								Duihuan duihuan=new Duihuan(HomeActivity.this,linearLayout);
							}
							if (tag.equals("weixin6")) {
								// 模板设置
								Log.e("---------------------", "weixin6");
								ModelSet modelsetOs=new ModelSet(HomeActivity.this,linearLayout);
							}
							if (tag.equals("weixin7")) {
								// 店铺地址设置
								Log.e("---------------------", "weixin7");
								MapSet modelsetOs=new MapSet(HomeActivity.this,linearLayout);
							}
							if (tag.equals("weixin8")) {
								// 微信商品管理
								Log.e("---------------------", "weixin8");
								WeiXinGoods weixingoods=new WeiXinGoods(HomeActivity.this,linearLayout);
							}
							if (tag.equals("weixin9")) {
								// 微信订单管理
								Log.e("---------------------", "weixin9");
								OrderManager gailan=new OrderManager(HomeActivity.this,linearLayout);
							}
							if (tag.equals("weixin10")) {
								// 会员wifi功能
								Log.e("---------------------", "weixin10");
								WiXinWiFi gailan=new WiXinWiFi(HomeActivity.this,linearLayout);
							}
							if (tag.equals("weixin11")) {
								// 图片交流模块管理
								ImageManager im = new ImageManager(HomeActivity.this, linearLayout);
							}
							if (tag.equals("weixin12")) {
								// 视频交流模块管理
								WiXinWiFi gailan=new WiXinWiFi(HomeActivity.this,linearLayout);
							}
							if (tag.equals("weixin13")) {
								// 文本交流模块管理
								WiXinWiFi gailan=new WiXinWiFi(HomeActivity.this,linearLayout);
							}
							if (tag.equals("weixin14")) {
								// 格子铺交流模块管理
								WiXinWiFi gailan=new WiXinWiFi(HomeActivity.this,linearLayout);
							}
							if (tag.equals("weixin15")) {
								// 游戏模块
								WiXinWiFi gailan=new WiXinWiFi(HomeActivity.this,linearLayout);
							}
							if (tag.equals("weixin16")) {
								// 公告管理
								WiXinWiFi gailan=new WiXinWiFi(HomeActivity.this,linearLayout);
							}
						};
					});
				}
				if (index.equals("1")) {
					// 客户管理
					Log.e("客户", "message");
					LinearLayout gridLayout = (LinearLayout) inflater.inflate(
							R.layout.weixin_item_menu_layout, null)
							.findViewById(R.id.weixin_item_menu_layout_List);
					linearLayout.removeAllViews();
					linearLayout.addView(gridLayout);
					ListView list = (ListView) linearLayout
							.findViewById(R.id.weixin_item_menu_layout_List_listView);
					String[] from = { "name" };
					int[] to = { R.id.menuItemName };
					SimpleAdapter adapter = new SimpleAdapter(
							HomeActivity.this, crmList, R.layout.menuitem,
							from, to);
					list.setAdapter(adapter);
					setListViewHeightBasedOnChildren(list);
					drawer.closeDrawer(START);
					list.setOnItemClickListener(new OnItemClickListener() {
						@Override
						public void onItemClick(
								android.widget.AdapterView<?> parent,
								View view, int position, long id) {
							ListView listView = (ListView) parent;
							HashMap<String, String> map = (HashMap<String, String>) listView
									.getItemAtPosition(position);
							String tag = map.get("tag");
							if (tag.equals("crm1")) {
								// 客户机会概览
								CrmManager crm = new CrmManager(HomeActivity.this, linearLayout);
							}
							if (tag.equals("crm2")) {
								// 我创建客户机会
								QueryChanceManager qcm = new QueryChanceManager(HomeActivity.this, linearLayout);
							}
							if (tag.equals("crm3")) {
								// 分配给我的客户机会
								QueryToChanceManager qtcm = new QueryToChanceManager(HomeActivity.this, linearLayout);
							}
							if (tag.equals("crm4")) {
								// 我的客户管理
								QueryMyCustomerManager qmcm = new QueryMyCustomerManager(HomeActivity.this, linearLayout);
							}
							if (tag.equals("crm5")) {
								// 全部客户机
								QueryAllChanceManager qacm = 
										new QueryAllChanceManager(HomeActivity.this, linearLayout);
							}
						};
					});
				}
				if (index.equals("2")) {
					// 库存管理
					Log.e("库存", "message");
					LinearLayout gridLayout = (LinearLayout) inflater.inflate(
							R.layout.weixin_item_menu_layout, null)
							.findViewById(R.id.weixin_item_menu_layout_List);
					linearLayout.removeAllViews();
					linearLayout.addView(gridLayout);
					ListView list = (ListView) linearLayout
							.findViewById(R.id.weixin_item_menu_layout_List_listView);
					String[] from = { "name" };
					int[] to = { R.id.menuItemName };
					SimpleAdapter adapter = new SimpleAdapter(
							HomeActivity.this, goodList, R.layout.menuitem,
							from, to);
					list.setAdapter(adapter);
					setListViewHeightBasedOnChildren(list);
					drawer.closeDrawer(START);
					list.setOnItemClickListener(new OnItemClickListener() {
						@Override
						public void onItemClick(
								android.widget.AdapterView<?> parent,
								View view, int position, long id) {
							ListView listView = (ListView) parent;
							HashMap<String, String> map = (HashMap<String, String>) listView
									.getItemAtPosition(position);
							String tag = map.get("tag");
							if (tag.equals("good1")) {
								// 库存概况
							}
							if (tag.equals("good2")) {
								// 库存查询
								GoodsQuery query=new GoodsQuery(HomeActivity.this, linearLayout);
							}
							if (tag.equals("good3")) {
								// 进货管理
								InGoods query=new InGoods(HomeActivity.this, linearLayout);
							}
							if (tag.equals("good4")) {
								// 销售管理
								OutGoods query=new OutGoods(HomeActivity.this, linearLayout);
							}
							if (tag.equals("good5")) {
								// 进货商信息
								SourceManager store=new SourceManager(HomeActivity.this,linearLayout);
							}
							if (tag.equals("good6")) {
								// 仓库信息
								StoreManager store=new StoreManager(HomeActivity.this,linearLayout);
							}

						};
					});
				}
				if (index.equals("3")) {
					// 员工管理
					Log.e("员工", "message");
					LinearLayout gridLayout = (LinearLayout) inflater.inflate(
							R.layout.weixin_item_menu_layout, null)
							.findViewById(R.id.weixin_item_menu_layout_List);
					linearLayout.removeAllViews();
					linearLayout.addView(gridLayout);
					ListView list = (ListView) linearLayout
							.findViewById(R.id.weixin_item_menu_layout_List_listView);
					String[] from = { "name" };
					int[] to = { R.id.menuItemName };
					SimpleAdapter adapter = new SimpleAdapter(
							HomeActivity.this, hrList, R.layout.menuitem, from,
							to);
					list.setAdapter(adapter);
					setListViewHeightBasedOnChildren(list);
					drawer.closeDrawer(START);
					list.setOnItemClickListener(new OnItemClickListener() {
						@Override
						public void onItemClick(
								android.widget.AdapterView<?> parent,
								View view, int position, long id) {
							ListView listView = (ListView) parent;
							HashMap<String, String> map = (HashMap<String, String>) listView
									.getItemAtPosition(position);
							String tag = map.get("tag");
							if (tag.equals("hr1")) {
								// 概览
								Intent i = new Intent(HomeActivity.this, AddCustomerWindow.class);  
								Bundle b = new Bundle();
								i.putExtras(b);  
								HomeActivity.this.startActivityForResult(i, 0);
								HomeActivity.this.overridePendingTransition(R.anim.modal_in, R.anim.modal_out);
							}
							if (tag.equals("hr2")) {
								// 员工账号管理
								QueryCustomerManager qcm = new QueryCustomerManager(HomeActivity.this, linearLayout);
							}
							if (tag.equals("hr3")) {
								// 绩效管理
								PerformanceManager pm = new PerformanceManager(HomeActivity.this, linearLayout);
							}
							if (tag.equals("hr4")) {
								// 签到管理
								MeetingManager mm = new MeetingManager(HomeActivity.this, linearLayout);
							}
							if (tag.equals("hr5")) {
								// 外勤管理
								
							}
							if (tag.equals("hr6")) {
								// 互动空间
								GetHuDongKongJianManager hdkj = new GetHuDongKongJianManager(HomeActivity.this, linearLayout);
							}

						};
					});
				}
				if (index.equals("4")) {
					// 短信管理
					Log.e("短信", "message");
					LinearLayout gridLayout = (LinearLayout) inflater.inflate(
							R.layout.weixin_item_menu_layout, null)
							.findViewById(R.id.weixin_item_menu_layout_List);
					linearLayout.removeAllViews();
					linearLayout.addView(gridLayout);
					ListView list = (ListView) linearLayout
							.findViewById(R.id.weixin_item_menu_layout_List_listView);
					String[] from = { "name" };
					int[] to = { R.id.menuItemName };
					SimpleAdapter adapter = new SimpleAdapter(
							HomeActivity.this, messageList, R.layout.menuitem,
							from, to);
					list.setAdapter(adapter);
					setListViewHeightBasedOnChildren(list);
					drawer.closeDrawer(START);
					list.setOnItemClickListener(new OnItemClickListener() {
						@Override
						public void onItemClick(
								android.widget.AdapterView<?> parent,
								View view, int position, long id) {
							ListView listView = (ListView) parent;
							HashMap<String, String> map = (HashMap<String, String>) listView
									.getItemAtPosition(position);
							String tag = map.get("tag");
							if (tag.equals("message1")) {
								// 短信设置
								MessageSetManager msm = new MessageSetManager(HomeActivity.this, linearLayout);
							}
							if (tag.equals("message2")) {
								// 会员发信
								VipSendManager vsm = new VipSendManager(HomeActivity.this, linearLayout);
							}
							if (tag.equals("message3")) {
								// 短信模板
								ModelManager mm = new ModelManager(HomeActivity.this, linearLayout);
							}
							if (tag.equals("message4")) {
								// 历史信息
							}

						};
					});
				}
				if (index.equals("5")) {
					// 系统管理
					Log.e("系统", "message");
					drawer.closeDrawer(START);
					// 权限管理
					GroupManager groupManager = new GroupManager(HomeActivity.this,linearLayout);
				}
				if (index.equals("6")) {
					// 账户管理
					Log.e("账户", "message");
					drawer.closeDrawer(START);
					LinearLayout gridLayout = (LinearLayout) inflater.inflate(
							R.layout.ajax_change_password_window, null)
							.findViewById(R.id.ajax_change_password_main);
					linearLayout.removeAllViews();
					linearLayout.addView(gridLayout);
					BootstrapButton enter = (BootstrapButton)findViewById(R.id.change_password_content_enter);
					BootstrapEditText old =(BootstrapEditText) findViewById(R.id.change_password_old);
					BootstrapEditText newp =(BootstrapEditText) findViewById(R.id.change_password_newp);  
					BootstrapEditText renewp =(BootstrapEditText) findViewById(R.id.change_password_renewp);
					enter.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							// TODO 自动生成的方法存根
							LoadingDialog dialog2 = new LoadingDialog(
									HomeActivity.this, "正在获取数据");
							dialog2.show();
							AjaxChangePasswordHandler handler = 
									new AjaxChangePasswordHandler(HomeActivity.this, dialog2);
							AjaxChangePasswordThread thread = 
									new AjaxChangePasswordThread(HomeActivity.this, handler);
							thread.start();
						}
					});
				}
				if (index.equals("7")) {
					Log.e("打印机设置", "message");
					if(print==null){
						print=PrintUtil.getPrintUtil(HomeActivity.this);
					}
					drawer.closeDrawer(START);
					LinearLayout gridLayout = (LinearLayout) inflater.inflate(
							R.layout.print_manager, null)
							.findViewById(R.id.print_manager_main);
					linearLayout.removeAllViews();
					linearLayout.addView(gridLayout);
					BootstrapButton link = (BootstrapButton)findViewById(R.id.print_link);
					BootstrapEditText addressText =(BootstrapEditText) findViewById(R.id.print_address);
					if(print.isLink()){
						link.setText("关闭");
					}else{
						link.setText("链接");
					}
					SharedPreferences preferences = getSharedPreferences("usermessage", Activity.MODE_PRIVATE);
				    String address = preferences.getString("linkPrintAddress", "");
				    addressText.setText(address);
				    link.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							BootstrapEditText addressText2 =(BootstrapEditText) findViewById(R.id.print_address);
							BootstrapButton link = (BootstrapButton)findViewById(R.id.print_link);
							if(print.isLink()){
								print.close();
								link.setText("链接");
							}else{
								print.linkPrint(addressText2.getText().toString());
								link.setText("关闭");
							}
						}
					});
				}
			}

		});
		HomeActivityPermissionThread thread = new HomeActivityPermissionThread(
				handler, this);
		thread.start();
	}
	private void init_kuaisu(DrawerLayout drawer){
		LinearLayout gridLayout = (LinearLayout) inflater.inflate(
				R.layout.kuaisu_main, null)
				.findViewById(R.id.kuaisu_main);
		linearLayout.removeAllViews();
		linearLayout.addView(gridLayout);
		drawer.closeDrawer(START);	
		//wifi
		BootstrapButton wifiButton = (BootstrapButton)
				findViewById(R.id.kuai_wifi);
		wifiButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				WiXinWiFi gailan=new WiXinWiFi(HomeActivity.this,linearLayout);
			}
		});								
		BootstrapButton outOrder = (BootstrapButton)
				findViewById(R.id.kuai_outOrder);
		BootstrapButton inOrder = (BootstrapButton)
				findViewById(R.id.kuai_inOrder);
		//outOrder 执行
		outOrder.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				OutGoods query=new OutGoods(HomeActivity.this, linearLayout);
			}
		});
		//inOrder 执行
		inOrder.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				InGoods query=new InGoods(HomeActivity.this, linearLayout);
			}
		});
		//vip
		BootstrapButton vipButton = (BootstrapButton)
				findViewById(R.id.kuai_queryVip);
		vipButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO 得到vip列表
				LinearLayout gridLayout = (LinearLayout) inflater.inflate(
						R.layout.kuaisu_vip, null)
						.findViewById(R.id.kuaisu_vip);
				linearLayout.removeAllViews();
				linearLayout.addView(gridLayout);
				BootstrapEditText nowpage=(BootstrapEditText) findViewById(R.id.weixin_game_vip_nowpage);
				nowpage.setText("1");
				LoadingDialog dialog = new LoadingDialog(HomeActivity.this,
						"正在获取数据");
				dialog.show();
//				TableLayout layout = (TableLayout) findViewById(R.id.weixin_game_vip);
//				layout.setVisibility(View.VISIBLE);
				XListView view=(XListView) findViewById(R.id.weixin_game_vip_list);
				view.setPullLoadEnable(true);
				view.setXListViewListener(new IXListViewListener() {
					
					@Override
					public void onRefresh() {
						// TODO 刷新
						BootstrapEditText nowpage=(BootstrapEditText) findViewById(R.id.weixin_game_vip_nowpage);
						BootstrapEditText nameedit=(BootstrapEditText) findViewById(R.id.weixin_game_vip_name);
						String namestr=nameedit.getText().toString();
						int nowpagenum=Integer.parseInt(nowpage.getText().toString());
						if(nowpagenum<=1){
							nowpagenum=1;
						}else{
							nowpagenum=nowpagenum-1;
						}
						nowpage.setText(String.valueOf(nowpagenum));
						LoadingDialog dialog = new LoadingDialog(HomeActivity.this, "正在获取数据");
						dialog.show();
						XListView view=(XListView) findViewById(R.id.weixin_game_vip_list);
						WeixinGetVipHandler vipHandler = new WeixinGetVipHandler(
								HomeActivity.this, view, dialog,R.layout.vip_list,"");
						WeixinGetVipThread vipThread = new WeixinGetVipThread(
								HomeActivity.this, String.valueOf(nowpagenum), "10", namestr, vipHandler);
						vipThread.start();
					}
					
					@Override
					public void onLoadMore() {
						// TODO 加载更多
						BootstrapEditText nowpage=(BootstrapEditText) findViewById(R.id.weixin_game_vip_nowpage);
						BootstrapEditText nameedit=(BootstrapEditText) findViewById(R.id.weixin_game_vip_name);
						String namestr=nameedit.getText().toString();
						int nowpagenum=Integer.parseInt(nowpage.getText().toString());
						nowpage.setText(String.valueOf(nowpagenum + 1));
						LoadingDialog dialog = new LoadingDialog(HomeActivity.this, "正在获取数据");
						dialog.show();
						XListView view=(XListView) findViewById(R.id.weixin_game_vip_list);
						WeixinGetVipHandler vipHandler = new WeixinGetVipHandler(
								HomeActivity.this, view, dialog,R.layout.vip_list,"");
						WeixinGetVipThread vipThread = new WeixinGetVipThread(
								HomeActivity.this, String.valueOf(nowpagenum+1), "10", namestr, vipHandler);
						vipThread.start();
					}
				});
				WeixinGetVipHandler vipHandler = new WeixinGetVipHandler(
						HomeActivity.this, view, dialog,R.layout.vip_list,"");
				WeixinGetVipThread vipThread = new WeixinGetVipThread(
						HomeActivity.this, "1", "10", "", vipHandler);
				vipThread.start();
				BootstrapButton query=(BootstrapButton) findViewById(R.id.weixin_game_vip_name_query);
				query.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO 按电话号查询
						BootstrapEditText nowpage=(BootstrapEditText) findViewById(R.id.weixin_game_vip_nowpage);
						nowpage.setText("1");
						BootstrapEditText nameedit=(BootstrapEditText) findViewById(R.id.weixin_game_vip_name);
						String namestr=nameedit.getText().toString();
						LoadingDialog dialog = new LoadingDialog(HomeActivity.this, "正在获取数据");
						dialog.show();
						XListView view=(XListView) findViewById(R.id.weixin_game_vip_list);
						WeixinGetVipHandler handler=new WeixinGetVipHandler(HomeActivity.this,view,dialog,R.layout.vip_list,"");
						WeixinGetVipThread thread=new WeixinGetVipThread(HomeActivity.this,"1","10",namestr,handler);
						thread.start();
					}
				});
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
	        if(type.equals("cancel")){
	        	//取消事件不做修改
	        }else if(type.equals("weixin_menu_add")){
	        	TableLayout t=(TableLayout) findViewById(R.id.weixin_gailan_menuManager_list);
	        	TableLayout t2=(TableLayout) findViewById(R.id.weixin_gailan_menuManager_list_item);
	        	t.removeAllViews();
	        	t2.removeAllViews();
	        	com.beardedhen.androidbootstrap.BootstrapButton b1=(com.beardedhen.androidbootstrap.BootstrapButton) findViewById(R.id.weixin_menu_manager);
	        	b1.performClick();
	        }else if(type.equals("weixin_menu_update")){
	        	TableLayout t=(TableLayout) findViewById(R.id.weixin_gailan_menuManager_list);
	        	TableLayout t2=(TableLayout) findViewById(R.id.weixin_gailan_menuManager_list_item);
	        	t.removeAllViews();
	        	t2.removeAllViews();
	        	com.beardedhen.androidbootstrap.BootstrapButton b1=(com.beardedhen.androidbootstrap.BootstrapButton) findViewById(R.id.weixin_menu_manager);
	        	b1.performClick();
	        }if(type.equals("weixin_gailan_AutoResendAddHandler")){
	        	int types=bundle.getInt("weixin_gailan_AutoResendAddHandlertype");
	        switch (types) {
			case WeiXinReSendDto.TEXT:
				//文本
				BootstrapButton b0=(BootstrapButton) HomeActivity.this.findViewById(R.id.weixin_gailan_huifuziyuan_pager_text);
				b0.performClick();
				break;
			case WeiXinReSendDto.IMAGE:
				//图片
				BootstrapButton b1=(BootstrapButton)HomeActivity.this.findViewById(R.id.weixin_gailan_huifuziyuan_pager_image);
				b1.performClick();
				break;
			case WeiXinReSendDto.IMAGE_TEXT:
				//图文
				BootstrapButton b2=(BootstrapButton)HomeActivity.this.findViewById(R.id.weixin_gailan_huifuziyuan_pager_imagetext);
				b2.performClick();
				break;
			case WeiXinReSendDto.MUSIC:
				//音乐
				BootstrapButton b3=(BootstrapButton)HomeActivity.this.findViewById(R.id.weixin_gailan_huifuziyuan_pager_music);
				b3.performClick();
				break;
			case WeiXinReSendDto.VIDEO:
				//视频
				BootstrapButton b4=(BootstrapButton)HomeActivity.this.findViewById(R.id.weixin_gailan_huifuziyuan_pager_video);
				b4.performClick();
				break;
			case WeiXinReSendDto.VOICE:
				//语音
				BootstrapButton b5=(BootstrapButton)HomeActivity.this.findViewById(R.id.weixin_gailan_huifuziyuan_pager_voice);
				b5.performClick();
				break;
			default:
				break;
			}
	        }if(type.equals("weixin_gailan_AutoResendsetAddHandler")){
	        	int types=bundle.getInt("weixin_gailan_AutoResendsetAddHandlertype");
	        	switch (types) {
				case WeiXinAutoReSendMenuDto.TYPE_TEXT:
					//文本
					BootstrapButton b0=(BootstrapButton) HomeActivity.this.findViewById(R.id.weixin_gailan_autoResendSet_pager_text);
					b0.performClick();
					break;
				case WeiXinAutoReSendMenuDto.TYPE_IMAGE:
					//图片
					BootstrapButton b1=(BootstrapButton)HomeActivity.this.findViewById(R.id.weixin_gailanautoResendSet_pager_image);
					b1.performClick();
					break;
				case WeiXinAutoReSendMenuDto.TYPE_LINK:
					//链接
					BootstrapButton b2=(BootstrapButton)HomeActivity.this.findViewById(R.id.weixin_gailan_autoResendSet_pager_link);
					b2.performClick();
					break;
				case WeiXinAutoReSendMenuDto.TYPE_LOCATION:
					//位置
					BootstrapButton b3=(BootstrapButton)HomeActivity.this.findViewById(R.id.weixin_gailan_autoResendSet_pager_location);
					b3.performClick();
					break;
				case WeiXinAutoReSendMenuDto.TYPE_VIDEO:
					//视频
					BootstrapButton b4=(BootstrapButton)HomeActivity.this.findViewById(R.id.weixin_gailan_autoResendSet_pager_video);
					b4.performClick();
					break;
				case WeiXinAutoReSendMenuDto.TYPE_VOICE:
					//语音
					BootstrapButton b5=(BootstrapButton)HomeActivity.this.findViewById(R.id.weixin_gailan_autoResendSet_pager_voice);
					b5.performClick();
					break;
				case WeiXinAutoReSendMenuDto.TYPE_EVENT:
					//事件
					BootstrapButton b6=(BootstrapButton)HomeActivity.this.findViewById(R.id.weixin_gailan_autoResendSet_pager_event);
					b6.performClick();
					break;
				default:
					break;
				}
	        }if(type.equals("game_add_awards_to_guaguaka")){
	        	BootstrapButton b = (BootstrapButton)HomeActivity.this.findViewById(R.id.weixin_game_guaguaka);
	        	b.performClick();
	        }if(type.equals("jiangpinduihuan")){
	        	String resultString=bundle.getString("value");
	        	if(resultString.indexOf("dzp")!=-1){
	        		LoadingDialog dialog = new LoadingDialog(HomeActivity.this, "正在获取数据");
					dialog.show();
					HuoDongHandler jifen=new HuoDongHandler(dialog, HomeActivity.this);
					HuodongThread thread=new HuodongThread(HomeActivity.this, jifen, resultString);
					thread.start();
	        	}else if(resultString.indexOf("sc")!=-1){
	        		LoadingDialog dialog = new LoadingDialog(HomeActivity.this, "正在获取数据");
					dialog.show();
					JifenHandler jifen=new JifenHandler(dialog, HomeActivity.this);
					jifenThread thread=new jifenThread(HomeActivity.this, jifen, resultString);
					thread.start();
	        	}else{
	        		Log.e("条形码",resultString);
	        		new SweetAlertDialog(HomeActivity.this, SweetAlertDialog.ERROR_TYPE)
					.setTitleText("失败")
					.setContentText("您的二维不是奖品二维码")
					.setConfirmText("确定").showCancelButton(false)
					.setCancelClickListener(null).setConfirmClickListener(null)
					.show();
	        	}
	        }
	        if(type.equals("startDate1")){
	        	TextView startDate1 = (TextView)findViewById(R.id.game_dazhuanpan_start_date1);
				String startDate11 = bundle.getString("value");
	        	startDate1.setText(startDate11);
	        }if(type.equals("startDate2")){
	        	TextView startDate2 = (TextView)findViewById(R.id.game_dazhuanpan_start_date2);
				String startDate12 = bundle.getString("value");
	        	startDate2.setText(startDate12);
	        }if(type.equals("startDate3")){
	        	TextView startDate3 = (TextView)findViewById(R.id.game_dazhuanpan_start_date3);
				String startDate13 = bundle.getString("value");
	        	startDate3.setText(startDate13);
	        }if(type.equals("endDate1")){
	        	TextView endDate1 = (TextView)findViewById(R.id.game_dazhuanpan_end_date1);
				endDate1.setText(bundle.getString("value"));
	        }if(type.equals("endDate2")){
	        	TextView endDate1 = (TextView)findViewById(R.id.game_dazhuanpan_end_date2);
				endDate1.setText(bundle.getString("value"));
	        }if(type.equals("endDate3")){
	        	TextView endDate1 = (TextView)findViewById(R.id.game_dazhuanpan_end_date3);
				endDate1.setText(bundle.getString("value"));
	        }if(type.equals("game_dazhuanpan_content1")){
	        	TextView content1 = (TextView)findViewById(R.id.game_dazhuanpan_content1);
	        	TextView id1 = (TextView)findViewById(R.id.game_dazhuanpan_id1);
				content1.setText(bundle.getString("content"));
				id1.setText(bundle.getString("id"));
	        }if(type.equals("game_dazhuanpan_content2")){
	        	TextView content2 = (TextView)findViewById(R.id.game_dazhuanpan_content2);
	        	TextView id2 = (TextView)findViewById(R.id.game_dazhuanpan_id2);
				content2.setText(bundle.getString("content"));
				id2.setText(bundle.getString("id"));
	        }if(type.equals("game_dazhuanpan_content3")){
	        	TextView content3 = (TextView)findViewById(R.id.game_dazhuanpan_content3);
	        	TextView id3 = (TextView)findViewById(R.id.game_dazhuanpan_id3);
				content3.setText(bundle.getString("content"));
				id3.setText(bundle.getString("id"));
	        }
	        if(type.equals("add_score_user") || type.equals("jian_price_user")
	        		|| type.equals("jian_score_user") || type.equals("add_price_user")){
	        	BootstrapButton b = (BootstrapButton)findViewById(R.id.weixin_game_vip_name_query);
	        	b.performClick();
	        }if(type.equals("add_vip_list") || type.equals("update_vip_list")){
	        	BootstrapButton b = (BootstrapButton)findViewById(R.id.weixin_vip_class_manager);
	        	b.performClick();
	        }if(type.equals("update_dui_huan") || type.equals("add_dui_huan")){
	        	BootstrapButton b = (BootstrapButton)findViewById(R.id.weixin_score_exchange);
	        	b.performClick();
	        }if(type.equals("weixin_enter_order")){
	        	BootstrapButton b = (BootstrapButton)findViewById(R.id.weixin_order_name_query);
	        	b.performClick();
	        }
	        if(type.equals("weixin_goods")){
	        	BootstrapButton up=(BootstrapButton) findViewById(R.id.weixin_goods_up_list);
	        	up.performClick();
	        	 String info=(String) bundle.get("info");
	        	 new SweetAlertDialog(HomeActivity.this, SweetAlertDialog.SUCCESS_TYPE)
				 .setTitleText("成功")
	             .setContentText(info)
	             .setConfirmText("确定")
	             .showCancelButton(false)
	             .setCancelClickListener(null)
	             .setConfirmClickListener(null).show();
	        }if(type.equals("add_store_house")){
	        	BootstrapButton b = (BootstrapButton)findViewById(R.id.goods_store_house_name_query);
	        	b.performClick();
	        }if(type.equals("add_source")){
	        	BootstrapButton b = (BootstrapButton)findViewById(R.id.goods_source_name_query);
	        	b.performClick();
	        }if(type.equals("create_group") || type.equals("update_group_info")){
	        	BootstrapButton b = (BootstrapButton)findViewById(R.id.get_all_group_name_query);
	        	b.performClick();
	        }
	        if(type.equals("addwifi")){
	        	 new SweetAlertDialog(HomeActivity.this, SweetAlertDialog.SUCCESS_TYPE)
				 .setTitleText("成功")
	             .setContentText("成功")
	             .setConfirmText("确定")
	             .showCancelButton(false)
	             .setCancelClickListener(null)
	             .setConfirmClickListener(null).show();
	        	 	ListView list=(ListView) findViewById(R.id.weixin_wifi_layout_list);
	        	 	LoadingDialog dialog = new LoadingDialog(this, "正在获取数据");
					dialog.show();
					WeiXinWiFiHandler handler=new WeiXinWiFiHandler(this, dialog,list);
					WeiXinWifiThread thread=new WeiXinWifiThread(this, handler);
					thread.start();
	        }
	        if(type.equals("goodsSelect")){
	        	
	        	 String namestr=(String) bundle.get("name");
	        	 String numstr=(String) bundle.get("num");
	        	 String pricestr=(String) bundle.get("price");
	        	 String typesstr=(String) bundle.get("types");
	        	 final String tiaomastr=(String) bundle.get("tiaoma");
	        	 String idstr=(String) bundle.get("id");
	        	 String inpricestr=(String) bundle.get("inprice");
	        	 String pinyinastr=(String) bundle.get("pinyin");
	        	 String salesnumstr=(String) bundle.get("salesnum");
	        	 String scorestr=(String) bundle.get("score");
	        	 String totalinprice=(String) bundle.get("totalinprice");
	        	 String totalprice=(String) bundle.get("totalprice");
	        	 String modelstr=(String) bundle.get("model");
	        	 String type2=(String) bundle.get("type2");
	        	 if(type2.equals("goodsquery")){
	        		 //库存管理->库存查询扫描二维码
	        		 List<GoodsTableDto> list=new ArrayList<GoodsTableDto>();
	        		 GoodsTableDto dto=new GoodsTableDto();
	        		 dto.setCodeid(tiaomastr);
	        		 dto.setGoodsModel(modelstr);
	        		 dto.setGoodsName(namestr);
	        		 dto.setGoodsNum(Double.parseDouble(numstr));
	        		 dto.setGoodsType(typesstr);
	        		 dto.setId(Long.parseLong(idstr));
	        		 dto.setInPrice(Double.parseDouble(inpricestr));
	        		 dto.setPrice(Float.parseFloat(pricestr));
	        		 dto.setSalesNum(Double.parseDouble(salesnumstr));
	        		 dto.setScore(Double.parseDouble(scorestr));
	        		 dto.setSpell(pinyinastr);
	        		 dto.setTotalInPrice(Double.parseDouble(totalinprice));
	        		 dto.setTotalPrice(Double.parseDouble(totalprice));
	        		 list.add(dto);
	        		 XListView view=(XListView) findViewById(R.id.queryGoods_list);
	    			 QueryGoodsAdapter adapter1 = new QueryGoodsAdapter(HomeActivity.this,list,R.layout.good_query_list_item);
	    			 view.setAdapter(adapter1);
	        	 }
	        	 if(type2.equals("OutOrderQuery")){
	        		 //销售订单查询
	        		
	        		 ScrollView scroll=(ScrollView)findViewById(R.id.good_outgoods_list);
	        		 TableLayout datascroll=(TableLayout)findViewById(R.id.good_outgoods_data_list);
	        		 TableRow manager=(TableRow)findViewById(R.id.good_order_manager);
	        		 manager.setVisibility(View.GONE);
	        		 datascroll.setVisibility(View.GONE);
	        		 scroll.setVisibility(View.VISIBLE);
	        		 LayoutInflater inflater = LayoutInflater.from(HomeActivity.this);
	        		 TableLayout gridLayout = (TableLayout) inflater.inflate(R.layout.good_outgoods_item, null);
	        		 scroll.removeAllViews();
	        	     scroll.addView(gridLayout);
		        		 BootstrapEditText name=(BootstrapEditText) gridLayout.findViewById(R.id.good_outgoods_item_name);
		        		 BootstrapEditText marks=(BootstrapEditText) gridLayout.findViewById(R.id.good_outgoods_item_marks);
		        		 BootstrapEditText price=(BootstrapEditText) gridLayout.findViewById(R.id.good_outgoods_item_price);
		        		 BootstrapEditText score=(BootstrapEditText) gridLayout.findViewById(R.id.good_outgoods_item_score);
		        		 BootstrapEditText num=(BootstrapEditText) gridLayout.findViewById(R.id.good_outgoods_item_num);
		        		 BootstrapEditText countPrice=(BootstrapEditText) gridLayout.findViewById(R.id.good_outgoods_item_countPrice);
		        		 BootstrapEditText types=(BootstrapEditText) gridLayout.findViewById(R.id.good_outgoods_item_type);
		        		 BootstrapEditText inprice=(BootstrapEditText) gridLayout.findViewById(R.id.good_outgoods_item_inprice);
		        		 BootstrapEditText pinyin=(BootstrapEditText) gridLayout.findViewById(R.id.good_outgoods_item_pinyin);
		        		 BootstrapEditText model=(BootstrapEditText) gridLayout.findViewById(R.id.good_outgoods_item_model);
		        		 BootstrapEditText changjia=(BootstrapEditText) gridLayout.findViewById(R.id.good_outgoods_item_changjia);
		        		 BootstrapEditText cangku=(BootstrapEditText) gridLayout.findViewById(R.id.good_outgoods_item_cangku);
		        		 BootstrapEditText changjiaid=(BootstrapEditText)findViewById(R.id.good_outgoods_item_changjia_id);
		        		 BootstrapEditText cangkuid=(BootstrapEditText)findViewById(R.id.good_outgoods_item_cangku_id);
		        		 BootstrapButton enter=(BootstrapButton) gridLayout.findViewById(R.id.good_outgoods_item_enter);
		        		 BootstrapButton cel=(BootstrapButton) gridLayout.findViewById(R.id.good_outgoods_item_cel);
		        		 
		        		 name.setText(namestr);
		        		 marks.setText("");
		        		 price.setText(pricestr);
		        		 score.setText(scorestr);
		        		 num.setText("1");
		        		 countPrice.setText(String.valueOf(1*Double.parseDouble(pricestr)));
		        		 types.setText(typesstr);
		        		 inprice.setText("0");
		        		 pinyin.setText(pinyinastr);
		        		 model.setText(modelstr);
		        		 changjia.setText("");
		        		 cangku.setText("");
		        		 changjiaid.setText("0");
		        		 cangkuid.setText("0");
		        		 price.addTextChangedListener(new PriceAndNumListener(price, num, countPrice));
		        		 num.addTextChangedListener(new PriceAndNumListener(price, num, countPrice));
		        		 scroll.removeAllViews();
		        	     scroll.addView(gridLayout);
		        	     enter.setOnClickListener(new OnClickListener() {
								
								@Override
								public void onClick(View v) {
									// TODO Auto-generated method stub
					        		 TableRow manager=(TableRow)findViewById(R.id.good_order_manager);
					        		 manager.setVisibility(View.VISIBLE);
					        		 BootstrapEditText name=(BootstrapEditText)findViewById(R.id.good_outgoods_item_name);
					        		 BootstrapEditText marks=(BootstrapEditText)findViewById(R.id.good_outgoods_item_marks);
					        		 BootstrapEditText price=(BootstrapEditText)findViewById(R.id.good_outgoods_item_price);
					        		 BootstrapEditText score=(BootstrapEditText)findViewById(R.id.good_outgoods_item_score);
					        		 BootstrapEditText num=(BootstrapEditText)findViewById(R.id.good_outgoods_item_num);
					        		 BootstrapEditText countPrice=(BootstrapEditText)findViewById(R.id.good_outgoods_item_countPrice);
					        		 BootstrapEditText types=(BootstrapEditText)findViewById(R.id.good_outgoods_item_type);
					        		 BootstrapEditText inprice=(BootstrapEditText)findViewById(R.id.good_outgoods_item_inprice);
					        		 BootstrapEditText pinyin=(BootstrapEditText)findViewById(R.id.good_outgoods_item_pinyin);
					        		 BootstrapEditText model=(BootstrapEditText)findViewById(R.id.good_outgoods_item_model);
					        		 BootstrapEditText changjia=(BootstrapEditText)findViewById(R.id.good_outgoods_item_changjia);
					        		 BootstrapEditText cangku=(BootstrapEditText)findViewById(R.id.good_outgoods_item_cangku);
					        		 BootstrapEditText changjiaid=(BootstrapEditText)findViewById(R.id.good_outgoods_item_changjia_id);
					        		 BootstrapEditText cangkuid=(BootstrapEditText)findViewById(R.id.good_outgoods_item_cangku_id);
					        		 BootstrapButton enter=(BootstrapButton)findViewById(R.id.good_outgoods_item_enter);
					        		 BootstrapButton cel=(BootstrapButton)findViewById(R.id.good_outgoods_item_cel);
					        		 if(goodstabledtoListType==null||goodstabledtoListType.equals("outOrder")){
					        			 goodstabledtoList=new ArrayList<OrdersItemDto>();
					        			 goodstabledtoListType="inOrder";
					        		 }
					        		 OrdersItemDto dto=new OrdersItemDto();
					        		 dto.setCodeid(tiaomastr);
					        		 dto.setConpanyId(0);
					        		 dto.setCreateDate(new Date().getTime()+"");
					        		 dto.setGoodsId(0);
					        		 dto.setGoodsinPrice(Double.parseDouble(inprice.getText().toString()));
					        		 dto.setGoodsModel(model.getText().toString());
					        		 dto.setGoodsName(name.getText().toString());
					        		 dto.setGoodsNum(Double.parseDouble(num.getText().toString()));
					        		 dto.setGoodsSourceId(Long.parseLong(changjiaid.getText().toString()));
					        		 dto.setGoodsSourceName(changjia.getText().toString());
					        		 dto.setGoodsToStorehouseId(Long.parseLong(cangkuid.getText().toString()));
					        		 dto.setGoodsToStorehouseName(cangku.getText().toString());
					        		 dto.setGoodsType(types.getText().toString());
					        		 dto.setMarks(marks.getText().toString());
					        		 dto.setPrice(Float.parseFloat(price.getText().toString()));
					        		 dto.setScore(Double.parseDouble((score.getText().toString())));
					        		 dto.setSpell(pinyin.getText().toString());
					        		 dto.setCountPrice(Double.parseDouble(countPrice.getText().toString()));
					        		 goodstabledtoList.add(dto);
					        		 ScrollView scroll=(ScrollView)findViewById(R.id.good_outgoods_list);
					        		 TableLayout datascroll=(TableLayout)findViewById(R.id.good_outgoods_data_list);
					        		 datascroll.setVisibility(View.VISIBLE);
					        		 scroll.setVisibility(View.GONE);
					        		 showOrderList(datascroll, goodstabledtoList, HomeActivity.this);
								}
							});
			        		 cel.setOnClickListener(new OnClickListener() {
								
								@Override
								public void onClick(View v) {
									// TODO Auto-generated method stub
									 ScrollView scroll=(ScrollView)findViewById(R.id.good_outgoods_list);
									 TableLayout datascroll=(TableLayout)findViewById(R.id.good_outgoods_data_list);
					        		 TableRow manager=(TableRow)findViewById(R.id.good_order_manager);
					        		 manager.setVisibility(View.VISIBLE);
					        		 datascroll.setVisibility(View.VISIBLE);
					        		 scroll.setVisibility(View.GONE);
								}
							});
		        	     
	        	 }
	        	 if(type2.equals("inOrderQuery")){
	        		 //进货订单查询
		        		
		        		 ScrollView scroll=(ScrollView)findViewById(R.id.good_ingoods_list);
		        		 TableLayout datascroll=(TableLayout)findViewById(R.id.good_ingoods_data_list);
		        		 TableRow manager=(TableRow)findViewById(R.id.good_order_manager);
		        		 manager.setVisibility(View.GONE);
		        		 datascroll.setVisibility(View.GONE);
		        		 scroll.setVisibility(View.VISIBLE);
		        		 LayoutInflater inflater = LayoutInflater.from(HomeActivity.this);
		        		 TableLayout gridLayout = (TableLayout) inflater.inflate(R.layout.good_ingoods_item, null);
		        		 scroll.removeAllViews();
		        	     scroll.addView(gridLayout);
		        		 BootstrapEditText name=(BootstrapEditText) gridLayout.findViewById(R.id.good_outgoods_item_name);
		        		 BootstrapEditText marks=(BootstrapEditText) gridLayout.findViewById(R.id.good_outgoods_item_marks);
		        		 BootstrapEditText price=(BootstrapEditText) gridLayout.findViewById(R.id.good_outgoods_item_price);
		        		 BootstrapEditText score=(BootstrapEditText) gridLayout.findViewById(R.id.good_outgoods_item_score);
		        		 BootstrapEditText num=(BootstrapEditText) gridLayout.findViewById(R.id.good_outgoods_item_num);
		        		 BootstrapEditText countPrice=(BootstrapEditText) gridLayout.findViewById(R.id.good_outgoods_item_countPrice);
		        		 BootstrapEditText types=(BootstrapEditText) gridLayout.findViewById(R.id.good_outgoods_item_type);
		        		 BootstrapEditText inprice=(BootstrapEditText) gridLayout.findViewById(R.id.good_outgoods_item_inprice);
		        		 BootstrapEditText pinyin=(BootstrapEditText) gridLayout.findViewById(R.id.good_outgoods_item_pinyin);
		        		 BootstrapEditText model=(BootstrapEditText) gridLayout.findViewById(R.id.good_outgoods_item_model);
		        		 BootstrapEditText changjia=(BootstrapEditText) gridLayout.findViewById(R.id.good_outgoods_item_changjia);
		        		 BootstrapEditText cangku=(BootstrapEditText) gridLayout.findViewById(R.id.good_outgoods_item_cangku);
		        		 BootstrapEditText changjiaid=(BootstrapEditText)findViewById(R.id.good_ingoods_item_changjia_id);
		        		 BootstrapEditText cangkuid=(BootstrapEditText)findViewById(R.id.good_ingoods_item_cangku_id);
		        		 BootstrapButton enter=(BootstrapButton) gridLayout.findViewById(R.id.good_outgoods_item_enter);
		        		 BootstrapButton cel=(BootstrapButton) gridLayout.findViewById(R.id.good_outgoods_item_cel);
		        		 name.setText(namestr);
		        		 marks.setText("");
		        		 price.setText(pricestr);
		        		 score.setText(scorestr);
		        		 num.setText("1");
		        		 countPrice.setText(String.valueOf(1*Double.parseDouble(pricestr)));
		        		 types.setText(typesstr);
		        		 inprice.setText("0");
		        		 pinyin.setText(pinyinastr);
		        		 model.setText(modelstr);
		        		 changjia.setText("");
		        		 changjiaid.setText("0");
		        		 cangkuid.setText("0");
		        		 cangku.setText("");
		        		 inprice.addTextChangedListener(new PriceAndNumListener(inprice, num, countPrice));
		        		 num.addTextChangedListener(new PriceAndNumListener(inprice, num, countPrice));
		        	     enter.setOnClickListener(new OnClickListener() {
								
								@Override
								public void onClick(View v) {
									// TODO Auto-generated method stub
					        		 TableRow manager=(TableRow)findViewById(R.id.good_order_manager);
					        		 manager.setVisibility(View.VISIBLE);
					        		 BootstrapEditText name=(BootstrapEditText)findViewById(R.id.good_outgoods_item_name);
					        		 BootstrapEditText marks=(BootstrapEditText)findViewById(R.id.good_outgoods_item_marks);
					        		 BootstrapEditText price=(BootstrapEditText)findViewById(R.id.good_outgoods_item_price);
					        		 BootstrapEditText score=(BootstrapEditText)findViewById(R.id.good_outgoods_item_score);
					        		 BootstrapEditText num=(BootstrapEditText)findViewById(R.id.good_outgoods_item_num);
					        		 BootstrapEditText countPrice=(BootstrapEditText)findViewById(R.id.good_outgoods_item_countPrice);
					        		 BootstrapEditText types=(BootstrapEditText)findViewById(R.id.good_outgoods_item_type);
					        		 BootstrapEditText inprice=(BootstrapEditText)findViewById(R.id.good_outgoods_item_inprice);
					        		 BootstrapEditText pinyin=(BootstrapEditText)findViewById(R.id.good_outgoods_item_pinyin);
					        		 BootstrapEditText model=(BootstrapEditText)findViewById(R.id.good_outgoods_item_model);
					        		 BootstrapEditText changjia=(BootstrapEditText)findViewById(R.id.good_outgoods_item_changjia);
					        		 BootstrapEditText cangku=(BootstrapEditText)findViewById(R.id.good_outgoods_item_cangku);
					        		 BootstrapEditText changjiaid=(BootstrapEditText)findViewById(R.id.good_ingoods_item_changjia_id);
					        		 BootstrapEditText cangkuid=(BootstrapEditText)findViewById(R.id.good_ingoods_item_cangku_id);
					        		 BootstrapButton enter=(BootstrapButton)findViewById(R.id.good_outgoods_item_enter);
					        		 BootstrapButton cel=(BootstrapButton)findViewById(R.id.good_outgoods_item_cel);
					        		 if(goodstabledtoListType==null||goodstabledtoListType.equals("inOrder")){
					        			 goodstabledtoList=new ArrayList<OrdersItemDto>();
					        			 goodstabledtoListType="outOrder";
					        		 }
					        		 OrdersItemDto dto=new OrdersItemDto();
					        		 dto.setCodeid(tiaomastr);
					        		 dto.setConpanyId(0);
					        		 dto.setCreateDate(new Date().getTime()+"");
					        		 dto.setGoodsId(0);
					        		 dto.setGoodsinPrice(Double.parseDouble(inprice.getText().toString()));
					        		 dto.setGoodsModel(model.getText().toString());
					        		 dto.setGoodsName(name.getText().toString());
					        		 dto.setGoodsNum(Double.parseDouble(num.getText().toString()));
					        		 dto.setGoodsSourceId(Long.parseLong(changjiaid.getText().toString()));
					        		 dto.setGoodsSourceName(changjia.getText().toString());
					        		 dto.setGoodsToStorehouseId(Long.parseLong(cangkuid.getText().toString()));
					        		 dto.setGoodsToStorehouseName(cangku.getText().toString());
					        		 dto.setGoodsType(types.getText().toString());
					        		 dto.setMarks(marks.getText().toString());
					        		 dto.setPrice(Float.parseFloat(price.getText().toString()));
					        		 dto.setScore(Double.parseDouble((score.getText().toString())));
					        		 dto.setSpell(pinyin.getText().toString());
					        		 dto.setCountPrice(Double.parseDouble(countPrice.getText().toString()));
					        		 goodstabledtoList.add(dto);
					        		 ScrollView scroll=(ScrollView)findViewById(R.id.good_ingoods_list);
					        		 TableLayout datascroll=(TableLayout)findViewById(R.id.good_ingoods_data_list);
					        		 datascroll.setVisibility(View.VISIBLE);
					        		 scroll.setVisibility(View.GONE);
					        		 showOrderList(datascroll, goodstabledtoList, HomeActivity.this);
								}
							});
			        		 cel.setOnClickListener(new OnClickListener() {
								
								@Override
								public void onClick(View v) {
									// TODO Auto-generated method stub
									 ScrollView scroll=(ScrollView)findViewById(R.id.good_ingoods_list);
									 TableLayout datascroll=(TableLayout)findViewById(R.id.good_ingoods_data_list);
					        		 TableRow manager=(TableRow)findViewById(R.id.good_order_manager);
					        		 manager.setVisibility(View.VISIBLE);
					        		 datascroll.setVisibility(View.VISIBLE);
					        		 scroll.setVisibility(View.GONE);
								}
							});
		        		
	        	 }
	        }
	        if(type.equals("goodsSelectnull")){
	        	 String type2=(String) bundle.get("type2");
	        	 final String tiaomastr=(String) bundle.get("codeid");
	        	 if(type2.equals("goodsquery")){
	        		 //库存管理->库存查询扫描二维码
	        	 }
	        	 if(type2.equals("OutOrderQuery")){
	        		 //销售订单查询
	        		 ScrollView scroll=(ScrollView)findViewById(R.id.good_outgoods_list);
	        		 TableLayout datascroll=(TableLayout)findViewById(R.id.good_outgoods_data_list);
	        		 TableRow manager=(TableRow)findViewById(R.id.good_order_manager);
	        		 manager.setVisibility(View.GONE);
	        		 datascroll.setVisibility(View.GONE);
	        		 scroll.setVisibility(View.VISIBLE);
	        		 LayoutInflater inflater = LayoutInflater.from(HomeActivity.this);
	        		 TableLayout gridLayout = (TableLayout) inflater.inflate(R.layout.good_outgoods_item, null);
	        		 scroll.removeAllViews();
	        	     scroll.addView(gridLayout);
		        		 BootstrapEditText name=(BootstrapEditText) gridLayout.findViewById(R.id.good_outgoods_item_name);
		        		 BootstrapEditText marks=(BootstrapEditText) gridLayout.findViewById(R.id.good_outgoods_item_marks);
		        		 BootstrapEditText price=(BootstrapEditText) gridLayout.findViewById(R.id.good_outgoods_item_price);
		        		 BootstrapEditText score=(BootstrapEditText) gridLayout.findViewById(R.id.good_outgoods_item_score);
		        		 BootstrapEditText num=(BootstrapEditText) gridLayout.findViewById(R.id.good_outgoods_item_num);
		        		 BootstrapEditText countPrice=(BootstrapEditText) gridLayout.findViewById(R.id.good_outgoods_item_countPrice);
		        		 BootstrapEditText types=(BootstrapEditText) gridLayout.findViewById(R.id.good_outgoods_item_type);
		        		 BootstrapEditText inprice=(BootstrapEditText) gridLayout.findViewById(R.id.good_outgoods_item_inprice);
		        		 BootstrapEditText pinyin=(BootstrapEditText) gridLayout.findViewById(R.id.good_outgoods_item_pinyin);
		        		 BootstrapEditText model=(BootstrapEditText) gridLayout.findViewById(R.id.good_outgoods_item_model);
		        		 BootstrapEditText changjia=(BootstrapEditText) gridLayout.findViewById(R.id.good_outgoods_item_changjia);
		        		 BootstrapEditText cangku=(BootstrapEditText) gridLayout.findViewById(R.id.good_outgoods_item_cangku);
		        		 BootstrapEditText changjiaid=(BootstrapEditText)findViewById(R.id.good_outgoods_item_changjia_id);
		        		 BootstrapEditText cangkuid=(BootstrapEditText)findViewById(R.id.good_outgoods_item_cangku_id);
		        		 BootstrapButton enter=(BootstrapButton) gridLayout.findViewById(R.id.good_outgoods_item_enter);
		        		 BootstrapButton cel=(BootstrapButton) gridLayout.findViewById(R.id.good_outgoods_item_cel);
		        		 
		        		 name.setText("");
		        		 marks.setText("");
		        		 price.setText("0");
		        		 score.setText("0");
		        		 num.setText("1");
		        		 countPrice.setText("0");
		        		 types.setText("");
		        		 inprice.setText("0");
		        		 pinyin.setText("");
		        		 model.setText("");
		        		 changjia.setText("");
		        		 cangku.setText("");
		        		 changjiaid.setText("0");
		        		 cangkuid.setText("0");
		        		 price.addTextChangedListener(new PriceAndNumListener(price, num, countPrice));
		        		 num.addTextChangedListener(new PriceAndNumListener(price, num, countPrice));
		        		 scroll.removeAllViews();
		        	     scroll.addView(gridLayout);
		        	     enter.setOnClickListener(new OnClickListener() {
								
								@Override
								public void onClick(View v) {
									// TODO Auto-generated method stub
					        		 TableRow manager=(TableRow)findViewById(R.id.good_order_manager);
					        		 manager.setVisibility(View.VISIBLE);
					        		 BootstrapEditText name=(BootstrapEditText)findViewById(R.id.good_outgoods_item_name);
					        		 BootstrapEditText marks=(BootstrapEditText)findViewById(R.id.good_outgoods_item_marks);
					        		 BootstrapEditText price=(BootstrapEditText)findViewById(R.id.good_outgoods_item_price);
					        		 BootstrapEditText score=(BootstrapEditText)findViewById(R.id.good_outgoods_item_score);
					        		 BootstrapEditText num=(BootstrapEditText)findViewById(R.id.good_outgoods_item_num);
					        		 BootstrapEditText countPrice=(BootstrapEditText)findViewById(R.id.good_outgoods_item_countPrice);
					        		 BootstrapEditText types=(BootstrapEditText)findViewById(R.id.good_outgoods_item_type);
					        		 BootstrapEditText inprice=(BootstrapEditText)findViewById(R.id.good_outgoods_item_inprice);
					        		 BootstrapEditText pinyin=(BootstrapEditText)findViewById(R.id.good_outgoods_item_pinyin);
					        		 BootstrapEditText model=(BootstrapEditText)findViewById(R.id.good_outgoods_item_model);
					        		 BootstrapEditText changjia=(BootstrapEditText)findViewById(R.id.good_outgoods_item_changjia);
					        		 BootstrapEditText cangku=(BootstrapEditText)findViewById(R.id.good_outgoods_item_cangku);
					        		 BootstrapEditText changjiaid=(BootstrapEditText)findViewById(R.id.good_outgoods_item_changjia_id);
					        		 BootstrapEditText cangkuid=(BootstrapEditText)findViewById(R.id.good_outgoods_item_cangku_id);
					        		 BootstrapButton enter=(BootstrapButton)findViewById(R.id.good_outgoods_item_enter);
					        		 BootstrapButton cel=(BootstrapButton)findViewById(R.id.good_outgoods_item_cel);
					        		 if(goodstabledtoListType==null||goodstabledtoListType.equals("outOrder")){
					        			 goodstabledtoList=new ArrayList<OrdersItemDto>();
					        			 goodstabledtoListType="inOrder";
					        		 }
					        		 OrdersItemDto dto=new OrdersItemDto();
					        		 dto.setCodeid(tiaomastr);
					        		 dto.setConpanyId(0);
					        		 dto.setCreateDate(new Date().getTime()+"");
					        		 dto.setGoodsId(0);
					        		 dto.setGoodsinPrice(Double.parseDouble(inprice.getText().toString()));
					        		 dto.setGoodsModel(model.getText().toString());
					        		 dto.setGoodsName(name.getText().toString());
					        		 dto.setGoodsNum(Double.parseDouble(num.getText().toString()));
					        		 dto.setGoodsSourceId(Long.parseLong(changjiaid.getText().toString()));
					        		 dto.setGoodsSourceName(changjia.getText().toString());
					        		 dto.setGoodsToStorehouseId(Long.parseLong(cangkuid.getText().toString()));
					        		 dto.setGoodsToStorehouseName(cangku.getText().toString());
					        		 dto.setGoodsType(types.getText().toString());
					        		 dto.setMarks(marks.getText().toString());
					        		 dto.setPrice(Float.parseFloat(price.getText().toString()));
					        		 dto.setScore(Double.parseDouble((score.getText().toString())));
					        		 dto.setSpell(pinyin.getText().toString());
					        		 dto.setCountPrice(Double.parseDouble(countPrice.getText().toString()));
					        		 goodstabledtoList.add(dto);
					        		 ScrollView scroll=(ScrollView)findViewById(R.id.good_outgoods_list);
					        		 TableLayout datascroll=(TableLayout)findViewById(R.id.good_outgoods_data_list);
					        		 datascroll.setVisibility(View.VISIBLE);
					        		 scroll.setVisibility(View.GONE);
					        		 showOrderList(datascroll, goodstabledtoList, HomeActivity.this);
								}
							});
			        		 cel.setOnClickListener(new OnClickListener() {
								
								@Override
								public void onClick(View v) {
									// TODO Auto-generated method stub
									 ScrollView scroll=(ScrollView)findViewById(R.id.good_outgoods_list);
									 TableLayout datascroll=(TableLayout)findViewById(R.id.good_outgoods_data_list);
					        		 TableRow manager=(TableRow)findViewById(R.id.good_order_manager);
					        		 manager.setVisibility(View.VISIBLE);
					        		 datascroll.setVisibility(View.VISIBLE);
					        		 scroll.setVisibility(View.GONE);
								}
							});
		        	     
	        	 }
	        	 if(type2.equals("inOrderQuery")){
	        		 //进货订单查询
	        		 ScrollView scroll=(ScrollView)findViewById(R.id.good_ingoods_list);
	        		 TableLayout datascroll=(TableLayout)findViewById(R.id.good_ingoods_data_list);
	        		 TableRow manager=(TableRow)findViewById(R.id.good_order_manager);
	        		 manager.setVisibility(View.GONE);
	        		 datascroll.setVisibility(View.GONE);
	        		 scroll.setVisibility(View.VISIBLE);
	        		 LayoutInflater inflater = LayoutInflater.from(HomeActivity.this);
	        		 TableLayout gridLayout = (TableLayout) inflater.inflate(R.layout.good_ingoods_item, null);
	        		 scroll.removeAllViews();
	        	     scroll.addView(gridLayout);
	        	     BootstrapEditText name=(BootstrapEditText) gridLayout.findViewById(R.id.good_outgoods_item_name);
	        		 BootstrapEditText marks=(BootstrapEditText) gridLayout.findViewById(R.id.good_outgoods_item_marks);
	        		 BootstrapEditText price=(BootstrapEditText) gridLayout.findViewById(R.id.good_outgoods_item_price);
	        		 BootstrapEditText score=(BootstrapEditText) gridLayout.findViewById(R.id.good_outgoods_item_score);
	        		 BootstrapEditText num=(BootstrapEditText) gridLayout.findViewById(R.id.good_outgoods_item_num);
	        		 BootstrapEditText countPrice=(BootstrapEditText) gridLayout.findViewById(R.id.good_outgoods_item_countPrice);
	        		 BootstrapEditText types=(BootstrapEditText) gridLayout.findViewById(R.id.good_outgoods_item_type);
	        		 BootstrapEditText inprice=(BootstrapEditText) gridLayout.findViewById(R.id.good_outgoods_item_inprice);
	        		 BootstrapEditText pinyin=(BootstrapEditText) gridLayout.findViewById(R.id.good_outgoods_item_pinyin);
	        		 BootstrapEditText model=(BootstrapEditText) gridLayout.findViewById(R.id.good_outgoods_item_model);
	        		 BootstrapEditText changjia=(BootstrapEditText) gridLayout.findViewById(R.id.good_outgoods_item_changjia);
	        		 BootstrapEditText cangku=(BootstrapEditText) gridLayout.findViewById(R.id.good_outgoods_item_cangku);
	        		 BootstrapEditText changjiaid=(BootstrapEditText)findViewById(R.id.good_ingoods_item_changjia_id);
	        		 BootstrapEditText cangkuid=(BootstrapEditText)findViewById(R.id.good_ingoods_item_cangku_id);
	        		 BootstrapButton enter=(BootstrapButton) gridLayout.findViewById(R.id.good_outgoods_item_enter);
	        		 BootstrapButton cel=(BootstrapButton) gridLayout.findViewById(R.id.good_outgoods_item_cel);
	        		 name.setText("");
	        		 marks.setText("");
	        		 price.setText("0");
	        		 score.setText("0");
	        		 num.setText("1");
	        		 countPrice.setText("0");
	        		 types.setText("");
	        		 inprice.setText("0");
	        		 pinyin.setText("");
	        		 model.setText("");
	        		 changjia.setText("");
	        		 cangku.setText("");
	        		 changjiaid.setText("0");
	        		 cangkuid.setText("0");
	        		 price.addTextChangedListener(new PriceAndNumListener(inprice, num, countPrice));
	        		 num.addTextChangedListener(new PriceAndNumListener(inprice, num, countPrice));
	        	     enter.setOnClickListener(new OnClickListener() {
							
							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
				        		 TableRow manager=(TableRow)findViewById(R.id.good_order_manager);
				        		 manager.setVisibility(View.VISIBLE);
				        		 BootstrapEditText name=(BootstrapEditText)findViewById(R.id.good_outgoods_item_name);
				        		 BootstrapEditText marks=(BootstrapEditText)findViewById(R.id.good_outgoods_item_marks);
				        		 BootstrapEditText price=(BootstrapEditText)findViewById(R.id.good_outgoods_item_price);
				        		 BootstrapEditText score=(BootstrapEditText)findViewById(R.id.good_outgoods_item_score);
				        		 BootstrapEditText num=(BootstrapEditText)findViewById(R.id.good_outgoods_item_num);
				        		 BootstrapEditText countPrice=(BootstrapEditText)findViewById(R.id.good_outgoods_item_countPrice);
				        		 BootstrapEditText types=(BootstrapEditText)findViewById(R.id.good_outgoods_item_type);
				        		 BootstrapEditText inprice=(BootstrapEditText)findViewById(R.id.good_outgoods_item_inprice);
				        		 BootstrapEditText pinyin=(BootstrapEditText)findViewById(R.id.good_outgoods_item_pinyin);
				        		 BootstrapEditText model=(BootstrapEditText)findViewById(R.id.good_outgoods_item_model);
				        		 BootstrapEditText changjia=(BootstrapEditText)findViewById(R.id.good_outgoods_item_changjia);
				        		 BootstrapEditText cangku=(BootstrapEditText)findViewById(R.id.good_outgoods_item_cangku);
				        		 BootstrapEditText changjiaid=(BootstrapEditText)findViewById(R.id.good_ingoods_item_changjia_id);
				        		 BootstrapEditText cangkuid=(BootstrapEditText)findViewById(R.id.good_ingoods_item_cangku_id);
				        		 BootstrapButton enter=(BootstrapButton)findViewById(R.id.good_outgoods_item_enter);
				        		 BootstrapButton cel=(BootstrapButton)findViewById(R.id.good_outgoods_item_cel);
				        		 if(goodstabledtoListType==null||goodstabledtoListType.equals("inOrder")){
				        			 goodstabledtoList=new ArrayList<OrdersItemDto>();
				        			 goodstabledtoListType="outOrder";
				        		 }
				        		 OrdersItemDto dto=new OrdersItemDto();
				        		 dto.setCodeid(tiaomastr);
				        		 dto.setConpanyId(0);
				        		 dto.setCreateDate(new Date().getTime()+"");
				        		 dto.setGoodsId(0);
				        		 dto.setGoodsinPrice(Double.parseDouble(inprice.getText().toString()));
				        		 dto.setGoodsModel(model.getText().toString());
				        		 dto.setGoodsName(name.getText().toString());
				        		 dto.setGoodsNum(Double.parseDouble(num.getText().toString()));
				        		 dto.setGoodsSourceId(Long.parseLong(changjiaid.getText().toString()));
				        		 dto.setGoodsSourceName(changjia.getText().toString());
				        		 dto.setGoodsToStorehouseId(Long.parseLong(cangkuid.getText().toString()));
				        		 dto.setGoodsToStorehouseName(cangku.getText().toString());
				        		 dto.setGoodsType(types.getText().toString());
				        		 dto.setMarks(marks.getText().toString());
				        		 dto.setPrice(Float.parseFloat(price.getText().toString()));
				        		 dto.setScore(Double.parseDouble((score.getText().toString())));
				        		 dto.setSpell(pinyin.getText().toString());
				        		 dto.setCountPrice(Double.parseDouble(countPrice.getText().toString()));
				        		 goodstabledtoList.add(dto);
				        		 ScrollView scroll=(ScrollView)findViewById(R.id.good_ingoods_list);
				        		 TableLayout datascroll=(TableLayout)findViewById(R.id.good_ingoods_data_list);
				        		 datascroll.setVisibility(View.VISIBLE);
				        		 scroll.setVisibility(View.GONE);
				        		 showOrderList(datascroll, goodstabledtoList, HomeActivity.this);
							}
						});
		        		 cel.setOnClickListener(new OnClickListener() {
							
							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								 ScrollView scroll=(ScrollView)findViewById(R.id.good_ingoods_list);
								 TableLayout datascroll=(TableLayout)findViewById(R.id.good_ingoods_data_list);
				        		 TableRow manager=(TableRow)findViewById(R.id.good_order_manager);
				        		 manager.setVisibility(View.VISIBLE);
				        		 datascroll.setVisibility(View.VISIBLE);
				        		 scroll.setVisibility(View.GONE);
							}
						});
	        	 }
	        }
	        if(type.equals("good_update_price")){
	        	BootstrapButton queryEnter=(BootstrapButton)findViewById(R.id.query_goods_query_enter);
	        	queryEnter.performClick();
	        }
	        if(type.equals("message_set_boolean")){
	        	Log.e("home--", "message_set_boolean");
	        }
	        if(type.equals("message_vip_send_select")){
	        	TextView id = (TextView)findViewById(R.id.message_vip_send_duihuan_id);
	        	id.setText(bundle.getString("id"));
	        	BootstrapButton select = (BootstrapButton)
	        			findViewById(R.id.message_vip_send_duihuan_jiangpin);
	        	select.setText(bundle.getString("name"));
	        }
	        if(type.equals("orderselect")){
	        	 String type2=(String) bundle.get("type2");
	        	if(type2.equals("OutOrderQuery")){
	        		 String id=(String) bundle.get("id");
	        		LoadingDialog dialog = new LoadingDialog(HomeActivity.this, "正在获取数据");
					dialog.show();
					LookOrderItemHandler handler=new LookOrderItemHandler(HomeActivity.this, dialog, false);
					LookOrderItemThread thread=new LookOrderItemThread(HomeActivity.this, handler, false,id );
					thread.start();
	        	}
	        	if(type2.equals("inOrderQuery")){
	        		 String id=(String) bundle.get("id");
		        		LoadingDialog dialog = new LoadingDialog(HomeActivity.this, "正在获取数据");
						dialog.show();
						LookOrderItemHandler handler=new LookOrderItemHandler(HomeActivity.this, dialog, true);
						LookOrderItemThread thread=new LookOrderItemThread(HomeActivity.this, handler, true,id );
						thread.start();
	        	}
	        }
	        if(type.equals("create_chance") || type.equals("update_my_create_chance")
	        		|| type.equals("allocation_my_create_chance")){
	        	BootstrapButton queryEnter=(BootstrapButton)findViewById(R.id.crm_query_my_chance_name_query);
	        	queryEnter.performClick();
	        }
	        if(type.equals("mapService")){
				startService(new Intent(HomeActivity.this,LocationService.class));
	        }
	        if(type.equals("employee_meeting_date")){
	        	BootstrapEditText date=(BootstrapEditText) HomeActivity.this.findViewById(R.id.employee_meeting_date);
	        	date.setText(bundle.getString("value"));
	        }
	        BootstrapEditText stute = (BootstrapEditText) HomeActivity.this.findViewById(R.id.employee_stute);
	        BootstrapEditText stuteId = (BootstrapEditText) HomeActivity.this.findViewById(R.id.employee_stute_id);
	        if(type.equals("employee_stute1")){
	        	stute.setText(bundle.getString("stute1Str"));
	        	stuteId.setText(bundle.getString("stute1"));
	        }
	        if(type.equals("employee_stute2")){
	        	stute.setText(bundle.getString("stute2Str"));
	        	stuteId.setText(bundle.getString("stute2"));
	        }
	        if(type.equals("employee_stute3")){
	        	stute.setText(bundle.getString("stute3Str"));
	        	stuteId.setText(bundle.getString("stute3"));
	        }
	        if(type.equals("employee_stute4")){
	        	stute.setText(bundle.getString("stute4Str"));
	        	stuteId.setText(bundle.getString("stute4"));
	        }
	        if(type.equals("employee_stute5")){
	        	stute.setText(bundle.getString("stute5Str"));
	        	stuteId.setText(bundle.getString("stute5"));
	        }
	    }
	 public void showOrderList(TableLayout toView,List<OrdersItemDto> list,HomeActivity act){
		 toView.removeAllViews();
		 double conprice=0.0;
		 double score=0.0;
		 for(int i=0;i<goodstabledtoList.size();i++){
			 OrdersItemDto dto=goodstabledtoList.get(i);
			 Log.e("检测总价", dto.getCountPrice()+"");
			 conprice=conprice+dto.getCountPrice();
			 score=score+dto.getGoodsNum()*dto.getScore();
			 LayoutInflater inflater = LayoutInflater.from(act);
			 LinearLayout gridLayout = (LinearLayout) inflater.inflate(R.layout.good_order_layout, null);
			 TextView name=(TextView) gridLayout.findViewById(R.id.good_order_layout_name);
			 TextView price=(TextView) gridLayout.findViewById(R.id.good_order_layout_price);
			 TextView num=(TextView) gridLayout.findViewById(R.id.good_order_layout_num);
			 name.setText(dto.getGoodsName());
			 price.setText("售价"+dto.getPrice()+"");
			 num.setText("数量"+dto.getGoodsNum()+"");
			 BootstrapButton button=(BootstrapButton) gridLayout.findViewById(R.id.good_order_layout_delete);
			 if(isruku){
				 button.setVisibility(View.GONE);
			 }else{
				 button.setOnClickListener(new DeleteOrderItem(toView, list, act, i));
			 }
			 toView.addView(gridLayout);
		 }
		 TextView conpricettext=(TextView) HomeActivity.this.findViewById(R.id.good_ingoods_zongjia);
		 conpricettext.setText("总价:"+conprice);
		 Object obj=HomeActivity.this.findViewById(R.id.good_ingoods_scoreNum);
		 if(obj!=null){
			 TextView scoretext=(TextView) HomeActivity.this.findViewById(R.id.good_ingoods_scoreNum);
			 scoretext.setText("积分:"+score);
		 }
	 }

}
