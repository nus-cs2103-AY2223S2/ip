package duke.ui;

public class CommandLineUi implements Ui {
    @Override
    public void showText(String text) {
        System.out.printf("     %s\n", text);
    }

    @Override
    public void showLine() {
        System.out.println("    ____________________________________________________________");
    }

    @Override
    public void showStartup() {
        String logo =
                " /\\_/\\\n" +
                        "( o.o )   ~meow~\n" +
                        " > ^ <";
        System.out.println(logo);
        showLine();
        showText("Hello! I'm duke.Duke");
        showText("What can I do for you?");
        showLine();
    }
}
