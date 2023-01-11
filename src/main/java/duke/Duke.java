package duke;

import duke.customization.*;
import duke.exception.*;
import duke.instruction.*;
import duke.task.TaskList;
import java.util.Scanner;

public class Duke {

    private static final TaskList list = new TaskList();
    private static final DisplayFormat format = new DisplayFormat(50, 4);

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
            if (input.equals("bye")) {
                ExitInstruction exit = new ExitInstruction();
                exit.run(list);
            } else if (input.equals("list")) {
                ListInstruction ls = new ListInstruction();
                ls.run(list);
            } else if (input.matches("mark [0-9]*")) {
                try {
                    String index = input.split(" ")[1];
                    MarkAsDoneInstruction mark = new MarkAsDoneInstruction(Integer.parseInt(index) - 1);
                    mark.run(list);
                } catch (InvalidMarkInputException e) {
                    format.displayWithBar(e.getMessage());
                }
            } else if (input.matches("unmark [0-9]*")) {
                try {
                    String index = input.split(" ")[1];
                    UnmarkInstruction unmark = new UnmarkInstruction(Integer.parseInt(index) - 1);
                    unmark.run(list);
                } catch (InvalidUnmarkInputException e) {
                    format.displayWithBar(e.getMessage());
                }
            } else {
                AddTaskInstruction addTask = new AddTaskInstruction(input);
                addTask.run(list);
            }
        }
    }
}
