package ch01.net.nio;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * description:
 *
 * @author wenguoli
 * @date 2020/2/25 9:54
 */
public class NioHttpServer {

    // 端口号
    private static final int port = 8880;

    // 选择器，用于监听多个通道的事件，单个线程可监听多个数据通道
    private static Selector selector;

    // 定义线程池
    private static final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(25, 25, 25,
            TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());

    // 服务器端通道
    private static ServerSocketChannel socketChannel;


    public static void main(String[] args) throws Exception {
        // 打开通道
        socketChannel = ServerSocketChannel.open();
        socketChannel.configureBlocking(false);
        socketChannel.bind(new InetSocketAddress(port));

        System.out.println("NIO启动:" + port);
        // 获取一个选择器
        // 底层的事件通知机制
        // 老板娘 selector
        NioHttpServer.selector = Selector.open();

        // 登记： 表示对这个通道上OP_ACCEPT事件感兴趣，并且返回一个标记
        // 此处表示，希望收到socket通道8080端口上建立连接这个通知
        SelectionKey selectionKey = socketChannel.register(NioHttpServer.selector, 0);
        selectionKey.interestOps(SelectionKey.OP_ACCEPT);

        while (true) {// 带几个美女，坐在大厅
            // 如果没有新的socket与服务器有连接或者是数据交互，这里就会等待1秒
            NioHttpServer.selector.select(1000L);

            // 开始处理
            Set<SelectionKey> selected = NioHttpServer.selector.selectedKeys();
            Iterator<SelectionKey> iter = selected.iterator();
            while (iter.hasNext()) {
                SelectionKey key = iter.next();

                if (key.isAcceptable()) { // 判断是否OP_ACCEPT的通知
                    // 处理连接
                    System.out.println("有新的连接啦，当前线程数量:"
                            + NioHttpServer.threadPoolExecutor.getActiveCount());

                    // 有新的连接，赶紧接客
                    SocketChannel chan = socketChannel.accept();
                    // 问一下价格多少，需要什么样服务...
                    chan.configureBlocking(false); // 非阻塞
                    // 注册一个新监听。
                    // 表示希望收到该连接上OP_READ数据传输事件的通知
                    chan.register(NioHttpServer.selector, SelectionKey.OP_READ);
                } else if (key.isReadable()) {
                    // 取出附着在上面的信息，也就是上面代码中附着的连接信息
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    // 处理中，不需要收到任何通知
                    key.cancel();
                    // tomcat 大保健旗舰店 有200技师，只有付钱的客户才会享受技师 泰式、保shen，
                    socketChannel.configureBlocking(false);
                    //线程处理
                    NioHttpServer.threadPoolExecutor.execute(() -> {
                        try {
                            // 读取里面的内容，请注意，此处大小随意写的。
                            // tomcat中会根据Http协议中定义的长度来读取数据，或者一直读到通道内无数据为止
                            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                            socketChannel.read(byteBuffer);
                            byteBuffer.flip(); // 转为读模式
                            String request = getString(byteBuffer);
                            System.out.println("收到新数据，当前线程数："
                                    + NioHttpServer.threadPoolExecutor.getActiveCount()
                                    + "，请求内容：" + request);
                            // 给一个当前时间作为返回值
                            // 随意返回，不是Http的协议
                            byteBuffer.clear();

                            ByteBuffer wrap = ByteBuffer.wrap(("tony" + System.currentTimeMillis()).getBytes());
                            socketChannel.write(wrap);
                            wrap.clear();
                            socketChannel.configureBlocking(false);
                            // 注册一个新监听。 表示希望收到该连接上OP_READ事件的通知
                            socketChannel.register(NioHttpServer.selector,
                                    SelectionKey.OP_READ);

                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        System.out.println(Thread.currentThread().getName() + " 服务器线程处理完毕，当前线程数："
                                + threadPoolExecutor.getActiveCount());
                    });
                }

                // 取出后删除
                iter.remove();
            }

            selected.clear();
            // 过掉cancelled keys
            NioHttpServer.selector.selectNow();
        }

    }

    /**
     * ByteBuffer 转换 String
     * @param buffer
     * @return
     */
    private static String getString(ByteBuffer buffer)
    {
        Charset charset = null;
        CharsetDecoder decoder = null;
        CharBuffer charBuffer = null;
        try
        {
            charset = Charset.forName("UTF-8");
            decoder = charset.newDecoder();
            // charBuffer = decoder.decode(buffer);//用这个的话，只能输出来一次结果，第二次显示为空
            charBuffer = decoder.decode(buffer.asReadOnlyBuffer());
            return charBuffer.toString();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return "";
        }
    }
}
