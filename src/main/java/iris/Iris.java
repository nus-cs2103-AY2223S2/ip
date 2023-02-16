package iris;

import iris.command.Command;
import iris.exception.IrisException;

/**
 * A teenage chatbot that can store text entered by the user and
 * display the stored text when requested in the form of a list
 *
 * @author lavanya
 * @version 1.0
 */
public class Iris {
    private static final String GREETING = "Welcome to Lavender Network!\n"
            + "I'm Iris, your favourite teenage chatbot.\n"
            + "I'm here to keep track of your tasks so you don't have to :)\n"
            + "Type \"help\" to see the commands.\n"
            + "What are you waiting for? Let's get started!";
    private TaskList tasks = null;
    private TaskStore taskStore = null;
    private IrisException initializingException = null;


    /**
     * Constructor that initiates the UI, task storage and task list for the bot
     */
    public Iris() {
        try {
            this.taskStore = new TaskStore();
            this.tasks = this.taskStore.parse();
        } catch (IrisException e) {
            this.initializingException = e;
        }
    }

    /**
     * gets a response for a given input
     * @param input the input to get the response for
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            command.execute(this.tasks, this.taskStore);
            return command.getResponse(this.tasks, this.taskStore);
        } catch (IrisException e) {
            return e.getMessage();
        }
    }

    /**
     *
     */
    public String startingMessage() {
        if (initializingException == null) {
            return GREETING;
        } else {
            try {
                if (this.tasks == null) {
                    this.tasks = new TaskList();
                    this.taskStore.reset();
                }
            } catch (IrisException e) {
                return "There was an error while resetting the task list. Please restart the application.";
            }

            return "Error while parsing stores file: " + initializingException.getMessage()
                    + "\nResetting the task list.\n" + GREETING;
        }
    }
}
