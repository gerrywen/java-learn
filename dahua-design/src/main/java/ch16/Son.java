package ch16;

/**
 * program: java-learn->Son
 * description:
 * author: gerry
 * created: 2020-03-21 20:10
 **/
public class Son extends Handler {
    //儿子只处理母亲的请求
    public Son() {
        super(Handler.SON_LEVEL_REQUEST);
    }

    // 儿子的答复
    @Override
    protected void response(IWomen women) {
        System.out.println("--------母亲向儿子请示-------");
        System.out.println(women.getRequest());
        System.out.println("儿子的答复是：同意\n");
    }
}
