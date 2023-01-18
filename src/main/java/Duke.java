import java.util.Scanner;

public class Duke {
//    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
//    }
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        String[] ls = new String[100];
        int lsCount = 0;
        while (!s.equals("bye")) {
            if (s.equals("list")) {
                for (int i = 0; i < lsCount; i++) {
                    System.out.println( (i+1) + ". " + ls[i]);
                }
            }
            else {
                ls[lsCount] = s;
                lsCount += 1;
                System.out.println("added: " + s);
            }
            s = sc.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
