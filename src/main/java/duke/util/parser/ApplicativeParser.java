package duke.util.parser;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

import duke.util.container.Pair;

/**
 * Basic implementation of a generic {@code ApplicativeParser}, based on functional applicative
 * parser from the <b>Haskell programming language</b>.
 * <p>
 * Uses static methods to create instances of this class.
 *
 * @param <T> the type of the parser result
 */
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
        String value = input.substringTo(offset);
        return value.isEmpty()
                ? Optional.empty()
                : Optional.of(Pair.of(input.subview(offset), input.substringTo(offset)));
    });

    private static final ApplicativeParser<String> UNTIL_EOF_PARSER = fromRunner(input -> {
        int length = input.length();
        return Optional.of(Pair.of(input.subview(length), input.substringTo(length)));
    });

    /////////////////////////////////////
    // INSTANCE FIELDS AND CONSTRUCTOR //
    /////////////////////////////////////

    private final Function<StringView, Optional<Pair<StringView, T>>> runner;
    private final String errorMessage;

    private ApplicativeParser(
            Function<StringView, Optional<Pair<StringView, T>>> runner,
            String errorMessage) {
        this.runner = runner;
        this.errorMessage = errorMessage;
    }

    //////////////////////
    // STATIC UTILITIES //
    //////////////////////

    private static <T> ApplicativeParser<T> fromRunner(
            Function<StringView, Optional<Pair<StringView, T>>> runner) {
        return new ApplicativeParser<>(runner, null);
    }

    /**
     * Creates a parser that parses nothing and returns exactly the given value. This method is
     * equivalent to {@code return} function in <b>Haskell</b>.
     *
     * @param <T> the type of the given value
     * @param value the given value
     * @return a parser that parses nothing, and returns exactly the given value
     */
    public static <T> ApplicativeParser<T> of(T value) {
        return fromRunner(input -> Optional.of(Pair.of(input, value)));
    }

    /**
     * Returns a parser that always fails.
     *
     * @param <T> the type of the parser result (which does not exist, in this case)
     * @return a parser that always fails
     */
    public static <T> ApplicativeParser<T> fail() {
        return ALWAYS_FAILED_PARSER.cast();
    }

    /**
     * Lifts a normal binary function to work on parsers. The first parser is run on the input, and
     * may consume some characters. Then, the second parser is run on the remaining input. Finally,
     * the results of the two parsers are combined using the binary function.
     * <p>
     * This method is equivalent to {@code liftA2} function in <b>Haskell</b>.
     * <p>
     * This method proves that an {@code ApplicativeParser} is an {@code Applicative}. However,
     * there is no way to encode the behavior of an {@code Applicative} using an instance method in
     * <b>Java</b>.
     *
     * @param <T> the type of the first parser result
     * @param <U> the type of the second parser result
     * @param <V> the type of the resultant parser result
     * @param combiner the given binary function
     * @param left the first parser
     * @param right the second parser
     * @return a new parser, that first runs the first parser, then the second parser, and finally
     *         combines the result of these parsers using the binary function
     */
    public static <T, U, V> ApplicativeParser<V> lift(
            BiFunction<? super T, ? super U, ? extends V> combiner,
            ApplicativeParser<T> left,
            ApplicativeParser<U> right) {
        return left.flatMap(leftValue -> right
                .flatMap(rightValue -> ApplicativeParser
                        .of(combiner.apply(leftValue, rightValue))));
    }

    /**
     * Returns a parser that succeeds when reaches eof, and fails otherwise. The result of this
     * parser should be discarded.
     *
     * @return the eof parser
     */
    public static ApplicativeParser<Void> eof() {
        return EOF_PARSER;
    }

    /**
     * Returns a parser that skips all leading whitespaces.
     *
     * @return a parser that skips all leading whitespaces
     */
    public static ApplicativeParser<Void> skipWhitespaces() {
        return SKIP_WHITESPACES_PARSER;
    }

    /**
     * Returns a parser that consumes input until it reaches a whitespace character.
     *
     * @return a parser that parses until a whitespace character
     */
    public static ApplicativeParser<String> parseNonWhitespaces() {
        return NON_WHITESPACES_PARSER;
    }

    /**
     * Returns a parser that consumes all remaining input.
     *
     * @return a parser that parses until eof
     */
    public static ApplicativeParser<String> parseUntilEof() {
        return UNTIL_EOF_PARSER;
    }

    /**
     * Returns a parser that succeeds when the input starts with the given prefix, fails otherwise.
     * If the parser succeeds, the result of the parser will be the given prefix.
     *
     * @param prefix the given prefix
     * @return a parser that succeeds when the input starts with the given prefix, fails otherwise
     */
    public static ApplicativeParser<String> parseString(String prefix) {
        return fromRunner(input -> input.startsWith(prefix)
                ? Optional.of(Pair.of(input.subview(prefix.length()), prefix))
                : Optional.empty());
    }

    /**
     * Returns a parser that parses until the given substring is encountered. The substring will
     * also be consumed from the input. This parser will returns the substring between the start of
     * the input, and the last character before the given substring.
     *
     * @param end the substring to find
     * @return a parser that parses until the given substring is encountered
     */
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

    /**
     * Returns a parser that tries multiple parsers, until one succeeds.
     *
     * @param <T> the type of the parser results
     * @param parsers an array of parsers
     * @return a parser that tries the given parsers, until one succeeds
     */
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

    @SuppressWarnings("unchecked")
    private <U> ApplicativeParser<U> cast() {
        return (ApplicativeParser<U>) this;
    }

    /**
     * Returns a parser that runs this parser and another parser, but drops the result of that
     * parser. This method is equivalent to {@code <*} operator in <b>Haskell</b>.
     *
     * @param <U> the type of the other parser result
     * @param that the other parser
     * @return a new parser that runs both parsers, but keeps the result of only one parser
     */
    public <U> ApplicativeParser<T> dropNext(ApplicativeParser<U> that) {
        return lift((left, right) -> left, this, that);
    }

    /**
     * Returns a parser that runs this parser and another parser, but takes the result of that
     * parser. This method is equivalent to {@code *>} operator in <b>Haskell</b>.
     *
     * @param <U> the type of the other parser result
     * @param that the other parser
     * @return a new parser that runs both parsers, but keeps the result of only one parser
     */
    public <U> ApplicativeParser<U> takeNext(ApplicativeParser<U> that) {
        return lift((left, right) -> right, this, that);
    }

    /**
     * Maps a function over the result of this parser.
     * <p>
     * This method is equivalent to {@code fmap} function in <b>Haskell</b>.
     * <p>
     * This method proves that an {@code ApplicativeParser} is a {@code Functor}.
     *
     * @param <U> the type of the new result
     * @param mapper the function to map the result of this parser
     * @return a new parser that applies the mapper function, after running this parser
     */
    public <U> ApplicativeParser<U> map(Function<? super T, ? extends U> mapper) {
        return fromRunner(runner.andThen(opt -> opt.map(pair -> {
            StringView input = pair.getFirst();
            T oldValue = pair.getSecond();
            return Pair.of(input, mapper.apply(oldValue));
        })));
    }

    /**
     * Runs this parser, then uses the given function to create another parser from the result of
     * this parser and run that parser.
     * <p>
     * This method is equivalent to {@code >>=} operator in <b>Haskell</b>.
     * <p>
     * This method proves that an {@code ApplicativeParser} is a {@code Monad}.
     *
     * @param <U> the type of the generated parser result
     * @param flatMapper the function to create another parser from the result of this parser
     * @return a parser that runs this parser, and then uses the mapper function to create and run
     *         another parser
     */
    public <U> ApplicativeParser<U> flatMap(
            Function<? super T, ? extends ApplicativeParser<? extends U>> flatMapper) {
        return fromRunner(input -> run(input).flatMap(pair -> {
            StringView oldInput = pair.getFirst();
            T oldValue = pair.getSecond();
            ApplicativeParser<? extends U> that = flatMapper.apply(oldValue);
            return that.<U>cast().run(oldInput);
        }));
    }

    /**
     * Maps a function that produces an {@code Optional} over the result of this parser, and unwraps
     * that {@code Optional}. If the {@code Optional} is empty, the parser fails.
     *
     * @param <U> the type of the new result
     * @param optionalMapper the function to map the result of this parser
     * @return a new parser that applies the mapper function, after running this parser
     */
    public <U> ApplicativeParser<U> optionalMap(
            Function<? super T, ? extends Optional<? extends U>> optionalMapper) {
        return flatMap(optionalMapper.andThen(opt -> opt
                .map(ApplicativeParser::of)
                .orElseGet(ApplicativeParser::fail))).cast();
    }

    /**
     * Uses a predicate to filter the result of this parser. If the test fails, the parser also
     * fails.
     *
     * @param predicate the predicate to test the result of this parser
     * @return a new parser that runs this parser and uses the predicate to test the result
     */
    public ApplicativeParser<T> filter(Predicate<? super T> predicate) {
        return fromRunner(input -> run(input).filter(pair -> predicate.test(pair.getSecond())));
    }

    /**
     * Chooses between this parser, or another parser.
     *
     * @param that the other parser
     * @return a parser that chooses the result of the first succeeds parser
     */
    public ApplicativeParser<T> or(ApplicativeParser<T> that) {
        return fromRunner(input -> run(input).or(() -> that.run(input)));
    }

    /**
     * Returns a new parser that throws an exception (immediately) if this parser fails. The thrown
     * exception will stop a parsing pipeline.
     *
     * @param errorMessage the error message of the exception
     * @return a new parser that throws if this parser fails
     */
    public ApplicativeParser<T> throwIfFail(String errorMessage) {
        return new ApplicativeParser<>(runner, errorMessage);
    }

    /**
     * Returns a parser that does nothing when fails.
     *
     * @return a parser that does nothing when fails.
     */
    public ApplicativeParser<T> ignoreIfFail() {
        return fromRunner(runner);
    }

    /**
     * Runs this parser on the given input.
     *
     * @param input the given input, to be parsed
     * @return a pair consists of the remaining input, and the parser result
     * @throws ParserException if this parser fails
     */
    public Pair<String, T> parse(String input) {
        return run(StringView.of(input, 0))
                .map(pair -> Pair.of(pair.getFirst().toString(), pair.getSecond()))
                .orElseThrow(() -> new ParserException("Unable to parse input: " + input));
    }
}


/**
 * Wrapper of a normal {@code String} instance. Instances of this class are used to represent slices
 * of the original string.
 * <p>
 * A {@code StringView} instance stores 2 fields: the original string and an index that represents
 * the offset into the original string.
 */
class StringView {

    private String value;
    private int index;

    private StringView(String value, int index) {
        this.value = value;
        this.index = index;
    }

    /**
     * Creates a new instance of this class.
     *
     * @param value the original string
     * @param index the initial offset
     * @return an instance of this class, which represents a substring that starts from
     *         {@code index} in the original string.
     */
    static StringView of(String value, int index) {
        return new StringView(value, index);
    }

    /**
     * Checks whether this view starts with the given prefix.
     *
     * @param prefix the prefix to check
     * @return whether this view starts with the given prefix
     */
    boolean startsWith(String prefix) {
        return value.startsWith(prefix, index);
    }

    /**
     * Returns a subview of this view, starting from an offset.
     *
     * @param offset the offset from the start of this view
     * @return a new subview that starts from the given offset
     */
    StringView subview(int offset) {
        return new StringView(value, index + offset);
    }

    /**
     * Returns a substring, from the start of this view to the given offset.
     *
     * @param offset the offset from the start of this view
     * @return a substring that starts at the start of this view, and ends before the given offset
     */
    String substringTo(int offset) {
        return value.substring(index, index + offset);
    }

    /**
     * Returns the character at the given offset.
     *
     * @param offset the offset from the start of this view
     * @return the chatacter at the offset
     */
    char charAt(int offset) {
        return value.charAt(index + offset);
    }

    /**
     * Returns the length of this view.
     *
     * @return the length of this view
     */
    int length() {
        return value.length() - index;
    }

    /**
     * Returns the index of the first occurence of the given substring in this view.
     *
     * @param str the given substring to find
     * @return the index of the first occurence of the given substring in this view, {@code -1} if
     *         the substring does not appear in this view
     */
    int indexOf(String str) {
        int result = value.indexOf(str, index);
        return result < 0 ? result : result - index;
    }

    /**
     * Checks whether this view represents an empty string.
     *
     * @return {@code true} if this view represents an empty string, otherwise {@code false}
     */
    boolean isEmpty() {
        return index >= value.length();
    }

    @Override
    public String toString() {
        return value.substring(index);
    }
}
