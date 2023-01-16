import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String LOGO = "       _             _    _    _                _                \n" +
                "  ___ | |__    __ _ | |_ | |_ (_) _ __    __ _ | |_  ___   _ __  \n" +
                " / __|| '_ \\  / _` || __|| __|| || '_ \\  / _` || __|/ _ \\ | '_ \\ \n" +
                "| (__ | | | || (_| || |_ | |_ | || | | || (_| || |_| (_) || | | |\n" +
                " \\___||_| |_| \\__,_| \\__| \\__||_||_| |_| \\__, | \\__|\\___/ |_| |_|\n" +
                "                                         |___/                   ";

        System.out.println("Hello from\n" + LOGO);

        reply("This is Chattington the Chatter, I'll chat with you.\n" +
                "What can I do for you?\n");

        while(true) {
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();
            if (input.equals("bye")) {
                reply("Alright, goodbye to you too!");
                break;
            } else {
                reply(input);
            }
        }
    }

    private static void reply(String s) {
        if (s.charAt(s.length() - 1) != '\n') {
            s += '\n';
        }
        String LINE_SEPARATOR = "----------------------------------------\n";
        String output = LINE_SEPARATOR + s + LINE_SEPARATOR;
        for (String line : output.split("\n")) {
            System.out.println(indent(line));
        }
    }

    private static String indent(String s) {
        return "\t" + s;
    }
}
