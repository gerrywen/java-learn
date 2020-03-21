package ch18.rabbitmq.routing;

import ch18.rabbitmq.utils.ConnectionUtil;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.nio.charset.StandardCharsets;

/**
 * description:
 *
 * @author wenguoli
 * @date 2020/2/28 17:43
 */
public class Send {
    private static final String EXCHANGE_NAME = "direct_logs";


    public static void main(String[] args) throws Exception{
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);

        String[] strings = {"110", "hello world!哈哈!"};
        String severity = getSeverity(strings);
        String message = getMessage(strings);

        channel.basicPublish(EXCHANGE_NAME, severity, null, message.getBytes(
                StandardCharsets.UTF_8));
        System.out.println(" [x] Sent '" + severity + "':'" + message + "'");

        //关闭通道和连接
        channel.close();
        connection.close();
    }

    private static String getSeverity(String[] strings) {
        if (strings.length < 1)
            return "info";
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
        if (length <= startIndex) return "";
        StringBuilder words = new StringBuilder(strings[startIndex]);
        for (int i = startIndex + 1; i < length; i++) {
            words.append(delimiter).append(strings[i]);
        }
        return words.toString();
    }
}
