import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws DukeException {

        //Always greets user when Duke is run
        String greeting = "Hello! I'm Duke! \n What can I do for you? \n";
        System.out.println(greeting);

        //placeholder array for task list
        Task[] taskArray = new Task[100];

        //Use scanner to read input
        Scanner scanner = new Scanner(System.in);

        int arrayIndex = 0; //keep track of tail end of array

        String keyWord = scanner.next();

        //6 cases: list, mark, unmark, todo, deadline, event
        //while loop checks for user exit input("bye")
        while (!keyWord.equals("bye")) {
            int taskIndex = 0;
            Task currentTask;
            StringBuilder taskDescription;
            String tempScanner;
            switch (keyWord) {
            case "list":
                System.out.println("\nHere are your tasks:\n");
                int taskCount = 1;
                //iterate through taskArray to print out list
                for (Task t : taskArray) {
                    if (t == null) {
                        break;
                    } else {
                        System.out.println(String.valueOf(taskCount) + ". " + t.toString() + "\n");
                        taskCount++;
                    }
                }
                if (taskCount == 1) {
                    System.out.println("You have no task in the list.");
                }
                keyWord = scanner.next();
                break;
            case "mark":
                try {
                    taskIndex = scanner.nextInt() - 1;
                    currentTask = taskArray[taskIndex];
                    currentTask.markAsDone();
                    System.out.println("\n____________________________________________________________");
                    System.out.println("\nNice! I've marked this task as done:\n");
                    System.out.println(currentTask.toString());
                    System.out.println("\n____________________________________________________________\n");
                    keyWord = scanner.next();
                    break;
                } catch (NoSuchElementException e) {
                    System.out.println("Oops! Mark must be followed by an integer.");
                }
            case "unmark":
                try {
                    taskIndex = scanner.nextInt() - 1;
                    currentTask = taskArray[taskIndex];
                    currentTask.markAsUndone();
                    System.out.println("\n____________________________________________________________");
                    System.out.println("\nOK, I've marked this task as not done yet:\n");
                    System.out.println(currentTask.toString());
                    System.out.println("\n____________________________________________________________\n");
                    keyWord = scanner.next();
                    break;
                } catch (NoSuchElementException e) {
                    System.out.println("Oops! Unmark must be followed by an integer.");
                }
            case "todo": // ToDo tasks have no date/time attached
                tempScanner = scanner.nextLine();
                try {
                    if (tempScanner.isEmpty() || tempScanner.equals(" ")) {
                        throw new DukeException("The description of a todo cannot be empty.");
                    } else {
                        taskDescription = new StringBuilder(tempScanner);
                        currentTask = new ToDo(taskDescription.toString());
                        taskArray[arrayIndex] = currentTask;
                        arrayIndex++;
                        addTaskReply(currentTask, arrayIndex);
                        keyWord = scanner.next();
                        break;
                    }
                } catch (DukeException e) {
                    System.out.println(e.toString());
                    keyWord = scanner.next();
                    break;
                }
            case "deadline": // Deadline tasks have one date/time
                tempScanner = scanner.nextLine();
                try {
                    if (tempScanner.isEmpty() || tempScanner.equals(" ")) {
                        throw new DukeException("The description of a deadline cannot be empty.");
                    }
                    taskDescription = new StringBuilder(tempScanner);
                    tempScanner = scanner.next();

                    //Using while loop, append scanner input until /by to taskDescription
                    while (!tempScanner.equals("/by")) {
                        taskDescription.append(" ").append(tempScanner);
                        tempScanner = scanner.next();
                    }

                    //Everything after /by is the deadline
                    String by = scanner.nextLine();
                    currentTask = new Deadline(taskDescription.toString(),by);
                    taskArray[arrayIndex] = currentTask;
                    arrayIndex++;
                    addTaskReply(currentTask, arrayIndex);
                    keyWord = scanner.next();
                    break;
                } catch (DukeException e) {
                    System.out.println(e.toString());
                    keyWord = scanner.next();
                    break;
                }
            case "event": // Event tasks have two date/times: from and to
                taskDescription = new StringBuilder();
                tempScanner = scanner.next();
                StringBuilder from = new StringBuilder();

                //Using while loop, append scanner input until /from to taskDescription
                while (!tempScanner.equals("/from")) {
                    taskDescription.append(" ").append(tempScanner);
                    tempScanner = scanner.next();
                }

                //Ignore /from
                tempScanner = scanner.next();

                //Using while loop, append scanner input until '/to' to from
                while (!tempScanner.equals("/to")) {
                    from.append(" ").append(tempScanner);
                    tempScanner = scanner.next();
                }

                //Everything after /to is the end timing of event
                String to = scanner.nextLine();
                currentTask = new Event(taskDescription.toString(), from.toString(), to);
                taskArray[arrayIndex] = currentTask;
                arrayIndex++;
                addTaskReply(currentTask, arrayIndex);
                keyWord = scanner.next();
                break;
            default:
                //if user input does not match any case at all
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means.");
                keyWord = scanner.next();
            }
        }

        //Exit after user inputs "bye"
        System.out.println("\nBye. Hope to see you again soon!\n");
    }

    //helper method that prints the reply to each added task
    public static void addTaskReply(Task currentTask, int arrayIndex) {
        System.out.println("\n____________________________________________________________");
        System.out.println("\nGot it. I've added this task: " + currentTask.toString());
        System.out.println("\nNow you have " + String.valueOf(arrayIndex) + " tasks in the list.");
        System.out.println("\n____________________________________________________________\n");
    }

}