import java.util.Scanner;

public class JeoBot {
    public static void greeting() {
        String name = "JeoBot";
        String divider = "-----------------------";
        System.out.println(divider + "\n" + "Greetings from " + name + "!\n" + "How may I help you?\n" + divider);
    }

    public static void echo() {
        String divider = "________________________________________________________________";
        boolean hasInput = true;
        Storage st = new Storage();
        while (hasInput) {
            Scanner in = new Scanner(System.in);
            String s = in.nextLine();
            System.out.println(divider);
            if (s.equals("bye")) {
                hasInput = false;
                System.out.println("Thank you for using my service. Hope to see you again soon!");
            } else if (s.equals("list")) {
                st.showTasks();
            } else {
                st.addTask(s);
            }
            System.out.println(divider);
        }
    }

    public static void main(String[] args) {
        greeting();
        echo();
    }
}
