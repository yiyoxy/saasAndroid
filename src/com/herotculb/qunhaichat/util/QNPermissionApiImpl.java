package com.herotculb.qunhaichat.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.herotculb.qunhaichat.dto.AwardsDto;
import com.herotculb.qunhaichat.dto.ChanceFlowDto;
import com.herotculb.qunhaichat.dto.ChanceListDto;
import com.herotculb.qunhaichat.dto.ConpanyAddressDto;
import com.herotculb.qunhaichat.dto.ConpanyDTO;
import com.herotculb.qunhaichat.dto.ConpanyGroupDto;
import com.herotculb.qunhaichat.dto.ConpanyUserDto;
import com.herotculb.qunhaichat.dto.ConpanyUserMeetingDto;
import com.herotculb.qunhaichat.dto.ConpanyZoneImage;
import com.herotculb.qunhaichat.dto.ConpanyZoneRet;
import com.herotculb.qunhaichat.dto.ConpanyZoneRetDto;
import com.herotculb.qunhaichat.dto.ConpanyZoneTouPiao;
import com.herotculb.qunhaichat.dto.ConpanyZoneTouPiaoItem;
import com.herotculb.qunhaichat.dto.ConpanyZoneTouPiaoItemDto;
import com.herotculb.qunhaichat.dto.ConpanyZoneTouPiaoItemSendUser;
import com.herotculb.qunhaichat.dto.ConpanyZoneZan;
import com.herotculb.qunhaichat.dto.DateCharDto;
import com.herotculb.qunhaichat.dto.DeviceDto;
import com.herotculb.qunhaichat.dto.GamesAwardsListDto;
import com.herotculb.qunhaichat.dto.GoodsLogDto;
import com.herotculb.qunhaichat.dto.GoodsSourceDto;
import com.herotculb.qunhaichat.dto.GoodsSourceLinkManDto;
import com.herotculb.qunhaichat.dto.GoodsTableDto;
import com.herotculb.qunhaichat.dto.GroupConpanyLinkUser;
import com.herotculb.qunhaichat.dto.Hangye;
import com.herotculb.qunhaichat.dto.ImageList;
import com.herotculb.qunhaichat.dto.InOrderDto;
import com.herotculb.qunhaichat.dto.KongJianDto;
import com.herotculb.qunhaichat.dto.LinkManListDto;
import com.herotculb.qunhaichat.dto.MapInfo;
import com.herotculb.qunhaichat.dto.MeetingDto;
import com.herotculb.qunhaichat.dto.MessageSetDto;
import com.herotculb.qunhaichat.dto.NotesDto;
import com.herotculb.qunhaichat.dto.NotesInfoDto;
import com.herotculb.qunhaichat.dto.NumLibsDto;
import com.herotculb.qunhaichat.dto.OrdersDto;
import com.herotculb.qunhaichat.dto.OrdersItemDto;
import com.herotculb.qunhaichat.dto.PerformanceDto;
import com.herotculb.qunhaichat.dto.PermissionLoadDTO;
import com.herotculb.qunhaichat.dto.PieCharDto;
import com.herotculb.qunhaichat.dto.RoleDto;
import com.herotculb.qunhaichat.dto.ScoreDuihuanDto;
import com.herotculb.qunhaichat.dto.ScoreToGoodsListDto;
import com.herotculb.qunhaichat.dto.SoftPermission;
import com.herotculb.qunhaichat.dto.SoftPermissionDto;
import com.herotculb.qunhaichat.dto.StoreHouseDto;
import com.herotculb.qunhaichat.dto.UserDTO;
import com.herotculb.qunhaichat.dto.VIPSetDto;
import com.herotculb.qunhaichat.dto.VWiFiDto;
import com.herotculb.qunhaichat.dto.VipErShouDto;
import com.herotculb.qunhaichat.dto.VipGonggaoDto;
import com.herotculb.qunhaichat.dto.VipImageMessageDto;
import com.herotculb.qunhaichat.dto.VipTextMessageDto;
import com.herotculb.qunhaichat.dto.VipVideoMessageDto;
import com.herotculb.qunhaichat.dto.VoteDTO;
import com.herotculb.qunhaichat.dto.VoteItemDTO;
import com.herotculb.qunhaichat.dto.VoteUserDto;
import com.herotculb.qunhaichat.dto.WebPublicMessageDto;
import com.herotculb.qunhaichat.dto.WeiXinAutoReSendItemDTO;
import com.herotculb.qunhaichat.dto.WeiXinAutoReSendMenuDto;
import com.herotculb.qunhaichat.dto.WeiXinDto;
import com.herotculb.qunhaichat.dto.WeiXinGoodsDto;
import com.herotculb.qunhaichat.dto.WeiXinMenuTableDto;
import com.herotculb.qunhaichat.dto.WeiXinReSendDto;
import com.herotculb.qunhaichat.dto.WeiXinWebHtmlDto;
import com.herotculb.qunhaichat.dto.WeixinOrderDto;

public class QNPermissionApiImpl implements QNPermissionApi {
	public static String username;
	public static String password;
	public static Context content;

	/**
	 * 登录方法 返回list map map内读取两个参数， 1.sucess ----false,true
	 * 2.info--------错误信息，成功信息
	 */
	public QNPermissionApiImpl(Context content) {
		// TODO Auto-generated constructor stub
		this.content = content;
		if (username == null) {
			SharedPreferences preferences = content.getSharedPreferences(
					"usermessage", Activity.MODE_PRIVATE);
			username = preferences.getString("username", "");
			password = preferences.getString("password", "");
		}
	}
	/**
	 * 登录方法 返回list map map内读取两个参数， 1.sucess ----false,true
	 * 2.info--------错误信息，成功信息
	 */
	public QNPermissionApiImpl(String username, String password) {
		// TODO Auto-generated constructor stub
		this.username = username;
		this.password = password;
		
	}
	@Override
	public List<Map<String, Object>> loginAjax(String username, String password) {
		// TODO Auto-generated method stub
		this.username = username;
		this.password = password;
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("username", username));
		params.add(new BasicNameValuePair("password", password));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.loginAjax, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			String info = person.getString("info");
			map.put("success", b);
			map.put("info", info);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	/**
	 * 获取账户权限， 返回类型 List<Map<String, Object>> 可读取参数 1. success -----false,true
	 * 2.info-----错误信息，只有错误信息，正确时不需要读取 3.map内的get名 3.1 conpany 公司信息
	 * 是conpanyDTO类型 3.2 user 登录的账户信息 是UserDTO 类型 3.3 crm boolean 类型 false true
	 * 代表主功能项下是否要子项目 <客户关系管理> 3.4 goods boolean 类型 false true 代表主功能项下是否要子项目
	 * <库存管理> 3.5 hr boolean 类型 false true 代表主功能项下是否要子项目 <员工管理> 3.5 system
	 * boolean 类型 false true 代表主功能项下是否要子项目 <系统管理> 3.6 weixin boolean 类型 false
	 * true 代表主功能项下是否要子项目 <微信管理> 3.7 messageInfo boolean 类型 false true
	 * 代表主功能项下是否要子项目 <短信平台> ps:在以上get名 后加list 则取得大项下的子项 类型为
	 * List<PermissionLoadDTO>
	 */
	@Override
	public List<Map<String, Object>> backManagerajax() {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.backManagerajax, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();

			try {
				Object islogin = person.get("stute");
				SharedPreferences preferences = content.getSharedPreferences(
						"usermessage", Activity.MODE_PRIVATE);
				String userName = preferences.getString("username", "");
				String passWord = preferences.getString("password", "");
				loginAjax(userName, passWord);
				return this.backManagerajax();
			} catch (Exception ex) {

			}
			JSONObject jsoncrmArr = person.getJSONObject("crm");
			JSONObject jsongoodsArr = person.getJSONObject("goods");
			JSONObject jsonhrArr = person.getJSONObject("hr");
			JSONObject jsonsystemArr = person.getJSONObject("system");
			JSONObject jsonweixinArr = person.getJSONObject("weixin");
			JSONObject jsonmessageInfoArr = person.getJSONObject("messageInfo");
			int msgNum = person.getInt("msgNum");
			JSONArray jsonmsg = person.getJSONArray("msg");
			JSONObject objuser = person.getJSONObject("user");
			JSONObject objconpany = person.getJSONObject("conpany");
			ConpanyDTO conpany = new ConpanyDTO();
			conpany.setCity(objconpany.getString("city"));
			conpany.setConpanyAdminEmail(objconpany
					.getString("conpanyAdminEmail"));
			conpany.setConpanyName(objconpany.getString("conpanyName"));
			conpany.setConpanyRigister(objconpany.getString("conpanyRigister"));
			conpany.setConpanyType(objconpany.getString("conpanyType"));
			conpany.setCountry(objconpany.getString("country"));
			conpany.setDistrict(objconpany.getString("district"));
			conpany.setEndDate(objconpany.getString("endDate"));
			conpany.setHangyeId(objconpany.getLong("endDate"));
			conpany.setHangyeName(objconpany.getString("hangyeName"));
			conpany.setId(objconpany.getLong("id"));
			conpany.setNowUserNum(objconpany.getInt("nowUserNum"));
			conpany.setPayConpany(objconpany.getBoolean("payConpany"));
			conpany.setProvince(objconpany.getString("province"));
			conpany.setSoftAdminName(objconpany.getString("softAdminName"));
			conpany.setSoftAdminPhone(objconpany.getString("softAdminPhone"));
			conpany.setUseConpany(objconpany.getBoolean("useConpany"));
			map.put("conpany", conpany);
			UserDTO user = new UserDTO();
			user.setAccuntEndDate(objuser.getString("accuntEndDate"));
			user.setAccuntStartDate(objuser.getString("accuntStartDate"));
			user.setAddress(objuser.getString("address"));
			user.setConpanyAdmin(objuser.getBoolean("conpanyAdmin"));
			user.setConpanyId(objuser.getLong("conpanyId"));
			user.setEmail(objuser.getString("email"));
			user.setFreeAcccunt(objuser.getBoolean("freeAcccunt"));
			user.setId(objuser.getLong("id"));
			user.setIdImage(objuser.getString("idImage"));
			user.setIdNum(objuser.getString("idNum"));
			user.setImage(objuser.getString("image"));
			user.setMarks(objuser.getString("marks"));
			user.setPassword(objuser.getString("password"));
			user.setPhone(objuser.getString("phone"));
			user.setPrice(objuser.getInt("price"));
			user.setSex(objuser.getBoolean("sex"));
			user.setState(objuser.getString("state"));
			user.setTrueName(objuser.getString("trueName"));
			user.setUseLogin(objuser.getBoolean("useLogin"));
			user.setUsername(objuser.getString("username"));
			map.put("user", user);
			boolean crmboolean = jsoncrmArr.getBoolean("isuse");
			map.put("crm", crmboolean);
			if (crmboolean) {
				List<PermissionLoadDTO> crmList = new ArrayList<PermissionLoadDTO>();
				JSONArray jsonarrcrm = jsoncrmArr.getJSONArray("list");
				for (int i = 0; i < jsonarrcrm.length(); i++) {
					PermissionLoadDTO dto = new PermissionLoadDTO();
					JSONObject jbt = jsonarrcrm.getJSONObject(i);
					dto.setIsuse(jbt.getBoolean("isuse"));
					dto.setName(jbt.getString("name"));
					dto.setMarks(jbt.getString("marks"));
					crmList.add(dto);
				}
				map.put("crmlist", crmList);
			}
			boolean goodsboolean = jsongoodsArr.getBoolean("isuse");
			map.put("goods", goodsboolean);
			if (goodsboolean) {
				List<PermissionLoadDTO> crmList = new ArrayList<PermissionLoadDTO>();
				JSONArray jsonarrcrm = jsongoodsArr.getJSONArray("list");
				for (int i = 0; i < jsonarrcrm.length(); i++) {
					PermissionLoadDTO dto = new PermissionLoadDTO();
					JSONObject jbt = jsonarrcrm.getJSONObject(i);
					dto.setIsuse(jbt.getBoolean("isuse"));
					dto.setName(jbt.getString("name"));
					dto.setMarks(jbt.getString("marks"));
					crmList.add(dto);
				}
				map.put("goodslist", crmList);
			}
			boolean hrboolean = jsonhrArr.getBoolean("isuse");
			map.put("hr", hrboolean);
			if (hrboolean) {
				List<PermissionLoadDTO> crmList = new ArrayList<PermissionLoadDTO>();
				JSONArray jsonarrcrm = jsonhrArr.getJSONArray("list");
				for (int i = 0; i < jsonarrcrm.length(); i++) {
					PermissionLoadDTO dto = new PermissionLoadDTO();
					JSONObject jbt = jsonarrcrm.getJSONObject(i);
					dto.setIsuse(jbt.getBoolean("isuse"));
					dto.setName(jbt.getString("name"));
					dto.setMarks(jbt.getString("marks"));
					crmList.add(dto);
				}
				map.put("hrlist", crmList);
			}
			boolean systemboolean = jsonsystemArr.getBoolean("isuse");
			map.put("system", systemboolean);
			if (systemboolean) {
				List<PermissionLoadDTO> crmList = new ArrayList<PermissionLoadDTO>();
				JSONArray jsonarrcrm = jsonsystemArr.getJSONArray("list");
				for (int i = 0; i < jsonarrcrm.length(); i++) {
					PermissionLoadDTO dto = new PermissionLoadDTO();
					JSONObject jbt = jsonarrcrm.getJSONObject(i);
					dto.setIsuse(jbt.getBoolean("isuse"));
					dto.setName(jbt.getString("name"));
					dto.setMarks(jbt.getString("marks"));
					crmList.add(dto);
				}
				map.put("systemlist", crmList);
			}
			boolean weixinboolean = jsonweixinArr.getBoolean("isuse");
			map.put("weixin", weixinboolean);
			if (weixinboolean) {
				List<PermissionLoadDTO> crmList = new ArrayList<PermissionLoadDTO>();
				JSONArray jsonarrcrm = jsonweixinArr.getJSONArray("list");
				for (int i = 0; i < jsonarrcrm.length(); i++) {
					PermissionLoadDTO dto = new PermissionLoadDTO();
					JSONObject jbt = jsonarrcrm.getJSONObject(i);
					dto.setIsuse(jbt.getBoolean("isuse"));
					dto.setName(jbt.getString("name"));
					dto.setMarks(jbt.getString("marks"));
					crmList.add(dto);
				}
				map.put("weixintemlist", crmList);
			}
			boolean messageInfoboolean = jsonmessageInfoArr.getBoolean("isuse");
			map.put("messageInfo", messageInfoboolean);
			if (messageInfoboolean) {
				List<PermissionLoadDTO> crmList = new ArrayList<PermissionLoadDTO>();
				JSONArray jsonarrcrm = jsonmessageInfoArr.getJSONArray("list");
				for (int i = 0; i < jsonarrcrm.length(); i++) {
					PermissionLoadDTO dto = new PermissionLoadDTO();
					JSONObject jbt = jsonarrcrm.getJSONObject(i);
					dto.setIsuse(jbt.getBoolean("isuse"));
					dto.setName(jbt.getString("name"));
					dto.setMarks(jbt.getString("marks"));
					crmList.add(dto);
				}
				map.put("messageInfolist", crmList);
			}

			map.put("success", true);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "获取数据异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> getCreateGroup(String groupId,
			String groupName, String groupMarks) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("groupId", groupId));
		params.add(new BasicNameValuePair("groupName", groupName));
		params.add(new BasicNameValuePair("groupMarks", groupMarks));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.group, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			String info = person.getString("info");
			Object islogin = null;
			try {
				islogin = person.get("stute");
				loginAjax(username, password);
				return this.getCreateGroup(groupId, groupName, groupMarks);
			} catch (Exception ex) {

			}

			map.put("success", b);
			map.put("info", info);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> getMyGroup(String groupName) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("groupName", groupName));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.allGroup, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			Object islogin = null;
			try {
				islogin = person.get("stute");
				loginAjax(username, password);
				return this.getMyGroup(groupName);
			} catch (Exception ex) {

			}
			if (!b) {
				map.put("success", false);
				map.put("info", "获取数据异常！");
			} else {
				// 解析data
				List<ConpanyGroupDto> weiXinList = new ArrayList<ConpanyGroupDto>();
				JSONArray jsonWXlist = person.getJSONArray("data");
				for (int i = 0; i < jsonWXlist.length(); i++) {
					ConpanyGroupDto dto = new ConpanyGroupDto();
					JSONObject jbt = jsonWXlist.getJSONObject(i);
					dto.setConpanyId(jbt.getLong("conpanyId"));
					dto.setId(jbt.getLong("id"));
					dto.setCreateConpanyGroupUserId(jbt
							.getLong("createConpanyGroupUserId"));
					dto.setCreateConpanyGroupUserName(jbt
							.getString("createConpanyGroupUserName"));
					dto.setCreateConpanyGroupUserTrueName(jbt
							.getString("createConpanyGroupUserTrueName"));
					dto.setGroupMarks(jbt.getString("groupMarks"));
					dto.setGroupName(jbt.getString("groupName"));
					dto.setUpLevelConpanyGroup(jbt
							.getLong("upLevelConpanyGroup"));
					dto.setUserNum(jbt.getLong("userNum"));
					weiXinList.add(dto);
				}
				map.put("data", weiXinList);
			}
			map.put("success", b);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	/**
	 * 获取该用户公司的所有分组
	 */
	@Override
	public List<Map<String, Object>> getAllGroup(String nowpage,
			String countNum, String groupId, String groupName) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("groupId", groupId));
		params.add(new BasicNameValuePair("groupName", groupName));
		params.add(new BasicNameValuePair("nowpage", nowpage));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.allGroup, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			Object islogin = null;
			try {
				islogin = person.get("stute");
				loginAjax(username, password);
				return this.getMyGroup(groupName);
			} catch (Exception ex) {

			}
			if (!b) {
				map.put("success", false);
				map.put("info", "获取数据异常！");
			} else {
				// 解析data
				List<ConpanyGroupDto> weiXinList = new ArrayList<ConpanyGroupDto>();
				JSONArray jsonWXlist = person.getJSONArray("data");
				for (int i = 0; i < jsonWXlist.length(); i++) {
					ConpanyGroupDto dto = new ConpanyGroupDto();
					JSONObject jbt = jsonWXlist.getJSONObject(i);
					dto.setConpanyId(jbt.getLong("conpanyId"));
					dto.setId(jbt.getLong("id"));
					dto.setCreateConpanyGroupUserId(jbt
							.getLong("createConpanyGroupUserId"));
					dto.setCreateConpanyGroupUserName(jbt
							.getString("createConpanyGroupUserName"));
					dto.setCreateConpanyGroupUserTrueName(jbt
							.getString("createConpanyGroupUserTrueName"));
					dto.setGroupMarks(jbt.getString("groupMarks"));
					dto.setGroupName(jbt.getString("groupName"));
					dto.setUpLevelConpanyGroup(jbt
							.getLong("upLevelConpanyGroup"));
					dto.setUserNum(jbt.getLong("userNum"));
					weiXinList.add(dto);
				}
				map.put("data", weiXinList);
			}
			map.put("success", b);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> getGroupConpanyUser(String nowpage,
			String countNum, String groupId, String groupName, String trueName) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("nowpage", nowpage));
		params.add(new BasicNameValuePair("countNum", countNum));
		params.add(new BasicNameValuePair("groupId", groupId));
		params.add(new BasicNameValuePair("groupName", groupName));
		params.add(new BasicNameValuePair("trueName", trueName));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.conpanyGroup, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			Object islogin = null;
			try {
				islogin = person.get("stute");
				loginAjax(username, password);
				return this.getGroupConpanyUser(nowpage, countNum, groupId,
						groupName, trueName);
			} catch (Exception ex) {

			}
			if (!b) {
				map.put("success", false);
				map.put("info", "获取数据异常！");
			} else {
				// 解析data
				List<ConpanyUserDto> weiXinList = new ArrayList<ConpanyUserDto>();
				JSONArray jsonWXlist = person.getJSONArray("data");
				for (int i = 0; i < jsonWXlist.length(); i++) {
					ConpanyUserDto dto = new ConpanyUserDto();
					JSONObject jbt = jsonWXlist.getJSONObject(i);
					dto.setConpanyId(jbt.getLong("conpanyId"));
					dto.setId(jbt.getLong("id"));
					dto.setAccuntEndDate(jbt.getString("accuntEndDate"));
					dto.setAccuntStartDate(jbt.getString("accuntStartDate"));
					dto.setAddress(jbt.getString("address"));
					dto.setConpanyAdmin(jbt.getBoolean("conpanyAdmin"));
					dto.setEmail(jbt.getString("email"));
					dto.setFreeAcccunt(jbt.getBoolean("freeAcccunt"));
					dto.setIdImage(jbt.getString("idImage"));
					dto.setIdNum(jbt.getString("idNum"));
					dto.setImage(jbt.getString("image"));
					dto.setMarks(jbt.getString("marks"));
					dto.setPassword(jbt.getString("password"));
					dto.setPhone(jbt.getString("phone"));
					dto.setPrice(jbt.getInt("price"));
					dto.setSex(jbt.getBoolean("sex"));
					dto.setState(jbt.getString("state"));
					dto.setTrueName(jbt.getString("trueName"));
					dto.setUseLogin(jbt.getBoolean("useLogin"));
					dto.setUsername(jbt.getString("username"));
					weiXinList.add(dto);
				}
				map.put("data", weiXinList);
			}
			map.put("success", b);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> insertConpanyUserToGroup(String userId,
			String groupId) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("userId", userId));
		params.add(new BasicNameValuePair("groupId", groupId));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.conpanyUserGroup, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			String info = person.getString("info");
			Object islogin = null;
			try {
				islogin = person.get("stute");
				loginAjax(username, password);
				return this.insertConpanyUserToGroup(userId, groupId);
			} catch (Exception ex) {

			}
			if (!b) {
				map.put("success", false);
				map.put("info", "获取数据异常！");
			} else {

			}
			map.put("success", b);
			map.put("info", info);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	/**
	 * 从组中删除成员
	 */
	@Override
	public List<Map<String, Object>> deleteConpanyUserFormGroup(String userId,
			String groupId) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("userId", userId));
		params.add(new BasicNameValuePair("groupId", groupId));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.conpanyUserDelGroup, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			String info = person.getString("info");
			Object islogin = null;
			try {
				islogin = person.get("stute");
				loginAjax(username, password);
				return this.deleteConpanyUserFormGroup(userId, groupId);
			} catch (Exception ex) {

			}
			if (!b) {
				map.put("success", false);
				map.put("info", "获取数据异常！");
			} else {

			}
			map.put("success", b);
			map.put("info", info);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	/**
	 * 删除组
	 */
	@Override
	public List<Map<String, Object>> deleteGroup(String groupId) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("groupId", groupId));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.delGroup, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			String info = person.getString("info");
			Object islogin = null;
			try {
				islogin = person.get("stute");
				loginAjax(username, password);
				return this.deleteGroup(groupId);
			} catch (Exception ex) {

			}
			if (!b) {
				map.put("success", false);
				map.put("info", "获取数据异常！");
			} else {

			}
			map.put("success", b);
			map.put("info", info);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> updateGroupInfo(
			String groupId, String groupName, String groupMarks) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("groupId", groupId));
		params.add(new BasicNameValuePair("groupName", groupName));
		params.add(new BasicNameValuePair("groupMarks", groupMarks));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.updateGroupInfo, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			String info = person.getString("info");
			Object islogin = null;
			try {
				islogin = person.get("stute");
				loginAjax(username, password);
				return this.updateGroupInfo( groupId, groupName,
						groupMarks);
			} catch (Exception ex) {

			}
			if (!b) {
				map.put("success", false);
				map.put("info", "获取数据异常！");
			} else {

			}
			map.put("success", b);
			map.put("info", info);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> lookupPremissionAll(String name) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("name", name));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.lookupPremissionAll, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			String info = person.getString("info");
			Object islogin = null;
			try {
				islogin = person.get("stute");
				loginAjax(username, password);
				return this.lookupPremissionAll(name);
			} catch (Exception ex) {

			}
			if (!b) {
				map.put("success", false);
				map.put("info", "获取数据异常！");
			} else {
				// 解析data
				List<SoftPermissionDto> spList = new ArrayList<SoftPermissionDto>();
				JSONArray jsonWXlist = person.getJSONArray("data");
				for (int i = 0; i < jsonWXlist.length(); i++) {
					SoftPermissionDto dto = new SoftPermissionDto();
					JSONObject jbt = jsonWXlist.getJSONObject(i);
					dto.setId(jbt.getLong("id"));
					dto.setFunctionName(jbt.getString("functionName"));
					dto.setMarks(jbt.getString("marks"));
					dto.setUplevel(jbt.getLong("uplevel"));
					dto.setUrl(jbt.getString("url"));
					spList.add(dto);
				}
				map.put("data", spList);
			}
			map.put("success", b);
			map.put("info", info);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	/**
	 * 查看主功能项权限
	 */
	@Override
	public List<Map<String, Object>> lookupPremissionMain(String id, String name) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("groupId", id));
		params.add(new BasicNameValuePair("name", name));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.lookupPremissionMain, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			Object islogin = null;
			try {
				islogin = person.get("stute");
				loginAjax(username, password);
				return this.lookupPremissionMain(id, name);
			} catch (Exception ex) {

			}
			if (!b) {
				map.put("success", false);
				map.put("info", "获取数据异常！");
			} else {
				// 解析data
				List<SoftPermissionDto> spList = new ArrayList<SoftPermissionDto>();
				JSONArray jsonWXlist = person.getJSONArray("data");
				for (int i = 0; i < jsonWXlist.length(); i++) {
					SoftPermissionDto dto = new SoftPermissionDto();
					JSONObject jbt = jsonWXlist.getJSONObject(i);
					dto.setId(jbt.getLong("id"));
					dto.setFunctionName(jbt.getString("functionName"));
					dto.setMarks(jbt.getString("marks"));
					dto.setUplevel(jbt.getLong("uplevel"));
					dto.setUrl(jbt.getString("url"));
					spList.add(dto);
				}
				map.put("data", spList);
			}
			map.put("success", b);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}
	/**
	 * 查看主功能项权限
	 */
	@Override
	public List<Map<String, Object>> lookupPremissionMain(String groupId, String name,String id) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("groupId", groupId));
		params.add(new BasicNameValuePair("name", name));
		params.add(new BasicNameValuePair("id", id));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.lookupPremissionMain, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			Object islogin = null;
			try {
				islogin = person.get("stute");
				loginAjax(username, password);
				return this.lookupPremissionMain(groupId, name,id);
			} catch (Exception ex) {

			}
			if (!b) {
				map.put("success", false);
				map.put("info", "获取数据异常！");
			} else {
				// 解析data
				List<SoftPermissionDto> spList = new ArrayList<SoftPermissionDto>();
				JSONArray jsonWXlist = person.getJSONArray("data");
				for (int i = 0; i < jsonWXlist.length(); i++) {
					SoftPermissionDto dto = new SoftPermissionDto();
					JSONObject jbt = jsonWXlist.getJSONObject(i);
					dto.setId(jbt.getLong("id"));
					dto.setFunctionName(jbt.getString("functionName"));
					dto.setMarks(jbt.getString("marks"));
					dto.setUplevel(jbt.getLong("uplevel"));
					dto.setUrl(jbt.getString("url"));
					spList.add(dto);
				}
				map.put("data", spList);
			}
			map.put("success", b);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}
	@Override
	public List<Map<String, Object>> addPermissionToRole(String isAll,
			String permissionId, String roleId) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("isAll", isAll));
		params.add(new BasicNameValuePair("permissionId", permissionId));
		params.add(new BasicNameValuePair("roleId", roleId));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.addPermissionToRole, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			String info = person.getString("info");
			Object islogin = null;
			try {
				islogin = person.get("stute");
				loginAjax(username, password);
				return this.addPermissionToRole(isAll, permissionId, roleId);
			} catch (Exception ex) {

			}
			if (!b) {
				map.put("success", false);
				map.put("info", "获取数据异常！");
			} else {

			}
			map.put("success", b);
			map.put("info", info);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	/**
	 * 删除权限到角色
	 */
	@Override
	public List<Map<String, Object>> deletePermissionToRole(
			String permissionId, String roleId) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("permissionId", permissionId));
		params.add(new BasicNameValuePair("roleId", roleId));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.deletePermissionToRole, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			String info = person.getString("info");
			Object islogin = null;
			try {
				islogin = person.get("stute");
				loginAjax(username, password);
				return this.deletePermissionToRole(permissionId, roleId);
			} catch (Exception ex) {

			}
			if (!b) {
				map.put("success", false);
				map.put("info", "获取数据异常！");
			} else {

			}
			map.put("success", b);
			map.put("info", info);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	/**
	 * 添加角色
	 */
	@Override
	public List<Map<String, Object>> addRole(String name, String marks,
			String groupId) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("roleName", name));
		params.add(new BasicNameValuePair("roleMarks", marks));
		params.add(new BasicNameValuePair("groupId", groupId));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.addRole, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			String info = person.getString("info");
			Object islogin = null;
			try {
				islogin = person.get("stute");
				loginAjax(username, password);
				return this.addRole(name, marks, groupId);
			} catch (Exception ex) {

			}
			if (!b) {
				map.put("success", false);
				map.put("info", "获取数据异常！");
			} else {

			}
			map.put("success", b);
			map.put("info", info);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	/**
	 * 删除角色
	 */
	@Override
	public List<Map<String, Object>> deleteRole(String roleId, String groupId) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("roleId", roleId));
		params.add(new BasicNameValuePair("groupId", groupId));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.deleteRole, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			String info = person.getString("info");
			Object islogin = null;
			try {
				islogin = person.get("stute");
				loginAjax(username, password);
				return this.deleteRole(roleId, groupId);
			} catch (Exception ex) {

			}
			if (!b) {
				map.put("success", false);
				map.put("info", "获取数据异常！");
			} else {

			}
			map.put("success", b);
			map.put("info", info);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	/**
	 * 查看所有角色
	 */
	@Override
	public List<Map<String, Object>> lookupRoleAll(String groupId,
			String roleName) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("roleName", roleName));
		params.add(new BasicNameValuePair("groupId", groupId));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.lookupRoleAll, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			Object islogin = null;
			try {
				islogin = person.get("stute");
				loginAjax(username, password);
				return this.lookupRoleAll(groupId, roleName);
			} catch (Exception ex) {

			}
			if (!b) {
				map.put("success", false);
				map.put("info", "获取数据异常！");
			} else {
				// 解析data
				List<RoleDto> weiXinList = new ArrayList<RoleDto>();
				JSONArray jsonWXlist = person.getJSONArray("data");
				for (int i = 0; i < jsonWXlist.length(); i++) {
					RoleDto dto = new RoleDto();
					JSONObject jbt = jsonWXlist.getJSONObject(i);
					dto.setConpanyId(jbt.getLong("conpanyId"));
					dto.setId(jbt.getLong("id"));
					dto.setName(jbt.getString("name"));
					dto.setConpanyAdminRole(jbt.getBoolean("conpanyAdminRole"));
					dto.setGroupid(jbt.getLong("groupid"));
					dto.setGroupName(jbt.getString("groupName"));
					dto.setMarks(jbt.getString("marks"));
					weiXinList.add(dto);
				}
				map.put("data", weiXinList);
			}
			map.put("success", b);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	/**
	 * 查看一个用户在该组内担当的角色
	 */
	@Override
	public List<Map<String, Object>> lookupRoleByUser(String userid,
			String groupid) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("userId", userid));
		params.add(new BasicNameValuePair("groupId", groupid));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.lookupRoleByUser, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			Object islogin = null;
			try {
				islogin = person.get("stute");
				loginAjax(username, password);
				return this.lookupRoleByUser(userid, groupid);
			} catch (Exception ex) {

			}
			if (!b) {
				map.put("success", false);
				map.put("info", "获取数据异常！");
			} else {
				// 解析data
				List<RoleDto> weiXinList = new ArrayList<RoleDto>();
				JSONArray jsonWXlist = person.getJSONArray("data");
				for (int i = 0; i < jsonWXlist.length(); i++) {
					RoleDto dto = new RoleDto();
					JSONObject jbt = jsonWXlist.getJSONObject(i);
					dto.setConpanyId(jbt.getLong("conpanyId"));
					dto.setId(jbt.getLong("id"));
					dto.setName(jbt.getString("name"));
					dto.setConpanyAdminRole(jbt.getBoolean("conpanyAdminRole"));
					dto.setGroupid(jbt.getLong("groupid"));
					dto.setGroupName(jbt.getString("groupName"));
					dto.setMarks(jbt.getString("marks"));
					weiXinList.add(dto);
				}
				map.put("data", weiXinList);
			}
			map.put("success", b);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	/**
	 * 查看一角色的所有权限
	 */
	@Override
	public List<Map<String, Object>> lookupPremissionByRole(String name,
			String groupId, String roleId) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("name", name));
		params.add(new BasicNameValuePair("groupId", groupId));
		params.add(new BasicNameValuePair("roleId", roleId));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.lookupPremissionByRole, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			Object islogin = null;
			try {
				islogin = person.get("stute");
				loginAjax(username, password);
				return this.lookupPremissionByRole(name, groupId, roleId);
			} catch (Exception ex) {

			}
			if (!b) {
				map.put("success", false);
				map.put("info", "获取数据异常！");
			} else {
				// 解析data
				List<SoftPermissionDto> spList = new ArrayList<SoftPermissionDto>();
				JSONArray jsonWXlist = person.getJSONArray("data");
				if(jsonWXlist.length()<=0){
					map.put("info", "不存在权限！");
					return null;
				}
				for (int i = 0; i < 260; i++) {
					SoftPermissionDto dto = new SoftPermissionDto();
					JSONObject jbt = jsonWXlist.getJSONObject(i);
					dto.setId(jbt.getLong("id"));
					dto.setFunctionName(jbt.getString("functionName"));
					dto.setMarks(jbt.getString("marks"));
					dto.setUplevel(jbt.getLong("uplevel"));
					dto.setUrl(jbt.getString("url"));
					spList.add(dto);
				}
				map.put("data", spList);
			}
			map.put("success", b);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	/**
	 * 添加角色到用户
	 */
	@Override
	public List<Map<String, Object>> addRoleToConpanyUser(String roleid,
			String groupId, String userId) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("userId", userId));
		params.add(new BasicNameValuePair("groupId", groupId));
		params.add(new BasicNameValuePair("roleid", roleid));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.addRoleToConpanyUser, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			Object islogin = null;
			try {
				islogin = person.get("stute");
				loginAjax(username, password);
				return this.addRoleToConpanyUser(roleid, groupId, userId);
			} catch (Exception ex) {

			}
			if (!b) {
				map.put("success", false);
				map.put("info", "获取数据异常！");
			} else {

			}
			map.put("success", b);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> deleteRoleToConpanyUser(String roleid,
			String groupId, String userId) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("userId", userId));
		params.add(new BasicNameValuePair("groupId", groupId));
		params.add(new BasicNameValuePair("roleid", roleid));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.deleteRoleToConpanyUser, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			Object islogin = null;
			try {
				islogin = person.get("stute");
				loginAjax(username, password);
				return this.deleteRoleToConpanyUser(roleid, groupId, userId);
			} catch (Exception ex) {

			}
			if (!b) {
				map.put("success", false);
				map.put("info", "获取数据异常！");
			} else {

			}
			map.put("success", b);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	/**
	 * 创建机会
	 */
	@Override
	public List<Map<String, Object>> createChance(String CustomerName,
			String CustomerAddress, String CustomerType, String CustomerLevel,
			String CustomerMark, String CreateManMark, String NotesUserId,
			String State) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("CustomerName", CustomerName));
		params.add(new BasicNameValuePair("CustomerAddress", CustomerAddress));
		params.add(new BasicNameValuePair("CustomerType", CustomerType));
		params.add(new BasicNameValuePair("CustomerLevel", CustomerLevel));
		params.add(new BasicNameValuePair("CustomerMark", CustomerMark));
		params.add(new BasicNameValuePair("CreateManMark", CreateManMark));
		params.add(new BasicNameValuePair("NotesUserId", NotesUserId));
		params.add(new BasicNameValuePair("State", State));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.createChance, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			Object islogin = null;
			try {
				islogin = person.get("stute");
				loginAjax(username, password);
				return this.createChance(CustomerName, CustomerAddress,
						CustomerType, CustomerLevel, CustomerMark,
						CreateManMark, NotesUserId, State);
			} catch (Exception ex) {

			}
			if (!b) {
				map.put("success", false);
				map.put("info", "获取数据异常！");
			} else {

			}
			map.put("success", b);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	/**
	 * 修改机会
	 */
	@Override
	public List<Map<String, Object>> updateChance(String CustomerName,
			String CustomerAddress, String CustomerType, String CustomerLevel,
			String CustomerMark, String CreateManMark, String NotesUserId,
			String State, String ChanceId) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("CustomerName", CustomerName));
		params.add(new BasicNameValuePair("CustomerAddress", CustomerAddress));
		params.add(new BasicNameValuePair("CustomerType", CustomerType));
		params.add(new BasicNameValuePair("CustomerLevel", CustomerLevel));
		params.add(new BasicNameValuePair("CustomerMark", CustomerMark));
		params.add(new BasicNameValuePair("CreateManMark", CreateManMark));
		params.add(new BasicNameValuePair("NotesUserId", NotesUserId));
		params.add(new BasicNameValuePair("State", State));
		params.add(new BasicNameValuePair("ChanceId", ChanceId));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.updateChance, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			Object islogin = null;
			try {
				islogin = person.get("stute");
				loginAjax(username, password);
				return this.updateChance(CustomerName, CustomerAddress,
						CustomerType, CustomerLevel, CustomerMark,
						CreateManMark, NotesUserId, State, ChanceId);
			} catch (Exception ex) {

			}
			if (!b) {
				map.put("success", false);
				map.put("info", "获取数据异常！");
			} else {

			}
			map.put("success", b);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	/**
	 * 删除机会
	 */
	@Override
	public List<Map<String, Object>> DeleteChance(String ChanceId) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("ChanceId", ChanceId));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.DeleteChance, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			Object islogin = null;
			try {
				islogin = person.get("stute");
				loginAjax(username, password);
				return this.DeleteChance(ChanceId);
			} catch (Exception ex) {

			}
			if (!b) {
				map.put("success", false);
				map.put("info", "获取数据异常！");
			} else {

			}
			map.put("success", b);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	/**
	 * 分配机会
	 */
	@Override
	public List<Map<String, Object>> allocationChance(String NotesUserId,
			String ChanceId) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("NotesUserId", NotesUserId));
		params.add(new BasicNameValuePair("ChanceId", ChanceId));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.allocationChance, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			Object islogin = null;
			try {
				islogin = person.get("stute");
				loginAjax(username, password);
				return this.allocationChance(NotesUserId, ChanceId);
			} catch (Exception ex) {

			}
			if (!b) {
				map.put("success", false);
				map.put("info", "获取数据异常！");
			} else {

			}
			map.put("success", b);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	/**
	 * 创建流程
	 */
	@Override
	public List<Map<String, Object>> createFlow(String chanceId, String name,
			String jsonString) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("name", name));
		params.add(new BasicNameValuePair("chanceId", chanceId));
		params.add(new BasicNameValuePair("jsonString", jsonString));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.createFlow, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			Object islogin = null;
			try {
				islogin = person.get("stute");
				loginAjax(username, password);
				return this.createFlow(chanceId, name, jsonString);
			} catch (Exception ex) {

			}
			if (!b) {
				map.put("success", false);
				map.put("info", "获取数据异常！");
			} else {

			}
			map.put("success", b);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> updateFlow(String name, String flowId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> deleteFlow(String id) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", id));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.deleteFlow, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			Object islogin = null;
			try {
				islogin = person.get("stute");
				loginAjax(username, password);
				return this.deleteFlow(id);
			} catch (Exception ex) {

			}
			if (!b) {
				map.put("success", false);
				map.put("info", "获取数据异常！");
			} else {

			}
			map.put("success", b);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> createFlowItem(String type,
			String startDate, String endDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> updateFlowItem(String type,
			String startDate, String endDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> forwordFlowItem(String type,
			String startDate, String endDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> updateLinkMan(String id,
			String linkManName, String linkManSex, String linkManPhone,
			String linkManJob, String linkManMark, String linkManBirthday) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", id));
		params.add(new BasicNameValuePair("linkManName", linkManName));
		params.add(new BasicNameValuePair("linkManSex", linkManSex));
		params.add(new BasicNameValuePair("linkManPhone", linkManPhone));
		params.add(new BasicNameValuePair("linkManJob", linkManJob));
		params.add(new BasicNameValuePair("linkManMark", linkManMark));
		params.add(new BasicNameValuePair("linkManBirthday", linkManBirthday));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.updateLinkMan, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			Object islogin = null;
			try {
				islogin = person.get("stute");
				loginAjax(username, password);
				return this.updateLinkMan(id, linkManName, linkManSex,
						linkManPhone, linkManJob, linkManMark, linkManBirthday);
			} catch (Exception ex) {

			}
			if (!b) {
				map.put("success", false);
				map.put("info", "获取数据异常！");
			} else {

			}
			map.put("success", b);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> changeState(String state, String chanceId) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 查询自己创建的机会
	 */
	@Override
	public List<Map<String, Object>> queryMyChance(String nowpage,
			String countNum, String ChanceName) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("nowpage", nowpage));
		params.add(new BasicNameValuePair("countNum", countNum));
		params.add(new BasicNameValuePair("ChanceName", ChanceName));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.queryMyChance, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			int pagenum = person.getInt("pagenum");
			Object islogin = null;
			try {
				islogin = person.get("stute");
				loginAjax(username, password);
				return this.queryMyChance(nowpage, countNum, ChanceName);
			} catch (Exception ex) {

			}
			if (!b) {
				map.put("success", false);
				map.put("info", "获取数据异常！");
			} else {
				// 解析data
				List<ChanceListDto> weiXinList = new ArrayList<ChanceListDto>();
				JSONArray jsonWXlist = person.getJSONArray("data");
				for (int i = 0; i < jsonWXlist.length(); i++) {
					ChanceListDto dto = new ChanceListDto();
					JSONObject jbt = jsonWXlist.getJSONObject(i);
					dto.setConpanyId(jbt.getLong("conpanyId"));
					dto.setId(jbt.getLong("id"));
					dto.setCreateDate(jbt.getString("createDate"));
					dto.setCreateManId(jbt.getLong("createManId"));
					dto.setCreateManMark(jbt.getString("createManMark"));
					dto.setCreayeManName(jbt.getString("creayeManName"));
					dto.setCustomerAddress(jbt.getString("customerAddress"));
					dto.setCustomerLevel(jbt.getInt("customerLevel"));
					dto.setCustomerMark(jbt.getString("customerMark"));
					dto.setCustomerName(jbt.getString("customerName"));
					dto.setCustomerType(jbt.getString("customerType"));
					dto.setLastBuy(jbt.getString("lastBuy"));
					dto.setNotesUserId(jbt.getLong("notesUserId"));
					dto.setNotesUserName(jbt.getString("notesUserName"));
					dto.setState(jbt.getInt("state"));
					weiXinList.add(dto);
				}
				map.put("data", weiXinList);
			}
			map.put("success", b);
			map.put("pagenum", pagenum);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	/**
	 * 获取一个机会
	 */
	@Override
	public List<Map<String, Object>> getChance(String ChanceName) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("ChanceName", ChanceName));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.getChance, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			int pagenum = 0;
			Object islogin = null;
			try {
				islogin = person.get("stute");
				loginAjax(username, password);
				return this.getChance(ChanceName);
			} catch (Exception ex) {

			}
			if (!b) {
				map.put("success", false);
				map.put("info", "获取数据异常！");
			} else {
				// 解析data
				ChanceListDto dto = new ChanceListDto();
				JSONObject jbt = person.getJSONObject("obj");
				dto.setConpanyId(jbt.getLong("conpanyId"));
				dto.setId(jbt.getLong("id"));
				dto.setCreateDate(jbt.getString("createDate"));
				dto.setCreateManId(jbt.getLong("createManId"));
				dto.setCreateManMark(jbt.getString("createManMark"));
				dto.setCreayeManName(jbt.getString("creayeManName"));
				dto.setCustomerAddress(jbt.getString("customerAddress"));
				dto.setCustomerLevel(jbt.getInt("customerLevel"));
				dto.setCustomerMark(jbt.getString("customerMark"));
				dto.setCustomerName(jbt.getString("customerName"));
				dto.setCustomerType(jbt.getString("customerType"));
				dto.setLastBuy(jbt.getString("lastBuy"));
				dto.setNotesUserId(jbt.getLong("notesUserId"));
				dto.setNotesUserName(jbt.getString("notesUserName"));
				dto.setState(jbt.getInt("state"));
				map.put("obj", dto);
			}
			map.put("success", b);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	/**
	 * 修改用户自己创建的机会
	 */
	@Override
	public List<Map<String, Object>> updateMyCreateChance(String CustomerName,
			String CustomerAddress, String CustomerType, String CustomerLevel,
			String CustomerMark, String CreateManMark, String NotesUserId,
			String State, String ChanceId) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("CustomerName", CustomerName));
		params.add(new BasicNameValuePair("CustomerAddress", CustomerAddress));
		params.add(new BasicNameValuePair("CustomerType", CustomerType));
		params.add(new BasicNameValuePair("CustomerLevel", CustomerLevel));
		params.add(new BasicNameValuePair("CustomerMark", CustomerMark));
		params.add(new BasicNameValuePair("CreateManMark", CreateManMark));
		params.add(new BasicNameValuePair("NotesUserId", NotesUserId));
		params.add(new BasicNameValuePair("State", State));
		params.add(new BasicNameValuePair("ChanceId", ChanceId));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.updateMyCreateChance, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			int pagenum = person.getInt("pagenum");
			Object islogin = null;
			try {
				islogin = person.get("stute");
				loginAjax(username, password);
				return this.updateMyCreateChance(CustomerName, CustomerAddress,
						CustomerType, CustomerLevel, CustomerMark,
						CreateManMark, NotesUserId, State, ChanceId);
			} catch (Exception ex) {

			}
			if (!b) {
				map.put("success", false);
				map.put("info", "获取数据异常！");
			} else {

			}
			map.put("success", b);
			map.put("pagenum", pagenum);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	/**
	 * 分配自己创建的机会
	 */
	@Override
	public List<Map<String, Object>> allocationMyCreateChance(
			String NotesUserId, String ChanceId) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("NotesUserId", NotesUserId));
		params.add(new BasicNameValuePair("ChanceId", ChanceId));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.allocationMyCreateChance, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			Object islogin = null;
			try {
				islogin = person.get("stute");
				loginAjax(username, password);
				return this.allocationMyCreateChance(NotesUserId, ChanceId);
			} catch (Exception ex) {

			}
			if (!b) {
				map.put("success", false);
				map.put("info", "获取数据异常！");
			} else {

			}
			map.put("success", b);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> DeleteMyCreateChance(String ChanceId) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("ChanceId", ChanceId));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.DeleteMyCreateChance, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			Object islogin = null;
			try {
				islogin = person.get("stute");
				loginAjax(username, password);
				return this.DeleteMyCreateChance(ChanceId);
			} catch (Exception ex) {

			}
			if (!b) {
				map.put("success", false);
				map.put("info", "获取数据异常！");
			} else {

			}
			map.put("success", b);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> queryLinkMan(String chanceName,
			String chanceId, String nowpage, String countNum) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("ChanceName", chanceName));
		params.add(new BasicNameValuePair("chanceId", chanceId));
		params.add(new BasicNameValuePair("nowpage", nowpage));
		params.add(new BasicNameValuePair("countNum", countNum));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.queryLinkMan, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			Object islogin = null;
			try {
				islogin = person.get("stute");
				loginAjax(username, password);
				return this.queryLinkMan(chanceName, chanceId, nowpage,
						countNum);
			} catch (Exception ex) {

			}
			if (!b) {
				map.put("success", false);
				map.put("info", "获取数据异常！");
			} else {
				// 解析data
				List<LinkManListDto> weiXinList = new ArrayList<LinkManListDto>();
				JSONArray jsonWXlist = person.getJSONArray("data");
				for (int i = 0; i < jsonWXlist.length(); i++) {
					LinkManListDto dto = new LinkManListDto();
					JSONObject jbt = jsonWXlist.getJSONObject(i);
					dto.setConpanyId(jbt.getLong("conpanyId"));
					dto.setId(jbt.getLong("id"));
					dto.setAddUserId(jbt.getLong("addUserId"));
					dto.setAddUserName(jbt.getString("addUserName"));
					dto.setChanceListId(jbt.getLong("chanceListId"));
					dto.setChanceListName(jbt.getString("chanceListName"));
					dto.setLinkManBirthday(jbt.getString("linkManBirthday"));
					dto.setLinkManJob(jbt.getString("linkManJob"));
					dto.setLinkManMark(jbt.getString("linkManMark"));
					dto.setLinkManMaxScore(jbt.getDouble("linkManScore"));
					dto.setLinkManName(jbt.getString("linkManName"));
					dto.setLinkManPhone(jbt.getString("linkManPhone"));
					dto.setLinkManScore(jbt.getDouble("linkManScore"));
					dto.setLinkManSex(jbt.getString("linkManSex"));
					dto.setMoney(jbt.getDouble("money"));
					dto.setOpenid(jbt.getString("openid"));
					dto.setUserTableId(jbt.getLong("userTableId"));
					dto.setVipId(jbt.getString("vipId"));
					dto.setVipidNum(jbt.getLong("vipidNum"));
					dto.setVipLevel(jbt.getString("vipLevel"));
					dto.setVipMarks(jbt.getString("vipMarks"));
					weiXinList.add(dto);
				}
				map.put("pagenum", person.getInt("pagenum"));
				map.put("data", weiXinList);
			}
			map.put("success", b);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	/**
	 * 添加联系人
	 */
	@Override
	public List<Map<String, Object>> addLinkMan(String chanceId,
			String linkManName, String linkManSex, String linkManPhone,
			String linkManJob, String linkManMark, String linkManBirthday) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("linkManName", linkManName));
		params.add(new BasicNameValuePair("chanceId", chanceId));
		params.add(new BasicNameValuePair("linkManSex", linkManSex));
		params.add(new BasicNameValuePair("linkManPhone", linkManPhone));
		params.add(new BasicNameValuePair("linkManJob", linkManJob));
		params.add(new BasicNameValuePair("linkManMark", linkManMark));
		params.add(new BasicNameValuePair("linkManBirthday", linkManBirthday));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.addLinkMan, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			String info = person.getString("info");
			Object islogin = null;
			try {
				islogin = person.get("stute");
				loginAjax(username, password);
				return this.addLinkMan(chanceId, linkManName, linkManSex,
						linkManPhone, linkManJob, linkManMark, linkManBirthday);
			} catch (Exception ex) {

			}
			if (!b) {
				map.put("success", false);
				map.put("info", "获取数据异常！");
			} else {

			}
			map.put("success", b);
			map.put("info", info);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> getLinkManById(String linkmanId) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("ChanceName", linkmanId));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.getLinkManById, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			Object islogin = null;
			try {
				islogin = person.get("stute");
				loginAjax(username, password);
				return this.getLinkManById(linkmanId);
			} catch (Exception ex) {

			}
			if (!b) {
				map.put("success", false);
				map.put("info", "获取数据异常！");
			} else {
				LinkManListDto dto = new LinkManListDto();
				JSONObject jbt = person.getJSONObject("obj");
				dto.setConpanyId(jbt.getLong("conpanyId"));
				dto.setId(jbt.getLong("id"));
				dto.setAddUserId(jbt.getLong("addUserId"));
				dto.setAddUserName(jbt.getString("addUserName"));
				dto.setChanceListId(jbt.getLong("chanceListId"));
				dto.setChanceListName(jbt.getString("chanceListName"));
				dto.setLinkManBirthday(jbt.getString("linkManBirthday"));
				dto.setLinkManJob(jbt.getString("linkManJob"));
				dto.setLinkManMark(jbt.getString("linkManMark"));
				dto.setLinkManMaxScore(jbt.getDouble("linkManScore"));
				dto.setLinkManName(jbt.getString("linkManName"));
				dto.setLinkManPhone(jbt.getString("linkManPhone"));
				dto.setLinkManScore(jbt.getDouble("linkManScore"));
				dto.setLinkManSex(jbt.getString("linkManSex"));
				dto.setMoney(jbt.getDouble("money"));
				dto.setOpenid(jbt.getString("openid"));
				dto.setUserTableId(jbt.getLong("userTableId"));
				dto.setVipId(jbt.getString("vipId"));
				dto.setVipidNum(jbt.getLong("vipidNum"));
				dto.setVipLevel(jbt.getString("vipLevel"));
				dto.setVipMarks(jbt.getString("vipMarks"));
				map.put("obj", dto);
			}
			map.put("success", b);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> deleteLinkMan(String id) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", id));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.deleteLinkMan, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			String info = person.getString("info");
			Object islogin = null;
			try {
				islogin = person.get("stute");
				loginAjax(username, password);
				return this.deleteLinkMan(id);
			} catch (Exception ex) {

			}
			if (!b) {
				map.put("success", false);
				map.put("info", "获取数据异常！");
			} else {

			}
			map.put("success", b);
			map.put("info", info);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	/**
	 * 查询开发记录
	 */
	@Override
	public List<Map<String, Object>> queryNotes(String linkManName,
			String chanceId, String nowpage, String countNum) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("ChanceName", linkManName));
		params.add(new BasicNameValuePair("chanceId", chanceId));
		params.add(new BasicNameValuePair("nowpage", nowpage));
		params.add(new BasicNameValuePair("countNum", countNum));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.queryNotes, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			
			Object islogin = null;
			try {
				islogin = person.get("stute");
				loginAjax(username, password);
				return this
						.queryNotes(linkManName, chanceId, nowpage, countNum);
			} catch (Exception ex) {

			}
			if (!b) {
				map.put("success", false);
				map.put("info", "获取数据异常！");
			} else {
				// 解析data
				int pagenum = person.getInt("pagenum");
				List<NotesDto> weiXinList = new ArrayList<NotesDto>();
				JSONArray jsonWXlist = person.getJSONArray("data");
				for (int i = 0; i < jsonWXlist.length(); i++) {
					NotesDto dto = new NotesDto();
					JSONObject jbt = jsonWXlist.getJSONObject(i);
					dto.setConpanyId(jbt.getLong("conpanyId"));
					dto.setId(jbt.getLong("id"));
					dto.setChanceListId(jbt.getLong("chanceListId"));
					dto.setChanceListName(jbt.getString("chanceListName"));
					dto.setEndDate(jbt.getString("endDate"));
					dto.setNotesUserId(jbt.getLong("notesUserId"));
					dto.setNotesUserName(jbt.getString("notesUserName"));
					dto.setStartDate(jbt.getString("startDate"));
					dto.setUpdateDate(jbt.getString("updateDate"));
					weiXinList.add(dto);
				}
				map.put("data", weiXinList);
				map.put("pagenum", pagenum);
			}
			map.put("success", b);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	/**
	 * 开始开发
	 */
	@Override
	public List<Map<String, Object>> startNotes(String chanceId) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("chanceId", chanceId));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.startNotes, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			String info = person.getString("info");
			Object islogin = null;
			try {
				islogin = person.get("stute");
				loginAjax(username, password);
				return this.startNotes(chanceId);
			} catch (Exception ex) {

			}
			if (!b) {
				map.put("success", false);
				map.put("info", "获取数据异常！");
			} else {

			}
			map.put("success", b);
			map.put("info", info);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> addNotesItem(String chanceId,
			String NotesId, String Title, String notesAddress,
			String NotesDriver, String Marks) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("chanceId", chanceId));
		params.add(new BasicNameValuePair("NotesId", NotesId));
		params.add(new BasicNameValuePair("Title", Title));
		params.add(new BasicNameValuePair("notesAddress", notesAddress));
		params.add(new BasicNameValuePair("NotesDriver", NotesDriver));
		params.add(new BasicNameValuePair("Marks", Marks));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.addNotesItem, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			String info = person.getString("info");
			Object islogin = null;
			try {
				islogin = person.get("stute");
				loginAjax(username, password);
				return this.addNotesItem(chanceId, NotesId, Title,
						notesAddress, NotesDriver, Marks);
			} catch (Exception ex) {

			}
			if (!b) {
				map.put("success", false);
				map.put("info", "获取数据异常！");
			} else {

			}
			map.put("success", b);
			map.put("info", info);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	/**
	 * 查询开发记录详情
	 */
	@Override
	public List<Map<String, Object>> queryNotesItem(String NotesId,
			String nowpage, String countNum, String chanceId) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("chanceId", chanceId));
		params.add(new BasicNameValuePair("NotesId", NotesId));
		params.add(new BasicNameValuePair("nowpage", nowpage));
		params.add(new BasicNameValuePair("countNum", countNum));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.queryNotesItem, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			Object islogin = null;
			try {
				islogin = person.get("stute");
				loginAjax(username, password);
				return this
						.queryNotesItem(NotesId, nowpage, countNum, chanceId);
			} catch (Exception ex) {

			}
			if (!b) {
				map.put("success", false);
				map.put("info", "获取数据异常！");
			} else {
				// 解析data
				int pagenum = person.getInt("pagenum");
				List<NotesInfoDto> weiXinList = new ArrayList<NotesInfoDto>();
				JSONArray jsonWXlist = person.getJSONArray("data");
				for (int i = 0; i < jsonWXlist.length(); i++) {
					NotesInfoDto dto = new NotesInfoDto();
					JSONObject jbt = jsonWXlist.getJSONObject(i);
					dto.setConpanyId(jbt.getLong("conpanyId"));
					dto.setId(jbt.getLong("id"));
					dto.setNotesAddress(jbt.getString("notesAddress"));
					dto.setNotesDate(jbt.getString("notesDate"));
					dto.setNotesDriver(jbt.getString("notesDriver"));
					dto.setNotesTitle(jbt.getString("notesTitle"));
					dto.setNotesMark(jbt.getString("notesMark"));
					dto.setNotesUserId(jbt.getLong("notesUserId"));
					dto.setNotesUserName(jbt.getString("notesUserName"));
					weiXinList.add(dto);
				}
				map.put("data", weiXinList);
				map.put("pagenum", pagenum);
			}
			map.put("success", b);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	/**
	 * 查询所有机会
	 */
	@Override
	public List<Map<String, Object>> queryAllChance(String nowpage,
			String countNum, String ChanceName) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("ChanceName", ChanceName));
		params.add(new BasicNameValuePair("nowpage", nowpage));
		params.add(new BasicNameValuePair("countNum", countNum));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.queryAllChance, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			Object islogin = null;
			try {
				islogin = person.get("stute");
				loginAjax(username, password);
				return this.queryAllChance(nowpage, countNum, ChanceName);
			} catch (Exception ex) {

			}
			if (!b) {
				map.put("success", false);
				map.put("info", "获取数据异常！");
			} else {
				// 解析data
				int pagenum = person.getInt("pagenum");
				List<ChanceListDto> weiXinList = new ArrayList<ChanceListDto>();
				JSONArray jsonWXlist = person.getJSONArray("data");
				for (int i = 0; i < jsonWXlist.length(); i++) {
					ChanceListDto dto = new ChanceListDto();
					JSONObject jbt = jsonWXlist.getJSONObject(i);
					dto.setConpanyId(jbt.getLong("conpanyId"));
					dto.setId(jbt.getLong("id"));
					dto.setCreateDate(jbt.getString("createDate"));
					dto.setCreateManId(jbt.getLong("createManId"));
					dto.setCreateManMark(jbt.getString("createManMark"));
					dto.setCreayeManName(jbt.getString("creayeManName"));
					dto.setCustomerAddress(jbt.getString("customerAddress"));
					dto.setCustomerLevel(jbt.getInt("customerLevel"));
					dto.setCustomerMark(jbt.getString("customerMark"));
					dto.setCustomerName(jbt.getString("customerName"));
					dto.setCustomerType(jbt.getString("customerType"));
					dto.setLastBuy(jbt.getString("lastBuy"));
					dto.setNotesUserId(jbt.getLong("notesUserId"));
					dto.setNotesUserName(jbt.getString("notesUserName"));
					dto.setState(jbt.getInt("state"));
					weiXinList.add(dto);
				}
				map.put("data", weiXinList);
				map.put("pagenum", pagenum);
			}
			map.put("success", b);
			
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> queryFlow(String name, String chanceId,
			String nowpage, String countNum) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("name", name));
		params.add(new BasicNameValuePair("chanceId", chanceId));
		params.add(new BasicNameValuePair("nowpage", nowpage));
		params.add(new BasicNameValuePair("countNum", countNum));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.queryFlow, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			String info = person.getString("info");
			int pagenum = person.getInt("pagenum");
			Object islogin = null;
			try {
				islogin = person.get("stute");
				loginAjax(username, password);
				return this.queryFlow(name, chanceId, nowpage, countNum);
			} catch (Exception ex) {

			}
			if (!b) {
				map.put("success", false);
				map.put("info", "获取数据异常！");
			} else {
				// 解析data
				List<ChanceFlowDto> weiXinList = new ArrayList<ChanceFlowDto>();
				JSONArray jsonWXlist = person.getJSONArray("data");
				for (int i = 0; i < jsonWXlist.length(); i++) {
					ChanceFlowDto dto = new ChanceFlowDto();
					JSONObject jbt = jsonWXlist.getJSONObject(i);
					dto.setConpanyId(jbt.getLong("conpanyId"));
					dto.setId(jbt.getLong("id"));
					dto.setCreateDate((Date) jbt.get("createDate"));
					dto.setChanceId(jbt.getLong("chanceId"));
					dto.setCreateUserId(jbt.getLong("createUserId"));
					dto.setCreateUserName(jbt.getString("createUserName"));
					dto.setJsonflow(jbt.getString("jsonflow"));
					dto.setName(jbt.getString("name"));
					weiXinList.add(dto);
				}
				map.put("data", weiXinList);
			}
			map.put("success", b);
			map.put("pagenum", pagenum);
			map.put("info", info);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> queryFlowItem(String id) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", id));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.queryFlowItem, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			String info = person.getString("info");
			Object islogin = null;
			try {
				islogin = person.get("stute");
				loginAjax(username, password);
				return this.queryFlowItem(id);
			} catch (Exception ex) {

			}
			if (!b) {
				map.put("success", false);
				map.put("info", "获取数据异常！");
			} else {
				// 解析data
				ChanceFlowDto dto = new ChanceFlowDto();
				JSONObject jbt = person.getJSONObject("obj");
				dto.setConpanyId(jbt.getLong("conpanyId"));
				dto.setId(jbt.getLong("id"));
				dto.setCreateDate((Date) jbt.get("createDate"));
				dto.setChanceId(jbt.getLong("chanceId"));
				dto.setCreateUserId(jbt.getLong("createUserId"));
				dto.setCreateUserName(jbt.getString("createUserName"));
				dto.setJsonflow(jbt.getString("jsonflow"));
				dto.setName(jbt.getString("name"));
				map.put("obj", dto);
			}
			map.put("success", b);
			map.put("info", info);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> queryMyCustemor(String nowpage,
			String countNum, String ChanceName) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("nowpage", nowpage));
		params.add(new BasicNameValuePair("countNum", countNum));
		params.add(new BasicNameValuePair("ChanceName", ChanceName));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.queryMyCustemor, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			Object islogin = null;
			try {
				islogin = person.get("stute");
				loginAjax(username, password);
				return this.queryMyCustemor(nowpage, countNum, ChanceName);
			} catch (Exception ex) {

			}
			if (!b) {
				map.put("success", false);
				map.put("info", "获取数据异常！");
			} else {
				// 解析data
				int pagenum = person.getInt("pagenum");
				List<ChanceListDto> weiXinList = new ArrayList<ChanceListDto>();
				JSONArray jsonWXlist = person.getJSONArray("data");
				for (int i = 0; i < jsonWXlist.length(); i++) {
					ChanceListDto dto = new ChanceListDto();
					JSONObject jbt = jsonWXlist.getJSONObject(i);
					dto.setConpanyId(jbt.getLong("conpanyId"));
					dto.setId(jbt.getLong("id"));
					dto.setCreateDate(jbt.getString("createDate"));
					dto.setCreateManId(jbt.getLong("createManId"));
					dto.setCreateManMark(jbt.getString("createManMark"));
					dto.setCreayeManName(jbt.getString("creayeManName"));
					dto.setCustomerAddress(jbt.getString("customerAddress"));
					dto.setCustomerLevel(jbt.getInt("customerLevel"));
					dto.setCustomerMark(jbt.getString("customerMark"));
					dto.setCustomerName(jbt.getString("customerName"));
					dto.setCustomerType(jbt.getString("customerType"));
					dto.setLastBuy(jbt.getString("lastBuy"));
					dto.setNotesUserId(jbt.getLong("notesUserId"));
					dto.setNotesUserName(jbt.getString("notesUserName"));
					dto.setState(jbt.getInt("state"));
					weiXinList.add(dto);
				}
				map.put("data", weiXinList);
				map.put("pagenum", pagenum);
			}
			map.put("success", b);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	/**
	 * 查询分配给自己的机会
	 */
	@Override
	public List<Map<String, Object>> queryToMyChance(String nowpage,
			String countNum, String ChanceName) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("nowpage", nowpage));
		params.add(new BasicNameValuePair("countNum", countNum));
		params.add(new BasicNameValuePair("ChanceName", ChanceName));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.queryToMyChance, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			Object islogin = null;
			try {
				islogin = person.get("stute");
				loginAjax(username, password);
				return this.queryToMyChance(nowpage, countNum, ChanceName);
			} catch (Exception ex) {

			}
			if (!b) {
				map.put("success", false);
				map.put("info", "获取数据异常！");
			} else {
				// 解析data
				int pagenum = person.getInt("pagenum");
				List<ChanceListDto> weiXinList = new ArrayList<ChanceListDto>();
				JSONArray jsonWXlist = person.getJSONArray("data");
				for (int i = 0; i < jsonWXlist.length(); i++) {
					ChanceListDto dto = new ChanceListDto();
					JSONObject jbt = jsonWXlist.getJSONObject(i);
					dto.setConpanyId(jbt.getLong("conpanyId"));
					dto.setId(jbt.getLong("id"));
					dto.setCreateDate(jbt.getString("createDate"));
					dto.setCreateManId(jbt.getLong("createManId"));
					dto.setCreateManMark(jbt.getString("createManMark"));
					dto.setCreayeManName(jbt.getString("creayeManName"));
					dto.setCustomerAddress(jbt.getString("customerAddress"));
					dto.setCustomerLevel(jbt.getInt("customerLevel"));
					dto.setCustomerMark(jbt.getString("customerMark"));
					dto.setCustomerName(jbt.getString("customerName"));
					dto.setCustomerType(jbt.getString("customerType"));
					dto.setLastBuy(jbt.getString("lastBuy"));
					dto.setNotesUserId(jbt.getLong("notesUserId"));
					dto.setNotesUserName(jbt.getString("notesUserName"));
					dto.setState(jbt.getInt("state"));
					weiXinList.add(dto);
				}
				map.put("data", weiXinList);
				map.put("pagenum", pagenum);
			}
			map.put("success", b);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> query(String type, String startDate,
			String endDate) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("type", type));
		params.add(new BasicNameValuePair("startDate", startDate));
		params.add(new BasicNameValuePair("endDate", endDate));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.query, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			String info = person.getString("info");
			Object islogin = null;
			try {
				islogin = person.get("stute");
				loginAjax(username, password);
				return this.query(type, startDate, endDate);
			} catch (Exception ex) {

			}
			if (!b) {
				map.put("success", false);
				map.put("info", "获取数据异常！");
			} else {
				// 解析data
				List<DateCharDto> weiXinList = new ArrayList<DateCharDto>();
				JSONArray jsonWXlist = person.getJSONArray("data");
				for (int i = 0; i < jsonWXlist.length(); i++) {
					DateCharDto dto = new DateCharDto();
					JSONObject jbt = jsonWXlist.getJSONObject(i);
					dto.setData((List<Integer>) jbt.get("data"));
					dto.setName(jbt.getString("name"));
					weiXinList.add(dto);
				}
				map.put("data", weiXinList);
				map.put("date", person.getJSONObject("date"));
				DateCharDto dto1 = new DateCharDto();
				JSONObject obj = person.getJSONObject("obj");
				dto1.setData((List<Integer>) obj.get("data"));
				dto1.setName(obj.getString("name"));
				map.put("obj", dto1);
				List<PieCharDto> pcList = new ArrayList<PieCharDto>();
				JSONArray jsonPClist = person.getJSONArray("data");
				for (int i = 0; i < jsonWXlist.length(); i++) {
					PieCharDto dto = new PieCharDto();
					JSONObject jbt = jsonWXlist.getJSONObject(i);
					dto.setColor(jbt.getString("color"));
					dto.setName(jbt.getString("name"));
					dto.setY(jbt.getInt("y"));
					pcList.add(dto);
				}
				map.put("count", pcList);
			}
			map.put("success", b);
			map.put("info", info);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> addGoods(String goodsName,
			String goodsNum, String goodsType, String Price, String inPrice,
			String score, String Spell, String goodsModel,
			String goodsSourceId, String storehouseId) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("goodsName", goodsName));
		params.add(new BasicNameValuePair("goodsNum", goodsNum));
		params.add(new BasicNameValuePair("goodsType", goodsType));
		params.add(new BasicNameValuePair("Price", Price));
		params.add(new BasicNameValuePair("inPrice", inPrice));
		params.add(new BasicNameValuePair("score", score));
		params.add(new BasicNameValuePair("Spell", Spell));
		params.add(new BasicNameValuePair("goodsModel", goodsModel));
		params.add(new BasicNameValuePair("goodsSourceId", goodsSourceId));
		params.add(new BasicNameValuePair("storehouseId", storehouseId));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.addGoods, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			String info = person.getString("info");
			Object islogin = person.get("stute");
			if (!b) {
				if (islogin != null) { // 状态不为空，登录错误
					map.put("login", false);
				} else {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.addGoods(goodsName, goodsNum, goodsType, Price,
							inPrice, score, Spell, goodsModel, goodsSourceId,
							storehouseId);
				}

			} else {

			}
			map.put("success", b);
			map.put("info", info);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> addGoodSource(String name, String address) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("goodsSoucename", name));
		params.add(new BasicNameValuePair("address", address));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.addGoodSource, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			String info = person.getString("info");
			Object islogin = null;
			if (!b) {
				if (islogin != null) { // 状态不为空，登录错误
					map.put("login", false);
				} else {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.addGoodSource(name, address);
				}
			} else {

			}
			map.put("success", b);
			map.put("info", info);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			list.add(map);
		}
		return list;
	}

	/**
	 * 查找供货商
	 */
	@Override
	public List<Map<String, Object>> queryGoodsSource(String name,
			String nowpage, String countNum) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("name", name));
		params.add(new BasicNameValuePair("nowpage", nowpage));
		params.add(new BasicNameValuePair("countNum", countNum));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.queryGoodsSource, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			Object islogin = null;
			if (!b) {
				if (islogin != null) { // 状态不为空，登录错误
					map.put("login", false);
				} else {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.queryGoodsSource(name, nowpage, countNum);
				}
			} else {
				// 解析data
				int pagenum = person.getInt("pagenum");
				List<GoodsSourceDto> weiXinList = new ArrayList<GoodsSourceDto>();
				JSONArray jsonWXlist = person.getJSONArray("data");
				for (int i = 0; i < jsonWXlist.length(); i++) {
					GoodsSourceDto dto = new GoodsSourceDto();
					JSONObject jbt = jsonWXlist.getJSONObject(i);
					dto.setConpanyId(jbt.getLong("conpanyId"));
					dto.setId(jbt.getLong("id"));
					dto.setName(jbt.getString("name"));
					dto.setAddress(jbt.getString("address"));
					weiXinList.add(dto);
				}
				map.put("data", weiXinList);
				map.put("pagenum", pagenum);
			}
			map.put("success", b);			
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			list.add(map);
		}
		return list;
	}

	/**
	 * 删除供货商
	 */
	@Override
	public List<Map<String, Object>> deleteGoodsSource(String id) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", id));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.deleteGoodsSource, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			String info = person.getString("info");
			Object islogin = person.get("stute");
			if (!b) {
				if (islogin != null) { // 状态不为空，登录错误
					map.put("login", false);
				} else {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.deleteGoodsSource(id);
				}
			} else {

			}
			map.put("success", b);
			map.put("info", info);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> getGoodsSource(String id) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", id));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.getGoodsSource, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			Object islogin = null;
			if (!b) {
				if (islogin != null) { // 状态不为空，登录错误
					map.put("login", false);
				} else {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.getGoodsSource(id);
				}
			} else {
				JSONObject obj = person.getJSONObject("obj");
				GoodsSourceDto dto = new GoodsSourceDto();
				dto.setConpanyId(obj.getLong("conpanyId"));
				dto.setId(obj.getLong("id"));
				dto.setName(obj.getString("name"));
				dto.setAddress(obj.getString("address"));
				map.put("obj", dto);
			}
			map.put("success", b);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> updateGoodSource(String id, String name,
			String address) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", id));
		params.add(new BasicNameValuePair("name", name));
		params.add(new BasicNameValuePair("address", address));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.updateGoodSource, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			String info = person.getString("info");
			Object islogin = person.get("stute");
			if (!b) {
				if (islogin != null) { // 状态不为空，登录错误
					map.put("login", false);
				} else {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.updateGoodSource(id, name, address);
				}
			} else {

			}
			map.put("success", b);
			map.put("info", info);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			list.add(map);
		}
		return list;
	}

	/**
	 * 创建订单
	 */
	@Override
	public List<Map<String, Object>> createOrder(String Name, String data,
			String id, String type, String chance) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", id));
		params.add(new BasicNameValuePair("name", Name));
		params.add(new BasicNameValuePair("data", data));
		params.add(new BasicNameValuePair("type", type));
		params.add(new BasicNameValuePair("chanceid", chance));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.createOrder, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			if (!b) {
				Object islogin = null;
				try {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.createOrder(Name, data, id, type, chance);
				} catch (Exception ex) {
					
				}
				String info = person.getString("info");
				map.put("success", b);
				map.put("info", info);
				list.add(map);
				return list;
			} else {

			}
			String info = person.getString("info");
			map.put("success", b);
			map.put("info", info);
			try{
			map.put("scoreNum", person.getString("scoreNum"));
			}catch(Exception e){
				e.printStackTrace();
				map.put("scoreNum","");
			}
			map.put("orderNum", person.getString("orderNum"));
			map.put("orderId", person.getString("orderId"));
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			list.add(map);
		}
		return list;
	}

	/**
	 * 查询订单
	 */
	@Override
	public List<Map<String, Object>> queryOrder(String num, String name,
			String chanceid, String endDate, String startDate, String nowpage,
			String countNum,boolean isNum) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		if(isNum){
			params.add(new BasicNameValuePair("num", num));
			params.add(new BasicNameValuePair("nowpage", nowpage));
			params.add(new BasicNameValuePair("countNum", countNum));
		}else{
			params.add(new BasicNameValuePair("name", name));
			params.add(new BasicNameValuePair("chanceid", chanceid));
			params.add(new BasicNameValuePair("endDate", endDate));
			params.add(new BasicNameValuePair("startDate", startDate));
		}
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.queryOrder, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			
			
			if (!b) {
				Object islogin = null;
				try {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.queryOrder(num, name, chanceid, endDate, startDate, nowpage, countNum, isNum);
				} catch (Exception ex) {

				}
				String info = person.getString("info");
				map.put("success", b);
				map.put("info", info);
				list.add(map);
				return list;
			} else {
				// 解析data
				List<OrdersDto> ordersList = new ArrayList<OrdersDto>();
				JSONArray jsonWXlist = person.getJSONArray("data");
				for (int i = 0; i < jsonWXlist.length(); i++) {
					OrdersDto dto = new OrdersDto();
					JSONObject jbt = jsonWXlist.getJSONObject(i);
					dto.setConpanyId(jbt.getLong("conpanyId"));
					dto.setId(jbt.getLong("id"));
					dto.setChanceId(jbt.getLong("chanceId"));
					dto.setChanceName(jbt.getString("chanceName"));
					dto.setCountPrice(jbt.getDouble("countPrice"));
					dto.setCreateDate( jbt.getString("createDate"));
					dto.setCreateUser(jbt.getLong("createUser"));
					dto.setCreateUserName(jbt.getString("createUserName"));
					dto.setInStoreUser(jbt.getLong("inStoreUser"));
					dto.setInStoreUserName(jbt.getString("inStoreUserName"));
					dto.setLinkmanId(jbt.getLong("linkmanId"));
					dto.setLinkmanName(jbt.getString("linkmanName"));
					dto.setMarks(jbt.getString("marks"));
					dto.setOrderNum(jbt.getString("orderNum"));
					dto.setPay(jbt.getBoolean("pay"));
					dto.setState(jbt.getInt("state"));
					dto.setTitle(jbt.getString("title"));
					ordersList.add(dto);
				}
				map.put("data", ordersList);
			}
			int pagenum = person.getInt("pagenum");
			map.put("success", b);
			map.put("pagenum", pagenum);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			list.add(map);
		}
		return list;
	}

	/**
	 * 删除订单
	 */
	@Override
	public List<Map<String, Object>> deleteOrder(String id) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", id));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.deleteOrder, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			if (!b) {
				Object islogin = null;
				try {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.deleteOrder(id);
				} catch (Exception ex) {

				}
				String info = person.getString("info");
				map.put("success", b);
				map.put("info", info);
				list.add(map);
				return list;
			} else {

			}
			String info = person.getString("info");
			map.put("success", b);
			map.put("info", info);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			list.add(map);
		}
		return list;
	}

	/**
	 * 查看销售订单内容
	 */
	@Override
	public List<Map<String, Object>> queryOrderItem(String id) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", id));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.queryOrderItem, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			if (!b) {
				Object islogin = null;
				try {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.queryOrderItem(id);
				} catch (Exception ex) {

				}
				String info = person.getString("info");
				map.put("success", b);
				map.put("info", info);
				list.add(map);
				return list;
			} else {
				// 解析data
				List<OrdersItemDto> weiXinList = new ArrayList<OrdersItemDto>();
				JSONArray jsonWXlist = person.getJSONArray("data");
				for (int i = 0; i < jsonWXlist.length(); i++) {
					OrdersItemDto dto = new OrdersItemDto();
					JSONObject jbt = jsonWXlist.getJSONObject(i);
					dto.setConpanyId(jbt.getLong("conpanyId"));
					dto.setId(jbt.getLong("id"));
					dto.setScore(jbt.getLong("score"));
					dto.setCodeid(jbt.getString("codeid"));
					dto.setCreateDate(jbt.getString("createDate"));
					dto.setGoodsId(jbt.getLong("goodsId"));
					dto.setGoodsModel(jbt.getString("goodsModel"));
					dto.setGoodsinPrice(jbt.getDouble("goodsinPrice"));
					dto.setGoodsName(jbt.getString("goodsName"));
					dto.setGoodsNum(jbt.getDouble("goodsNum"));
					dto.setGoodsSourceId(jbt.getLong("goodsSourceId"));
					dto.setGoodsSourceName(jbt.getString("goodsSourceName"));
					dto.setGoodsToStorehouseId(jbt
							.getLong("goodsToStorehouseId"));
					dto.setGoodsToStorehouseName(jbt
							.getString("goodsToStorehouseName"));
					dto.setGoodsType(jbt.getString("goodsType"));
					dto.setInOrderId(jbt.getLong("inOrderId"));
					dto.setMarks(jbt.getString("marks"));
					dto.setPrice(jbt.getInt("price"));
					dto.setScore(jbt.getDouble("score"));
					dto.setSpell(jbt.getString("spell"));
					dto.setCountPrice(dto.getPrice()*dto.getGoodsNum());
					weiXinList.add(dto);
				}
				map.put("data", weiXinList);
				OrdersDto dto1 = new OrdersDto();
				JSONObject orders = person.getJSONObject("obj");
				dto1.setConpanyId(orders.getLong("conpanyId"));
				dto1.setId(orders.getLong("id"));
				dto1.setChanceName(orders.getString("chanceName"));
				dto1.setCountPrice(orders.getDouble("countPrice"));
				dto1.setCreateDate( orders.getString("createDate"));
				dto1.setCreateUser(orders.getLong("createUser"));
				dto1.setCreateUserName(orders.getString("createUserName"));
				dto1.setInStoreUser(orders.getLong("inStoreUser"));
				dto1.setInStoreUserName(orders.getString("inStoreUserName"));
				dto1.setLinkmanId(orders.getLong("linkmanId"));
				dto1.setLinkmanName(orders.getString("linkmanName"));
				dto1.setMarks(orders.getString("marks"));
				dto1.setOrderNum(orders.getString("orderNum"));
				dto1.setPay(orders.getBoolean("pay"));
				dto1.setState(orders.getInt("state"));
				dto1.setTitle(orders.getString("title"));
				map.put("obj", dto1);
			}
			map.put("success", b);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> queryInOrderItem(String id) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", id));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.queryInOrderItem, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			if (!b) {
				Object islogin = null;
				try {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.queryInOrderItem(id);
				} catch (Exception ex) {

				}
				String info = person.getString("info");
				map.put("success", b);
				map.put("info", info);
				list.add(map);
				return list;
			} else {
				// 解析data
				List<OrdersItemDto> weiXinList = new ArrayList<OrdersItemDto>();
				JSONArray jsonWXlist = person.getJSONArray("data");
				for (int i = 0; i < jsonWXlist.length(); i++) {
					OrdersItemDto dto = new OrdersItemDto();
					JSONObject jbt = jsonWXlist.getJSONObject(i);
					dto.setConpanyId(jbt.getLong("conpanyId"));
					dto.setId(jbt.getLong("id"));
					dto.setScore(jbt.getLong("score"));
					dto.setCodeid(jbt.getString("codeid"));
					dto.setCreateDate( jbt.getString("createDate"));
					dto.setGoodsId(jbt.getLong("goodsId"));
					dto.setGoodsModel(jbt.getString("goodsModel"));
					dto.setGoodsinPrice(jbt.getDouble("goodsinPrice"));
					dto.setGoodsName(jbt.getString("goodsName"));
					dto.setGoodsNum(jbt.getDouble("goodsNum"));
					dto.setGoodsSourceId(jbt.getLong("goodsSourceId"));
					dto.setGoodsSourceName(jbt.getString("goodsSourceName"));
					dto.setGoodsToStorehouseId(jbt
							.getLong("goodsToStorehouseId"));
					dto.setGoodsToStorehouseName(jbt
							.getString("goodsToStorehouseName"));
					dto.setGoodsType(jbt.getString("goodsType"));
					dto.setInOrderId(jbt.getLong("inOrderId"));
					dto.setMarks(jbt.getString("marks"));
					dto.setPrice(jbt.getInt("price"));
					dto.setScore(jbt.getDouble("score"));
					dto.setSpell(jbt.getString("spell"));
					dto.setCountPrice(dto.getGoodsinPrice()*dto.getGoodsNum());
					weiXinList.add(dto);
				}
				map.put("data", weiXinList);
				InOrderDto dto = new InOrderDto();
				JSONObject orders = person.getJSONObject("obj");
				dto.setConpanyId(orders.getLong("conpanyId"));
				dto.setId(orders.getLong("id"));
				dto.setName(orders.getString("name"));
				dto.setCreateDate(orders.getString("createDate"));
				dto.setCreateUser(orders.getLong("createUser"));
				dto.setCreateUserName(orders.getString("createUserName"));
				dto.setInStoreUser(orders.getLong("inStoreUser"));
				dto.setInStoreUserName(orders.getString("inStoreUserName"));
				dto.setOrderNum(orders.getString("orderNum"));
				dto.setState(orders.getInt("state"));
				map.put("obj", dto);
			}
			map.put("success", b);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("info", "出错了");
			map.put("success", false);
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> OrderInStore(String id) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", id));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.OrderInStore, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			String info = person.getString("info");
			Object islogin = person.get("stute");
			if (!b) {
				if (islogin != null) { // 状态不为空，登录错误
					map.put("login", false);
				} else {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.OrderInStore(id);
				}
			} else {

			}
			map.put("success", b);
			map.put("info", info);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> InOrderInStore(String id) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", id));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.InOrderInStore, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			String info = person.getString("info");
			Object islogin = person.get("stute");
			if (!b) {
				if (islogin != null) { // 状态不为空，登录错误
					map.put("login", false);
				} else {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.InOrderInStore(id);
				}
			} else {

			}
			map.put("success", b);
			map.put("info", info);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> createStoreHouse(String name,
			String address, String tel, String userid) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("Storehouseame", name));
		params.add(new BasicNameValuePair("address", address));
		params.add(new BasicNameValuePair("tel", tel));
		params.add(new BasicNameValuePair("userid", userid));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.createStoreHouse, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			String info = person.getString("info");
			Object islogin = null;
			if (!b) {
				if (islogin != null) { // 状态不为空，登录错误
					map.put("login", false);
				} else {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.createStoreHouse(name, address, tel, userid);
				}
			} else {

			}
			map.put("success", b);
			map.put("info", info);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> updateStoreHouse(String name,
			String address, String tel, String userid, String id) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("Storehouseame", name));
		params.add(new BasicNameValuePair("address", address));
		params.add(new BasicNameValuePair("tel", tel));
		params.add(new BasicNameValuePair("userid", userid));
		params.add(new BasicNameValuePair("id", id));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.updateStoreHouse, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			String info = person.getString("info");
			Object islogin = null;
			if (!b) {
				if (islogin != null) { // 状态不为空，登录错误
					map.put("login", false);
				} else {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this
							.updateStoreHouse(name, address, tel, userid, id);
				}
			} else {

			}
			map.put("success", b);
			map.put("info", info);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> deleteStoreHouse(String id) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", id));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.deleteStoreHouse, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			String info = person.getString("info");
			Object islogin = person.get("stute");
			if (!b) {
				if (islogin != null) { // 状态不为空，登录错误
					map.put("login", false);
				} else {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.deleteStoreHouse(id);
				}
			} else {

			}
			map.put("success", b);
			map.put("info", info);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> StoreHouseToStoreHouse(
			String storeHouseId, String toStoreHouseId) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 创建进货计划
	 */
	@Override
	public List<Map<String, Object>> createInOrder(String Name, String data,
			String id, String type) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("name", Name));
		params.add(new BasicNameValuePair("data", data));
		params.add(new BasicNameValuePair("id", id));
		params.add(new BasicNameValuePair("type", type));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.createInOrder, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			
			if (!b) {
				Object islogin = null;
				try {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.createInOrder(Name, data, id, type);
				} catch (Exception ex) {

				}
				String info = person.getString("info");
				map.put("success", b);
				map.put("info", info);
				list.add(map);
				return list;
			} else {
				map.put("orderNum", person.getString("orderNum"));
				map.put("orderId", person.getLong("orderId"));
			}
			map.put("success", b);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> deleteInOrder(String id) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", id));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.deleteInOrder, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			if (!b) {
				Object islogin = null;
				try {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.queryOrderItem(id);
				} catch (Exception ex) {

				}
				String info = person.getString("info");
				map.put("success", b);
				map.put("info", info);
				list.add(map);
				return list;
			} else {

			}
			String info = person.getString("info");
			map.put("success", b);
			map.put("info", info);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			list.add(map);
		}
		return list;
	}

	/**
	 * 查看订单
	 */
	@Override
	public List<Map<String, Object>> queryInOrder(String num, String name,
			String endDate, String startDate, String nowpage, String countNum,boolean isNum) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();

		params.add(new BasicNameValuePair("nowpage", nowpage));
		params.add(new BasicNameValuePair("countNum", countNum));
		if(isNum){
			params.add(new BasicNameValuePair("num", num));
		}else{
			params.add(new BasicNameValuePair("name", name));
			params.add(new BasicNameValuePair("endDate", endDate));
			params.add(new BasicNameValuePair("startDate", startDate));
		}
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.queryInOrder, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			
			if (!b) {
				Object islogin = null;
				try {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.queryInOrder(num, name, endDate, startDate, nowpage, countNum,isNum);
				} catch (Exception ex) {

				}
				String info = person.getString("info");
				map.put("success", b);
				map.put("info", info);
				list.add(map);
				return list;
			} else {
				// 解析data
				List<InOrderDto> inoList = new ArrayList<InOrderDto>();
				JSONArray jsonWXlist = person.getJSONArray("data");
				for (int i = 0; i < jsonWXlist.length(); i++) {
					InOrderDto dto = new InOrderDto();
					JSONObject jbt = jsonWXlist.getJSONObject(i);
					dto.setConpanyId(jbt.getLong("conpanyId"));
					dto.setId(jbt.getLong("id"));
					dto.setName(jbt.getString("name"));
					dto.setCreateDate(jbt.getString("createDate"));
					dto.setCreateUser(jbt.getLong("createUser"));
					dto.setCreateUserName(jbt.getString("createUserName"));
					dto.setInStoreUser(jbt.getLong("inStoreUser"));
					dto.setInStoreUserName(jbt.getString("inStoreUserName"));
					dto.setOrderNum(jbt.getString("orderNum"));
					dto.setState(jbt.getInt("state"));
					inoList.add(dto);
				}
				int pagenum = person.getInt("pagenum");
				map.put("pagenum",pagenum);
				map.put("data", inoList);
			}
			map.put("success", b);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			list.add(map);
		}
		return list;
	}

	/**
	 * 添加供货商联系人
	 */
	@Override
	public List<Map<String, Object>> addGoodSourceLinkMan(String id,
			String linkManBirthday, String linkname, String phone) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", id));
		params.add(new BasicNameValuePair("linkManBirthday", linkManBirthday));
		params.add(new BasicNameValuePair("linkname", linkname));
		params.add(new BasicNameValuePair("phone", phone));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.addGoodSourceLinkMan, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			Object islogin = null;
			if (!b) {
				if (islogin != null) { // 状态不为空，登录错误
					map.put("login", false);
				} else {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.addGoodSourceLinkMan(id, linkManBirthday,
							linkname, phone);
				}
			} else {

			}
			map.put("success", b);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> queryGoodsSourceLinkman(String name,
			String nowpage, String countNum) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("name", name));
		params.add(new BasicNameValuePair("nowpage", nowpage));
		params.add(new BasicNameValuePair("countNum", countNum));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.queryGoodsSourceLinkman, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			Object islogin = null;
			if (!b) {
				if (islogin != null) { // 状态不为空，登录错误
					map.put("login", false);
				} else {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this
							.queryGoodsSourceLinkman(name, nowpage, countNum);
				}
			} else {
				// 解析data
				int pagenum = person.getInt("pagenum");
				List<GoodsSourceLinkManDto> weiXinList = new ArrayList<GoodsSourceLinkManDto>();
				JSONArray jsonWXlist = person.getJSONArray("data");
				for (int i = 0; i < jsonWXlist.length(); i++) {
					GoodsSourceLinkManDto dto = new GoodsSourceLinkManDto();
					JSONObject jbt = jsonWXlist.getJSONObject(i);
					dto.setConpanyId(jbt.getLong("conpanyId"));
					dto.setId(jbt.getLong("id"));
					dto.setName(jbt.getString("name"));
					dto.setGoodsSourceid(jbt.getLong("goodsSourceid"));
					dto.setGoodsSourceName(jbt.getString("goodsSourceName"));
					dto.setLinkManBirthday(jbt.getString("linkManBirthday"));
					dto.setPhone(jbt.getString("phone"));
					weiXinList.add(dto);
				}
				map.put("data", weiXinList);
				map.put("pagenum", pagenum);
			}
			map.put("success", b);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> updateGoodSourceLinkMan(String id,
			String linkManBirthday, String linkname, String phone) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", id));
		params.add(new BasicNameValuePair("linkManBirthday", linkManBirthday));
		params.add(new BasicNameValuePair("linkname", linkname));
		params.add(new BasicNameValuePair("phone", phone));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.updateGoodSourceLinkMan, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			Object islogin = null;
			if (!b) {
				if (islogin != null) { // 状态不为空，登录错误
					map.put("login", false);
				} else {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.updateGoodSourceLinkMan(id, linkManBirthday,
							linkname, phone);
				}
			} else {

			}
			map.put("success", b);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> deleteGoodsSourceLinkman(String id) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", id));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.deleteGoodsSourceLinkman, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			String info = person.getString("info");
			Object islogin = person.get("stute");
			if (!b) {
				if (islogin != null) { // 状态不为空，登录错误
					map.put("login", false);
				} else {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.deleteGoodsSourceLinkman(id);
				}
			} else {

			}
			map.put("success", b);
			map.put("info", info);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			list.add(map);
		}
		return list;
	}

	/**
	 * 获取单个
	 */
	@Override
	public List<Map<String, Object>> getGoodsSourceLinkMan(String id) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", id));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.getGoodsSourceLinkMan, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			String info = person.getString("info");
			Object islogin = person.get("stute");
			if (!b) {
				if (islogin != null) { // 状态不为空，登录错误
					map.put("login", false);
				} else {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.getGoodsSourceLinkMan(id);
				}
			} else {
				JSONObject gLinkMan = person.getJSONObject("obj");
				GoodsSourceLinkManDto dto = new GoodsSourceLinkManDto();
				dto.setConpanyId(gLinkMan.getLong("conpanyId"));
				dto.setId(gLinkMan.getLong("id"));
				dto.setName(gLinkMan.getString("name"));
				dto.setGoodsSourceid(gLinkMan.getLong("goodsSourceid"));
				dto.setGoodsSourceName(gLinkMan.getString("goodsSourceName"));
				dto.setLinkManBirthday(gLinkMan.getString("linkManBirthday"));
				dto.setPhone(gLinkMan.getString("phone"));
				map.put("obj", dto);
			}
			map.put("success", b);
			map.put("info", info);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			list.add(map);
		}
		return list;
	}

	/**
	 * 查询仓库
	 */
	@Override
	public List<Map<String, Object>> queryStoreHouse(String name,
			String nowpage, String countNum) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("name", name));
		params.add(new BasicNameValuePair("nowpage", nowpage));
		params.add(new BasicNameValuePair("countNum", countNum));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.queryStoreHouse, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			Object islogin = null;
			if (!b) {
				if (islogin != null) { // 状态不为空，登录错误
					map.put("login", false);
				} else {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.queryStoreHouse(name, nowpage, countNum);
				}
			} else {
				// 解析data
				int pagenum = person.getInt("pagenum");
				List<StoreHouseDto> shList = new ArrayList<StoreHouseDto>();
				JSONArray jsonWXlist = person.getJSONArray("data");
				for (int i = 0; i < jsonWXlist.length(); i++) {
					StoreHouseDto dto = new StoreHouseDto();
					JSONObject jbt = jsonWXlist.getJSONObject(i);
					dto.setConpanyId(jbt.getLong("conpanyId"));
					dto.setId(jbt.getLong("id"));
					dto.setName(jbt.getString("name"));
					dto.setAddress(jbt.getString("address"));
					dto.setManagerUserId(jbt.getLong("managerUserId"));
					dto.setManagerUserName(jbt.getString("managerUserName"));
					dto.setTal(jbt.getString("tal"));
					shList.add(dto);
				}
				map.put("data", shList);
				map.put("pagenum", pagenum);
			}
			map.put("success", b);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> getStoreHouse(String id) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", id));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.getStoreHouse, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			Object islogin = null;
			if (!b) {
				if (islogin != null) { // 状态不为空，登录错误
					map.put("login", false);
				} else {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.getStoreHouse(id);
				}
			} else {
				// 解析data
				StoreHouseDto dto = new StoreHouseDto();
				JSONObject jbt = person.getJSONObject("obj");
				dto.setConpanyId(jbt.getLong("conpanyId"));
				dto.setId(jbt.getLong("id"));
				dto.setName(jbt.getString("name"));
				dto.setAddress(jbt.getString("address"));
				dto.setManagerUserId(jbt.getLong("managerUserId"));
				dto.setManagerUserName(jbt.getString("managerUserName"));
				dto.setTal(jbt.getString("tal"));
				map.put("obj", dto);
			}
			map.put("success", b);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> queryGoods(String name, String spell,String nowpage,String countNum) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("name", name));
			params.add(new BasicNameValuePair("spell_query", spell));
			params.add(new BasicNameValuePair("countNum", countNum));
			params.add(new BasicNameValuePair("nowpage", nowpage));
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.queryGoods, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			Object islogin =null;
			if (!b) {
				try{
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.queryGoods(name, spell,nowpage,countNum);
				}catch(Exception ex){
					
				}
				String info = person.getString("info");
				map.put("success", b);
				map.put("info", info);
				list.add(map);
				return list;
			} else {
				// 解析data
				List<GoodsTableDto> weiXinList = new ArrayList<GoodsTableDto>();
				JSONArray jsonWXlist = person.getJSONArray("data");
				for (int i = 0; i < jsonWXlist.length(); i++) {
					GoodsTableDto dto = new GoodsTableDto();
					JSONObject jbt = jsonWXlist.getJSONObject(i);
					dto.setConpanyId(jbt.getLong("conpanyId"));
					dto.setId(jbt.getLong("id"));
					dto.setScore(jbt.getLong("score"));
					dto.setCodeid(jbt.getString("codeid"));
					dto.setGoodsModel(jbt.getString("goodsModel"));
					dto.setGoodsName(jbt.getString("goodsName"));
					dto.setGoodsNum(jbt.getDouble("goodsNum"));
					dto.setGoodsType(jbt.getString("goodsType"));
					dto.setInPrice(jbt.getDouble("inPrice"));
					dto.setPrice(jbt.getInt("price"));
					dto.setSalesNum(jbt.getDouble("salesNum"));
					dto.setScore(jbt.getDouble("score"));
					dto.setSpell(jbt.getString("spell"));
					dto.setTotalInPrice(jbt.getDouble("totalInPrice"));
					dto.setTotalPrice(jbt.getDouble("totalPrice"));
					weiXinList.add(dto);
				}
				map.put("data", weiXinList);
			}
			String pagenum = person.getString("pagenum");
			map.put("success", b);
			map.put("pagenum", pagenum);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			list.add(map);
		}
		return list;
	}
	@Override
	public List<Map<String, Object>> queryGoods(String idcode) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("codeid", idcode));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.queryGoods, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			Object islogin =null;
			if (!b) {
				try{
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.queryGoods(idcode);
				}catch(Exception ex){
					
				}
				String info = person.getString("info");
				map.put("success", b);
				map.put("info", info);
				list.add(map);
				return list;
			} else {
				// 解析data
				List<GoodsTableDto> weiXinList = new ArrayList<GoodsTableDto>();
				JSONArray jsonWXlist = person.getJSONArray("data");
				if(jsonWXlist.length()<=0){
					map.put("success", false);
					map.put("info", "该商品不存在");
					list.add(map);
					return list;
				}
				if(jsonWXlist.length()>=2){
					map.put("success", false);
					map.put("info", "该商品超过两个相同的编号");
					list.add(map);
					return list;
				}
				for (int i = 0; i < jsonWXlist.length(); i++) {
					GoodsTableDto dto = new GoodsTableDto();
					JSONObject jbt = jsonWXlist.getJSONObject(i);
					dto.setConpanyId(jbt.getLong("conpanyId"));
					dto.setId(jbt.getLong("id"));
					dto.setScore(jbt.getLong("score"));
					dto.setCodeid(jbt.getString("codeid"));
					dto.setGoodsModel(jbt.getString("goodsModel"));
					dto.setGoodsName(jbt.getString("goodsName"));
					dto.setGoodsNum(jbt.getDouble("goodsNum"));
					dto.setGoodsType(jbt.getString("goodsType"));
					dto.setInPrice(jbt.getDouble("inPrice"));
					dto.setPrice(jbt.getInt("price"));
					dto.setSalesNum(jbt.getDouble("salesNum"));
					dto.setScore(jbt.getDouble("score"));
					dto.setSpell(jbt.getString("spell"));
					dto.setTotalInPrice(jbt.getDouble("totalInPrice"));
					dto.setTotalPrice(jbt.getDouble("totalPrice"));
					weiXinList.add(dto);
				}
				map.put("data", weiXinList);
			}
			String pagenum = person.getString("pagenum");
			map.put("success", b);
			map.put("pagenum", pagenum);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> getGoods(String id) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", id));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.getGoods, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			Object islogin =null;
			if (!b) {
				try{
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.getGoods(id);
				}catch(Exception ex){
					
				}
				String info = person.getString("info");
				map.put("success", b);
				map.put("info", info);
				list.add(map);
				return list;
			} else {
				// 解析data
				GoodsTableDto dto = new GoodsTableDto();
				JSONObject jbt = person.getJSONObject("obj");
				dto.setConpanyId(jbt.getLong("conpanyId"));
				dto.setId(jbt.getLong("id"));
				dto.setScore(jbt.getLong("score"));
				dto.setCodeid(jbt.getString("codeid"));
				dto.setGoodsModel(jbt.getString("goodsModel"));
				dto.setGoodsName(jbt.getString("goodsName"));
				dto.setGoodsNum(jbt.getDouble("goodsNum"));
				dto.setGoodsType(jbt.getString("goodsType"));
				dto.setInPrice(jbt.getDouble("inPrice"));
				dto.setPrice(jbt.getInt("price"));
				dto.setSalesNum(jbt.getDouble("salesNum"));
				dto.setScore(jbt.getDouble("score"));
				dto.setSpell(jbt.getString("spell"));
				dto.setTotalInPrice(jbt.getDouble("totalInPrice"));
				dto.setTotalPrice(jbt.getDouble("totalPrice"));
				map.put("obj", dto);
			}
			map.put("success", b);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> updatePrice(String id, String updateprice) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", id));
		params.add(new BasicNameValuePair("updateprice", updateprice));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.updatePrice, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			String info = person.getString("info");
			Object islogin =null;
			if (!b) {
				try{
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.updatePrice(id, updateprice);
				}catch(Exception ex){
					
				}
				map.put("success", b);
				map.put("info", info);
				list.add(map);
				return list;
			} else {

			}
			map.put("success", b);
			map.put("info", info);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> queryGoodsLog(String id, String nowpage,
			String countNum) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", id));
		params.add(new BasicNameValuePair("nowpage", nowpage));
		params.add(new BasicNameValuePair("countNum", countNum));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.queryGoodsLog, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			Object islogin =null;
			if (!b) {
				try{
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.queryGoodsLog(id, nowpage, countNum);
				}catch(Exception ex){
					
				}
				String info = person.getString("info");
				map.put("success", b);
				map.put("info", info);
				list.add(map);
				return list;
			} else {
				// 解析data
				List<GoodsLogDto> glList = new ArrayList<GoodsLogDto>();
				JSONArray jsonWXlist = person.getJSONArray("data");
				for (int i = 0; i < jsonWXlist.length(); i++) {
					GoodsLogDto dto = new GoodsLogDto();
					JSONObject jbt = jsonWXlist.getJSONObject(i);
					dto.setConpanyId(jbt.getLong("conpanyId"));
					dto.setId(jbt.getLong("id"));
					dto.setAction(jbt.getInt("action"));
					dto.setChanceId(jbt.getLong("chanceId"));
					dto.setChanceName(jbt.getString("chanceName"));
					dto.setCodeid(jbt.getString("codeid"));
					dto.setCreateManId(jbt.getLong("createManId"));
					dto.setCreateManName(jbt.getString("createManName"));
					dto.setGoodsId(jbt.getLong("goodsId"));
					dto.setGoodsinPrice(jbt.getDouble("goodsinPrice"));
					dto.setGoodsModel(jbt.getString("goodsModel"));
					dto.setGoodsName(jbt.getString("goodsName"));
					dto.setGoodsNum(jbt.getDouble("goodsNum"));
					dto.setGoodsSourceId(jbt.getLong("goodsSourceId"));
					dto.setGoodsSourceName(jbt.getString("goodsSourceName"));
					dto.setGoodsToStorehouseId(jbt
							.getLong("goodsToStorehouseId"));
					dto.setGoodsToStorehouseName(jbt
							.getString("goodsToStorehouseName"));
					dto.setGoodsType(jbt.getString("goodsType"));
					dto.setPrice(jbt.getDouble("price"));
					dto.setSalesNum(jbt.getDouble("salesNum"));
					dto.setSpell(jbt.getString("spell"));
					dto.setStartdate( jbt.getString("startdate"));
					dto.setTotalInPrice(jbt.getDouble("totalInPrice"));
					dto.setTotalPrice(jbt.getDouble("totalPrice"));
					glList.add(dto);
				}
				map.put("data", glList);
			}
			int pagenum = person.getInt("pagenum");
			map.put("success", b);
			map.put("pagenum", pagenum);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			list.add(map);
		}
		return list;
	}

	/**
	 * 获取销售图表数据
	 */
	@Override
	public List<Map<String, Object>> querySaleChat(String startDate,
			String endDate, String id) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", id));
		params.add(new BasicNameValuePair("startDate", startDate));
		params.add(new BasicNameValuePair("endDate", endDate));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.querySaleChat, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			
			Object islogin =null;
			if (!b) {
				try{
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.querySaleChat(startDate, endDate, id);
				}catch(Exception ex){
					
				}
				String info = person.getString("info");
				map.put("success", b);
				map.put("info", info);
				list.add(map);
				return list;
			} else {
				// 解析data
				map.put("json", json);
			}

			map.put("success", b);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			list.add(map);
		}
		return list;
	}

	/**
	 * 获取进货图表
	 */
	@Override
	public List<Map<String, Object>> queryInGoods(String startDate,
			String endDate, String id) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", id));
		params.add(new BasicNameValuePair("startDate", startDate));
		params.add(new BasicNameValuePair("endDate", endDate));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.queryInGoods, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			Object islogin =null;
			if (!b) {
				try{
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.queryInGoods(startDate, endDate, id);
				}catch(Exception ex){
					
				}
				String info = person.getString("info");
				map.put("success", b);
				map.put("info", info);
				list.add(map);
				return list;
			} else {
				// 解析data
				map.put("json", json);
			}

			map.put("success", b);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			list.add(map);
		}
		return list;
	}

	/**
	 * 获取价格趋势
	 */
	@Override
	public List<Map<String, Object>> priceChar(String startDate,
			String endDate, String id) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", id));
		params.add(new BasicNameValuePair("startDate", startDate));
		params.add(new BasicNameValuePair("endDate", endDate));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.priceChar, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			Object islogin =null;
			if (!b) {
				try{
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.priceChar(startDate, endDate, id);
				}catch(Exception ex){
					
				}
				String info = person.getString("info");
				map.put("success", b);
				map.put("info", info);
				list.add(map);
				return list;
			} else {
				// 解析data
				map.put("json", json);
			}
			map.put("success", b);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			list.add(map);
		}
		return list;
	}

	/**
	 * 查询货物储存仓库图表权限
	 */
	@Override
	public List<Map<String, Object>> queryStoreHouseChat(String id) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", id));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.queryStoreHouseChat, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			Object islogin =null;
			if (!b) {
				try{
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.queryStoreHouseChat( id);
				}catch(Exception ex){
					
				}
				String info = person.getString("info");
				map.put("success", b);
				map.put("info", info);
				list.add(map);
				return list;
			} else {
				// 解析data
				map.put("json", json);
			}

			map.put("success", b);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			list.add(map);
		}
		return list;
	}

	/**
	 * 获取一些文档中引用的信息，比如联系人，客户，客户开发进程等等
	 */
	@Override
	public List<Map<String, Object>> getObject(String id, String type) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", id));
		params.add(new BasicNameValuePair("type", type));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.getObject, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			Object islogin = person.get("stute");
			if (!b) {
				if (islogin != null) { // 状态不为空，登录错误
					map.put("login", false);
				} else {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.getObject(id, type);
				}
			} else {
				// 解析data
				if (type.equals("custemor")) {
					ChanceListDto dto = new ChanceListDto();
					JSONObject jbt = person.getJSONObject("obj");
					dto.setConpanyId(jbt.getLong("conpanyId"));
					dto.setId(jbt.getLong("id"));
					dto.setCreateDate(jbt.getString("createDate"));
					dto.setCreateManId(jbt.getLong("createManId"));
					dto.setCreateManMark(jbt.getString("createManMark"));
					dto.setCreayeManName(jbt.getString("creayeManName"));
					dto.setCustomerAddress(jbt.getString("customerAddress"));
					dto.setCustomerLevel(jbt.getInt("customerLevel"));
					dto.setCustomerMark(jbt.getString("customerMark"));
					dto.setCustomerName(jbt.getString("customerName"));
					dto.setCustomerType(jbt.getString("customerType"));
					dto.setLastBuy(jbt.getString("lastBuy"));
					dto.setNotesUserId(jbt.getLong("notesUserId"));
					dto.setNotesUserName(jbt.getString("NotesUserName"));
					dto.setState(jbt.getInt("state"));
					map.put("obj", dto);
				} else if (type.equals("linkman")) {
					LinkManListDto dto = new LinkManListDto();
					JSONObject jbt = person.getJSONObject("obj");
					dto.setConpanyId(jbt.getLong("conpanyId"));
					dto.setId(jbt.getLong("id"));
					dto.setAddUserId(jbt.getLong("addUserId"));
					dto.setAddUserName(jbt.getString("addUserName"));
					dto.setChanceListId(jbt.getLong("chanceListId"));
					dto.setChanceListName(jbt.getString("chanceListName"));
					dto.setLinkManBirthday(jbt.getString("linkManBirthday"));
					dto.setLinkManJob(jbt.getString("linkManJob"));
					dto.setLinkManMark(jbt.getString("linkManMark"));
					dto.setLinkManMaxScore(jbt.getDouble("linkManScore"));
					dto.setLinkManName(jbt.getString("linkManName"));
					dto.setLinkManPhone(jbt.getString("linkManPhone"));
					dto.setLinkManScore(jbt.getDouble("linkManScore"));
					dto.setLinkManSex(jbt.getString("linkManSex"));
					dto.setMoney(jbt.getDouble("money"));
					dto.setOpenid(jbt.getString("openid"));
					dto.setUserTableId(jbt.getLong("userTableId"));
					dto.setVipId(jbt.getString("vipId"));
					dto.setVipidNum(jbt.getLong("vipidNum"));
					dto.setVipLevel(jbt.getString("vipLevel"));
					dto.setVipMarks(jbt.getString("vipMarks"));
					map.put("obj", dto);
				} else if (type.equals("Note")) {
					List<NotesDto> weiXinList = new ArrayList<NotesDto>();
					JSONArray jsonWXlist = person.getJSONArray("data");
					for (int i = 0; i < jsonWXlist.length(); i++) {
						NotesDto dto = new NotesDto();
						JSONObject jbt = jsonWXlist.getJSONObject(i);
						dto.setConpanyId(jbt.getLong("conpanyId"));
						dto.setId(jbt.getLong("id"));
						dto.setChanceListId(jbt.getLong("chanceListId"));
						dto.setChanceListName(jbt.getString("chanceListName"));
						dto.setEndDate(jbt.getString("endDate"));
						dto.setNotesUserId(jbt.getLong("notesUserId"));
						dto.setNotesUserName(jbt.getString("notesUserName"));
						dto.setStartDate(jbt.getString("startDate"));
						dto.setUpdateDate(jbt.getString("updateDate"));
						weiXinList.add(dto);
					}
					map.put("data", weiXinList);
				} else if (type.equals("NoteItem")) {
					NotesInfoDto dto = new NotesInfoDto();
					JSONObject jbt = person.getJSONObject("obj");
					dto.setConpanyId(jbt.getLong("conpanyId"));
					dto.setId(jbt.getLong("id"));
					dto.setNotesAddress(jbt.getString("notesAddress"));
					dto.setNotesDate(jbt.getString("notesDate"));
					dto.setNotesDriver(jbt.getString("notesDriver"));
					dto.setNotesTitle(jbt.getString("notesTitle"));
					dto.setNotesUserId(jbt.getLong("notesUserId"));
					dto.setNotesUserName(jbt.getString("notesUserName"));
					map.put("obj", dto);
				} else if (type.equals("user")) {
					ConpanyUserDto dto = new ConpanyUserDto();
					JSONObject jbt = person.getJSONObject("obj");
					dto.setConpanyId(jbt.getLong("conpanyId"));
					dto.setId(jbt.getLong("id"));
					dto.setAccuntEndDate(jbt.getString("accuntEndDate"));
					dto.setAccuntStartDate(jbt.getString("accuntStartDate"));
					dto.setAddress(jbt.getString("address"));
					dto.setConpanyAdmin(jbt.getBoolean("conpanyAdmin"));
					dto.setEmail(jbt.getString("email"));
					dto.setFreeAcccunt(jbt.getBoolean("freeAcccunt"));
					dto.setIdImage(jbt.getString("idImage"));
					dto.setIdNum(jbt.getString("idNum"));
					dto.setImage(jbt.getString("image"));
					dto.setMarks(jbt.getString("marks"));
					dto.setPassword(jbt.getString("password"));
					dto.setPhone(jbt.getString("phone"));
					dto.setPrice(jbt.getInt("price"));
					dto.setSex(jbt.getBoolean("sex"));
					dto.setState(jbt.getString("state"));
					dto.setTrueName(jbt.getString("trueName"));
					dto.setUseLogin(jbt.getBoolean("useLogin"));
					dto.setUsername(jbt.getString("username"));
					map.put("obj", dto);
				} else if (type.equals("goodSource")) {
					GoodsSourceDto dto = new GoodsSourceDto();
					JSONObject jbt = person.getJSONObject("obj");
					dto.setConpanyId(jbt.getLong("conpanyId"));
					dto.setId(jbt.getLong("id"));
					dto.setName(jbt.getString("name"));
					dto.setAddress(jbt.getString("address"));
					map.put("obj", dto);
				} else if (type.equals("StoreHouse")) {
					StoreHouseDto dto = new StoreHouseDto();
					JSONObject jbt = person.getJSONObject("obj");
					dto.setConpanyId(jbt.getLong("conpanyId"));
					dto.setId(jbt.getLong("id"));
					dto.setName(jbt.getString("name"));
					dto.setAddress(jbt.getString("address"));
					dto.setManagerUserId(jbt.getLong("managerUserId"));
					dto.setManagerUserName(jbt.getString("managerUserName"));
					dto.setTal(jbt.getString("tal"));
					map.put("obj", dto);
				} else if (type.equals("goodsourceLinkMan")) {
					GoodsSourceLinkManDto dto = new GoodsSourceLinkManDto();
					JSONObject jbt = person.getJSONObject("obj");
					dto.setConpanyId(jbt.getLong("conpanyId"));
					dto.setId(jbt.getLong("id"));
					dto.setName(jbt.getString("name"));
					dto.setGoodsSourceid(jbt.getLong("goodsSourceid"));
					dto.setGoodsSourceName(jbt.getString("goodsSourceName"));
					dto.setLinkManBirthday(jbt.getString("linkManBirthday"));
					dto.setPhone(jbt.getString("phone"));
					map.put("obj", dto);
				}
			}

			map.put("success", b);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> addCustemmer(String username,
			String password, String name, String phone, String email,
			String useLogin, String image, String idimage, String stute,
			String idnum, String sex, String marks, String address, String price) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("username", username));
		params.add(new BasicNameValuePair("password", password));
		params.add(new BasicNameValuePair("name", name));
		params.add(new BasicNameValuePair("phone", phone));
		params.add(new BasicNameValuePair("email", email));
		params.add(new BasicNameValuePair("useLogin", useLogin));
		params.add(new BasicNameValuePair("image", image));
		params.add(new BasicNameValuePair("idimage", idimage));
		params.add(new BasicNameValuePair("stute", stute));
		params.add(new BasicNameValuePair("idnum", idnum));
		params.add(new BasicNameValuePair("sex", sex));
		params.add(new BasicNameValuePair("marks", marks));
		params.add(new BasicNameValuePair("address", address));
		params.add(new BasicNameValuePair("price", price));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.addCustemmer, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			String info = person.getString("info");

			if (!b) {
				Object islogin = null;
				try {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.addCustemmer(username, password, name, phone,
							email, useLogin, image, idimage, stute, idnum, sex,
							marks, address, price);
				} catch (Exception ex) {

				}
				map.put("success", false);
				map.put("info", "获取数据失败");
				list.add(map);
				return list;
			} else {

			}
			map.put("success", b);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> queryCustemmer(String nowpage,
			String countNum, String trueName) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("nowpage", nowpage));
		params.add(new BasicNameValuePair("countNum", countNum));
		params.add(new BasicNameValuePair("trueName", trueName));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.queryCustemmer, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			if (!b) {
				Object islogin = null;
				try {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.queryCustemmer(nowpage, countNum, trueName);
				} catch (Exception ex) {

				}
				map.put("success", false);
				map.put("info", "获取数据失败");
				list.add(map);
				return list;
			} else {
				// 解析data
				int pagenum = person.getInt("pagenum");
				List<ConpanyUserDto> weiXinList = new ArrayList<ConpanyUserDto>();
				JSONArray jsonWXlist = person.getJSONArray("data");
				for (int i = 0; i < jsonWXlist.length(); i++) {
					ConpanyUserDto dto = new ConpanyUserDto();
					JSONObject jbt = jsonWXlist.getJSONObject(i);
					dto.setConpanyId(jbt.getLong("conpanyId"));
					dto.setId(jbt.getLong("id"));
					dto.setAccuntEndDate(jbt.getString("accuntEndDate"));
					dto.setAccuntStartDate(jbt.getString("accuntStartDate"));
					dto.setAddress(jbt.getString("address"));
					dto.setConpanyAdmin(jbt.getBoolean("conpanyAdmin"));
					dto.setEmail(jbt.getString("email"));
					dto.setFreeAcccunt(jbt.getBoolean("freeAcccunt"));
					dto.setIdImage(jbt.getString("idImage"));
					dto.setIdNum(jbt.getString("idNum"));
					dto.setImage(jbt.getString("image"));
					dto.setMarks(jbt.getString("marks"));
					dto.setPassword(jbt.getString("password"));
					dto.setPhone(jbt.getString("phone"));
					dto.setPrice(jbt.getInt("price"));
					dto.setSex(jbt.getBoolean("sex"));
					dto.setState(jbt.getString("state"));
					dto.setTrueName(jbt.getString("trueName"));
					dto.setUseLogin(jbt.getBoolean("useLogin"));
					dto.setUsername(jbt.getString("username"));
					weiXinList.add(dto);
				}
				map.put("data", weiXinList);
				map.put("pagenum", pagenum);
			}
			map.put("success", b);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> lookCustemmerInfo(String id) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", id));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.lookCustemmerInfo, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			String info = person.getString("info");

			if (!b) {
				Object islogin = null;
				try {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.lookCustemmerInfo(id);
				} catch (Exception ex) {

				}
				map.put("success", false);
				map.put("info", "获取数据失败");
				list.add(map);
				return list;
			} else {
				// 解析data
				List<ConpanyUserDto> weiXinList = new ArrayList<ConpanyUserDto>();
				JSONArray jsonWXlist = person.getJSONArray("obj");
				for (int i = 0; i < jsonWXlist.length(); i++) {
					ConpanyUserDto dto = new ConpanyUserDto();
					JSONObject jbt = jsonWXlist.getJSONObject(i);
					dto.setConpanyId(jbt.getLong("conpanyId"));
					dto.setId(jbt.getLong("id"));
					dto.setAccuntEndDate(jbt.getString("accuntEndDate"));
					dto.setAccuntStartDate(jbt.getString("accuntStartDate"));
					dto.setAddress(jbt.getString("address"));
					dto.setConpanyAdmin(jbt.getBoolean("conpanyAdmin"));
					dto.setEmail(jbt.getString("email"));
					dto.setFreeAcccunt(jbt.getBoolean("freeAcccunt"));
					dto.setIdImage(jbt.getString("idImage"));
					dto.setIdNum(jbt.getString("idNum"));
					dto.setImage(jbt.getString("image"));
					dto.setMarks(jbt.getString("marks"));
					dto.setPassword(jbt.getString("password"));
					dto.setPhone(jbt.getString("phone"));
					dto.setPrice(jbt.getInt("price"));
					dto.setSex(jbt.getBoolean("sex"));
					dto.setState(jbt.getString("state"));
					dto.setTrueName(jbt.getString("trueName"));
					dto.setUseLogin(jbt.getBoolean("useLogin"));
					dto.setUsername(jbt.getString("username"));
					weiXinList.add(dto);
				}
				map.put("obj", weiXinList);
			}
			map.put("info", info);
			map.put("success", b);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> updateCustemmerInfo(String id,
			String name, String value) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", id));
		params.add(new BasicNameValuePair("name", name));
		params.add(new BasicNameValuePair("value", value));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.updateCustemmerInfo, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			String info = person.getString("info");

			if (!b) {
				Object islogin = null;
				try {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.updateCustemmerInfo(id, name, value);
				} catch (Exception ex) {

				}
				map.put("success", false);
				map.put("info", "获取数据失败");
				list.add(map);
				return list;
			} else {
				// 解析data
//				List<ConpanyUserDto> weiXinList = new ArrayList<ConpanyUserDto>();
//				JSONArray jsonWXlist = person.getJSONArray("obj");
//				for (int i = 0; i < jsonWXlist.length(); i++) {
					ConpanyUserDto dto = new ConpanyUserDto();
					JSONObject jbt = person.getJSONObject("obj");
					dto.setConpanyId(jbt.getLong("conpanyId"));
					dto.setId(jbt.getLong("id"));
					dto.setAccuntEndDate(jbt.getString("accuntEndDate"));
					dto.setAccuntStartDate(jbt.getString("accuntStartDate"));
					dto.setAddress(jbt.getString("address"));
					dto.setConpanyAdmin(jbt.getBoolean("conpanyAdmin"));
					dto.setEmail(jbt.getString("email"));
					dto.setFreeAcccunt(jbt.getBoolean("freeAcccunt"));
					dto.setIdImage(jbt.getString("idImage"));
					dto.setIdNum(jbt.getString("idNum"));
					dto.setImage(jbt.getString("image"));
					dto.setMarks(jbt.getString("marks"));
					dto.setPassword(jbt.getString("password"));
					dto.setPhone(jbt.getString("phone"));
					dto.setPrice(jbt.getInt("price"));
					dto.setSex(jbt.getBoolean("sex"));
					dto.setState(jbt.getString("state"));
					dto.setTrueName(jbt.getString("trueName"));
					dto.setUseLogin(jbt.getBoolean("useLogin"));
					dto.setUsername(jbt.getString("username"));
//					weiXinList.add(dto);
//				}
				map.put("obj", dto);
			}
			map.put("info", info);
			map.put("success", b);
//			map.put("list", person.getJSONArray("list"));
//			map.put("name", person.getString("name"));
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> getCustemmerMeetingInfo(String date) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("date", date));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.getCustemmerMeetingInfo, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");

			if (!b) {
				Object islogin = null;
				try {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.getCustemmerMeetingInfo(date);
				} catch (Exception ex) {

				}
				map.put("success", false);
				map.put("info", "获取数据失败");
				list.add(map);
				return list;
			} else {
				// 解析data
				List<ConpanyUserMeetingDto> cumList = new ArrayList<ConpanyUserMeetingDto>();
				JSONArray jsonWXlist = person.getJSONArray("data");
				for (int i = 0; i < jsonWXlist.length(); i++) {
					ConpanyUserMeetingDto dto = new ConpanyUserMeetingDto();
					JSONObject jbt = jsonWXlist.getJSONObject(i);
					dto.setConpanyId(jbt.getLong("conpanyId"));
					dto.setId(jbt.getLong("id"));
					dto.setConpanyUsertrueName(jbt
							.getString("conpanyUsertrueName"));
					dto.setCreateDate(jbt.getString("createDate"));
					dto.setEndDate( jbt.getString("endDate"));
					dto.setStartDate(jbt.getString("startDate"));
					dto.setStute(jbt.getLong("stute"));
					dto.setStuteMarks(jbt.getString("stuteMarks"));
					dto.setUserId(jbt.getLong("userId"));
					cumList.add(dto);
				}
				map.put("data", cumList);
			}
			map.put("success", b);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> getUpdateMeetingInfo(String type,
			String value, String id) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("type", type));
		params.add(new BasicNameValuePair("value", value));
		params.add(new BasicNameValuePair("id", id));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.getUpdateMeetingInfo, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			String info = person.getString("info");
			if (!b) {
				Object islogin = null;
				try {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.getUpdateMeetingInfo(type, value, id);
				} catch (Exception ex) {

				}
				map.put("success", false);
				map.put("info", "获取数据失败");
				list.add(map);
				return list;
			} else {

			}
			map.put("success", b);
			map.put("info", info);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> getMeetingSet(String ip, String uptime,
			String downtime) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("ip", ip));
		params.add(new BasicNameValuePair("uptime", uptime));
		params.add(new BasicNameValuePair("downtime", downtime));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.getMeetingSet, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			String info = person.getString("info");
			if (!b) {
				Object islogin = null;
				try {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.getMeetingSet(ip, uptime, downtime);
				} catch (Exception ex) {

				}
				map.put("success", false);
				map.put("info", "获取数据失败");
				list.add(map);
				return list;
			} else {

			}
			map.put("success", b);
			map.put("info", info);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	/**
	 * 获取签到时间信息
	 */
	@Override
	public List<Map<String, Object>> getMeetingSetInfo() {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.getMeetingSetInfo, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			if (!b) {
				Object islogin = null;
				try {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.getMeetingSetInfo();
				} catch (Exception ex) {

				}
				map.put("success", false);
				map.put("info", "获取数据失败");
				list.add(map);
				return list;
			} else {
				// 解析data
				List<MeetingDto> weiXinList = new ArrayList<MeetingDto>();
				JSONArray jsonWXlist = person.getJSONArray("data");
				for (int i = 0; i < jsonWXlist.length(); i++) {
					MeetingDto dto = new MeetingDto();
					JSONObject jbt = jsonWXlist.getJSONObject(i);
					dto.setConpanyId(jbt.getLong("conpanyId"));
					dto.setId(jbt.getLong("id"));
					dto.setEndDate(jbt.getString("endDate"));
					dto.setIpAddress(jbt.getString("ipAddress"));
					dto.setIPTest(jbt.getBoolean("iptest"));
					dto.setManagerDate(jbt.getString("managerDate"));
					dto.setManagerUserId(jbt.getLong("managerUserId"));
					dto.setManagerUserTrueName(jbt
							.getString("managerUserTrueName"));
					dto.setStartDate(jbt.getString("startDate"));
					weiXinList.add(dto);
				}
				map.put("data", weiXinList);
				MeetingDto dto1 = new MeetingDto();
				JSONObject meeting = person.getJSONObject("obj");
				dto1.setConpanyId(meeting.getLong("conpanyId"));
				dto1.setId(meeting.getLong("id"));
				dto1.setEndDate(meeting.getString("endDate"));
				dto1.setIpAddress(meeting.getString("ipAddress"));
				dto1.setIPTest(meeting.getBoolean("iptest"));
				dto1.setManagerDate(meeting.getString("managerDate"));
				dto1.setManagerUserId(meeting.getLong("managerUserId"));
				dto1.setManagerUserTrueName(meeting
						.getString("managerUserTrueName"));
				dto1.setStartDate(meeting.getString("startDate"));
				map.put("obj", dto1);
			}
			map.put("success", b);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	/**
	 * 获取绩效信息，绩效所有数据按照月份分类，主要标准有：签到次数，创建客户机会的次数， 创建客户的销售次数，金额，被指定的客户销售次数，金额
	 */
	@Override
	public List<Map<String, Object>> getPerForMance(String startDate,
			String endDate) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("startDate", startDate));
		params.add(new BasicNameValuePair("endDate", endDate));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.getPerForMance, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			if (!b) {
				Object islogin = null;
				try {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.getPerForMance(startDate, endDate);
				} catch (Exception ex) {

				}
				map.put("success", false);
				map.put("info", "获取数据失败");
				list.add(map);
				return list;
			} else {
				// 解析data
				List<PerformanceDto> weiXinList = new ArrayList<PerformanceDto>();
				JSONArray jsonWXlist = person.getJSONArray("data");
				for (int i = 0; i < jsonWXlist.length(); i++) {
					PerformanceDto dto = new PerformanceDto();
					JSONObject jbt = jsonWXlist.getJSONObject(i);
					dto.setConpanyId(jbt.getLong("conpanyId"));
					dto.setId(jbt.getLong("id"));
					dto.setCreateDate(jbt.getString("createDate"));
					dto.setMeetingLastNum(jbt.getLong("meetingLastNum"));
					dto.setMeetingLeave(jbt.getLong("meetingLeave"));
					dto.setMeetingNum(jbt.getLong("meetingNum"));
					dto.setMeetingNumOut(jbt.getLong("meetingOutNum"));
					dto.setMeetingOutNum(jbt.getLong("meetingNumOut"));
					dto.setMyCreateChanceBuyCountPrice(jbt
							.getDouble("myCreateChanceBuyCountPrice"));
					dto.setMyCreateChanceBuyNum(jbt
							.getLong("myCreateChanceBuyNum"));
					dto.setToChanceNum(jbt.getLong("myCreateChanceNum"));
					dto.setUserId(jbt.getLong("userId"));
					dto.setUserTrueName(jbt.getString("userTrueName"));
					weiXinList.add(dto);
				}
				map.put("data", weiXinList);
			}
			map.put("success", b);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> meeting(String marks, String type,String latitude,String longitude) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("marks", marks));
		params.add(new BasicNameValuePair("type", type));
		params.add(new BasicNameValuePair("latitude", latitude));
		params.add(new BasicNameValuePair("longitude", longitude));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.meeting, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");

			if (!b) {
				Object islogin = null;
				try {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.meeting(marks, type,latitude,longitude);
				} catch (Exception ex) {

				}
				map.put("success", false);
				String info = person.getString("info");
				map.put("info", info);
				list.add(map);
				return list;
			}
			if (type.equals("test")) {
				String isup = person.getString("isup");
				map.put("isup", isup);
			} else {
				String info = person.getString("info");
				map.put("info", info);
			}
			map.put("success", b);

			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> main(String type, String startDate,
			String endDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> myCreateCustomChance(String type,
			String startDate, String endDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> toMyCustomChance(String type,
			String startDate, String endDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> MyCustomManager(String type,
			String startDate, String endDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> mainGoods(String type, String startDate,
			String endDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> queryData(String type, String startDate,
			String endDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> inGoods(String type, String startDate,
			String endDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> outGoods(String type, String startDate,
			String endDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> storehouse(String type, String startDate,
			String endDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> GoodSource(String type, String startDate,
			String endDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> mainHr(String type, String startDate,
			String endDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> custemr(String type, String startDate,
			String endDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> meetingHr(String type, String startDate,
			String endDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> performance(String type, String startDate,
			String endDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> systemPermission(String type,
			String startDate, String endDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> mainWeiXin(String type, String startDate,
			String endDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> weixin_UserId_Set(String type,
			String startDate, String endDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> weixin_VIP_set(String type,
			String startDate, String endDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> weixin_Game_set(String type,
			String startDate, String endDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> weixin_convert_set(String type,
			String startDate, String endDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> weixin_Model_set(String type,
			String startDate, String endDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> weixin_Model_set_map(String type,
			String startDate, String endDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> weixin_model_goodsManager(String type,
			String startDate, String endDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> weixin_model_order(String type,
			String startDate, String endDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> queryAllChanceList(String type,
			String startDate, String endDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> mainWifi(String type, String startDate,
			String endDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> mainMess(String type, String startDate,
			String endDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> vipsend(String type, String startDate,
			String endDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> messagetemp(String type, String startDate,
			String endDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> messagelog(String type, String startDate,
			String endDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> getWeXinInfo() {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.getWeXinInfo, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			

			if (!b) {
				Object islogin = null;
				try {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.getWeXinInfo();
				} catch (Exception ex) {

				}
				String info = person.getString("info");
				map.put("success", false);
				map.put("info", info);
				list.add(map);
				return list;
			} else {
				// 解析data
				List<WeiXinDto> weiXinList = new ArrayList<WeiXinDto>();
				JSONObject jbt = person.getJSONObject("data");
				
					WeiXinDto dto = new WeiXinDto();
					dto.setAppId(jbt.getString("appId"));
					dto.setAppSecret(jbt.getString("appSecret"));
					dto.setConpanyId(jbt.getLong("conpanyId"));
					dto.setId(jbt.getLong("id"));
					dto.setName(jbt.getString("name"));
					dto.setTokens(jbt.getString("tokens"));
					dto.setUserid(jbt.getLong("userid"));
					weiXinList.add(dto);
				map.put("data", weiXinList);
			}
			map.put("success", b);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> updateWeXinInfo(String appid,
			String appSecret, String tokens) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("appid", appid));
		params.add(new BasicNameValuePair("appSecret", appSecret));
		params.add(new BasicNameValuePair("tokens", tokens));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.updateWeXinInfo, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			String info = person.getString("info");
			if (!b) {
				Object islogin = null;
				try {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.updateWeXinInfo(appid, appSecret, tokens);
				} catch (Exception ex) {

				}
				
				map.put("success", false);
				map.put("info", info);
				list.add(map);
				return list;
			} else {

			}

			map.put("success", b);
			map.put("info", info);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> updataMenuToWeiXin() {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.updataMenuToWeiXin, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			String info = person.getString("info");
			if (!b) {
				Object islogin = null;
				try {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.updataMenuToWeiXin();
				} catch (Exception ex) {

				}
				map.put("success", false);
				map.put("info", "获取数据失败");
				list.add(map);
				return list;
			} else {

			}
			map.put("success", b);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> getMenu(String id) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", id));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.getMenu, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");

			if (!b) {
				Object islogin = null;
				try {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.getMenu(id);
				} catch (Exception ex) {

				}
				map.put("success", false);
				map.put("info", person.getString("info"));
				list.add(map);
				return list;
			} else {
				// 解析data
				List<WeiXinMenuTableDto> weiXinList = new ArrayList<WeiXinMenuTableDto>();
				JSONArray jsonWXlist = person.getJSONArray("data");
				for (int i = 0; i < jsonWXlist.length(); i++) {
					WeiXinMenuTableDto dto = new WeiXinMenuTableDto();
					JSONObject jbt = jsonWXlist.getJSONObject(i);
					dto.setConpanyId(jbt.getLong("conpanyId"));
					dto.setId(jbt.getLong("id"));
					dto.setName(jbt.getString("name"));
					dto.setKey_is(jbt.getBoolean("key_is"));
					dto.setKeys_s(jbt.getString("keys_s"));
					dto.setPid(jbt.getLong("pid"));
					dto.setType(jbt.getString("type"));
					dto.setUrl_is(jbt.getBoolean("url_is"));
					dto.setUrls_s(jbt.getString("urls_s"));
					weiXinList.add(dto);
				}
				map.put("data", weiXinList);
			}
			map.put("success", b);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "获取数据失败");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> addMenu(String id, String name,
			String url, String key) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", id));
		params.add(new BasicNameValuePair("name", name));
		params.add(new BasicNameValuePair("url", url));
		params.add(new BasicNameValuePair("key", key));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.addMenu, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");

			String info = person.getString("info");
			if (!b) {
				try {
					Object islogin = person.get("stute");
					if (islogin != null) { // 状态不为空，登录错误
						map.put("login", false);
					} else {
						islogin = person.get("stute");
						loginAjax(username, password);
						return this.addMenu(id, name, url, key);
					}
				} catch (Exception ex) {

				}
			}
			map.put("success", b);
			map.put("info", info);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> deleteMenu(String id) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", id));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.deleteMenu, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			String info = person.getString("info");

			if (!b) {
				Object islogin = null;
				try {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.deleteMenu(id);
				} catch (Exception ex) {

				}
				map.put("success", false);
				map.put("info", person.getString("info"));
				list.add(map);
				return list;
			} else {

			}

			map.put("success", b);
			map.put("info", info);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> editMenu(String id, String name,
			String url, String key) {
		// TODO Auto-generated method stub
		Log.e("editMenu", id);
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", id));
		params.add(new BasicNameValuePair("name", name));
		params.add(new BasicNameValuePair("url", url));
		params.add(new BasicNameValuePair("key", key));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.editMenu, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			String info = person.getString("info");
			if (!b) {
				try {
					Object islogin = person.get("stute");
					if (islogin != null) { // 状态不为空，登录错误
						loginAjax(username, password);
						return this.editMenu(id, name, url, key);
					}
				} catch (Exception ex) {

				}
				map.put("success", b);
				map.put("info", info);
				list.add(map);
				return list;
			} else {
				map.put("success", b);
				map.put("info", info);
				list.add(map);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> getMenuItem(String id) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", id));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.getMenuItem, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			String info = person.getString("info");

			if (!b) {
				Object islogin = null;
				try {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.getMenuItem(id);
				} catch (Exception ex) {

				}
				map.put("success", false);
				map.put("info", "获取数据失败");
				list.add(map);
				return list;
			} else {
				// 解析data
				List<WeiXinMenuTableDto> weiXinList = new ArrayList<WeiXinMenuTableDto>();
				JSONArray jsonWXlist = person.getJSONArray("data");
				for (int i = 0; i < jsonWXlist.length(); i++) {
					WeiXinMenuTableDto dto = new WeiXinMenuTableDto();
					JSONObject jbt = jsonWXlist.getJSONObject(i);
					dto.setConpanyId(jbt.getLong("conpanyId"));
					dto.setId(jbt.getLong("id"));
					dto.setName(jbt.getString("name"));
					dto.setKey_is(jbt.getBoolean("key_is"));
					dto.setKeys_s(jbt.getString("keys_s"));
					dto.setPid(jbt.getLong("pid"));
					dto.setType(jbt.getString("type"));
					dto.setUrl_is(jbt.getBoolean("url_is"));
					dto.setUrls_s(jbt.getString("urls_s"));
					weiXinList.add(dto);
				}
				map.put("data", weiXinList);
			}
			map.put("success", b);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> getImage() {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.getImage, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");

			if (!b) {
				Object islogin = null;
				try {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.getImage();
				} catch (Exception ex) {

				}
				try {
					String info = person.getString("info");
					map.put("success", false);
					map.put("info", info);
					list.add(map);
					return list;
				} catch (Exception ex) {

				}
				map.put("success", false);
				map.put("info", "获取数据失败");
				list.add(map);
				return list;
			} else {
				// 解析data
				List<WeiXinReSendDto> weiXinList = new ArrayList<WeiXinReSendDto>();
				JSONArray jsonWXlist = person.getJSONArray("data");
				for (int i = 0; i < jsonWXlist.length(); i++) {
					WeiXinReSendDto dto = new WeiXinReSendDto();
					JSONObject jbt = jsonWXlist.getJSONObject(i);
					dto.setConpanyId(jbt.getLong("conpanyId"));
					dto.setId(jbt.getLong("id"));
					dto.setName(jbt.getString("name"));
					dto.setContent(jbt.getString("content"));
					dto.setDescription(jbt.getString("description"));
					dto.sethQMusicUrl(jbt.getString("hQMusicUrl"));
					dto.setImage(jbt.getString("image"));
					dto.setMediaId(jbt.getString("mediaId"));
					dto.setMusicURL(jbt.getString("musicURL"));
					dto.setPicUrl(jbt.getString("picUrl"));
					dto.setThumbMediaId(jbt.getString("thumbMediaId"));
					dto.setTitle(jbt.getString("title"));
					dto.setUrl(jbt.getString("url"));
					dto.setType(jbt.getInt("type"));
					weiXinList.add(dto);
				}
				map.put("data", weiXinList);
			}
			map.put("success", b);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> getVoice() {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.getVoice, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			if (!b) {
				Object islogin = null;
				try {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.getImage();
				} catch (Exception ex) {

				}
				try {
					String info = person.getString("info");
					map.put("success", false);
					map.put("info", info);
					list.add(map);
					return list;
				} catch (Exception ex) {

				}
				map.put("success", false);
				map.put("info", "获取数据失败");
				list.add(map);
				return list;
			} else {
				// 解析data
				List<WeiXinReSendDto> weiXinList = new ArrayList<WeiXinReSendDto>();
				JSONArray jsonWXlist = person.getJSONArray("data");
				for (int i = 0; i < jsonWXlist.length(); i++) {
					WeiXinReSendDto dto = new WeiXinReSendDto();
					JSONObject jbt = jsonWXlist.getJSONObject(i);
					dto.setConpanyId(jbt.getLong("conpanyId"));
					dto.setId(jbt.getLong("id"));
					dto.setName(jbt.getString("name"));
					dto.setContent(jbt.getString("content"));
					dto.setDescription(jbt.getString("description"));
					dto.sethQMusicUrl(jbt.getString("hQMusicUrl"));
					dto.setImage(jbt.getString("image"));
					dto.setMediaId(jbt.getString("mediaId"));
					dto.setMusicURL(jbt.getString("musicURL"));
					dto.setPicUrl(jbt.getString("picUrl"));
					dto.setThumbMediaId(jbt.getString("thumbMediaId"));
					dto.setTitle(jbt.getString("title"));
					dto.setUrl(jbt.getString("url"));
					dto.setType(jbt.getInt("type"));
					weiXinList.add(dto);
				}
				map.put("data", weiXinList);
			}
			map.put("success", b);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> getVideo() {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.getVideo, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			if (!b) {
				Object islogin = null;
				try {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.getImage();
				} catch (Exception ex) {

				}
				try {
					String info = person.getString("info");
					map.put("success", false);
					map.put("info", info);
					list.add(map);
					return list;
				} catch (Exception ex) {

				}
				map.put("success", false);
				map.put("info", "获取数据失败");
				list.add(map);
				return list;
			} else {
				// 解析data
				List<WeiXinReSendDto> weiXinList = new ArrayList<WeiXinReSendDto>();
				JSONArray jsonWXlist = person.getJSONArray("data");
				for (int i = 0; i < jsonWXlist.length(); i++) {
					WeiXinReSendDto dto = new WeiXinReSendDto();
					JSONObject jbt = jsonWXlist.getJSONObject(i);
					dto.setConpanyId(jbt.getLong("conpanyId"));
					dto.setId(jbt.getLong("id"));
					dto.setName(jbt.getString("name"));
					dto.setContent(jbt.getString("content"));
					dto.setDescription(jbt.getString("description"));
					dto.sethQMusicUrl(jbt.getString("hQMusicUrl"));
					dto.setImage(jbt.getString("image"));
					dto.setMediaId(jbt.getString("mediaId"));
					dto.setMusicURL(jbt.getString("musicURL"));
					dto.setPicUrl(jbt.getString("picUrl"));
					dto.setThumbMediaId(jbt.getString("thumbMediaId"));
					dto.setTitle(jbt.getString("title"));
					dto.setUrl(jbt.getString("url"));
					dto.setType(jbt.getInt("type"));
					weiXinList.add(dto);
				}
				map.put("data", weiXinList);
			}
			map.put("success", b);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> getText() {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.getText, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			if (!b) {
				Object islogin = null;
				try {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.getText();
				} catch (Exception ex) {

				}
				try {
					String info = person.getString("info");
					map.put("success", false);
					map.put("info", info);
					list.add(map);
					return list;
				} catch (Exception ex) {

				}
				String info = person.getString("info");
				map.put("success", false);
				map.put("info", "获取数据错误");
				list.add(map);
				return list;
			} else {
				// 解析data
				List<WeiXinReSendDto> weiXinList = new ArrayList<WeiXinReSendDto>();
				JSONArray jsonWXlist = person.getJSONArray("data");
				for (int i = 0; i < jsonWXlist.length(); i++) {
					WeiXinReSendDto dto = new WeiXinReSendDto();
					JSONObject jbt = jsonWXlist.getJSONObject(i);
					dto.setConpanyId(jbt.getLong("conpanyId"));
					dto.setId(jbt.getLong("id"));
					dto.setName(jbt.getString("name"));
					dto.setContent(jbt.getString("content"));
					dto.setDescription(jbt.getString("description"));
					dto.sethQMusicUrl(jbt.getString("hQMusicUrl"));
					dto.setImage(jbt.getString("image"));
					dto.setMediaId(jbt.getString("mediaId"));
					dto.setMusicURL(jbt.getString("musicURL"));
					dto.setPicUrl(jbt.getString("picUrl"));
					dto.setThumbMediaId(jbt.getString("thumbMediaId"));
					dto.setTitle(jbt.getString("title"));
					dto.setUrl(jbt.getString("url"));
					dto.setType(jbt.getInt("type"));
					weiXinList.add(dto);
				}
				map.put("data", weiXinList);
			}
			map.put("success", b);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> getMusic() {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.getMusic, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			if (!b) {
				Object islogin = null;
				try {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.getImage();
				} catch (Exception ex) {

				}
				try {
					String info = person.getString("info");
					map.put("success", false);
					map.put("info", info);
					list.add(map);
					return list;
				} catch (Exception ex) {

				}
				map.put("success", false);
				map.put("info", "获取数据失败");
				list.add(map);
				return list;
			} else {
				// 解析data
				List<WeiXinReSendDto> weiXinList = new ArrayList<WeiXinReSendDto>();
				JSONArray jsonWXlist = person.getJSONArray("data");
				for (int i = 0; i < jsonWXlist.length(); i++) {
					WeiXinReSendDto dto = new WeiXinReSendDto();
					JSONObject jbt = jsonWXlist.getJSONObject(i);
					dto.setConpanyId(jbt.getLong("conpanyId"));
					dto.setId(jbt.getLong("id"));
					dto.setName(jbt.getString("name"));
					dto.setContent(jbt.getString("content"));
					dto.setDescription(jbt.getString("description"));
					dto.sethQMusicUrl(jbt.getString("hQMusicUrl"));
					dto.setImage(jbt.getString("image"));
					dto.setMediaId(jbt.getString("mediaId"));
					dto.setMusicURL(jbt.getString("musicURL"));
					dto.setPicUrl(jbt.getString("picUrl"));
					dto.setThumbMediaId(jbt.getString("thumbMediaId"));
					dto.setTitle(jbt.getString("title"));
					dto.setUrl(jbt.getString("url"));
					dto.setType(jbt.getInt("type"));
					weiXinList.add(dto);
				}
				map.put("data", weiXinList);
			}
			map.put("success", b);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> getImageText() {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.getImageText, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			if (!b) {
				Object islogin = null;
				try {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.getImage();
				} catch (Exception ex) {

				}
				try {
					String info = person.getString("info");
					map.put("success", false);
					map.put("info", info);
					list.add(map);
					return list;
				} catch (Exception ex) {

				}
				map.put("success", false);
				map.put("info", "获取数据失败");
				list.add(map);
				return list;
			} else {
				// 解析data
				List<WeiXinReSendDto> weiXinList = new ArrayList<WeiXinReSendDto>();
				JSONArray jsonWXlist = person.getJSONArray("data");
				for (int i = 0; i < jsonWXlist.length(); i++) {
					WeiXinReSendDto dto = new WeiXinReSendDto();
					JSONObject jbt = jsonWXlist.getJSONObject(i);
					dto.setConpanyId(jbt.getLong("conpanyId"));
					dto.setId(jbt.getLong("id"));
					dto.setName(jbt.getString("name"));
					dto.setContent(jbt.getString("content"));
					dto.setDescription(jbt.getString("description"));
					dto.sethQMusicUrl(jbt.getString("hQMusicUrl"));
					dto.setImage(jbt.getString("image"));
					dto.setMediaId(jbt.getString("mediaId"));
					dto.setMusicURL(jbt.getString("musicURL"));
					dto.setPicUrl(jbt.getString("picUrl"));
					dto.setThumbMediaId(jbt.getString("thumbMediaId"));
					dto.setTitle(jbt.getString("title"));
					dto.setUrl(jbt.getString("url"));
					dto.setType(jbt.getInt("type"));
					weiXinList.add(dto);
				}
				map.put("data", weiXinList);
			}
			map.put("success", b);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> addImage(String name, String mediaId) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("name", name));
		params.add(new BasicNameValuePair("mediaId", mediaId));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.addImage, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			String info = person.getString("info");
			if (!b) {
				try {
					Object islogin = person.get("stute");

					islogin = person.get("stute");
					loginAjax(username, password);
					return this.addImage(name, mediaId);
				} catch (Exception ex) {

				}
			}

			map.put("success", b);
			map.put("info", info);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> addVideo(String name, String mediaId,
			String title, String description) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("name", name));
		params.add(new BasicNameValuePair("mediaId", mediaId));
		params.add(new BasicNameValuePair("title", title));
		params.add(new BasicNameValuePair("description", description));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.addVideo, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			String info = person.getString("info");
			if (!b) {
				try {
					Object islogin = person.get("stute");

					islogin = person.get("stute");
					loginAjax(username, password);
					return this.addVideo(name, mediaId, title, description);
				} catch (Exception ex) {

				}
			}

			map.put("success", b);
			map.put("info", info);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> addVoice(String name, String mediaId) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("name", name));
		params.add(new BasicNameValuePair("mediaId", mediaId));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.addVoice, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			String info = person.getString("info");
			if (!b) {
				try {
					Object islogin = person.get("stute");

					islogin = person.get("stute");
					loginAjax(username, password);
					return this.addVoice(name, mediaId);
				} catch (Exception ex) {

				}
			}

			map.put("success", b);
			map.put("info", info);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> addText(String name, String content) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("name", name));
		params.add(new BasicNameValuePair("content", content));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.addText, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			String info = person.getString("info");

			if (!b) {
				try {
					Object islogin = person.get("stute");

					islogin = person.get("stute");
					loginAjax(username, password);
					return this.addText(name, content);
				} catch (Exception ex) {

				}
			}

			map.put("success", b);
			map.put("info", info);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "获取数据异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> addMusic(String name, String title,
			String description, String hQMusicUrl, String thumbMediaId,
			String musicURL) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("name", name));
		params.add(new BasicNameValuePair("title", title));
		params.add(new BasicNameValuePair("description", description));
		params.add(new BasicNameValuePair("hQMusicUrl", hQMusicUrl));
		params.add(new BasicNameValuePair("thumbMediaId", thumbMediaId));
		params.add(new BasicNameValuePair("musicURL", musicURL));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.addMusic, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			String info = person.getString("info");
			if (!b) {
				try {
					Object islogin = person.get("stute");

					islogin = person.get("stute");
					loginAjax(username, password);
					return this.addMusic(name, title, description, hQMusicUrl,
							thumbMediaId, musicURL);
				} catch (Exception ex) {

				}
			}

			map.put("success", b);
			map.put("info", info);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> addImageText(String name, String title,
			String description, String picUrl, String url) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("name", name));
		params.add(new BasicNameValuePair("title", title));
		params.add(new BasicNameValuePair("description", description));
		params.add(new BasicNameValuePair("picUrl", picUrl));
		params.add(new BasicNameValuePair("url", url));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.addImageText, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			String info = person.getString("info");
			if (!b) {
				try {
					Object islogin = person.get("stute");

					islogin = person.get("stute");
					loginAjax(username, password);
					return this.addImageText(name, title, description, picUrl,
							url);
				} catch (Exception ex) {

				}
			}

			map.put("success", b);
			map.put("info", info);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> getWeiXinReSend(String id) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", id));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.getWeiXinReSend, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			String info = person.getString("info");
			Object islogin = person.get("stute");
			if (!b) {
				if (islogin != null) { // 状态不为空，登录错误
					map.put("login", false);
				} else {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.getWeiXinReSend(id);
				}
			} else {

			}

			map.put("success", b);
			map.put("info", info);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> updateImage(String id, String name,
			String mediaId) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", id));
		params.add(new BasicNameValuePair("name", name));
		params.add(new BasicNameValuePair("mediaId", mediaId));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.updateImage, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			String info = person.getString("info");
			if (!b) {
				try {
					Object islogin = person.get("stute");
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.updateImage(id, name, mediaId);
				} catch (Exception ex) {

				}
			}
			map.put("success", b);
			map.put("info", info);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> updateVideo(String id, String name,
			String mediaId, String title, String description) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", id));
		params.add(new BasicNameValuePair("name", name));
		params.add(new BasicNameValuePair("mediaId", mediaId));
		params.add(new BasicNameValuePair("title", title));
		params.add(new BasicNameValuePair("description", description));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.updateVideo, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			String info = person.getString("info");
			if (!b) {
				try {
					Object islogin = person.get("stute");
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.updateVideo(id, name, mediaId, title,
							description);
				} catch (Exception ex) {

				}
			}
			map.put("success", b);
			map.put("info", info);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> updateVoice(String id, String name,
			String mediaId) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", id));
		params.add(new BasicNameValuePair("name", name));
		params.add(new BasicNameValuePair("mediaId", mediaId));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.updateVoice, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			String info = person.getString("info");
			if (!b) {
				try {
					Object islogin = person.get("stute");
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.updateVoice(id, name, mediaId);
				} catch (Exception ex) {

				}
			}
			map.put("success", b);
			map.put("info", info);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> updateText(String id, String name,
			String content) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", id));
		params.add(new BasicNameValuePair("name", name));
		params.add(new BasicNameValuePair("content", content));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.updateText, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			String info = person.getString("info");

			if (!b) {
				try {
					Object islogin = person.get("stute");
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.updateText(id, name, content);
				} catch (Exception ex) {

				}
			}
			map.put("success", b);
			map.put("info", info);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> updateMusic(String id, String name,
			String title, String description, String hQMusicUrl,
			String thumbMediaId, String musicURL) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", id));
		params.add(new BasicNameValuePair("name", name));
		params.add(new BasicNameValuePair("title", title));
		params.add(new BasicNameValuePair("description", description));
		params.add(new BasicNameValuePair("hQMusicUrl", hQMusicUrl));
		params.add(new BasicNameValuePair("thumbMediaId", thumbMediaId));
		params.add(new BasicNameValuePair("musicURL", musicURL));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.updateMusic, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			String info = person.getString("info");
			if (!b) {
				try {
					Object islogin = person.get("stute");
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.updateMusic(id, name, title, description,
							hQMusicUrl, thumbMediaId, musicURL);
				} catch (Exception ex) {

				}
			}
			map.put("success", b);
			map.put("info", info);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> updateImageText(String id, String name,
			String title, String description, String picUrl, String url) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", id));
		params.add(new BasicNameValuePair("name", name));
		params.add(new BasicNameValuePair("title", title));
		params.add(new BasicNameValuePair("description", description));
		params.add(new BasicNameValuePair("picUrl", picUrl));
		params.add(new BasicNameValuePair("url", url));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.updateImageText, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			String info = person.getString("info");
			if (!b) {
				try {
					Object islogin = person.get("stute");
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.updateImageText(id, name, title, description,
							picUrl, url);
				} catch (Exception ex) {

				}
			}
			map.put("success", b);
			map.put("info", info);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> deleteReSend(String id) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", id));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.deleteReSend, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			String info = person.getString("info");

			if (!b) {
				try {
					Object islogin = person.get("stute");
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.deleteReSend(id);
				} catch (Exception ex) {

				}
			}

			map.put("success", b);
			map.put("info", info);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> getAutoReSend_Text() {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.getAutoReSend_Text, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			if (!b) {
				Object islogin = null;
				try {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.getAutoReSend_Location();
				} catch (Exception ex) {

				}
				String info = person.getString("info");
				map.put("success", false);
				map.put("info", info);
				list.add(map);
				return list;
			} else {
				// 解析data
				List<WeiXinAutoReSendMenuDto> weiXinList = new ArrayList<WeiXinAutoReSendMenuDto>();
				JSONArray jsonWXlist = person.getJSONArray("data");
				for (int i = 0; i < jsonWXlist.length(); i++) {
					WeiXinAutoReSendMenuDto dto = new WeiXinAutoReSendMenuDto();
					JSONObject jbt = jsonWXlist.getJSONObject(i);
					dto.setConpanyId(jbt.getLong("conpanyId"));
					dto.setId(jbt.getLong("id"));
					dto.setName(jbt.getString("name"));
					dto.setContent(jbt.getString("content"));
					dto.setType(jbt.getInt("type"));
					dto.setUses(jbt.getBoolean("uses"));
					dto.setWeixin_events(jbt.getString("weixin_events"));
					dto.setWeixin_keys(jbt.getLong("weixin_keys"));
					weiXinList.add(dto);
				}
				map.put("data", weiXinList);
			}
			map.put("success", b);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	/**
	 * 自动发送图片，解析json
	 */
	@Override
	public List<Map<String, Object>> getAutoReSend_Image() {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.getAutoReSend_Image, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			if (!b) {
				Object islogin = null;
				try {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.getAutoReSend_Location();
				} catch (Exception ex) {

				}
				String info = person.getString("info");
				map.put("success", false);
				map.put("info", info);
				list.add(map);
				return list;
			} else {
				// 解析data
				List<WeiXinAutoReSendMenuDto> weiXinList = new ArrayList<WeiXinAutoReSendMenuDto>();
				JSONArray jsonWXlist = person.getJSONArray("data");
				for (int i = 0; i < jsonWXlist.length(); i++) {
					WeiXinAutoReSendMenuDto dto = new WeiXinAutoReSendMenuDto();
					JSONObject jbt = jsonWXlist.getJSONObject(i);
					dto.setConpanyId(jbt.getLong("conpanyId"));
					dto.setId(jbt.getLong("id"));
					dto.setName(jbt.getString("name"));
					dto.setContent(jbt.getString("content"));
					dto.setType(jbt.getInt("type"));
					dto.setUses(jbt.getBoolean("uses"));
					dto.setWeixin_events(jbt.getString("weixin_events"));
					dto.setWeixin_keys(jbt.getLong("weixin_keys"));
					weiXinList.add(dto);
				}
				map.put("data", weiXinList);
			}
			map.put("success", b);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> getAutoReSend_Link() {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.getAutoReSend_Link, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");

			if (!b) {
				Object islogin = null;
				try {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.getAutoReSend_Location();
				} catch (Exception ex) {

				}
				String info = person.getString("info");
				map.put("success", false);
				map.put("info", info);
				list.add(map);
				return list;
			} else {
				// 解析data
				List<WeiXinAutoReSendMenuDto> weiXinList = new ArrayList<WeiXinAutoReSendMenuDto>();
				JSONArray jsonWXlist = person.getJSONArray("data");
				for (int i = 0; i < jsonWXlist.length(); i++) {
					WeiXinAutoReSendMenuDto dto = new WeiXinAutoReSendMenuDto();
					Log.e("link1", "1");
					JSONObject jbt = jsonWXlist.getJSONObject(i);
					Log.e("link1", "2");
					dto.setConpanyId(jbt.getLong("conpanyId"));
					dto.setId(jbt.getLong("id"));
					Log.e("link1", "3");
					dto.setName(jbt.getString("name"));
					dto.setContent(jbt.getString("content"));
					Log.e("link1", "4");
					dto.setType(jbt.getInt("type"));
					dto.setUses(jbt.getBoolean("uses"));
					dto.setWeixin_events(jbt.getString("weixin_events"));
					Log.e("link1", "5");
					dto.setWeixin_keys(jbt.getLong("weixin_keys"));
					weiXinList.add(dto);
				}
				map.put("data", weiXinList);
			}
			map.put("success", b);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> getAutoReSend_Location() {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.getAutoReSend_Location, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			if (!b) {
				Object islogin = null;
				try {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.getAutoReSend_Location();
				} catch (Exception ex) {

				}
				String info = person.getString("info");
				map.put("success", false);
				map.put("info", info);
				list.add(map);
				return list;
			} else {
				// 解析data
				List<WeiXinAutoReSendMenuDto> weiXinList = new ArrayList<WeiXinAutoReSendMenuDto>();
				JSONArray jsonWXlist = person.getJSONArray("data");
				for (int i = 0; i < jsonWXlist.length(); i++) {
					WeiXinAutoReSendMenuDto dto = new WeiXinAutoReSendMenuDto();
					JSONObject jbt = jsonWXlist.getJSONObject(i);
					dto.setConpanyId(jbt.getLong("conpanyId"));
					dto.setId(jbt.getLong("id"));
					dto.setName(jbt.getString("name"));
					dto.setContent(jbt.getString("content"));
					dto.setType(jbt.getInt("type"));
					dto.setUses(jbt.getBoolean("uses"));
					dto.setWeixin_events(jbt.getString("weixin_events"));
					dto.setWeixin_keys(jbt.getLong("weixin_keys"));
					weiXinList.add(dto);
				}
				map.put("data", weiXinList);
			}
			map.put("success", b);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> getAutoReSend_Event() {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.getAutoReSend_Event, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");

			if (!b) {
				Object islogin = null;
				try {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.getAutoReSend_Location();
				} catch (Exception ex) {

				}
				String info = person.getString("info");
				map.put("success", false);
				map.put("info", info);
				list.add(map);
				return list;
			} else {
				// 解析data
				List<WeiXinAutoReSendMenuDto> weiXinList = new ArrayList<WeiXinAutoReSendMenuDto>();
				JSONArray jsonWXlist = person.getJSONArray("data");
				for (int i = 0; i < jsonWXlist.length(); i++) {
					WeiXinAutoReSendMenuDto dto = new WeiXinAutoReSendMenuDto();
					JSONObject jbt = jsonWXlist.getJSONObject(i);
					dto.setConpanyId(jbt.getLong("conpanyId"));
					dto.setId(jbt.getLong("id"));
					dto.setName(jbt.getString("name"));
					dto.setContent(jbt.getString("content"));
					dto.setType(jbt.getInt("type"));
					dto.setUses(jbt.getBoolean("uses"));
					dto.setWeixin_events(jbt.getString("weixin_events"));
					dto.setWeixin_keys(jbt.getLong("weixin_keys"));
					weiXinList.add(dto);
				}
				map.put("data", weiXinList);
			}
			map.put("success", b);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> getAutoReSend_Video() {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.getAutoReSend_Video, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			if (!b) {
				Object islogin = null;
				try {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.getAutoReSend_Location();
				} catch (Exception ex) {

				}
				String info = person.getString("info");
				map.put("success", false);
				map.put("info", info);
				list.add(map);
				return list;
			} else {
				// 解析data
				List<WeiXinAutoReSendMenuDto> weiXinList = new ArrayList<WeiXinAutoReSendMenuDto>();
				JSONArray jsonWXlist = person.getJSONArray("data");
				for (int i = 0; i < jsonWXlist.length(); i++) {
					WeiXinAutoReSendMenuDto dto = new WeiXinAutoReSendMenuDto();
					JSONObject jbt = jsonWXlist.getJSONObject(i);
					dto.setConpanyId(jbt.getLong("conpanyId"));
					dto.setId(jbt.getLong("id"));
					dto.setName(jbt.getString("name"));
					dto.setContent(jbt.getString("content"));
					dto.setType(jbt.getInt("type"));
					dto.setUses(jbt.getBoolean("uses"));
					dto.setWeixin_events(jbt.getString("weixin_events"));
					dto.setWeixin_keys(jbt.getLong("weixin_keys"));
					weiXinList.add(dto);
				}
				map.put("data", weiXinList);
			}
			map.put("success", b);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> getAutoReSend_Voice() {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.getAutoReSend_Voice, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			if (!b) {
				Object islogin = null;
				try {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.getAutoReSend_Voice();
				} catch (Exception ex) {

				}
				String info = person.getString("info");
				map.put("success", false);
				map.put("info", info);
				list.add(map);
				return list;
			} else {
				// 解析data
				List<WeiXinAutoReSendMenuDto> weiXinList = new ArrayList<WeiXinAutoReSendMenuDto>();
				JSONArray jsonWXlist = person.getJSONArray("data");
				for (int i = 0; i < jsonWXlist.length(); i++) {
					WeiXinAutoReSendMenuDto dto = new WeiXinAutoReSendMenuDto();
					JSONObject jbt = jsonWXlist.getJSONObject(i);
					dto.setConpanyId(jbt.getLong("conpanyId"));
					dto.setId(jbt.getLong("id"));
					dto.setName(jbt.getString("name"));
					dto.setContent(jbt.getString("content"));
					dto.setType(jbt.getInt("type"));
					dto.setUses(jbt.getBoolean("uses"));
					dto.setWeixin_events(jbt.getString("weixin_events"));
					dto.setWeixin_keys(jbt.getLong("weixin_keys"));
					weiXinList.add(dto);
				}
				map.put("data", weiXinList);
			}
			map.put("success", b);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> addAutoReSend_Text(String name,
			String content) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("name", name));
		params.add(new BasicNameValuePair("content", content));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.addAutoReSend_Text, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			String info = person.getString("info");

			if (!b) {
				Object islogin = null;
				try {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.addAutoReSend_Text(name, content);
				} catch (Exception ex) {

				}
				map.put("success", false);
				map.put("info", info);
				list.add(map);
				return list;
			} else {

			}
			map.put("success", b);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	/**
	 * 添加发送图片
	 */
	@Override
	public List<Map<String, Object>> addAutoReSend_Image(String name,
			String content) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("name", name));
		params.add(new BasicNameValuePair("content", content));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.addAutoReSend_Image, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			String info = person.getString("info");

			if (!b) {
				Object islogin = null;
				try {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.addAutoReSend_Image(name, content);
				} catch (Exception ex) {

				}
				map.put("success", false);
				map.put("info", info);
				list.add(map);
				return list;
			} else {

			}
			map.put("success", b);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> addAutoReSend_Link(String name,
			String content) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("name", name));
		params.add(new BasicNameValuePair("content", content));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.addAutoReSend_Link, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			String info = person.getString("info");

			if (!b) {
				Object islogin = null;
				try {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.addAutoReSend_Link(name, content);
				} catch (Exception ex) {

				}
				map.put("success", false);
				map.put("info", "获取数据失败");
				list.add(map);
				return list;
			} else {

			}
			map.put("success", b);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> addAutoReSend_Location(String name,
			String content) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("name", name));
		params.add(new BasicNameValuePair("content", content));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.addAutoReSend_Location, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			String info = person.getString("info");

			if (!b) {
				Object islogin = null;
				try {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.addAutoReSend_Location(name, content);
				} catch (Exception ex) {

				}
				map.put("success", false);
				map.put("info", info);
				list.add(map);
				return list;
			} else {

			}
			map.put("success", b);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> addAutoReSend_Event(String name,
			String content, String event) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("name", name));
		params.add(new BasicNameValuePair("content", content));
		params.add(new BasicNameValuePair("event", event));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.addAutoReSend_Event, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			String info = person.getString("info");

			if (!b) {
				Object islogin = null;
				try {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.addAutoReSend_Event(name, content, event);
				} catch (Exception ex) {

				}
				map.put("success", false);
				map.put("info", info);
				list.add(map);
				return list;
			} else {

			}
			map.put("success", b);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> addAutoReSend_Video(String name,
			String content) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("name", name));
		params.add(new BasicNameValuePair("content", content));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.addAutoReSend_Video, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			String info = person.getString("info");

			if (!b) {
				Object islogin = null;
				try {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.addAutoReSend_Video(name, content);
				} catch (Exception ex) {

				}
				map.put("success", false);
				map.put("info", info);
				list.add(map);
				return list;
			} else {

			}
			map.put("success", b);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> addAutoReSend_Voice(String name,
			String content) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("name", name));
		params.add(new BasicNameValuePair("content", content));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.addAutoReSend_Voice, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			String info = person.getString("info");

			if (!b) {
				Object islogin = null;
				try {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.addAutoReSend_Voice(name, content);
				} catch (Exception ex) {

				}
				map.put("success", false);
				map.put("info", info);
				list.add(map);
				return list;
			} else {

			}
			map.put("success", b);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> getAutoReSend(String id) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", id));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.getAutoReSend, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			String info = person.getString("info");
			Object islogin = person.get("stute");
			if (!b) {
				if (islogin != null) { // 状态不为空，登录错误
					map.put("login", false);
				} else {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.getAutoReSend(id);
				}
			} else {
				// 解析data
				List<WeiXinAutoReSendMenuDto> weiXinList = new ArrayList<WeiXinAutoReSendMenuDto>();
				JSONArray jsonWXlist = person.getJSONArray("obj");
				for (int i = 0; i < jsonWXlist.length(); i++) {
					WeiXinAutoReSendMenuDto dto = new WeiXinAutoReSendMenuDto();
					JSONObject jbt = jsonWXlist.getJSONObject(i);
					dto.setConpanyId(jbt.getLong("conpanyId"));
					dto.setId(jbt.getLong("id"));
					dto.setName(jbt.getString("name"));
					dto.setContent(jbt.getString("content"));
					dto.setType(jbt.getInt("type"));
					dto.setUses(jbt.getBoolean("uses"));
					dto.setWeixin_events(jbt.getString("weixin_events"));
					dto.setWeixin_keys(jbt.getLong("weixin_keys"));
					weiXinList.add(dto);
				}
				map.put("obj", weiXinList);
			}

			map.put("success", b);
			map.put("info", info);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> addWeiXinInfoToAutoResend(String id,
			String name, String info_name, String info_id) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", id));
		params.add(new BasicNameValuePair("name", name));
		params.add(new BasicNameValuePair("info_name", info_name));
		params.add(new BasicNameValuePair("info_id", info_id));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.addWeiXinInfoToAutoResend, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			String info = person.getString("info");
			if (!b) {
				Object islogin = null;
				try {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.addWeiXinInfoToAutoResend(id, name, info_name,
							info_id);
				} catch (Exception ex) {

				}
				map.put("success", false);
				map.put("info", info);
				list.add(map);
				return list;
			} else {

			}

			map.put("success", b);
			map.put("info", info);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> getAutoReSendItem(String id) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", id));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.getAutoReSendItem, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");

			if (!b) {
				try {
					Object islogin = person.get("stute");
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.getAutoReSendItem(id);
				} catch (Exception ex) {

				}
				String info = person.getString("info");
				map.put("success", b);
				map.put("info", info);
				list.add(map);
			} else {
				// 解析data
				List<WeiXinAutoReSendItemDTO> weiXinList = new ArrayList<WeiXinAutoReSendItemDTO>();
				JSONArray jsonWXlist = person.getJSONArray("data");
				for (int i = 0; i < jsonWXlist.length(); i++) {
					WeiXinAutoReSendItemDTO dto = new WeiXinAutoReSendItemDTO();
					JSONObject jbt = jsonWXlist.getJSONObject(i);
					dto.setConpanyId(jbt.getLong("conpanyId"));
					dto.setId(jbt.getLong("id"));
					dto.setName(jbt.getString("name"));
					dto.setConpanyId(jbt.getLong("conpanyId"));
					dto.setResendid(jbt.getLong("resendid"));
					dto.setAoturesendId(jbt.getLong("aoturesendId"));
					weiXinList.add(dto);
				}
				map.put("data", weiXinList);
			}

			map.put("success", b);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> deleteWeiXinInfoToAutoResend(String id) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", id));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.deleteWeiXinInfoToAutoResend, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			String info = person.getString("info");
			if (!b) {
				try {
					Object islogin = person.get("stute");
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.getAutoReSendItem(id);
				} catch (Exception ex) {

				}

				map.put("success", b);
				map.put("info", info);
				list.add(map);
			} else {

			}

			map.put("success", b);
			map.put("info", info);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> deleteWeiXinInfo(String id) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", id));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.deleteWeiXinInfo, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			String info = person.getString("info");

			if (!b) {
				try {
					Object islogin = person.get("stute");
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.deleteWeiXinInfo(id);
				} catch (Exception ex) {

				}
			}

			map.put("success", b);
			map.put("info", info);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> updateAutoReSend_Text(String id,
			String name, String content) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", id));
		params.add(new BasicNameValuePair("name", name));
		params.add(new BasicNameValuePair("content", content));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.updateAutoReSend_Text, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			String info = person.getString("info");
			if (!b) {
				Object islogin = null;
				try {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.updateAutoReSend_Voice(id, name, content);
				} catch (Exception ex) {

				}
				map.put("success", false);
				map.put("info", info);
				list.add(map);
				return list;
			} else {

			}

			map.put("success", b);
			map.put("info", info);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> updateAutoReSend_Image(String id,
			String name, String content) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", id));
		params.add(new BasicNameValuePair("name", name));
		params.add(new BasicNameValuePair("content", content));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.updateAutoReSend_Image, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			String info = person.getString("info");
			if (!b) {
				Object islogin = null;
				try {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.updateAutoReSend_Voice(id, name, content);
				} catch (Exception ex) {

				}
				map.put("success", false);
				map.put("info", info);
				list.add(map);
				return list;
			} else {

			}

			map.put("success", b);
			map.put("info", info);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> updateAutoReSend_Link(String id,
			String name, String content) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", id));
		params.add(new BasicNameValuePair("name", name));
		params.add(new BasicNameValuePair("content", content));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.updateAutoReSend_Link, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			String info = person.getString("info");
			if (!b) {
				Object islogin = null;
				try {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.updateAutoReSend_Voice(id, name, content);
				} catch (Exception ex) {

				}
				map.put("success", false);
				map.put("info", info);
				list.add(map);
				return list;
			} else {

			}

			map.put("success", b);
			map.put("info", info);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> updateAutoReSend_Location(String id,
			String name, String content) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", id));
		params.add(new BasicNameValuePair("name", name));
		params.add(new BasicNameValuePair("content", content));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.updateAutoReSend_Location, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			String info = person.getString("info");
			if (!b) {
				Object islogin = null;
				try {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.updateAutoReSend_Voice(id, name, content);
				} catch (Exception ex) {

				}
				map.put("success", false);
				map.put("info", info);
				list.add(map);
				return list;
			} else {

			}

			map.put("success", b);
			map.put("info", info);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> updateAutoReSend_Event(String id,
			String name, String content, String event) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", id));
		params.add(new BasicNameValuePair("name", name));
		params.add(new BasicNameValuePair("content", content));
		params.add(new BasicNameValuePair("event", event));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.updateAutoReSend_Event, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			String info = person.getString("info");
			if (!b) {
				Object islogin = null;
				try {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.updateAutoReSend_Voice(id, name, content);
				} catch (Exception ex) {

				}
				map.put("success", false);
				map.put("info", info);
				list.add(map);
				return list;
			} else {

			}

			map.put("success", b);
			map.put("info", info);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> updateAutoReSend_Video(String id,
			String name, String content) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", id));
		params.add(new BasicNameValuePair("name", name));
		params.add(new BasicNameValuePair("content", content));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.updateAutoReSend_Video, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			String info = person.getString("info");
			if (!b) {
				Object islogin = null;
				try {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.updateAutoReSend_Voice(id, name, content);
				} catch (Exception ex) {

				}
				map.put("success", false);
				map.put("info", info);
				list.add(map);
				return list;
			} else {

			}

			map.put("success", b);
			map.put("info", info);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> updateUse(String id, String value) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", id));
		params.add(new BasicNameValuePair("value", value));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.updateUse, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			String info = person.getString("info");
			if (!b) {
				Object islogin = null;
				try {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.getWenzhang(id);
				} catch (Exception ex) {

				}
				map.put("success", false);
				map.put("info", info);
				list.add(map);
				return list;
			} else {

			}

			map.put("success", b);
			map.put("info", info);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> sendWenzhang(String wenzhangname,
			String wenzhangcontent, String strjson) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("wenzhangname", wenzhangname));
		params.add(new BasicNameValuePair("wenzhangcontent", wenzhangcontent));
		params.add(new BasicNameValuePair("strjson", strjson));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.sendWenzhang, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			String info = person.getString("info");
			Object islogin = person.get("stute");
			if (!b) {
				if (islogin != null) { // 状态不为空，登录错误
					map.put("login", false);
				} else {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.sendWenzhang(wenzhangname, wenzhangcontent,
							strjson);
				}
			} else {

			}

			map.put("success", b);
			map.put("info", info);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> getWenzhang(String id) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", id));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.getWenzhang, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			

			if (!b) {
				Object islogin = null;
				try {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.getWenzhang(id);
				} catch (Exception ex) {

				}
				String info = person.getString("info");
				map.put("success", false);
				map.put("info", info);
				list.add(map);
				return list;
			} else {
					// 解析data
					WebPublicMessageDto dto = new WebPublicMessageDto();
					JSONObject jbt = person.getJSONObject("obj");
					dto.setConpanyId(jbt.getLong("conpanyId"));
					dto.setId(jbt.getLong("id"));
					dto.setName(jbt.getString("name"));
					dto.setContent(jbt.getString("content"));
					dto.setLinkaddress(jbt.getString("linkaddress"));
					dto.setLooknum(jbt.getLong("looknum"));
					dto.setStartdate(jbt.getString("startdate"));
					dto.setVote(jbt.getBoolean("vote"));
					map.put("data1", dto);
					try{
						JSONObject obj2 = person.getJSONObject("obj2");
						VoteDTO vote=new VoteDTO();
						List<VoteItemDTO> listvoteitem=new ArrayList<VoteItemDTO>();
						vote.setConpanyId(obj2.getLong("conpanyId"));
						vote.setEndDate(obj2.getString("endDate"));
						vote.setId(obj2.getLong("id"));
						vote.setName(obj2.getString("name"));
						vote.setOnes(obj2.getBoolean("ones"));
						vote.setPublics(obj2.getBoolean("publics"));
						vote.setStardate(obj2.getString("stardate"));
						vote.setWenzhangId(obj2.getLong("wenzhangId"));
						JSONArray voteIrenJson = obj2.getJSONArray("voteItem");
						for (int i = 0; i < voteIrenJson.length(); i++) {
							VoteItemDTO itemdto = new VoteItemDTO();
							JSONObject obj3 = voteIrenJson.getJSONObject(i);
							itemdto.setConpanyId(obj3.getLong("conpanyId"));
							itemdto.setId(obj3.getLong("id"));
							itemdto.setName(obj3.getString("name"));
							itemdto.setNum(obj3.getLong("num"));
							listvoteitem.add(itemdto);
						}
						vote.setVoteItem(listvoteitem);
						map.put("data2", vote);
					}catch(Exception e){
						map.put("data2", null);
					}
			}
			map.put("success", b);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> updateWenzhang(String id,
			String wenzhangname, String wenzhangcontent, String strjson) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", id));
		params.add(new BasicNameValuePair("wenzhangname", wenzhangname));
		params.add(new BasicNameValuePair("wenzhangcontent", wenzhangcontent));
		params.add(new BasicNameValuePair("strjson", strjson));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.updateWenzhang, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			String info = person.getString("info");
			Object islogin = person.get("stute");
			if (!b) {
				if (islogin != null) { // 状态不为空，登录错误
					map.put("login", false);
				} else {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.updateWenzhang(id, wenzhangname,
							wenzhangcontent, strjson);
				}
			} else {

			}

			map.put("success", b);
			map.put("info", info);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> getWenzhangList(String name,
			String nowpage, String countNum) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("name", name));
		params.add(new BasicNameValuePair("nowpage", nowpage));
		params.add(new BasicNameValuePair("countNum", countNum));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.getWenzhangList, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
		

			if (!b) {
				Object islogin = null;
				try {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.getWenzhangList(name, nowpage, countNum);
				} catch (Exception ex) {

				}
				String info = person.getString("info");
				map.put("success", false);
				map.put("info", info);
				list.add(map);
				return list;
			} else {
				// 解析data
				List<WebPublicMessageDto> weiXinList = new ArrayList<WebPublicMessageDto>();
				JSONArray jsonWXlist = person.getJSONArray("data");
				for (int i = 0; i < jsonWXlist.length(); i++) {
					WebPublicMessageDto dto = new WebPublicMessageDto();
					JSONObject jbt = jsonWXlist.getJSONObject(i);
					dto.setConpanyId(jbt.getLong("conpanyId"));
					dto.setId(jbt.getLong("id"));
					dto.setName(jbt.getString("name"));
					dto.setContent(jbt.getString("content"));
					dto.setLinkaddress(jbt.getString("linkaddress"));
					dto.setLooknum(jbt.getLong("looknum"));
					dto.setStartdate(jbt.getString("startdate"));
					dto.setVote(jbt.getBoolean("vote"));
					weiXinList.add(dto);
				}
				map.put("data", weiXinList);
				map.put("pagenum",person.getString("pagenum") );
			}
			map.put("success", b);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> deleteWenzhang(String id) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", id));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.deleteWenzhang, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			String info = person.getString("info");
			if (!b) {
				Object islogin = null;
				try {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.deleteWenzhang(id);
				} catch (Exception ex) {

				}
				map.put("success", false);
				map.put("info", info);
				list.add(map);
				return list;
			} else {

			}

			map.put("success", b);
			map.put("info", info);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> addDevice(String id,String name, String tokens,
			String open) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("name", name));
		params.add(new BasicNameValuePair("tokens", tokens));
		params.add(new BasicNameValuePair("open", open));
		params.add(new BasicNameValuePair("id", id));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.addDevice, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			String info = person.getString("info");
			if (!b) {
				Object islogin = null;
				try {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.addDevice(id,name, tokens, open);
				} catch (Exception ex) {

				}
				map.put("success", false);
				map.put("info", info);
				list.add(map);
				return list;
			} else {
			}

			map.put("success", b);
			map.put("info", info);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> addWifiRigister(String id) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", id));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.addWifiRigister, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			String info = person.getString("info");
			if (!b) {
				Object islogin = null;
				try {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.addWifiRigister(id);
				} catch (Exception ex) {

				}
				map.put("success", false);
				map.put("info", info);
				list.add(map);
				return list;
			} else {
				
			}

			map.put("success", b);
			map.put("info", info);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> updateDevice(String name, String tokens,
			String open, String id) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("name", name));
		params.add(new BasicNameValuePair("tokens", tokens));
		params.add(new BasicNameValuePair("open", open));
		params.add(new BasicNameValuePair("id", id));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.updateDevice, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			String info = person.getString("info");
			if (!b) {
				Object islogin = null;
				try {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.updateDevice(name, tokens, open, id);
				} catch (Exception ex) {

				}
				map.put("success", false);
				map.put("info", info);
				list.add(map);
				return list;
			} else {
			}

			map.put("success", b);
			map.put("info", info);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> deleteDevice(String id) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", id));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.deleteDevice, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			String info = person.getString("info");
			if (!b) {
				Object islogin = null;
				try {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.deleteDevice(id);
				} catch (Exception ex) {

				}
				map.put("success", false);
				map.put("info", info);
				list.add(map);
				return list;
			} else {
			}

			map.put("success", b);
			map.put("info", info);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> lookLinkDevice(String nowpage, String countNum,
			String id) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", id));
		params.add(new BasicNameValuePair("nowpage", nowpage));
		params.add(new BasicNameValuePair("countNum", countNum));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.lookLinkDevice, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			
			
			if (!b) {
				Object islogin = null;
				try {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.lookLinkDevice(nowpage, countNum, id);
				} catch (Exception ex) {

				}
				String info = person.getString("info");
				map.put("success", false);
				map.put("info", info);
				list.add(map);
				return list;
			} else {
				// 解析data
				List<DeviceDto> weiXinList = new ArrayList<DeviceDto>();
				JSONArray jsonWXlist = person.getJSONArray("data");
				for (int i = 0; i < jsonWXlist.length(); i++) {
					DeviceDto dto = new DeviceDto();
					JSONObject jbt = jsonWXlist.getJSONObject(i);
					dto.setConpanyId(jbt.getLong("conpanyId"));
					dto.setId(jbt.getLong("id"));
					dto.setAp_id(jbt.getLong("ap_id"));
					dto.setCountNum(jbt.getLong("countNum"));
					dto.setEndDate( jbt.getString("endDate"));
					dto.setIncoming(jbt.getInt("incoming"));
					dto.setIp(jbt.getString("ip"));
					dto.setLinkmainName(jbt.getString("linkmainName"));
					dto.setMac(jbt.getString("mac"));
					dto.setNoLogin(jbt.getBoolean("noLogin"));
					dto.setOutgoing(jbt.getInt("outgoing"));
					dto.setToken(jbt.getString("token"));
					dto.setTokens(jbt.getString("tokens"));
					weiXinList.add(dto);
				}
				map.put("data", weiXinList);
			}
			String pagenum = person.getString("pagenum");
			map.put("success", b);
			map.put("pagenum", pagenum);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> getDeviceList() {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.getDeviceList, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			if (!b) {
				Object islogin = null;
				try {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.getDeviceList();
				} catch (Exception ex) {

				}
				String info = person.getString("info");
				map.put("success", false);
				map.put("info", info);
				list.add(map);
				return list;
			} else {
				// 解析data
				List<VWiFiDto> vWifiList = new ArrayList<VWiFiDto>();
				JSONArray jsonWXlist = person.getJSONArray("data");
				for (int i = 0; i < jsonWXlist.length(); i++) {
					VWiFiDto dto = new VWiFiDto();
					JSONObject jbt = jsonWXlist.getJSONObject(i);
					dto.setConpanyId(jbt.getLong("conpanyId"));
					dto.setId(jbt.getLong("id"));
					dto.setTokens(jbt.getString("tokens"));
					dto.setHtmlContent(jbt.getString("htmlContent"));
					dto.setName(jbt.getString("name"));
					dto.setSys_load(jbt.getString("sys_load"));
					dto.setSys_memfree(jbt.getString("sys_memfree"));
					dto.setSys_uptime(jbt.getString("sys_uptime"));
					dto.setUseUp(jbt.getBoolean("useUp"));
					dto.setWebRigister(jbt.getBoolean("webRigister"));
					dto.setWifidog_uptime(jbt.getString("wifidog_uptime"));
					vWifiList.add(dto);
				}
				map.put("data", vWifiList);
			}

			map.put("success", b);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> addMac(String id) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", id));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.addMac, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			if (!b) {
				Object islogin = null;
				try {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.addMac(id);
				} catch (Exception ex) {

				}
				String info = person.getString("info");
				map.put("success", false);
				map.put("info", info);
				list.add(map);
				return list;
			} 
			map.put("info", "成功");
			map.put("success", b);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> getAwards() {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.getAwards, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			Object islogin = null;
			if (!b) {
				if (islogin != null) { // 状态不为空，登录错误
					map.put("login", false);
				} else {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.getAwards();
				}
			} else {
				// 解析data
				List<AwardsDto> weiXinList = new ArrayList<AwardsDto>();
				JSONArray jsonWXlist = person.getJSONArray("data");
				for (int i = 0; i < jsonWXlist.length(); i++) {
					AwardsDto dto = new AwardsDto();
					JSONObject jbt = jsonWXlist.getJSONObject(i);
					dto.setId(jbt.getLong("id"));
					dto.setConpanyId(jbt.getLong("conpanyId"));
					dto.setContent(jbt.getString("content"));
					dto.setMarks(jbt.getString("marks"));
					weiXinList.add(dto);
				}
				map.put("data", weiXinList);
			}

			map.put("success", b);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> addAwards(String content, String marks) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("content", content));
		params.add(new BasicNameValuePair("marks", marks));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.addAwards, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			String info = person.getString("info");
			Object islogin = null;
			if (!b) {
				if (islogin != null) { // 状态不为空，登录错误
					map.put("login", false);
				} else {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.addAwards(content, marks);
				}
			} else {

			}

			map.put("success", b);
			map.put("info", info);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> deleteAwards(String id) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", id));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.deleteAwards, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			String info = person.getString("info");
			Object islogin = person.get("stute");
			if (!b) {
				if (islogin != null) { // 状态不为空，登录错误
					map.put("login", false);
				} else {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.deleteAwards(id);
				}
			} else {

			}

			map.put("success", b);
			map.put("info", info);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> addDazhuanpan1(String text,
			String startDate, String endDate, String id, String num) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("text", text));
		params.add(new BasicNameValuePair("startDate", startDate));
		params.add(new BasicNameValuePair("endDate", endDate));
		params.add(new BasicNameValuePair("id", id));
		params.add(new BasicNameValuePair("num", num));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.addDazhuanpan1, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			String info = person.getString("info");
			Object islogin = person.get("stute");
			if (!b) {
				if (islogin != null) { // 状态不为空，登录错误
					map.put("login", false);
				} else {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.addDazhuanpan1(text, startDate, endDate, id,
							num);
				}
			} else {

			}

			map.put("success", b);
			map.put("info", info);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> addDazhuanpan2(String text,
			String startDate, String endDate, String id, String num) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("text", text));
		params.add(new BasicNameValuePair("startDate", startDate));
		params.add(new BasicNameValuePair("endDate", endDate));
		params.add(new BasicNameValuePair("id", id));
		params.add(new BasicNameValuePair("num", num));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.addDazhuanpan2, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			String info = person.getString("info");
			Object islogin = person.get("stute");
			if (!b) {
				if (islogin != null) { // 状态不为空，登录错误
					map.put("login", false);
				} else {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.addDazhuanpan2(text, startDate, endDate, id,
							num);
				}
			} else {

			}

			map.put("success", b);
			map.put("info", info);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> addDazhuanpan3(String text,
			String startDate, String endDate, String id, String num) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("text", text));
		params.add(new BasicNameValuePair("startDate", startDate));
		params.add(new BasicNameValuePair("endDate", endDate));
		params.add(new BasicNameValuePair("id", id));
		params.add(new BasicNameValuePair("num", num));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.addDazhuanpan3, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			String info = person.getString("info");
			Object islogin = person.get("stute");
			if (!b) {
				if (islogin != null) { // 状态不为空，登录错误
					map.put("login", false);
				} else {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.addDazhuanpan3(text, startDate, endDate, id,
							num);
				}
			} else {

			}

			map.put("success", b);
			map.put("info", info);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> getDaZhuanpanInfo() {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.getDaZhuanpanInfo, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			Object islogin = null;
			if (!b) {
				if (islogin != null) { // 状态不为空，登录错误
					map.put("login", false);
				} else {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.getDaZhuanpanInfo();
				}
			} else {
				// 解析data
				List<GamesAwardsListDto> weiXinList = new ArrayList<GamesAwardsListDto>();
				JSONArray jsonWXlist = person.getJSONArray("data");
				for (int i = 0; i < jsonWXlist.length(); i++) {
					GamesAwardsListDto dto = new GamesAwardsListDto();
					JSONObject jbt = jsonWXlist.getJSONObject(i);
					dto.setConpanyId(jbt.getLong("conpanyId"));
					dto.setId(jbt.getLong("id"));
					dto.setContent(jbt.getString("content"));
					dto.setNum(jbt.getInt("num"));
					dto.setAwardsid(jbt.getLong("awardsid"));
					dto.setEndDate(jbt.getString("endDate"));
					dto.setGamesid(jbt.getLong("gamesid"));
					dto.setStartDate(jbt.getString("startDate"));
					dto.setValue(jbt.getLong("value"));
					weiXinList.add(dto);
				}
				map.put("data", weiXinList);
			}
			map.put("use", person.getBoolean("use"));
			map.put("jilv", person.getInt("jilv"));
			map.put("num", person.getInt("num"));
			map.put("score", person.getBoolean("score"));
			map.put("scoreNum", person.getInt("scoreNum"));
			map.put("success", b);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> updateGameDaZhuanPan(String use,
			String type) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("value", use));
		params.add(new BasicNameValuePair("type", type));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.updateGameDaZhuanPan, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			String info = person.getString("info");
			Object islogin = null;
			if (!b) {
				if (islogin != null) { // 状态不为空，登录错误
					map.put("login", false);
				} else {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.updateGameDaZhuanPan(use, type);
				}
			} else {

			}
			map.put("success", b);
			map.put("info", info);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> setDazhuanpanJilv(String value, String type) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("value", value));
		params.add(new BasicNameValuePair("type", type));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.setDazhuanpanJilv, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			String info = person.getString("info");
			Object islogin = null;
			if (!b) {
				if (islogin != null) { // 状态不为空，登录错误
					map.put("login", false);
				} else {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.setDazhuanpanJilv(value, type);
				}
			} else {

			}
			map.put("success", b);
			map.put("info", info);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> setDazhuanpanNum(String value) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("value", value));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.setDazhuanpanNum, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			String info = person.getString("info");
			Object islogin = null;
			if (!b) {
				if (islogin != null) { // 状态不为空，登录错误
					map.put("login", false);
				} else {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.setDazhuanpanNum(value);
				}
			} else {

			}
			map.put("success", b);
			map.put("info", info);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> setguaguakaJilv(String value, String type) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("value", value));
		params.add(new BasicNameValuePair("type", type));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.setguaguakaJilv, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			String info = person.getString("info");
			Object islogin = person.get("stute");
			if (!b) {
				if (islogin != null) { // 状态不为空，登录错误
					map.put("login", false);
				} else {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.setguaguakaJilv(value, type);
				}
			} else {

			}
			map.put("success", b);
			map.put("info", info);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> setguaguakaNum(String value) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("value", value));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.setguaguakaNum, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			String info = person.getString("info");
			Object islogin = null;
			if (!b) {
				if (islogin != null) { // 状态不为空，登录错误
					map.put("login", false);
				} else {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.setguaguakaNum(value);
				}
			} else {

			}
			map.put("success", b);
			map.put("info", info);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> updateGameguaguaka(String value,
			String type) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("value", value));
		params.add(new BasicNameValuePair("type", type));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.updateGameguaguaka, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			String info = person.getString("info");
			Object islogin = null;
			if (!b) {
				if (islogin != null) { // 状态不为空，登录错误
					map.put("login", false);
				} else {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.updateGameguaguaka(value, type);
				}
			} else {

			}
			map.put("success", b);
			map.put("info", info);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> getguaguaka() {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.getguaguaka, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			Object islogin = null;
			if (!b) {
				if (islogin != null) { // 状态不为空，登录错误
					map.put("login", false);
				} else {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.getguaguaka();
				}
			} else {
				// 解析data
				List<GamesAwardsListDto> weiXinList = new ArrayList<GamesAwardsListDto>();
				JSONArray jsonWXlist = person.getJSONArray("data");
				for (int i = 0; i < jsonWXlist.length(); i++) {
					GamesAwardsListDto dto = new GamesAwardsListDto();
					JSONObject jbt = jsonWXlist.getJSONObject(i);
					dto.setConpanyId(jbt.getLong("conpanyId"));
					dto.setId(jbt.getLong("id"));
					dto.setContent(jbt.getString("content"));
					dto.setNum(jbt.getInt("num"));
					dto.setAwardsid(jbt.getLong("awardsid"));
					dto.setEndDate(jbt.getString("endDate"));
					dto.setGamesid(jbt.getLong("gamesid"));
					dto.setStartDate(jbt.getString("startDate"));
					dto.setValue(jbt.getLong("value"));
					weiXinList.add(dto);
				}
				map.put("data", weiXinList);
			}
			map.put("use", person.getBoolean("use"));
			map.put("jilv", person.getInt("jilv"));
			map.put("num", person.getInt("num"));
			map.put("score", person.getBoolean("score"));
			map.put("scoreNum", person.getInt("scoreNum"));
			map.put("success", b);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> addAwardsToguaguaka(String value,
			String text, String id, String enddate, String startdate, String num) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("value", value));
		params.add(new BasicNameValuePair("text", text));
		params.add(new BasicNameValuePair("id", id));
		params.add(new BasicNameValuePair("enddate", enddate));
		params.add(new BasicNameValuePair("startdate", startdate));
		params.add(new BasicNameValuePair("num", num));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.addAwardsToguaguaka, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			String info = person.getString("info");
			Object islogin = null;
			if (!b) {
				if (islogin != null) { // 状态不为空，登录错误
					map.put("login", false);
				} else {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.addAwardsToguaguaka(value, text, id, enddate,
							startdate, num);
				}
			} else {

			}
			map.put("success", b);
			map.put("info", info);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> deleteGuaguaka(String id) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", id));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.deleteGuaguaka, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			String info = person.getString("info");
			Object islogin = null;
			if (!b) {
				if (islogin != null) { // 状态不为空，登录错误
					map.put("login", false);
				} else {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.deleteGuaguaka(id);
				}
			} else {

			}
			map.put("success", b);
			map.put("info", info);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> getVip(String nowpage, String countNum,
			String name) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("nowpage", nowpage));
		params.add(new BasicNameValuePair("countNum", countNum));
		params.add(new BasicNameValuePair("name", name));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.getVip, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			if (!b) {
				Object islogin = null;
				try {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.getVip(nowpage, countNum, name);
				} catch (Exception ex) {

				}
				map.put("success", false);
				map.put("info", "获取数据失败");
				list.add(map);
				return list;
			} else {
				// 解析data
				int pagenum = person.getInt("pagenum");
				List<LinkManListDto> weiXinList = new ArrayList<LinkManListDto>();
				JSONArray jsonWXlist = person.getJSONArray("data");
				for (int i = 0; i < jsonWXlist.length(); i++) {
					LinkManListDto dto = new LinkManListDto();
					JSONObject jbt = jsonWXlist.getJSONObject(i);
					dto.setConpanyId(jbt.getLong("conpanyId"));
					dto.setId(jbt.getLong("id"));
					dto.setAddUserId(jbt.getLong("addUserId"));
					dto.setAddUserName(jbt.getString("addUserName"));
					dto.setChanceListId(jbt.getLong("chanceListId"));
					dto.setChanceListName(jbt.getString("chanceListName"));
					dto.setLinkManBirthday(jbt.getString("linkManBirthday"));
					dto.setLinkManJob(jbt.getString("linkManJob"));
					dto.setLinkManMark(jbt.getString("linkManMark"));
					dto.setLinkManMaxScore(jbt.getDouble("linkManScore"));
					dto.setLinkManName(jbt.getString("linkManName"));
					dto.setLinkManPhone(jbt.getString("linkManPhone"));
					dto.setLinkManScore(jbt.getDouble("linkManScore"));
					dto.setLinkManSex(jbt.getString("linkManSex"));
					dto.setMoney(jbt.getDouble("money"));
					dto.setOpenid(jbt.getString("openid"));
					dto.setUserTableId(jbt.getLong("userTableId"));
					dto.setVipId(jbt.getString("vipId"));
					dto.setVipidNum(jbt.getLong("vipidNum"));
					dto.setVipLevel(jbt.getString("vipLevel"));
					dto.setVipMarks(jbt.getString("vipMarks"));
					weiXinList.add(dto);
				}
				map.put("data", weiXinList);
				map.put("pagenum", pagenum);
				map.put("success", b);
			}
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> getVipList() {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.getVipList, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");

			if (!b) {
				Object islogin = null;
				try {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.getVipList();
				} catch (Exception ex) {

				}
				map.put("success", false);
				map.put("info", "获取数据失败");
				list.add(map);
				return list;
			} else {
				// 解析data
				List<VIPSetDto> weiXinList = new ArrayList<VIPSetDto>();
				JSONArray jsonWXlist = person.getJSONArray("data");
				for (int i = 0; i < jsonWXlist.length(); i++) {
					VIPSetDto dto = new VIPSetDto();
					JSONObject jbt = jsonWXlist.getJSONObject(i);
					dto.setConpanyId(jbt.getLong("conpanyId"));
					dto.setId(jbt.getLong("id"));
					dto.setMarks(jbt.getString("marks"));
					dto.setName(jbt.getString("name"));
					dto.setScore(jbt.getLong("score"));
					weiXinList.add(dto);
				}
				map.put("data", weiXinList);
			}
			map.put("success", b);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> addVipList(String marks, String sroce,
			String name) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("marks", marks));
		params.add(new BasicNameValuePair("sroce", sroce));
		params.add(new BasicNameValuePair("name", name));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.addVipList, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			String info = person.getString("info");

			if (!b) {
				Object islogin = null;
				try {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.addVipList(marks, sroce, name);
				} catch (Exception ex) {

				}
				map.put("success", false);
				map.put("info", "获取数据失败");
				list.add(map);
				return list;
			} else {

			}
			map.put("success", b);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	/**
	 * 给用户添加积分
	 */
	@Override
	public List<Map<String, Object>> addscoreToUser(String id, String sroce) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", id));
		params.add(new BasicNameValuePair("sroce", sroce));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.addscoreToUser, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			String info = person.getString("info");

			if (!b) {
				Object islogin = null;
				try {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.addscoreToUser(id, sroce);
				} catch (Exception ex) {

				}
				map.put("success", false);
				map.put("info", "获取数据失败");
				list.add(map);
				return list;
			} else {

			}
			map.put("success", b);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> addpriceToUser(String id, String money) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", id));
		params.add(new BasicNameValuePair("money", money));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.addpriceToUser, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			String info = person.getString("info");

			if (!b) {
				Object islogin = null;
				try {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.addpriceToUser(id, money);
				} catch (Exception ex) {

				}
				map.put("success", false);
				map.put("info", "获取数据失败");
				list.add(map);
				return list;
			} else {

			}
			map.put("success", b);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	/**
	 * 给用户减少积分
	 */
	@Override
	public List<Map<String, Object>> jianscoreToUser(String id, String sroce) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", id));
		params.add(new BasicNameValuePair("sroce", sroce));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.jianscoreToUser, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			String info = person.getString("info");

			if (!b) {
				Object islogin = null;
				try {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.jianscoreToUser(id, sroce);
				} catch (Exception ex) {

				}
				map.put("success", false);
				map.put("info", "获取数据失败");
				list.add(map);
				return list;
			} else {

			}
			map.put("success", b);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> jianpriceToUser(String id, String money) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", id));
		params.add(new BasicNameValuePair("money", money));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.jianpriceToUser, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			String info = person.getString("info");

			if (!b) {
				Object islogin = null;
				try {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.jianpriceToUser(id, money);
				} catch (Exception ex) {

				}
				map.put("success", false);
				map.put("info", "获取数据失败");
				list.add(map);
				return list;
			} else {

			}
			map.put("success", b);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> getHuiyuanxinxi(String id) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", id));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.getHuiyuanxinxi, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			String info = person.getString("info");

			if (!b) {
				Object islogin = null;
				try {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.getHuiyuanxinxi(id);
				} catch (Exception ex) {

				}
				map.put("success", false);
				map.put("info", "获取数据失败");
				list.add(map);
				return list;
			} else {
				// 解析data
//				List<VIPSetDto> weiXinList = new ArrayList<VIPSetDto>();
//				JSONArray jsonWXlist = person.getJSONArray("data");
//				for (int i = 0; i < jsonWXlist.length(); i++) {
					VIPSetDto dto = new VIPSetDto();
					JSONObject jbt = person.getJSONObject("data");
					dto.setConpanyId(jbt.getLong("conpanyId"));
					dto.setId(jbt.getLong("id"));
					dto.setName(jbt.getString("name"));
					dto.setScore(jbt.getLong("score"));
					dto.setMarks(jbt.getString("marks"));
//					weiXinList.add(dto);
//				}
				map.put("data", dto);
			}
			map.put("success", b);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> getVipInfo(String id) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", id));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.getVipInfo, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");

			if (!b) {
				Object islogin = null;
				try {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.getVipInfo(id);
				} catch (Exception ex) {

				}
				map.put("success", false);
				map.put("info", "获取数据失败");
				list.add(map);
				return list;
			} else {
				// 解析data
				List<VIPSetDto> weiXinList = new ArrayList<VIPSetDto>();
				JSONArray jsonWXlist = person.getJSONArray("data");
				for (int i = 0; i < jsonWXlist.length(); i++) {
					VIPSetDto dto = new VIPSetDto();
					JSONObject jbt = jsonWXlist.getJSONObject(i);
					dto.setConpanyId(jbt.getLong("conpanyId"));
					dto.setId(jbt.getLong("id"));
					dto.setName(jbt.getString("name"));
					dto.setScore(jbt.getLong("score"));
					dto.setMarks(jbt.getString("marks"));
					weiXinList.add(dto);
				}
				map.put("data", weiXinList);
			}
			map.put("success", b);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> updateVipList(String id, String name,
			String sroce, String marks) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", id));
		params.add(new BasicNameValuePair("name", name));
		params.add(new BasicNameValuePair("sroce", sroce));
		params.add(new BasicNameValuePair("marks", marks));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.updateVipList, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");

			if (!b) {
				Object islogin = null;
				try {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.updateVipList(id, name, sroce, marks);
				} catch (Exception ex) {

				}
				map.put("success", false);
				map.put("info", "获取数据失败");
				list.add(map);
				return list;
			} else {
				// 解析data
				VIPSetDto dto = new VIPSetDto();
				JSONObject jbt = person.getJSONObject("data");
				dto.setConpanyId(jbt.getLong("conpanyId"));
				dto.setId(jbt.getLong("id"));
				dto.setMarks(jbt.getString("marks"));
				dto.setName(jbt.getString("name"));
				dto.setScore(jbt.getLong("score"));
				map.put("data", dto);
			}
			map.put("success", b);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> getScoreDuiHuanList() {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.getScoreDuiHuanList, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");

			if (!b) {
				Object islogin = null;
				try {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.getScoreDuiHuanList();
				} catch (Exception ex) {

				}
				map.put("success", false);
				map.put("info", "获取数据失败");
				list.add(map);
				return list;
			} else {
				// 解析data
				List<ScoreDuihuanDto> weiXinList = new ArrayList<ScoreDuihuanDto>();
				JSONArray jsonWXlist = person.getJSONArray("data");
				for (int i = 0; i < jsonWXlist.length(); i++) {
					ScoreDuihuanDto dto = new ScoreDuihuanDto();
					JSONObject jbt = jsonWXlist.getJSONObject(i);
					dto.setConpanyId(jbt.getLong("conpanyId"));
					dto.setId(jbt.getLong("id"));
					dto.setName(jbt.getString("name"));
					dto.setScore(jbt.getLong("score"));
					dto.setContent(jbt.getString("content"));
					dto.setImage(jbt.getString("image"));
					dto.setNum(jbt.getInt("num"));
					weiXinList.add(dto);
				}
				map.put("data", weiXinList);
			}
			map.put("success", b);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> addScoreDuiHuanList(String marks,
			String sroce, String name, String image, String num) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("marks", marks));
		params.add(new BasicNameValuePair("sroce", sroce));
		params.add(new BasicNameValuePair("name", name));
		params.add(new BasicNameValuePair("image", image));
		params.add(new BasicNameValuePair("num", num));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.addScoreDuiHuanList, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			String info = person.getString("info");

			if (!b) {
				Object islogin = null;
				try {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.addScoreDuiHuanList(marks, sroce, name, image,
							num);
				} catch (Exception ex) {

				}
				map.put("success", false);
				map.put("info", "获取数据失败");
				list.add(map);
				return list;
			} else {

			}
			map.put("success", b);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> getScoreDuiHuan(String id) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", id));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.getScoreDuiHuan, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			String info = person.getString("info");

			if (!b) {
				Object islogin = null;
				try {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.getScoreDuiHuan(id);
				} catch (Exception ex) {

				}
				map.put("success", false);
				map.put("info", "获取数据失败");
				list.add(map);
				return list;
			} else {
				// 解析data
				List<ScoreDuihuanDto> weiXinList = new ArrayList<ScoreDuihuanDto>();
				JSONArray jsonWXlist = person.getJSONArray("data");
				for (int i = 0; i < jsonWXlist.length(); i++) {
					ScoreDuihuanDto dto = new ScoreDuihuanDto();
					JSONObject jbt = jsonWXlist.getJSONObject(i);
					dto.setConpanyId(jbt.getLong("conpanyId"));
					dto.setId(jbt.getLong("id"));
					dto.setName(jbt.getString("name"));
					dto.setScore(jbt.getLong("score"));
					dto.setContent(jbt.getString("content"));
					dto.setImage(jbt.getString("image"));
					dto.setNum(jbt.getInt("num"));
					weiXinList.add(dto);
				}
				map.put("data", weiXinList);
			}
			map.put("success", b);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> updateScoreDuiHuan(String id, String name,
			String sroce, String marks, String image, String num) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", id));
		params.add(new BasicNameValuePair("name", name));
		params.add(new BasicNameValuePair("sroce", sroce));
		params.add(new BasicNameValuePair("marks", marks));
		params.add(new BasicNameValuePair("image", image));
		params.add(new BasicNameValuePair("num", num));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.updateScoreDuiHuan, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");

			if (!b) {
				Object islogin = null;
				try {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.updateScoreDuiHuan(id, name, sroce, marks,
							image, num);
				} catch (Exception ex) {

				}
				map.put("success", false);
				map.put("info", "获取数据失败");
				list.add(map);
				return list;
			} else {
				// 解析data
				// List<ScoreDuihuanDto> weiXinList=new
				// ArrayList<ScoreDuihuanDto>();
				// JSONArray jsonWXlist=person.getJSONArray("data");
				// for (int i = 0; i < jsonWXlist.length(); i++) {
				ScoreDuihuanDto dto = new ScoreDuihuanDto();
				// JSONObject jbt=jsonWXlist.getJSONObject(i);
				JSONObject jbt = person.getJSONObject("data");
				dto.setConpanyId(jbt.getLong("conpanyId"));
				dto.setId(jbt.getLong("id"));
				dto.setName(jbt.getString("name"));
				dto.setScore(jbt.getLong("score"));
				dto.setContent(jbt.getString("content"));
				dto.setImage(jbt.getString("image"));
				dto.setNum(jbt.getInt("num"));
				// weiXinList.add(dto);
				// }
				map.put("data", dto);
			}
			map.put("success", b);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> deleteScoreDuiHuan(String id) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", id));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.deleteScoreDuiHuan, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			String info = person.getString("info");

			if (!b) {
				Object islogin = null;
				try {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.deleteScoreDuiHuan(id);
				} catch (Exception ex) {

				}
				map.put("success", false);
				map.put("info", "获取数据失败");
				list.add(map);
				return list;
			} else {

			}
			map.put("success", b);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> getVoteUserList(String id, String nowpage,
			String countNum) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", id));
		params.add(new BasicNameValuePair("nowpage", nowpage));
		params.add(new BasicNameValuePair("countNum", countNum));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.getVoteUserList, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			String info = person.getString("info");
			String pagenum = person.getString("pagenum");

			if (!b) {
				Object islogin = null;
				try {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.getVoteUserList(id, nowpage, countNum);
				} catch (Exception ex) {

				}
				map.put("success", false);
				map.put("info", "获取数据失败");
				list.add(map);
				return list;
			} else {
				// 解析data
				List<VoteUserDto> weiXinList = new ArrayList<VoteUserDto>();
				JSONArray jsonWXlist = person.getJSONArray("data");
				for (int i = 0; i < jsonWXlist.length(); i++) {
					VoteUserDto dto = new VoteUserDto();
					JSONObject jbt = jsonWXlist.getJSONObject(i);
					dto.setId(jbt.getLong("id"));
					dto.setName(jbt.getString("name"));
					dto.setLinkmenId(jbt.getLong("linkmenId"));
					dto.setPhone(jbt.getString("phone"));
					dto.setVoteId(jbt.getLong("voteId"));
					dto.setVoteItemId(jbt.getLong("voteItemId"));
					dto.setVoteItemName(jbt.getString("voteItemName"));
					weiXinList.add(dto);
				}
				map.put("data", weiXinList);
			}
			map.put("pagenum", pagenum);
			map.put("success", b);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> codeConvert() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> jifenTogoodManager(String num) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("num", num));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.jifenTogoodManager, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");


			if (!b) {
				Object islogin = null;
				try {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.jifenTogoodManager(num);
				} catch (Exception ex) {

				}
				String info = person.getString("info");
				map.put("success", false);
				map.put("info", info);
				list.add(map);
				return list;
			} else {
				// 解析data
					ScoreToGoodsListDto dto = new ScoreToGoodsListDto();
					JSONObject jbt = person.getJSONObject("score");
					dto.setId(jbt.getLong("id"));
					dto.setName(jbt.getString("name"));
					dto.setScoreDuiHuanId(jbt.getLong("scoreDuiHuanId"));
					dto.setLinkmainId(jbt.getLong("linkmainId"));
					dto.setNum(jbt.getInt("num"));
					dto.setUserid(jbt.getLong("userid"));
					dto.setUses(jbt.getBoolean("uses"));
					dto.setXuliehao(jbt.getString("xuliehao"));
				map.put("score", dto);
				try{
						ScoreDuihuanDto dto2 = new ScoreDuihuanDto();
						JSONObject jbt2 = person.getJSONObject("ScoreDuihuan");
						dto2.setId(jbt2.getLong("id"));
						dto2.setConpanyId(jbt2.getLong("conpanyId"));
						dto2.setContent(jbt2.getString("content"));
						dto2.setImage(jbt2.getString("image"));
						dto2.setName(jbt2.getString("name"));
						dto2.setNum(jbt2.getInt("num"));
						dto2.setScore(jbt2.getLong("score"));
					map.put("ScoreDuihuan", dto2);
				}catch(Exception ex){
					map.put("ScoreDuihuan", null);
				}
			}
			map.put("success", b);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> huodongTogoodManager(String num) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("num", num));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.huodongTogoodManager, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");


			if (!b) {
				Object islogin = null;
				try {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.huodongTogoodManager(num);
				} catch (Exception ex) {

				}
				map.put("success", false);
				String info = person.getString("info");
				map.put("info", info);
				list.add(map);
				return list;
			} else {
				// 解析data
				try{
					AwardsDto dto = new AwardsDto();
					JSONObject jbt = person.getJSONObject("Awards");
					dto.setId(jbt.getLong("id"));
					dto.setConpanyId(jbt.getLong("conpanyId"));
					dto.setContent(jbt.getString("content"));
					dto.setMarks(jbt.getString("marks"));
				
				map.put("Awards", dto);
				}catch(Exception ex){
					map.put("Awards", null);
				}
					NumLibsDto dto2 = new NumLibsDto();
					JSONObject jbt2 = person.getJSONObject("NumLibs");
					dto2.setId(jbt2.getLong("id"));
					dto2.setConpanyId(jbt2.getLong("conpanyId"));
					dto2.setContent(jbt2.getString("content"));
					dto2.setAwardsId(jbt2.getLong("awardsId"));
					dto2.setEndDate(jbt2.getString("endDate"));
					dto2.setLinkmanId(jbt2.getLong("linkmanId"));
					dto2.setStartDate(jbt2.getString("startDate"));
					dto2.setUserid(jbt2.getLong("userid"));
					dto2.setUses(jbt2.getBoolean("uses"));
					dto2.setXuliehao(jbt2.getString("xuliehao"));
					map.put("NumLibs", dto2);
			}
			map.put("success", b);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> web() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> saveWeixinWeb() {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.saveWeixinWeb, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			String info = person.getString("info");

			if (!b) {
				Object islogin = null;
				try {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.saveWeixinWeb();
				} catch (Exception ex) {

				}
				map.put("success", false);
				map.put("info", "获取数据失败");
				list.add(map);
				return list;
			} else {

			}
			map.put("success", b);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> getWeixinWeb(String nowpage,
			String countNum, String name) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("nowpage", nowpage));
		params.add(new BasicNameValuePair("countNum", countNum));
		params.add(new BasicNameValuePair("name", name));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.getWeixinWeb, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			String info = person.getString("info");
			String pagenum = person.getString("pagenum");

			if (!b) {
				Object islogin = null;
				try {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.getWeixinWeb(nowpage, countNum, name);
				} catch (Exception ex) {

				}
				map.put("success", false);
				map.put("info", "获取数据失败");
				list.add(map);
				return list;
			} else {
				// 解析data
				List<WeiXinWebHtmlDto> webList = new ArrayList<WeiXinWebHtmlDto>();
				JSONArray jsonWXlist = person.getJSONArray("data");
				for (int i = 0; i < jsonWXlist.length(); i++) {
					WeiXinWebHtmlDto dto = new WeiXinWebHtmlDto();
					JSONObject jbt = jsonWXlist.getJSONObject(i);
					dto.setId(jbt.getLong("id"));
					dto.setConpanyId(jbt.getLong("conpanyId"));
					dto.setConpanyName(jbt.getString("conpanyName"));
					dto.setCreateUserId(jbt.getLong("createUserId"));
					dto.setCreateUserName(jbt.getString("createUserName"));
					dto.setHtmls(jbt.getString("htmls"));
					dto.setName(jbt.getString("name"));
					dto.setPrivates(jbt.getBoolean("privates"));
					webList.add(dto);
				}
				map.put("data", webList);
			}
			map.put("pagenum", pagenum);
			map.put("success", b);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> useWeixinWeb(String id) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", id));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.useWeixinWeb, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			String info = person.getString("info");

			if (!b) {
				Object islogin = null;
				try {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.useWeixinWeb(id);
				} catch (Exception ex) {

				}
				map.put("success", false);
				map.put("info", "获取数据失败");
				list.add(map);
				return list;
			} else {

			}
			map.put("success", b);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> deleteWeixinWeb(String id) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", id));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.deleteWeixinWeb, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			String info = person.getString("info");

			if (!b) {
				Object islogin = null;
				try {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.deleteWeixinWeb(id);
				} catch (Exception ex) {

				}
				map.put("success", false);
				map.put("info", "获取数据失败");
				list.add(map);
				return list;
			} else {

			}
			map.put("success", b);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> saveMap(String x, String y) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("x", x));
		params.add(new BasicNameValuePair("y", y));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.saveMap, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			String info = person.getString("info");

			if (!b) {
				Object islogin = null;
				try {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.saveMap(x, y);
				} catch (Exception ex) {

				}
				map.put("success", false);
				map.put("info",info);
				list.add(map);
				return list;
			} else {
				map.put("info",info);
			}
			map.put("success", b);
			
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> getMaps() {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.getMaps, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			

			if (!b) {
				Object islogin = null;
				try {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.getMaps();
				} catch (Exception ex) {

				}
				String info = person.getString("info");
				map.put("success", false);
				map.put("info", info);
				list.add(map);
				return list;
			} else {
				// 解析data
				List<ConpanyAddressDto> weiXinList = new ArrayList<ConpanyAddressDto>();
				JSONArray jsonWXlist = person.getJSONArray("data");
				for (int i = 0; i < jsonWXlist.length(); i++) {
					ConpanyAddressDto dto = new ConpanyAddressDto();
					JSONObject jbt = jsonWXlist.getJSONObject(i);
					dto.setConpanyId(jbt.getLong("conpanyId"));
					dto.setId(jbt.getLong("id"));
					dto.setConpanyName(jbt.getString("conpanyName"));
					dto.setMap_x(jbt.getString("map_x"));
					dto.setMap_y(jbt.getString("map_y"));
					dto.setPhone(jbt.getString("phone"));
					weiXinList.add(dto);
				}
				map.put("data", weiXinList);
			}
			map.put("success", b);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> deleteMap(String id) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", id));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.deleteMap, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			String info = person.getString("info");

			if (!b) {
				Object islogin = null;
				try {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.deleteMap(id);
				} catch (Exception ex) {

				}
				map.put("success", false);
				map.put("info", info);
				list.add(map);
				return list;
			} else {

			}
			map.put("success", b);
			map.put("info", info);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> add(String goodsName, String goodsType,
			String Price, String inPrice, String score, String Spell,
			String goodsModel,String codeid,String image1,String image2,String image3,String image4) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("goodname", goodsName));
		params.add(new BasicNameValuePair("goodsType", goodsType));
		params.add(new BasicNameValuePair("price", Price));
		params.add(new BasicNameValuePair("inPrice", inPrice));
		params.add(new BasicNameValuePair("score", score));
		params.add(new BasicNameValuePair("spell", Spell));
		params.add(new BasicNameValuePair("goodsModel", goodsModel));
		params.add(new BasicNameValuePair("codeid", codeid));
		params.add(new BasicNameValuePair("image1", image1));
		params.add(new BasicNameValuePair("image2", image2));
		params.add(new BasicNameValuePair("image3", image3));
		params.add(new BasicNameValuePair("image4", image4));
		params.add(new BasicNameValuePair("marks", "    ."));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.add, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			

			if (!b) {
				Object islogin = null;
				try {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.add(goodsName, goodsType, Price, inPrice, score, Spell, goodsModel, codeid, image1, image2, image3, image4);
				} catch (Exception ex) {

				}
				String info = person.getString("info");
				map.put("success", false);
				map.put("info", info);
				list.add(map);
				return list;
			} else {

			}
			String info = person.getString("info");
			map.put("success", b);
			map.put("info", info);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> delete(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> update(String num) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> queryGood(String name, String b,
			String nowpage, String countNum) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("name", name));
		params.add(new BasicNameValuePair("b", b));
		params.add(new BasicNameValuePair("nowpage", nowpage));
		params.add(new BasicNameValuePair("countNum", countNum));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.queryGood, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b1 = person.getBoolean("success");
			
			
			if (!b1) {
				Object islogin = null;
				try {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.queryGood(name, b, nowpage, countNum);
				} catch (Exception ex) {

				}
				String info = person.getString("info");
				map.put("success", false);
				map.put("info", info);
				list.add(map);
				return list;
			} else {
				
				// 解析data
				List<WeiXinGoodsDto> weiXinList = new ArrayList<WeiXinGoodsDto>();
				JSONArray jsonWXlist = person.getJSONArray("data");
				for (int i = 0; i < jsonWXlist.length(); i++) {
					WeiXinGoodsDto dto = new WeiXinGoodsDto();
					JSONObject jbt = jsonWXlist.getJSONObject(i);
					dto.setConpanyId(jbt.getLong("conpanyId"));
					dto.setId(jbt.getLong("id"));
					dto.setScore(jbt.getLong("score"));
					dto.setGoodsId(jbt.getLong("goodsId"));
					dto.setGoodsModel(jbt.getString("goodsModel"));
					dto.setGoodsName(jbt.getString("goodsName"));
					dto.setGoodsType(jbt.getString("goodsType"));
					dto.setImage1(jbt.getString("image1"));
					dto.setImage2(jbt.getString("image2"));
					dto.setImage3(jbt.getString("image3"));
					dto.setImage4(jbt.getString("image4"));
					dto.setInPrice(jbt.getInt("inPrice"));
					dto.setMarks(jbt.getString("marks"));
					dto.setPrice(jbt.getInt("price"));
					dto.setScore(jbt.getDouble("score"));
					dto.setUseShow(jbt.getBoolean("useShow"));
					weiXinList.add(dto);
				}
				map.put("data", weiXinList);
			}
			String pagenum = person.getString("pagenum");
			map.put("success", b1);
			map.put("pagenum", pagenum);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> shangjia(String id) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", id));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.shangjia, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			String info = person.getString("info");

			if (!b) {
				Object islogin = null;
				try {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.shangjia(id);
				} catch (Exception ex) {

				}
				map.put("success", false);
				map.put("info", info);
				list.add(map);
				return list;
			} else {

			}
			map.put("info", info);
			map.put("success", b);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> xiajia(String id) {
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair("id", id));
				List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
				Map<String, Object> map = new HashMap<String, Object>();
				try {
					String json = HttpClient.doPost(QNPermissionApi.mainLink
							+ QNPermissionApi.xiajia, params);
					JSONTokener jsonParser = new JSONTokener(json);
					JSONObject person = (JSONObject) jsonParser.nextValue();
					Boolean b = person.getBoolean("success");
					String info = person.getString("info");

					if (!b) {
						Object islogin = null;
						try {
							islogin = person.get("stute");
							loginAjax(username, password);
							return this.xiajia(id);
						} catch (Exception ex) {

						}
						map.put("success", false);
						map.put("info", info);
						list.add(map);
						return list;
					} else {

					}
					map.put("info", info);
					map.put("success", b);
					list.add(map);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					map.put("success", false);
					map.put("info", "登录异常");
					list.add(map);
				}
				return list;
	}

	@Override
	public List<Map<String, Object>> deleteOrd(String num) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> enter(String id) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", id));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.enter, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b1 = person.getBoolean("success");
			String info = person.getString("info");
			if (!b1) {
				Object islogin = null;
				try {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.enter(id);
				} catch (Exception ex) {

				}
				map.put("success", false);
				map.put("info", "获取数据失败");
				list.add(map);
				return list;
			} else {

			}
			map.put("success", b1);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> look(String num2, String song,
			String nowpage, String countNum) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("num2", num2));
		params.add(new BasicNameValuePair("song", song));
		params.add(new BasicNameValuePair("nowpage", nowpage));
		params.add(new BasicNameValuePair("countNum", countNum));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.look, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b1 = person.getBoolean("success");
			if (!b1) {
				Object islogin = null;
				try {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.look(num2, song, nowpage, countNum);
				} catch (Exception ex) {

				}
				map.put("success", false);
				map.put("info", "获取数据失败");
				list.add(map);
				return list;
			} else {
				// 解析data
				int pagenum = person.getInt("pagenum");
				List<WeixinOrderDto> weiXinList = new ArrayList<WeixinOrderDto>();
				JSONArray jsonWXlist = person.getJSONArray("data");
				for (int i = 0; i < jsonWXlist.length(); i++) {
					WeixinOrderDto dto = new WeixinOrderDto();
					JSONObject jbt = jsonWXlist.getJSONObject(i);
					dto.setConpanyId(jbt.getLong("conpanyId"));
					dto.setId(jbt.getLong("id"));
					dto.setMarks(jbt.getString("marks"));
					dto.setAddress(jbt.getString("address"));
					dto.setEndDate(jbt.getString("endDate"));
					dto.setLinkManId(jbt.getLong("linkManId"));
					dto.setNum(jbt.getString("num"));
					dto.setOrderId(jbt.getLong("orderId"));
					dto.setPhone(jbt.getString("phone"));
					dto.setStartDate(jbt.getString("startDate"));
					dto.setUserid(jbt.getLong("userid"));
					dto.setUsername(jbt.getString("username"));
					weiXinList.add(dto);
				}
				map.put("pagenum", pagenum);
				map.put("data", weiXinList);
			}
			map.put("success", b1);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> setboolean(String value, String type) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("value", value));
		params.add(new BasicNameValuePair("type", type));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.setboolean, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b1 = person.getBoolean("success");
			if (!b1) {
				Object islogin = null;
				try {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.setboolean(value, type);
				} catch (Exception ex) {

				}
				map.put("success", false);
				map.put("info", "获取数据失败");
				list.add(map);
				return list;
			} else {

			}
			map.put("success", b1);
			map.put("info", person.getString("info"));
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> getMessageSet() {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.getMessageSet, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b1 = person.getBoolean("success");
			if (!b1) {
				Object islogin = null;
				try {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.getMessageSet();
				} catch (Exception ex) {

				}
				map.put("success", false);
				map.put("info", "获取数据失败");
				list.add(map);
				return list;
			} else {
				MessageSetDto dto = new MessageSetDto();
				JSONObject jbt = person.getJSONObject("obj");
				dto.setAddPriceToVip(jbt.getBoolean("addPriceToVip"));
				dto.setAddPriceToVipContent(jbt
						.getString("addPriceToVipContent"));
				dto.setAddPriceToVipnum(jbt.getLong("addPriceToVipnum"));
				dto.setAddscoreToVip(jbt.getBoolean("addscoreToVip"));
				dto.setAddscoreToVipContent(jbt
						.getString("addscoreToVipContent"));
				dto.setAddscoreToVipnum(jbt.getLong("addscoreToVipnum"));
				dto.setConpanyId(jbt.getLong("conpanyId"));
				dto.setId(jbt.getLong("id"));
				dto.setNum(jbt.getLong("num"));
				dto.setOrderToUser(jbt.getBoolean("orderToUser"));
				dto.setOrderToUserContent(jbt.getString("orderToUserContent"));
				dto.setOrderToUsernum(jbt.getLong("orderToUsernum"));
				dto.setOrderToUserPhone(jbt.getString("orderToUserPhone"));
				dto.setQianMing(jbt.getString("qianMing"));
				dto.setReducePriceToVip(jbt.getBoolean("reducePriceToVip"));
				dto.setReducescoreToVip(jbt.getBoolean("reducescoreToVip"));
				dto.setReducesPriceToVipContent(jbt
						.getString("reducesPriceToVipContent"));
				dto.setReducesPriceToVipnum(jbt.getLong("reducesPriceToVipnum"));
				dto.setReducesscoreToVipContent(jbt
						.getString("reducesscoreToVipContent"));
				dto.setReducesscoreToVipnum(jbt.getLong("reducesscoreToVipnum"));
				dto.setWinxinInfoToUser(jbt.getBoolean("winxinInfoToUser"));
				dto.setWinxinInfoToUserContent(jbt
						.getString("winxinInfoToUserContent"));
				dto.setWinxinInfoToUsernum(jbt.getLong("winxinInfoToUsernum"));
				dto.setWinxinInfoToUserPhone(jbt
						.getString("winxinInfoToUserPhone"));
				dto.setYudingToUser(jbt.getBoolean("yudingToUser"));
				dto.setYudingToUserContent(jbt.getString("yudingToUserContent"));
				dto.setYudingToUsernum(jbt.getLong("yudingToUsernum"));
				dto.setYudingToUserPhone(jbt.getString("yudingToUserPhone"));
				map.put("obj", dto);
			}
			map.put("success", b1);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> setTemp(String volue, String type,
			String methed, String phone) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("value", volue));
		params.add(new BasicNameValuePair("type", type));
		params.add(new BasicNameValuePair("methed", methed));
		params.add(new BasicNameValuePair("phone", phone));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.setTemp, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b1 = person.getBoolean("success");
			if (!b1) {
				Object islogin = null;
				try {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.setTemp(volue, type, methed, phone);
				} catch (Exception ex) {

				}
				map.put("success", false);
				map.put("info", "获取数据失败");
				list.add(map);
				return list;
			} else {
				MessageSetDto dto = new MessageSetDto();
				JSONObject jbt = person.getJSONObject("obj");
				dto.setAddPriceToVip(jbt.getBoolean("addPriceToVip"));
				dto.setAddPriceToVipContent(jbt
						.getString("addPriceToVipContent"));
				dto.setAddPriceToVipnum(jbt.getLong("addPriceToVipnum"));
				dto.setAddscoreToVip(jbt.getBoolean("addscoreToVip"));
				dto.setAddscoreToVipContent(jbt
						.getString("addscoreToVipContent"));
				dto.setAddscoreToVipnum(jbt.getLong("addscoreToVipnum"));
				dto.setConpanyId(jbt.getLong("conpanyId"));
				dto.setId(jbt.getLong("id"));
				dto.setNum(jbt.getLong("num"));
				dto.setOrderToUser(jbt.getBoolean("orderToUser"));
				dto.setOrderToUserContent(jbt.getString("orderToUserContent"));
				dto.setOrderToUsernum(jbt.getLong("orderToUsernum"));
				dto.setOrderToUserPhone(jbt.getString("orderToUserPhone"));
				dto.setQianMing(jbt.getString("qianMing"));
				dto.setReducePriceToVip(jbt.getBoolean("reducePriceToVip"));
				dto.setReducescoreToVip(jbt.getBoolean("reducescoreToVip"));
				dto.setReducesPriceToVipContent(jbt
						.getString("reducesPriceToVipContent"));
				dto.setReducesPriceToVipnum(jbt.getLong("reducesPriceToVipnum"));
				dto.setReducesscoreToVipContent(jbt
						.getString("reducesscoreToVipContent"));
				dto.setReducesscoreToVipnum(jbt.getLong("reducesscoreToVipnum"));
				dto.setWinxinInfoToUser(jbt.getBoolean("winxinInfoToUser"));
				dto.setWinxinInfoToUserContent(jbt
						.getString("winxinInfoToUserContent"));
				dto.setWinxinInfoToUsernum(jbt.getLong("winxinInfoToUsernum"));
				dto.setWinxinInfoToUserPhone(jbt
						.getString("winxinInfoToUserPhone"));
				dto.setYudingToUser(jbt.getBoolean("yudingToUser"));
				dto.setYudingToUserContent(jbt.getString("yudingToUserContent"));
				dto.setYudingToUsernum(jbt.getLong("yudingToUsernum"));
				dto.setYudingToUserPhone(jbt.getString("yudingToUserPhone"));
				map.put("obj", dto);
			}
			map.put("success", b1);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> sendVipMessage(String value,
			String select, String type, String num) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("value", value));
		params.add(new BasicNameValuePair("type", type));
		params.add(new BasicNameValuePair("select", select));
		params.add(new BasicNameValuePair("num", num));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.sendVipMessage, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b1 = person.getBoolean("success");
			String info = person.getString("info");
			if (!b1) {
				Object islogin = null;
				try {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.sendVipMessage(value, select, type, num);
				} catch (Exception ex) {

				}
				map.put("success", false);
				map.put("info", "获取数据失败");
				list.add(map);
				return list;
			} else {

			}
			map.put("success", b1);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> updateAutoReSend_Voice(String id,
			String name, String content) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", id));
		params.add(new BasicNameValuePair("name", name));
		params.add(new BasicNameValuePair("content", content));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.updateAutoReSend_Voice, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			String info = person.getString("info");
			if (!b) {
				Object islogin = null;
				try {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.updateAutoReSend_Voice(id, name, content);
				} catch (Exception ex) {

				}
				map.put("success", false);
				map.put("info", info);
				list.add(map);
				return list;
			} else {

			}

			map.put("success", b);
			map.put("info", info);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> ajaxchangePassword(String old,
			String newp, String renewp) {
		// TODO 修改密码
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("old", old));
		params.add(new BasicNameValuePair("newp", newp));
		params.add(new BasicNameValuePair("renewp", renewp));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.ajaxchangePassword, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			String info = person.getString("info");
			if (!b) {
				Object islogin = null;
				try {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.ajaxchangePassword(old, newp, renewp);
				} catch (Exception ex) {

				}
				map.put("success", false);
				map.put("info", info);
				list.add(map);
				return list;
			} else {

			}

			map.put("success", b);
			map.put("info", info);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}
	@Override
	public List<Map<String, Object>> locationUp(String longitude,String latitude) {
		// TODO 外勤
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("longitude", longitude));
		params.add(new BasicNameValuePair("latitude", latitude));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.locationUp, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			String info = person.getString("info");
			if (!b) {
				Object islogin = null;
				try {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.locationUp(longitude, latitude);
				} catch (Exception ex) {

				}
				if(info.equals("没有登录")){
					loginAjax(username, password);
					return this.locationUp(longitude, latitude);
				}
				map.put("success", false);
				map.put("info", info);
				list.add(map);
				return list;
			} else {

			}

			map.put("success", b);
			map.put("info", info);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "登录异常");
			list.add(map);
		}
		return list;
	}
	@Override
	public List<Map<String, Object>> createConpany(String province,
			String city, String district, String conpanyName,
			String softAdminName, String softAdminPhone, String conpanyType,
			String conpanyType_id, String conpanyAdminEmail,
			String conpanyPinYin) {
		// TODO 注册
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("province", province));
		params.add(new BasicNameValuePair("city", city));
		params.add(new BasicNameValuePair("district", district));
		params.add(new BasicNameValuePair("conpanyName", conpanyName));
		params.add(new BasicNameValuePair("softAdminName", softAdminName));
		params.add(new BasicNameValuePair("softAdminPhone", softAdminPhone));
		params.add(new BasicNameValuePair("conpanyType", conpanyType));
		params.add(new BasicNameValuePair("conpanyType_id", conpanyType_id));
		params.add(new BasicNameValuePair("conpanyAdminEmail", conpanyAdminEmail));
		params.add(new BasicNameValuePair("code", conpanyPinYin));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.createConpany, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			String info = person.getString("info");
			if (!b) {
				Object islogin = null;
				try {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.createConpany(province, city, district,
							conpanyName, softAdminName, softAdminPhone,
							conpanyType, conpanyType_id, conpanyAdminEmail,
							conpanyPinYin);
				} catch (Exception ex) {

				}
				if(info.equals("没有登录")){
					loginAjax(username, password);
					return this.createConpany(province, city, district,
							conpanyName, softAdminName, softAdminPhone,
							conpanyType, conpanyType_id, conpanyAdminEmail,
							conpanyPinYin);
				}
				map.put("success", false);
				map.put("info", info);
				list.add(map);
				return list;
			} else {

			}

			map.put("success", b);
			map.put("info", info);
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "注册异常");
			list.add(map);
		}
		return list;
	}
	@Override
	public List<Map<String, Object>> getHangye() {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.getHangye, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			Object islogin = null;
			if (!b) {
				if (islogin != null) { // 状态不为空，登录错误
					map.put("login", false);
				} else {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.getHangye();
				}
			} else {
				// 解析data
				List<Hangye> weiXinList = new ArrayList<Hangye>();
				JSONArray jsonWXlist = person.getJSONArray("data");
				for (int i = 0; i < jsonWXlist.length(); i++) {
					Hangye dto = new Hangye();
					JSONObject jbt = jsonWXlist.getJSONObject(i);
					dto.setId(jbt.getLong("id"));
					dto.setHangyeName(jbt.getString("hangyeName"));
					weiXinList.add(dto);
				}
				map.put("data", weiXinList);
			}
			map.put("success", b);			
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			list.add(map);
		}
		return list;
	}
	@Override
	public List<Map<String, Object>> waiqinGroupManager(String groupid,
			String trueName, String userid, String startDate, String endDate) {
		// TODO 外勤管理
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("groupid", groupid));
		params.add(new BasicNameValuePair("trueName", trueName));
		params.add(new BasicNameValuePair("userid", userid));
		params.add(new BasicNameValuePair("startDate", startDate));
		params.add(new BasicNameValuePair("endDate", endDate));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.waiqinGroupManager, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			Object islogin = null;
			if (!b) {
				if (islogin != null) { // 状态不为空，登录错误
					map.put("login", false);
				} else {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.waiqinGroupManager(groupid, trueName,
							userid, startDate, endDate);
				}
			} else {
				// 解析data
				if(groupid != null){
					List<GroupConpanyLinkUser> linkUserList = new ArrayList<GroupConpanyLinkUser>();
					JSONArray jsonWXlist = person.getJSONArray("data");
					for (int i = 0; i < jsonWXlist.length(); i++) {
						GroupConpanyLinkUser dto = new GroupConpanyLinkUser();
						JSONObject jbt = jsonWXlist.getJSONObject(i);
						dto.setId(jbt.getLong("id"));
						dto.setConpanyId(jbt.getLong("conpanyId"));
						dto.setConpanyUserId(jbt.getLong("conpanyUserId"));
						dto.setConpanyUserName(jbt.getString("conpanyUserTrueName"));
						dto.setGroupId(jbt.getLong("groupId"));
						dto.setGroupName(jbt.getString("groupName"));
						linkUserList.add(dto);
					}
					map.put("data", linkUserList);
				}else if(userid != null){
					List<MapInfo> infoList = new ArrayList<MapInfo>();
					JSONArray jsonWXlist = person.getJSONArray("data");
					for (int i = 0; i < jsonWXlist.length(); i++) {
						MapInfo dto = new MapInfo();
						JSONObject jbt = jsonWXlist.getJSONObject(i);
						dto.setId(jbt.getLong("id"));
						dto.setConpanyId(jbt.getLong("conpanyId"));
						dto.setConpanyUserId(jbt.getLong("conpanyUserId"));
						dto.setConpanyUserName(jbt.getString("conpanyUserName"));
						dto.setCreateDate(jbt.getString("createDate"));
						dto.setMapLocation(jbt.getString("mapLocation"));
						infoList.add(dto);
					}
					map.put("data", infoList);
				}else{
					List<SoftPermission> infoList = new ArrayList<SoftPermission>();
					JSONArray jsonWXlist = person.getJSONArray("data");
					for (int i = 0; i < jsonWXlist.length(); i++) {
						SoftPermission dto = new SoftPermission();
						JSONObject jbt = jsonWXlist.getJSONObject(i);
						dto.setId(jbt.getLong("id"));
						dto.setFunctionName(jbt.getString("functionName"));
						dto.setMarks(jbt.getString("marks"));
						dto.setUplevel(jbt.getLong("uplevel"));
						dto.setUrl(jbt.getString("url"));
						infoList.add(dto);
					}
					map.put("data", infoList);
				}			
				
			}
			map.put("success", b);			
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			list.add(map);
		}
		return list;
	}
	@Override
	public List<Map<String, Object>> getHuDongKongJian(String groupId,
			String nowpage, String countNum) {
		// TODO 互动空间
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("groupId", groupId));
		params.add(new BasicNameValuePair("nowpage", nowpage));
		params.add(new BasicNameValuePair("countNum", countNum));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.getHuDongKongJian, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			Object islogin = null;
			if (!b) {
				if (islogin != null) { // 状态不为空，登录错误
					map.put("login", false);
				} else {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.getHuDongKongJian(groupId, nowpage,
							countNum);
				}
			} else {
				// 解析data
				if(groupId != null){
					List<KongJianDto> kongJianList = new ArrayList<KongJianDto>();
					JSONArray jsonWXlist = person.getJSONArray("data");
					for (int i = 0; i < jsonWXlist.length(); i++) {
						KongJianDto dto = new KongJianDto();
						JSONObject jbt = jsonWXlist.getJSONObject(i);
						dto.setId(jbt.getLong("id"));
						dto.setConpanyId(jbt.getLong("conpanyId"));
						dto.setGroupId(jbt.getLong("groupId"));
						dto.setContent(jbt.getString("content"));
						dto.setCreateDate(jbt.getString("createDate"));
						dto.setCreateUserId(jbt.getLong("createUserId"));
						dto.setCreateUserName(jbt.getString("createUserName"));
						dto.setIndexNum(jbt.getInt("indexNum"));
						dto.setTitle(jbt.getString("title"));
						dto.setTouPiao(jbt.getBoolean("touPiao"));
						dto.setZan(jbt.getLong("zan"));
						List<Object> fileList = new ArrayList<Object>();
						JSONArray file = jbt.getJSONArray("fileList");
						for(int j = 0; j <file.length(); j++){
							ConpanyZoneImage imgFile = new ConpanyZoneImage();
							JSONObject img = file.getJSONObject(j);
							imgFile.setConpanyId(img.getLong("conpanyId"));
							imgFile.setFileName(img.getString("fileName"));
							imgFile.setId(img.getLong("id"));
							imgFile.setLink(img.getString("link"));
							imgFile.setMainId(img.getLong("mainId"));
							imgFile.setRet(imgFile.isRet());
							imgFile.setSystemFile(img.getString("systemFile"));
							imgFile.setType(img.getString("type"));
							fileList.add(imgFile);
						}
						dto.setFileList(fileList);
						List<Object> imageList = new ArrayList<Object>();
						JSONArray image = jbt.getJSONArray("imageList");
						for(int j = 0; j <image.length(); j++){
							ConpanyZoneImage imgFile = new ConpanyZoneImage();
							JSONObject img = image.getJSONObject(j);
							imgFile.setConpanyId(img.getLong("conpanyId"));
							imgFile.setFileName(img.getString("fileName"));
							imgFile.setId(img.getLong("id"));
							imgFile.setLink(img.getString("link"));
							imgFile.setMainId(img.getLong("mainId"));
							imgFile.setRet(imgFile.isRet());
							imgFile.setSystemFile(img.getString("systemFile"));
							imgFile.setType(img.getString("type"));
							imageList.add(imgFile);
						}
						dto.setImageList(imageList);
						List<Object> vidioList = new ArrayList<Object>();
						JSONArray video = jbt.getJSONArray("vidioList");
						for(int j = 0; j <video.length(); j++){
							ConpanyZoneImage imgFile = new ConpanyZoneImage();
							JSONObject img = video.getJSONObject(j);
							imgFile.setConpanyId(img.getLong("conpanyId"));
							imgFile.setFileName(img.getString("fileName"));
							imgFile.setId(img.getLong("id"));
							imgFile.setLink(img.getString("link"));
							imgFile.setMainId(img.getLong("mainId"));
							imgFile.setRet(imgFile.isRet());
							imgFile.setSystemFile(img.getString("systemFile"));
							imgFile.setType(img.getString("type"));
							vidioList.add(imgFile);
						}
						dto.setVidioList(vidioList);
						
						if(jbt.getBoolean("touPiao")){
							List<ConpanyZoneTouPiaoItemDto> toupiaoitemDto= new ArrayList<ConpanyZoneTouPiaoItemDto>();
							JSONArray tpList = jbt.getJSONArray("toupiaoItemList");		
							
							for(int j=0;j<tpList.length();j++){
								ConpanyZoneTouPiaoItemDto touPiao = new ConpanyZoneTouPiaoItemDto();
								JSONObject tPiao = tpList.getJSONObject(j);
								touPiao.setBaifenbi(tPiao.getInt("baifenbi"));
								touPiao.setConpanyId(tPiao.getLong("conpanyId"));
								touPiao.setConpanyZoneTouPiaoId(tPiao.getLong("conpanyZoneTouPiaoId"));
								touPiao.setCountNum(tPiao.getLong("countNum"));
								touPiao.setGroupId(tPiao.getLong("groupId"));
								touPiao.setId(tPiao.getLong("id"));
								touPiao.setImage(tPiao.getString("image"));
								touPiao.setName(tPiao.getString("name"));
								JSONArray sendUser = tPiao.getJSONArray("sendUserlist");
								List<Object> sUser = new ArrayList<Object>();
								for(int k=0;k<sendUser.length();k++){
									sUser.add(sendUser.get(k));
								}
								touPiao.setSendUserlist(sUser);
								toupiaoitemDto.add(touPiao);
//										touPiao.setBaifenbi();
							}
							dto.setToupiaoItemList(toupiaoitemDto);
						}
						
						List<ConpanyZoneRetDto> retdtolist=new ArrayList<ConpanyZoneRetDto>();
						JSONArray ret = jbt.getJSONArray("retList");
						for(int j = 0; j <ret.length(); j++){
							ConpanyZoneRetDto retdto = new ConpanyZoneRetDto();
							JSONObject cztpiao = ret.getJSONObject(j);
							retdto.setConpanyId(cztpiao.getLong("conpanyId"));
							retdto.setConpanyZoneId(cztpiao.getLong("conpanyZoneId"));
							retdto.setContent(cztpiao.getString("content"));
							retdto.setCreateDate(cztpiao.getString("createDate"));
							retdto.setCreateUserId(cztpiao.getLong("createUserId"));
							retdto.setCreateUserName(cztpiao.getString("createUserName"));
							retdto.setGroupId(cztpiao.getLong("groupId"));
							retdto.setId(cztpiao.getLong("id"));
							retdto.setIndexNum(cztpiao.getInt("indexNum"));
							vidioList = new ArrayList<Object>();
							video = cztpiao.getJSONArray("vidioList");
							for(int k = 0; k <video.length(); k++){
								ConpanyZoneImage imgFile = new ConpanyZoneImage();
								JSONObject img = video.getJSONObject(k);
								imgFile.setConpanyId(img.getLong("conpanyId"));
								imgFile.setFileName(img.getString("fileName"));
								imgFile.setId(img.getLong("id"));
								imgFile.setLink(img.getString("link"));
								imgFile.setMainId(img.getLong("mainId"));
								imgFile.setRet(imgFile.isRet());
								imgFile.setSystemFile(img.getString("systemFile"));
								imgFile.setType(img.getString("type"));
								vidioList.add(imgFile);
							}
							retdto.setVidioList(vidioList);
							fileList = new ArrayList<Object>();
							file = jbt.getJSONArray("fileList");
							for(int k = 0; k <file.length(); k++){
								ConpanyZoneImage imgFile = new ConpanyZoneImage();
								JSONObject img = file.getJSONObject(k);
								imgFile.setConpanyId(img.getLong("conpanyId"));
								imgFile.setFileName(img.getString("fileName"));
								imgFile.setId(img.getLong("id"));
								imgFile.setLink(img.getString("link"));
								imgFile.setMainId(img.getLong("mainId"));
								imgFile.setRet(imgFile.isRet());
								imgFile.setSystemFile(img.getString("systemFile"));
								imgFile.setType(img.getString("type"));
								fileList.add(imgFile);
							}
							retdto.setFileList(fileList);
							imageList = new ArrayList<Object>();
							image = jbt.getJSONArray("imageList");
							for(int k = 0; k <image.length(); k++){
								ConpanyZoneImage imgFile = new ConpanyZoneImage();
								JSONObject img = image.getJSONObject(k);
								imgFile.setConpanyId(img.getLong("conpanyId"));
								imgFile.setFileName(img.getString("fileName"));
								imgFile.setId(img.getLong("id"));
								imgFile.setLink(img.getString("link"));
								imgFile.setMainId(img.getLong("mainId"));
								imgFile.setRet(imgFile.isRet());
								imgFile.setSystemFile(img.getString("systemFile"));
								imgFile.setType(img.getString("type"));
								imageList.add(imgFile);
							}
							retdto.setImageList(imageList);
							retdtolist.add(retdto);
						}
						dto.setRetList(retdtolist);
						List<Object> zanList= new ArrayList<Object>();
						JSONArray zan = jbt.getJSONArray("zanList");
						for(int j = 0; j <zan.length(); j++){
							ConpanyZoneZan czan = new ConpanyZoneZan();
							JSONObject czz = zan.getJSONObject(j);
							if(czz != null){
								czan.setConpanyId(czz.getLong("conpanyId"));
								czan.setConpanyZoneid(czz.getLong("conpanyZoneid"));
								czan.setCreateDate(czz.getString("createDate"));
								czan.setCreateUserId(czz.getLong("createUserId"));
								czan.setCreateUserName(czz.getString("createUserName"));
								czan.setGroupId(czz.getLong("groupId"));
								czan.setId(czz.getLong("id"));
							}
							zanList.add(czan);
						}
						dto.setZanList(zanList);
						kongJianList.add(dto);
					}
					map.put("data", kongJianList);
				}else{

				}			
				
			}
			map.put("success", b);			
			list.add(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			list.add(map);
		}
		return list;
	}
	@Override
	public List<Map<String, Object>> sendKongJianManager(String groupId,
			String title, String content, String file, String img,
			String video, String toupiao, String toupiao_title,
			String toupiao_start, String toupiao_end, String toupiao_move) {
		// TODO 回复
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("groupId", groupId));
		params.add(new BasicNameValuePair("title", title));
		params.add(new BasicNameValuePair("content", content));
		params.add(new BasicNameValuePair("file", file));
		params.add(new BasicNameValuePair("img", img));
		params.add(new BasicNameValuePair("video", video));
		params.add(new BasicNameValuePair("toupiao", toupiao));
		params.add(new BasicNameValuePair("toupiao_title", toupiao_title));
		params.add(new BasicNameValuePair("toupiao_start", toupiao_start));
		params.add(new BasicNameValuePair("toupiao_end", toupiao_end));
		params.add(new BasicNameValuePair("toupiao_move", toupiao_move));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.sendKongJianManager, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Log.e("person--", ""+person);
			Boolean b = person.getBoolean("success");
			Object islogin = null;
			if (!b) {
				if (islogin != null) { // 状态不为空，登录错误
					map.put("login", false);
				} else {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.sendKongJianManager(groupId, title,
							content, file, img, video, toupiao,
							toupiao_title, toupiao_start, toupiao_end,
							toupiao_move);
				}
			} else {
				//解析data
				List<KongJianDto> kongJianList = new ArrayList<KongJianDto>();
				JSONArray jsonWXlist = person.getJSONArray("data");
				for (int i = 0; i < jsonWXlist.length(); i++) {
					KongJianDto dto = new KongJianDto();
					JSONObject jbt = jsonWXlist.getJSONObject(i);
					dto.setId(jbt.getLong("id"));
					dto.setConpanyId(jbt.getLong("conpanyId"));
					dto.setGroupId(jbt.getLong("groupId"));
					dto.setContent(jbt.getString("content"));
					dto.setCreateDate(jbt.getString("createDate"));
					dto.setCreateUserId(jbt.getLong("createUserId"));
					dto.setCreateUserName(jbt.getString("createUserName"));
					dto.setIndexNum(jbt.getInt("indexNum"));
					dto.setTitle(jbt.getString("title"));
					dto.setTouPiao(jbt.getBoolean("touPiao"));
					dto.setZan(jbt.getLong("zan"));
					List<Object> fileList = (List<Object>)jbt.getJSONArray("fileList");
					dto.setFileList(fileList);
					dto.setImageList((List<Object>)jbt.getJSONArray("imageList"));
					dto.setVidioList((List<Object>)jbt.getJSONArray("vidioList"));
					List<ConpanyZoneTouPiaoItemDto> toupiaoitemDto=new ArrayList<ConpanyZoneTouPiaoItemDto>();
					List<Object> listTouPiao=(List<Object>)jbt.getJSONArray("toupiaoItemList");
					if(listTouPiao.iterator().hasNext()){
						ConpanyZoneTouPiao ctoupiao=(ConpanyZoneTouPiao) listTouPiao.iterator().next();
						dto.setToupiaoList(ctoupiao);
						List<Object> toupiaoItems= (List<Object>)jbt.getJSONArray("itemdto");
						Iterator<Object> itemi=toupiaoItems.iterator();
						while(itemi.hasNext()){
							ConpanyZoneTouPiaoItem toupiaoitem=(ConpanyZoneTouPiaoItem) itemi.next();
							ConpanyZoneTouPiaoItemDto itemdto=new ConpanyZoneTouPiaoItemDto();
							if(ctoupiao.getCountTouPiao()==0){
								itemdto.setBaifenbi(0);
							}else{
								itemdto.setBaifenbi((toupiaoitem.getCountNum()/ctoupiao.getCountTouPiao())*100);
							}
							itemdto.setConpanyId(toupiaoitem.getConpanyId());
							itemdto.setConpanyZoneTouPiaoId(toupiaoitem.getConpanyZoneTouPiaoId());
							itemdto.setCountNum(toupiaoitem.getCountNum());
							itemdto.setGroupId(toupiaoitem.getGroupId());
							itemdto.setId(toupiaoitem.getId());
							itemdto.setImage(toupiaoitem.getImage());
							itemdto.setName(toupiaoitem.getName());
							List<Object> sendUser=(List<Object>)jbt.getJSONArray("sendUser");
							itemdto.setSendUserlist(sendUser);
							toupiaoitemDto.add(itemdto);
						}
						dto.setToupiaoItemList(toupiaoitemDto);
					}else{
						dto.setToupiaoList(new ConpanyZoneTouPiao());
					}
					List<Object> retMessge=(List<Object>)jbt.getJSONArray("retdtolist");
					List<ConpanyZoneRetDto> retdtolist=new ArrayList<ConpanyZoneRetDto>();
					Iterator<Object> retMessgei=retMessge.iterator();
					while(retMessgei.hasNext()){
						ConpanyZoneRet cret=(ConpanyZoneRet) retMessgei.next();
						ConpanyZoneRetDto retdto=new ConpanyZoneRetDto();
						retdto.setConpanyId(cret.getConpanyId());
						retdto.setConpanyZoneId(cret.getConpanyZoneId());
						retdto.setContent(cret.getContent());
//						retdto.setCreateDate(cret.getCreateDate());
						retdto.setCreateUserId(cret.getCreateUserId());
						retdto.setCreateUserName(cret.getCreateUserName());
						retdto.setGroupId(cret.getGroupId());
						retdto.setId(cret.getId());
						retdto.setIndexNum(cret.getIndexNum());
						retdto.setVidioList(retdto.getVidioList());
						retdto.setFileList(retdto.getFileList());
						retdto.setImageList(retdto.getImageList());
						retdtolist.add(retdto);
					}
					dto.setRetList(retdtolist);
					dto.setZanList((List<Object>)jbt.get("zanList"));
					kongJianList.add(dto);
				}
				map.put("data", kongJianList);
			}
			map.put("success", b);
			list.add(map);
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			list.add(map);
		}
		return list;
	}
	@Override
	public List<Map<String, Object>> resendKongJianManager(String id,
			String file, String img, String video, String content) {
		// TODO 重复回复
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", id));
		params.add(new BasicNameValuePair("file", file));
		params.add(new BasicNameValuePair("img", img));
		params.add(new BasicNameValuePair("video", video));
		params.add(new BasicNameValuePair("content", content));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.resendKongJianManager, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			Object islogin = null;
			if (!b) {
				if (islogin != null) { // 状态不为空，登录错误
					map.put("login", false);
				} else {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.resendKongJianManager(id, file, img, video, content);
				}
			} else {
				//解析data
				List<ConpanyZoneRetDto> retdto=new ArrayList<ConpanyZoneRetDto>();
				JSONArray jsonWXlist = person.getJSONArray("data");
//				for (int i = 0; i < jsonWXlist.length(); i++) {
					ConpanyZoneRetDto dto=new ConpanyZoneRetDto();
					ConpanyZoneRet ret=new ConpanyZoneRet();
					JSONObject jbt = jsonWXlist.getJSONObject(0);
					
					ret.setConpanyId(jbt.getLong("conpanyId"));
					ret.setConpanyZoneId(jbt.getLong("conpanyZoneId"));
					ret.setContent(jbt.getString("content"));
					ret.setCreateDate(new Date());
					ret.setCreateUserId(jbt.getLong("createUserId"));
					ret.setCreateUserName(jbt.getString("createUserName"));
					ret.setGroupId(jbt.getLong("groupId"));
					ret.setIndexNum(0);
					
					dto.setConpanyId(ret.getConpanyId());
					dto.setConpanyZoneId(ret.getConpanyZoneId());
					dto.setContent(content);
//					dto.setCreateDate(ret.getCreateDate());
					dto.setCreateUserId(ret.getCreateUserId());
					dto.setCreateUserName(ret.getCreateUserName());
					List<Object> fileList=new ArrayList<Object>();
					if(!file.equals("0")){
						String[] filestrlist=file.split(",");
						for(int i=0;i<filestrlist.length;i++){
							String[] filenamelink=filestrlist[i].split(":");
							ConpanyZoneImage image=new ConpanyZoneImage();
							image.setConpanyId(jbt.getLong("conpanyId"));
							image.setFileName(filenamelink[2]);
							image.setLink(filenamelink[0]+":"+filenamelink[1]);
							image.setMainId(ret.getId());
							image.setRet(true);
							image.setSystemFile("");
							image.setType("3");
							fileList.add(image);
						}
					}
					dto.setFileList(fileList);
					dto.setGroupId(jbt.getLong("groupId"));
					dto.setId(ret.getId());
					List<Object> imgList=new ArrayList<Object>();
					if(!img.equals("0")){
						String[] imgstrlist=img.split(",");
						for(int i=0;i<imgstrlist.length;i++){
							ConpanyZoneImage image=new ConpanyZoneImage();
							image.setConpanyId(jbt.getLong("conpanyId"));
							image.setLink(imgstrlist[i]);
							image.setMainId(ret.getId());
							image.setRet(true);
							image.setSystemFile("");
							image.setType("1");
							imgList.add(image);
						}
					}
					dto.setImageList(imgList);
					dto.setIndexNum(ret.getIndexNum());
					List<Object> videoList=new ArrayList<Object>();
					if(!video.equals("0")){
						String[] videostrlist=video.split(",");
						for(int i=0;i<videostrlist.length;i++){
							ConpanyZoneImage image=new ConpanyZoneImage();
							image.setConpanyId(jbt.getLong("conpanyId"));
							image.setLink(videostrlist[i]);
							image.setMainId(ret.getId());
							image.setRet(true);
							image.setSystemFile("");
							image.setType("2");
							videoList.add(image);
						}
					}
					dto.setVidioList(videoList);
//				}
			}
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			list.add(map);
		}
		return list;
	}
	@Override
	public List<Map<String, Object>> setIndexKongjian(String id) {
		// TODO 设置置顶
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", id));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.setIndexKongjian, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			Object islogin = null;
			if (!b) {
				if (islogin != null) { // 状态不为空，登录错误
					map.put("login", false);
				} else {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.setIndexKongjian(id);
				}
			} else {
				
			}
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			list.add(map);
		}
			return list;
	}
	@Override
	public List<Map<String, Object>> deleteKongjian(String id) {
		// TODO 删除
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", id));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.deleteKongjian, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			Object islogin = null;
			if (!b) {
				if (islogin != null) { // 状态不为空，登录错误
					map.put("login", false);
				} else {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.deleteKongjian(id);
				}
			} else {
				map.put("info", person.getString("info"));
				list.add(map);
			}
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			list.add(map);
		}
			return list;
	}
	@Override
	public List<Map<String, Object>> zan(String id) {
		// TODO 点赞
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", id));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.zan, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			Object islogin = null;
			if (!b) {
				if (islogin != null) { // 状态不为空，登录错误
					map.put("login", false);
				} else {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.zan(id);
				}
			} else {
				//解析data
				JSONObject jbt = person.getJSONObject("zanobj");
				ConpanyZoneZan zan=new ConpanyZoneZan();
				zan.setConpanyId(jbt.getLong("conpanyId"));
				zan.setConpanyZoneid(jbt.getLong("conpanyZoneid"));
				zan.setCreateDate(jbt.getString("createDate"));
				zan.setCreateUserId(jbt.getLong("createUserId"));
				zan.setCreateUserName(jbt.getString("createUserName"));
				zan.setGroupId(jbt.getLong("groupId"));
				zan.setId(jbt.getLong("id"));
				map.put("zanobj", zan);
				map.put("num", person.get("num"));
				map.put("success", b);
				list.add(map);
			}
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			list.add(map);
		}
			return list;
	}
	@Override
	public List<Map<String, Object>> getList(String nowpage, String countNum,
			String type) {
		// TODO 文件管理
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("nowpage", nowpage));
		params.add(new BasicNameValuePair("countNum", countNum));
		params.add(new BasicNameValuePair("type", type));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.getList, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			Object islogin = null;
			if (!b) {
				if (islogin != null) { // 状态不为空，登录错误
					map.put("login", false);
				} else {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.getList(nowpage, countNum, type);
				}
			} else {
				//解析data
				List<ImageList> imageList=new ArrayList<ImageList>();
				JSONArray jsonWXlist = person.getJSONArray("data");
				for (int i = 0; i < jsonWXlist.length(); i++) {
					ImageList dto = new ImageList();
					JSONObject jbt = jsonWXlist.getJSONObject(i);
					dto.setConpanyId(jbt.getLong("conpanyId"));
					dto.setFileName(jbt.getString("fileName"));
					dto.setFilesize(jbt.getLong("filesize"));
					dto.setFiletype(jbt.getString("filetype"));
					dto.setId(jbt.getLong("id"));
					dto.setLinkaddress(jbt.getString("linkaddress"));
					dto.setMd5(jbt.getString("md5"));
					dto.setStartDate(jbt.getString("startDate"));
					dto.setType(jbt.getString("type"));
					dto.setUserId(jbt.getLong("userId"));
					dto.setUserName(jbt.getString("userName"));
					dto.setWwwLinkAddress(jbt.getString("wwwLinkAddress"));
					imageList.add(dto);
				}
				map.put("data", imageList);
				map.put("nowpage", nowpage);
				map.put("success", b);
				list.add(map);
			}
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			list.add(map);
		}
		return list;
	}
	@Override
	public List<Map<String, Object>> getImages(String nowpage) {
		// TODO 图片审核
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("nowpage", nowpage));
//		params.add(new BasicNameValuePair("countNum", countNum));
//		params.add(new BasicNameValuePair("type", type));
//		params.add(new BasicNameValuePair("id", id));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.getImages, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			Object islogin = null;
			if (!b) {
				if (islogin != null) { // 状态不为空，登录错误
					map.put("login", false);
				} else {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.getImages(nowpage);
				}
			} else {
				//解析数据
//				if(type == null){
					List<Object> imageList = new ArrayList<Object>();
					JSONArray images = person.getJSONArray("data");
					for(int i=0;i<images.length();i++){
						JSONObject jbt = images.getJSONObject(i);
						VipImageMessageDto imagedto = new VipImageMessageDto();
						imagedto.setCity(jbt.getString("city"));
						imagedto.setConpanyId(jbt.getLong("conpanyId"));
						imagedto.setConpanyName(jbt.getString("conpanyName"));
						imagedto.setCountry(jbt.getString("country"));
						imagedto.setCreateDate(jbt.getString("createDate"));
						imagedto.setCreateVipId(jbt.getLong("createVipId"));
						imagedto.setCreateVipName(jbt.getString("createVipName"));
						imagedto.setCreateVipPhone(jbt.getString("createVipPhone"));
						imagedto.setDistrict(jbt.getString("district"));
						imagedto.setGif(jbt.getBoolean("gif"));
						imagedto.setHangyeId(jbt.getLong("hangyeId"));
						imagedto.setHangyeName(jbt.getString("hangyeName"));
						imagedto.setId(jbt.getLong("id"));
						imagedto.setImageLink(jbt.getString("imageLink"));
						imagedto.setIndexNum(jbt.getLong("indexNum"));
						imagedto.setLookNum(jbt.getLong("lookNum"));
						imagedto.setMessage(jbt.getString("message"));
						imagedto.setPass(jbt.getInt("pass"));
						imagedto.setPassMessage(jbt.getString("passMessage"));
						imagedto.setProvince(jbt.getString("province"));
						imagedto.setReturnNum(jbt.getLong("returnNum"));
						imagedto.setTitle(jbt.getString("title"));
						imagedto.setTouXiangImage(jbt.getString("touXiangImage"));
						imagedto.setUserTableId(jbt.getLong("userTableId"));
						imagedto.setZan(jbt.getLong("zan"));
						imageList.add(imagedto);
					}
					map.put("data", imageList);
					map.put("success", b);
					list.add(map);
				}
//				else{
//					map.put("info", person.getString("info"));
//					map.put("success", person.getString("success"));
//				}
//			}
			
			}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				map.put("success", false);
				list.add(map);
			}
			return list;
	}
	@Override
	public List<Map<String, Object>> getVideos(String nowpage, String countNum,
			String type, String id) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("nowpage", nowpage));
		params.add(new BasicNameValuePair("countNum", countNum));
		params.add(new BasicNameValuePair("type", type));
		params.add(new BasicNameValuePair("id", id));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.getVideos, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			Object islogin = null;
			if (!b) {
				if (islogin != null) { // 状态不为空，登录错误
					map.put("login", false);
				} else {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.getVideos(nowpage, countNum, type, id);
				}
			} else {
				//解析数据
				if(type == null){
					List<Object> videoList = new ArrayList<Object>();
					JSONArray videos = person.getJSONArray("data");
					for(int i = 0;i<videos.length();i++){
						JSONObject jbt = videos.getJSONObject(i);
						VipVideoMessageDto videoDto = new VipVideoMessageDto();
						videoDto.setCity(jbt.getString("city"));
						videoDto.setConpanyId(jbt.getLong("conpanyId"));
						videoDto.setConpanyName(jbt.getString("conpanyName"));
						videoDto.setCountry(jbt.getString("country"));
						videoDto.setCreateDate(jbt.getString("createDate"));
						videoDto.setCreateVipId(jbt.getLong("createVipId"));
						videoDto.setCreateVipName(jbt.getString("createVipName"));
						videoDto.setCreateVipPhone(jbt.getString("createVipPhone"));
						videoDto.setDistrict(jbt.getString("district"));
						videoDto.setHangyeId(jbt.getLong("hangyeId"));
						videoDto.setHangyeName(jbt.getString("hangyeName"));
						videoDto.setId(jbt.getLong("id"));
						videoDto.setIndexNum(jbt.getLong("indexNum"));
						videoDto.setLookNum(jbt.getLong("lookNum"));
						videoDto.setMessage(jbt.getString("message"));
						videoDto.setPass(jbt.getInt("pass"));
						videoDto.setPassMessage(jbt.getString("passMessage"));
						videoDto.setProvince(jbt.getString("province"));
						videoDto.setReturnNum(jbt.getLong("returnNum"));
						videoDto.setTitle(jbt.getString("title"));
						videoDto.setTouXiangImage(jbt.getString("touXiangImage"));
						videoDto.setUserTableId(jbt.getLong("userTableId"));
						videoDto.setZan(jbt.getLong("zan"));
						videoList.add(videoDto);
					}
					map.put("data", videoList);
					list.add(map);
				}else{
					map.put("info", person.getString("info"));
					map.put("success", person.getString("success"));
				}
			}
			
			}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				map.put("success", false);
				list.add(map);
			}
			return list;
	}
	@Override
	public List<Map<String, Object>> getTexts(String nowpage, String countNum,
			String type, String id) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("nowpage", nowpage));
		params.add(new BasicNameValuePair("countNum", countNum));
		params.add(new BasicNameValuePair("type", type));
		params.add(new BasicNameValuePair("id", id));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.getTexts, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			Object islogin = null;
			if (!b) {
				if (islogin != null) { // 状态不为空，登录错误
					map.put("login", false);
				} else {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.getTexts(nowpage, countNum, type, id);
				}
			} else {
				//解析data
				if(type == null){
					List<Object> textList = new ArrayList<Object>();
					JSONArray texts = person.getJSONArray("data");
					for(int i = 0; i < texts.length();i++){
						JSONObject jbt = texts.getJSONObject(i);
						VipTextMessageDto textdto = new VipTextMessageDto();
						textdto.setCity(jbt.getString("city"));
						textdto.setConpanyId(jbt.getLong("conpanyId"));
						textdto.setConpanyName(jbt.getString("conpanyName"));
						textdto.setCountry(jbt.getString("country"));
						textdto.setCreateDate(jbt.getString("createDate"));
						textdto.setCreateVipId(jbt.getLong("createVipId"));
						textdto.setCreateVipName(jbt.getString("createVipName"));
						textdto.setCreateVipPhone(jbt.getString("createVipPhone"));
						textdto.setDistrict(jbt.getString("district"));
						textdto.setHangyeId(jbt.getLong("hangyeId"));
						textdto.setHangyeName(jbt.getString("hangyeName"));
						textdto.setId(jbt.getLong("id"));
						textdto.setIndexNum(jbt.getLong("indexNum"));
						textdto.setLookNum(jbt.getLong("lookNum"));
						textdto.setMessage(jbt.getString("message"));
						textdto.setPass(jbt.getInt("pass"));
						textdto.setPassMessage(jbt.getString("passMessage"));
						textdto.setProvince(jbt.getString("province"));
						textdto.setReturnNum(jbt.getLong("returnNum"));
						textdto.setTitle(jbt.getString("title"));
						textdto.setTouXiangImage(jbt.getString("touXiangImage"));
						textdto.setUserTableId(jbt.getLong("userTableId"));
						textdto.setZan(jbt.getLong("zan"));
						textList.add(textdto);
					}
					map.put("data", textList);
					list.add(map);
				}else{
					map.put("info", person.getString("info"));
					map.put("success", person.getString("success"));
				}				
			}			
			}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				map.put("success", false);
				list.add(map);
			}
			return list;
	}
	@Override
	public List<Map<String, Object>> getErShou(String nowpage, String countNum,
			String type, String id) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("nowpage", nowpage));
		params.add(new BasicNameValuePair("countNum", countNum));
		params.add(new BasicNameValuePair("type", type));
		params.add(new BasicNameValuePair("id", id));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.getErShou, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			Object islogin = null;
			if (!b) {
				if (islogin != null) { // 状态不为空，登录错误
					map.put("login", false);
				} else {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.getErShou(nowpage, countNum, type, id);
				}
			} else {
				//解析data
				if(type == null){
					List<Object> ershouList = new ArrayList<Object>();
					JSONArray ershou = person.getJSONArray("data");
					for(int i = 0;i< ershou.length(); i++){
						JSONObject jbt = ershou.getJSONObject(i);
						VipErShouDto erdto = new VipErShouDto();
						erdto.setCity(jbt.getString("city"));
						erdto.setConpanyId(jbt.getLong("conpanyId"));
						erdto.setConpanyName(jbt.getString("conpanyName"));
						erdto.setCountry(jbt.getString("country"));
						erdto.setCreateDate(jbt.getString("createDate"));
						erdto.setCreateVipId(jbt.getLong("createVipId"));
						erdto.setCreateVipName(jbt.getString("createVipName"));
						erdto.setCreateVipPhone(jbt.getString("createVipPhone"));
						erdto.setDistrict(jbt.getString("district"));
						erdto.setErShouTypeId(jbt.getLong("erShouTypeId"));
						erdto.setErShouTypeName(jbt.getString("erShouTypeName"));
						erdto.setHangyeId(jbt.getLong("hangyeId"));
						erdto.setHangyeName(jbt.getString("hangyeName"));
						erdto.setId(jbt.getLong("id"));
						erdto.setIndexNum(jbt.getLong("indexNum"));
						erdto.setImage1(jbt.getString("image1"));
						erdto.setImage2(jbt.getString("image2"));
						erdto.setImage3(jbt.getString("image3"));
						erdto.setImage4(jbt.getString("image4"));
						erdto.setMessage(jbt.getString("message"));
						erdto.setPass(jbt.getInt("pass"));
						erdto.setPassMessage(jbt.getString("passMessage"));
						erdto.setPhone(jbt.getString("phone"));
						erdto.setPrice(jbt.getString("price"));
						erdto.setProvince(jbt.getString("province"));
						erdto.setTitle(jbt.getString("title"));
						erdto.setTouXiangImage(jbt.getString("touXiangImage"));
						erdto.setUserTableId(jbt.getLong("userTableId"));
						ershouList.add(erdto);
					}
					map.put("data", ershouList);
					list.add(map);
				}else{
					map.put("info", person.getString("info"));
					map.put("success", person.getString("success"));
				}
			}
			
			}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				map.put("success", false);
				list.add(map);
			}
			return list;
	}
	@Override
	public List<Map<String, Object>> getGame(String nowpage, String countNum,
			String type, String id) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", id));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.getGame, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			Object islogin = null;
			if (!b) {
				if (islogin != null) { // 状态不为空，登录错误
					map.put("login", false);
				} else {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.getGame(nowpage, countNum, type, id);
				}
			} else {
				//解析data
				if(id != null){
					map.put("info", person.getString("info"));
					map.put("success", person.getString("success"));
				}
			}
			
			}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				map.put("success", false);
				list.add(map);
			}
			return list;
	}
	@Override
	public List<Map<String, Object>> getConpanyGame(String nowpage,
			String countNum, String type, String id) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("nowpage", nowpage));
		params.add(new BasicNameValuePair("countNum", countNum));
		params.add(new BasicNameValuePair("type", type));
		params.add(new BasicNameValuePair("id", id));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.getConpanyGame, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			Object islogin = null;
			if (!b) {
				if (islogin != null) { // 状态不为空，登录错误
					map.put("login", false);
				} else {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.getConpanyGame(nowpage, countNum, type, id);
				}
			} else {
				if(id != null){
					map.put("info", person.getString("info"));
					map.put("success", person.getString("success"));
				}
			}
			
			}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				map.put("success", false);
				list.add(map);
			}
			return list;
	}
	@Override
	public List<Map<String, Object>> getGonggao(String nowpage,
			String countNum, String type, String id) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("nowpage", nowpage));
		params.add(new BasicNameValuePair("countNum", countNum));
		params.add(new BasicNameValuePair("type", type));
		params.add(new BasicNameValuePair("id", id));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = HttpClient.doPost(QNPermissionApi.mainLink
					+ QNPermissionApi.getGonggao, params);
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			Boolean b = person.getBoolean("success");
			Object islogin = null;
			if (!b) {
				if (islogin != null) { // 状态不为空，登录错误
					map.put("login", false);
				} else {
					islogin = person.get("stute");
					loginAjax(username, password);
					return this.getGonggao(nowpage, countNum, type, id);
				}
			} else {
				//解析data 
				if(type == null){
					JSONArray gaoList = person.getJSONArray("data");
					List<Object> gao = new ArrayList<Object>();
					for(int i = 0;i<gaoList.length(); i++){
						JSONObject jbt = gaoList.getJSONObject(i);
						VipGonggaoDto gaodto = new VipGonggaoDto();
						gaodto.setConpanyId(jbt.getLong("conpanyId"));
						gaodto.setGonggaoContent(jbt.getString("GonggaoContent"));
						gaodto.setId(jbt.getLong("id"));
						gaodto.setImageLink(jbt.getString("imageLink"));
						gaodto.setSendDate(jbt.getString("sendDate"));
						gaodto.setVideoLink(jbt.getString("videoLink"));
						gao.add(gaodto);
					}
					map.put("data", gao);
					list.add(map);
				}
			}
			
			}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				map.put("success", false);
				list.add(map);
			}
			return list;
	}
}
