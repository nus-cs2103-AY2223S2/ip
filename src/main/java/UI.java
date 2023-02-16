public class UI {
    private static final String FIRST_COMMAND_MESSAGE = "You can let me know by typing it below!";
    private static final String NEXT_COMMAND_MESSAGE = "What can I do for you next?";
    private static final String INVALID_DATE_FORMAT_MESSAGE = "Please re-enter the request with the date in the \n" +
                                                              "following format: dd-Mmm-yyyy";


    public void printWelcome(){
        UI.horizontalLine();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello there! I am: \n" + logo + "\nWhat can I help you with!");
        System.out.println(FIRST_COMMAND_MESSAGE);
        UI.horizontalLine();
    }

    public void printNextCommandMessage() {
        System.out.println(NEXT_COMMAND_MESSAGE);
    }

    public void printInvalidDateFormatMessage() {
        System.out.println(INVALID_DATE_FORMAT_MESSAGE);
    }
    public static void horizontalLine() {
        for (int i = 0; i < 50; i++) {
            char horizontalBar = '\u2015';
            System.out.print(horizontalBar);

        }

        System.out.print("\n");
    }



}