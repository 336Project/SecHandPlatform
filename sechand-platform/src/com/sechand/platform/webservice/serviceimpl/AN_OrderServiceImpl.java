package com.sechand.platform.webservice.serviceimpl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.sechand.platform.base.BaseServiceImpl;
import com.sechand.platform.model.Account;
import com.sechand.platform.model.Order;
import com.sechand.platform.model.Role;
import com.sechand.platform.model.Trade;
import com.sechand.platform.model.User;
import com.sechand.platform.utils.SysUtils;
import com.sechand.platform.utils.WebUtil;
import com.sechand.platform.webservice.service.AN_OrderService;

public class AN_OrderServiceImpl extends BaseServiceImpl implements
		AN_OrderService {

	@Override
	public String updateByCustomer(String orderId, String companyId,
			String contactTelUser, String repairContent) {
		Integer orderId_Int = Integer.valueOf(orderId);
		Integer companyId_Int = Integer.valueOf(companyId);
		Order o = baseDao.getByClassAndId(Order.class, orderId_Int);
		User u = baseDao.getByClassAndId(User.class, companyId_Int);
		if (o != null && u != null) {
			if (Order.STATUS_NEW.equals(o.getStatus())) {
				Map<String, Object> parmas = new HashMap<String, Object>();
				parmas.put("contactTelUser", contactTelUser);
				parmas.put("repairContent", repairContent);
				parmas.put("companyId", u.getId());
				parmas.put("customerCompany", u.getNickName());
				baseDao.updateColumnsByParmas(Order.class, o.getId(), parmas);
				return "修改成功!";
			} else {
				return "该订单不能修改，只能修改新订单!";
			}
		}
		return null;
	}

	@Override
	public String quoteByOrder(String id, String price, String contactTelCompany) {
		Order o = baseDao.getByClassNameAndId(Order.class, Integer.valueOf(id));
		if (!Order.STATUS_NEW.equals(o.getStatus()))
			return "只有新订单才能报价!";
		// 当前登录用户与操作当前订单的公司id一样
		Map<String, Object> parmas = new HashMap<String, Object>();
		parmas.put("price", price);
		parmas.put("contactTelCompany", contactTelCompany);
		parmas.put("quoteTime", SysUtils.getDateFormat(new Date()));
		parmas.put("status", Order.STATUS_QUOTE);
		baseDao.updateColumnsByParmas(Order.class, o.getId(), parmas);
		return "报价成功!";
	}

	@Override
	public List<Order> listOrdersByPageRows(int currentPage, int pageSize,
			String keyword) {
		Map<String, Object> whereParams = new HashMap<String, Object>();
		if (!StringUtils.isEmpty(keyword)) {
			whereParams.put("or_customerUser_like", keyword);
			whereParams.put("or_customerCompany_like", keyword);
			whereParams.put("or_repairContent_like", keyword);
			whereParams.put("or_contactTelUser_like", keyword);
			whereParams.put("or_contactTelCompany_like", keyword);
			whereParams.put("or_price_like", keyword);
			whereParams.put("or_createTime_like", keyword);
			whereParams.put("or_quoteTime_like", keyword);
			whereParams.put("or_completeTime_like", keyword);
			whereParams.put("or_status_like", keyword);
		}
		return baseDao.listPageRowsByClassNameAndParams(Order.class,
				whereParams, currentPage, pageSize);
	}

	@Override
	public int countByKeyword(String keyword) {
		Map<String, Object> whereParams = new HashMap<String, Object>();
		if (!StringUtils.isEmpty(keyword)) {
			whereParams.put("or_customerUser_like", keyword);
			whereParams.put("or_customerCompany_like", keyword);
			whereParams.put("or_repairContent_like", keyword);
			whereParams.put("or_contactTelUser_like", keyword);
			whereParams.put("or_contactTelCompany_like", keyword);
			whereParams.put("or_price_like", keyword);
			whereParams.put("or_createTime_like", keyword);
			whereParams.put("or_completeTime_like", keyword);
			whereParams.put("or_quoteTime_like", keyword);
			whereParams.put("or_status_like", keyword);
		}
		return baseDao.countByClassNameAndParams(Order.class, whereParams);
	}

	@Override
	public boolean disableByIds(String ids) {
		try {
			if (StringUtils.isNotBlank(ids)) {
				baseDao.updateColumnByIds(Order.class, "status",
						Order.STATUS_INVALID, ids.split(","));
				return true;
			}
		} catch (Exception e) {
		}
		return false;
	}

	@Override
	public List<Order> listCustomerOrdersByPageRows(String roleCode,
			String ids, int currentPage, String keyword) {
		Map<String, Object> whereParams = new HashMap<String, Object>();
		Integer id_Int = Integer.valueOf(ids);
		if (Role.CODE_CUSTOMER.equals(roleCode)) {// 普通用户
			whereParams.put("userId", id_Int);
		} else {// 公司
			whereParams.put("companyId", id_Int);
		}
		if (!StringUtils.isEmpty(keyword)) {
			if (Role.CODE_CUSTOMER.equals(roleCode)) {
				whereParams.put("or_customerCompany_like", keyword);
			} else {
				whereParams.put("or_customerUser_like", keyword);
			}
			whereParams.put("or_repairContent_like", keyword);
			whereParams.put("or_contactTelUser_like", keyword);
			whereParams.put("or_contactTelCompany_like", keyword);
			whereParams.put("or_price_like", keyword);
			whereParams.put("or_createTime_like", keyword);
			whereParams.put("or_quoteTime_like", keyword);
			whereParams.put("or_completeTime_like", keyword);
			whereParams.put("or_status_like", keyword);
		}
		return baseDao.listPageRowsByClassNameAndParams(Order.class,
				whereParams, currentPage, 15);
	}

	@Override
	public int countCustomerByKeyword(String roleCode, String ids,
			String keyword) {
		Map<String, Object> whereParams = new HashMap<String, Object>();
		Integer id_Int = Integer.valueOf(ids);
		if (Role.CODE_CUSTOMER.equals(roleCode)) {// 普通用户
			whereParams.put("userId", id_Int);
		} else {// 公司
			whereParams.put("companyId", id_Int);
		}
		if (!StringUtils.isEmpty(keyword)) {
			if (Role.CODE_CUSTOMER.equals(roleCode)) {
				whereParams.put("or_customerCompany_like", keyword);
			} else {
				whereParams.put("or_customerUser_like", keyword);
			}
			whereParams.put("or_repairContent_like", keyword);
			whereParams.put("or_contactTelUser_like", keyword);
			whereParams.put("or_contactTelCompany_like", keyword);
			whereParams.put("or_price_like", keyword);
			whereParams.put("or_createTime_like", keyword);
			whereParams.put("or_completeTime_like", keyword);
			whereParams.put("or_quoteTime_like", keyword);
			whereParams.put("or_status_like", keyword);
		}
		return baseDao.countByClassNameAndParams(Order.class, whereParams);
	}

	@Override
	public boolean updateStatusByIds(String ids, String status) {
		try {
			if (StringUtils.isNotBlank(ids) && StringUtils.isNotBlank(status)) {
				if (Order.STATUS_CANCEL.equals(status)
						|| Order.STATUS_COM.equals(status)
						|| Order.STATUS_CONFIRM.equals(status)) {
					baseDao.updateColumnByIds(Order.class, "status", status,
							ids.split(","));
					return true;
				}
			}
		} catch (Exception e) {
		}
		return false;
	}

	@Override
	public String updateByCustomer(Order order) {
		if (order != null) {
			Order o = baseDao.getByClassAndId(Order.class, order.getId());
			User u = baseDao.getByClassAndId(User.class, order.getCompanyId());
			if (o != null && u != null) {
				if (Order.STATUS_NEW.equals(o.getStatus())) {
					Map<String, Object> parmas = new HashMap<String, Object>();
					parmas.put("contactTelUser", order.getContactTelUser());
					parmas.put("repairContent", order.getRepairContent());
					parmas.put("companyId", u.getId());
					parmas.put("customerCompany", u.getNickName());
					baseDao.updateColumnsByParmas(Order.class, o.getId(),
							parmas);
					return "修改成功!";
				} else {
					return "该订单不能修改，只能修改新订单!";
				}
			}
		}
		return null;
	}

	@Override
	public String repairByCustomer(String companyId, String customerUser,
			String contactTelUser, String repairContent, String userId) {
		Integer companyId_Int = Integer.valueOf(companyId);
		Integer userId_Int = Integer.valueOf(userId);
		User company = baseDao.getByClassAndId(User.class, companyId_Int);
		if (company != null) {
			Order o = new Order();
			o.setCompanyId(company.getId());
			o.setContactTelCompany(company.getTel());
			o.setContactTelUser(contactTelUser);
			o.setCreateTime(SysUtils.getDateFormat(new Date()));
			o.setCustomerCompany(company.getNickName());
			o.setCustomerUser(customerUser);
			o.setRepairContent(repairContent);
			o.setStatus(Order.STATUS_NEW);
			o.setUserId(userId_Int);
			int id = baseDao.save(Order.class, o);
			if (id > 0) {
				return "报修成功!";
			}
		} else {
			return "所选的维修公司不存在!";
		}
		return null;
	}

	@Override
	public String cancelById(String id) {
		if (StringUtils.isNotBlank(id)) {
			Order order = baseDao.getByClassAndId(Order.class,
					Integer.valueOf(id));
			if (order != null) {
				if (Order.STATUS_NEW.equals(order.getStatus())
						|| Order.STATUS_QUOTE.equals(order.getStatus())) {
					baseDao.updateColumnById(Order.class, "status",
							Order.STATUS_CANCEL, order.getId());
					return "取消成功!";
				} else {
					return "只有新订单或已报价的订单,才可以取消!";
				}
			}
		}
		return null;
	}

	@Override
	public String confirmById(String id) {
		if (StringUtils.isNotBlank(id)) {
			Order order = baseDao.getByClassAndId(Order.class,
					Integer.valueOf(id));
			User customer = baseDao.getByClassAndId(User.class,
					order.getUserId());
			User company = baseDao.getByClassAndId(User.class,
					order.getCompanyId());
			if (order != null && customer != null && company != null) {
				if (Order.STATUS_COM.equals(order.getStatus())) {
					try {
						double price = Double.valueOf(order.getPrice());// 维修金额
						double customer_balance = Double.valueOf(customer
								.getBalance());// 用户余额
						double company_balance = Double.valueOf(company
								.getBalance());// 公司账户余额
						customer_balance = customer_balance - price;
						company_balance = company_balance + price;
						if (customer_balance < 0) {
							return "确认失败:余额不足，请先充值!";
						}
						// 保存交易记录start
						Trade trade = new Trade();
						trade.setFromUserId(customer.getId());
						trade.setFromUserName(customer.getUserName());
						trade.setFromUserNickName(customer.getNickName());
						trade.setMoney(order.getPrice());
						trade.setStatus(Trade.STATUS_SUCCESS);
						trade.setTime(SysUtils.getDateFormat(new Date()));
						trade.setToUserId(company.getId());
						trade.setToUserName(company.getUserName());
						trade.setToUserNickName(company.getNickName());
						baseDao.save(Trade.class, trade);
						// 保存交易记录end

						// 添加用户账户信息
						Account account = new Account();
						account.setCreateTime(SysUtils
								.getDateFormat(new Date()));
						account.setMoney("-" + order.getPrice());
						account.setNickName(customer.getNickName());
						account.setRemark("维修扣款");
						account.setSource(Account.SOURCE_USER);
						account.setStatus(Account.STATUS_CONFIRM);
						account.setType(Account.TYPE_CONSUME);
						account.setUserId(customer.getId());
						account.setUserName(customer.getUserName());
						account.setCompleteTime(SysUtils
								.getDateFormat(new Date()));
						account.setBalance(SysUtils
								.getMoneyFormat(customer_balance));
						baseDao.save(Account.class, account);
						// 添加公司账户信息
						Account account2 = new Account();
						account2.setCreateTime(SysUtils
								.getDateFormat(new Date()));
						account2.setMoney(order.getPrice());
						account2.setNickName(company.getNickName());
						account2.setRemark("维修收款");
						account2.setSource(Account.SOURCE_USER);
						account2.setStatus(Account.STATUS_CONFIRM);
						account2.setType(Account.TYPE_TO_ACCOUNT);
						account2.setUserId(company.getId());
						account2.setUserName(company.getUserName());
						account2.setCompleteTime(SysUtils
								.getDateFormat(new Date()));
						account2.setBalance(SysUtils
								.getMoneyFormat(company_balance));
						baseDao.save(Account.class, account2);
						// 更新用户余额
						baseDao.updateColumnById(User.class, "balance",
								SysUtils.getMoneyFormat(customer_balance),
								customer.getId());
						baseDao.updateColumnById(User.class, "balance",
								SysUtils.getMoneyFormat(company_balance),
								company.getId());
						baseDao.updateColumnById(Order.class, "status",
								Order.STATUS_CONFIRM, order.getId());
						return "确认成功!";
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					return "只有已完成的订单,才可以确认!";
				}
			}
		}
		return null;
	}

	@Override
	public String completeById(String id) {
		if (StringUtils.isNotBlank(id)) {
			Order order = baseDao.getByClassAndId(Order.class,
					Integer.valueOf(id));
			if (order != null) {
				if (Order.STATUS_QUOTE.equals(order.getStatus())) {
					Map<String, Object> parmas = new HashMap<String, Object>();
					parmas.put("completeTime",
							SysUtils.getDateFormat(new Date()));
					parmas.put("status", Order.STATUS_COM);
					baseDao.updateColumnsByParmas(Order.class, order.getId(),
							parmas);
					return "操作成功!";
				} else {
					return "只有已报价的订单，才能完成!";
				}
			}
		}
		return null;
	}
}
