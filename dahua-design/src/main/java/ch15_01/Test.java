package ch15_01;

/**
 * description:
 *
 * @author wenguoli
 * @date 2020/3/17 10:27
 */
public class Test {

    public static void main(String[] args) {
//        test1();
//        test2();
        String className = PropertyUtil.createResourcePath().getProperty("className");
        try {
            String packageName = Test.class.getName().replaceAll("\\.\\w+", "");//去 掉.类名
            className = packageName + "." + className;
            // 通过类名生成实例对象并将其返回
            Class c = Class.forName(className);
            IFruitFactory ff = (IFruitFactory) c.newInstance();
            IFruit apple = ff.getApple();
            apple.get();

            IFruit banana = ff.getBanana();
            banana.get();
            System.out.println("~~~~~~~~~~~~~~~~~~~~");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private static void test2() {
        Test obj = new Test();
        //包名
        String packageName = obj.getClass().getName().replaceAll("\\.\\w+", "");//去 掉.类名
        //类名:
        String className = obj.getClass().getName().replaceAll(".*\\.", "");//去掉 包名.

        System.out.println(packageName);
        System.out.println(className);

        //或者
        String classFullName = obj.getClass().getName();
        String packageName1 = classFullName.substring(0, classFullName.lastIndexOf(".") - 1);
        String className1 = classFullName.substring(classFullName.lastIndexOf("."));

        System.out.println(packageName1);
        System.out.println(className1);
    }

    private static void test1() {
        IFruitFactory ff = new NorthFruitFactory();
        IFruit apple = ff.getApple();
        apple.get();

        IFruit banana = ff.getBanana();
        banana.get();

        System.out.println("~~~~~~~~~~~~~~~~~~~~");
        IFruitFactory bb = new SouthFruitFactory();
        IFruit apple2 = bb.getApple();
        apple2.get();

        IFruit banana2 = bb.getBanana();
        banana2.get();

        System.out.println("~~~~~~~~~~~~~~~~~~~~");
        //比如要增加室内innerApple,InnerBanana
        IFruitFactory cc = new InnerFruitFactory();
        IFruit apple3 = cc.getApple();
        apple3.get();
        IFruit banana3 = cc.getBanana();
        banana3.get();
    }
}
