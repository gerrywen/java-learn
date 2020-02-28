package ch02.rabbitmq.work2;

import ch02.rabbitmq.utils.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;

import java.nio.charset.StandardCharsets;

/**
 * description:
 *
 * @author wenguoli
 * @date 2020/2/28 15:23
 */
public class Send {

    private final static String QUEUE_NAME = "test_queue_work";

    public static void main(String[] argv) throws Exception {
        // 获取到连接以及mq通道
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        // 声明队列
        boolean durable = true;
        channel.queueDeclare(QUEUE_NAME, durable, false, false, null);

        for (int i = 0; i < 100; i++) {
            // 消息内容
            String message = "" + i;

            // 此时，我们确信即使RabbitMQ重新启动，task_queue队列也不会丢失。
            // 现在，我们需要将消息标记为persistent—通过将MessageProperties(它实现了BasicProperties)设置为PERSISTENT_TEXT_PLAIN值
            channel.basicPublish("", QUEUE_NAME,
                    MessageProperties.PERSISTENT_TEXT_PLAIN,
                    message.getBytes(StandardCharsets.UTF_8));
            System.out.println(" [x] Sent '" + message + "'");

            Thread.sleep(i * 10);
        }

        channel.close();
        connection.close();
    }
}
