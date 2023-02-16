package boo.command;

import boo.task.Task;
import boo.tasklist.TaskList;

/**
 * Represents a find command that is entered by the user to find tasks related to a key phrase.
 */
public class FindCommand extends Command {
    /** Key phrase with this find command. */
    private String keyPhrase;

    /** Task list containing all the tasks. */
    private TaskList tasks;


    /**
     * Constructs a {@code FindCommand}.
     *
     * @param keyPhrase The phrase to look up in task names.
     * @param tasks The lists of all available tasks.
     */
    public FindCommand(String keyPhrase, TaskList tasks) {
        super();
        this.keyPhrase = keyPhrase;
        this.tasks = tasks;
    }

    /**
     * Finds tasks whose names contain the words mentioned in the key phrase.
     *
     * @return tasks whose names contain the words mentioned in the key phrase
     */
    @Override
    public String runCommand() {
        StringBuilder sb = new StringBuilder();
        sb.append("Tasks whose name contains \"");
        sb.append(keyPhrase);
        sb.append("\":\n\n");

        //Iterate through each task in the task list
        int count = 1;
        for (int i = 0; i < tasks.getSizeOfTaskList(); i = i + 1) {
            Task currentTask = tasks.getTask(i);
            String taskName = currentTask.getNameOfTask().toLowerCase();
            if (taskName.contains(keyPhrase.toLowerCase())) {
                sb.append(count + ". " + currentTask.getStatusOfTaskInString() + "\n");
                count += 1;
            }
        }

        //No result
        if (count == 1) {
            sb.append("There is no such task.");
        }
        return sb.toString();
    }
}
