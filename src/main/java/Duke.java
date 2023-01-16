import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import Exceptions.*;

public class Duke {
    public static void main(String[] args) throws DukeException {

        // Duke's greeting
        String greet = "Hello! I'm Duke\nWhat can I do for you?";
        System.out.println(greet);

        List<Task> taskList = new ArrayList<>();

        /*
        Initialize the scanner to take in a user input.
        The first word is taken as the command. The subsequent words are
        for the command description.
         */
        Scanner sc = new Scanner(System.in);

        bot:
        while (sc.hasNext()) {
            String command = sc.next();
            String description = sc.nextLine();

            switch (command) {
                // Command for bye
                case "bye":
                    String goodbye = "Bye. Hope to see you again soon!";
                    System.out.println(goodbye);
                    break bot;

                // Command for list
                case "list": {
                    if (taskList.size() > 0) {
                        System.out.println("Here are the tasks in your list:");
                        int taskCount = 0;
                        for (Task t : taskList) {
                            System.out.println(taskCount+1 + "." + t);
                            taskCount++;
                        }
                    } else {
                        System.out.println("You have no tasks. Hooray!");
                    }
                    break;
                }

                // Command to mark as done
                case "mark": {
                    description = description.substring(1);
                    int taskToMark = Integer.parseInt(description) - 1;
                    int taskCount = 0;

                    if (taskToMark > taskList.size()) {
                        System.out.println("Sorry! The value you chose is out of bounds!");
                    }

                    for (Task t : taskList) {
                        if (taskToMark == taskCount) {
                            t.markAsDone();
                            System.out.println("Nice! I've marked this task as done:");
                            System.out.println("    " + t);
                            break;
                        } else {
                            taskCount++;
                        }
                    }
                    break;
                }

                // Command to unmark
                case "unmark": {
                    description = description.substring(1);
                    int taskToMark = Integer.parseInt(description) - 1;
                    int taskCount = 0;

                    if (taskToMark > taskList.size()) {
                        System.out.println("Sorry! The value you chose is out of bounds!");
                    }

                    for (Task t : taskList) {
                        if (taskToMark == taskCount) {
                            t.markAsUndone();
                            System.out.println("OK, I've marked this task as not done yet:");
                            System.out.println("    " + t);
                            break;
                        } else {
                            taskCount++;
                        }
                    }
                    break;
                }

                // Command to remove task
                case "delete": {
                    description = description.substring(1);
                    int taskToRemove = Integer.parseInt(description) - 1;
                    int taskCount = 0;

                    if (taskToRemove > taskList.size()) {
                        System.out.println("Sorry! The value you chose is out of bounds!");
                    }

                    for (Task t : taskList) {
                        if (taskToRemove == taskCount) {
                            System.out.println("Noted. I've removed this task:");
                            System.out.println("    " + t);
                            taskList.remove(taskCount);
                            if (taskList.size() == 1) {
                                System.out.println("Now you have 1 task in the list.");
                            } else if (taskList.size() == 0) {
                                System.out.println("You have no tasks in the list.");
                            } else {
                                System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                            }
                            break;
                        } else {
                            taskCount++;
                        }
                    }
                    break;
                }

                case "todo": {

                    if (description.length() == 0) {
                        throw new DukeTodoEmpty();
                    }

                    description = description.substring(1);

                    System.out.println("Got it. I've added this task:");
                    Todo todo = new Todo(description);
                    taskList.add(todo);
                    System.out.println("    " + todo);

                    // Grammar police
                    if (taskList.size() == 1) {
                        System.out.println("Now you have 1 task in the list.");
                    } else if (taskList.size() == 0) {
                        System.out.println("You have no tasks in the list.");
                    } else {
                        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                    }
                    break;
                }

                case "deadline": {

                    if (description.length() == 0) {
                        throw new DukeDeadlineEmpty();
                    }

                    description = description.substring(1);
                    String[] s = description.split("/");
                    System.out.println("Got it. I've added this task:");
                    Deadline deadline = new Deadline(s[0], s[1].substring(2));
                    taskList.add(deadline);
                    System.out.println("    " + deadline);

                    // Grammar police
                    if (taskList.size() == 1) {
                        System.out.println("Now you have 1 task in the list.");
                    } else if (taskList.size() == 0) {
                        System.out.println("You have no tasks in the list.");
                    } else {
                        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                    }
                    break;
                }

                case "event": {

                    if (description.length() == 0) {
                        throw new DukeEventEmpty();
                    }

                    description = description.substring(1);
                    String[] s = description.split("/");
                    System.out.println("Got it. I've added this task:");
                    Event event = new Event(s[0], s[1].substring(4), s[2].substring(2));
                    taskList.add(event);
                    System.out.println("    " + event);

                    // Grammar police
                    if (taskList.size() == 1) {
                        System.out.println("Now you have 1 task in the list.");
                    } else if (taskList.size() == 0) {
                        System.out.println("You have no tasks in the list.");
                    } else {
                        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                    }
                    break;
                }

                default: {
                        throw new DukeUnknownCommand();
                    }
            }
        }
    }
}
