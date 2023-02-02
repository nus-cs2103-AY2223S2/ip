import java.util.ArrayList;
import java.util.Arrays;
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
    private Parser parser = new Parser();

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

    private String formatAddTaskReply(TaskList taskList, Task task) {
        String formattedReply;
        formattedReply = String.format(
                "Got it. I've added this task:\n\t%s\n" +
                        "Now you have %d task(s) in the list.",
                task.toString(),
                taskList.getListSize());
        return formattedReply;
    }

    public void runDuke() {
        Scanner sc = new Scanner(System.in);
        String[] parsedCommand;
        String formattedReply;
        int taskIndex;

        greet();
        while (true) {
            String userInput = sc.nextLine().toLowerCase();
            try {
                parsedCommand = parser.parseCommand(userInput);
                switch (parsedCommand[0]) {
                case "todo":
                    //checkCommandLength(parsedCommand);
                    Task newTodo = new Todo(parsedCommand[1]);
                    taskList.addTask(newTodo);
                    formattedReply = formatAddTaskReply(taskList, newTodo);
                    reply(formattedReply);
                    break;
                case "deadline":
                    //checkCommandLength(parsedCommand);
                    Task newDeadline = new Deadline(parsedCommand[1], parsedCommand[2]);
                    taskList.addTask(newDeadline);
                    formattedReply = formatAddTaskReply(taskList, newDeadline);
                    reply(formattedReply);
                    break;
                case "event":
                    //checkCommandLength(parsedCommand);
                    Task newEvent = new Event(parsedCommand[1], parsedCommand[2], parsedCommand[3]);
                    taskList.addTask(newEvent);
                    formattedReply = formatAddTaskReply(taskList, newEvent);
                    reply(formattedReply);
                    break;
                case "list":
                    formattedReply = String.format(
                            "Here are the tasks in your list:\n%s", taskList.getTaskList());
                    reply(formattedReply);
                    break;
                case "delete":
                    taskIndex = Integer.parseInt(parsedCommand[1]);
                    taskList.delTask(taskIndex);
                    break;
                case "mark":
                    taskIndex = Integer.parseInt(parsedCommand[1]);
                    taskList.markTask(taskIndex - 1);
                    formattedReply = String.format(
                            "Nice! I've marked this task as done:\n%s", taskList.getTask(taskIndex - 1));
                    reply(formattedReply);
                    break;
                case "unmark":
                    taskIndex = Integer.parseInt(parsedCommand[1]);
                    taskList.unmarkTask(taskIndex - 1);
                    formattedReply = String.format(
                            "OK, I've marked this task as not done yet:\n%s", taskList.getTask(taskIndex - 1));
                    reply(formattedReply);
                    break;
                case "bye":
                    printExitMessage();
                    break;
                default:
                    throw new InvalidCommandException("Sorry, I don't understand that command, try again.");
                }
            } catch (InvalidCommandException e) {
                reply(e.getMessage());
                continue;
            }
            if (userInput.equals("bye")) {
                break;
            }
        }
    }
}
