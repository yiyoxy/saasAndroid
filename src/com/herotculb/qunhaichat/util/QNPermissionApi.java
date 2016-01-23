package com.herotculb.qunhaichat.util;

import java.util.List;
import java.util.Map;

public interface QNPermissionApi {
	public static final String mainLink="http://www.xt12306.com";
	//登录
	public static final String loginAjax = "/loginAjax"; 
	/**
	 * 登录方法  返回list map map内读取两个参数，
	 * 1.sucess ----false,true
	 * 2.info--------错误信息，成功信息
	 */
	public List<Map<String,Object>> loginAjax(String username,String password);
	//注册
	public static final String createConpany = "/createConpany"; 
	public List<Map<String,Object>> createConpany(String province,String city,
			String district,String conpanyName,String softAdminName,
			String softAdminPhone,String conpanyType,String conpanyType_id,
			String conpanyAdminEmail,String conpanyPinYin);
	//获取行业
	public static final String getHangye = "/getHangye";
	public List<Map<String,Object>> getHangye();
	//获取权限
	public static final String backManagerajax = "/backManagerajax"; 
	/**
	 * 获取账户权限，
	 * 返回类型   List<Map<String, Object>>
	 * 可读取参数
	 * 1. success -----false,true
	 * 2.info-----错误信息，只有错误信息，正确时不需要读取
	 * 3.map内的get名
	 * 	3.1 conpany  公司信息 是conpanyDTO类型
	 *  3.2 user 登录的账户信息 是UserDTO 类型
	 *  3.3 crm boolean 类型 false true  代表主功能项下是否要子项目 <客户关系管理>
	 *  3.4 goods  boolean 类型 false true  代表主功能项下是否要子项目 <库存管理>
	 *  3.5 hr  boolean 类型 false true  代表主功能项下是否要子项目 <员工管理>
	 *  3.5 system  boolean 类型 false true  代表主功能项下是否要子项目 <系统管理>
	 *  3.6 weixin  boolean 类型 false true  代表主功能项下是否要子项目 <微信管理>
	 *  3.7 messageInfo  boolean 类型 false true  代表主功能项下是否要子项目 <短信平台>
	 *  ps:在以上get名 后加list 则取得大项下的子项 类型为 List<PermissionLoadDTO>
	 *  stute 如果存在并且为 1 则是登录超时
	 */
	public List<Map<String,Object>> backManagerajax();
	//修改密码
	public static final String ajaxchangePassword = "/ajaxchangePassword";
	//外勤跟踪
		public static final String locationUp = "/hr/location/locationUp";
	public List<Map<String,Object>> ajaxchangePassword(String old,String newp,
			String renewp);
	//组管理
	public static final String group = "/Group/createGroup"; //创建组/部门权限权限
	/**
	 *   创建组，返回map 
	 *   success true false
	 *   info  提示字符串
	 *   stute 如果存在并且为 1 则是登录超时
	 * @param groupId
	 * @param groupName
	 * @param groupMarks
	 * @return
	 */
	public List<Map<String,Object>> getCreateGroup(String groupId,
			String groupName,String groupMarks);
	public static final String userGroup = "/Group/getUserInGroup"; //获取用户所在组权限
	public List<Map<String,Object>> getMyGroup(String groupName);
	public static final String allGroup = "/Group/getAllGroup"; //获取所有组列表权限
	public List<Map<String,Object>> getAllGroup(String nowpage,String countNum,
			String groupId,String groupName);
	public static final String conpanyGroup = "/Group/getGroupConpanyUser"; //获取一个组的所有员工列表权限
	public List<Map<String,Object>> getGroupConpanyUser(String nowpage,String countNum,
			String groupId,String groupName,String trueName);
	public static final String conpanyUserGroup = "/Group/insertConpanyUserToGroup"; //向组内添加一个成员权限
	public List<Map<String,Object>> insertConpanyUserToGroup(String userId,String groupId);
	public static final String conpanyUserDelGroup = "/Group/deleteConpanyUserFormGroup"; //从组中删除成员权限
	public List<Map<String,Object>> deleteConpanyUserFormGroup(String userId,String groupId);
	public static final String delGroup = "/Group/deleteGroup"; //删除组
	public List<Map<String,Object>> deleteGroup(String groupId);
	public static final String updateGroupInfo = "/Group/updateGroupInfo"; //编辑组信息
	public List<Map<String,Object>> updateGroupInfo(String groupId,String groupName,
			String groupMarks);
	//权限管理
	public static final String lookupPremissionAll = "/Permission/lookupPremissionAll"; //查看系统权限的权限
	public List<Map<String,Object>> lookupPremissionAll(String name);
	public static final String lookupPremissionMain = "/Permission/lookupPremissionMain"; //查看系统权限的权限【主要】
	public List<Map<String,Object>> lookupPremissionMain(String id,String name);
	public List<Map<String,Object>> lookupPremissionMain(String groupId,String name,String id);
	//向角色分配权限的权限
	public static final String addPermissionToRole = "/RolePermission/addPermissionToRole"; //向角色添加权限的权限
	public List<Map<String,Object>> addPermissionToRole(String isAll,String permissionId,String roleId);
	public static final String deletePermissionToRole = "/RolePermission/deletePermissionToRole"; //移除所在组角色的权限
	public List<Map<String,Object>> deletePermissionToRole(String permissionId,String roleId);
	public static final String addRole = "/RolePermission/addRole"; //添加所在组角色
	public List<Map<String,Object>> addRole(String name,String marks,String groupId);
	public static final String deleteRole = "/RolePermission/deleteRole"; //删除所在组的角色
	public List<Map<String,Object>> deleteRole(String roleId,String groupId);
	public static final String lookupRoleAll = "/RolePermission/lookupRoleAll"; //查看所有角色
	public List<Map<String,Object>> lookupRoleAll(String groupId,String roleName);
	public static final String lookupRoleByUser = "/RolePermission/lookupRoleByUser"; //查看一个用户在该组内担当的角色
	public List<Map<String,Object>> lookupRoleByUser(String userid,String groupid);
	public static final String lookupPremissionByRole = "/RolePermission/lookupPremissionByRole"; //查看角色的权限列表
	public List<Map<String,Object>> lookupPremissionByRole(String name,String groupId,String roleId);
	public static final String addRoleToConpanyUser = "/RolePermission/addRoleToConpanyUser"; //添加角色到用户
	public List<Map<String,Object>> addRoleToConpanyUser(String roleid,String groupId,String userId);
	public static final String deleteRoleToConpanyUser = "/RolePermission/deleteRoleToConpanyUser"; //删除角色到用户
	public List<Map<String,Object>> deleteRoleToConpanyUser(String roleid,String groupId,String userId);
	// crm权限
	public static final String createChance = "/crm/function/createChance"; //创建机会
	public List<Map<String,Object>> createChance(String CustomerName,
			String CustomerAddress, String CustomerType, String CustomerLevel,
			String CustomerMark, String CreateManMark, String NotesUserId
			,String State);
	public static final String updateChance = "/crm/function/updateChance"; //修改机会
	public List<Map<String,Object>> updateChance(String CustomerName,String CustomerAddress,
			String CustomerType,String CustomerLevel,String CustomerMark,String CreateManMark,
			String NotesUserId,String State,String ChanceId);
	public static final String DeleteChance = "/crm/function/DeleteChance"; //删除机会
	public List<Map<String,Object>> DeleteChance(String ChanceId);
	public static final String allocationChance = "/crm/function/allocationChance"; //分配机会
	public List<Map<String,Object>> allocationChance(String NotesUserId,String ChanceId);
	public static final String createFlow = "/crm/function/createFlow"; //创建流程
	public List<Map<String,Object>> createFlow(String chanceId,String name,String jsonString);
	public static final String updateFlow = "/crm/function/updateFlow"; //修改流
	public List<Map<String,Object>> updateFlow(String name,String flowId);
	public static final String deleteFlow = "/crm/function/deleteFlow"; //删除流
	public List<Map<String,Object>> deleteFlow(String id);
	public static final String createFlowItem = "/crm/function/createFlowItem"; //创建流程项
	public List<Map<String,Object>> createFlowItem(String type,String startDate,String endDate);
	public static final String updateFlowItem = "/crm/function/updateFlowItem"; //更新流节点
	public List<Map<String,Object>> updateFlowItem(String type,String startDate,String endDate);
	public static final String forwordFlowItem = "/crm/function/forwordFlowItem"; //从定向流节点
	public List<Map<String,Object>> forwordFlowItem(String type,String startDate,String endDate);
	public static final String updateLinkMan = "/crm/function/updateLinkMan"; //更新联系人
	public List<Map<String,Object>> updateLinkMan(String id,String linkManName,String linkManSex,
			String linkManPhone,String linkManJob,String linkManMark,String linkManBirthday);
	public static final String changeState = "/crm/function/changeState"; //改变状态
	public List<Map<String,Object>> changeState(String state,String chanceId);
	public static final String queryMyChance = "/crm/function/queryMyChance"; //查询自己创建的机会
	public List<Map<String,Object>> queryMyChance(String nowpage,String countNum,String ChanceName);
	public static final String getChance = "/crm/function/getChance"; //查看单个机会详情的权限
	public List<Map<String,Object>> getChance(String ChanceName);
	public static final String updateMyCreateChance = "/crm/function/updateMyCreateChance"; //修改用户自己创建的机会
	public List<Map<String,Object>> updateMyCreateChance(String CustomerName,String CustomerAddress,
			String CustomerType,String CustomerLevel,String CustomerMark,String CreateManMark,
			String NotesUserId,String State,String ChanceId);
	public static final String allocationMyCreateChance = "/crm/function/allocationMyCreateChance"; //分配自己创建的机会
	public List<Map<String,Object>> allocationMyCreateChance(String NotesUserId,String ChanceId);
	public static final String DeleteMyCreateChance = "/crm/function/DeleteMyCreateChance"; //删除自己创造的机会
	public List<Map<String,Object>> DeleteMyCreateChance(String ChanceId);
	public static final String queryLinkMan = "/crm/function/queryLinkMan"; //查询机会/客户的联系人权限
	public List<Map<String,Object>> queryLinkMan(String linkManName,String chanceId,String nowpage,String countNum);
	public static final String addLinkMan = "/crm/function/addLinkMan"; //添加联系人权限
	public List<Map<String,Object>> addLinkMan(String chanceId,String linkManName,String linkManSex,
			String linkManPhone,String linkManJob,String linkManMark,String linkManBirthday);
	public static final String getLinkManById = "/crm/function/getLinkManById"; //获取一个联系人的详细信息权限
	public List<Map<String,Object>> getLinkManById(String linkmanId);
	public static final String deleteLinkMan = "/crm/function/deleteLinkMan"; //删除联系人权限
	public List<Map<String,Object>> deleteLinkMan(String id);
	public static final String queryNotes = "/crm/function/queryNotes"; //查询开发记录
	public List<Map<String,Object>> queryNotes(String linkManName,String chanceId,String nowpage,String countNum);
	public static final String startNotes = "/crm/function/startNotes"; //对一个机会进行创建一个开发记录
	public List<Map<String,Object>> startNotes(String chanceId);
	public static final String addNotesItem = "/crm/function/addNotesItem"; //向开发记录添加纪录项
	public List<Map<String,Object>> addNotesItem(String chanceId,String NotesId,String Title,
			String notesAddress,String NotesDriver,String Marks);
	public static final String queryNotesItem = "/crm/function/queryNotesItem"; //查询开发记录详情
	public List<Map<String,Object>> queryNotesItem(String NotesId,String nowpage,String countNum,String chanceId);
	public static final String queryAllChance = "/crm/function/queryAllChance"; //查询所有机会的权限
	public List<Map<String,Object>> queryAllChance(String nowpage,String countNum,String ChanceName);
	public static final String queryFlow = "/crm/function/queryFlow"; //查询建议流程的权限
	public List<Map<String,Object>> queryFlow(String name,String chanceId,String nowpage,String countNum);
	public static final String queryFlowItem = "/crm/function/queryFlowItem"; //查看建议流程
	public List<Map<String,Object>> queryFlowItem(String id);
	public static final String queryMyCustemor = "/crm/function/queryMyCustemor"; //查询由自己参与或创建的客户查询
	public List<Map<String,Object>> queryMyCustemor(String nowpage,String countNum,String ChanceName);
	public static final String queryToMyChance = "/crm/function/queryToMyChance"; //查询分配给自己的机会
	public List<Map<String,Object>> queryToMyChance(String nowpage,String countNum,String ChanceName);
	public static final String query = "/crm/function/query"; //查询图表权限
	public List<Map<String,Object>> query(String type,String startDate,String endDate);
	//库存管理权限
	public static final String addGoods = "/goods/function/addGoods"; //添加商品
	public List<Map<String,Object>> addGoods(String goodsName,String goodsNum,String goodsType,
			String Price,String inPrice,String score,String Spell,
			String goodsModel,String goodsSourceId,String storehouseId);
	public static final String addGoodSource = "/goods/function/addGoodSource"; //添加供货商
	public List<Map<String,Object>> addGoodSource(String name,String address);
	public static final String queryGoodsSource = "/goods/function/queryGoodsSource"; //查找供货商
	public List<Map<String,Object>> queryGoodsSource(String name,String nowpage,String countNum);
	public static final String deleteGoodsSource = "/goods/function/deleteGoodsSource"; //删除供货商
	public List<Map<String,Object>> deleteGoodsSource(String id);
	public static final String getGoodsSource = "/goods/function/getGoodsSource"; //查看供货商详情
	public List<Map<String,Object>> getGoodsSource(String id);
	public static final String updateGoodSource = "/goods/function/updateGoodSource"; //更新供货商详情
	public List<Map<String,Object>> updateGoodSource(String id,String name,String address);
	public static final String createOrder = "/goods/function/createOrder"; //创建销售订单
	public List<Map<String,Object>> createOrder(String Name,String data,String id,
			String type,String chance);
	public static final String queryOrder = "/goods/function/queryOrder"; //查询销售订单
	public List<Map<String,Object>> queryOrder(String num,String name,String chanceid,
			String endDate,String startDate,String nowpage,String countNum,boolean isNum);
	public static final String deleteOrder = "/goods/function/deleteOrder"; //删除销售订单
	public List<Map<String,Object>> deleteOrder(String id);
	public static final String queryOrderItem = "/goods/function/queryOrderItem"; //查看销售订单内容
	public List<Map<String,Object>> queryOrderItem(String id);
	public static final String queryInOrderItem = "/goods/function/queryInOrderItem"; //查看进货订单内容
	public List<Map<String,Object>> queryInOrderItem(String id);
	public static final String OrderInStore = "/goods/function/OrderInStore"; //入库销售订单权限
	public List<Map<String,Object>> OrderInStore(String id);
	public static final String InOrderInStore = "/goods/function/InOrderInStore"; //入库进货单
	public List<Map<String,Object>> InOrderInStore(String id);
	public static final String createStoreHouse = "/goods/function/createStoreHouse"; //创建仓库
	public List<Map<String,Object>> createStoreHouse(String name,String address,String tel,String userid);
	public static final String updateStoreHouse = "/goods/function/updateStoreHouse"; //更新仓库
	public List<Map<String,Object>> updateStoreHouse(String name,String address,String tel,
			String userid,String id);
	public static final String deleteStoreHouse = "/goods/function/deleteStoreHouse"; //删除仓库
	public List<Map<String,Object>> deleteStoreHouse(String id);
	public static final String StoreHouseToStoreHouse = "/goods/function/StoreHouseToStoreHouse"; //合并仓库
	public List<Map<String,Object>> StoreHouseToStoreHouse(String storeHouseId,String toStoreHouseId);
	public static final String createInOrder = "/goods/function/createInOrder"; //创建进货单
	public List<Map<String,Object>> createInOrder(String Name,String data,String id,String type);
	public static final String deleteInOrder = "/goods/function/deleteInOrder"; //删除进货单
	public List<Map<String,Object>> deleteInOrder(String id);
	public static final String queryInOrder = "/goods/function/queryInOrder"; //查找进货单
	public List<Map<String,Object>> queryInOrder(String num,String name,String endDate,
			String startDate,String nowpage,String countNum,boolean isNum);
	public static final String addGoodSourceLinkMan = "/goods/function/addGoodSourceLinkMan"; //添加供货商联系人
	public List<Map<String,Object>> addGoodSourceLinkMan(String id,String linkManBirthday,String linkname,String phone);
	public static final String queryGoodsSourceLinkman = "/goods/function/queryGoodsSourceLinkman"; //查询供货商联系人
	public List<Map<String,Object>> queryGoodsSourceLinkman(String name,String nowpage,String countNum);
	public static final String updateGoodSourceLinkMan = "/goods/function/updateGoodSourceLinkMan"; //更新供应商联系人权限
	public List<Map<String,Object>> updateGoodSourceLinkMan(String id,String linkManBirthday,String linkname,String phone);
	public static final String deleteGoodsSourceLinkman = "/goods/function/deleteGoodsSourceLinkman"; //删除供货商联系人
	public List<Map<String,Object>> deleteGoodsSourceLinkman(String id);
	public static final String getGoodsSourceLinkMan = "/goods/function/getGoodsSourceLinkMan"; //查询供货商联系人详情
	public List<Map<String,Object>> getGoodsSourceLinkMan(String id);
	public static final String queryStoreHouse = "/goods/function/queryStoreHouse"; //查询仓库
	public List<Map<String,Object>> queryStoreHouse(String name,String nowpage,String countNum);
	public static final String getStoreHouse = "/goods/function/getStoreHouse"; //获取单个仓库信息
	public List<Map<String,Object>> getStoreHouse(String id);
	public static final String queryGoods = "/goods/function/queryGoods"; //查询库存
	public List<Map<String,Object>> queryGoods(String name,String spell,String nowpage,String countNum);
	public List<Map<String,Object>> queryGoods(String codeid);
	public static final String getGoods = "/goods/function/getGoods"; //获取单个货物信息
	public List<Map<String,Object>> getGoods(String id);
	public static final String updatePrice = "/goods/function/updatePrice"; //修改商品价格
	public List<Map<String,Object>> updatePrice(String id,String updateprice);
	public static final String queryGoodsLog = "/goods/function/queryGoodsLog"; //查看商品操作日志权限
	public List<Map<String,Object>> queryGoodsLog(String id,String nowpage,String countNum);
	public static final String querySaleChat = "/goods/function/char/querySaleChat"; //查看货物的销售图表权限
	public List<Map<String,Object>> querySaleChat(String startDate,String endDate,String id);
	public static final String queryInGoods = "/goods/function/char/queryInGoods"; //查看货物的进货统计图表权限
	public List<Map<String,Object>> queryInGoods(String startDate,String endDate,String id);
	public static final String priceChar = "/goods/function/char/priceChar"; //查看货物的价格趋势统计图表权限
	public List<Map<String,Object>> priceChar(String startDate,String endDate,String id);
	public static final String queryStoreHouseChat = "/goods/function/char/queryStoreHouseChat"; //查询货物储存仓库图表权限
	public List<Map<String,Object>> queryStoreHouseChat(String id);
	//系统通用权限
	//查看信息连接项权限 在一些系统信息里，查看如，联系人，开发记录，等
	public static final String getObject = "/crm/function/getObject"; 
	public List<Map<String,Object>> getObject(String id,String type);
	//hr权限开始
	public static final String addCustemmer = "/hr/function/addCustemmer"; //添加员工账户
	public List<Map<String,Object>> addCustemmer(String username,String password,String name,
			String phone,String email,String useLogin,
			String image,String idimage,String stute,
			String idnum,String sex,String marks,
			String address,String price);
	public static final String queryCustemmer = "/hr/function/queryCustemmer"; //查询员工信息
	public List<Map<String,Object>> queryCustemmer(String nowpage,String countNum,String trueName);
	public static final String lookCustemmerInfo = "/hr/function/lookCustemmerInfo"; //查看员工详情
	public List<Map<String,Object>> lookCustemmerInfo(String id);
	public static final String updateCustemmerInfo = "/hr/function/updateCustemmerInfo"; //修改员工信息权限
	public List<Map<String,Object>> updateCustemmerInfo(String id,String name,String value);
	public static final String getCustemmerMeetingInfo = "/hr/function/getCustemmerMeetingInfo"; //获取全公司员工签到信息
	public List<Map<String,Object>> getCustemmerMeetingInfo(String date);
	public static final String getUpdateMeetingInfo = "/hr/function/getUpdateMeetingInfo"; //修改签到状况
	public List<Map<String,Object>> getUpdateMeetingInfo(String type,String value,String id);
	public static final String getMeetingSet = "/hr/function/getMeetingSet"; //设置签到时间
	public List<Map<String,Object>> getMeetingSet(String ip,String uptime,String downtime);
	public static final String getMeetingSetInfo = "/hr/function/getMeetingSetInfo"; //获取签到时间信息以及历史
	public List<Map<String,Object>> getMeetingSetInfo();
	public static final String getPerForMance = "/hr/function/getPerForMance"; //获取一段时间的绩效信息
	public List<Map<String,Object>> getPerForMance(String startDate,String endDate);
	public static final String meeting = "/hr/function/meeting"; //签到权限
	public List<Map<String,Object>> meeting(String marks,String type,String latitude,String longitude);
	//页面访问权限
	public static final String main = "/crm/page/main"; //客户管理->客户机会概览页面
	public List<Map<String,Object>> main(String type,String startDate,String endDate);
	public static final String myCreateCustomChance = "/crm/page/myCreateCustomChance"; //客户管理->我创建的客户机会页面
	public List<Map<String,Object>> myCreateCustomChance(String type,String startDate,String endDate);
	public static final String toMyCustomChance = "/crm/page/toMyCustomChance"; //客户管理->分配给我的客户机会页面
	public List<Map<String,Object>> toMyCustomChance(String type,String startDate,String endDate);
	public static final String MyCustomManager = "/crm/page/MyCustomManager"; //客户管理->我的客户管理页面
	public List<Map<String,Object>> MyCustomManager(String type,String startDate,String endDate);
	public static final String mainGoods = "/goods/page/main"; //客户管理->仓库概况页面
	public List<Map<String,Object>> mainGoods(String type,String startDate,String endDate);
	public static final String queryData = "/goods/page/queryData"; //客户管理->库存查询页面
	public List<Map<String,Object>> queryData(String type,String startDate,String endDate);
	public static final String inGoods = "/goods/page/inGoods"; //客户管理->进货管理页面
	public List<Map<String,Object>> inGoods(String type,String startDate,String endDate);
	public static final String outGoods = "/goods/page/outGoods"; //客户管理->销售管理页面
	public List<Map<String,Object>> outGoods(String type,String startDate,String endDate);
	public static final String storehouse = "/goods/page/storehouse"; //客户管理->仓库信息页面
	public List<Map<String,Object>> storehouse(String type,String startDate,String endDate);
	public static final String GoodSource = "/goods/page/GoodSource"; //客户管理->进货商信息页面
	public List<Map<String,Object>> GoodSource(String type,String startDate,String endDate);
	public static final String mainHr = "/hr/page/main"; //员工管理->概览页面
	public List<Map<String,Object>> mainHr(String type,String startDate,String endDate);
	public static final String custemr = "/hr/page/custemr"; //员工管理->员工账号管理页面
	public List<Map<String,Object>> custemr(String type,String startDate,String endDate);
	public static final String meetingHr = "/hr/page/meeting"; //员工管理->签到管理页面
	public List<Map<String,Object>> meetingHr(String type,String startDate,String endDate);
	public static final String performance = "/hr/page/performance"; //员工管理->绩效管理页面
	public List<Map<String,Object>> performance(String type,String startDate,String endDate);
	public static final String systemPermission = "/itempage/systemPermission"; //系统权限管理->权限管理页面
	public List<Map<String,Object>> systemPermission(String type,String startDate,String endDate);
	public static final String mainWeiXin = "/weixin/page/main"; //微信管理->概览页面
	public List<Map<String,Object>> mainWeiXin(String type,String startDate,String endDate);
	public static final String weixin_UserId_Set = "/weixin/page/main"; //微信管理->微信公众设置页面
	public List<Map<String,Object>> weixin_UserId_Set(String type,String startDate,String endDate);
	public static final String weixin_VIP_set = "/weixin/page/weixin_VIP_set"; //微信管理->会员制度设置页面
	public List<Map<String,Object>> weixin_VIP_set(String type,String startDate,String endDate);
	public static final String weixin_Game_set = "/weixin/page/weixin_Game_set"; //微信管理->活动与游戏管理页面
	public List<Map<String,Object>> weixin_Game_set(String type,String startDate,String endDate);
	public static final String weixin_convert_set = "/weixin/page/weixin_convert_set"; //微信管理->序列号兑换页面
	public List<Map<String,Object>> weixin_convert_set(String type,String startDate,String endDate);
	public static final String weixin_Model_set = "/weixin/page/weixin_Model_set"; //微信管理->模板设置页面
	public List<Map<String,Object>> weixin_Model_set(String type,String startDate,String endDate);
	public static final String weixin_Model_set_map = "/weixin/page/main"; //微信管理->设置店铺地址页面
	public List<Map<String,Object>> weixin_Model_set_map(String type,String startDate,String endDate);
	public static final String weixin_model_goodsManager = "/weixin/page/weixin_model_goodsManager"; //微信管理->微信商品管理
	public List<Map<String,Object>> weixin_model_goodsManager(String type,String startDate,String endDate);
	public static final String weixin_model_order = "/weixin/page/weixin_model_order"; //微信管理->微信订单页面
	public List<Map<String,Object>> weixin_model_order(String type,String startDate,String endDate);
	public static final String queryAllChanceList = "/crm/page/queryAllChanceList"; //客户管理->查询所有客户机会页面
	public List<Map<String,Object>> queryAllChanceList(String type,String startDate,String endDate);
	public static final String mainWifi = "/wifiController/main"; //微信管理->会员wifi
	public List<Map<String,Object>> mainWifi(String type,String startDate,String endDate);
	//短信管理->短信设置 短信管理->短信设置，设置都在什么情况下自动发短信，以及发送什么短信，页面权限
	public static final String mainMess = "/messageinfo/main"; //
	public List<Map<String,Object>> mainMess(String type,String startDate,String endDate);
	public static final String vipsend = "/messageinfo/vipsend"; //短信管理->会员短信
	public List<Map<String,Object>> vipsend(String type,String startDate,String endDate);
	public static final String messagetemp = "/messageinfo/messagetemp"; //短信管理->短信模板	
	public List<Map<String,Object>> messagetemp(String type,String startDate,String endDate);
	public static final String messagelog = "/messageinfo/messagelog"; //短信管理->短信发送的历史
	public List<Map<String,Object>> messagelog(String type,String startDate,String endDate);
	public static final String getWeXinInfo = "/weixin/getWeXinInfo"; //微信管理->获取微信账户的信息
	public List<Map<String,Object>> getWeXinInfo();
	public static final String updateWeXinInfo = "/weixin/updateWeXinInfo"; //微信管理->设置微信账户的信息
	public List<Map<String,Object>> updateWeXinInfo(String appid,String appSecret,String tokens);
	public static final String updataMenuToWeiXin = "/weixin/updataMenuToWeiXin"; //微信管理->设置同步至微信账户的菜单
	public List<Map<String,Object>> updataMenuToWeiXin();
	public static final String getMenu = "/weixin/getMenu"; //微信管理->查看微信公众账号菜单权限
	public List<Map<String,Object>> getMenu(String id);
	public static final String addMenu = "/weixin/addMenu"; //微信管理->添加微信公众账号菜单权限
	public List<Map<String,Object>> addMenu(String id,String name,String url,String key);
	public static final String deleteMenu = "/weixin/deleteMenu"; //微信管理->删除微信公众账号菜单权限
	public List<Map<String,Object>> deleteMenu(String id);
	public static final String editMenu = "/weixin/editMenu"; //微信管理->编辑微信公众账号菜单权限
	public List<Map<String,Object>> editMenu(String id,String name,String url,String key);
	public static final String getMenuItem = "/weixin/getMenuItem"; //微信管理->编辑微信公众账号菜单权限
	public List<Map<String,Object>> getMenuItem(String id);
	public static final String getImage = "/weixin/getImage"; //微信管理->查看图片资源权限
	public List<Map<String,Object>> getImage();
	public static final String getVoice = "/weixin/getVoice"; //微信管理->查看声音资源权限
	public List<Map<String,Object>> getVoice();
	public static final String getVideo = "/weixin/getVideo"; //微信管理->查看视频资源权限
	public List<Map<String,Object>> getVideo();
	public static final String getText = "/weixin/getText"; //微信管理->查看文本资源权限
	public List<Map<String,Object>> getText();
	public static final String getMusic = "/weixin/getMusic"; //微信管理->查看音乐资源权限
	public List<Map<String,Object>> getMusic();
	public static final String getImageText = "/weixin/getImageText"; //微信管理->查看图片+文本资源权限
	public List<Map<String,Object>> getImageText();
	public static final String addImage = "/weixin/addImage"; //微信管理->添加图片资源权限
	public List<Map<String,Object>> addImage(String name,String mediaId);
	public static final String addVideo = "/weixin/addVideo"; //微信管理->添加视频资源权限
	public List<Map<String,Object>> addVideo(String name,String mediaId,String title,String description);
	public static final String addVoice = "/weixin/addVoice"; //微信管理->添加声音资源权限
	public List<Map<String,Object>> addVoice(String name,String mediaId);
	public static final String addText = "/weixin/addText"; //微信管理->添加文本资源权限
	public List<Map<String,Object>> addText(String name,String content);
	public static final String addMusic = "/weixin/addMusic"; //微信管理->添加音乐资源权限
	public List<Map<String,Object>> addMusic(String name,String title,String description,
			String hQMusicUrl,String thumbMediaId,String musicURL);
	public static final String addImageText = "/weixin/addImageText"; //微信管理->添加文本+图片资源权限
	public List<Map<String,Object>> addImageText(String name,String title,String description,
			String picUrl,String url);
	public static final String getWeiXinReSend = "/weixin/getWeiXinReSend"; //微信管理->查看单个已定义的资源信息
	public List<Map<String,Object>> getWeiXinReSend(String id);
	public static final String updateImage = "/weixin/updateImage"; //微信管理->修改图片资源的权限
	public List<Map<String,Object>> updateImage(String id,String name,String mediaId);
	public static final String updateVideo = "/weixin/updateVideo"; //微信管理->修改视频资源的权限
	public List<Map<String,Object>> updateVideo(String id,String name,String mediaId,
			String title,String description);
	public static final String updateVoice = "/weixin/updateVoice"; //微信管理->修改声音资源的权限
	public List<Map<String,Object>> updateVoice(String id,String name,String mediaId);
	public static final String updateText = "/weixin/updateText"; //微信管理->修改文本资源的权限
	public List<Map<String,Object>> updateText(String id,String name,String content);
	public static final String updateMusic = "/weixin/updateMusic"; //微信管理->修改音乐资源的权限
	public List<Map<String,Object>> updateMusic(String id,String name,String title,String description,
			String hQMusicUrl,String thumbMediaId,String musicURL);
	public static final String updateImageText = "/weixin/updateImageText"; //微信管理->修改文本+图片资源的权限
	public List<Map<String,Object>> updateImageText(String id,String name,String title,String description,
			String picUrl,String url);
	public static final String deleteReSend = "/weixin/deleteReSend"; //微信管理->删除已定义的资源
	public List<Map<String,Object>> deleteReSend(String id);
	public static final String getAutoReSend_Text = "/weixin/getAutoReSend_Text"; //微信管理->获取文本自动回复列表
	public List<Map<String,Object>> getAutoReSend_Text();
	public static final String getAutoReSend_Image = "/weixin/getAutoReSend_Image"; //微信管理->获取图片自动回复列表
	public List<Map<String,Object>> getAutoReSend_Image();
	public static final String getAutoReSend_Link = "/weixin/getAutoReSend_Link"; //微信管理->获取网址自动回复列表
	public List<Map<String,Object>> getAutoReSend_Link();
	public static final String getAutoReSend_Location = "/weixin/getAutoReSend_Location"; //微信管理->获取地理位置自动回复列表
	public List<Map<String,Object>> getAutoReSend_Location();
	public static final String getAutoReSend_Event = "/weixin/getAutoReSend_Event"; //微信管理->获取手指点击菜单时自动回复列表
	public List<Map<String,Object>> getAutoReSend_Event();
	public static final String getAutoReSend_Video = "/weixin/getAutoReSend_Video"; //微信管理->获取视频自动回复列表
	public List<Map<String,Object>> getAutoReSend_Video();
	public static final String getAutoReSend_Voice = "/weixin/getAutoReSend_Voice"; //微信管理->获取语音自动回复列表
	public List<Map<String,Object>> getAutoReSend_Voice();
	public static final String addAutoReSend_Text = "/weixin/addAutoReSend_Text"; //微信管理->添加自动回复——用户发送类型：文本
	public List<Map<String,Object>> addAutoReSend_Text(String name,String content);
	public static final String addAutoReSend_Image = "/weixin/addAutoReSend_Image"; //微信管理->添加自动回复——用户发送类型：图片
	public List<Map<String,Object>> addAutoReSend_Image(String name,String content);
	public static final String addAutoReSend_Link = "/weixin/addAutoReSend_Link"; //微信管理->添加自动回复——用户发送类型：网址链接
	public List<Map<String,Object>> addAutoReSend_Link(String name,String content);
	public static final String addAutoReSend_Location = "/weixin/addAutoReSend_Location"; //微信管理->添加自动回复——用户发送类型：地理位置
	public List<Map<String,Object>> addAutoReSend_Location(String name,String content);
	public static final String addAutoReSend_Event = "/weixin/addAutoReSend_Event"; //微信管理->添加自动回复——用户发送类型：事件
	public List<Map<String,Object>> addAutoReSend_Event(String name,String content,String event);
	public static final String addAutoReSend_Video = "/weixin/addAutoReSend_Video"; //微信管理->添加自动回复——用户发送类型：视频
	public List<Map<String,Object>> addAutoReSend_Video(String name,String content);
	public static final String addAutoReSend_Voice = "/weixin/addAutoReSend_Voice"; //微信管理->添加自动回复——用户发送类型：语音
	public List<Map<String,Object>> addAutoReSend_Voice(String name,String content);
	public static final String getAutoReSend = "/weixin/getAutoReSend"; //微信管理->查看设定的自动回复详情
	public List<Map<String,Object>> getAutoReSend(String id);
	public static final String addWeiXinInfoToAutoResend = "/weixin/addWeiXinInfoToAutoResend"; //微信管理->向已设定的自动回复添加已经定义好的资源
	public List<Map<String,Object>> addWeiXinInfoToAutoResend(String id,String name,String info_name,String info_id);
	public static final String getAutoReSendItem = "/weixin/getAutoReSendItem"; //微信管理->获取设定自动回复的资源
	public List<Map<String,Object>> getAutoReSendItem(String id);
	public static final String deleteWeiXinInfoToAutoResend = "/weixin/deleteWeiXinInfoToAutoResend"; //微信管理->删除设定自动回复的资源
	public List<Map<String,Object>> deleteWeiXinInfoToAutoResend(String id);
	public static final String deleteWeiXinInfo = "/weixin/deleteWeiXinInfo"; //微信管理->删除自动回复
	public List<Map<String,Object>> deleteWeiXinInfo(String id);
	public static final String updateAutoReSend_Text = "/weixin/updateAutoReSend_Text"; //微信管理->修改自动回复——用户发送类型：文本
	public List<Map<String,Object>> updateAutoReSend_Text(String id,String name,String content);
	public static final String updateAutoReSend_Voice = "/weixin/updateAutoReSend_Voice"; //微信管理->修改自动回复——用户发送类型：语音
	public List<Map<String,Object>> updateAutoReSend_Voice(String id,String name,String content);
	public static final String updateAutoReSend_Image = "/weixin/updateAutoReSend_Image"; //微信管理->修改自动回复——用户发送类型：图片
	public List<Map<String,Object>> updateAutoReSend_Image(String id,String name,String content);
	public static final String updateAutoReSend_Link = "/weixin/updateAutoReSend_Link"; //微信管理->修改自动回复——用户发送类型：网址链接
	public List<Map<String,Object>> updateAutoReSend_Link(String id,String name,String content);
	public static final String updateAutoReSend_Location = "/weixin/updateAutoReSend_Location"; //微信管理->修改自动回复——用户发送类型：地理位置
	public List<Map<String,Object>> updateAutoReSend_Location(String id,String name,String content);
	public static final String updateAutoReSend_Event = "/weixin/updateAutoReSend_Event"; //微信管理->修改自动回复——用户发送类型：事件
	public List<Map<String,Object>> updateAutoReSend_Event(String id,String name,String content,String event);
	public static final String updateAutoReSend_Video = "/weixin/updateAutoReSend_Video"; //微信管理->修改自动回复——用户发送类型：视频
	public List<Map<String,Object>> updateAutoReSend_Video(String id,String name,String content);
	public static final String updateUse = "/weixin/updateUse"; //微信管理->自动回复开启与关闭
	public List<Map<String,Object>> updateUse(String id,String value);
	public static final String sendWenzhang = "/weixin/sendWenzhang"; //微信管理->创建文章
	public List<Map<String,Object>> sendWenzhang(String wenzhangname,String wenzhangcontent,String strjson);
	public static final String getWenzhang = "/weixin/getWenzhang"; //管理->查询单个文章详情
	public List<Map<String,Object>> getWenzhang(String id);
	public static final String updateWenzhang = "/weixin/updateWenzhang"; //微信管理->修改更新文章
	public List<Map<String,Object>> updateWenzhang(String id,String wenzhangname,String wenzhangcontent,String strjson);
	public static final String getWenzhangList = "/weixin/getWenzhangList"; //微信管理->查询文章列表	
	public List<Map<String,Object>> getWenzhangList(String name,String nowpage,String countNum);
	public static final String deleteWenzhang = "/weixin/deleteWenzhang"; //微信管理->删除文章的权限
	public List<Map<String,Object>> deleteWenzhang(String id);
	public static final String addDevice = "/wifiController/addDevice"; //微信管理->添加路由设备
	public List<Map<String,Object>> addDevice(String id,String name,String tokens,String open);
	public static final String addWifiRigister = "/wifiController/addWifiRigister"; //微信管理->修改wifi登录方式
	public List<Map<String,Object>> addWifiRigister(String id);
	public static final String updateDevice = "/wifiController/updateDevice"; //微信管理->修改路由设备
	public List<Map<String,Object>> updateDevice(String name,String tokens,String open,String id);
	public static final String deleteDevice = "/wifiController/deleteDevice"; //微信管理->删除路由设备
	public List<Map<String,Object>> deleteDevice(String id);
	public static final String lookLinkDevice = "/wifiController/lookLinkDevice"; //微信管理->查看链接路由的设备
	public List<Map<String,Object>> lookLinkDevice(String nowpage, String countNum,
			String id);
	public static final String getDeviceList = "/wifiController/getDeviceList"; //微信管理->获取路由列表
	public List<Map<String,Object>> getDeviceList();
	public static final String addMac = "/wifiController/addMac"; //微信管理->给上网设备禁网/允许
	public List<Map<String,Object>> addMac(String id);
	//微信活动权限开始
	public static final String getAwards = "/gameController/getAwards"; //微信游戏活动权限->奖品->查看奖品列表的权限
	public List<Map<String,Object>> getAwards();
	public static final String addAwards = "/gameController/addAwards"; //微信游戏活动权限->奖品->添加奖品的权限
	public List<Map<String,Object>> addAwards(String content,String marks);
	public static final String deleteAwards = "/gameController/deleteAwards"; //微信游戏活动权限->奖品->删除奖品的权限
	public List<Map<String,Object>> deleteAwards(String id);
	public static final String addDazhuanpan1 = "/gameController/addDazhuanpan1"; //微信游戏活动权限->大转盘->大转盘奖品1设置权限
	public List<Map<String,Object>> addDazhuanpan1(String text,String startDate,String endDate,
			String id,String num);
	public static final String addDazhuanpan2 = "/gameController/addDazhuanpan2"; //微信游戏活动权限->大转盘->大转盘奖品2设置权限
	public List<Map<String,Object>> addDazhuanpan2(String text,String startDate,String endDate,
			String id,String num);
	public static final String addDazhuanpan3 = "/gameController/addDazhuanpan3"; //微信游戏活动权限->大转盘->大转盘奖品3设置权限
	public List<Map<String,Object>> addDazhuanpan3(String text,String startDate,String endDate,
			String id,String num);
	public static final String getDaZhuanpanInfo = "/gameController/getDaZhuanpanInfo"; //微信游戏活动权限->大转盘->获取大转盘当前信息权限
	public List<Map<String,Object>> getDaZhuanpanInfo();
	public static final String updateGameDaZhuanPan = "/gameController/updateGameDaZhuanPan"; //微信游戏活动权限->大转盘->开启与关闭大转盘权限
	public List<Map<String,Object>> updateGameDaZhuanPan(String use,String type);
	public static final String setDazhuanpanJilv = "/gameController/setDazhuanpanJilv"; //微信游戏活动权限->大转盘->设置大转盘几率
	public List<Map<String,Object>> setDazhuanpanJilv(String value,String type);
	public static final String setDazhuanpanNum = "/gameController/setDazhuanpanNum"; //微信游戏活动权限->大转盘->设置大转盘每天微信关注者可玩次数
	public List<Map<String,Object>> setDazhuanpanNum(String value);
	public static final String setguaguakaJilv = "/gameController/setguaguakaJilv"; //微信游戏活动权限->刮刮卡->设置刮刮卡几率
	public List<Map<String,Object>> setguaguakaJilv(String value,String type);
	public static final String setguaguakaNum = "/gameController/setguaguakaNum"; //微信游戏活动权限->刮刮卡->设置刮刮卡每位微信关注者可以使用次数
	public List<Map<String,Object>> setguaguakaNum(String value);
	public static final String updateGameguaguaka = "/gameController/updateGameguaguaka"; //微信游戏活动权限->刮刮卡->刮刮卡开启关闭权限
	public List<Map<String,Object>> updateGameguaguaka(String value,String type);
	public static final String getguaguaka = "/gameController/getguaguaka"; //微信游戏活动权限->刮刮卡->获取刮刮卡设置信息权限
	public List<Map<String,Object>> getguaguaka();
	public static final String addAwardsToguaguaka = "/gameController/addAwardsToguaguaka"; //微信游戏活动权限->刮刮卡->给刮刮卡设置奖品权限	
	public List<Map<String,Object>> addAwardsToguaguaka(String value,String text,String id,
			String enddate,String startdate,String num);
	public static final String deleteGuaguaka = "/gameController/deleteGuaguaka"; //微信游戏活动权限->刮刮卡->删除刮刮卡奖品权限
	public List<Map<String,Object>> deleteGuaguaka(String id);
	//VIP权限
	public static final String getVip = "/weixin/vip/getVip"; //微信管理->会员管理的权限设置->查看会员列表
	public List<Map<String,Object>> getVip(String nowpage,String countNum,String name);
	public static final String getVipList = "/weixin/vip/getVipList"; //微信管理->会员管理的权限设置->查看已设置的会员等级列表
	public List<Map<String,Object>> getVipList();
	public static final String addVipList = "/weixin/vip/addVipList"; //微信管理->会员管理的权限设置->添加新的会员等级权限
	public List<Map<String,Object>> addVipList(String marks,String sroce,String name);
	public static final String addscoreToUser = "/weixin/vip/addscoreToUser"; //微信管理->会员管理的权限设置->给用户添加积分
	public List<Map<String,Object>> addscoreToUser(String id,String sroce);
	public static final String addpriceToUser = "/weixin/vip/addpriceToUser"; //微信管理->会员管理的权限设置->给用户充值权限
	public List<Map<String,Object>> addpriceToUser(String id,String money);
	public static final String jianscoreToUser = "/weixin/vip/jianscoreToUser"; //微信管理->会员管理的权限设置->给用户减少积分
	public List<Map<String,Object>> jianscoreToUser(String id,String sroce);
	public static final String jianpriceToUser = "/weixin/vip/jianpriceToUser"; //微信管理->会员管理的权限设置->给用户减少储值钱数权限
	public List<Map<String,Object>> jianpriceToUser(String id,String money);
	public static final String getHuiyuanxinxi = "/weixin/vip/getHuiyuanxinxi"; //微信管理->会员管理的权限设置->查看会员的级别信息
	public List<Map<String,Object>> getHuiyuanxinxi(String id);
	public static final String getVipInfo = "/weixin/vip/getVipInfo"; //微信管理->会员管理的权限设置->查看级别信息
	public List<Map<String,Object>> getVipInfo(String id);
	public static final String updateVipList = "/weixin/vip/updateVipList"; //微信管理->会员管理的权限设置->修改等级信息
	public List<Map<String,Object>> updateVipList(String id,String name,String sroce,String marks);
	public static final String getScoreDuiHuanList = "/weixin/vip/getScoreDuiHuanList"; //微信管理->会员管理的权限设置->获取积分兑换列表
	public List<Map<String,Object>> getScoreDuiHuanList();
	public static final String addScoreDuiHuanList = "/weixin/vip/addScoreDuiHuanList"; //微信管理->会员管理的权限设置->向兑换列表中添加新的兑换品
	public List<Map<String,Object>> addScoreDuiHuanList(String marks,String sroce,String name,
			String image,String num);
	public static final String getScoreDuiHuan = "/weixin/vip/getScoreDuiHuan"; //微信管理->会员管理的权限设置->查看兑换物详情
	public List<Map<String,Object>> getScoreDuiHuan(String id);
	public static final String updateScoreDuiHuan = "/weixin/vip/updateScoreDuiHuan"; //微信管理->会员管理的权限设置->修改兑换物详情
	public List<Map<String,Object>> updateScoreDuiHuan(String id,String name,String sroce,
			String marks,String image,String num);
	public static final String deleteScoreDuiHuan = "/weixin/vip/deleteScoreDuiHuan"; //微信管理->会员管理的权限设置->删除兑换物详情
	public List<Map<String,Object>> deleteScoreDuiHuan(String id);
	public static final String getVoteUserList = "/weixin/getVoteUserList"; //微信管理->文章管理->查看投票人
	public List<Map<String,Object>> getVoteUserList(String id,String nowpage,String countNum);
	//活动序列号积分序列号权限开始
	public static final String codeConvert = "/weixin/CodeConvert"; //序列号兑换权限
	public List<Map<String,Object>> codeConvert();
	public static final String jifenTogoodManager = "/weixin/CodeConvert/jifenTogoodManager"; //序列号兑换->积分序列号兑换
	public List<Map<String,Object>> jifenTogoodManager(String num);
	public static final String huodongTogoodManager = "/weixin/CodeConvert/huodongTogoodManager"; //序列号兑换->活动序列号兑换
	public List<Map<String,Object>> huodongTogoodManager(String num);
	public static final String web = "/weixin/function/web"; //微信微网站管理
	public List<Map<String,Object>> web();
	public static final String saveWeixinWeb = "/weixin/function/saveWeixinWeb"; //微信微网站管理权限->保存微网站设计
	public List<Map<String,Object>> saveWeixinWeb();
	public static final String getWeixinWeb = "/weixin/function/getWeixinWeb"; //微信微网站管理权限->查看微信微网站设定好的模板
	public List<Map<String,Object>> getWeixinWeb(String nowpage,String countNum,String name);
	public static final String useWeixinWeb = "/weixin/function/useWeixinWeb"; //微信微网站管理权限->选择使用模板权限
	public List<Map<String,Object>> useWeixinWeb(String id);
	public static final String deleteWeixinWeb = "/weixin/function/deleteWeixinWeb"; //微信管理->删除模板
	public List<Map<String,Object>> deleteWeixinWeb(String id);
	//设置地理位置权限开始
	public static final String saveMap = "/weixin/function/saveMap"; //地理位置权限->设置地理位置
	public List<Map<String,Object>> saveMap(String x,String y);
	public static final String getMaps = "/weixin/function/getMaps"; //地理位置权限->获取设置的地理位置
	public List<Map<String,Object>> getMaps();
	public static final String deleteMap = "/weixin/function/deleteMap"; //地理位置权限->删除地理位置
	public List<Map<String,Object>> deleteMap(String id);
	//设置微信货物管理权限开始
	public static final String add = "/weixin/goods/add"; //微信商品管理权限->添加新商品权限
	public List<Map<String,Object>> add(String goodsName,String goodsType,String Price,
			String inPrice,String score,String Spell,String goodsModel,String codeid,String image1,String image2,String image3,String image4);
	public static final String delete = "/weixin/goods/delete"; //微信商品管理权限->删除商品权限
	public List<Map<String,Object>> delete(String id);
	public static final String update = "/weixin/goods/update"; //微信商品管理权限->更新商品权限
	public List<Map<String,Object>> update(String num);
	public static final String queryGood = "/weixin/goods/query"; //微信商品管理权限->查询商品权限
	public List<Map<String,Object>> queryGood(String name,String b,String nowpage,String countNum);
	public static final String shangjia = "/weixin/goods/shangjia"; //微信商品管理权限->上架商品的权限
	public List<Map<String,Object>> shangjia(String id);
	public static final String xiajia = "/weixin/goods/xiajia"; //微信商品管理权限->下架商品的权限
	public List<Map<String,Object>> xiajia(String id);
	//微信订单管理权限
	public static final String deleteOrd = "/weixin/order/delete"; //微信订单->取消订单权限
	public List<Map<String,Object>> deleteOrd(String num);
	public static final String enter = "/weixin/order/enter"; //微信订单->确定订单查询权限
	public List<Map<String,Object>> enter(String id);
	public static final String look = "/weixin/order/look"; //微信订单->确定订单查询权限
	public List<Map<String,Object>> look(String num2,String song,String nowpage,String countNum);
	//短信
	public static final String setboolean = "/messageinfo/setboolean"; //短信管理->短信设置权限
	public List<Map<String,Object>> setboolean(String value,String type);
	public static final String getMessageSet = "/messageinfo/getMessageSet"; //短信管理->查看短信设置权限
	public List<Map<String,Object>> getMessageSet();
	public static final String setTemp = "/messageinfo/setTemp"; //短信管理->设置短信模板
	public List<Map<String,Object>> setTemp(String volue,String type,String methed,String phone);
	public static final String sendVipMessage = "/messageinfo/sendVipMessage"; //短信管理->群发vip短信权限
	public List<Map<String,Object>> sendVipMessage(String value,String select,String type,String num);
	public List<Map<String,Object>> locationUp(String longitude,String latitude);
	//外勤管理
	public static final String waiqinGroupManager = "/hr/function/waiqinGroupManager";
	public List<Map<String,Object>> waiqinGroupManager(String groupid,String trueName
			,String userid,String startDate,String endDate);
	//互动空间
	public static final String getHuDongKongJian = "/hr/function/getHuDongKongJian";
	public List<Map<String,Object>> getHuDongKongJian(String groupId,String nowpage,
			String countNum);
	public static final String sendKongJianManager = "/hr/function/sendKongJianManager";
	public List<Map<String,Object>> sendKongJianManager(String groupId,String title,
			String content,String file,String img,String video,String toupiao,
			String toupiao_title,String toupiao_start,String toupiao_end,
			String toupiao_move);
	public static final String resendKongJianManager = "/hr/function/resendKongJianManager";
	public List<Map<String,Object>> resendKongJianManager(String id,String file,
			String img,String video,String content);
	public static final String setIndexKongjian = "/hr/function/setIndexKongjian";
	public List<Map<String,Object>> setIndexKongjian(String id);
	public static final String deleteKongjian = "/hr/function/deleteKongjian";
	public List<Map<String,Object>> deleteKongjian(String id);
	public static final String zan = "/hr/function/zan";
	public List<Map<String,Object>> zan(String id);
	public static final String getList = "/fileManager/getList";
	public List<Map<String,Object>> getList(String nowpage,String countNum,String type);
	//内容审核
	public static final String getImages = "/ImageModelController/getImage";
	public List<Map<String,Object>> getImages(String nowpage);
	public static final String getVideos = "/VideoModelController/getVideo";
	public List<Map<String,Object>> getVideos(String nowpage,
			String countNum,String type,String id);
	public static final String getTexts = "/TextModelController/getText";
	public List<Map<String,Object>> getTexts(String nowpage,
			String countNum,String type,String id);
	public static final String getErShou = "/ErShouModelController/getErShou";
	public List<Map<String,Object>> getErShou(String nowpage,
			String countNum,String type,String id);
	public static final String getGame = "/gamesController/getGame";
	public List<Map<String,Object>> getGame(String nowpage,
			String countNum,String type,String id);
	public static final String getConpanyGame = "/gamesController/getConpanyGame";
	public List<Map<String,Object>> getConpanyGame(String nowpage,
			String countNum,String type,String id);
	public static final String getGonggao = "/gonggaoController/getGonggao";
	public List<Map<String,Object>> getGonggao(String nowpage,
			String countNum,String type,String id);
	
}
