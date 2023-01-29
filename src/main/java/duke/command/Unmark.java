package duke.command;

import duke.task.TaskList;

/**
 * This class marks a particular task in the task list as not done.
 */
public class Unmark extends Commands {
    public Unmark(String str) {
        this.setCommandStorage(str);
    }

    /**
     * This function takes in a task list, and mark a specified task in the task list as not done.
     * Then it increments the task count.
     */
    @Override
    public void execute(TaskList tasks) {
        String content = this.getCommandStorage();
        int taskNumber = Character.getNumericValue(content.charAt(content.length() - 1));
        taskNumber -= 1;
        tasks.markTask(taskNumber, false);
        System.out.println("OK, I've marked this task as not done yet:\n" + "[" + tasks.getTaskIcon(taskNumber)
                + "] " + tasks.getTaskContent(taskNumber));
    }
}