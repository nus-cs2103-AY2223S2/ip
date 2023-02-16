package util;

/**
 * Pair of 2 objects
 *
 * @param <T> Type of first object
 * @param <U> Type of second object
 */
public class Pair<T, U> {
    private final T first;
    private final U second;

    /**
     * @param first  First object
     * @param second Second object
     */
    public Pair(T first, U second) {
        this.first = first;
        this.second = second;
    }

    /**
     * @return First object
     */
    public T first() {
        return this.first;
    }

    /**
     * @return Second object
     */
    public U second() {
        return this.second;
    }

    @Override
    public String toString() {
        return String.format("<%s, %s>",
                this.first.toString(),
                this.second.toString());
    }
}
