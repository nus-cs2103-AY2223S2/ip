import java.io.InputStreamReader;
import java.io.*;
import java.util.*;


public class Duke {

    static List<String> storedText = new ArrayList<String>();
    public static void main(String[] args) throws IOException{
        // String logo = " ____        _        \n"
        //         + "|  _ \\ _   _| | _____ \n"
        //         + "| | | | | | | |/ / _ \\\n"
        //         + "| |_| | |_| |   <  __/\n"
        //         + "|____/ \\__,_|_|\\_\\___|\n";
        // System.out.println("Hello from\n" + logo);
        greeting();
        BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
        processUserInput(bReader);
        System.out.println("Bye! Hope to see you soon again!");
    }

    public static void greeting() {
        System.out.println("Hello, I am Duke!\nWhat can I do for you?");
    }

    public static void processUserInput(BufferedReader brToUse) throws IOException{
        String userInput = brToUse.readLine();
        while (!userInput.equals("bye")) {
            // System.out.println(userInput);
            if (userInput.equals("list")) {
                for (int i = 0; i < storedText.size(); i++) {
                    Integer currIndex = i + 1;
                    String toUse = currIndex.toString() + ". " + storedText.get(i);
                    System.out.println(toUse);
                }
                userInput = brToUse.readLine();
                continue;
            }
            storedText.add(userInput);
            String toOutput = "added: " + userInput;
            System.out.println(toOutput);
            userInput = brToUse.readLine();
        }
    }




}
