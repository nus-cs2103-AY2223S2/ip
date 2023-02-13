package duke.parser;
import java.time.LocalDate;
import java.util.Scanner;

import duke.exception.EmptyDescriptionException;
import duke.exception.WrongCommandException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;



/**
 * A parser to understand and perform user command
 */
public class Parser {
    private TaskList taskList;
    private Scanner sc;

    /**
     * Constructor for Parser
     * @param taskList
     */
    public Parser(TaskList taskList) {
        this.taskList = taskList;
        //this.sc = new Scanner(System.in);
    }

    /**
     * Perform the user command and create the tasks
     * @throws EmptyDescriptionException If no description of task after the command word
     * @throws WrongCommandException If wrong command word is being entered
     */
    public String performCommand(String input) {
        //String input = this.sc.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                return taskList.printList();
            } else {
                String[] arrOfString = input.split(" ");
                String command = arrOfString[0];
                switch (command) {
                case "mark":
                    return taskList.markTask(input.substring(5));

                case "unmark":
                    return taskList.unmarkTask(input.substring(7));

                case "todo":
                    Task taskToDo = new ToDo(input);
                    taskList.addTask(taskToDo);
                    return taskList.printAddComment(taskToDo);

                case "deadline":
                    LocalDate deadline = LocalDate.parse(
                            input.substring(input.indexOf("/") + 4));
                    Task taskDeadline = new Deadline(input, deadline);
                    taskList.addTask(taskDeadline);
                    return taskList.printAddComment(taskDeadline);

                case "event":
                    LocalDate startDate = LocalDate.parse(
                            input.substring(input.indexOf("/") + 6,
                                    input.lastIndexOf("/") - 1));
                    LocalDate endDate = LocalDate.parse(
                            input.substring(input.lastIndexOf("/") + 4));
                    Task taskEvent = new Event(input, startDate, endDate);
                    taskList.addTask(taskEvent);
                    return taskList.printAddComment(taskEvent);

                case "delete":
                    return taskList.deleteTask(input.substring(7));

                case "find":
                    return taskList.find(input.substring(5));

                default:
                    return "Cannot understand bruh";
                }
            }
        }

        return "Bye. Hope to see you again soon!";
    }

    /**
     * Check whether the command and input is valid or invalid
     * @param input Input String by user
     * @return Perform command to return string by Duke
     */
    public String checkCommand(String input) {
        String[] arrOfString = input.trim().split(" ");
        String command = arrOfString[0];
        try {
            if ((input.equals("todo")) || (input.equals("deadline")) || (input.equals("event"))) {
                throw new EmptyDescriptionException();
            } else if (!(command.equals("mark") || command.equals("unmark")
                    || command.equals("todo") || command.equals("deadline")
                    || command.equals("event") || command.equals("delete")
                    || command.equals("find") || !command.equals("list"))) {
                throw new WrongCommandException();
            }
        } catch (EmptyDescriptionException | WrongCommandException e) {
            return e.getMessage();
        }
        return this.performCommand(input);
    }

}




