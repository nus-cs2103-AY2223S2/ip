package leo.command;

import java.util.Objects;
import java.util.Scanner;
import leo.leoException.LeoException;
import leo.storage.Storage;
import leo.ui.Ui;

public class Parser {

    private final Storage s;

    public Parser(Storage s) {
        this.s = s;
    }

    public void readCommand() {
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        while (!command.contains("bye")) {
            if (Objects.equals(command, "list")) {
                new List(s, command);
            } else if (command.contains("mark")) {
                if (command.contains("unmark")) {
                    new Unmark(s, command);
                } else {
                    new Mark(s, command);
                }
            } else if (command.contains("delete")) {
                new Delete(s, command);
            } else if (command.contains("find")) {
                new Find(s, command);
            } else {
                try {
                    new Add(s, command);
                } catch (LeoException e) {
                    Ui.displayMessage(Ui.leoResponse(e.getMessage()));
                }
            }
            command = scanner.nextLine();
        }

        new Exit(s, command);
    }

}
