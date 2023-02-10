package duke; // Tan Matthew Simon Castaneda

import duke.contact.ContactList;
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

    private String taskFilepath;
    private String contactsFilepath;
    private Storage taskStorage;
    private Storage contactStorage;
    public TaskList taskList;

    public ContactList contactList;


    private Ui ui;

    public Duke() {}


    /**
     * Helper function to initialize ChadGPT's context.
     *
     * @param filepath1 Relative filepath of ChadGPT's task storage.
     * @param filepath2 Relative filepath of ChadGPT's contacts storage.
     *
     */
    public Duke(String filepath1, String filepath2) {
        this.taskFilepath = filepath1;
        this.contactsFilepath = filepath2;
        this.ui = new Ui();
        this.taskStorage = new Storage(filepath1);
        this.contactStorage = new Storage(filepath2);
        try {
            this.taskList = taskStorage.loadTask();
            this.contactList = contactStorage.loadContacts();
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
            this.taskStorage.saveTask(this.taskList);
            this.contactStorage.saveContacts(this.contactList);
        } else {
            return Parser.run(input, command, this.taskList, this.contactList);
        }

        return "we out bois";
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        if (input.equals("bye")) {
            this.taskStorage.saveTask(this.taskList);
            this.contactStorage.saveContacts(this.contactList);
            return "g bro bye";
        }
        return Parser.run(input, input.split(" "), this.taskList, this.contactList);
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
