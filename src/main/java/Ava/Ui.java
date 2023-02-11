package Ava;

public class Ui {
    //ASCII Emoticons
    private static final String AVA_EYES_OPEN = "(｡◕‿◕｡)";
    private static final String AVA_EYES_CLOSE = "( ^_^)";
    private static final String BOUNDARY = "﹋";
    private static final String FORMAT_SPACE = "         ";
    //Boundary Constants
    private static int BNUM = 50;
    //Introduction Constant
    private static final String INTRO_MESSAGE = "Heya! I am Ava.";
    private static final String GREET = "Nice to meet you!!";
    private static final String ASK_MESSAGE = "How can I brighten you're day ?";
    private static final String EXIT_MESSAGE = "Have a nice day !!";


    public static String getFormatSpace(){
        return Ui.FORMAT_SPACE;
    }

    /**
     * Prints the Intro message
     */
    public static void displayIntro(){
        Ui.displayBoundary();
        Ui.printOutput(INTRO_MESSAGE);
        Ui.printOutput(GREET);
        Ui.printOutput(ASK_MESSAGE);
        Ui.displayBoundary();
    }

    /**
     * Returns the Intro message
     */
    public static String getIntro(){
        return INTRO_MESSAGE + "\n" + GREET + "\n" + ASK_MESSAGE;
    }

    /**
     * Prints the exit message.
     */
    public static void displayExit() {
        Ui.displayBoundary();
        Ui.printOutput(EXIT_MESSAGE);
        Ui.displayBoundary();
    }

    /**
     * Prints a Boilerplate to ask user the command.
     */
    public static void ask() {
        System.out.print("\n"+ Ui.AVA_EYES_OPEN + ": ");
    }

    /**
     * Displays Output with boundaries
     */
    public static void displayOutput(String output) {
        Ui.displayBoundary();
        printOutput(output);
        Ui.displayBoundary();
    }

    /**
     * PRINTING MESSAGE WITH A SPECIAL EMOTICON
     */
    private static void printOutput(String emote, String message ) {

        System.out.println(emote + message);
    }

    /**
     * Default Way to print a message with AVA_EYES_CLOSE EMOTICON
     */
    private static void printOutput(String message) {

        System.out.println(Ui.AVA_EYES_CLOSE +" "+message);
    }

    /**
     * Displays Boundary According to Constant BOUNDARY and the number is determined by BNUM
     */
    private static void displayBoundary() {
        String res = "\n";
        for (int i = 0; i < BNUM ; i++) {
            res += Ui.BOUNDARY;
        }
        System.out.println(res);
    }



}
