import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        // Level 1
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello I'm Duke \nWhat can I do for you?");
        String input = sc.nextLine();

        while(!input.equals("bye")) {
            System.out.println(input);
            input = sc.nextLine();
        }

        sc.close();
    }
}
