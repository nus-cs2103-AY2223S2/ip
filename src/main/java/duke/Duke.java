package duke;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.time.format.DateTimeParseException;

import java.util.Scanner;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Response response;

    /**
     * Represents the Duke program.
     *
     * @param filePath File path of task list in storage.
     */
    public Duke(String filePath) {
        response = new Response();
        storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            tasks = new TaskList();
        }
    }

    public String getGreetingMessage() {
        return response.showGreeting();
    }

    /**
     * Returns Duke's response to user input.
     *
     * @param input User input.
     * @return Duke's response.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            assert c != null : "command c should not be null";
            return c.execute(tasks, response, storage);
        } catch (DukeException e) {
            return response.showError(e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            return response.showError("Can you please double check your task number?");
        } catch (NumberFormatException e) {
            return response.showError("Can you please pass in a number?");
        } catch (DateTimeParseException e) {
            return response.showError("Can you please ensure your dates are valid? (hint: yyyy-mm-dd)");
        } catch (IOException e) {
            return response.showError("Sorry, something went wrong with saving");
        }
    }
}
