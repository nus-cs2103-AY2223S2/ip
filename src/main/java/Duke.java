import java.util.Scanner;

public class Duke {
    private static Task[] taskList = new Task[100];
    private static int taskCount = 0;

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Scanner sc = new Scanner(System.in);
        while (true) {
            String userInput = sc.nextLine();
            if (userInput.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } 
            
            else if (userInput.equals("list")) {
                if (taskCount == 0) {
                    System.out.println("You have no tasks");
                }
                else {
                    System.out.println("Here are the tasks in your list");
                    for (int i = 0; i < taskCount; i++) {
                        System.out.printf("%d. %s \n", i + 1, taskList[i]);
                    }
                }
            } 
            
            else if (userInput.split(" ")[0].equals("mark")) {
                markTask(Integer.valueOf(userInput.split(" ")[1]) - 1);
            } 
            
            else if (userInput.split(" ")[0].equals("unmark")) {
                unmarkTask(Integer.valueOf(userInput.split(" ")[1]) - 1);
            }
            
            
            else {
                addTaskToList(userInput);
            }
        }
    }


    public static void addTaskToList(String userInput) {
        System.out.printf("added: %s\n", userInput);
        taskList[taskCount++] = new Task(userInput);
    }

    public static void markTask(int taskID) {
        if (taskCount >= taskID - 1) {
            System.out.println("Nice! I've marked this task as done:");
            Task currentTask = taskList[taskID];
            currentTask.mark();
            System.out.println(currentTask);
        } else {
            System.out.println("Invalid taskID entered!");
        }
    }

    public static void unmarkTask(int taskID) {
        if (taskCount >= taskID - 1) {
            System.out.println("OK, I've marked this task as not done yet:");
            Task currentTask = taskList[taskID];
            currentTask.unmark();
            System.out.println(currentTask);
        } else {
            System.out.println("Invalid taskID entered!");
        }
    }

}
