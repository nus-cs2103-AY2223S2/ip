import java.util.Scanner;

public class Duke {
    private static Task[] toDoList = new Task[100];
    private static int taskCount = 0;
    private static boolean startDuke = true;

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Scanner sc = new Scanner(System.in);
        
        

        while (startDuke) {

            String[] userInput = sc.nextLine().split(" ", 2);
            
            switch (userInput[0]) {
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    startDuke = false;
                    break;

                case "list":
                    if (taskCount == 0) {
                        System.out.println("You have no tasks");
                    }
                    else {
                        System.out.println("Here are the tasks in your list");
                        for (int i = 0; i < taskCount; i++) {
                            System.out.printf("%d. %s \n", i + 1, toDoList[i]);
                        }
                    }
                    break;
                
                case "mark":
                    markTask(Integer.valueOf(userInput[1]) - 1);
                    break;
                
                case "unmark":
                    unmarkTask(Integer.valueOf(userInput[1]) - 1);
                    break;

                case "todo":
                    System.out.println("Got it. I've added this task:");
                    addTaskToList("todo",userInput[1]);
                    break;

                case "deadline":
                    System.out.println("Got it. I've added this task:");
                    addTaskToList("deadline", userInput[1]);
                    break;

                case "event":
                    System.out.println("Got it. I've added this task:");
                    addTaskToList("event", userInput[1]);
                    break;

            }
        }
        
    }


    public static void addTaskToList(String type, String userInput) {
        switch (type) {
            case "todo":
                Task newToDo = new Todo(userInput);
                toDoList[taskCount++] = newToDo;
                System.out.println(newToDo);
                break;

            case "deadline":
                String[] deadlineFormatter = userInput.split(" /by ");
                Task newDeadLineTask = new Deadline(deadlineFormatter[0], deadlineFormatter[1]);
                toDoList[taskCount++] = newDeadLineTask;
                System.out.println(newDeadLineTask);
                break;

            case "event":
                String[] eventFormatter = userInput.split("/");
                Task newEventTask = new Event(eventFormatter[0], eventFormatter[1], eventFormatter[2]);
                toDoList[taskCount++] = newEventTask;
                System.out.println(newEventTask);
                break;

        }
        System.out.printf("Now you have %d tasks in the list.\n", taskCount);
    }

    public static void markTask(int taskID) {
        if (taskCount >= taskID - 1) {
            System.out.println("Nice! I've marked this task as done:");
            Task currentTask = toDoList[taskID];
            currentTask.mark();
            System.out.println(currentTask);
        } else {
            System.out.println("Invalid taskID entered!");
        }
    }

    public static void unmarkTask(int taskID) {
        if (taskCount >= taskID - 1) {
            System.out.println("OK, I've marked this task as not done yet:");
            Task currentTask = toDoList[taskID];
            currentTask.unmark();
            System.out.println(currentTask);
        } else {
            System.out.println("Invalid taskID entered!");
        }
    }

}
