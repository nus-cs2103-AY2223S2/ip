package duke.ui;

/**
 * User interface implementation for the command line.
 */
public class CommandLineUi implements Ui {
    /**
     * Displays text on command line.
     *
     * @param text The text to be displayed.
     */
    @Override
    public void showText(String text) {
        System.out.printf("     %s\n", text);
    }

    /**
     * Displays horizontal line on the command line.
     */
    @Override
    public void showLine() {
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Displays startup text on the command line.
     */
    @Override
    public void showStartup() {
        String logo =
                " /\\_/\\\n"
                + "( o.o )   ~meow~\n"
                + " > ^ <";
        System.out.println(logo);
        showLine();
        showText("Hello! I'm duke.Duke");
        showText("What can I do for you?");
        showLine();
    }
}
