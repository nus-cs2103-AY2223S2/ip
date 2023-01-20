import java.util.Scanner;

public class Duke {


    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Scanner sc = new Scanner(System.in);

        String echo = sc.nextLine();

        while (!(echo.equals("bye"))) {
            System.out.print(echo);
            echo = sc.nextLine();
        }
        System.out.print("bye. Hope to see you again soon!");
    }

}
