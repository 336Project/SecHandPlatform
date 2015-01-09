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
	public int save(Class<?> entityClass, Object entity) {
		return baseDao.save(entityClass, entity);
	}


	@Override
	public void saveOrUpdate(Object entity) {
		baseDao.saveOrUpdate(entity);
	}


	@Override
	public void saveOrUpdate(Class<?> entityClass, Object entity) {
		baseDao.saveOrUpdate(entityClass, entity);
	}


	@Override
	public <T> T getByClassNameAndId(Class<?> entityClass, Serializable id) {
		return baseDao.getByClassNameAndId(entityClass, id);
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
	public <T> T getByClassNameAndParams(Class<?> entityClass,
			Map<String, Object> whereParams) {
		return baseDao.getByClassNameAndParams(entityClass, whereParams);
	}


	@Override
	public void delete(Object entity) {
		baseDao.delete(entity);
	}


	@Override
	public void delete(Class<?> entityClass, Object entity) {
		baseDao.delete(entityClass, entity);
	}


	@Override
	public void deleteByClassNameAndId(Class<?> entityClass, Serializable id) {
		baseDao.deleteByClassNameAndId(entityClass, id);
	}


	@Override
	public void deleteByClassNameAndIds(Class<?> entityClass, Serializable[] ids) {
		baseDao.deleteByClassNameAndIds(entityClass, ids);
	}


	@Override
	public void deleteByClassNameAndParams(Class<?> entityClass,
			Map<String, Object> whereParams) {
		baseDao.deleteByClassNameAndParams(entityClass, whereParams);
	}


	@Override
	public void update(Object entity) {
		baseDao.update(entity);
	}


	@Override
	public void update(Class<?> entityClass, Object entity) {
		baseDao.update(entityClass, entity);
	}


	@Override
	public int countByClassName(Class<?> entityClass) {
		return baseDao.countByClassName(entityClass);
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
	public int countByClassNameAndParams(Class<?> entityClass,
			Map<String, Object> whereParams) {
		return baseDao.countByClassNameAndParams(entityClass, whereParams);
	}


	@Override
	public <T> List<T> listByClassName(Class<?> entityClass) {
		return baseDao.listByClassName(entityClass);
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
	public <T> List<T> listByClassNameAndParams(Class<?> entityClass,
			Map<String, Object> whereParams) {
		return baseDao.listByClassNameAndParams(entityClass, whereParams);
	}


	@Override
	public <T> List<T> listPageRowsByClassName(Class<?> entityClass,
			int currentPage, int pageSize) {
		return baseDao.listPageRowsByClassName(entityClass, currentPage, pageSize);
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
	public <T> List<T> listPageRowsByClassNameAndParams(Class<?> entityClass,
			Map<String, Object> whereParams, int currentPage, int pageSize) {
		return baseDao.listPageRowsByClassNameAndParams(entityClass, whereParams, currentPage, pageSize);
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

	@Override
	public void updateColumnById(Class<?> entityClass, String columnName,
			Object value, Serializable id) {
		baseDao.updateColumnById(entityClass, columnName, value, id);
	}

	@Override
	public void updateColumnsByParmas(Class<?> entityClass, Serializable id,
			Map<String, Object> parmas) {
		baseDao.updateColumnsByParmas(entityClass, id, parmas);
	}

}
