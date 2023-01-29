package duke.command;
import duke.exceptions.EmptyDescriptionException;
import duke.exceptions.TaskDoesNotExistException;
import duke.task.TaskList;

import java.util.Scanner;


public class Parser {
    public TaskList taskList;
    public Scanner scanner;
    String input;


    public Parser(TaskList taskList) {
        this.taskList = taskList;
        this.scanner = new Scanner(System.in);
    }
    public void getTaskType() {

        input = scanner.nextLine();
        while (!input.equals("bye")) {
            try {
                //print List
                if (input.equals("list")) {
                    taskList.getList();
                    input = scanner.nextLine();
                } else {
                    //split into 2, first part is duke.task type, second part is instruction
                    String[] parts = input.split(" ", 2);
                    String taskType = parts[0];

                    switch (taskType) {
                        case "mark":
                            taskList.markTask(parts);
                            break;
                        case "unmark":
                            taskList.unmarkTask(parts);
                            break;
                        case "delete":
                            taskList.deleteTask(parts);
                            break;
                        case "todo":
                        case "deadline":
                        case "event":
                            taskList.createTask(parts);
                            break;
                    }
                }
            } catch (TaskDoesNotExistException e) {
                System.out.println(e.getMessage());
            } catch (EmptyDescriptionException e) {
                System.out.println(e.getMessage());
            }
            input = scanner.nextLine();
        }


        System.out.println("Bye. Hope to see you again soon!");
    }
}
