package aqua.logic.parser;

import aqua.exception.IllegalSyntaxException;


/**
 * A parser to parse a String into its object representation.
 *
 * @param <R> the return object type.
 */
@FunctionalInterface
public interface Parser<R> {
    /**
     * Parses the data in the given String into its object representation.
     *
     * @param input - the String to parse.
     * @return the object representation of the data in the given String.
     * @throws IllegalSyntaxException if the String cannot be parsed due to
     *      syntax errors.
     */
    public R parse(String input) throws IllegalSyntaxException;
}
