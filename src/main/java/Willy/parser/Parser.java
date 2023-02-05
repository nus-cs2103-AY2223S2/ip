package Willy.parser;

import java.util.Arrays;

import Willy.exception.WillyException;
import Willy.task.TaskList;

public class Parser {

    private TaskList tList;
    private boolean isExit;

    public Parser(TaskList tList) {
        this.tList = tList;
        this.isExit = false;
    }

    public static void listCommand(TaskList tList) {
        int taskCount = tList.getTaskCount();
        if (taskCount == 0) {
            System.out.println("You have 0 tasks in your list");
        } else {
            System.out.format("You have %d tasks in your list \n", taskCount);
            System.out.println(tList.toString());
        }
    }

    // public static void byeCommand() {
    // System.out.println("Bye. Hope to see you again soon!");
    // System.exit(0);
    // }

    public int getIndex(String input) {
        return Integer.parseInt(input) - 1;
    }

    public boolean getExitStatus() {
        return isExit;
    }

    public void exitCommand() {
        System.out.println("Bye. Hope to see you again soon!");
        isExit = true;
    }

    public void parseCommand(String command) throws WillyException {
        String[] tempBySpace = command.split(" ");
        String[] tempBySlash = command.split("/");

        if (tempBySpace[0].equals("mark")) {
            int index = getIndex(tempBySpace[1]);
            tList.markTask(index);
        } else if (tempBySpace[0].equals("unmark")) {
            int index = getIndex(tempBySpace[1]);
            tList.unmarkTask(index);
        } else if (command.contains("delete")) {
            int index = getIndex(tempBySpace[1]);
            tList.deleteTask(index);
        } else if (command.equals("list")) {
            listCommand(tList);
        } else if (command.equals("bye")) {
            exitCommand();
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
                } else {
                    tList.addDeadline(tempBySlash[0], tempBySlash[1]);
                }
            }
            if (command.contains("event")) {
                tList.addEvent(tempBySlash[0], tempBySlash[1], tempBySlash[2]);
            }
        }
    }

}
