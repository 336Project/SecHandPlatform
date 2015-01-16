package com.sechand.platform.serviceimpl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.sechand.platform.base.BaseServiceImpl;
import com.sechand.platform.model.Account;
import com.sechand.platform.model.User;
import com.sechand.platform.service.AccountService;
import com.sechand.platform.utils.SysUtils;

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
			int pageSize, String keyword) {
		Map<String, Object> whereParams=new HashMap<String, Object>();
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
	public int countByKeyword(String keyword) {
		Map<String, Object> whereParams=new HashMap<String, Object>();
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
			}else{
				return "不能重复确认!";
			}
		}
		return null;
	}

}
