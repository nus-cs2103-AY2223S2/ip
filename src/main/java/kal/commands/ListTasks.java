package kal.commands;

import kal.TaskList;

/**
 * This class represents the command to list all current tasks.
 */
public class ListTasks extends Command {
    private static final String RESPONSE_HEADER_FEW_TASKS = "Meow! I'm happy because you don't have many tasks~\n"
            + "Here they are:\n";
    private static final String RESPONSE_HEADER_MANY_TASKS = "SO many things to do! Gotta go go go!!!\n"
            + "Your tasks:\n";
    private static final int NUMEROUS_TASK_THRESHOLD = 8;
    private String response;

    /**
     * Constructs a new ListTasks object.
     *
     * @param message The full command message represented by the ListTasks object.
     */
    public ListTasks(String message) {
        super(message);
        this.response = ListTasks.RESPONSE_HEADER_FEW_TASKS;
    }

    @Override
    public void execute(TaskList toDoList) {
        if (toDoList.size() > ListTasks.NUMEROUS_TASK_THRESHOLD) {
            this.response = ListTasks.RESPONSE_HEADER_MANY_TASKS;
        }
        String res = "";
        for (int i = 0; i < toDoList.size(); i++) {
            res += String.format("%d.%s\n", i + 1, toDoList.get(i));
        }
        this.response += res;
    }

    @Override
    public String getResponseOutput() {
        return this.response;
    }
}
