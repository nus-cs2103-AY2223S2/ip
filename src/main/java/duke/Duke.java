package duke; // Tan Matthew Simon Castaneda

import duke.helper.Parser;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;
import javafx.application.Application;


/**
 * Encapsulates ChadGPT in its full majesty.
 * TODO : Add all the exceptions regarding wrong input of date
 */
public class Duke {

    private String filepath;
    private Storage storage;
    public TaskList taskList;


    private Ui ui;

    public Duke() {}


    /**
     * Helper function to initialize ChadGPT's context.
     *
     * @param filepath Relative filepath of ChadGPT's storage.
     */
    public Duke(String filepath) {
        this.filepath = filepath;
        this.ui = new Ui();
        this.storage = new Storage(filepath);
        try {
            this.taskList = storage.loadTask();
        } catch (Exception e) {
            System.out.println("honggan");
            System.out.println(e.getMessage());
        }
    }

    /**
     * Runs ChadGPT in its full majesty.
     */
    public String run() {
        String input = ui.readLine();
        String[] command = input.split(" ");

        if (input.equals("bye")) {
            this.storage.saveTask(this.taskList);
        } else {
            return Parser.run(input, command, this.taskList);
        }

        return "we out bois";
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        if (input.equals("bye")) {
            this.storage.saveTask(this.taskList);
            return "g bro bye";
        }
        return Parser.run(input, input.split(" "), this.taskList);
    }


    /**
     * Method to start up ChadGPT.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
