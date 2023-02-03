package dudu.util;

/**
 * Ui class for Dudu to display the interface
 */
public class Ui {
    private static final String LOGO =
            " ____   _   _  ____   _   _\n"
                    + "|  _ \\ | | | ||  _ \\ | | | |\n"
                    + "| | | || | | || | | || | | |\n"
                    + "| |_| || |_| || |_| || |_| |\n"
                    + "|____/  \\___/ |____/  \\___/\n";
    private static final String DIVIDER = "___________________________________________\n";
    private static final String GREETING = DIVIDER + LOGO
            + "Hello! I'm Dudu\n" + "What can I do for you?\n"
            + DIVIDER;
    public Ui() {}

    public void showLine() {
        System.out.print(DIVIDER);
    }
    public void showWelcome() {
        System.out.print(GREETING);
    }
}
