package leo.task;

import java.io.Serializable;
import java.util.ArrayList;
public class TaskList implements Serializable {

    private ArrayList<Task> tasks = new ArrayList<>();

    private void addTask(String taskString) throws LeoTaskException {
        try {
            Task task = Task.createTask(taskString);

            if (task == null) {
                throw new LeoTaskException("I'm sorry, I don't know what you want. ¿Que miras bobo?\n");
            }

            tasks.add(task);
            System.out.printf("Alright, I've added: %s to your task list.\n", tasks.get(tasks.size() - 1));
            System.out.printf("You have %d tasks in your list, vamos, get moving!\n", tasks.size());
        } catch (LeoTaskException e) {
            e.printStackTrace();
            System.out.println();
        }
    }

    private void deleteTask(int taskId) throws LeoTaskException {
        try {
            if (tasks.isEmpty()) {
                throw new EmptyDeletionException();
            }
            String taskDesc = tasks.remove(taskId - 1).toString();
            System.out.printf("Alright! I've removed this from your list: %s\n", taskDesc);
            System.out.printf("You have %d tasks in your list, vamos, get moving!\n", tasks.size());
        } catch (LeoTaskException e) {
            e.printStackTrace();
            System.out.println();
        }
    }

    public void processRequest(String request) throws LeoTaskException {

        try {
            for (int i = 0; i < 25; i++) {
                System.out.print("*");
            }
            System.out.println();

            String[] rqDetails = request.split(" ", 2);
            if (!Task.commands.contains(rqDetails[0])) {
                throw new InvalidCommandException();
            }

            else if (Task.descCommands.contains(rqDetails[0]) && rqDetails.length <= 1) {
                throw new EmptyFieldException();
            }

            switch (rqDetails[0]) {
                case "bye":
                    System.out.println("It was nice talking, see you soon!");
                    break;
                case "list":
                    System.out.println("Here are your tasks you legend: ");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.printf("%d) %s\n", i + 1, tasks.get(i));
                    }
                    break;
                case "mark":
                    tasks.get(Integer.parseInt(rqDetails[1]) - 1).setDone();
                    break;
                case "unmark":
                    tasks.get(Integer.parseInt(rqDetails[1]) - 1).resetDone();
                    break;
                case "delete":
                    deleteTask(Integer.parseInt(rqDetails[1]));
                    break;
                default:
                    addTask(request);
                    break;
            }

            for (int i = 0; i < 25; i++) {
                System.out.print("*");
            }
            System.out.println();
        } catch (LeoTaskException e) {
            e.printStackTrace();
            System.out.println();
        }
    }

}
