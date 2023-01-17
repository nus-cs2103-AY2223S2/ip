import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hiii! I'm panpan\nWhat's up?");
        echo();
    }
    public static void echo() {
        Scanner input = new Scanner(System.in);
        String cmd;
        while (true) {
            cmd = input.nextLine();
            if (cmd.equals("bye")) {
                System.out.println("Buhbyeee, hope to see you again soon <3");
                return;
            } else {
                System.out.println(cmd);
            }
        }
    }
}
