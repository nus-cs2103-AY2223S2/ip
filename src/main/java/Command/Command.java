package command;

import java.util.Objects;
import java.util.Scanner;
import leoException.LeoException;
import storage.Storage;

public class Command {

    private final Storage s;

    public Command(Storage s) {
        this.s = s;
    }

    public void readCommand() {
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        while (!command.contains("bye")) {
            if (Objects.equals(command, "list")) {
                new List(s);
            } else if (command.contains("mark")) {
                if (command.contains("unmark")) {
                    new Unmark(s, command);
                } else {
                    new Mark(s, command);
                }
            } else if (command.contains("delete")) {
                new Delete(s, command);
            } else {
                try {
                    new Add(s, command);
                } catch (LeoException e) {
                    System.out.println(e.getMessage());
                }
            }
            command = scanner.nextLine();
        }

        new Exit(s);
    }

}
