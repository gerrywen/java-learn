package ch12_02;


/**
 * program: java-learn->GamePlayerProxy
 * description:
 * author: gerry
 * created: 2020-03-19 11:02
 **/
public class GamePlayerProxy implements IGamePlayer {
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
    }

    /**
     * 代理的代理暂时还没有，就是自己
     * @return
     */
    @Override
    public IGamePlayer getProxy() {
        return this;
    }
}
