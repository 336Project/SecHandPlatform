package com.sechand.platform.serviceimpl;

import java.util.List;

import com.sechand.platform.base.BaseServiceImpl;
import com.sechand.platform.model.Account;
import com.sechand.platform.service.AccountService;

public class AccountServiceImpl extends BaseServiceImpl implements
		AccountService {

	@Override
	public long add(Account account) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Account> listPageRowsAccountsByKeyword(int currentPage,
			int pageSize, String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countByKeyword(String keyword) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deleteByIds(String[] ids) {
		
	}

	@Override
	public boolean updateAccount(Account account) {
		return false;
	}

}
