package spongebob;

import spongebob.command.Command;
import spongebob.exception.SpongebobException;
import spongebob.parser.Parser;
import spongebob.storage.Storage;
import spongebob.task.TaskList;
import spongebob.ui.Ui;

/**
 * Initializes the program and interact with the user.
 */
public class Spongebob {
    protected String greetingMsg;
    private Storage storage;
    private TaskList tasks;
    private final Ui UI;

    /**
     * Constructor to create spongebob.
     *
     * @param filePath description of the file path.
     */
    public Spongebob(String filePath) {
        assert filePath != null : "Empty filepath";

        UI = new Ui();
        setGreetingMsg();

        try {
            storage = new Storage(filePath);
            setTasks(new TaskList(storage.setUpStorageAndLoadData()));
        } catch (SpongebobException e) {
            Storage.setDefaultStorage();
            setTasks(new TaskList());
        }
    }

    public void setTasks(TaskList taskList) {
        tasks = taskList;
    }

    public void setGreetingMsg() {
        greetingMsg = UI.showWelcomeMessage();
    }

    public String getGreetingMsg() {
        return greetingMsg;
    }

    /**
     * Returns response given a user input.
     *
     * @param input command to execute.
     * @return response given by spongebob.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input.trim());
            return c.execute(tasks, UI, storage);
        } catch (SpongebobException e) {
            return UI.showError(e.getMessage());
        }
    }
}