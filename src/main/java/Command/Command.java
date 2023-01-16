package Command;

import Storage.Storage;

import java.util.Objects;
import java.util.Scanner;

public class Command {

    private final Storage s = new Storage();

    public void readCommand() {
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        while (!Objects.equals(command, "bye")) {
            if (Objects.equals(command, "list")) {
                new List(s);
            } else if (command.contains("mark")) {
                if (command.contains("unmark")) {
                    new Unmark(s, command);
                } else {
                    new Mark(s, command);
                }
            } else {
                new Add(s, command);
            }
            command = scanner.nextLine();
        }

        new Exit();
    }

}
