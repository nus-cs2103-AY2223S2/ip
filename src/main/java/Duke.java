import java.util.Scanner;

public class Duke {
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
        System.out.println("=============================================");
        System.out.println("Oink! I'm \n" + logo + art);
        System.out.println("Nice to meet you! How can I assist you today?");
        System.out.println("=============================================");

        Scanner sc = new Scanner(System.in);
        while (true) {
            String command = sc.nextLine();
            if (command.equals("bye")) {
                System.out.println("Oink oink! See you again :)");
                sc.close();
                break;
            } else {
                System.out.println(command);
                System.out.println("=============================================");
            }
        }
    }
}

