package aqua.logic.parser;

import aqua.exception.IllegalSyntaxException;
import javafx.concurrent.Service;
import javafx.concurrent.Task;


public class ParserService<T> extends Service<T> {
    private final Parser<T> parser;
    private final String input;


    public ParserService(Parser<T> parser, String input) {
        this.parser = parser;
        this.input = input;
    }


    @Override
    protected Task<T> createTask() {
        return new Task<>() {
            @Override
            public T call() throws IllegalSyntaxException {
                return parser.parse(input);
            }
        };
    }
}
