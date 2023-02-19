package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.task.Priority;
import duke.task.Task;

/**
 * Sets and queries the priority of a task.
 */
public class PriorityCommand extends Command {

    private String commandType;
    /**
     * Constructor for Priority
     *
     * @param input The user input.
     */
    public PriorityCommand(String input) {
        super(input);
        this.commandType = input.substring(1, 4);
        assert commandType.equals("get") || commandType.equals("set");
    }

    /**
     * Gets or sets the Priority of a given task.
     *
     * @param tasks The current Task List.
     * @return The current Task List.
     */
    @Override
    public TaskList execute(TaskList tasks) {
        switch(commandType) {
            case "get":
                int taskIndex = Integer.parseInt(input.substring(5));
                Task task = tasks.get(taskIndex);
                Ui.getPriorityMessage(task);
                break;
            case "set":
                taskIndex = Integer.parseInt(input.substring(7)) - 1;
                task = tasks.get(taskIndex);
                Priority priority = Priority.priorityValue(input.charAt(5));
                task.setPriority(priority);
                tasks.remove(taskIndex);
                tasks.add(taskIndex, task);
                Ui.setPriorityMessage(task);
        }
        return tasks;
    }
}
