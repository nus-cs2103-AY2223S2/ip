package duke.utils;

import java.util.ArrayList;

import duke.Tasks.Task;
import duke.Tasks.TaskList;

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
                                    + REPLY + "Quitting Duke...\n"
                                    + REPLY + "See you soon!\n"
                                    + SEPERATOR;
    
    public void echoMessage(String message) {
        System.out.println(message);
    }
    
    public void printHello() {
        System.out.print(HELLO);
    }

    public void printQuit() {
        System.out.print(QUIT);
    }

    public void showPrompt() {
        System.out.print(INPUT_PROMPT);
    }

    public void notifySuccessAdd(Task t) {
        System.out.println(SEPERATOR
                + REPLY + "Successfully added: " + t.toString());
    }

    public void notifySuccessComplete(Task t) {
        System.out.println(SEPERATOR
                + REPLY + "Successfully completed: " + t.toString());
    }

    public void notifyUnmark(Task t) {
        System.out.println(SEPERATOR
                + REPLY + "Unmarked task: " + t.toString());
    }

    public void nofifyMarkFail(Task t) {
        System.out.println(SEPERATOR
            + REPLY + "Cannot mark completed task: " + t.toString());
    }

    public void notifyUnmarkFail(Task t) {
        System.out.println(SEPERATOR 
            + REPLY + "Cannot unmark incomplete task: " + t.toString());
    }

    public void showCount() {
        String isare;
        String s;
        int taskCount = TaskList.taskCount;
        if (taskCount > 1) {
            isare = " are: ";
            s = " tasks";
        } else {
            isare = " is: ";
            s = " task";
        }
        System.out.println(REPLY + "There" + isare + Integer.toString(taskCount) 
                            + s + " in the list.\n" + SEPERATOR);
    }

    public void showAll() {
        int taskCount = TaskList.taskCount;
        ArrayList<Task> allTasks = new ArrayList<>(TaskList.allTasks);
        System.out.println(SEPERATOR + "All Tasks:");
        for (Integer i = 0; i < taskCount; i++) {
            String showString = "   "  + Integer.toString(i+1)+ ": "
                                + allTasks.get(i).toString();
            System.out.println(showString);     
        }
        System.out.println(SEPERATOR);
    }

    public void showInvalidCommand() {
        System.out.println(SEPERATOR
                        + REPLY + "Invalid Command!\n"
                        + CMD_LIST);
    }

    public void showError(Exception e) {
        System.out.println(SEPERATOR + e.getMessage() + "\n" + SEPERATOR);
    }

    public void notifyLoad() {
        System.out.println("Loaded successfully from previous session.");
    }

    public void notifySave() {
        System.out.println(SAVE_SUCCESS);
    }
    
}
