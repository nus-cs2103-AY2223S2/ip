import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        //Always greets user when Duke is run
        String greeting = "Hello! I'm Duke! \n What can I do for you? \n";
        System.out.println(greeting);

        //placeholder array for task list
        Task[] taskArray = new Task[100];

        //Use scanner to read input
        Scanner scanner = new Scanner(System.in);

        int arrayIndex = 0; //keep track of tail end of array

        while (scanner.hasNext()) {
            String userInput = scanner.nextLine();
            if (userInput.equals("bye")) {
                break;
            } else if (userInput.equals("list")) {
                int taskCount = 1;
                for (Task t : taskArray) {
                    if (t == null) {
                        break;
                    } else {
                        System.out.println(String.valueOf(taskCount) + ". " + t.toString() + "\n");
                        taskCount++;
                    }
                }
            } else {
                Task currentTask = new Task(userInput);
                taskArray[arrayIndex] = currentTask;
                arrayIndex++;
                System.out.println("\nadded: " + currentTask.toString() + "\n");
            }
        }

        //Exit
        System.out.println("\nBye. Hope to see you again soon!\n");
    }
}