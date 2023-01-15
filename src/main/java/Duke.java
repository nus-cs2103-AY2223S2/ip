import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static final String divider = "=============================================";
    public static ArrayList<String> commands = new ArrayList<>();
    public static void displayList() {
        for (int i = 0; i < commands.size(); i++) {
            System.out.println(Integer.toString(i + 1) + ". " + commands.get(i));
        }
        System.out.println(divider);
    }

    public static void main(String[] args) {
        String logo = " ____  ____  ____  ____   __\n"
                + "(  _ \\(  __)(  _ \\(  _ \\ / _\\\n"
                + " ) __/ ) _)  ) __/ ) __//    \\\n"
                + "(__)  (____)(__)  (__)  \\_/\\_/\n";
        String art = "       _\n"
                + "  <`--'\\>______\n"
                + "  /. .  `'     \\\n"
                + " (`')  ,        @\n"
                + "  `-._,        /\n"
                + "     )-)_/--( >  jv\n"
                + "    ''''  ''''\n";
        System.out.println(divider);
        System.out.println("Oink! I'm \n" + logo + art);
        System.out.println("Nice to meet you! How can I assist you today?");
        System.out.println(divider);

        Scanner sc = new Scanner(System.in);
        while (true) {
            String command = sc.nextLine();
            if (command.equals("bye")) {
                System.out.println("Oink oink! See you again :)");
                sc.close();
                break;
            } else if (command.equals("list")) {
                displayList();
            } else {
                commands.add(command);
                System.out.println("Added: " + command);
                System.out.println(divider);
            }
        }
    }
}

