import java.util.Scanner;

public class Ui {

    Scanner userInputScan;

    //Text print constants
    private static final String NAME = "C4PO-Storage";
    private static final String LINE = "-----------------------------------------------";
    private static final String QUOTE = "Hello. I don’t believe we have been introduced. A pleasure to meet you. I am " + NAME + " Human-Computer Relations.";
    private static final String JOB_QUOTE = "I collect words which you say.";

    private static final String CALL_TO_ACTION = ": Master, please type your response below.";


    private static final String FORGOT_QUOTE = "Oh! It seems I do not remember your past tasks... silly me! Let me organise my circuits.";
    private static final String REMEMBER_QUOTE = "Oh! It seems I do remember your past tasks... thank goodness! Let me see, the tasks are:";
    private static final String INIT_MEMORY_QUOTE = "Thank you for waiting sir. Successfully initiated memory";
    private static final String FAREWELL_QUOTE = ": Goodbye! I'll miss all of you, especially you R3-D3.";

    private static final String NO_SUCH_TASK_QUOTE = "No such item exists in list";

    public static final String UNABLE_TO_UNDERSTAND_QUOTE = "My apologies sir, my program forbids me from translating anything other than command words.";
    private static final String MARKED_DONE_QUOTE = "Great work sir! I've marked this task as done. Task:";
    public static final String MARK_ERR = "Failed to delete! Index for deletion may be empty or does not exist.";
    private static final String UNMARKED_QUOTE = "Ahhh I see ...  I shall unmark that task then. *beep* Done. Task:";
    private static final String DATA_FILE_CREATE_ERROR = "Error creating data file";
    private static final String NOTHING_LIST = "Master, there seems to be nothing in your list! Goodness me! Has my circuitry failed me again?!";
    private static final String ITEMS_IN_LIST_PREFIX = "Master, here are the items in your list:";
    private static final String TASK_ADDED = "Excellent sir, I've added the task: ";
    private static final String TASK_DEL_QUOTE = "Ahhh I see ...  I shall delete that task then. *beep* Done. Task deleted:";
    private static final String MARK_FAIL = "Operation failed. The task may not exist.";


    public Ui() {
        userInputScan = new Scanner(System.in);

    }



    /**
     * Returns a string containing the user input
     * @return
     */
    public String getNextInput() {
        return userInputScan.nextLine();
    }

    /**
     * Prints a specified number of dashed lines to the console output
     * @param num
     */
    public static void printDashLine(Integer num) {
        for (int i = 0; i < num; i++) {
            System.out.println(LINE);
        }
    }


    public static void showNoSuchTask() {
        System.out.println(NO_SUCH_TASK_QUOTE);
    }
    /**
     * Prints welcomes messages to the output terminal
     */
    public static void showIntroduction() {
        //Introductory Responses
        System.out.println(QUOTE);
        System.out.println(JOB_QUOTE);
        System.out.println(NAME + CALL_TO_ACTION);
        System.out.println(LINE);
        System.out.println(LINE);
    }


    /**
     * Prints string str to the output console
     * @param str
     */
    public static void print(String str) {
        System.out.println(str);
    }
    
    public static void showTaskDeletedQuote() {
        System.out.println(TASK_DEL_QUOTE);
    }

    public static void printTask(Task task) {
        System.out.println(task);
    }

    /**
     * Prints loading error message
     */
    public static void showLoadingError() {
        System.out.println(FORGOT_QUOTE);
    }

    /**
     * Prints initialised db message
     */
    public static void showCreatedDbMsg() {
        System.out.println(INIT_MEMORY_QUOTE);
    }


    public static void showLoadedMsg() {
        System.out.println(REMEMBER_QUOTE);
    }

    public static void showCreateDbError() {
        System.out.println(DATA_FILE_CREATE_ERROR);
    }

    public static void showFarewell() {
        System.out.println(NAME + FAREWELL_QUOTE);
    }

    public static void showMarkedDone() {
        System.out.println(MARKED_DONE_QUOTE);
    }
    
    public static void showNothingInList() {
        System.out.println(NOTHING_LIST);
    }

    public static void showUnmarked() {
        System.out.println(UNMARKED_QUOTE);
    }

    public static void showTaskAdded(){
        System.out.println(TASK_ADDED);
    }
    
    public static void showListPre() {
        System.out.println(ITEMS_IN_LIST_PREFIX);
    }

    public static void showMarkFail() {
        System.out.println(MARK_FAIL);
    }
}
