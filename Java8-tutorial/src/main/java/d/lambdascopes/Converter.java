package d.lambdascopes;

@FunctionalInterface
interface Converter<F, T> {
    T convert(F from);
}
