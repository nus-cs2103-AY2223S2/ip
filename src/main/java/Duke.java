// Tan Matthew Simon Castaneda
import java.util.*;
import java.io.*;
import java.nio.file.*;
public class Duke {

    public static void main(String[] args) {

        //initialize scanner
        Scanner sc = new Scanner(System.in);


        Storage storage = new Storage("./duke.txt");
        TaskList taskList = storage.loadTask();





        //intro message from duke

        System.out.println("Whats good its CHADGpt\nwhat do you want from me");


        while(true) {
            String input = sc.nextLine();
            String[] command = input.split(" ");

            if (input.equals("bye")) {
                storage.saveTask(taskList);
                break;
            } else {
                CommandManager.run(input, command, taskList);
            }
        }

        System.out.println("Thanks for using me brother");
    }
}
