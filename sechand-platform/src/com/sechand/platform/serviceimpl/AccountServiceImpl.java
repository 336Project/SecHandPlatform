package com.sechand.platform.serviceimpl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.sechand.platform.base.BaseServiceImpl;
import com.sechand.platform.model.Account;
import com.sechand.platform.model.Role;
import com.sechand.platform.model.User;
import com.sechand.platform.service.AccountService;
import com.sechand.platform.utils.SysUtils;
import com.sechand.platform.utils.WebUtil;

public class AccountServiceImpl extends BaseServiceImpl implements
		AccountService {

	@Override
	public long add(Account account) {
		if(account!=null){
			User user=baseDao.getByClassAndId(User.class, account.getUserId());
			if(user!=null){
				account.setCreateTime(SysUtils.getDateFormat(new Date()));
				account.setStatus(Account.STATUS_TO_CONFIRM);
				account.setUserName(user.getUserName());
				account.setNickName(user.getNickName());
				account.setType(Account.TYPE_RECHARGE);
				return baseDao.save(account);
			}
		}
		return -1;
	}

	@Override
	public List<Account> listPageRowsAccountsByKeyword(int currentPage,
			int pageSize, String keyword,boolean isAdmin) {
		Map<String, Object> whereParams=new HashMap<String, Object>();
		//用户校验
		User user=(User) WebUtil.getSession(WebUtil.KEY_LOGIN_USER_SESSION);
		if(user==null) return null;
		if(!isAdmin){//非管理员，则获取相应用户的信息
			whereParams.put("userId", user.getId());
		}else{
			if(!Role.CODE_ADMIN.equals(user.getRoleCode())) return null;
		}
		if(!StringUtils.isEmpty(keyword)){
			whereParams.put("or_userName_like", keyword);
			whereParams.put("or_createTime_like", keyword);
			whereParams.put("or_completeTime_like", keyword);
			whereParams.put("or_money_like", keyword);
			whereParams.put("or_type_like", keyword);
			whereParams.put("or_status_like",keyword);
			whereParams.put("or_remark_like",keyword);
		}
		return baseDao.listPageRowsByClassNameAndParams(Account.class, whereParams, currentPage, pageSize);
	}

	@Override
	public int countByKeyword(String keyword,boolean isAdmin) {
		Map<String, Object> whereParams=new HashMap<String, Object>();
		//用户校验
		User user=(User) WebUtil.getSession(WebUtil.KEY_LOGIN_USER_SESSION);
		if(user==null) return 0;
		if(!isAdmin){//非管理员，则获取相应用户的信息
			whereParams.put("userId", user.getId());
		}else{
			if(!Role.CODE_ADMIN.equals(user.getRoleCode())) return 0;
		}
		if(!StringUtils.isEmpty(keyword)){
			whereParams.put("or_userName_like", keyword);
			whereParams.put("or_createTime_like", keyword);
			whereParams.put("or_completeTime_like", keyword);
			whereParams.put("or_money_like", keyword);
			whereParams.put("or_type_like", keyword);
			whereParams.put("or_status_like",keyword);
			whereParams.put("or_remark_like",keyword);
		}
		return baseDao.countByClassNameAndParams(Account.class, whereParams);
	}

	@Override
	public void deleteByIds(String[] ids) {
		baseDao.deleteByClassNameAndIds(Account.class, ids);
	}

	@Override
	public boolean updateAccount(Account account) {
		return false;
	}

	@Override
	public String confirmById(String id) {
		Account account=baseDao.getByClassAndId(Account.class, Integer.valueOf(id));
		if(account!=null){
			if(!Account.STATUS_CONFIRM.equals(account.getStatus())){
				if(Account.STATUS_CANCEL.equals(account.getStatus())){
					return "该充值已取消,不能确认!";
				}else{
					User user=baseDao.getByClassNameAndId(User.class, account.getUserId());
					if(user!=null){
						try{
							String current_balance=user.getBalance();//获取当前余额
							if(StringUtils.isBlank(current_balance)){
								current_balance="0";
							}
							double balance=Double.parseDouble(current_balance)+Double.parseDouble(account.getMoney());
							//更新账户记录状态
							Map<String, Object> parmas=new HashMap<String, Object>();
							parmas.put("status", Account.STATUS_CONFIRM);
							parmas.put("completeTime", SysUtils.getDateFormat(new Date()));
							baseDao.updateColumnsByParmas(Account.class, account.getId(), parmas);
							//更新用户余额
							baseDao.updateColumnById(User.class, "balance", SysUtils.getMoneyFormat(balance), user.getId());
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

	@Override
	public String pickup(Account account) {
		User user=(User) WebUtil.getSession(WebUtil.KEY_LOGIN_USER_SESSION);
		if(account!=null&&user!=null){
			if(user.getId().equals(account.getUserId())){
				double balance=Double.valueOf(account.getBalance());
				double money=Double.valueOf(account.getMoney());
				if(balance>money){
					Account a=new Account();
					a.setType(Account.TYPE_PICKUP);
					a.setSource(Account.SOURCE_USER);
					a.setStatus(Account.STATUS_TO_PICKUP);
					a.setBalance(SysUtils.getMoneyFormat(balance));
					a.setCreateTime(SysUtils.getDateFormat(new Date()));
					a.setMoney("-"+SysUtils.getMoneyFormat(money));
					a.setNickName(user.getNickName());
					a.setUserId(user.getId());
					a.setUserName(user.getUserName());
					a.setRemark("用户提现");
					baseDao.save(Account.class, a);
					return "提现申请成功，等待确认!";
				}else{
					return "当前最多可提现￥"+balance;
				}
			}
		}
		return null;
	}

}
