package duke.driver;

import java.util.Scanner;
import duke.parser.DukeParser;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.ToDo;
import duke.command.DukeCommand;

public class Driver {
    TaskList taskList;

    public Driver() {}

    private static final String BAR =
            "    ____________________________________________________________";

    private static final String INDENTATION = "     ";
    private static final String NEW_LINE = "\n";


    private static void echo(String... texts) {
        System.out.println(BAR);
        for (String text : texts) {
            System.out.println(INDENTATION + text);
        }
        System.out.println(BAR);
        System.out.print(NEW_LINE);
    }

    Scanner scanner = new Scanner(System.in);

    public TaskList run(TaskList tasks) {
        while (true) {
            try {
                String inputString = scanner.nextLine();
                DukeParser parser = new DukeParser(inputString);
                String[] commandArgs = parser.parse();
                DukeCommand command = parser.getCommand();

                if (command.equals(DukeCommand.BYE)) {
                    echo("Bye. Hope to see you again soon!");
                    scanner.close();
                    return tasks;
                }

                switch (command) {
                    case LIST: {
                        System.out.println(BAR);
                        System.out.println(INDENTATION + "list");
                        tasks.printAll();
                        System.out.println(BAR);
                        break;
                    }
                    case BYE: {
                        echo("Bye. Hope to see you again soon!");
                        scanner.close();
                        break;
                    }
                    case DEADLINE: {
                        String description = commandArgs[0];
                        String by = commandArgs[1];
                        Deadline deadline = new Deadline(description, by);
                        tasks.addTask(deadline);
                        int numTasks = tasks.getNumTasks();
                        echo("Got it. I've added this task:", "  " + deadline.toString(),
                                "Now you have " + numTasks + " tasks in the list.");
                        break;
                    }
                    case EVENT: {
                        String description = commandArgs[0];
                        String from = commandArgs[1];
                        String to = commandArgs[2];
                        Event event = new Event(description, from, to);
                        tasks.addTask(event);
                        int numTasks = tasks.getNumTasks();
                        echo("Got it. I've added this task:", "  " + event.toString(),
                                "Now you have " + numTasks + " tasks in the list.");
                        break;
                    }
                    case TODO: {
                        String description = commandArgs[0];
                        Task task = new ToDo(description);
                        tasks.addTask(task);
                        int numTasks = tasks.getNumTasks();
                        echo("Got it. I've added this task:", "  " + task.toString(),
                                "Now you have " + numTasks + " tasks in the list.");
                        break;
                    }
                    case MARK: {
                        int taskIndex = Integer.parseInt(commandArgs[0]);
                        echo("Nice! I've marked this task as done:",
                                " " + tasks.markTask(taskIndex));
                        break;
                    }
                    case UNMARK: {
                        int taskIndex = Integer.parseInt(commandArgs[0]);
                        echo("OK, I've marked this task as not done yet:",
                                " " + tasks.unmarkTask(taskIndex));
                        break;
                    }
                    case DELETE: {
                        int taskIndex = Integer.parseInt(commandArgs[0]);
                        echo("Noted. I've removed this task:", " " + tasks.deleteTasks(taskIndex));
                        break;
                    }
                }
            } catch (Error e) {
                echo(e.getMessage());
            }
        }
    }

}
