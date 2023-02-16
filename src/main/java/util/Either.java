package util;

import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Algebraic sum data type.
 * Can be either a valid left result
 * or an invalid right error.
 *
 * @param <L> Type of valid result
 * @param <R> Type of invalid result
 */
public interface Either<L, R> {
    static <L, R> Either<L, R> left(L l) {
        return new Either<>() {
            private final L left = l;

            @Override
            public String toString() {
                return String.format("Left(%s)",
                        this.left.toString());
            }

            @Override
            public boolean isLeft() {
                return true;
            }

            @Override
            public L fromLeft(L def) {
                return this.left;
            }

            @Override
            public R fromRight(R def) {
                return def;
            }

            @Override
            public <T> Either<T, R> map(Function<? super L, ? extends T> f) {
                return Either.left(f.apply(this.left));
            }

            @Override
            public <T> Either<T, R> flatMap(Function<? super L, ? extends Either<? extends T, ? extends R>> f) {
                return f.apply(this.left)
                        .match(
                                Either::left,
                                Either::right);
            }

            @Override
            public Either<L, R> filterOrElse(Predicate<? super L> tester, R failRes) {
                return tester.test(this.left) ? this : Either.right(failRes);
            }

            @Override
            public <T> T match(Function<? super L, ? extends T> l, Function<? super R, ? extends T> r) {
                return l.apply(this.left);
            }
        };
    }

    static <L, R> Either<L, R> right(R r) {
        return new Either<>() {
            private final R right = r;

            @Override
            public String toString() {
                return String.format("Right(%s)",
                        this.right.toString());
            }

            @Override
            public boolean isRight() {
                return true;
            }

            @Override
            public L fromLeft(L def) {
                return def;
            }

            @Override
            public R fromRight(R def) {
                return this.right;
            }

            @Override
            public <T> Either<T, R> map(Function<? super L, ? extends T> f) {
                return Either.right(this.right);
            }

            @Override
            public <T> Either<T, R> flatMap(Function<? super L, ? extends Either<? extends T, ? extends R>> f) {
                return Either.right(this.right);
            }

            @Override
            public Either<L, R> filterOrElse(Predicate<? super L> tester, R failRes) {
                return this;
            }

            @Override
            public <T> T match(Function<? super L, ? extends T> l, Function<? super R, ? extends T> r) {
                return r.apply(this.right);
            }
        };
    }

    default boolean isLeft() {
        return !isRight();
    }

    default boolean isRight() {
        return !isLeft();
    }

    /**
     * Extracts the left value if it is left, otherwise returns default value
     *
     * @param def Default value
     */
    L fromLeft(L def);

    /**
     * Extracts the right value if it is right, otherwise returns default value
     *
     * @param def Default value
     */
    R fromRight(R def);

    /**
     * If isLeft, maps the function over the wrapped object, otherwise returns
     * original right object
     *
     * @param <T> Left type of resultant Either
     * @param f   Mapping function
     * @return new Either object
     */
    <T> Either<T, R> map(Function<? super L, ? extends T> f);

    /**
     * Allows user to chain multiple methods that return Either objects.
     * If right object is returned within the chain, chaining will stop and right
     * object will be
     * returned at the end.
     *
     * @param <T> left type of resultant Either
     * @param f   Function that accepts left object and returns another Either
     *            object
     */
    <T> Either<T, R> flatMap(Function<? super L, ? extends Either<? extends T, ? extends R>> f);

    /**
     * Checks if left object satisfies predicate
     *
     * @param tester  Predicate to test left object
     * @param failRes Object if left object fails predicate
     * @return Left if original left object satisfies predicate, Right(failRes) if
     * not and the original right object if Either is originally right
     */
    Either<L, R> filterOrElse(Predicate<? super L> tester, R failRes);

    /**
     * Matches the appropriate function to produce new output
     *
     * @param <T> Type of the new object
     * @param l   Function to match if this is left
     * @param r   Function to match if this is right
     * @return new object
     */
    <T> T match(Function<? super L, ? extends T> l, Function<? super R, ? extends T> r);
}
