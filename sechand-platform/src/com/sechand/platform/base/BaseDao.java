package com.sechand.platform.base;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


public interface BaseDao {
	public int save(Object entity);
	public int save(Class<?> entityClass, Object entity);
	public void saveOrUpdate(Object entity);
	public void saveOrUpdate(Class<?> entityClass, Object entity);
	public <T> T getByClassNameAndId(Class<?> entityClass,Serializable id);
	public <T> T getByClassAndId(Class<?> clazz,Serializable id);
	public <T> T getByHQL(String hql);
	public <T> T getBySQL(String sql);
	public <T> T getByClassNameAndParams(Class<?> entityClass,Map<String, Object> whereParams);
	public void delete(Object entity);
	public void delete(Class<?> entityClass, Object entity);
	public void deleteByClassNameAndId(Class<?> entityClass,Serializable id);
	public void deleteByClassNameAndIds(Class<?> entityClass,Serializable[] ids);
	public void deleteByClassNameAndParams(Class<?> entityClass,Map<String, Object> whereParams);
	public void deleteByHQL(String hql);
	public void deleteBySQL(String sql);
	public void update(Object entity);
	public void updateColumnById(Class<?> entityClass,String columnName,Object value,Serializable id);
	public void updateColumnsByParmas(Class<?> entityClass,Serializable id,Map<String, Object> parmas);
	public void update(Class<?> entityClass, Object entity);
	public int countByClassName(Class<?> entityClass);
	public int countByHQL(String hql);
	public int countBySQL(String sql);
	public int countByClassNameAndParams(Class<?> entityClass,Map<String, Object> whereParams);
	public <T> List<T> listByClassName(Class<?> entityClass);
	public <T> List<T> listByHQL(String hql);
	public <T> List<T> listBySQL(String sql);
	public <T> List<T> listByClassNameAndParams(Class<?> entityClass,Map<String, Object> whereParams);
	public <T> List<T> listPageRowsByClassName(Class<?> entityClass,int currentPage,int pageSize);
	public <T> List<T> listPageRowsByHQL(String hql,int currentPage,int pageSize);
	public <T> List<T> listPageRowsBySQL(String sql,int currentPage,int pageSize);
	public <T> List<T> listPageRowsByClassNameAndParams(Class<?> entityClass,Map<String, Object> whereParams,int currentPage,int pageSize);
}
