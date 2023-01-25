import java.util.Scanner;
import java.util.ArrayList;
import java.lang.Math;

public class Duke {
    public static ArrayList<Task> taskList = new ArrayList<>();
    static int tasksDone = 0;
    public static void main(String[] args) throws DukeException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String helpStr = "What can I help you with?";
        String byeStr = "Bye. Hope to see you again soon!";

        System.out.println(helpStr);
        // create a Scanner object to read user input
        Scanner input = new Scanner(System.in);

        // loop until "bye"
        while (true) {
            // read user input
            String command = input.nextLine();

            // check if user inputs "bye"
            if (command.equalsIgnoreCase("bye")) {
                System.out.println(byeStr);
                return;
            }
            // check if user inputs "list" and display items in list
            else if (command.equalsIgnoreCase("list")) {
                if (taskList.size() == 0) {
                    System.out.println("Your list is empty!");
                } else {
                    for (int i = 0; i < taskList.size(); i++) {
                        System.out.println(i + 1 + ". " + taskList.get(i));
                    }
                }
            } else if (command.startsWith("mark ") || command.startsWith("unmark ")) {
                try {
                    // Mark task as done
                    if (command.substring(0, command.indexOf(" ")).equalsIgnoreCase("mark")) {
                        int index = Integer.parseInt(command.replaceAll("mark ", "")) - 1;
                        if (taskList.get(index).isDone()) {
                            throw new DukeException("marked");
                        }
                        else {
                            taskList.get(index).mark();
                            tasksDone += 1;
                        }
                    }
                    // Unmark task
                    else if (command.substring(0, command.indexOf(" ")).equalsIgnoreCase("unmark")) {
                        int index = Integer.parseInt(command.replaceAll("unmark ", "")) - 1;
                        if (taskList.get(index).isDone()) {
                            taskList.get(index).unmark();
                            tasksDone -= 1;
                        }
                        else {
                            throw new DukeException("unmarked");
                        }
                    }
                } catch (DukeException e) {
                    e.MarkedException();
                }
            }
            else {
                try {
                    Task newTask;

                    // Create new ToDo task
                    if (command.toLowerCase().startsWith("todo ")) {
                        String description = command.substring(command.indexOf(" ") + 1);

                        if (description.equals("")) {
                            throw new DukeException("todo");
                        }
                        else{
                            newTask = new ToDo(command.substring(command.indexOf(" ") + 1));
                        }
                    }
                    // Create new Deadline task
                    else if (command.toLowerCase().startsWith("deadline ")) {
                        String description = command.substring(command.indexOf(" ") + 1, command.indexOf("/"));
                        String by = command.substring(command.indexOf("/by") + 4);

                        System.out.println(description + " - " + by);

                        if (description.equals("")) {
                            throw new DukeException("deadline");
                        }
                        else if (by.equals("")) {
                            throw new DukeException("empty time");
                        }
                        else {
                            newTask = new Deadline(description, by);
                        }
                    }
                    // Create new Event task
                    else if (command.toLowerCase().startsWith("event ")) {
                        String description = command.substring(command.indexOf(" ") + 1, command.indexOf("/from"));
                        String from = command.substring(command.indexOf("/from") + 6, command.indexOf(" /to"));
                        String to = command.substring(command.indexOf("/to") + 4);

                        if (description.equals("")) {
                            throw new DukeException("event");
                        }
                        else if (from.equals("") || to.equals("")) {
                            throw new DukeException("empty time");
                        }
                        else {
                            newTask = new Event(description, from, to);
                        }
                    }
                    else {
                        throw new DukeException("wrong");
                    }

                    taskList.add(newTask);
                    System.out.println("I've added this task to your list:");
                    System.out.println(newTask);
                } catch (DukeException e) {
                    e.WrongCommandException();
                    e.EmptyDescriptionException();
                    e.EmptyTimeException();
                }

            }

            System.out.println("You have " + taskList.size() + " tasks in your list. | " +
                     + (taskList.size() - Math.abs(tasksDone)) + " tasks to be completed.");
        }
    }
}
