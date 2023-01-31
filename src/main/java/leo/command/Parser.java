package leo.command;

import java.util.Objects;
import java.util.Scanner;

import leo.leoexception.LeoException;
import leo.storage.Storage;
import leo.ui.Ui;

/**
 * Represents a Parser make sense of commands from user input.
 */
public class Parser {

    private final Storage s;

    /**
     * Constructor to create a Parser object.
     *
     * @param s Storage to store Tasks from commands by user.
     */
    public Parser(Storage s) {
        this.s = s;
    }

    /**
     * Reads user inputs line by line.
     */
    public void readCommand() {
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        while (!command.contains("bye")) {
            if (Objects.equals(command, "list")) {
                new ListCommand(s, command);
            } else if (command.contains("mark")) {
                if (command.contains("unmark")) {
                    new UnmarkCommand(s, command);
                } else {
                    new MarkCommand(s, command);
                }
            } else if (command.contains("delete")) {
                new DeleteCommand(s, command);
            } else if (command.contains("find")) {
                new FindCommand(s, command);
            } else {
                try {
                    new AddCommand(s, command);
                } catch (LeoException e) {
                    Ui.displayMessage(Ui.leoResponse(e.getMessage()));
                }
            }
            command = scanner.nextLine();
        }

        new ExitCommand(s, command);
    }

}
