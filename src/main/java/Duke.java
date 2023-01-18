import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String echo = "";
        Scanner sc = new Scanner(System.in);
        String logo = "\n____________________________________________________________\n";

        System.out.println(logo + "Hello! I'm GPT-1!");
        System.out.println("What can I do for you?" + logo);

        while (!echo.equals("bye")) {
            echo = sc.next();
            System.out.println(logo + echo + logo);
        }
        System.out.println(logo + "Bye. Hope to see you again soon!" + logo);
    }
}