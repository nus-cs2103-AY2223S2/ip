package parsing;

import java.util.function.Function;
import java.util.function.Predicate;

import util.Either;
import util.Pair;

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
        return this.result.match(
                pr -> condition.test(pr.first()) ? this
                        : error(String.format("%s failed to satisfy condition.", pr.first())),
                errorMsg -> this);
    }

    public Result<T> overrideMsg(String errorMsg) {
        return this.result.match(
                pr -> this,
                msg -> error(errorMsg));
    }

    public boolean isOk() {
        return this.result.isLeft();
    }

    public boolean isError() {
        return this.result.isRight();
    }

    // public T getRes() {
    // return this.result.fromLeft(null).first();
    // }

    // public String getRemainInp() {
    // return this.result.fromLeft(null).second();
    // }

    // public String getErrorMsg() {
    // return this.result.fromRight(null);
    // }

    public <U> U match(Function<? super Pair<? extends T, ? extends String>, ? extends U> okFunction,
            Function<? super String, ? extends U> errorFunction) {
        return this.result.match(okFunction, errorFunction);
    }

    public <U> Result<U> map(Function<? super T, ? extends U> f) {
        return new Result<>(this.result.map(pr -> new Pair<>(f.apply(pr.first()), pr.second())));
    }

    @Override
    public String toString() {
        return this.result.match(
                pr -> String.format("Ok(%s, %s)", pr.first(), pr.second()),
                msg -> String.format("Error(%s)", msg));
    }
}
