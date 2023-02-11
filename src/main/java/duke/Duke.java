package duke;

import command.Command;
import exception.DukeException;
import task.Tasklist;

public class Duke {
    private Ui userInterface;
    private Storage backend;
    private Tasklist tasklist;


    public Duke(String filePath) {
        this.userInterface = new Ui();
        this.backend = new Storage(filePath);
        try {
            this.tasklist = new Tasklist(backend.load(), backend);
        } catch (DukeException e) {
            userInterface.displayErrorMessage(e.getMessage());
            this.tasklist = new Tasklist();
        }
    }
    public void start() throws DukeException {
            userInterface.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = userInterface.readCommand();
                userInterface.showLine(); // show the divider line ("_______")
                Parser parser = new Parser(fullCommand);
                Command command = parser.parseCommand();
                command.execute(userInterface,tasklist, backend);
                isExit = command.isExit();

            } catch (DukeException e) {
                userInterface.displayErrorMessage(e.getMessage());
            } finally {
                userInterface.showLine();
            }
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke("./data/duke.txt");
        try {
            duke.start();
        } catch(DukeException e) {
            System.out.println(e);
        }
    }
}
