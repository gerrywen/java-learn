package ch18.rabbitmq.utils;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * description: 获取MQ的连接工具类
 *
 * @author wenguoli
 * @date 2020/2/28 10:53
 */
public class ConnectionUtil {

    public static Connection getConnection() throws Exception {
        ConnectionFactory factory  = new ConnectionFactory();
        //设置服务地址
        factory.setHost("47.98.184.122");
        //设置端口
        factory.setPort(5672);
        // 设置账号信息，用户名、密码、vhost
        factory.setVirtualHost("testhost");
        factory.setUsername("admin");
        factory.setPassword("admin");
        // 通过工程获取连接
        return factory.newConnection();
    }
}
