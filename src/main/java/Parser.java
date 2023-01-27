import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

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
        return new Parser<U>(inp -> {
            Result<T> temp = this.parse(inp);
            if (temp.isError()) {
                return Result.error(temp.getErrorMsg());
            }
            Parser<U> next = f.apply(temp.getRes());
            return next.parse(temp.getRemainInp());
        });
    }

    public <U> Parser<U> ignoreThen(Parser<U> p) {
        return new Parser<>(inp -> {
            Result<T> temp = this.parse(inp);
            if (temp.isError()) {
                return Result.error(temp.getErrorMsg());
            }
            return p.parse(temp.getRemainInp());
        });
    }

    public <U> Parser<T> thenIgnore(Parser<U> p) {
        return new Parser<>(inp -> {
            Result<T> temp = this.parse(inp);
            if (temp.isError()) {
                return Result.error(temp.getErrorMsg());
            }
            Result<U> temp2 = p.parse(temp.getRemainInp());
            if (temp2.isError()) {
                return Result.error(temp2.getErrorMsg());
            }
            return Result.ok(temp.getRes(), temp2.getRemainInp());
        });
    }

    public Parser<T> or(Parser<T> p) {
        return new Parser<>(inp -> {
            Result<T> temp = this.parse(inp);
            if (temp.isOk()) {
                return temp;
            }
            return p.parse(inp);
        });
    }

    public Parser<List<T>> many() {
        return new Parser<>(inp -> {
            List<T> res = new ArrayList<>();
            String remain = inp;
            Result<T> temp = this.parse(remain);
            while (temp.isOk()) {
                remain = temp.getRemainInp();
                res.add(temp.getRes());
                temp = this.parse(remain);
            }
            return Result.ok(res, remain);
        });
    }

    public <U> Parser<List<T>> manyUntil(Parser<U> p) {
        return new Parser<>(inp -> {
            List<T> res = new ArrayList<>();
            String remain = inp;
            Result<T> temp = this.parse(remain);
            Result<U> until = p.parse(remain);
            while (until.isError() && temp.isOk()) {
                remain = temp.getRemainInp();
                res.add(temp.getRes());
                temp = this.parse(remain);
                until = p.parse(remain);
            }
            if (until.isError()) {
                return Result.error(String.format("Ending Flag: %s", until.getErrorMsg()));
            }
            return Result.ok(res, until.getRemainInp());
        });
    }

    public Parser<List<T>> some() {
        return new Parser<>(inp -> {
            List<T> res = new ArrayList<>();
            String remain = inp;
            Result<T> temp = this.parse(remain);
            while (temp.isOk()) {
                remain = temp.getRemainInp();
                res.add(temp.getRes());
                temp = this.parse(remain);
            }
            if (res.isEmpty()) {
                return Result.error(temp.getErrorMsg());
            }
            return Result.ok(res, remain);
        });
    }

    public <U> Parser<List<T>> someUntil(Parser<U> p) {
        return new Parser<>(inp -> {
            List<T> res = new ArrayList<>();
            String remain = inp;
            Result<T> temp = this.parse(remain);
            Result<U> until = p.parse(remain);
            while (until.isError() && temp.isOk()) {
                remain = temp.getRemainInp();
                res.add(temp.getRes());
                temp = this.parse(remain);
                until = p.parse(remain);
            }
            if (until.isError()) {
                return Result.error(String.format("Ending Flag: %s", until.getErrorMsg()));
            }
            if (res.isEmpty()) {
                return Result.error(this.parse(until.getRemainInp()).getErrorMsg());
            }
            return Result.ok(res, until.getRemainInp());
        });
    }

    public <U> Parser<U> map(Function<? super T, ? extends U> f) {
        return new Parser<>(inp -> {
            return this.parse(inp).map(f);
        });
    }

    public static <U> Parser<U> retn(U res) {
        return new Parser<>(inp -> Result.ok(res, inp));
    }

    public static Parser<Character> charParser(char c) {
        return new Parser<>(inp -> {
            if (inp.isEmpty() || inp.charAt(0) != c) {
                return Result.error(String.format("Character %s not found.", c));
            }
            return Result.ok(c, inp.substring(1));
        });
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

    public static Parser<Void> skipSpace() {
        return new Parser<>(inp -> {
            while (!inp.isEmpty() && WS.contains(inp.charAt(0))) {
                inp = inp.substring(1);
            }
            return Result.ok(null, inp);
        });
    }
}