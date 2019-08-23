package d.lambdascopes;


public class Main1 {
    public static void main(String[] args) {
        // 我们可以直接在 lambda 表达式中访问外部的局部变量：
//        final int num = 1;

        // 但是和匿名对象不同的是，这里的变量num可以不用声明为final，该代码同样正确：
        int num = 1;

        // 传入整形 返回 字符串
        Converter<Integer, String> stringConverter = (from) -> String.valueOf(from + num);
        String convert = stringConverter.convert(2);
        System.out.print(convert);

        // 不过这里的 num 必须不可被后面的代码修改（即隐性的具有final的语义），例如下面的就无法编译：
//        num = 3;//在lambda表达式中试图修改num同样是不允许的。
        // 报错：Error:(13, 86) java: 从lambda 表达式引用的本地变量必须是最终变量或实际上的最终变量


    }
}
