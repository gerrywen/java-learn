package ch15;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


/**
 * program: java-learn->PropertyUtil
 * description:
 * author: gerry
 * created: 2020-03-16 22:34
 **/
public class PropertyUtil {
    private static Properties props;


    synchronized static private void loadProps() {
        System.out.println("开始加载properties文件内容.......");

        props = new Properties();
        InputStream in = null;
        try {
            // 第一种，通过类加载器进行获取properties文件流
            in = PropertyUtil.class.getClassLoader().getResourceAsStream("jdbc.properties");

            // 第二种，通过类进行获取properties文件流
//            in = PropertyUtil.class.getResourceAsStream("jdbc.properties");

            props.load(in);
        } catch (FileNotFoundException e) {
            System.out.println("jdbc.properties文件未找到");
        } catch (IOException e) {
            System.out.println("出现IOException");
        } finally {
            try {
                if (null != in) {
                    // 关闭文件流
                    in.close();
                }
            } catch (IOException e) {
                System.out.println("jdbc.properties文件流关闭出现异常");
            }
        }

        System.out.println("加载properties文件内容完成...........");
        System.out.println("properties文件内容：" + props);
    }

    /**
     * 获取属性信息
     * @param key 键
     * @return 值
     */
    public static String getProperty(String key){
        if(null == props) {
            loadProps();
        }
        return props.getProperty(key);
    }

    /**
     * 获取属性信息，没有
     * @param key 键
     * @param defaultValue 默认值
     * @return 值
     */
    public static String getProperty(String key, String defaultValue) {
        if(null == props) {
            loadProps();
        }
        return props.getProperty(key, defaultValue);
    }
}
