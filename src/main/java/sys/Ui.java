package sys;

import command.Command;

import exception.DukeException;

import task.TaskList;

import java.util.Scanner;

public class Ui {

    private Storage storage;
    private TaskList tasks;

    public Ui() {

    };

    public void setContext(Storage storage, TaskList tasks) {
        this.storage = storage;
        this.tasks = tasks;
    }

    public void acceptInput() {

        // Print welcome message.
        System.out.println("Hello! I'm Duke\n What can I do for you?");

        // Create Scanner and Parser object.
        Scanner sc = new Scanner(System.in);
        Parser p = new Parser(this.tasks, this.storage, this);

        // Accept input from user.
        while (sc.hasNextLine()) {
            try {
                String input = sc.nextLine();
                this.showLine();

                // Parse input line.
                Command c = p.parse(input);

                // Execute the command.
                c.execute(this.tasks, this, this.storage);
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
