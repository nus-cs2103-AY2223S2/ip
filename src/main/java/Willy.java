import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import exception.WillyException;
import task.Deadline;
import task.Event;
import task.Task;
import task.TaskList;
import task.Todo;

public class Willy {

    public static void listCommand(TaskList tList) {
        int taskCount = tList.getTaskCount();
        if (taskCount == 0) {
            System.out.println("You have 0 tasks in your list");
        } else {
            System.out.format("You have %d tasks in your list \n", taskCount);
            System.out.println(tList.toString());
        }
    }

    public static void byeCommand() {
        System.out.println("Bye. Hope to see you again soon!");
        System.exit(0);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Hello! I'm Willy+\nWhat can I do for you?");

        TaskList tList = new TaskList();

        while (true) {
            // Init scanner
            String command = sc.nextLine();

            // For marking
            String[] tempBySpace = command.split(" ");
            // For adding
            String[] tempBySlash = command.split("/");
            // Task tempBySlashTask = lst.get(Integer.parseInt(tempBySpace[1]) - 1);

            try {
                if (tempBySpace[0].equals("mark")) {
                    int index = Integer.parseInt(tempBySpace[1]) - 1;
                    tList.markTask(index);
                } else if (tempBySpace[0].equals("unmark")) {
                    int index = Integer.parseInt(tempBySpace[1]) - 1;
                    tList.unmarkTask(index);
                } else if (command.contains("delete")) {
                    int index = Integer.parseInt(tempBySpace[1]) - 1;
                    tList.deleteTask(index);
                    // System.out.format("Now you have %d things in your list%n", lst.size());

                } else if (command.equals("list")) {
                    listCommand(tList);
                } else if (command.equals("bye")) {
                    byeCommand();
                    sc.close();
                } else if (command.equals("blah")) {
                    throw new WillyException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                } else {
                    if (command.contains("todo")) {
                        if (command.length() > 4) {
                            tList.addTodo(command);
                        } else {
                            throw new WillyException("☹ OOPS!!! The description of a todo cannot be empty.");
                        }
                    }
                    if (command.contains("deadline")) {
                        if (tempBySlash.length > 2) {
                            String combinedString = String.join(" ",
                                    Arrays.asList(tempBySlash).subList(1, tempBySlash.length));
                            String dateString = combinedString.substring(3);
                            String[] dateArray = dateString.split(" ");
                            tList.addDeadlineWithDate(tempBySlash[0], dateArray);

                            System.out.println("help");
                        } else {
                            tList.addDeadline(tempBySlash[0], tempBySlash[1]);
                        }
                    }
                    if (command.contains("event")) {
                        tList.addEvent(tempBySlash[0], tempBySlash[1], tempBySlash[2]);
                    }

                }
            } catch (WillyException e) {
                System.out.println(e.getMessage());
            }
        }
    }

}
