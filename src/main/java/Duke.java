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
                System.out.println("Congrats bro you've done something with your life\n");
                System.out.println(storedTask[taskPointer].toString());
            } else if (command[0].equals("unmark")) {
                taskPointer = Integer.parseInt(command[1]) - 1;
                storedTask[taskPointer].undoTask();
                System.out.println("Stop being useless why u ask me to unmark\n");
                System.out.println(storedTask[taskPointer].toString());
            } else {
                storedTask[taskCounter] = new Task(input);
                taskCounter++;
                System.out.println(input);
            }
        }

        //exit message from duke
        System.out.println("Thanks for using me brother");
    }
}
