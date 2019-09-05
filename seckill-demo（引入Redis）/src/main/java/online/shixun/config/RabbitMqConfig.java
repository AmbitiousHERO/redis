package online.shixun.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Cy
 * @data 2019/9/4 - 21:45
 */
@Configuration
public class RabbitMqConfig {
    /**
     * 新建队列
     * @return
     */
    @Bean(name = "messages")
    public Queue queueMessages(){
        return new Queue("transmit");
    }

    /**
     * 定义交换机
     * @return
     */
    @Bean
    public TopicExchange exchange(){
        return new TopicExchange("exchange");
    }

    @Bean
    Binding bindingExchangeMessages(@Qualifier("messages") Queue queueMessages,TopicExchange exchange){
        return BindingBuilder.bind(queueMessages).to(exchange).with("transmit");
    }
}
