package com.sechand.platform.base;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.opensymphony.xwork2.ActionContext;

public class BaseUtil {
	
	/**
	 * 1.map中的key必须为   "and_name_like"操作_字段名_操作(例如：and_money_>)                value为要传入的值
	 * 2.诺是key为order by，则key写为   order_by,       value 为字段名_升序    (例如：name_desc)
	 * @author wei
	 */
	public static String getHqlString(String entityName,Map<String, Object> params){
		String hql="";
		hql="from "+entityName+" where 1=1 ";
		StringBuffer sb=new StringBuffer();
		StringBuffer orSB=new StringBuffer();//拼接or语句
		StringBuffer andSB=new StringBuffer();//拼接and语句
		if(params!=null){
			Set<String> keys=params.keySet();
			for (String key : keys) {
				final Object obj=params.get(key);
				if(key.contains("order_by")||key.contains("limit")){   //如果存在order_by的操作留在最后
					continue;
				}
				if(key.indexOf("or_") == 0){//以or_开头的
					if(orSB.length()!=0){
						orSB.append(" or ");
					}
					key=key.replace("or_", "");
					orSB=getHQLBuffer(orSB, key, obj);
				}else{//以and_开头的
					andSB.append(" and ");
					key=key.replace("and_", "");
					andSB=getHQLBuffer(andSB, key, obj);
				}//end if
			}
			sb.append(andSB);
			if(orSB.length()!=0){
				sb.append(" and ( ").append(orSB).append(" )");
			}
			//排序条件
			if(params.containsKey("order_by")){
				sb.append(" order by " + params.get("order_by").toString().replace("_desc", " desc").replace("_asc", " asc"));
			}
			//分页条件
			if(params.containsKey("limit")){
				sb.append(" limit " + params.get("limit"));
			}
		}
		return hql+sb.toString();
	}
	/**
	 * 
	 * @Author:Helen  
	 * 2015-1-14下午9:41:11
	 * @param sb
	 * @param key
	 * @param obj
	 * @return
	 * StringBuffer
	 * @TODO 拼接HQL
	 */
	private static StringBuffer getHQLBuffer(StringBuffer sb,String key,Object obj){
		if(key.indexOf("_not_like") != -1 && key.indexOf("_not_like") == (key.length() - 9)){
			sb.append(key.substring(0, key.length() - 9) + " not like '%" + obj + "%'");
		}else if(key.indexOf("_like") != -1 && key.indexOf("_like") == (key.length() - 5)){
			sb.append(key.substring(0, key.length() - 5) + " like '%" + obj + "%'");
		}else if(key.indexOf("_not_in") != -1 && key.indexOf("_not_in") == (key.length() - 7)){
			sb.append(key.substring(0, key.length() - 7) + " not in (" + obj + ")");
		}else if(key.indexOf("_in") != -1 && key.indexOf("_in") == (key.length() - 3)){
			sb.append(key.substring(0, key.length() - 3) + " in (" + obj + ")");
		}else if(key.indexOf("_<>")!=-1&&key.indexOf("_<>")==(key.length() - 3)){
			sb.append(key.substring(0, key.length() - 3) + " <> '" + obj + "'");
		}else if(key.indexOf("_>")!=-1&&key.indexOf("_>")==(key.length() - 3)){
			sb.append(key.substring(0, key.length() - 3)  + " > " +obj );
		}else if(key.indexOf("_<")!=-1&&key.indexOf("_<")==(key.length() -3)){
			sb.append(key.substring(0, key.length() - 3)  + " < " +obj );
		}else if(key.indexOf("_<=")!=-1&&key.indexOf("_<=")==(key.length() - 3)){
			sb.append(key.substring(0, key.length() - 3)  + " <= " +obj );
		}else if(key.indexOf("_>=")!=-1&&key.indexOf("_>=")==(key.length() - 3)){
			sb.append(key.substring(0, key.length() - 3)  + " >= " +obj );
		}else{
			if(obj instanceof String){
				sb.append(key + " = '" + obj + "'");
			}else{
				sb.append(key + " = " +obj );
			}
		}
		return sb;
	}
	
	/**
	 * 
	 * @author Helen
	 * 2014-12-23 下午5:28:25
	 * @param key
	 * @param path
	 * @return 
	 * TODO 从配置文件Properties读取key的值
	 */
	public static String readFromProperties(String key,String path){
		Resource resource = new ClassPathResource(path);
		Properties p;
		try {
			p = PropertiesLoaderUtils.loadProperties(resource);
			return p.getProperty(key,"");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	/*
	 * 登录session
	 */
	public static final String KEY_LOGIN_USER_SESSION="user";
	public static void add2Session(String key,Object value){
		ActionContext.getContext().getSession().put(key, value);
	}
	public static void remove4Session(String key){
		ActionContext.getContext().getSession().remove(key);
	}
	public static Object getSession(String key){
		return ActionContext.getContext().getSession().get(key);
	}
	
	
	public static void main(String[] args) {
		Map<String, Object> whereParams=new HashMap<String, Object>();
		whereParams.put("and_id", 1);
		whereParams.put("or_userName_like", "正常");
		whereParams.put("or_realName_like", "正常");
		whereParams.put("or_nickName_like", "正常");
		whereParams.put("or_email_like", "正常");
		whereParams.put("or_tel_like", "正常");
		whereParams.put("or_status_like", "正常");
		whereParams.put("order_by", "id_asc");
		whereParams.put("limit", 10);
		String rst=BaseUtil.getHqlString("Account", whereParams);
		System.out.println(rst);
	}
}
