package ch01.net.bio;

import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.Scanner;

/**
 * @author wenguoli
 * @description:
 * @date 2020/2/23 0023 17:17
 */
public class BIOClient {
    private static Charset charset = Charset.forName("UTF-8");

    public static void main(String[] args) throws Exception{
        Socket socket = new Socket("127.0.0.1", 8000);
        OutputStream outputStream = socket.getOutputStream();

        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入：");
        String msg = scanner.nextLine();
        outputStream.write(msg.getBytes(charset)); // 阻塞，写完成
        scanner.close(); // 关闭写

        socket.close(); // 关闭socket
    }
}
