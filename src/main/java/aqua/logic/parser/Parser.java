package aqua.logic.parser;

import aqua.exception.IllegalSyntaxException;


@FunctionalInterface
public interface Parser<R> {
    public R parse(String input) throws IllegalSyntaxException;
}
