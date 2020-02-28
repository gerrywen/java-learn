package ch02.rabbitmq.subscribe;

import ch02.rabbitmq.utils.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.nio.charset.StandardCharsets;

/**
 * description:
 *
 * @author wenguoli
 * @date 2020/2/28 16:35
 */
public class Send {
    private final static String EXCHANGE_NAME = "test_exchange_fanout";


    public static void main(String[] argv) throws Exception {
        // 获取到连接以及mq通道
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        // 声明exchange
        // 有几种可用的交换类型:direct、topic、headers和fanout。我们将关注最后一个——扇形展开。让我们创建一个这种类型的交换，
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

        // 消息内容
        for (int i = 0; i < 10; ++i) {
            String message = argv.length < 1 ? "info: Hello World!---" + i :
                    String.join(" ", argv);
            channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes(StandardCharsets.UTF_8));
            System.out.println(" [x] Sent '" + message + "'");
        }



        // 关闭连接
        channel.close();
        connection.close();
    }
}
