import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {

        // Duke's greeting
        String greet = "Hello! I'm Duke\nWhat can I do for you?";
        System.out.println(greet);

        // Take user input
        List<Task> taskList = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            String currentTask = sc.nextLine();

            // Command for bye
            if (currentTask.equals("bye")) {
                String goodbye = "Bye. Hope to see you again soon!";
                System.out.println(goodbye);
                break;
            }

            // Command for list
            else if (currentTask.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                int taskCount = 1;
                for (Task t: taskList) {
                    System.out.println(taskCount + "." + t);
                    taskCount++;
                }
            }

            // Command to mark as done
            else if (currentTask.equals("mark")) {
                System.out.println("Which task do you want to mark as done?");
                int taskToMark = Integer.parseInt(sc.nextLine());
                int taskCount = 1;

                for (Task t : taskList) {
                    if (taskToMark == taskCount) {
                        t.markAsDone();
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(t);
                        break;
                    } else {
                        taskCount++;
                    }
                }
            }

            // Command to unmark
            else if (currentTask.equals("unmark")) {
                System.out.println("Which task do you want to mark as not done?");
                int taskToMark = Integer.parseInt(sc.nextLine());
                int taskCount = 1;

                for (Task t : taskList) {
                    if (taskToMark == taskCount) {
                        t.markAsUndone();
                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println(t);
                        break;
                    } else {
                        taskCount++;
                    }
                }
            }

            // Command to remove task
            else if (currentTask.equals("remove")) {
                System.out.println("Which task do you want to remove?");
                int taskToRemove = Integer.parseInt(sc.nextLine());
                int taskCount = 1;

                for (Task t : taskList) {
                    if (taskToRemove - 1 == taskCount) {
                        System.out.println("Alright, removing task.");
                        taskList.remove(taskCount);
                        break;
                    } else {
                        taskCount++;
                    }
                }
            }

            // Command to add task
            else {
                System.out.println("added: " + currentTask);
                taskList.add(new Task(currentTask));
            }
        }
    }
}
