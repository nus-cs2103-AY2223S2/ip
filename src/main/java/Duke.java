import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
Level-1
idea:
- design a "reply" method to format the duke reply text
- print hello statement
- scanf input
    - print indented horizontal line
    - print input
    - print indented horizontal line

Level-2
idea:
- addTask method to add to static array of strings
- formatTaskList to take task array and output string
    in list form
- if list command, reply(formatTaskList(taskArray))
 */
public class Duke {
    private TaskList taskList = new TaskList();

    private void reply(String s) {
        System.out.println("\t"
                + "____________________________________________________________");
        System.out.println("\t" + s.replace("\n", "\n    "));
        System.out.println("\t"
                + "____________________________________________________________");
    }

    private void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        reply("Hello! I'm Duke\nWhat can I do for you?");
    }

    /**
     * Takes string and adds to static array of strings.
     *
     */

    private void printExitMessage() {
        reply("Bye! Hope to see you again soon!");
    }

    public void runDuke() {
        Scanner sc = new Scanner(System.in);
        int taskIndex;

        greet();
        while (true) {
            String userInput = sc.nextLine().toLowerCase();
            String[] command = userInput.split(" ", 10);
            switch (command[0]) {
            case "list":
                reply(taskList.getTaskList());
                break;
            case "bye":
                printExitMessage();
                break;
            case "mark":
                taskIndex = Integer.parseInt(command[1]);
                taskList.markTask(taskIndex);
                break;
            case "delete":
                taskIndex = Integer.parseInt(command[1]);
                taskList.delTask(taskIndex);
                break;
            case "unmark":

            default:
                taskList.addTask(userInput);
                reply("added: " + userInput);
                break;
            }
            if (userInput.equals("bye")) {
                break;
            }
        }
    }
}
