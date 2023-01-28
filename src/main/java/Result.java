import java.util.function.Function;
import java.util.function.Predicate;

public class Result<T> {
    private final Either<Pair<T, String>, String> result;

    private Result(Either<Pair<T, String>, String> result) {
        this.result = result;
    }

    static <T> Result<T> ok(T res, String remain) {
        return new Result<>(Either.left(new Pair<>(res, remain)));
    }

    static <T> Result<T> error(String error) {
        return new Result<>(Either.right(error));
    }

    public Result<T> filter(Predicate<T> condition) {
        if (this.result.isRight()) {
            return this;
        }
        if (condition.test(this.getRes())) {
            return this;
        }
        return error(
                String.format("%s failed to satisfy condition", this.getRes().toString()));
    }

    public Result<T> overrideMsg(String errorMsg) {
        if (this.isOk()) {
            return this;
        }
        return error(errorMsg);
    }

    public boolean isOk() {
        return this.result.isLeft();
    }

    public boolean isError() {
        return this.result.isRight();
    }

    public T getRes() {
        return this.result.fromLeft(null).first();
    }

    public String getRemainInp() {
        return this.result.fromLeft(null).second();
    }

    public String getErrorMsg() {
        return this.result.fromRight(null);
    }

    public <U> Result<U> map(Function<? super T, ? extends U> f) {
        return new Result<>(this.result.map(pr -> new Pair<>(f.apply(pr.first()), pr.second())));
    }

    @Override
    public String toString() {
        return this.result.toString();
    }
}
