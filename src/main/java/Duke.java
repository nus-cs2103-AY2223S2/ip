import java.io.InputStreamReader;
import java.io.*;
import java.util.*;


public class Duke {
    public static void main(String[] args) throws IOException{
        // String logo = " ____        _        \n"
        //         + "|  _ \\ _   _| | _____ \n"
        //         + "| | | | | | | |/ / _ \\\n"
        //         + "| |_| | |_| |   <  __/\n"
        //         + "|____/ \\__,_|_|\\_\\___|\n";
        // System.out.println("Hello from\n" + logo);
        greeting();
        BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
        String userInput = bReader.readLine();
        while (!userInput.equals("bye")) {
            System.out.println(userInput);
            userInput = bReader.readLine();
        }
        System.out.println("Bye! Hope to see you soon again!");
    }

    public static void greeting() {
        System.out.println("Hello, I am Duke!\nWhat can I do for you?");
    }


}
