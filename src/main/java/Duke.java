import duke.commands.Command;
import duke.commands.ExitCommand;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.Tasks;
import duke.ui.Ui;

/**
 * Represents the Duke chatbot.
 */
public class Duke {

    private Ui ui;
    private Storage storage;
    private Tasks tasks;
    private boolean isStart; //tracks if it is start of program, so duke can welcome user.

    /**
     * Creates a new Duke.
     */
    public Duke() {
        this.ui = new Ui();
        this.tasks = new Tasks();
        this.storage = new Storage();
        this.storage.load(this.tasks);
        this.isStart = true;
    }

    /**
     * Gets Duke's response to user input.
     * @param input User input.
     * @return Duke's response.
     */
    public String getResponse(String input) {
        String response;
        if (isStart) {
            response = ui.getWelcomeMessage();
            isStart = false;
        } else {
            Command c = Parser.parse(input);
            if (c instanceof ExitCommand) {
                response = ui.getGoodbyeMessage();
                this.storage.save(this.tasks);
            } else {
                response = c.execute(this.tasks, this.ui, this.storage);
            }
        }
        return response;
    }
}
