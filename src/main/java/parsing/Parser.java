package parsing;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import util.Util;

/**
 * Wrapper around a function from a string input to a
 * parsed object plus remaining input, or if parsing fails,
 * an error message.
 * 
 * @param <T> Type of parsed object
 * @see Result
 */
public class Parser<T> {
    private final Function<String, Result<T>> fn;

    private static final List<Character> WS = List.of(' ', '\n', '\t', '\r');

    /**
     * @param fn Function from string to Result
     * @see Result
     */
    public Parser(Function<String, Result<T>> fn) {
        this.fn = fn;
    }

    /**
     * Parses input string
     * 
     * @param input Input string
     * @return Result of parse
     */
    public Result<T> parse(String input) {
        return fn.apply(input);
    }

    /**
     * Chain multiple parsers together, while keeping
     * parse result within scope
     * 
     * @param <U> Type of new object to parse
     * @param f   Function from original parse result to new parser
     * @return New parser
     */
    public <U> Parser<U> bind(Function<T, Parser<U>> f) {
        return new Parser<U>(inp -> this.parse(inp).match(
                pr -> f.apply(pr.first()).parse(pr.second()),
                msg -> Result.error(msg)));
    }

    /**
     * Chain 2 parsers, ignoring result from first parse.
     * 
     * @param p Next parser in chain
     * @return New parser
     */
    public <U> Parser<U> ignoreThen(Parser<U> p) {
        return new Parser<>(inp -> this.parse(inp).match(
                pr -> p.parse(pr.second()),
                msg -> Result.error(msg)));
    }

    /**
     * Chain 2 parsers, ignoring result form second parse.
     * 
     * @param p Next Parser in chain
     * @return New parser
     */
    public <U> Parser<T> thenIgnore(Parser<U> p) {
        return new Parser<>(inp -> this.parse(inp).match(
                pr -> p.parse(pr.second()).match(
                        pr2 -> Result.ok(pr.first(), pr2.second()),
                        msg -> Result.error(msg)),
                msg -> Result.error(msg)));
    }

    /**
     * Chain 2 parsers, result will be whichever parser succeeds first.
     * 
     * @param p Other parser to try
     * @return New parser
     */
    public Parser<T> or(Parser<T> p) {
        return new Parser<>(inp -> this.parse(inp).match(
                pr -> Result.ok(pr.first(), pr.second()),
                msg -> p.parse(inp).match(
                        pr -> Result.ok(pr.first(), pr.second()),
                        msg2 -> Result.error(msg + '\n' + msg2))));
    }

    /**
     * Tries this parser as many times as possible
     * 
     * @return Parser that parses a list of the original type.
     *         Empty list returned if no parse succeeds.
     */
    public Parser<List<T>> many() {
        Function<String, Result<Stream<T>>> f = new Function<>() {
            @Override
            public Result<Stream<T>> apply(String inp) {
                return parse(inp).match(
                        pr -> {
                            Result<Stream<T>> prev = this.apply(pr.second());
                            return prev.map(s -> Stream.concat(Stream.of(pr.first()), s));
                        },
                        msg -> Result.ok(Stream.<T>of(), inp));
            }
        };
        return new Parser<>(inp -> f.apply(inp).map(s -> s.collect(Collectors.toList())));

    }

    /**
     * Many until another parser succeeds. Other parser result ignored.
     * 
     * @param <U> Type of other parser
     * @param p   Other parser that ends attempt to parse
     * @return New parser
     */
    public <U> Parser<List<T>> manyUntil(Parser<U> p) {
        Function<String, Result<Stream<T>>> f = new Function<>() {
            @Override
            public Result<Stream<T>> apply(String inp) {
                return p.parse(inp).match(
                        pr -> Result.ok(Stream.of(), pr.second()),
                        e -> parse(inp).match(
                                pr -> {
                                    Result<Stream<T>> prev = this.apply(pr.second());
                                    return prev.map(s -> Stream.concat(Stream.of(pr.first()), s));
                                },
                                msg -> Result.error(String.format("Ending Flag: %s", e))));
            }
        };
        return new Parser<>(inp -> f.apply(inp).map(s -> s.collect(Collectors.toList())));
    }

    /**
     * Same as many except that result must have at least 1 element.
     * 
     * @see #many()
     */
    public Parser<List<T>> some() {
        return many().satisfyOrElse(s -> !s.isEmpty(), "some: Less than 1 parse succesfull.");
    }

    /**
     * Same as manyUntil except that result must have at least 1 element.
     * 
     * @param <U> Type of other parser
     * @param p   Other parser that ends attempt to parse
     * @see #manyUntil(Parser)
     */
    public <U> Parser<List<T>> someUntil(Parser<U> p) {
        return manyUntil(p).satisfyOrElse(s -> !s.isEmpty(), "some: Less than 1 parse successfull.");
    }

    /**
     * Replaces error message if result is an error.
     * 
     * @param errorMsg Message to replace with
     */
    public Parser<T> overrideMsg(String errorMsg) {
        return new Parser<>(inp -> this.parse(inp).overrideMsg(errorMsg));
    }

    /**
     * Parser fails if result does not satisfy condition
     * 
     * @see Result#filterOrElse(Predicate, String)
     */
    public Parser<T> satisfyOrElse(Predicate<T> condition, String failMsg) {
        return new Parser<>(inp -> this.parse(inp).filterOrElse(condition, failMsg));
    }

    /**
     * Maps parser to new parser of new type
     * 
     * @see Result#map(Function)
     */
    public <U> Parser<U> map(Function<? super T, ? extends U> f) {
        return new Parser<>(inp -> {
            return this.parse(inp).map(f);
        });
    }

    /**
     * @return Parser that parses a string of a certain number of characters
     * 
     * @param amt Number of characters to parse
     */
    public static Parser<String> take(int amt) {
        return new Parser<>(inp -> {
            if (inp.length() < amt) {
                return Result.error("Input size too low.");
            }
            return Result.ok(inp.substring(0, amt), inp.substring(amt));
        });
    }

    /**
     * @return Parser that parses a string of characters that satisfy a condition
     * 
     * @param pred Condition that characters must satisfy
     */
    public static <U> Parser<String> takeWhile(Predicate<Character> pred) {
        return anyChar()
                .satisfyOrElse(pred, "")
                .many()
                .map(l -> Util.listToString(l));
    }

    /**
     * @return Parser that parses a string of characters until an ending condition
     *         is
     *         satisfied
     * 
     * @param pred Ending condition
     */
    public static Parser<String> takeTill(Predicate<Character> pred) {
        return anyChar()
                .satisfyOrElse(pred.negate(), "")
                .many()
                .map(l -> Util.listToString(l));
    }

    /**
     * @return Parser that skips over input. Result must be ignored.
     * 
     * @param amt Number of characters to skip
     */
    public static Parser<Void> skip(int amt) {
        return take(amt).map(s -> null);
    }

    /**
     * @return Parser that skips over characters while characters satisfy condition.
     *         Result must be ignored.
     * 
     * @param pred Condition that characters must satisfy
     */
    public static <U> Parser<Void> skipWhile(Predicate<Character> pred) {
        return takeWhile(pred).map(s -> null);
    }

    /**
     * @return Parser that skips over characters until condition is satisfied.
     *         Result must be ignored.
     * 
     * @param pred Condition that ends the parsing
     */
    public static Parser<Void> skipTill(Predicate<Character> pred) {
        return takeTill(pred).map(s -> null);
    }

    /**
     * @return Parser that returns a fixed value, regardless of input.
     *         Does not consume input.
     * 
     * @param res Result to be returned
     */
    public static <U> Parser<U> retn(U res) {
        return new Parser<>(inp -> Result.ok(res, inp));
    }

    /**
     * @return Parser that can parse any character
     */
    public static Parser<Character> anyChar() {
        return new Parser<>(inp -> {
            if (inp.isEmpty()) {
                return Result.error("End of input.");
            }
            return Result.ok(inp.charAt(0), inp.substring(1));
        });
    }

    /**
     * @return Parser that parses a certain character
     * 
     * @param c Character to be parsed
     */
    public static Parser<Character> charParser(char c) {
        return anyChar().satisfyOrElse(x -> x == c, String.format("Character %s not found.", c));
    }

    /**
     * @return Parser that parses a certain string
     * 
     * @param str String to be parsed
     */
    public static Parser<String> strParser(String str) {
        return take(str.length()).satisfyOrElse(s -> s.equals(str), String.format("String %s not found."));
    }

    /**
     * @return strParser except case is ignored
     * 
     * @see #strParser(String)
     */
    public static Parser<String> strParserIgnoreCase(String str) {
        return take(str.length()).satisfyOrElse(s -> s.toLowerCase().equals(str.toLowerCase()),
                String.format("String %s not found. (case ignored).", str));
    }

    /**
     * @return Parser that parses the next string without spaces
     */
    public static Parser<String> nextStr() {
        return skipSpace()
                .ignoreThen(takeTill(c -> WS.contains(c)))
                .satisfyOrElse(s -> !s.isEmpty(), "No next string found.");
    }

    /**
     * @return Parser that parses a string before another parse succeeds
     * 
     * @param p Parser to compare against
     */
    public static <T> Parser<String> strUntil(Parser<T> p) {
        return anyChar()
                .someUntil(p)
                .map(l -> Util.listToString(l))
                .map(s -> Util.cleanup(s));
    }

    /**
     * @return Parser that parses until EOL
     */
    public static Parser<String> nextLine() {
        return anyChar()
                .someUntil(endOfLine())
                .map(s -> Util.listToString(s))
                .overrideMsg("End of input.");
    }

    /**
     * @return Parser that parses a unsigned integer
     */
    public static Parser<Integer> positiveDecimal() {
        return takeWhile(c -> c >= 48 && c < 58)
                .satisfyOrElse(s -> !s.isEmpty(), "No parsable integers.")
                .map(s -> Integer.parseInt(s))
                .overrideMsg("Failed to parse unsigned integer.");
    }

    /**
     * @return Parser that parses a negative integer
     */
    public static Parser<Integer> negativeDecimal() {
        return charParser('-')
                .ignoreThen(positiveDecimal())
                .map(x -> -x)
                .overrideMsg("Failed to parse signed integer.");
    }

    /**
     * @return Parser that parses a signed integer
     */
    public static Parser<Integer> decimal() {
        return positiveDecimal()
                .or(negativeDecimal())
                .overrideMsg("Failed to parse integer");
    }

    /**
     * @return Parser that skips over spaces.
     *         Result must be ignored.
     */
    public static Parser<Void> skipSpace() {
        return skipWhile(c -> WS.contains(c));
    }

    /**
     * @return Parser that succeds if input is at EOL.
     *         Result must be ignored.
     */
    public static Parser<Void> endOfLine() {
        return new Parser<>(inp -> {
            if (inp.isEmpty()) {
                return Result.ok(null, inp);
            }
            if (inp.charAt(0) == '\n') {
                return Result.ok(null, inp.substring(1));
            }
            return Result.error("Not EOL yet.");
        });
    }

    /**
     * @return Parser that can parse dates
     */
    public static Parser<LocalDate> dateParser() {
        return new Parser<>(inp -> take(10).parse(inp).match(
                pr -> {
                    try {
                        return Result.ok(LocalDate.parse(pr.first()), pr.second());
                    } catch (DateTimeParseException ex) {
                        return Result.error("Failed to parse date.");
                    }
                },
                msg -> Result.error(msg)));
    }
}