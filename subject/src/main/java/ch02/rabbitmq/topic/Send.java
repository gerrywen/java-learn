package ch02.rabbitmq.topic;

import ch02.rabbitmq.utils.ConnectionUtil;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.nio.charset.StandardCharsets;

/**
 * program: java-learn->Send
 * description:
 * author: gerry
 * created: 2020-02-29 22:03
 **/
public class Send {
    private static final String EXCHANGE_NAME = "topic_logs";

    public static void main(String[] args) throws Exception{
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        // 话题交互机
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);

//        String[] strings = {"#", "hello world!#!"};
        String[] strings = {"kern.critical", "A critical kernel error"};

        String routingKey = getRouting(strings);
        String message = getMessage(strings);

        channel.basicPublish(EXCHANGE_NAME, routingKey , null, message.getBytes(StandardCharsets.UTF_8));

        System.out.println(" [x] Sent '" + routingKey + "':'" + message + "'");

    }

    private static String getRouting(String[] strings) {
        if (strings.length < 1)
            return "anonymous.info";
        return strings[0];
    }

    private static String getMessage(String[] strings) {
        if (strings.length < 2)
            return "Hello World!";
        return joinStrings(strings, " ", 1);
    }

    private static String joinStrings(String[] strings, String delimiter, int startIndex) {
        int length = strings.length;
        if (length == 0) return "";
        if (length < startIndex) return "";
        StringBuilder words = new StringBuilder(strings[startIndex]);
        for (int i = startIndex + 1; i < length; i++) {
            words.append(delimiter).append(strings[i]);
        }
        return words.toString();
    }
}
