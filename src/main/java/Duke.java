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
        //echo();
        add();
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

    public static void add() {
        Scanner input = new Scanner(System.in);
        String cmd;
        String[] list = new String[100];
        Integer count = 0;
        while (true) {
            cmd = input.nextLine();
            if (cmd.equals("bye")) {
                System.out.println("Buhbyeee, hope to see you again soon <3");
                return;
            } else if (cmd.equals("list")) {
                for (int i = 1; i <= count; i++ ) {
                    System.out.println(i + ". " + list[i-1]);
                }
            } else {
                list[count] = cmd;
                count ++;
                System.out.println("added: " + cmd);
            }
        }
    }
}
