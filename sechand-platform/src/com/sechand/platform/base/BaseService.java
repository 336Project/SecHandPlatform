package com.sechand.platform.base;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface BaseService {
	public int save(Object entity);
	public int save(String entityName, Object entity);
	public void saveOrUpdate(Object entity);
	public void saveOrUpdate(String entityName, Object entity);
	public <T> T getByClassNameAndId(String entityName,Serializable id);
	public <T> T getByClassAndId(Class<?> clazz,Serializable id);
	public <T> T getByHQL(String hql);
	public <T> T getBySQL(String sql);
	public <T> T getByClassNameAndParams(String entityName,Map<String, Object> whereParams);
	public void delete(Object entity);
	public void delete(String entityName, Object entity);
	public void deleteByClassNameAndId(String entityName,Serializable id);
	public void deleteByClassNameAndIds(String entityName,Serializable[] ids);
	public void deleteByClassNameAndParams(String entityName,Map<String, Object> whereParams);
	public void deleteByHQL(String hql);
	public void deleteBySQL(String sql);
	public void update(Object entity);
	public void update(String entityName, Object entity);
	public void updateColumnById(String entityName,String columnName,Object value,Serializable id);
	public void updateColumnsByParmas(String entityName,Serializable id,Map<String, Object> parmas);
	public int countByClassName(String entityName);
	public int countByHQL(String hql);
	public int countBySQL(String sql);
	public int countByClassNameAndParams(String entityName,Map<String, Object> whereParams);
	public <T> List<T> listByClassName(String entityName);
	public <T> List<T> listByHQL(String hql);
	public <T> List<T> listBySQL(String sql);
	public <T> List<T> listByClassNameAndParams(String entityName,Map<String, Object> whereParams);
	public <T> List<T> listPageRowsByClassName(String entityName,int currentPage,int pageSize);
	public <T> List<T> listPageRowsByHQL(String hql,int currentPage,int pageSize);
	public <T> List<T> listPageRowsBySQL(String sql,int currentPage,int pageSize);
	public <T> List<T> listPageRowsByClassNameAndParams(String entityName,Map<String, Object> whereParams,int currentPage,int pageSize);
}
