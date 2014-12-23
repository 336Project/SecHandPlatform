package com.sechand.platform.base;

import java.util.Map;
import java.util.Set;

import com.sechand.platform.utils.SysUtils;


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
		if(params!=null){
			Set<String> keys=params.keySet();
			for (String key : keys) {
				final Object obj=params.get(key);
				if(key.contains("order_by")||key.contains("limit")){   //如果存在order_by的操作留在最后
					continue;
				}
				if(key.indexOf("or_") == 0){//以or_开头的
					if(sb.length()==0){
						sb.append(" and ");
					}else{
						sb.append(" or ");
					}
				}else{//以and_开头的
					sb.append(" and ");
				}//end if
				
				if(key.indexOf("_not_like") != -1 && key.indexOf("_not_like") == (key.length() - 9)){
					sb.append(key.substring(key.indexOf("_")+1, key.length() - 9) + " not like '%" + obj + "%'");
				}else if(key.indexOf("_like") != -1 && key.indexOf("_like") == (key.length() - 5)){
					sb.append(key.substring(key.indexOf("_")+1, key.length() - 5) + " like '%" + obj + "%'");
				}else if(key.indexOf("_not_in") != -1 && key.indexOf("_not_in") == (key.length() - 7)){
					sb.append(key.substring(key.indexOf("_")+1, key.length() - 7) + " not in (" + obj + ")");
				}else if(key.indexOf("_in") != -1 && key.indexOf("_in") == (key.length() - 3)){
					sb.append(key.substring(key.indexOf("_")+1, key.length() - 3) + " in (" + obj + ")");
				}else if(key.indexOf("_<>")!=-1&&key.indexOf("_<>")==(key.length() - 3)){
					sb.append(key.substring(key.indexOf("_")+1, key.length() - 3) + " <> '" + obj + "'");
				}else if(key.indexOf("_>")!=-1&&key.indexOf("_>")==(key.length() - 3)){
					sb.append(key.substring(key.indexOf("_")+1, key.length() - 3)  + " > " +obj );
				}else if(key.indexOf("_<")!=-1&&key.indexOf("_<")==(key.length() -3)){
					sb.append(key.substring(key.indexOf("_")+1, key.length() - 3)  + " < " +obj );
				}else if(key.indexOf("_<=")!=-1&&key.indexOf("_<=")==(key.length() - 3)){
					sb.append(key.substring(key.indexOf("_")+1, key.length() - 3)  + " <= " +obj );
				}else if(key.indexOf("_>=")!=-1&&key.indexOf("_>=")==(key.length() - 3)){
					sb.append(key.substring(key.indexOf("_")+1, key.length() - 3)  + " >= " +obj );
				}else{
					if(obj instanceof String){
						sb.append(key.substring(key.indexOf("_")+1) + " = '" + obj + "'");
					}else{
						sb.append(key.substring(key.indexOf("_")+1) + " = " +obj );
					}
				}
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
	
}
