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

        switch(commands[0].toUpperCase()) {
            case "LIST":
                Task.listTasks();
                break;
            case "MARK":
                Task.markTasks(Integer.parseInt(commands[1])-1);
                break;
            case "UNMARK":
                Task.unmarkTasks(Integer.parseInt(commands[1])-1);
                break;
            default:
                String taskName = commands[1];
                for (int i = 2; i < commands.length; i++) {
                    taskName += " " + commands[i];
                }
                Task.addTask(commands[0], taskName);
        }
        System.out.println("\n----------------------------------\n");
    }

}
