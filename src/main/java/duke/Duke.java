package duke;

import duke.customization.Ui;
import duke.exception.*;
import duke.instruction.*;
import duke.parser.Parser;
import duke.storage.DukeStorage;
import duke.task.TaskList;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Duke {
    private final DukeStorage storage;
    private TaskList list;
    private final Ui ui;

    String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|";
    String greetingMessage = "Hello! I'm Duke\n"
            + "What can I do for you?";

    public Duke(String filePath) {
        ui = new Ui();
        storage = new DukeStorage(filePath);
        list = new TaskList();

        try {
            list = storage.load();
            System.out.println(list);
        } catch (FileNotFoundException e) {
            ui.displayWithBar("Warning: something went wrong when loading the TaskList\n" +
                    e.getMessage());
            list = new TaskList();
        } catch (InvalidInputException e) {
            ui.displayWithBar(e.getMessage());
            list = new TaskList();
        }
    }

    public void run() {
        // display logo and greeting messages
        System.out.println("Hello from\n" + logo);
        ui.displayWithBar(greetingMessage);

        Scanner sc = new Scanner(System.in);
        // taking input from the user until receiving an exit instruction
        while (sc.hasNext()) {
            String input = sc.nextLine();
            try {
                Command instruction = Parser.parseInstruction(input);
                instruction.run(list);
                storage.save(list);
            } catch (DukeException e) {
                ui.displayWithBar(e.getMessage());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
