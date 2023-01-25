import java.util.Scanner;

public class Ui {
    protected static String divider = "    ____________________________________________________________";
    private Scanner command;

    public Ui() {
        greetUser();
        this.command = new Scanner(System.in);
    }

    public void greetUser() {
        System.out.println(divider);
        System.out.println("    Hello! I'm Duke");
        System.out.println("    What can I do for you?");
        System.out.println(divider);
    }

    public String nextInput() {
        return this.command.nextLine();
    }

    public void closeCommand() {
        this.command.close();
    }

    public void showLoadingError() {
        System.out.println("History unable to be retrieved!");
    }
}
