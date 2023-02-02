package duke;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.time.format.DateTimeParseException;

import java.util.List;
import java.util.Scanner;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            tasks = new TaskList();
        }
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        List<Task> taskList = tasks.getTasks();

        // Greeting
        ui.showGreeting();

        // Commands
        String command = sc.nextLine();

        while (!command.equals("bye")) {
            ui.showKaren();

            try {
                Command c = Parser.parse(command);
                c.execute(tasks, ui, storage);
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } catch (IndexOutOfBoundsException e) {
                ui.showError("Can you please double check your task number?");
            } catch (NumberFormatException e) {
                ui.showError("Can you please pass in a number?");
            } catch (DateTimeParseException e) {
                ui.showError("Can you please ensure your dates are valid? (hint: yyyy-mm-dd)");
            } catch (IOException e) {
                ui.showError("Sorry, something went wrong with saving");
            }

            ui.printHorizontalLine();
            command = sc.nextLine();
        }

        // Exit
        ui.showExit();
    }

    public static void main(String[] args) {
        new Duke("data/data.txt").run();
    }
}
