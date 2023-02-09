package duke.ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    private static final String LINE_PREFIX = "|| ";
    private static final String LS = System.lineSeparator();
    private static final String DIVIDER = "===================================================";

    private final Scanner in;
    private final PrintStream out;
    private ArrayList<String> responses;

    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
        responses = new ArrayList<>();
    }

    public String showWelcome() {
        String message = "Hello from Duke,\n What can I do for you?";
        return message;
    }

    public void showToUser(String... message) {
        for (String m : message) {
            responses.add(m.replace("\n", LS));
        }
    }

    public String getResponses() {
        String concatenatedResponse = "";
        for (String r : responses) {
            concatenatedResponse += r + "\n";
            System.out.println(r);
        }
        responses.clear();
        return concatenatedResponse;
    }


    public void showGoodbyeMessage() {
        showToUser("Bye. Hope to see you again soon!");
    }

    public void showError(String e) {
        showToUser(e);
    }

    public void showLoadingError() {
        showToUser("Error Loading");
    }
}
