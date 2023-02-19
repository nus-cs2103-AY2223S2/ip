package aqua.logic.parser;

import aqua.exception.SyntaxException;
import javafx.concurrent.Service;
import javafx.concurrent.Task;


/** A {@code Service} to parse a String */
public class ParserService<T> extends Service<T> {
    private final Parser<T> parser;
    private final String input;


    /**
     * Constructs a {@code ParserService}.
     *
     * @param parser - the parser to use.
     * @param input - the String to parse.
     */
    public ParserService(Parser<T> parser, String input) {
        this.parser = parser;
        this.input = input;
    }


    @Override
    protected Task<T> createTask() {
        return new Task<>() {
            @Override
            public T call() throws SyntaxException {
                return parser.parse(input);
            }
        };
    }
}
