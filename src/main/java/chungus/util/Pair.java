package chungus.util;

/**
 * A general pair class.
 */
public class Pair<S, T> {
    private S first;
    private T second;

    /**
     * Constructor for a pair.
     * 
     * @param first  The first item.
     * @param second The second item.
     */
    public Pair(S first, T second) {
        this.first = first;
        this.second = second;
    }

    /**
     * Gets the first item.
     * 
     * @return The first item.
     */
    public S first() {
        return first;
    }

    /**
     * Gets the second item.
     * 
     * @return The second item.
     */
    public T second() {
        return second;
    }

    /**
     * Builds a pair. Just a convenience method
     * 
     * @param first  The first item.
     * @param second The second item.
     * @param <S>    Type for the first item.
     * @param <T>    Type for the second item.
     * @return The new pair.
     */
    public static <S, T> Pair<S, T> of(S first, T second) {
        return new Pair<>(first, second);
    }
}
