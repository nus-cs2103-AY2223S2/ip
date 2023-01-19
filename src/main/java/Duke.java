import java.io.*;

import tasks.*;

public class Duke {
    
    public static void main(String[] args) throws Exception {
        String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("---------------------------------- \n Hello from\n" + logo + "\n What can I do for you? \n ---------------------------------\n");

        awaitInput();
    }

    private static void awaitInput() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String userInput = br.readLine();
        while (!userInput.equalsIgnoreCase("BYE")) {   
            handleInput(userInput);
            userInput = br.readLine();
        }
        System.out.println("\n----------------------------------\n\n Bye! Hope to see you again!\n\n----------------------------------");
    }

    enum Job {
        LIST,
        MARK,
        UNMARK,
        TODO,
        DEADLINE,
        EVENT,
        DELETE,
        INVALID
    }

    private static void handleInput(String userInput) {
        System.out.println("\n----------------------------------\n");
        
        String[] commands = userInput.split(" ");

        String j = commands[0].toUpperCase();
        Job job;

        try {
            job = Job.valueOf(j);
        } catch (IllegalArgumentException e) {
            job = Job.INVALID;
        }

        switch(job) {
            case LIST:
                Task.listTasks();
                break;
            case MARK:
                Task.markTasks(Integer.parseInt(commands[1])-1);
                break;
            case UNMARK:
                Task.unmarkTasks(Integer.parseInt(commands[1])-1);
                break;
            case DELETE:
                Task.deleteTask(Integer.parseInt(commands[1])-1);
                break;
            default:
                String taskName = "";
                for (int i = 1; i < commands.length; i++) {
                    taskName += commands[i] + " ";
                }
                try {
                    Task.addTask(commands[0], taskName);
                } catch(Exception e) {
                    System.out.println(e.getMessage());
                }
        }
        System.out.println("\n----------------------------------\n");
    }

}
