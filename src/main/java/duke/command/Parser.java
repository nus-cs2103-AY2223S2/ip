package duke.command;
import duke.exceptions.EmptyDescriptionException;
import duke.exceptions.TaskDoesNotExistException;
import duke.exceptions.InvalidPriorityException;
import duke.task.TaskList;

public class Parser {
    public TaskList taskList;
    String input;

    public Parser(TaskList taskList) {
        this.taskList = taskList;
    }
    public String getDukeResponse(String input) {

            try {
                //print List
                if (input.equals("list")) {
                    return taskList.getList();
                } else {
                    //split into 2, first part is duke.task type, second part is instruction
                    String[] parts = input.split(" ", 2);
                    String command = parts[0];

                    switch (command) {
                        case "mark":
                            return taskList.markTask(parts);
                        case "unmark":
                            return taskList.unmarkTask(parts);
                        case "delete":
                            return taskList.deleteTask(parts);
                        case "find":
                            return taskList.find(parts);
                        case "priority":
                            return taskList.changePriority(parts);
                        case "todo":
                        case "deadline":
                        case "event":
                            return taskList.createTask(parts);
                        default:
                            assert false : "Command does not make sense";
                            return "I'm sorry but I'm afraid I do not understand what you mean";
                    }
                }
            } catch (TaskDoesNotExistException e) {
                String taskStr = e.getMessage();
                return taskStr;
            } catch (InvalidPriorityException e) {
              String priorityStr = e.getMessage();
              return priorityStr;
            } catch (EmptyDescriptionException e) {
                String descStr = e.getMessage();
                return descStr;
            }

    }
}
