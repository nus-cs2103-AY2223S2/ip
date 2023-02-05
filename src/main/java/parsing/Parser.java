package parsing;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import util.Util;

public class Parser<T> {
    private final Function<String, Result<T>> fn;

    private static final List<Character> WS = List.of(' ', '\n', '\t', '\r');

    public Parser(Function<String, Result<T>> fn) {
        this.fn = fn;
    }

    public Result<T> parse(String input) {
        return fn.apply(input);
    }

    public <U> Parser<U> bind(Function<T, Parser<U>> f) {
        return new Parser<U>(inp -> this.parse(inp).match(
                pr -> f.apply(pr.first()).parse(pr.second()),
                msg -> Result.error(msg)));
    }

    public <U> Parser<U> ignoreThen(Parser<U> p) {
        return new Parser<>(inp -> this.parse(inp).match(
                pr -> p.parse(pr.second()),
                msg -> Result.error(msg)));
    }

    public <U> Parser<T> thenIgnore(Parser<U> p) {
        return new Parser<>(inp -> this.parse(inp).match(
                pr -> p.parse(pr.second()).match(
                        pr2 -> Result.ok(pr.first(), pr2.second()),
                        msg -> Result.error(msg)),
                msg -> Result.error(msg)));
    }

    public Parser<T> or(Parser<T> p) {
        return new Parser<>(inp -> this.parse(inp).match(
                pr -> Result.ok(pr.first(), pr.second()),
                msg -> p.parse(inp).match(
                        pr -> Result.ok(pr.first(), pr.second()),
                        msg2 -> Result.error(msg + '\n' + msg2))));
    }

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

    public Parser<List<T>> some() {
        return many().satisfy(s -> !s.isEmpty(), "some: Less than 1 parse succesfull.");
    }

    public <U> Parser<List<T>> someUntil(Parser<U> p) {
        return manyUntil(p).satisfy(s -> !s.isEmpty(), "some: Less than 1 parse successfull.");
    }

    public Parser<T> overrideMsg(String errorMsg) {
        return new Parser<>(inp -> this.parse(inp).overrideMsg(errorMsg));
    }

    public Parser<T> satisfy(Predicate<T> condition, String failMsg) {
        return new Parser<>(inp -> this.parse(inp).filterOrElse(condition, failMsg));
    }

    public <U> Parser<U> map(Function<? super T, ? extends U> f) {
        return new Parser<>(inp -> {
            return this.parse(inp).map(f);
        });
    }

    public static Parser<String> take(int amt) {
        return new Parser<>(inp -> {
            if (inp.length() < amt) {
                return Result.error("Input size too low.");
            }
            return Result.ok(inp.substring(0, amt), inp.substring(amt));
        });
    }

    public static <U> Parser<String> takeWhile(Predicate<Character> pred) {
        return anyChar()
                .satisfy(pred, "")
                .many()
                .map(l -> Util.listToString(l));
    }

    public static Parser<String> takeTill(Predicate<Character> pred) {
        return anyChar()
                .satisfy(pred.negate(), "")
                .many()
                .map(l -> Util.listToString(l));
    }

    public static Parser<Void> skip(int amt) {
        return take(amt).map(s -> null);
    }

    public static <U> Parser<Void> skipWhile(Predicate<Character> pred) {
        return takeWhile(pred).map(s -> null);
    }

    public static Parser<Void> skipTill(Predicate<Character> pred) {
        return takeTill(pred).map(s -> null);
    }

    public static <U> Parser<U> retn(U res) {
        return new Parser<>(inp -> Result.ok(res, inp));
    }

    public static Parser<Character> anyChar() {
        return new Parser<>(inp -> {
            if (inp.isEmpty()) {
                return Result.error("End of input.");
            }
            return Result.ok(inp.charAt(0), inp.substring(1));
        });
    }

    public static Parser<Character> charParser(char c) {
        return anyChar().satisfy(x -> x == c, String.format("Character %s not found.", c));
    }

    public static Parser<String> strParser(String str) {
        return take(str.length()).satisfy(s -> s.equals(str), String.format("String %s not found."));
    }

    public static Parser<String> strParserIgnoreCase(String str) {
        return take(str.length()).satisfy(s -> s.toLowerCase().equals(str.toLowerCase()),
                String.format("String %s not found. (case ignored).", str));
    }

    public static Parser<String> nextStr() {
        return skipSpace()
                .ignoreThen(takeTill(c -> WS.contains(c)));
    }

    public static <T> Parser<String> strUntil(Parser<T> p) {
        return anyChar()
                .someUntil(p)
                .map(l -> Util.listToString(l))
                .map(s -> Util.cleanup(s));
    }

    public static Parser<String> nextLine() {
        return anyChar()
                .someUntil(endOfLine())
                .map(s -> Util.listToString(s))
                .overrideMsg("End of input.");
    }

    public static Parser<Integer> positiveDecimal() {
        return takeWhile(c -> c >= 48 && c < 58)
                .map(s -> Integer.parseInt(s))
                .overrideMsg("Failed to parse unsigned integer.");
    }

    public static Parser<Integer> negativeDecimal() {
        return charParser('-')
                .ignoreThen(positiveDecimal())
                .map(x -> -x)
                .overrideMsg("Failed to parse signed integer.");
    }

    public static Parser<Integer> decimal() {
        return positiveDecimal()
                .or(negativeDecimal())
                .overrideMsg("Failed to parse integer");
    }

    public static Parser<Void> skipSpace() {
        return skipWhile(c -> WS.contains(c));
    }

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