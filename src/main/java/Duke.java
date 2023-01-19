import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class Duke {
    static int exit = 0;
    static List<String> todo = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?\n" );

        while (exit != 1) {
            Scanner sc = new Scanner(System.in);
            String info = sc.nextLine();
            if (info.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!\n");
                exit = 1;
            } else if (info.equals("list")) {
                showList();
            } else {
                System.out.println("added: " + info);
                todo.add(info);
            }
        }
    }

    public static void showList() {
        todo.forEach( (s) -> System.out.println( (todo.indexOf(s)+1) +". " + s));
    }
}
