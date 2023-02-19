package duke.command;
import duke.exceptions.EmptyDescriptionException;
import duke.exceptions.TaskDoesNotExistException;
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
                    String taskType = parts[0];

                    switch (taskType) {
                        case "mark":
                            return taskList.markTask(parts);
                        case "unmark":
                            return taskList.unmarkTask(parts);
                        case "delete":
                            return taskList.deleteTask(parts);
                        case "find":
                            return taskList.find(parts);
                        case "todo":
                        case "deadline":
                        case "event":
                            return taskList.createTask(parts);
                        default:
                            assert false : "Command does not make sense";
                            return "Sorry i'm not sure what you mean...";
                    }
                }
            } catch (TaskDoesNotExistException e) {
                String str1 = e.getMessage();
                return str1;
            } catch (EmptyDescriptionException e) {
                String str2 = e.getMessage();
                return str2;
            }

    }
}
