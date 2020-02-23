package ch01.net.bio;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author wenguoli
 * @description:
 * @date 2020/2/23 0023 17:20
 */
public class BIOServer {
    public static void main(String[] args) throws Exception{
        ServerSocket serverSocket = new ServerSocket(8080);
        System.out.println("服务器启动成功");
        while (!serverSocket.isClosed()) {
            Socket request = serverSocket.accept();// 阻塞
            System.out.println("收到新连接 : " + request.toString());
            try {
                // 接收数据、打印
                InputStream inputStream = request.getInputStream();
                Reader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader reader = new BufferedReader(inputStreamReader);
                String msg;
                while ((msg = reader.readLine()) != null) {
                    if (msg.length() == 0){
                        break;
                    }
                    System.out.println(msg);
                }
                System.out.println("收到数据,来自："+ request.toString());
            }catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    request.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        // 服务关闭
        serverSocket.close();
    }
}
