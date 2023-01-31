package util;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

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

    public Parser<Stream<T>> many() {
        // return new Parser<>(inp -> {
        // List<T> res = new ArrayList<>();
        // String remain = inp;
        // Result<T> temp = this.parse(remain);
        // while (temp.isOk()) {
        // remain = temp.match(
        // pr -> pr.second(),
        // msg -> throw new Exception("This should never happen.")
        // );
        // res.add(temp.getRes());
        // temp = this.parse(remain);
        // }
        // return Result.ok(res, remain);
        // });
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
        return new Parser<>(inp -> f.apply(inp));

    }

    public <U> Parser<Stream<T>> manyUntil(Parser<U> p) {
        // return new Parser<>(inp -> {
        // List<T> res = new ArrayList<>();
        // String remain = inp;
        // Result<T> temp = this.parse(remain);
        // Result<U> until = p.parse(remain);
        // while (until.isError() && temp.isOk()) {
        // remain = temp.getRemainInp();
        // res.add(temp.getRes());
        // temp = this.parse(remain);
        // until = p.parse(remain);
        // }
        // if (until.isError()) {
        // return Result.error(String.format("Ending Flag: %s", until.getErrorMsg()));
        // }
        // return Result.ok(res, until.getRemainInp());
        // });
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
                                msg -> Result.error(String.format("Ending Flag: %s", msg))));
            }
        };

        return new Parser<>(inp -> f.apply(inp));
    }

    public Parser<Stream<T>> some() {
        Function<String, Result<Stream<T>>> f = new Function<>() {
            @Override
            public Result<Stream<T>> apply(String inp) {
                return parse(inp).match(
                        pr -> parse(pr.second()).match(
                                pr2 -> {
                                    Result<Stream<T>> prev = this.apply(pr.second());
                                    return prev.map(s -> Stream.concat(Stream.of(pr.first()), s));
                                },
                                msg -> Result.ok(Stream.of(pr.first()), pr.second())),
                        msg -> Result.error(msg));
            }
        };
        return new Parser<>(inp -> f.apply(inp));
    }

    // public <U> Parser<List<T>> someUntil(Parser<U> p) {
    // return new Parser<>(inp -> {
    // List<T> res = new ArrayList<>();
    // String remain = inp;
    // Result<T> temp = this.parse(remain);
    // Result<U> until = p.parse(remain);
    // while (until.isError() && temp.isOk()) {
    // remain = temp.getRemainInp();
    // res.add(temp.getRes());
    // temp = this.parse(remain);
    // until = p.parse(remain);
    // }
    // if (until.isError()) {
    // return Result.error(String.format("Ending Flag: %s", until.getErrorMsg()));
    // }
    // if (res.isEmpty()) {
    // return Result.error(this.parse(until.getRemainInp()).getErrorMsg());
    // }
    // return Result.ok(res, until.getRemainInp());
    // });
    // }

    public Parser<T> overrideMsg(String errorMsg) {
        return new Parser<>(inp -> this.parse(inp).overrideMsg(errorMsg));
    }

    public Parser<T> satisfy(Predicate<T> condition) {
        return new Parser<>(inp -> this.parse(inp).filter(condition));
    }

    public <U> Parser<U> map(Function<? super T, ? extends U> f) {
        return new Parser<>(inp -> {
            return this.parse(inp).map(f);
        });
    }

    public static <U> Parser<U> retn(U res) {
        return new Parser<>(inp -> Result.ok(res, inp));
    }

    public static Parser<Character> anyCharParser() {
        return new Parser<>(inp -> {
            if (inp.isEmpty()) {
                return Result.error("End of input.");
            }
            return Result.ok(inp.charAt(0), inp.substring(1));
        });
    }

    public static Parser<Character> charParser(char c) {
        return anyCharParser().satisfy(x -> x == c).overrideMsg(String.format("Character %s not found.", c));
    }

    public static Parser<String> strParser(String str) {
        return new Parser<>(inp -> {
            if (str.length() > inp.length() || !inp.substring(0, str.length()).equals(str)) {
                return Result.error(String.format("String %s not found.", str));
            }
            return Result.ok(str, inp.substring(str.length()));
        });
    }

    public static Parser<String> strParserIgnoreCase(String str) {
        return new Parser<>(inp -> {
            if (str.length() > inp.length()
                    || !inp.substring(0, str.length()).toLowerCase().equals(str.toLowerCase())) {
                return Result.error(String.format("String %s not found (case ignored).", str));
            }
            return Result.ok(str, inp.substring(str.length()));
        });
    }

    public static Parser<String> nextStrParser() {
        return skipSpace()
                .ignoreThen(anyCharParser()
                        .satisfy(c -> !WS.contains(c))
                        .some()
                        .map(s -> s.map(c -> c.toString()).reduce("", (a, b) -> a + b)));
    }

    public static Parser<String> nextLineParser() {
        return anyCharParser()
                .manyUntil(endOfLine())
                .map(s -> s.map(c -> c.toString()).reduce("", (a, b) -> a + b));
    }

    public static Parser<Integer> unisgnedIntParser() {
        return anyCharParser().satisfy(c -> c >= 48 && c < 58)
                .some()
                .map(s -> s.map(c -> c.toString()).reduce("", (a, b) -> a + b))
                .map(s -> Integer.parseInt(s))
                .overrideMsg("Failed to parse unsigned integer.");
    }

    public static Parser<Integer> signedIntParser() {
        return charParser('-')
                .ignoreThen(unisgnedIntParser())
                .map(x -> -x)
                .overrideMsg("Failed to parse signed integer.");
    }

    public static Parser<Integer> intParser() {
        return unisgnedIntParser()
                .or(signedIntParser())
                .overrideMsg("Failed to parse integer");
    }

    public static Parser<Void> skipSpace() {
        return new Parser<>(inp -> {
            while (!inp.isEmpty() && WS.contains(inp.charAt(0))) {
                inp = inp.substring(1);
            }
            return Result.ok(null, inp);
        });
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
}