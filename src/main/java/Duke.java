import java.util.Scanner;

public class Duke {
    private final static String bye = "bye";
    private final static String goodbyeMessage = "Bye. Hope to see you again soon!";

    private static void indentedPrintln(String message) {
        String indentedMessage = "   " + message;
        System.out.println(indentedMessage);
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        while (!str.equals(bye)) {
            indentedPrintln(str);
            str = sc.nextLine();
        }
        indentedPrintln(goodbyeMessage);
    }
}
