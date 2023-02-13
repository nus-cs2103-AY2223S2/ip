package duke.parser;

import duke.exception.EmptyDescriptionException;
import duke.exception.WrongCommandException;
import duke.task.*;

import java.time.LocalDate;
import java.util.Scanner;

/**
 * A parser to understand and perform user command
 */
public class Parser {
    public TaskList taskList;
    public Scanner sc;

    /**
     * Constructor for Parser
     * @param taskList
     */
    public Parser(TaskList taskList) {
        this.taskList = taskList;
        this.sc = new Scanner(System.in);
    }

    /**
     * Perform the user command and create the tasks
     * @throws EmptyDescriptionException If no description of task after the command word
     * @throws WrongCommandException If wrong command word is being entered
     */
    public String performCommand(String input) {
        //String input = this.sc.nextLine();
        while (!input.equals("bye")) {
            try {
                if (input.equals("list")) {
                    return taskList.printList();
                } else {
                    taskList.emptyDes(input);
                    String command = input.substring(0, input.indexOf(" "));
                    taskList.wrongCommand(command);
                    switch (command) {
                        case "mark":
                            return taskList.markTask(input.substring(5));
                            //break;

                        case "unmark":
                            return taskList.unmarkTask(input.substring(7));
                            //break;

                        case "todo":
                            Task taskToDo = new ToDo(input);
                            taskList.addTask(taskToDo);
                            return taskList.printAddComment(taskToDo);
                            //break;

                        case "deadline":
                            LocalDate deadline = LocalDate.parse(
                                    input.substring(input.indexOf("/") + 4));
                            Task taskDeadline = new Deadline(input, deadline);
                            taskList.addTask(taskDeadline);
                            return taskList.printAddComment(taskDeadline);
                            //break;

                        case "event":
                            LocalDate startDate = LocalDate.parse(
                                    input.substring(input.indexOf("/") + 6,
                                    input.lastIndexOf("/") - 1));
                            LocalDate endDate = LocalDate.parse(
                                    input.substring(input.lastIndexOf("/") + 4));
                            Task taskEvent = new Event(input, startDate, endDate);
                            taskList.addTask(taskEvent);
                            return taskList.printAddComment(taskEvent);
                            //break;

                        case "delete":
                            return taskList.deleteTask(input.substring(7));
                            //break;

                        case "find":
                            return taskList.find(input.substring(5));
                    }
                }
            } catch (EmptyDescriptionException | WrongCommandException e) {
                return e.getMessage();
            }

        input = sc.nextLine();
    }
        return "Bye. Hope to see you again soon!";


}




}




