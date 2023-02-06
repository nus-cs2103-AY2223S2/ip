package duke;



/**
 * The Duke program implements an application that
 * is able to track the tasks that a user has.
 * The task is divided into three different categories
 * Todo, Event and Deadline.
 *
 * @author Bryan Ong
 */
public class Duke {

    private final Ui ui;
    private final Storage storage;
    private final Parser parser;


    public Duke() {
        ui = new Ui();
        storage = new Storage();
        parser = new Parser();
    }

    public String getInitMsg() {
        return storage.init();
    }

    public static void main(String[] args) throws Exception {
        //new Duke().run();
    }

    public String getResponse(String input) {
        return parser.parse(input);
    }

}
