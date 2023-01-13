package duke;

import duke.customization.DisplayFormat;
import duke.exception.*;
import duke.instruction.*;
import duke.parser.Parser;
import duke.storage.DukeStorage;
import duke.task.TaskList;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Duke {

    private static final DukeStorage storage = new DukeStorage();
    private static final DisplayFormat format = new DisplayFormat(70, 4);


    public static void main(String[] args) {
        TaskList list;
        try {
            list = storage.load();
            System.out.println(list);
        } catch (FileNotFoundException e) {
            format.displayWithBar("Warning: something went wrong when loading the TaskList\n" +
                    e.getMessage());
            list = new TaskList();
        } catch (InvalidInputException e) {
            format.displayWithBar(e.getMessage());
            list = new TaskList();
        }

        // display logo and greeting messages
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|";
        String greetingMessage = "Hello! I'm Duke\n"
                + "What can I do for you?";
        System.out.println("Hello from\n" + logo);
        format.displayWithBar(greetingMessage);

        // taking input from the user until receiving an exit instruction
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String input = sc.nextLine();
            try {
                GeneralDukeInstruction instruction = Parser.parseInstruction(input);
                instruction.run(list);
                storage.save(list);
            } catch (GeneralDukeException e) {
                format.displayWithBar(e.getMessage());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
