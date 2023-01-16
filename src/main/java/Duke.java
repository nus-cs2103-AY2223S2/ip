import java.util.Scanner;

public class Duke {

    private static void printWithDecorations(String input) {
        System.out.println("OwO OwO OwO OwO OwO OwO");
        System.out.println('~' + input + '~');
        System.out.println("OwO OwO OwO OwO OwO OwO");
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            Duke.printWithDecorations(input);
            input = sc.nextLine();
        }

        sc.close();

        String bye = "Bye. Hope to see you again soon!";
        Duke.printWithDecorations(bye);
    }
}
