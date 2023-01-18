import java.util.ArrayList;
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String echo = "";
        Scanner sc = new Scanner(System.in);
        String logo = "\n____________________________________________________________\n";

        System.out.println(logo + "Hello! I'm GPT-1!");
        System.out.println("What can I do for you?" + logo);

        ArrayList<Task> storer = new ArrayList<>();
        while (!echo.equals("bye")) {
            echo = sc.nextLine();
            String[] commands = echo.split(" ");
            if (echo.equals("list")) {
                System.out.println(logo);
                for (int i = 1; i <= storer.size(); i++) {
                    int j = i - 1;
                    System.out.println(i + ". " + storer.get(j));
                }
                System.out.println(logo);

            } else if (commands[0].equals("mark")) {
                int num = Integer.valueOf(commands[1]);
                storer.get(num - 1).mark();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(storer.get(num - 1));

            } else {
                System.out.println(logo);
                storer.add(new Task(echo));
                System.out.println("added: " + echo);
                System.out.println(logo);
            }


        }
        System.out.println(logo + "Bye. Hope to see you again soon!" + logo);
    }
}

