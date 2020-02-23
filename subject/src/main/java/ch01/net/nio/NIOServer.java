package ch01.net.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @author wenguoli
 * @description: 直接基于非阻塞的写法
 * @date 2020/2/23 0023 17:47
 */
public class NIOServer {
    public static void main(String[] args) throws Exception {
        // 创建网络服务端
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);// 设置为非阻塞模式
        SocketAddress port = new InetSocketAddress(8080); // 绑定端口
        serverSocketChannel.socket().bind(port);
        System.out.println("启动成功");
        while (true) {
            SocketChannel socketChannel = serverSocketChannel.accept();
            // tcp请求 读取/响应
            if (socketChannel != null){
                System.out.println("收到新连接 : " + socketChannel.getRemoteAddress());
                socketChannel.configureBlocking(false); // 默认是阻塞的,一定要设置为非阻塞
                try {
                    ByteBuffer requestBuffer = ByteBuffer.allocate(1024);
                    // 判断通道是否打开并读取到数据
                    while (socketChannel.isOpen() && socketChannel.read(requestBuffer) != -1) {
                        // 长连接情况下,需要手动判断数据有没有读取结束 (此处做一个简单的判断: 超过0字节就认为请求结束了)
                        if (requestBuffer.position() > 0) break;
                    }
                    if(requestBuffer.position() == 0) continue; // 如果没数据了, 则不继续后面的处理
                    requestBuffer.flip(); // 就能把buffer的当前位置更改为buffer缓冲区的第一个位置

                    byte[] content =  new byte[requestBuffer.limit()];
                    requestBuffer.get(content);

                    System.out.println(new String(content));
                    System.out.println("收到数据,来自："+ socketChannel.getRemoteAddress());

                    // 响应结果 200
                    String response = "HTTP/1.1 200 OK\r\n" +
                            "Content-Length: 11\r\n\r\n" +
                            "Hello World";
                    ByteBuffer buffer = ByteBuffer.wrap(response.getBytes());
                    while (buffer.hasRemaining()) {
                        socketChannel.write(buffer);// 非阻塞
                    }

                }catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        // 用到了非阻塞的API, 在设计上,和BIO可以有很大的不同.继续改进
    }
}
