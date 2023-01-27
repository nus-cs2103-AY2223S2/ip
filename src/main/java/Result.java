import java.util.function.Function;

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

    boolean isOk() {
        return this.result.isLeft();
    }

    boolean isError() {
        return this.result.isRight();
    }

    T getRes() {
        return this.result.fromLeft(null).first();
    }

    String getRemainInp() {
        return this.result.fromLeft(null).second();
    }

    String getErrorMsg() {
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
