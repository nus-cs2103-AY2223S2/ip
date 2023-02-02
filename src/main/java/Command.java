package main.java;

import java.util.ArrayList;

public class Command {
    public enum CommandType {
        EXIT,
        LIST,
        MARK,
        UNMARK,
        ADD_TASK,
        DELETE_TASK,
        INVALID_COMMAND
    }
    private static final String ADD_TASK_OUTPUT = "Got it. I've added this task:\n\t%s\nNow you have %d tasks in the list.";
    private static final String DELETE_TASK_OUTPUT = "Noted. I've removed this task:\n\t%s\nNow you have %d tasks in the list.";
    private CommandType commandType;
    private String param;

    public Command(CommandType commandType) {
        this.commandType = commandType;
    }

    public Command(CommandType commandType, String param) {
        this.commandType = commandType;
        this.param = param;
    }

    public CommandType getCommandType() {
        return this.commandType;
    }

    public String getParam() {
        return this.param;
    }

    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException {
        String output = "";

        switch (this.commandType) {
            case EXIT:
                ui.exit();
                break;
            case LIST:
                output = "Here are the tasks in your list:";
                for (int i = 0; i < taskList.size(); ++i) {
                    output += String.format("\n%d.%s", i + 1, taskList.get(i).toString());
                }
                break;
            case MARK:
                int index = Integer.parseInt(param);
                Task task = taskList.markTask(index);
                output = "Nice! I've marked this task as done:\n" + task.toString();
                storage.save(taskList);
                break;
            case UNMARK:
                index = Integer.parseInt(param);
                task = taskList.unmarkTask(index);
                output = "Nice! I've marked this task as done:\n" + task.toString();
                storage.save(taskList);
                break;
            case DELETE_TASK:
                index = Integer.parseInt(param);
                task = taskList.deleteTask(index);
                output = String.format(DELETE_TASK_OUTPUT, task.toString(), taskList.size());
                storage.save(taskList);
                break;
            case ADD_TASK:
                task = Task.parseTaskFromInput(param);
                taskList.addTask(task);
                output = String.format(ADD_TASK_OUTPUT, task.toString(), taskList.size());
                storage.save(taskList);
                break;
            case INVALID_COMMAND:
                throw new DukeException(ERROR.INVALID_INPUT.getMessage());
        }

        ui.printFormattedOutput(output);
    }
}
