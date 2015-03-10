package com.sechand.platform.webservice.serviceimpl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.sechand.platform.base.BaseServiceImpl;
import com.sechand.platform.model.Account;
import com.sechand.platform.model.Role;
import com.sechand.platform.model.User;
import com.sechand.platform.utils.SysUtils;
import com.sechand.platform.utils.WebUtil;
import com.sechand.platform.webservice.service.AN_AccountService;

public class AN_AccountServiceImpl extends BaseServiceImpl implements
		AN_AccountService {

	@Override
	public User login(String username, String password) {
		String hql = "from Account where (userName='" + username
				+ "' or email='" + username + "') and password ='"
				+ SysUtils.encrypt(password) + "'";
		User account = baseDao.getByHQL(hql);
		if (account != null) {
			return account;
		}
		return null;
	}

	@Override
	public List<Account> listPageRowsAccountsByKeyword(int currentPage,
			int pageSize, String keyword, boolean isAdmin) {
		Map<String, Object> whereParams = new HashMap<String, Object>();
		if (!StringUtils.isEmpty(keyword)) {
			whereParams.put("or_userName_like", keyword);
			whereParams.put("or_createTime_like", keyword);
			whereParams.put("or_completeTime_like", keyword);
			whereParams.put("or_money_like", keyword);
			whereParams.put("or_type_like", keyword);
			whereParams.put("or_status_like", keyword);
			whereParams.put("or_remark_like", keyword);
		}
		return baseDao.listPageRowsByClassNameAndParams(Account.class,
				whereParams, currentPage, pageSize);
	}
	
	@Override
	public List<Account> listPageRowsAccountsByKeywordAndId(String ids,int currentPage,
			int pageSize, String keyword, boolean isAdmin) {
		Map<String, Object> whereParams = new HashMap<String, Object>();
		//用户校验
		whereParams.put("userId", ids);
		if (!StringUtils.isEmpty(keyword)) {
			whereParams.put("or_userName_like", keyword);
			whereParams.put("or_createTime_like", keyword);
			whereParams.put("or_completeTime_like", keyword);
			whereParams.put("or_money_like", keyword);
			whereParams.put("or_type_like", keyword);
			whereParams.put("or_status_like", keyword);
			whereParams.put("or_remark_like", keyword);
		}
		return baseDao.listPageRowsByClassNameAndParams(Account.class,
				whereParams, currentPage, pageSize);
	}

	@Override
	public int countByKeyword(String keyword, boolean isAdmin) {
		Map<String, Object> whereParams = new HashMap<String, Object>();
		// 用户校验
		// User user=(User) WebUtil.getSession(WebUtil.KEY_LOGIN_USER_SESSION);
		// if(user==null) return 0;
		// if(!isAdmin){//非管理员，则获取相应用户的信息
		// whereParams.put("userId", user.getId());
		// }else{
		// if(!Role.CODE_ADMIN.equals(user.getRoleCode())) return 0;
		// }
		if (!StringUtils.isEmpty(keyword)) {
			whereParams.put("or_userName_like", keyword);
			whereParams.put("or_createTime_like", keyword);
			whereParams.put("or_completeTime_like", keyword);
			whereParams.put("or_money_like", keyword);
			whereParams.put("or_type_like", keyword);
			whereParams.put("or_status_like", keyword);
			whereParams.put("or_remark_like", keyword);
		}
		return baseDao.countByClassNameAndParams(Account.class, whereParams);
	}

	@Override
	public long add(int id, String money, String source, String remark) {
//		int userid = Integer.parseInt(id);
		User user = baseDao.getByClassAndId(User.class, id);
		if (user != null) {
			Account a = new Account();
			a.setBalance(user.getBalance());
			a.setCreateTime(SysUtils.getDateFormat(new Date()));
			a.setMoney(money);
			a.setNickName(user.getNickName());
			a.setSource(source);
			a.setRemark(remark);
			a.setStatus(Account.STATUS_TO_CONFIRM);
			a.setType(Account.TYPE_RECHARGE);
			a.setUserId(user.getId());
			a.setUserName(user.getUserName());
			return baseDao.save(Account.class, a);
		}
		return -1;
	}

	@Override
	public String pickup(int id, String money) {
//		int userid = Integer.parseInt(id);
		User user = baseDao.getByClassAndId(User.class, id);
		double balance = Double.valueOf(user.getBalance());
		double money_double = Double.valueOf(money);
		if (balance > money_double) {
			Account a = new Account();
			a.setType(Account.TYPE_PICKUP);
			a.setSource(Account.SOURCE_USER);
			a.setStatus(Account.STATUS_TO_CONFIRM);
			a.setBalance(SysUtils.getMoneyFormat(balance));
			a.setCreateTime(SysUtils.getDateFormat(new Date()));
			a.setMoney("-" + SysUtils.getMoneyFormat(money_double));
			a.setNickName(user.getNickName());
			a.setUserId(user.getId());
			a.setUserName(user.getUserName());
			a.setRemark("用户提现");
			baseDao.save(Account.class, a);
			return "提现申请成功，等待确认!";
		} else {
			return "当前最多可提现￥" + SysUtils.getMoneyFormat(balance);
		}
	}
	@Override
	public String confirmById(String id) {
		Account account=baseDao.getByClassAndId(Account.class, Integer.valueOf(id));
		if(account!=null){
			if(!Account.STATUS_CONFIRM.equals(account.getStatus())){
				if(Account.STATUS_CANCEL.equals(account.getStatus())){
					return "该记录已取消,不能确认!";
				}else{
					User user=baseDao.getByClassNameAndId(User.class, account.getUserId());
					if(user!=null){
						try{
							String current_balance=user.getBalance();//获取当前余额
							if(StringUtils.isBlank(current_balance)){
								current_balance="0";
							}
							if(Account.TYPE_RECHARGE.equals(account.getType())){//充值
								double balance=Double.parseDouble(current_balance)+Double.parseDouble(account.getMoney());
								current_balance=SysUtils.getMoneyFormat(balance);
								//更新账户记录状态
								Map<String, Object> parmas=new HashMap<String, Object>();
								parmas.put("status", Account.STATUS_CONFIRM);
								parmas.put("completeTime", SysUtils.getDateFormat(new Date()));
								baseDao.updateColumnsByParmas(Account.class, account.getId(), parmas);
								//更新用户余额
								baseDao.updateColumnById(User.class, "balance", current_balance, user.getId());
							}else if(Account.TYPE_PICKUP.equals(account.getType())){//提现
								double money=Double.parseDouble(account.getMoney());
								double balance=Double.parseDouble(current_balance);
								current_balance=SysUtils.getMoneyFormat(balance);
								Map<String, Object> parmas=new HashMap<String, Object>();
								String remark ="";
								if(money>0){
									remark= "提现金额有误!";
								}else{
									if(balance>-money){//余额足以提现
										balance=balance+money;
										current_balance=SysUtils.getMoneyFormat(balance);
										remark="提现成功";
										//更新用户余额
										baseDao.updateColumnById(User.class, "balance", current_balance, user.getId());
									}else{//不足以提现
										remark="提现金额超过当前余额,不能提现";
									}
								}
								parmas.put("balance", current_balance);
								parmas.put("status", Account.STATUS_CONFIRM);
								parmas.put("completeTime", SysUtils.getDateFormat(new Date()));
								parmas.put("remark", remark);
								baseDao.updateColumnsByParmas(Account.class, account.getId(), parmas);
							}
							return "确认成功!";
						}catch (Exception e) {
						}
					}
				}
			}else{
				return "该记录已确认过，请勿重复确认!";
			}
		}
		return null;
	}
	@Override
	public void deleteByIds(String[] ids) {
		baseDao.deleteByClassNameAndIds(Account.class, ids);
	}
	@Override
	public String cancelById(String id) {
		Account account=baseDao.getByClassAndId(Account.class, Integer.valueOf(id));
		if(account!=null){
			if(Account.STATUS_TO_CONFIRM.equals(account.getStatus())){
				//更新账户记录状态
				baseDao.updateColumnById(Account.class, "status", Account.STATUS_CANCEL, account.getId());
				return "取消成功!";
			}else{
				return "只有待确认的记录,才能取消!";
			}
		}
		return null;
	}
}
