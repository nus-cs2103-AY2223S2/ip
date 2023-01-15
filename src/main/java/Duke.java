import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Duke {

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    private static void greet() {
        System.out.println("Hello, I am Duke.\n" +
                "What can I do for you?");
    }

    public static void main(String[] args) {
        greet();
    }
}
