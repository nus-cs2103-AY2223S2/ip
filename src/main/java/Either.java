import java.util.function.Function;

public interface Either<L, R> {
    public boolean isLeft();

    public boolean isRight();

    public L fromLeft(L def);

    public R fromRight(R def);

    public <T> Either<T, R> map(Function<? super L, ? extends T> f);

    public <T> Either<T, R> flatMap(Function<? super L, ? extends Either<? extends T, R>> f);

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
            public boolean isRight() {
                return false;
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
            public <T> Either<T, R> flatMap(Function<? super L, ? extends Either<? extends T, R>> f) {
                Either<? extends T, R> temp = f.apply(this.left);
                if (temp.isLeft()) {
                    return Either.left(temp.fromLeft(null));
                }
                return Either.right(temp.fromRight(null));
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
            public boolean isLeft() {
                return false;
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
            public R fromRight(R deg) {
                return this.right; 
            }

            @Override
            public <T> Either<T, R> map(Function<? super L, ? extends T> f) {
                return Either.<T, R>right(this.right);
            }

            @Override
            public <T> Either<T, R> flatMap(Function<? super L, ? extends Either<? extends T, R>> f) {
                return Either.<T, R>right(this.right);
            }
        };
    }
}
