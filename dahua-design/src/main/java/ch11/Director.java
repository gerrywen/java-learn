package ch11;

import java.util.ArrayList;

/**
 * program: java-learn->Director
 * description: 导演类
 * author: gerry
 * created: 2020-03-21 12:32
 **/
public class Director {
    private ArrayList<String> sequence = new ArrayList();
    private BenzBuilder benzBuilder = new BenzBuilder();
    private BMWBuilder bmwBuilder = new BMWBuilder();

    /**
     * A类型的奔驰车模型，先start，然后stop，其他什么引擎、喇叭一概没有
     *
     * @return
     */
    public BenzModel getABenzModel() {
        //清理场景，这里是一些初级程序员不注意的地方
        this.sequence.clear();
        //ABenzModel的执行顺序
        this.sequence.add("start");
        this.sequence.add("stop");
        //按照顺序返回一个奔驰车
        this.benzBuilder.setSequence(this.sequence);
        return (BenzModel) this.benzBuilder.getCarModel();
    }

    /**
     * B型号的奔驰车模型，是先发动引擎，然后启动，然后停止，没有喇叭
     *
     * @return
     */
    public BenzModel getBBenzModel() {
        this.sequence.clear();
        this.sequence.add("engine boom");
        this.sequence.add("start");
        this.sequence.add("stop");
        this.benzBuilder.setSequence(this.sequence);
        return (BenzModel) this.benzBuilder.getCarModel();
    }

    /**
     * C型号的宝马车是先按下喇叭（炫耀嘛），然后启动，然后停止
     *
     * @return
     */
    public BMWModel getCBMWModel() {
        this.sequence.clear();
        this.sequence.add("alarm");
        this.sequence.add("start");
        this.sequence.add("stop");
        this.bmwBuilder.setSequence(this.sequence);
        return (BMWModel) this.bmwBuilder.getCarModel();
    }

    /**
     * D类型的宝马车只有一个功能，就是跑，启动起来就跑，永远不停止
     *
     * @return
     */
    public BMWModel getDBMWModel() {
        this.sequence.clear();
        this.sequence.add("start");
        this.bmwBuilder.setSequence(this.sequence);
        return (BMWModel) this.benzBuilder.getCarModel();
    }
    /**
     * 这里还可以有很多方法，你可以先停止，然后再启动，或者一直停着不动，静态的嘛 * 导演类嘛，按照什么顺序是导演说了算
     */

}
