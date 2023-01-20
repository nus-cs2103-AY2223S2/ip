import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Duke {


    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "\n");

        Scanner sc = new Scanner(System.in);

        String echo = sc.nextLine();

        List<String> store = new ArrayList<>();

        while (!echo.equals("bye")) {
            if (echo.equals("list")) {
                for (int i = 0; i < store.size(); i++) {
                    System.out.print(i+1 + ":" + store.get(i) + "\n");
                }
                echo = sc.nextLine();
            } else {
                store.add(echo);
                System.out.print("added:" + echo + "\n");
                echo = sc.nextLine();
            }
        }
        System.out.print("Bye, have a good day!");
    }
}