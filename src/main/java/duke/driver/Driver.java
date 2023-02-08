package duke.driver;

import java.util.Scanner;

import duke.command.DukeCommand;
import duke.parser.DukeParser;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.ToDo;
import duke.ui.Ui;



public class Driver {

    public Driver() {}


    private Scanner scanner = new Scanner(System.in);

    /**
     * Executes the series of commands, and returns the updated input {@code taskList}.
     * 
     * @param {@code taskList}
     * @return The updated input {@code taskList}.
     */
    public TaskList run(TaskList taskList) {
        while (true) {
            try {
                String inputString = scanner.nextLine();
                DukeCommand command = DukeParser.parseCommand(inputString);
                String[] commandArgs = DukeParser.parseCommandArgs(command, inputString);


                if (command.equals(DukeCommand.BYE)) {
                    Ui.prettyPrint("Bye. Hope to see you again soon!");
                    scanner.close();
                    return taskList;
                }

                switch (command) {
                case LIST: {
                    String[] taskStringList = taskList.getPrintableTaskList();
                    Ui.prettyPrint(taskStringList);
                    break;
                }
                case BYE: {
                    Ui.prettyPrint("Bye. Hope to see you again soon!");
                    scanner.close();
                    break;
                }
                case DEADLINE: {
                    String description = commandArgs[0];
                    String by = commandArgs[1];
                    Deadline deadline = new Deadline(description, by);
                    taskList.addTask(deadline);
                    int numTasks = taskList.getNumTasks();

                    Ui.printAddTask(deadline, numTasks);
                    break;
                }
                case EVENT: {
                    String description = commandArgs[0];
                    String from = commandArgs[1];
                    String to = commandArgs[2];
                    Event event = new Event(description, from, to);

                    taskList.addTask(event);
                    int numTasks = taskList.getNumTasks();

                    Ui.printAddTask(event, numTasks);
                    break;
                }
                case TODO: {
                    String description = commandArgs[0];
                    Task task = new ToDo(description);
                    taskList.addTask(task);
                    int numTasks = taskList.getNumTasks();

                    Ui.printAddTask(task, numTasks);
                    break;
                }
                case FIND: {
                    String keyword = commandArgs[0];

                    Ui.prettyPrint("Here are the matching tasks in your list:",
                            taskList.find(keyword).toString());
                    break;
                }
                case MARK: {
                    int taskIndex = Integer.parseInt(commandArgs[0]);
                    try {
                        Ui.prettyPrint("Nice! I've marked this task as done:",
                                taskList.markTask(taskIndex));
                    } catch (Exception e) {
                        // TODO : remove pokemon exception
                        Ui.prettyPrint(e.getMessage());
                    }
                    break;
                }
                case UNMARK: {
                    int taskIndex = Integer.parseInt(commandArgs[0]);
                    Ui.prettyPrint("OK, I've marked this task as not done yet:",
                            taskList.unmarkTask(taskIndex));
                    break;
                }
                case DELETE: {
                    int taskIndex = Integer.parseInt(commandArgs[0]);
                    Ui.prettyPrint("Noted. I've removed this task:",
                            taskList.deleteTask(taskIndex));
                    break;
                }
                }
            } catch (Error e) {
                Ui.prettyPrint(e.getMessage());
            }
        }
    }

}
