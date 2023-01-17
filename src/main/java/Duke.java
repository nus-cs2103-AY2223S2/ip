import java.util.*;
public class Duke {
    public static final String dashes = "--------------------------------------";
    public String format(String input) {
        return dashes + "\n" + input + "\n" + dashes + "\n";
    }
    public void echo() {
        System.out.println(this.format("Hello! I'm Gerty\nWhat can I do for you?"));
        while (true) {
            Scanner input = new Scanner(System.in);
            String line = input.nextLine();
            if ("bye".equalsIgnoreCase(line)) {
                System.out.println(this.format("Bye. Hope to see you again soon!"));
                break;
            }
            System.out.println(this.format(line));
        }
    }
    public static void main(String[] args) {
        Duke gerty = new Duke();
        gerty.echo();
    }
}
