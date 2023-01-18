// Tan Matthew Simon Castaneda
import java.util.*;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        //initialize scanner
        Scanner sc = new Scanner(System.in);
        //intro message from duke

        System.out.println("Whats good its duke\nwhat do you want from me");

        //to store all the tasks

        Task[] storedTask = new Task[100];

        //while not bye
        //might want to do switch case?
        int taskCounter = 0;
        int taskPointer;
        while(true) {
            String input = sc.nextLine();
            String[] command = input.split(" ");
            if (command[0].equals("bye")) {
                break;
            } else if (command[0].equals("list")) {
                for (int i = 0; i < storedTask.length; i++) {
                    if (storedTask[i] == null) {
                        break;
                    }
                    System.out.printf("%d." + storedTask[i].toString() + "\n", i+1);
                }
            } else if (command[0].equals("mark")) {
                taskPointer = Integer.parseInt(command[1]) - 1;
                storedTask[taskPointer].markAsDone();
            } else if (command[0].equals("unmark")) {
                taskPointer = Integer.parseInt(command[1]) - 1;
                storedTask[taskPointer].undoTask();
            } else if (command[0].equals("todo")) {
                storedTask[taskCounter] = new Todo(input);
                taskCounter++;
                System.out.println("Gotchu fam");
                System.out.println("I've added " + storedTask[taskCounter-1].toString() +
                        " to all the shit u need todo");
                System.out.printf("shag bro now u got %d tasks\n", taskCounter);
            } else if (command[0].equals("deadline")) {
                String[] newInput = input.split("/");
                storedTask[taskCounter] = new Deadline(newInput[0], newInput[1]);
                taskCounter++;
                System.out.println("Gotchu fam");
                System.out.println("I've added " + storedTask[taskCounter-1].toString() +
                        " to all the shit u need todo");
                System.out.printf("shag bro now u got %d tasks\n", taskCounter);
            } else if (command[0].equals("event")) {
                String[] newInput = input.split("/");
                storedTask[taskCounter] = new Event(newInput[0], newInput[1], newInput[2]);
                taskCounter++;
                System.out.println("Gotchu fam");
                System.out.println("I've added " + storedTask[taskCounter-1].toString() +
                        " to all the shit u need todo");
                System.out.printf("shag bro now u got %d tasks\n", taskCounter);
            } else {
                System.out.println("Invalid command wake up brother");
            }
        }

        //exit message from duke
        System.out.println("Thanks for using me brother");
    }
}
