import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke\nWhat can I do for you?\n");

        boolean flag = true;
        List<String> ls = new ArrayList<>();

        while (flag) {
            Scanner sc = new Scanner(System.in);
            String inp = sc.nextLine();
            switch (inp) {
                case "list":
                    for (int i = 0; i<ls.size();i++) {
                        System.out.println(i+1 + ". " +ls.get(i));
                    }
                    break;
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    flag = false;
                    break;
                default:
                    ls.add(inp);
                    System.out.println("added: "+inp);
            }
        }
    }
}
