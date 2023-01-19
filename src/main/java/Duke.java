import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hi! I'm Samantha\nHow can I help?");
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        while (!s.equals("bye")) {
            System.out.println("    " + s);
            s = sc.next();
        }
        System.out.println("    Bye. Hope to see you soon!");
    }
}
