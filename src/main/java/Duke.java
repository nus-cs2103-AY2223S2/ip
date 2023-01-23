// Tan Matthew Simon Castaneda
import java.util.*;
public class Duke {
    public static void main(String[] args) {

        //initialize scanner
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> storedTask = new ArrayList<>(100);


        //intro message from duke

        System.out.println("Whats good its duke\nwhat do you want from me");


        int taskCounter = 0;

        while(true) {
            String input = sc.nextLine();
            String[] command = input.split(" ");
            if (input.equals("bye")) {
                break;
            } else {
                CommandManager.run(input, command, storedTask, taskCounter);
            }

        }

        System.out.println("Thanks for using me brother");
    }
}
