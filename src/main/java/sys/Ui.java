package sys;

import javafx.scene.control.Label;

import command.Command;

import exception.DukeException;

import response.Response;
import task.TaskList;

import java.util.Scanner;

/**
 * Represents the interface system that the user interacts with.
 */
public class Ui {

    private Storage storage;
    private TaskList tasks;
    private Parser parser;

    /**
     * Constructor for UI.
     */
    public Ui() {}

    /**
     * Sets the context for the program.
     *
     * @param storage The storage area used by the application.
     * @param tasks The task list used by the application.
     */
    public void setContext(Storage storage, TaskList tasks) {
        this.storage = storage;
        this.tasks = tasks;
        this.parser = new Parser(tasks, storage, this);
    }

    /**
     * Allows the application to accept commands from the user via standard input.
     *
     * @param input The input given by the user.
     * @return The output from the program.
     */
    public Response send(String input) {
        // Accept input from user.
        try {
            this.showLine();

            // Parse input line.
            Command c = parser.parse(input);

            // Execute the command.
            Response output = c.execute(this.tasks, this, this.storage);

            System.out.println(output);

            return output;
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            return new Response(e.getMessage(), tasks);
        }
    }

    /**
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * Prints the given error message.
     *
     * @param e The error message.
     */
    public void showError(String e) {
        System.out.println("ERROR: " + e);
    }

    /**
     * Prints a horizontal line to partition the application inputs and outputs.
     */
    public void showLine() {
        System.out.println("__________________________________");
    }
}
