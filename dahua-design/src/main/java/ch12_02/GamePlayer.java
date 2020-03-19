package ch12_02;


/**
 * program: java-learn->GamePlayer
 * description:
 * author: gerry
 * created: 2020-03-19 10:59
 **/
public class GamePlayer implements IGamePlayer {
    private String name = "";

    /**
     * 我要代理谁
     */
    private IGamePlayer proxy = null;

    /**
     * 通过构造函数传递名称
     *
     * @param _name
     */
    public GamePlayer(String _name) {
        this.name = _name;
    }

    @Override
    public void login(String user, String password) {
        if (this.isProxy()) {
            System.out.println("登录名为" + user + "的用户" + this.name + "登录成功！");
        } else {
            System.out.println("请使用指定的代理访问");
        }
    }

    @Override
    public void killBoss() {
        if (this.isProxy()) {
            System.out.println(this.name + "在打怪！");
        } else {
            System.out.println("请使用指定的代理访问");
        }
    }

    @Override
    public void upgrade() {
        if (this.isProxy()) {
            System.out.println(this.name + "又升了一级！");
        } else {
            System.out.println("请使用指定的代理访问");
        }
    }

    @Override
    public IGamePlayer getProxy() {
        // 强制使用自己的代理，相当于安排秘书
        this.proxy = new GamePlayerProxy(this);
        return this.proxy;
    }

    /**
     * 校验是否是代理访问
     *
     * @return
     */
    private Boolean isProxy() {
        return this.proxy != null;
    }
}
