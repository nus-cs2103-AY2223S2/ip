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
    private ArrayList<Task> foundTasks;
    private ArrayList<Integer> foundTaskIndices;

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

    private void findTask(String keyword) {
        foundTasks = new ArrayList<>();
        foundTaskIndices = new ArrayList<>();
        for (Task task : tasks) {
            if (task.toString().contains(keyword)) {
                foundTasks.add(task);
                foundTaskIndices.add(tasks.indexOf(task) + 1);
            }
        }
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
            case "find":
                findTask(parsedRequest[1]);
                if (foundTasks.isEmpty()) {
                    Ui.printResponse("You've been caught offside my friend, no tasks found!");
                    break;
                }
                Ui.printResponse("Here are the matching tasks in your list:");
                for (int i = 0; i < foundTasks.size(); i++) {
                    System.out.printf("%d) %s\n", foundTaskIndices.get(i), foundTasks.get(i));
                }
                Ui.printResponse("To perform any action on these tasks, use the stated indices.");
                Ui.printDivider();
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
