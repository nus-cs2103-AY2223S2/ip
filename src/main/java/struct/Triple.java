package struct;

public final class Triple<A, B, C> {
    private final A a;
    private final B b;
    private final C c;

    public Triple(A a, B b, C c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    /**
     * Returns the first value.
     * @return The first value.
     */
    public A getFirst() {
        return this.a;
    }

    /**
     * Returns the second value.
     * @return The second value.
     */
    public B getSecond() {
        return this.b;
    }

    /**
     * Returns the third value.
     * @return The third value.
     */
    public C getThird() {
        return this.c;
    }
}
