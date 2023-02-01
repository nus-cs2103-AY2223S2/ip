package util;

import java.util.function.Function;
import java.util.function.Predicate;

public interface Either<L, R> {
    default public boolean isLeft() {
        return !isRight();
    }

    default public boolean isRight() {
        return !isLeft();
    };

    public L fromLeft(L def);

    public R fromRight(R def);

    public <T> Either<T, R> map(Function<? super L, ? extends T> f);

    public <T> Either<T, R> flatMap(Function<? super L, ? extends Either<? extends T, ? extends R>> f);

    public Either<L, R> filterOrElse(Predicate<? super L> tester, R failRes);

    public <T> T match(Function<? super L, ? extends T> l, Function<? super R, ? extends T> r);

    public static <L, R> Either<L, R> left(L l) {
        return new Either<L, R>() {
            private final L left = l;

            @Override
            public String toString() {
                return String.format("Left(%s)", this.left.toString());
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
                return f.apply(this.left).match(
                    left -> Either.left(left),
                    right -> Either.right(right)
                );
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

    public static <L, R> Either<L, R> right(R r) {
        return new Either<L, R>() {
            private final R right = r;

            @Override
            public String toString() {
                return String.format("Right(%s)", this.right.toString());
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
                return Either.<T, R>right(this.right);
            }

            @Override
            public <T> Either<T, R> flatMap(Function<? super L, ? extends Either<? extends T, ? extends R>> f) {
                return Either.<T, R>right(this.right);
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
}
