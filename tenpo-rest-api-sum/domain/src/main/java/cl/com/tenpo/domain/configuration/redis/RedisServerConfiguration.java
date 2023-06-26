package cl.com.tenpo.domain.configuration.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisServerConfiguration {

    @Value(value = "${parameter.cache.redis.hostname}")
    private String parameterCacheRedisHostname;

    @Value(value = "${parameter.cache.redis.port}")
    private Integer parameterCacheRedisPort;

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        return template;
    }

    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        JedisConnectionFactory jedisConFactory
                = new JedisConnectionFactory();
        jedisConFactory.setHostName(parameterCacheRedisHostname);
        jedisConFactory.setPort(parameterCacheRedisPort);
        jedisConFactory.getPoolConfig().setMaxIdle(30);
        jedisConFactory.getPoolConfig().setMinIdle(10);
        return jedisConFactory;
    }
}
