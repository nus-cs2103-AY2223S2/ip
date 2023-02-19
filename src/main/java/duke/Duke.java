package duke;

import duke.command.Command;

import duke.exception.DukeException;

import duke.parser.Parser;

import duke.tasklist.TaskList;

import duke.ui.TextUi;

import java.util.Scanner;


/**
 * Represents the chatbot that helps a user maintain a list of Tasks.
 */
public class Duke {
    private final TaskList taskList = new TaskList();

    private void run() {

        TextUi.greet();

        Scanner commandScanner = new Scanner(System.in);
        boolean isEnd = false;

        while (true) {

            String userCommand = TextUi.getUserCommand(commandScanner);

            try {
                Command command = Parser.parse(userCommand);
                String response = command.execute(this.taskList);
                TextUi.show(response);
                isEnd = command.isExitCommand();

            } catch (DukeException e) {
                TextUi.showError(e);

            } finally {
                TextUi.endCommand();

            }

            if (isEnd) {
                break;
            }

        }

        commandScanner.close();

    }

    public static void main(String[] args) {
        Duke bot = new Duke();
        bot.run();

    }

    /**
     * Returns the chatbot's response to the given user command.
     *
     * @param userCommand The user's text input.
     * @return The String response of the chatbot.
     */
    public String getResponse(String userCommand) {

        String response = "";

        try {
            Command command = Parser.parse(userCommand);
            response = command.execute(this.taskList).strip();

        } catch (DukeException e) {
            response = e.getMessage();

        } finally {
            return response;

        }
    }

}
