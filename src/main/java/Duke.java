import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Duke {
    
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
        
        String[] commands = userInput.split(" ");

        if (userInput.equalsIgnoreCase("list")) {
            Task.listTasks();
        } else if (commands[0].equalsIgnoreCase("mark")) {
            Task.markTasks(Integer.parseInt(commands[1])-1);
        } else if (commands[0].equalsIgnoreCase("unmark")) {
            Task.unmarkTasks(Integer.parseInt(commands[1])-1);
        } else {
            Task.addTask(userInput);
        }

        System.out.println("\n----------------------------------\n");
    }

}
