package shigure;

import java.util.Scanner;

/**
 * A UI controller for Miki interactive command-line I/O.
 */
public class Cli extends Ui {
    private static final String DIV =
            "    ____________________________________________________________________________";
    private final boolean isAsciiOnly;
    private final boolean isAutoDiv = true;
    private final Scanner sc = new Scanner(System.in);

    /**
     * Creates a new CLI, optionally using ASCII-only versions of built-in <code>Strings</code>.
     *
     * @param isAsciiOnly whether this Cli shall operate in ASCII-only mode.
     */
    public Cli(boolean isAsciiOnly) {
        this.isAsciiOnly = isAsciiOnly;
    }

    /**
     * Prints an output prompt and reads a single line of input.
     *
     * @return the read line of user input.
     */
    public String readLine() {
        System.out.print(">");
        return sc.nextLine();
    }

    @Override
    public void printDiv() {
        System.out.println(DIV);
    }

    @Override
    public void printAutoDiv() {
        if (isAutoDiv) {
            printDiv();
        }
    }

    /**
     * Prints a block of output to the user interface,
     * formatted with indentation for clarity.
     *
     * @param s <code>String</code> to be printed.
     */
    private void print(String s) {
        String[] lines = s.split("\n");
        for (String ln : lines) {
            System.out.println("     " + ln);
        }
    }

    @Override
    public void printUser(String s) {
        // Do nothing
    }

    @Override
    public void printMiki(String s) {
        print(s);
    }

    @Override
    public void printTasks(String[] tasks) {
        for (String t : tasks) {
            printMiki(t);
        }
    }

    @Override
    public void refreshTasks(String[] tasks) {
        // Do nothing
    }

    @Override
    public void printIntro() {
        printAutoDiv();
        String username = System.getProperty("user.name");

        if (!isAsciiOnly) {
            printMiki("in honour / fuzuki miki / 2020 | 2021\n"
                    + "\uD83C\uDF80✨\n"
                    + "Hello " + username + " !! Konmiki! ＼(￣▽￣)/");
        } else {
            printMiki("in honour / fuzuki miki / 2020 | 2021\n"
                    + "01 f3 80 / 27 28\n"
                    + "Hello " + username + " !! Konmiki! \\(^v^)/");
        }
        printAutoDiv();
    }

    @Override
    public void clearInput() {
        // Do nothing
    }

    @Override
    public void close() {
        // Do nothing
    }

}
