package e.factory.pizza;

public abstract class Pizza {
    protected String name;

    /**
     * 抽象方法
     */
    public abstract void prepare();

    public void bake() {
        System.out.println(name + " baking;");
    }

    public void cut() {
        System.out.println(name + " cutting;");
    }

    public void box() {
        System.out.println(name + " boxing;");
    }

    public void setname(String name) {
        this.name = name;
    }
}
