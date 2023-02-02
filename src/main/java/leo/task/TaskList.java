package leo.task;

import java.io.Serializable;
import java.util.ArrayList;

import leo.parser.Parser;
import leo.ui.Ui;

/**
 * Represents a list of tasks.
 */
public class TaskList implements Serializable {

    private static final long serialVersionUID = 1L;

    private ArrayList<Task> tasks = new ArrayList<>();

    private void addTask(String[] parsedRequest) throws LeoTaskException {
        try {
            Task task = Task.createTask(parsedRequest);

            if (task == null) {
                throw new LeoTaskException("I'm sorry, I don't know what you want. ¿Que miras bobo?");
            }

            tasks.add(task);

            Ui.printDivider();
            System.out.printf("Alright, I've added: %s to your task list.\n", tasks.get(tasks.size() - 1));
            System.out.printf("You have %d tasks in your list, vamos, get moving!\n", tasks.size());
            Ui.printDivider();
        } catch (LeoTaskException e) {
            return;
        }
    }

    private void deleteTask(int taskId) throws LeoTaskException {
        if (tasks.isEmpty()) {
            Ui.printError(new EmptyDeletionException());
        }
        String taskDesc = tasks.remove(taskId).toString();
        System.out.printf("Alright! I've removed this from your list: %s\n", taskDesc);
        System.out.printf("You have %d tasks in your list, vamos, get moving!\n", tasks.size());
    }

    /**
     * Processes the request given by the user.
     * @param parsedRequest
     * @throws LeoTaskException
     */
    public void processRequest(String[] parsedRequest) throws LeoTaskException {
        try {
            if (!Task.commands.contains(parsedRequest[0])) {
                throw new InvalidCommandException();
            }

            else if (Task.descCommands.contains(parsedRequest[0]) && parsedRequest.length <= 1) {
                throw new EmptyFieldException();
            }

            switch (parsedRequest[0]) {
            case "list":
                Ui.printResponse("Here are your tasks, you legend!:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.printf("%d) %s\n", i + 1, tasks.get(i));
                }
                Ui.printDivider();
                break;
            case "mark":
                tasks.get(Parser.getTaskID(parsedRequest[1])).setDone();
                break;
            case "unmark":
                tasks.get(Parser.getTaskID(parsedRequest[1])).setNotDone();
                break;
            case "delete":
                deleteTask(Parser.getTaskID(parsedRequest[1]));
                break;
            default:
                addTask(parsedRequest);
                break;
            }
        } catch (LeoTaskException e) {
            Ui.printError(e);
        }
    }
}
