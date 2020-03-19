package ch12_03;

/**
 * program: java-learn->GamePlayerProxy
 * description:
 * author: gerry
 * created: 2020-03-19 11:02
 **/
public class GamePlayerProxy implements IGamePlayer, IProxy {
    private IGamePlayer gamePlayer = null;

    /**
     * 通过构造函数传递要对谁进行代练
     *
     * @param _gamePlayer
     */
    public GamePlayerProxy(IGamePlayer _gamePlayer) {
        this.gamePlayer = _gamePlayer;
    }

    @Override
    public void login(String user, String password) {
        this.gamePlayer.login(user, password);
    }

    @Override
    public void killBoss() {
        this.gamePlayer.killBoss();
    }

    @Override
    public void upgrade() {
        this.gamePlayer.upgrade();
        this.count();
    }

    @Override
    public void count() {
        System.out.println("升级总费用是：150元");
    }
}
