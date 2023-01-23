package sebastian.command;

import sebastian.task.Task;


abstract public class AddTaskCommand extends Command{
    /**
     * Response after a task is added to the task list
     * @param task the task being added
     * @return notice that the task has been added
     */
    public String addTask(Task task, int totalTasks){
        return  "Noted. I've added this task:" + "\n" +
                task + "\n" +
                "Now you have " + totalTasks + " tasks in the list";
    }







}
