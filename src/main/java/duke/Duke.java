package duke;

import duke.command.Command;

import duke.exception.DukeException;

import duke.parser.Parser;

import duke.tasklist.TaskList;

import duke.ui.Ui;

import java.util.Scanner;

/**
 * Represents the chatbot that helps a user maintain a list of Tasks.
 */
public class Duke {
    private final TaskList taskList = new TaskList();

    private void start() {

        Ui.greet();

        Scanner commandScanner = new Scanner(System.in);
        boolean toExit = false;

        while (true) {

            String userCommand = Ui.getUserCommand(commandScanner);

            try {
                Command command = Parser.parse(userCommand);
                String response = command.execute(this.taskList);
                Ui.show(response);
                toExit = command.isExitCommand();

            } catch (DukeException e) {
                Ui.showError(e);

            } finally {
                Ui.endCommand();

            }

            if (toExit) {
                break;
            }

        }

        commandScanner.close();

    }

    public static void main(String[] args) {
        Duke bot = new Duke();
        bot.start();

    }
}
