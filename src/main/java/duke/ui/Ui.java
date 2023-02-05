package duke.ui;

public class Ui {

    private static final String BAR =
            "    ____________________________________________________________";

    private static final String INDENTATION = "     ";
    private static final String NEW_LINE = "\n";

    public static void greet() {
        System.out.println(BAR);
        System.out.println(INDENTATION + "Hello! I'm Duke");
        System.out.println(INDENTATION + "What can I do for you?");
        System.out.println(BAR);
        System.out.print(NEW_LINE);
    }


}
