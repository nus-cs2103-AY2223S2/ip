import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String LINE = "___________________________________________________\n";
    private ArrayList<String> list = new ArrayList<>();
    private int counter = 0;

    public void greet() {
        System.out.println(Duke.LINE
                + "Hello! I'm Duke\n"
                + "What can I do for you?\n"
                + Duke.LINE);
    }

    public void exit() {
        System.out.println(Duke.LINE
                + "Bye. Hope to see you again soon!\n"
                + Duke.LINE);
    }

    public void start(Scanner sc) {
        this.greet();

        while(sc.hasNext()) {
            String input = sc.nextLine();

            if (input.equals("bye")) {
                this.exit();
                break;
            } else if (input.equals("list")) {
                System.out.println(Duke.LINE);
                for (int i = 0; i < counter; i++) {
                    System.out.println(i+1 + ". " + list.get(i));
                }
                System.out.println(Duke.LINE);
            } else {
                list.add(input);
                counter++;
                System.out.println(Duke.LINE + "added: " + input);
                System.out.println(Duke.LINE);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Duke duke = new Duke();
        duke.start(scanner);
    }
}
