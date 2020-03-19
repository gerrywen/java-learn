package ch12_01;

import ch12.IGamePlayer;

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
     * @param name
     */
    public GamePlayerProxy(String name) {
        try {
            gamePlayer = new GamePlayer(this, name);
        }catch (Exception e) {
            e.printStackTrace();
        }
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
}
