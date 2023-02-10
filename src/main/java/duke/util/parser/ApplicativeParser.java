package duke.util.parser;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

import duke.exception.ParserException;
import duke.util.container.Pair;

public class ApplicativeParser<T> {

    ///////////////////////////////////
    // PREDEFINED PARSER COMBINATORS //
    ///////////////////////////////////

    private static final ApplicativeParser<?> ALWAYS_FAILED_PARSER =
            fromRunner(ignore -> Optional.empty());

    private static final ApplicativeParser<Void> EOF_PARSER = fromRunner(
            input -> input.isEmpty() ? Optional.of(Pair.of(input, null)) : Optional.empty());

    private static final ApplicativeParser<Void> SKIP_WHITESPACES_PARSER = fromRunner(input -> {
        int length = input.length();
        int offset = 0;
        while (offset < length && Character.isWhitespace(input.charAt(offset))) {
            offset++;
        }
        return Optional.of(Pair.of(input.subview(offset), null));
    });

    private static final ApplicativeParser<String> NON_WHITESPACES_PARSER = fromRunner(input -> {
        int length = input.length();
        int offset = 0;
        while (offset < length && !Character.isWhitespace(input.charAt(offset))) {
            offset++;
        }
        return Optional.of(Pair.of(input.subview(offset), input.substringTo(offset)));
    });

    private static final ApplicativeParser<String> UNTIL_EOF_PARSER = fromRunner(input -> {
        int length = input.length();
        return Optional.of(Pair.of(input.subview(length), input.substringTo(length)));
    });

    /////////////////////////////////////
    // INSTANCE FIELDS AND CONSTRUCTOR //
    /////////////////////////////////////

    private Function<StringView, Optional<Pair<StringView, T>>> runner;
    private String errorMessage = null;

    private ApplicativeParser(Function<StringView, Optional<Pair<StringView, T>>> runner) {
        this.runner = runner;
    }

    //////////////////////
    // STATIC UTILITIES //
    //////////////////////

    private static <T> ApplicativeParser<T> fromRunner(
            Function<StringView, Optional<Pair<StringView, T>>> runner) {
        return new ApplicativeParser<>(runner);
    }

    public static <T> ApplicativeParser<T> of(T value) {
        return fromRunner(input -> Optional.of(Pair.of(input, value)));
    }

    @SuppressWarnings("unchecked")
    public static <T> ApplicativeParser<T> fail() {
        return (ApplicativeParser<T>) ALWAYS_FAILED_PARSER;
    }

    public static <T, U, V> ApplicativeParser<V> lift(
            BiFunction<? super T, ? super U, ? extends V> combiner, ApplicativeParser<T> left,
            ApplicativeParser<U> right) {
        return left.flatMap(leftValue -> right
                .flatMap(rightValue -> ApplicativeParser
                        .of(combiner.apply(leftValue, rightValue))));
    }

    public static ApplicativeParser<Void> eof() {
        return EOF_PARSER;
    }

    public static ApplicativeParser<Void> skipWhitespaces() {
        return SKIP_WHITESPACES_PARSER;
    }

    public static ApplicativeParser<String> parseNonWhitespaces() {
        return NON_WHITESPACES_PARSER;
    }

    public static ApplicativeParser<String> parseUntilEof() {
        return UNTIL_EOF_PARSER;
    }

    public static ApplicativeParser<String> parseString(String prefix) {
        return fromRunner(input -> input.startsWith(prefix)
                ? Optional.of(Pair.of(input.subview(prefix.length()), prefix))
                : Optional.empty());
    }

    public static ApplicativeParser<String> parseUntil(String end) {
        return fromRunner(input -> {
            int offset = input.indexOf(end);
            return offset < 0
                    ? Optional.empty()
                    : Optional.of(Pair.of(
                            input.subview(offset + end.length()),
                            input.substringTo(offset)));
        });
    }

    @SafeVarargs
    public static <T> ApplicativeParser<T> choice(ApplicativeParser<T>... parsers) {
        return Arrays.stream(parsers)
                .reduce(ApplicativeParser::or)
                .orElseGet(ApplicativeParser::fail);
    }

    //////////////////////
    // INSTANCE METHODS //
    //////////////////////

    private Optional<Pair<StringView, T>> run(StringView input) {
        Optional<Pair<StringView, T>> result = runner.apply(input);
        if (errorMessage != null && result.isEmpty()) {
            throw new ParserException(errorMessage);
        }
        return result;
    }

    public <U> ApplicativeParser<T> dropNext(ApplicativeParser<U> that) {
        return lift((left, right) -> left, this, that);
    }

    public <U> ApplicativeParser<U> takeNext(ApplicativeParser<U> that) {
        return lift((left, right) -> right, this, that);
    }

    public <U> ApplicativeParser<U> map(Function<? super T, ? extends U> mapper) {
        return fromRunner(runner.andThen(opt -> opt.map(pair -> {
            StringView input = pair.getFirst();
            T oldValue = pair.getSecond();
            return Pair.of(input, mapper.apply(oldValue));
        })));
    }

    public <U> ApplicativeParser<U> flatMap(Function<? super T, ApplicativeParser<U>> flatMapper) {
        return fromRunner(input -> run(input).flatMap(pair -> {
            StringView oldInput = pair.getFirst();
            T oldValue = pair.getSecond();
            return flatMapper.apply(oldValue).run(oldInput);
        }));
    }

    public <U> ApplicativeParser<U> optionalMap(Function<? super T, Optional<U>> optionalMapper) {
        return flatMap(optionalMapper.andThen(opt -> opt
                .map(ApplicativeParser::of)
                .orElseGet(ApplicativeParser::fail)));
    }

    public ApplicativeParser<T> filter(Predicate<? super T> predicate) {
        return fromRunner(input -> run(input).filter(pair -> predicate.test(pair.getSecond())));
    }

    public ApplicativeParser<T> or(ApplicativeParser<T> that) {
        return fromRunner(input -> run(input).or(() -> that.run(input)));
    }

    public ApplicativeParser<T> throwIfFail(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }

    public Pair<String, T> parse(String input) {
        return run(StringView.of(input))
                .map(pair -> Pair.of(pair.getFirst().toString(), pair.getSecond()))
                .orElseThrow(() -> new ParserException("Unable to parse input: " + input));
    }
}


class StringView {

    private String value;
    private int index;

    private StringView(String value, int index) {
        this.value = value;
        this.index = index;
    }

    static StringView of(String value) {
        return new StringView(value, 0);
    }

    boolean startsWith(String prefix) {
        return value.startsWith(prefix, index);
    }

    StringView subview(int offset) {
        return new StringView(value, index + offset);
    }

    String substringTo(int offset) {
        return value.substring(index, index + offset);
    }

    char charAt(int offset) {
        return value.charAt(index + offset);
    }

    int length() {
        return value.length() - index;
    }

    int indexOf(String str) {
        int result = value.indexOf(str, index);
        return result < 0 ? result : result - index;
    }

    boolean isEmpty() {
        return index >= value.length();
    }

    @Override
    public String toString() {
        return value.substring(index);
    }
}
