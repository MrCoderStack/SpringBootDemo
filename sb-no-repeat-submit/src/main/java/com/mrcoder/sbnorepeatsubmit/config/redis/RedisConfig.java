package com.mrcoder.sbnorepeatsubmit.config.redis;

import com.mrcoder.sbnorepeatsubmit.util.RedisCacheUtil;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @Description: Redis配置类
 */
@Configuration
@ConditionalOnClass({ RedisTemplate.class })
public class RedisConfig {

	/**
	 * Redis操作模板配置
	 * 
	 * @param connectionFactory
	 * @return
	 */
	@Bean
	public RedisTemplate<?, ?> redisTemplate(RedisConnectionFactory connectionFactory) {
		RedisTemplate<Object, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(connectionFactory);
		// key的序列化方式
		template.setKeySerializer(new StringRedisSerializer());
		// value序列化方式
		template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
		return template;
	}

	/**
	 * 注入Redis操作工具类
	 * 
	 * @param redisTemplate
	 * @return
	 */
	@Bean
	public RedisCacheUtil redisCacheUtil(@Qualifier("redisTemplate") RedisTemplate<String, Object> redisTemplate) {
		return new RedisCacheUtil(redisTemplate);
	}

	@Bean
	public RedisTemplate<?, ?> redisTemplate(RedisConnectionFactory connectionFactory, Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer) {
		RedisTemplate<byte[], byte[]> template = new RedisTemplate<byte[], byte[]>();
		template.setConnectionFactory(connectionFactory);
		// 设置key/hashkey序列化
		RedisSerializer<String> stringSerializer = new StringRedisSerializer();
		template.setKeySerializer(stringSerializer);
		template.setHashKeySerializer(stringSerializer);

		// 设置值序列化
		template.setValueSerializer(jackson2JsonRedisSerializer);
		template.setHashValueSerializer(jackson2JsonRedisSerializer);
		template.afterPropertiesSet();

		template.setKeySerializer(new StringRedisSerializer());
		template.afterPropertiesSet();

		return template;
	}
}
