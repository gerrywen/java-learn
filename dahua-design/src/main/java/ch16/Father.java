package ch16;

/**
 * program: java-learn->Father
 * description: 父亲类
 * author: gerry
 * created: 2020-03-21 20:09
 **/
public class Father extends Handler {
    //父亲只处理女儿的请求
    public Father() {
        super(Handler.FATHER_LEVEL_REQUEST);
    }

    // 父亲的答复
    @Override
    protected void response(IWomen women) {
        System.out.println("--------女儿向父亲请示-------");
        System.out.println(women.getRequest());
        System.out.println("父亲的答复是:同意\n");
    }

}
