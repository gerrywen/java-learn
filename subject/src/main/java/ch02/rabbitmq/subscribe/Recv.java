package ch02.rabbitmq.subscribe;

import ch02.rabbitmq.utils.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DeliverCallback;

import java.nio.charset.StandardCharsets;

/**
 * description:
 *
 * @author wenguoli
 * @date 2020/2/28 16:39
 */
public class Recv {

    // 交换机
    private final static String EXCHANGE_NAME = "test_exchange_fanout";

    public static void main(String[] argv) throws Exception {
        // 获取到连接以及mq通道
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();


        // 声明交换机
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

        // 绑定队列到交换机
        String queueName = channel.queueDeclare().getQueue(); // 获取队列名称
        channel.queueBind(queueName, EXCHANGE_NAME, "");

        // 同一时刻服务器只会发一条消息给消费者
//        channel.basicQos(1);

        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            System.out.println(" [x] Received '" + message + "'");
        };
        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {
        });

    }
}
