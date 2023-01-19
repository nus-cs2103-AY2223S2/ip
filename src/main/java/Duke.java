import models.Item;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class Duke {

    public static Optional<Integer> parseInt(String str) {
        try {
            return Optional.of(Integer.parseInt(str));
        } catch (NumberFormatException nfe) {
            return Optional.empty();
        }
    }

    public static void main(String[] args) {
        var reader = new Scanner(System.in);
        var writer = System.out;
        var items = new ArrayList<Item>();

        writer.println("Hello, I'm Kude!");

        while (true) {
            writer.print("> ");
            var cmd = reader.nextLine();
            var cmdArgs = cmd.split(" ");

            if (cmdArgs[0].equals("mark") && cmdArgs.length >= 2) {
                var idxOpt = parseInt(cmdArgs[1]);
                idxOpt.ifPresentOrElse(
                    idx -> {
                        if (idx < 1 || idx > items.size()) {
                            writer.println("< invalid index!");
                            return;
                        }
                        var item = items.get(idx - 1);
                        item.setIsDone(true);
                        writer.println("< marked: " + item);
                    },
                    () -> writer.println("< provide index!")
                );
            }
            else if (cmdArgs[0].equals("unmark") && cmdArgs.length >= 2) {
                var idxOpt = parseInt(cmdArgs[1]);
                idxOpt.ifPresentOrElse(
                        idx -> {
                            if (idx < 1 || idx > items.size()) {
                                writer.println("< invalid index!");
                                return;
                            }
                            var item = items.get(idx - 1);
                            item.setIsDone(false);
                            writer.println("< unmarked: " + item);
                        },
                        () -> writer.println("< provide index!")
                );
            }
            else if (cmd.equals("list")) {
                for (int i = 0; i < items.size(); i++) {
                    writer.println("< " + (i + 1) + ". " + items.get(i));
                }
            }
            else if (cmd.equals("bye")) {
                writer.println("< Bye!!");
                return;
            } else {
                items.add(new Item(cmd));
                writer.println("< added: " + cmd);
            }
        }
    }
}
