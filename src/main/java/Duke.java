import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        int exit = 0;
        System.out.println("Hello! I'm Duke\nWhat can I do for you?\n" );

        while (exit != 1) {
            Scanner sc = new Scanner(System.in);
            String info = sc.nextLine();
            if (info.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!\n");
                exit = 1;
            } else {
                System.out.println(info);
            }
        }
    }
}
