import java.util.*;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("----------------------------------------");

        ArrayList<String> list = new ArrayList<String>();
        Scanner sc = new Scanner(System.in);
        while (true) {
            String in = sc.nextLine();
            System.out.println("----------------------------------------");
            if (in.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (in.equals("list")) {
                for (int i = 0; i < list.size(); i++) {
                    System.out.println((i + 1) + ". " + list.get(i));
                }
            } else {
                list.add(in);
                System.out.println(in);
            }
            System.out.println("----------------------------------------");
        }
        sc.close();
    }
}
