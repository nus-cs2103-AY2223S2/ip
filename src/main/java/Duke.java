import java.util.ArrayList;
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String echo = "";
        Scanner sc = new Scanner(System.in);
        String logo = "\n____________________________________________________________\n";

        System.out.println(logo + "Hello! I'm GPT-1!");
        System.out.println("What can I do for you?" + logo);

        ArrayList<String> storer = new ArrayList<>();
        while (!echo.equals("bye")) {
            echo = sc.nextLine();

            if (echo.equals("list")) {
                System.out.println(logo);
                for (int i = 1; i <= storer.size(); i++) {
                    int j = i - 1;
                    System.out.println(i + ". " + storer.get(j));
                }
                System.out.println(logo);

            } else {
                System.out.println(logo);
                storer.add(echo);
                System.out.println("added: " + echo);
                System.out.println(logo);
            }


        }
        System.out.println(logo + "Bye. Hope to see you again soon!" + logo);
    }
}