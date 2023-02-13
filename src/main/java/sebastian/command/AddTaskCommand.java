package sebastian.command;

import sebastian.task.Task;

/**
 * Class used to handle a command to add a task
 */
public abstract class AddTaskCommand extends Command {

    /**
     * Response after a task is added to the task list
     * @param task the task being added
     * @return notice that the task has been added
     */
    public String addTask(Task task, int totalTasks) {
        return "As you command. I've added this task:" + "\n"
                + task + "\n"
                + "Now you have a total of " + totalTasks + " tasks on the list";
    }







}
