package com.sechand.platform.base;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Set;


import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
@SuppressWarnings("unchecked")
public class BaseDaoImpl extends HibernateDaoSupport implements BaseDao{

	@Override
	public int save(Object entity) {
		return (Integer) getHibernateTemplate().save(entity);
	}

	@Override
	public int save(Class<?> entityClass, Object entity) {
		return (Integer) getHibernateTemplate().save(entityClass.getSimpleName(), entity);
	}

	@Override
	public void saveOrUpdate(Object entity) {
		getHibernateTemplate().saveOrUpdate(entity);
	}

	@Override
	public void saveOrUpdate(Class<?> entityClass, Object entity) {
		getHibernateTemplate().saveOrUpdate(entityClass.getSimpleName(),entity);
	}

	@Override
	public void delete(Object entity) {
		getHibernateTemplate().delete(entity);
	}

	@Override
	public void delete(Class<?> entityClass, Object entity) {
		getHibernateTemplate().delete(entityClass.getSimpleName(), entity);
	}
	
	@Override
	public void deleteByClassNameAndId(Class<?> entityClass, Serializable id) {
		/*Object object=getByClassNameAndId(entityClass.getSimpleName(), id);//getHibernateTemplate().get("cn.edu.xmut.demo.model.Account", id);
		getHibernateTemplate().delete(object);*/
		String hql="from "+entityClass.getSimpleName()+" where id="+id;
		deleteByHQL(hql);
	}

	@Override
	public <T> T getByClassNameAndId(Class<?> entityClass, Serializable id) {
		final String hql="from "+entityClass.getSimpleName()+" where id="+id;
		return (T) getByHQL(hql);
	}

	@Override
	public <T> T getByClassAndId(Class<?> clazz, Serializable id) {
		return  (T) getHibernateTemplate().get(clazz, id);
	}

	@Override
	public <T> T getByHQL(final String hql) {
		try{
			T object=getHibernateTemplate().execute(new HibernateCallback<T>() {
	
				@Override
				public T doInHibernate(Session session)
						throws HibernateException, SQLException {
					Query query=session.createQuery(hql);
					return (T) query.uniqueResult();
				}
			});
			return object;
		}catch(Exception e){
			//e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public <T> T getByClassNameAndParams(Class<?> entityClass,Map<String, Object> whereParams) {
		final String hql=BaseUtil.getHqlString(entityClass.getSimpleName(), whereParams);
		/*List<T> objects=getHibernateTemplate().find(BaseUtil.getHqlString(entityClass.getSimpleName(), whereParams));
		if(objects!=null&&objects.size()>0){
			return objects.get(0);
		}
		return null;*/
		try{
			T object=getHibernateTemplate().execute(new HibernateCallback<T>() {
	
				@Override
				public T doInHibernate(Session session)
						throws HibernateException, SQLException {
					Query query=session.createQuery(hql);
					return (T) query.uniqueResult();
				}
			});
			return object;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public void deleteByClassNameAndIds(Class<?> entityClass,final Serializable[] ids) {
		if(ids!=null){
			/*for (Serializable id : ids) {
				deleteByClassNameAndId(entityClass.getSimpleName(), id);
			}*/
			StringBuffer sb=new StringBuffer();
			sb.append("delete from "+entityClass.getSimpleName()+" where ");
			for (int i = 0; i < ids.length; i++) {
				if(i==0){
					sb.append("id="+ids[i]);
				}else{
					sb.append("or id="+ids[i]);
				}
			}
			//final String hql = "delete from "+entityClass.getSimpleName()+" where id in (:ids)";
			final String hql = sb.toString();
			getHibernateTemplate().execute(new HibernateCallback<Object>() {
				@Override
				public Object doInHibernate(Session session)
						throws HibernateException, SQLException {
					Query query = session.createQuery(hql);
					//query.setParameterList("ids", ids);
					return query.executeUpdate();
				}
			});
		}else{
			throw new NullPointerException("ids can not be null!");
		}
	}

	@Override
	public void deleteByClassNameAndParams(Class<?> entityClass, Map<String, Object> whereParams) {
		/*List<Object> objects=getHibernateTemplate().find(BaseUtil.getHqlString(entityClass.getSimpleName(), whereParams));
		if(objects!=null){
			//一条一条删的
			getHibernateTemplate().deleteAll(objects);
		}*/
		final String hql="delete "+BaseUtil.getHqlString(entityClass.getSimpleName(), whereParams);
		//getHibernateTemplate().bulkUpdate(hql);
		getHibernateTemplate().execute(new HibernateCallback<Object>() {

			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				return session.createQuery(hql).executeUpdate();
			}
		});
	}

	@Override
	public void update(Object entity) {
		getHibernateTemplate().update(entity);
	}

	@Override
	public void update(Class<?> entityClass, Object entity) {
		getHibernateTemplate().update(entityClass.getSimpleName(), entity);
	}

	@Override
	public int countByClassName(Class<?> entityClass) {
		final String hql = "select count(*) from "+entityClass.getSimpleName();
		int count=0;
		try{
			count=getHibernateTemplate().execute(new HibernateCallback<Integer>() {
				@Override
				public Integer doInHibernate(Session session) throws HibernateException,
						SQLException {
					Query query=session.createQuery(hql);
					return ((Long) query.uniqueResult()).intValue();
				}
			});
		}catch (Exception e){
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public int countByHQL(final String hql) {
		int count=0;
		try{
			count=getHibernateTemplate().execute(new HibernateCallback<Integer>() {
				@Override
				public Integer doInHibernate(Session session) throws HibernateException,
						SQLException {
					Query query=session.createQuery("select count(*) "+hql);
					return ((Long) query.uniqueResult()).intValue();
				}
			});
		}catch (Exception e){
		}
		return count;
	}

	@Override
	public int countByClassNameAndParams(final Class<?> entityClass,final Map<String, Object> whereParams) {
		final String hql="select count(*)"+BaseUtil.getHqlString(entityClass.getSimpleName(), whereParams);
		int count=0;
		try{
			count=getHibernateTemplate().execute(new HibernateCallback<Integer>() {
				@Override
				public Integer doInHibernate(Session session) throws HibernateException,
						SQLException {
					Query query=session.createQuery(hql);
					return ((Long) query.uniqueResult()).intValue();
				}
			});
		}catch (Exception e){
		}
		return count;
	}

	@Override
	public <T> List<T> listByClassName(Class<?> entityClass) {
		return getHibernateTemplate().find("from "+entityClass.getSimpleName());
	}

	@Override
	public <T> List<T> listByHQL(String hql) {
		return getHibernateTemplate().find(hql);
	}

	@Override
	public <T> List<T> listByClassNameAndParams(Class<?> entityClass,
			Map<String, Object> whereParams) {
		return getHibernateTemplate().find(BaseUtil.getHqlString(entityClass.getSimpleName(), whereParams));
	}

	@Override
	public <T> List<T> listPageRowsByClassName(Class<?> entityClass,
			final int currentPage, final int pageSize) {
		final String hql="from "+entityClass.getSimpleName();
		List<T> objects=getHibernateTemplate().executeFind(new HibernateCallback<List<T>>() {
			@Override
			public List<T> doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query=session.createQuery(hql);
				query.setFirstResult((currentPage-1)*pageSize);
				query.setMaxResults(pageSize);
				return query.list();
			}
		});
		return objects;
	}

	@Override
	public <T> List<T> listPageRowsByHQL(final String hql, final int currentPage,
			final int pageSize) {
		List<T> objects=getHibernateTemplate().executeFind(new HibernateCallback<List<T>>() {
			@Override
			public List<T> doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query=session.createQuery(hql);
				query.setFirstResult((currentPage-1)*pageSize);
				query.setMaxResults(pageSize);
				return query.list();
			}
		});
		return objects;
	}

	@Override
	public <T> List<T> listPageRowsByClassNameAndParams(Class<?> entityClass,
			Map<String, Object> whereParams, final int currentPage, final int pageSize) {
		final String hql=BaseUtil.getHqlString(entityClass.getSimpleName(), whereParams);
		List<T> objects=getHibernateTemplate().executeFind(new HibernateCallback<List<T>>() {
			@Override
			public List<T> doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query=session.createQuery(hql);
				query.setFirstResult((currentPage-1)*pageSize);
				query.setMaxResults(pageSize);
				return query.list();
			}
		});
		return objects;
	}

	@Override
	public <T> List<T> listPageRowsBySQL(final String sql, final int currentPage,
			final int pageSize) {
		List<T> objects=getHibernateTemplate().executeFind(new HibernateCallback<List<T>>() {
			@Override
			public List<T> doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query=session.createSQLQuery(sql);
				query.setFirstResult((currentPage-1)*pageSize);
				query.setMaxResults(pageSize);
				return query.list();
			}
		});
		return objects;
	}

	@Override
	public <T> List<T> listBySQL(final String sql) {
		List<T> objects=getHibernateTemplate().executeFind(new HibernateCallback<List<T>>() {
			@Override
			public List<T> doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query=session.createSQLQuery(sql);
				return query.list();
			}
		});
		return objects;
	}

	@Override
	public int countBySQL(final String sql) {
		int count=0;
		try{
			count=getHibernateTemplate().execute(new HibernateCallback<Integer>() {
	
				@Override
				public Integer doInHibernate(Session session) throws HibernateException,
						SQLException {
					Query query=session.createSQLQuery(sql);
					return ((BigInteger) query.uniqueResult()).intValue();
				}
			});
		}catch(Exception e){
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public <T> T getBySQL(final String sql) {
		try{
			T object=getHibernateTemplate().execute(new HibernateCallback<T>() {
	
				@Override
				public T doInHibernate(Session session) throws HibernateException,
						SQLException {
					Query query=session.createSQLQuery(sql);
					return (T) query.uniqueResult();
				}
			});
			return object;
		}catch(Exception e){
			
		}
		return null;
	}

	@Override
	public void deleteByHQL(final String hql) {
		getHibernateTemplate().execute(new HibernateCallback<Integer>() {

			@Override
			public Integer doInHibernate(Session session) throws HibernateException, SQLException {
				Query query=session.createQuery("delete "+hql);
				return query.executeUpdate();
			}
		});
	}

	@Override
	public void deleteBySQL(final String sql) {
		getHibernateTemplate().execute(new HibernateCallback<Integer>() {

			@Override
			public Integer doInHibernate(Session session) throws HibernateException, SQLException {
				Query query=session.createSQLQuery("delete "+sql);
				return query.executeUpdate();
			}
		});
	}

	@Override
	public void updateColumnById(Class<?> entityClass, String columnName,
			Object value, Serializable id) {
		StringBuffer sb=new StringBuffer();
		sb.append("update "+entityClass.getSimpleName()+" set "+columnName);
		if(value instanceof String){
			sb.append("='"+value+"' where id="+id);
		}else{
			sb.append("="+value+" where id="+id);
		}
		final String hql=sb.toString();
		getHibernateTemplate().execute(new HibernateCallback<Integer>() {
			@Override
			public Integer doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query=session.createQuery(hql);
				return query.executeUpdate();
			}
			
		});
	}

	@Override
	public void updateColumnsByParmas(Class<?> entityClass, Serializable id,
			Map<String, Object> parmas) {
		StringBuffer sb=new StringBuffer();
		sb.append("update "+entityClass.getSimpleName()+" set ");
		Set<String> keys=parmas.keySet();
		for (String key : keys) {
			Object value=parmas.get(key);
			if(value instanceof String){
				sb.append(key+"='"+value+"',");
			}else{
				sb.append(key+"="+value+",");
			}
		}
		//去掉最后一个,
		sb.replace(sb.length()-1, sb.length(), "");
		sb.append(" where id="+id);
		final String hql=sb.toString();
		getHibernateTemplate().execute(new HibernateCallback<Integer>() {
			@Override
			public Integer doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query=session.createQuery(hql);
				return query.executeUpdate();
			}
			
		});
	}

	@Override
	public void updateColumnByIds(Class<?> entityClass, String columnName,
			Object value, Serializable[] ids) {
		StringBuffer sb=new StringBuffer();
		sb.append("update "+entityClass.getSimpleName()+" set "+columnName);
		if(value instanceof String){
			sb.append("='"+value+"'");
		}else{
			sb.append("="+value);
		}
		sb.append(" where ");
		for (int i = 0; i < ids.length; i++) {
			if(i==0){
				sb.append("id="+ids[i]);
			}else{
				sb.append(" or id="+ids[i]);
			}
		}
		final String hql=sb.toString();
		getHibernateTemplate().execute(new HibernateCallback<Integer>() {
			@Override
			public Integer doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query=session.createQuery(hql);
				return query.executeUpdate();
			}
		});
	}

	@Override
	public void updateColumnsByParmas(Class<?> entityClass, Serializable[] ids,
			Map<String, Object> parmas) {
		StringBuffer sb=new StringBuffer();
		sb.append("update "+entityClass.getSimpleName()+" set ");
		Set<String> keys=parmas.keySet();
		for (String key : keys) {
			Object value=parmas.get(key);
			if(value instanceof String){
				sb.append(key+"='"+value+"',");
			}else{
				sb.append(key+"="+value+",");
			}
		}
		//去掉最后一个,
		sb.replace(sb.length()-1, sb.length(), "");
		sb.append(" where ");
		for (int i = 0; i < ids.length; i++) {
			if(i==0){
				sb.append("id="+ids[i]);
			}else{
				sb.append(" or id="+ids[i]);
			}
		}
		final String hql=sb.toString();
		getHibernateTemplate().execute(new HibernateCallback<Integer>() {
			@Override
			public Integer doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query=session.createQuery(hql);
				return query.executeUpdate();
			}
			
		});
	}

}
