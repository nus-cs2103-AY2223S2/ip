import java.util.ArrayList;
import java.util.Scanner;

public class DukeIO {

    private static final String INPUT_PROMPT = "MyDuke >    "; 

    private static final String LOGO =  " ____        _        \n"
                                    + "|  _ \\ _   _| | _____ \n"
                                    + "| | | | | | | |/ / _ \\\n"
                                    + "| |_| | |_| |   <  __/\n"
                                    + "|____/ \\__,_|_|\\_\\___|\n";
    
    private static final String SEPERATOR = "======================================================\n";

    private static final String CMD_LIST = SEPERATOR
                                         + "[list] : Show a list of all tasks currently\n"
                                         + "[todo] : Add a to-do task\n"
                                         + "[deadline] : Add a task to be completed by a date\n"
                                         + "[event] : Add an event from start time to end time\n"
                                         + "[mark] : Mark a task as completed\n"
                                         + "[unmark] : Unmark a completed task\n"
                                         + "[delete] : Delete a task from the list\n"
                                         + SEPERATOR;

    private static final String REPLY = "|    ";
    
    
    private static final String HELLO = REPLY + "Hello from\n" 
                                        + SEPERATOR
                                        + LOGO
                                        + SEPERATOR
                                        + REPLY + "What's on your mind today?\n"
                                        + SEPERATOR;

    private static final String SAVE_SUCCESS = "Successfully saved all tasks\n";
    
    private static final String QUIT = SEPERATOR
                                    + REPLY + SAVE_SUCCESS 
                                    + REPLY + "Quitting Duke...\n"
                                    + REPLY + "See you soon!\n"
                                    + SEPERATOR;
     

    public static String[] tokenise(Scanner sc) {
        String[] tokens = sc.nextLine().split(" ");
        return tokens;
    }
    
    public static void printHello() {
        System.out.println(HELLO);
    }

    public static void printQuit() {
        System.out.println(QUIT);
    }

    public static void showPrompt() {
        System.out.print(INPUT_PROMPT);
    }

    public static void showSuccessToast(Task t) {
        System.out.println(REPLY + "Successfully added: " + t.toString());
    }

    public static void showCount() {
        String isare;
        String s;
        int taskCount = MyDuke.getTaskCount();
        if (taskCount > 1) {
            isare = " are: ";
            s = " tasks";
        } else {
            isare = " is: ";
            s = " task";
        }
        System.out.println("\nThere" + isare + Integer.toString(taskCount) 
                            + s + " in the list.\n");
    }

    public static void showAll() {
        int taskCount = MyDuke.getTaskCount();
        ArrayList<Task> allTasks = MyDuke.getAllTasks();
        System.out.println("All Tasks:");
        for (Integer i = 0; i < taskCount; i++) {
            String showString = "   "  + Integer.toString(i+1)+ ": "
                                + allTasks.get(i).toString();
            System.out.println(showString);     
        }
        System.out.println();
    }

    public static void showInvalidCommand() {
        System.out.println(SEPERATOR
                        + REPLY + "Invalid Command!\n"
                        + CMD_LIST);
    }

    public static void showError(Exception e) {
        System.out.println(e.getMessage());
    }
    
}
