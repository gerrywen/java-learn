package e.builtinfunctionalinterfaces.functions;

@FunctionalInterface
public interface Function<T,R> {
    //将Function对象应用到输入的参数上，然后返回计算结果。
    R apply(T t);

}
