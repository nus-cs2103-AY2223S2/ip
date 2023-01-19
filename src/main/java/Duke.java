// Tan Matthew Simon Castaneda
import java.util.*;
public class Duke {
    public static void main(String[] args) {

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
            //writen with the help of ChatGPT
            try {
                // check for invalid command
                if (!(command[0].equals("list") || command[0].equals("mark") || command[0].equals("unmark")
                        || command[0].equals("todo") || command[0].equals("bye")
                        || command[0].equals("deadline") || command[0].equals("event")))
                {
                    throw new DukeException("Invalid command wake up brother");
                }
                // rest of the code for processing valid commands
            } catch (DukeException e) {
                System.out.println(e.getMessage());
                continue;
            }
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
                try {
                    storedTask[taskPointer].markAsDone();
                } catch (NullPointerException e) {
                    System.out.println("Wake up and choose a better task to mark");
                    continue;
                }
            } else if (command[0].equals("unmark")) {
                taskPointer = Integer.parseInt(command[1]) - 1;
                try {
                    storedTask[taskPointer].undoTask();
                } catch (NullPointerException e ) {
                    System.out.println("Wake up and choose a better task to unmark brother");
                    continue;
                }
            } else if (command[0].equals("todo")) {
                String[] newInput = input.split("todo ");
                storedTask[taskCounter] = new Todo(newInput[1]);
                taskCounter++;
                System.out.println("Gotchu fam");
                System.out.printf("I've added\n" + storedTask[taskCounter-1].toString() +
                        "\nto all the shit u need to do\n");
                System.out.printf("shag bro now u got %d tasks\n", taskCounter);
            } else if (command[0].equals("deadline")) {
                String[] newInput = input.split("/");
                storedTask[taskCounter] = new Deadline(newInput[0], newInput[1]);
                taskCounter++;
                System.out.println("Gotchu fam");
                System.out.printf("I've added\n" + storedTask[taskCounter-1].toString() +
                        "\nto all the shit u need to do\n");
                System.out.printf("shag bro now u got %d tasks\n", taskCounter);
            } else if (command[0].equals("event")) {
                String[] newInput = input.split("/");
                storedTask[taskCounter] = new Event(newInput[0], newInput[1], newInput[2]);
                taskCounter++;
                System.out.println("Gotchu fam");
                System.out.printf("I've added\n" + storedTask[taskCounter-1].toString() +
                        "\nto all the shit u need to do\n");
                System.out.printf("shag bro now u got %d tasks\n", taskCounter);
            } else {
                System.out.println("Invalid command wake up brother");
            }
        }

        //exit message from duke
        System.out.println("Thanks for using me brother");
    }
}
