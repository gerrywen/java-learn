package h.facademode;

import h.facademode.hometheater.HomeTheaterFacade;

public class MainTest {
    public static void main(String[] args) {
        HomeTheaterFacade mHomeTheaterFacade=new HomeTheaterFacade();

        mHomeTheaterFacade.ready();
        mHomeTheaterFacade.play();
    }
}
