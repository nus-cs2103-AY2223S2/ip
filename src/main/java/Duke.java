import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String LINE = "___________________________________________________\n";
    private ArrayList<Task> list = new ArrayList<>();
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
            String[] input = sc.nextLine().split(" ");

            switch(input[0]) {
                case "bye":
                    this.exit();
                    break;

                case "list":
                    System.out.println(Duke.LINE + "Here are the tasks in your list:");
                    for (int i = 0; i < counter; i++) {
                        System.out.println(i+1 + "." + list.get(i).toString());
                    }
                    System.out.println(Duke.LINE);
                    break;

                case "mark":
                    int position = Integer.parseInt(input[1]) - 1;
                    Task toChange = list.get(position);
                    toChange.mark();
                    System.out.println(Duke.LINE + "Nice! I've marked this task as done:\n"
                            + toChange.toString() + "\n" + Duke.LINE);
                    break;

                case "unmark":
                    int positionu = Integer.parseInt(input[1]) - 1;
                    Task toChangeu = list.get(positionu);
                    toChangeu.unmark();
                    System.out.println(Duke.LINE + "Okay, I've marked this task as not done yet:\n"
                            + toChangeu.toString() + "\n" + Duke.LINE);
                    break;


                default:
                    list.add(new Task(input[0]));
                    counter++;
                    System.out.println(Duke.LINE + "added: " + input[0]);
                    System.out.println(Duke.LINE);
                    break;
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Duke duke = new Duke();
        duke.start(scanner);
    }
}
