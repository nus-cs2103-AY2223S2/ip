package duke.parser;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exception.EmptyDescriptionException;
import duke.exception.WrongCommandException;
import duke.exception.WrongFormatException;
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
    private ArrayList<String> listOfCommands;

    /**
     * Constructor for Parser
     * @param taskList
     */
    public Parser(TaskList taskList) {
        this.taskList = taskList;
        //this.sc = new Scanner(System.in);
        this.listOfCommands = new ArrayList<>() {
            {
                //add("list");
                add("mark");
                add("unmark");
                add("todo");
                add("deadline");
                add("event");
                add("delete");
                add("find");
            }
        };
    }

    /**
     * Perform the user command and create the tasks
     * @throws EmptyDescriptionException If no description of task after the command word
     * @throws WrongCommandException If wrong command word is being entered
     */
    public String performCommand(String input) {
        try {
            this.checkCommand(input);

            if (input.equals("bye")) {
                return "Bye. Hope to see you again soon!";
            } else if (input.equals("list")) {
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
                    return "Please enter a valid command and/or task!";
                }
            }
        } catch (EmptyDescriptionException | WrongCommandException | WrongFormatException e){
            return e.getMessage();
        } catch (DateTimeParseException e) {
            return "Please enter date in the correct format! YYYY-MM-DD, example: 2023-10-10";
        }

    }

    /**
     * Check whether the command and input is valid or invalid
     * @param input Input String by user
     * @return Perform command to return string by Duke
     */
    public void checkCommand(String input) throws EmptyDescriptionException, WrongCommandException, WrongFormatException {
        String[] arrOfString = input.trim().split(" ");
        String command = arrOfString[0];
        if (listOfCommands.contains(input)) {
            throw new EmptyDescriptionException();
        } else if (arrOfString.length > 1 && (command.equals("bye") || command.equals("list"))) {
            throw new WrongFormatException();
        } else if (!listOfCommands.contains(command)) {
            throw new WrongCommandException();
        }
    }

}




