package chungus.util;

public class Pair<S, T> {
    private S first;
    private T second;

    public Pair(S _first, T _second) {
        first = _first;
        second = _second;
    }

    public S first() {
        return first;
    }

    public T second() {
        return second;
    }

    public static <S, T> Pair<S, T> of(S first, T second) {
        return new Pair<>(first, second);
    }
}
