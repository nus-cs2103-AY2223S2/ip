import java.util.*;
public class Duke {
    public static void main(String[] args) {

        System.out.println("Hello! I'm Duke What can I do for you?");
        Scanner sc= new Scanner(System.in);
        boolean exit = false;
        while(!exit) {
            String str = sc.nextLine();
            exit = echo(str);
        }

    }
    public static boolean echo(String str) {
        if(str.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
            return true;
        } else {
            System.out.println(str);
            return false;
        }
    }
}
