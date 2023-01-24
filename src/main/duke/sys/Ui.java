package sys;

import command.Command;
import exception.DukeException;
import task.TaskList;

import java.util.Scanner;

public class Ui {

    private Storage storage;
    private TaskList tl;

    public Ui() {};

    public void setContext(Storage storage, TaskList tl) {
        this.storage = storage;
        this.tl = tl;
    }

    public void acceptInput() {

        // Print welcome message
        System.out.println("Hello! I'm Duke\n What can I do for you?");

        // Create Scanner and sys.Parser object
        Scanner sc = new Scanner(System.in);
        Parser p = new Parser(tl, storage, this);

        // Always ready to receive input
        while (sc.hasNextLine()) {
            try {
                String input = sc.nextLine();
                this.showLine();

                Command c = p.parse(input);

                c.execute(this.tl, this, storage);
            } catch (DukeException e) {
                showError(e.getMessage());
            } finally {
                this.showLine();
            }
        }
    }

    public void showError(String e) {
        System.out.println("ERROR: " + e);
    }

    public void showLine() {
        System.out.println("__________________________________");
    }
}
