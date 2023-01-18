import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Duke {
    private static String[] arr = new String[100];
    private static int curr = 0;
    public static void main(String[] args) throws IOException {
        String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("---------------------------------- \n Hello from\n" + logo + "\n What can I do for you? \n ---------------------------------\n");

        awaitInput();
    }

    private static void awaitInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String userInput = br.readLine();
        while (!userInput.equalsIgnoreCase("BYE")) {   
            handleInput(userInput);
            userInput = br.readLine();
        }
        System.out.println("\n----------------------------------\n\n Bye! Hope to see you again!\n\n----------------------------------");
    }

    private static void handleInput(String userInput) {
        System.out.println("\n----------------------------------\n");
        if (userInput.equalsIgnoreCase("list")) {
            System.out.println("Here are the items in your list: \n");
            for (int i = 0; i < curr; i++) {
                System.out.println(i+1 + ") " + arr[i]);
            }
        } else {
            arr[curr] = userInput;
            curr++;
            System.out.println("added: " + userInput);
        }
        System.out.println("\n----------------------------------\n");
    }

}
