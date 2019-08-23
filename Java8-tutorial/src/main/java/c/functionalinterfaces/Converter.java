package c.functionalinterfaces;

@FunctionalInterface
interface Converter<F, T> {
    T convert(F from);
}
