public class AvaUI {
    //ASCII Emoticons
    private final String AVA_EYES_OPEN = "(｡◕‿◕｡)";
    private final String AVA_EYES_CLOSE = "( ^_^)";
    private final String BOUNDARY = "﹋";
    //Boundary Constants
    private int BNUM = 50;

    //Introduction Constant
    private final String INTRO_MESSAGE = "Heya! I am Ava.";
    private final String GREET = "Nice to meet you!!";
    private final String ASK_MESSAGE = "How can I brighten you're day ?";
    private final String EXIT_MESSAGE = "Have a nice day !!";


    public void displayIntro(){
        /**
         * Prints the Intro message
         */
        this.displayBoundary();
        this.printOutput(INTRO_MESSAGE);
        this.printOutput(GREET);
        this.printOutput(ASK_MESSAGE);
        this.displayBoundary();
    }

    public void displayExit() {
        /**
         * Prints the exit message.
         */
        this.displayBoundary();
        this.printOutput(EXIT_MESSAGE);
        this.displayBoundary();
    }

    public void ask() {
        /**
         * Prints a Boilerplate to ask user the command.
         */
        System.out.print("\n"+ this.AVA_EYES_OPEN + ": ");
    }

    public void displayOutput(String output) {
        /**
         * Displays Output with boundaries
         */
        this.displayBoundary();
        printOutput(output);
        this.displayBoundary();
    }


    private void printOutput(String emote, String message ) {
        /**
         * PRINTING MESSAGE WITH A SPECIAL EMOTICON
         */
        System.out.println(emote + message);
    }

    private void printOutput(String message) {
        /**
         * Default Way to print a message with AVA_EYES_CLOSE EMOTICON
         */
        System.out.println(this.AVA_EYES_CLOSE +" "+message);
    }

    private void displayBoundary() {
        /**
         * Displays Boundary According to Constant BOUNDARY and the number is determined by BNUM
         */
        String res = "\n";
        for (int i = 0; i < BNUM ; i++) {
            res += this.BOUNDARY;
        }
        System.out.println(res);
    }



}
