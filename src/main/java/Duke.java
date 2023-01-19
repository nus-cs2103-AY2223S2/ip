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
    private static List<String> taskList = new ArrayList<>();

    private static void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        reply("Hello! I'm Duke\nWhat can I do for you?");
    }

    private static void reply(String s) {
        System.out.println("\t"
                + "____________________________________________________________");
        System.out.println("\t" + s.replace("\n", "\n    "));
        System.out.println("\t"
                + "____________________________________________________________");
    }

    /**
     * Takes string and adds to static array of strings.
     *
     */
    private static void addTask(String task) {
        taskList.add(task);
        reply("added: " + task);
    }

    private static void displayTasks() {
        StringBuilder sb = new StringBuilder();
        int taskIndex;
        String taskDescription;

        for (int i = 0; i < taskList.size(); i++) {
            taskIndex = i + 1;
            taskDescription = taskList.get(i);
            sb.append(String.format("%d. %s\n", taskIndex, taskDescription));
        }
        reply(sb.toString());
    }

    private static void printExitMessage() {
        reply("Bye! Hope to see you again soon!");
    }

    private static void runDuke() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String userInput = sc.nextLine().toLowerCase();
            switch (userInput) {
                case "list":
                    displayTasks();
                    break;
                case "bye":
                    printExitMessage();
                    break;
                default:
                    addTask(userInput);
                    break;
            }
            if (userInput.equals("bye")) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        greet();
        runDuke();
    }
}
