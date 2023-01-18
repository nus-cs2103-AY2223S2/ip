import java.util.*;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        userInputs();
    }

    private static void userInputs() {
        Scanner sc = new Scanner(System.in);
        ArrayList list = new ArrayList<>(100);

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        while (true) {
            String input = sc.nextLine();

            switch (input) {
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    break;

                case "list":
                    for (int i = 0; i < list.size(); i++) {
                        System.out.println(i + 1 + "." + list.get(i));
                    }
                    break;

                default:
                    list.add(input);

            }
        }
    }
}

