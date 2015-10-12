package com.common.template.redis.dao;

import java.util.Set;

public interface IRedisDao {
	/**
	 * 每次增长1
	 * @param key
	 * @return
	 */
	long increase(String key);
	
	/**
	 * 每次增长n
	 * @param key
	 * @param count 增长数量
	 * @return
	 */
	long increase(String key,int count);
	
	/**
	 * set里放入值
	 * @param key
	 * @param value
	 * @return
	 */
	long sAdd(String key,String value);
	
	/**
	 * 后一个集合并入到前一个集合中
	 * @param key
	 * @param otherKey
	 * @return
	 */
	long sUnionAndStore(String key,String otherKey);
	
	
	/**
	 * 交叉的集合
	 * @param key
	 * @param otherKey
	 * @return
	 */
	Set<String> sIntersect(String key,String otherKey);
	
	/**
	 * 删除key
	 * @param key
	 */
	void delete(String key);
	
	/**
	 * 设置过期时间
	 * @param key
	 * @param timeout 小时
	 * @return
	 */
	boolean expire(String key,long timeout);
	
	/**
	 * 模糊匹配查找键集合
	 * @param pattern
	 * @return
	 */
	Set<String> keys(String pattern);
	
	/**
	 * set的大小
	 * @param key
	 * @return
	 */
	long sSize(String key);
	
	/**
	 * 根据key获取值
	 * @param key
	 * @return
	 */
	String get(String key);
	
	/**
	 * string set opt
	 * @Date 2014-8-28
	 * @param key 设计原则（表名：主键id：列名）
	 * @param value	设计原则（对于列的值）
	 */
	void set(String key, String value);

}
