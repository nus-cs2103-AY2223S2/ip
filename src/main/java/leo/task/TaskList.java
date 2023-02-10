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
            tasks.add(task);
            Ui.printDivider();
            System.out.printf("Alright, I've added: %s to your task list.\n", tasks.get(tasks.size() - 1));
            System.out.printf("You have %d tasks in your list, vamos, get moving!\n", tasks.size());
            Ui.printDivider();
        } catch (LeoTaskException e) {
            Ui.printError(e);
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

    private String addTaskGUI(String[] parsedRequest) throws LeoTaskException {
        try {
            Task task = Task.createTask(parsedRequest);
            tasks.add(task);
            return String.format("Alright, I've added: %s to your task list.\nYou have %d tasks in your list, vamos, get moving!\n", tasks.get(tasks.size() - 1), tasks.size());
        } catch (MissingDeadlineException e) {
            return e.getMessage();
        } catch (MissingTimelineException e) {
            return e.getMessage();
        } catch (EmptyFieldException e) {
            return e.getMessage();
        } catch (LeoTaskException e) {
            return e.getMessage();
        }
    }

    private String deleteTaskGUI(int taskId) throws LeoTaskException {
        if (tasks.isEmpty()) {
            Ui.printError(new EmptyDeletionException());
        }
        String taskDesc = tasks.remove(taskId).toString();
        return String.format("Alright! I've removed this from your list: %s\nYou have %d tasks in your list, vamos, get moving!\n", taskDesc, tasks.size());
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
            case "help":
                Ui.printHelp();
                break;
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

    public String processRequestGUI(String[] parsedRequest){
        String response = "";
        try {
            if (!Task.commands.contains(parsedRequest[0])) {
                throw new InvalidCommandException();
            }
            else if (Task.descCommands.contains(parsedRequest[0]) && parsedRequest.length <= 1) {
                throw new EmptyFieldException();
            }
            switch (parsedRequest[0]) {
                case "help":
                    response = Ui.getHelp();
                    break;
                case "bye":
                    response = "It was nice talking, see you soon!\n";
                    break;
                case "list":
                    response = "Here are your tasks, you legend!:\n";
                    for (int i = 0; i < tasks.size(); i++) {
                        response += String.format("%d) %s\n", i + 1, tasks.get(i));
                    }
                    break;
                case "mark":
                    tasks.get(Parser.getTaskID(parsedRequest[1])).setDone();
                    response = String.format("Well done on completing the task! Let me mark that as done! Campeon del mundo!\n%s\n", tasks.get(Parser.getTaskID(parsedRequest[1])));
                    break;
                case "unmark":
                    tasks.get(Parser.getTaskID(parsedRequest[1])).setNotDone();
                    response = String.format("Ok, I've marked that as not done! Please get to it :(\n%s\n", tasks.get(Parser.getTaskID(parsedRequest[1])));
                    break;
                case "delete":
                    response = deleteTaskGUI(Parser.getTaskID(parsedRequest[1]));
                    break;
                case "find":
                    findTask(parsedRequest[1]);
                    if (foundTasks.isEmpty()) {
                        response = "You've been caught offside my friend, no tasks found!";
                        break;
                    }
                    response = "Here are the matching tasks in your list:\n";
                    for (int i = 0; i < foundTasks.size(); i++) {
                        response += String.format("%d) %s\n", foundTaskIndices.get(i), foundTasks.get(i));
                    }
                    response += "To perform any action on these tasks, use the stated indices.";
                    break;
                default:
                    response = addTaskGUI(parsedRequest);
                    break;
            }
        } catch (LeoTaskException e) {
            response = e.getMessage();
        }
        return response;
    }
}
