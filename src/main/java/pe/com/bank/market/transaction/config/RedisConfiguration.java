package pe.com.bank.market.transaction.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.data.redis.core.ReactiveRedisTemplate;

import pe.com.bank.market.transaction.redis.BuySellRate;

@Configuration
public class RedisConfiguration {
	
	@Bean
    ReactiveRedisOperations<String, BuySellRate> reactiveRedisOperations(
            ReactiveRedisConnectionFactory factory) {
		
		Jackson2JsonRedisSerializer<BuySellRate> serializer = new Jackson2JsonRedisSerializer<>(BuySellRate.class);
		
		RedisSerializationContext.RedisSerializationContextBuilder<String, BuySellRate> builder =
		        RedisSerializationContext.newSerializationContext(new StringRedisSerializer());
		
		RedisSerializationContext<String, BuySellRate> context = builder.value(serializer).build();
		
        return new ReactiveRedisTemplate<>(factory,context);
    }
	


}
