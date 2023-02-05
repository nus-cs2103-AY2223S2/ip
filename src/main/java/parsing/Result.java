package parsing;

import java.util.function.Function;
import java.util.function.Predicate;

import util.Either;
import util.Pair;

/**
 * Result of a parse by a Parser
 * 
 * @param <T> Wrapped type
 * @see Parser
 */
public class Result<T> {
    private final Either<Pair<T, String>, String> result;

    private Result(Either<Pair<T, String>, String> result) {
        this.result = result;
    }

    /**
     * @param res    Parse result
     * @param remain Remaining input after parse
     * @return Ok result
     */
    static <T> Result<T> ok(T res, String remain) {
        return new Result<>(Either.left(new Pair<>(res, remain)));
    }

    /**
     * @param error Error message
     * @return Error result
     */
    static <T> Result<T> error(String error) {
        return new Result<>(Either.right(error));
    }

    public Result<T> filterOrElse(Predicate<? super T> condition, String errorMsg) {
        return new Result<>(this.result.filterOrElse(pr -> condition.test(pr.first()), errorMsg));
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
