package ch12_02;

/**
 * program: java-learn->IGamePlayer
 * description: 游戏者接口
 * author: gerry
 * created: 2020-03-19 10:54
 **/
public interface IGamePlayer {
    /**
     * 登录游戏
     * @param user
     * @param password
     */
    public void login(String user, String password);

    /**
     * 杀怪，网络游戏的主要特色
     */
    public void killBoss();

    /**
     * 升级
     */
    public void upgrade();

    /**
     * 每个人都可以找一下自己的代理
     * @return
     */
    public IGamePlayer getProxy();
}
