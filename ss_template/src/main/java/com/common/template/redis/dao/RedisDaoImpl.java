package com.common.template.redis.dao;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

@Repository("redisDao")
public class RedisDaoImpl implements IRedisDao {
	
	@Resource
	private StringRedisTemplate stringRedisTemplate;

	@Override
	public long increase(String key) {
		return stringRedisTemplate.opsForValue().increment(key, 1);
	}

	@Override
	public long increase(String key, int count) {
		return stringRedisTemplate.opsForValue().increment(key, count);
	}

	@Override
	public long sAdd(String key, String value) {
		return stringRedisTemplate.opsForSet().add(key, value);
	}

	@Override
	public long sUnionAndStore(String key, String otherKey) {
		return stringRedisTemplate.opsForSet().unionAndStore(key, otherKey, key);
	}

	@Override
	public Set<String> sIntersect(String key, String otherKey) {
		Set<String> set = stringRedisTemplate.opsForSet().intersect(key, otherKey);
		if(set == null){
			set = new HashSet<String>();
		}
		return set;
	}

	@Override
	public void delete(String key) {
		stringRedisTemplate.delete(key);
	}

	@Override
	public boolean expire(String key, long timeout) {
		return stringRedisTemplate.expire(key, timeout, TimeUnit.HOURS);
	}

	@Override
	public Set<String> keys(String pattern) {
		if(pattern == null || pattern.trim().length() <= 0){
			return null;
		}
		return stringRedisTemplate.keys(pattern);
	}

	@Override
	public long sSize(String key) {
		return stringRedisTemplate.opsForSet().size(key);
	}

	@Override
	public String get(String key) {
		return stringRedisTemplate.opsForValue().get(key);
	}

	@Override
	public void set(String key, String value) {
		stringRedisTemplate.opsForValue().set(key, value);
	}

}
