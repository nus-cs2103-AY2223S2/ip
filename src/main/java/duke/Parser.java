package duke;

import java.util.Scanner;
public class Parser {
    private TaskList tasksList = new TaskList();
    private Storage storage = new Storage();
    Scanner scanner = new Scanner(System.in);

    String input = "";

    public void getInput() {
        while (!input.equals("bye")) {
            try {
                input = scanner.nextLine();
                String command = input.split(" ")[0];

                if (command.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                } else if (command.equals("list")) {
                    tasksList.listTaskList();
                } else if (command.equals("mark")) {
                    tasksList.markTask(input);
                } else if (command.equals("unmark")) {
                    tasksList.unmarkTask(input);
                } else if (command.equals("todo")) {
                    tasksList.addToDo(input);
                } else if (command.equals("deadline")) {
                    tasksList.addDeadline(input);
                } else if (command.equals("event")) {
                    tasksList.addEvent(input);
                } else if (command.equals("delete")) {
                    tasksList.deleteTask(input);
                } else if (command.equals("find")) {
                    tasksList.findEvent(input);
                } else {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                // if you try and catch the exception, you can still continue to run the program! WOW!
                e.printStackTrace();
            }
        }
    }
}
