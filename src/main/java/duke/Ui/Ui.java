package duke.Ui;

import duke.exception.DukeException;
import duke.task.TaskList;

public class Ui {

    public Ui() {

    }


    public void showLoadingError() {
        System.out.println("Error initialising DUKE! File Corrupted!");
    }

    public void showWelcome() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    public void showLine() {
        for (int n = 0; n < 50; n++) {
            System.out.print("-");
        }
        System.out.println("\n");

    }


    public void showError(String message) {
        System.out.println(message);
    }

    public String readCommand(TaskList tasks, int currIteration) throws DukeException {
        String result = tasks.printCommands(currIteration);
        if (result == null) {
            throw new DukeException("End of command!");
        }
        System.out.println(result);
        return result;
    }

}
