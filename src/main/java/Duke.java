import java.util.Objects;
import java.util.Scanner;

public class Duke {
    public static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    public static String intro = "    ____________________________________________________________\n" +
            "     Hello! I'm Duke\n" +
            "     What can I do for you?\n" +
            "    ____________________________________________________________\n";
    public static String outro = "    ____________________________________________________________\n" +
            "     Bye. Hope to see you again soon!\n" +
            "    ____________________________________________________________\n";

    public static String getReply(String str) {
        return "    ____________________________________________________________\n" +
                "     " + str + "\n" +
                "    ____________________________________________________________\n";
    }

    public static void main(String[] args) {
        System.out.println(Duke.intro);
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("");
            String str = sc.nextLine();
            if (Objects.equals(str, "bye")) {
                System.out.println(Duke.outro);
                break;
            }
            System.out.println(Duke.getReply(str));
        }
    }
}
