public class Book {
    /** Logo for Book */
    private static final String LOGO = " ____\n"
            + "|  _ \\  ___   ___  _\n"
            + "| |_| |/ _ \\ / _ \\| |  _\n"
            + "|  _ <| | | | | | | |/ /\n"
            + "| |_| | |_| | |_| |   <  \n"
            + "|____/ \\___/ \\___/|_|\\_\\\n";
    /** Horizontal line for separation. */
    private static final String LINE =
            "________________________________________________________________________________\n";
    public static void main(String[] args) {
        System.out.println(LINE + "Good day! This is\n" + LOGO + "What may I help you with?\n" + LINE);
        System.out.println("Bye. Pick up the book again soon!\n" + LINE);
    }
}
