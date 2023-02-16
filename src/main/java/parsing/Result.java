package parsing;

import util.Either;
import util.Pair;

import java.util.function.Function;
import java.util.function.Predicate;

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

    /**
     * Checks if wrapped object satisfies condition
     *
     * @param condition Condition to test
     * @param errorMsg  Error message if condition unsatisfied
     * @return New filtered result
     */
    public Result<T> filterOrElse(Predicate<? super T> condition, String errorMsg) {
        return new Result<>(this.result.filterOrElse(pr -> condition.test(pr.first()), errorMsg));
    }

    /**
     * Overrides message if this is Error
     *
     * @param errorMsg Message to override with
     * @return New result
     */
    public Result<T> overrideMsg(String errorMsg) {
        return this.result.match(
                pr -> this,
                msg -> error(errorMsg));
    }

    /**
     * @return true if this is ok
     */
    public boolean isOk() {
        return this.result.isLeft();
    }

    /**
     * @return true if this is error
     */
    public boolean isError() {
        return this.result.isRight();
    }

    /**
     * Converts result to new object based on 2 mapping functions
     *
     * @param <U>           Type of new object
     * @param okFunction    Function to convert Pair<parsedObject, remainingInput>
     *                      to new object if this is ok
     * @param errorFunction Function to conver error message to new object if this
     *                      is error
     * @return New object
     */
    public <U> U match(Function<? super Pair<? extends T, ? extends String>, ? extends U> okFunction,
                       Function<? super String, ? extends U> errorFunction) {
        return this.result.match(okFunction, errorFunction);
    }

    /**
     * Maps parsed object to new wrapped object if this is ok
     *
     * @param f Mapping function
     * @return New result
     */
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
