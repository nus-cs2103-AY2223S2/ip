package duke;

import duke.customization.*;
import duke.exception.*;
import duke.instruction.*;
import duke.parser.Parser;
import duke.task.TaskList;

import java.util.Scanner;

public class Duke {

    private static final TaskList list = new TaskList();
    private static final DisplayFormat format = new DisplayFormat(70, 4);

    public static void main(String[] args) throws GeneralDukeException {
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
        while (true) {
            String input = sc.nextLine();
            try{
                GeneralDukeInstruction instruction = Parser.parseInstruction(input);
                instruction.run(list);
            } catch (GeneralDukeException e) {
                format.displayWithBar(e.getMessage());
            }
        }
    }
}
