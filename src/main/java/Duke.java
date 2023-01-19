package main.java;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String FULL_LINE = "_______________________________________________\n";
    private static final String ADD_TASK_OUTPUT = "Got it. I've added this task:\n\t%s\nNow you have %d tasks in the list.";
    private static final String DELETE_TASK_OUTPUT = "Noted. I've removed this task:\n\t%s\nNow you have %d tasks in the list.";
    private static ArrayList<Task> taskList = new ArrayList<>();


    public static void main(String[] args) {
        String welcomeString = "Hello I'm Duke, your personal task manager!";
        String byeString = "Bye. Hope to see you again soon!";

        printFormattedOutput(welcomeString);

        String input = "";
        Scanner sc = new Scanner(System.in);

        while (true) {
            input = sc.nextLine();
            try {
                String output = "";
                if (input.equals("bye")) {
                    printFormattedOutput(byeString);
                    break;
                } else if (input.equals("list")) {
                    output = "Here are the tasks in your list:";
                    for (int i = 0; i < taskList.size(); ++i) {
                        output += String.format("\n%d.%s", i + 1, taskList.get(i).toString());
                    }
                } else if (input.matches("mark \\d+")) {
                    int index = Integer.parseInt(input.replace("mark ", ""));
                    if (index == 0 || index > taskList.size()) {
                        throw new DukeException(String.format(ERROR.INVALID_INDEX.getMessage(), taskList.size()));
                    }
                    Task task = taskList.get(index - 1);
                    task.markAsDone();
                    output = "Nice! I've marked this task as done:\n" + task.toString();
                } else if (input.matches("unmark \\d+")) {
                    int index = Integer.parseInt(input.replace("unmark ", ""));
                    if (index == 0 || index > taskList.size()) {
                        throw new DukeException(String.format(ERROR.INVALID_INDEX.getMessage(), taskList.size()));
                    }
                    Task task = taskList.get(index - 1);
                    task.markAsUndone();
                    output = "OK, I've marked this task as not done yet:\n" + task.toString();
                } else if (input.matches("delete \\d+")) {
                    int index = Integer.parseInt(input.replace("delete ", ""));
                    if (index == 0 || index > taskList.size()) {
                        throw new DukeException(String.format(ERROR.INVALID_INDEX.getMessage(), taskList.size()));
                    }
                    Task task = taskList.get(index - 1);
                    taskList.remove(index - 1);
                    output = String.format(DELETE_TASK_OUTPUT, task.toString(), taskList.size());
                } else if (input.startsWith("todo")) {
                    String desc = input.replace("todo", "").trim();
                    if (desc.isBlank()) {
                        throw new DukeException(ERROR.TODO_EMPTY.getMessage());
                    }
                    Todo task = new Todo(desc);
                    taskList.add(task);
                    output = String.format(ADD_TASK_OUTPUT, task.toString(), taskList.size());
                } else if (input.startsWith("deadline")) {
                    String desc = input.replace("deadline", "").trim();
                    if (desc.isBlank()) {
                        throw new DukeException(ERROR.DEADLINE_EMPTY.getMessage());
                    }
                    String[] params = desc.split(" /by ");
                    Deadline task = new Deadline(params[0], params[1]);
                    taskList.add(task);
                    output = String.format(ADD_TASK_OUTPUT, task.toString(), taskList.size());
                } else if (input.startsWith("event")) {
                    String desc = input.replace("event", "").trim();
                    if (desc.isBlank()) {
                        throw new DukeException(ERROR.EVENT_EMPTY.getMessage());
                    }
                    String[] params = desc.split("( /from | /to )");
                    Event task = new Event(params[0], params[1], params[2]);
                    taskList.add(task);
                    output = String.format(ADD_TASK_OUTPUT, task.toString(), taskList.size());
                } else {
                    throw new DukeException(ERROR.INVALID_INPUT.getMessage());
                }
                printFormattedOutput(output);
            } catch (DukeException dukeException) {
                printFormattedOutput(dukeException.getMessage());
            }
        }
    }

    public static void printFormattedOutput(String output) {
        output = FULL_LINE + output + "\n" + FULL_LINE;
        System.out.println(output);
    }
}