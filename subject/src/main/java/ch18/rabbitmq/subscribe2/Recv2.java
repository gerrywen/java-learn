package ch18.rabbitmq.subscribe2;

import ch18.rabbitmq.utils.ConnectionUtil;
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
public class Recv2 {

    private final static String QUEUE_NAME = "test_queue_work2";

    // 交换机
    private final static String EXCHANGE_NAME = "test_exchange_fanout";

    public static void main(String[] argv) throws Exception {
        // 获取到连接以及mq通道
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        // 声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        // 声明交换机
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

        // 绑定队列到交换机
//        String queueName = channel.queueDeclare().getQueue(); // 获取队列名称
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "");

        // 同一时刻服务器只会发一条消息给消费者
        channel.basicQos(1);

        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            System.out.println(" [x] Received '" + message + "'");
            //开启这行 表示使用手动确认模式
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
        };
        // 监听队列，false表示手动返回完成状态，true表示自动
        boolean autoAck = false; // acknowledgment is covered below
        channel.basicConsume(QUEUE_NAME, autoAck, deliverCallback, consumerTag -> {
        });

    }
}
