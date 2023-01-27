// Tan Matthew Simon Castaneda
import java.util.*;
public class Duke {
    public static void main(String[] args) {

        //initialize scanner
        Scanner sc = new Scanner(System.in);
        TaskList taskList = new TaskList(100);


        //intro message from duke

        System.out.println("Whats good its CHADGpt\nwhat do you want from me");





        while(true) {
            String input = sc.nextLine();
            String[] command = input.split(" ");

            if (input.equals("bye")) {
                break;
            } else {
                CommandManager.run(input, command, taskList);
            }

        }

        System.out.println("Thanks for using me brother");
    }
}
