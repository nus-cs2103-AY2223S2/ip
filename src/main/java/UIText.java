import java.util.Scanner;

public class UIText {
    private static final String GREET = "Hello! I am Kevin, an interactive chatbot. What can I do for you?";
    private static final String EXIT = "Bye! Hope to talk to you again!";
    private Scanner sc;
    public UIText() {
        this.sc = new Scanner(System.in);
    }

    public String getInput() {
        return sc.nextLine();
    }

    public String greet() {
        return GREET;
    }
    public String exit() {
        sc.close();
        return EXIT;
    }
}
