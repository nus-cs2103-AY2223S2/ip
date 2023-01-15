import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String lines = "\t____________________________________________________________\n";
        System.out.println(
                lines +
                        "\tHello! I'm Duke\n" +
                        "\tWhat can I do for you?\n" +
                        lines
        );

        String commands = "";
        Scanner sc = new Scanner(System.in);
        commands = sc.nextLine();
        while (!commands.equals("bye")) {
            System.out.println(
                    lines +
                    "\t" + commands + "\n" +
                    lines
            );
            commands = sc.nextLine();
        }
        System.out.println(
                lines +
                "\tBye. Hope to see you again soon!\n" +
                lines
        );
    }
}
