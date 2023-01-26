package tunabot;

/**
 * Class to handle User Interface
 */
public class Ui {
    private static final String LINE = "------------------------------";
    public Ui() {
    }
    public void saveFileProblem() {
        System.out.println("BLUB! Problem with save file!");
    }

    /**
     * Prints out greeting message upon launch
     */
    public void greeting() {
        System.out.println(LINE);
        System.out.println("    Hello! I'm TunaBot\n"
                + "    What can I do for you?");
        System.out.println(LINE);
    }

    public void line() {
        System.out.println(LINE);
    }
    public void printErrorMessage(Exception e) {
        System.out.println(e.getMessage());
    }

    /**
     * Prints error when date time format is wrong.
     */
    public void printDateTimeFormatError() {
        System.out.println("BLUB! Please use the format dd/mm/yy-hhmm "
                + "with time in 24H format! eg. 29/12/23-1854");
    }
}
