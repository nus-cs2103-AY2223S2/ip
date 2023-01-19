import java.util.ArrayList;
import java.util.Scanner;

import static utils.UI.*;


public class Duke {
    private static Scanner sc = new Scanner(System.in);
    private static String currentInput;
    private static ArrayList<Task> taskList = new ArrayList<>();

    public static String mark(boolean toMark) {
        int index = Integer.parseInt(toMark ? currentInput.substring(5) : currentInput.substring(7)) - 1;
        if (index >= taskList.size() || index < 0) {
            return "Task index out of bounds, please input a valid index";
        } else {
            Task curTask = taskList.get(index);
            curTask.setCompleted(toMark);
            String output;
            if (toMark) {
                output = "Nice! I've marked this task as done:\n";
            } else {
                output = "OK, I've marked this task as not done yet:\n";
            }
            return output + "  " + curTask.toString();
        }
    }

    public static String list() {
        if (taskList.size() == 0) {
            return "List empty, add tasks!";
        } else {
            StringBuilder response = new StringBuilder();
            for (int i = 0; i < taskList.size(); i++) {
                Task curTask = taskList.get(i);
                response.append((i+ 1)).append(".").append(curTask.toString());
                if (i < taskList.size() - 1) {
                    response.append("\n");
                }
            }
            return response.toString();
        }
    }

    public static String addTask() {
        StringBuilder response = new StringBuilder();
        response.append("Got it. I've added this task:\n");
        if (currentInput.matches("^todo .*")) {
            //Adding a Todo task
            taskList.add(new Todo(currentInput.substring(5)));
        } else if (currentInput.matches("^deadline .*")) {
            //Adding a Deadline
            int byPos = currentInput.indexOf(" /by ");
            if (byPos == -1) {
                reply("Deadline not specified with /by");
                return "";
            }
            taskList.add(new Deadline(currentInput.substring(9, byPos), currentInput.substring(byPos + 5)));
        } else {
            //Adding an Event
            int fromPos = currentInput.indexOf(" /from ");
            int toPos = currentInput.indexOf(" /to ");
            if (fromPos == -1 || toPos == -1 || toPos > currentInput.length() + 4) {
                reply("Please include both /from and /to");
                return "";
            }
            if (fromPos > toPos) {
                reply("Please add the from date first followed by to date");
                return "";
            }
            if (fromPos == 5) {
                reply("Please include a description of the task");
                return "";
            }
            String description = currentInput.substring(6, fromPos);
            String from = currentInput.substring(fromPos + 7, toPos);
            String to = currentInput.substring(toPos+ 5);
            taskList.add(new Event(description, from, to));
        }
        int count = taskList.size();
        response.append("  ").append(taskList.get(count - 1).toString()).append("\n");
        response.append("Now you have ").append(count).append(" tasks in the list.");
        return response.toString();
    }

    public static String deleteTask(String command) {
        StringBuilder response = new StringBuilder();
        int index = Integer.parseInt(command.substring(7)) - 1;
        if (index < 0 || index >= taskList.size()) {
            return "Error: Please input a valid task index!";
        } else {
            response.append("Noted. I've removed this task:\n");
            response.append("  ").append(taskList.get(index).toString()).append("\n");
            taskList.remove(index);
            response.append("Now you have ").append(taskList.size()).append(" tasks in the list.");
        }
        return response.toString();
    }

    public static void main(String[] args) {
        //Introduction
        greet();
        currentInput = sc.nextLine();
        while (!currentInput.equalsIgnoreCase("bye")) {
            //when there is no input
            if (currentInput.equals("")) {
                reply("Please input a command");
            } else if (currentInput.equalsIgnoreCase("list")) {
                reply(list());
            } else if (currentInput.matches("mark \\d+") || currentInput.matches("unmark \\d+")) {
                boolean toMark = currentInput.matches("mark \\d+");
                reply(mark(toMark));
            } else if (currentInput.matches("^(todo|deadline|event) .*")) {
                reply(addTask());
            } else if (currentInput.matches("^delete \\d+")) {
                reply(deleteTask(currentInput));
            } else {
                reply("Unknown command, please try again");
            }
            currentInput = sc.nextLine();
        }
        //Signing off
        signOff();
    }
}
