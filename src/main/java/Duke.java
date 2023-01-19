import java.util.*;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?\n");

        Scanner sc = new Scanner(System.in);
        List<String> arr = new ArrayList<>();

        while (sc.hasNextLine()) {
            String ip = sc.nextLine();
            if (ip.equalsIgnoreCase("bye")) {
                break;
            } else if (ip.equalsIgnoreCase("list")) {
                for (int i = 0; i < arr.size(); i++) {
                    System.out.println(i + 1 + ". " + arr.get(i));
                }
                System.out.println();
            }
            // level 1
            // System.out.println(ip + "\n");

            // level 2
            else {
                System.out.println("added: " + ip + "\n");
                arr.add(ip);
            }

        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
