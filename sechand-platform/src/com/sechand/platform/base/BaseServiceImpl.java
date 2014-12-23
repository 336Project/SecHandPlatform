package com.sechand.platform.base;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class BaseServiceImpl implements BaseService{
	protected BaseDao baseDao;
	@Override
	public int save(Object obj) {
		return baseDao.save(obj);
	}
	
	@Override
	public int save(String entityName, Object entity) {
		return baseDao.save(entityName, entity);
	}


	@Override
	public void saveOrUpdate(Object entity) {
		baseDao.saveOrUpdate(entity);
	}


	@Override
	public void saveOrUpdate(String entityName, Object entity) {
		baseDao.saveOrUpdate(entityName, entity);
	}


	@Override
	public <T> T getByClassNameAndId(String entityName, Serializable id) {
		return baseDao.getByClassNameAndId(entityName, id);
	}


	@Override
	public <T> T getByClassAndId(Class<?> clazz, Serializable id) {
		return baseDao.getByClassAndId(clazz, id);
	}


	@Override
	public <T> T getByHQL(String hql) {
		return baseDao.getByHQL(hql);
	}


	@Override
	public <T> T getBySQL(String sql) {
		return baseDao.getBySQL(sql);
	}


	@Override
	public <T> T getByClassNameAndParams(String entityName,
			Map<String, Object> whereParams) {
		return baseDao.getByClassNameAndParams(entityName, whereParams);
	}


	@Override
	public void delete(Object entity) {
		baseDao.delete(entity);
	}


	@Override
	public void delete(String entityName, Object entity) {
		baseDao.delete(entityName, entity);
	}


	@Override
	public void deleteByClassNameAndId(String entityName, Serializable id) {
		baseDao.deleteByClassNameAndId(entityName, id);
	}


	@Override
	public void deleteByClassNameAndIds(String entityName, Serializable[] ids) {
		baseDao.deleteByClassNameAndIds(entityName, ids);
	}


	@Override
	public void deleteByClassNameAndParams(String entityName,
			Map<String, Object> whereParams) {
		baseDao.deleteByClassNameAndParams(entityName, whereParams);
	}


	@Override
	public void update(Object entity) {
		baseDao.update(entity);
	}


	@Override
	public void update(String entityName, Object entity) {
		baseDao.update(entityName, entity);
	}


	@Override
	public int countByClassName(String entityName) {
		return baseDao.countByClassName(entityName);
	}


	@Override
	public int countByHQL(String hql) {
		return baseDao.countByHQL(hql);
	}


	@Override
	public int countBySQL(String sql) {
		return baseDao.countBySQL(sql);
	}


	@Override
	public int countByClassNameAndParams(String entityName,
			Map<String, Object> whereParams) {
		return baseDao.countByClassNameAndParams(entityName, whereParams);
	}


	@Override
	public <T> List<T> listByClassName(String entityName) {
		return baseDao.listByClassName(entityName);
	}


	@Override
	public <T> List<T> listByHQL(String hql) {
		return baseDao.listByHQL(hql);
	}


	@Override
	public <T> List<T> listBySQL(String sql) {
		return baseDao.listBySQL(sql);
	}


	@Override
	public <T> List<T> listByClassNameAndParams(String entityName,
			Map<String, Object> whereParams) {
		return baseDao.listByClassNameAndParams(entityName, whereParams);
	}


	@Override
	public <T> List<T> listPageRowsByClassName(String entityName,
			int currentPage, int pageSize) {
		return baseDao.listPageRowsByClassName(entityName, currentPage, pageSize);
	}


	@Override
	public <T> List<T> listPageRowsByHQL(String hql, int currentPage,
			int pageSize) {
		return baseDao.listPageRowsByHQL(hql, currentPage, pageSize);
	}


	@Override
	public <T> List<T> listPageRowsBySQL(String sql, int currentPage,
			int pageSize) {
		return baseDao.listPageRowsBySQL(sql, currentPage, pageSize);
	}


	@Override
	public <T> List<T> listPageRowsByClassNameAndParams(String entityName,
			Map<String, Object> whereParams, int currentPage, int pageSize) {
		return baseDao.listPageRowsByClassNameAndParams(entityName, whereParams, currentPage, pageSize);
	}
	
	@Override
	public void deleteByHQL(String hql) {
		baseDao.deleteByHQL(hql);
	}

	@Override
	public void deleteBySQL(String sql) {
		baseDao.deleteBySQL(sql);
	}
	
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
	public BaseDao getBaseDao(){
		return baseDao;
	}

}
