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

        String keyWord = scanner.next();

        //4 cases: list, mark, unmark, add task(default)
        //while loop checks for user exit input("bye")
        while (!keyWord.equals("bye")) {
            int taskIndex;
            Task currentTask;
            switch (keyWord) {
            case "list":
                int taskCount = 1;
                //iterate through taskarray to print out list
                for (Task t : taskArray) {
                    if (t == null) {
                        break;
                    } else {
                        System.out.println(String.valueOf(taskCount) + ". " + t.toString() + "\n");
                        taskCount++;
                    }
                }
                keyWord = scanner.next();
                break;
            case "mark":
                taskIndex = Integer.parseInt(scanner.next()) - 1;
                currentTask = taskArray[taskIndex];
                currentTask.markAsDone();
                System.out.println("\nNice! I've marked this task as done:\n");
                System.out.println(currentTask.toString() + "\n");
                keyWord = scanner.next();
                break;
            case "unmark":
                taskIndex = Integer.parseInt(scanner.next()) - 1;
                currentTask = taskArray[taskIndex];
                currentTask.markAsUndone();
                System.out.println("\nOK, I've marked this task as not done yet:\n");
                System.out.println(currentTask.toString() + "\n");
                keyWord = scanner.next();
                break;
            default:
                //no keyword so append rest of the line to keyword and add as new task
                String userTask = keyWord + scanner.nextLine();
                currentTask = new Task(userTask);
                taskArray[arrayIndex] = currentTask;
                arrayIndex++;
                System.out.println("\nadded: " + currentTask.toString() + "\n");
                keyWord = scanner.next();
            }
        }

        //Exit
        System.out.println("\nBye. Hope to see you again soon!\n");
    }
}