import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Hello! I'm Tik. What can i do for you?");
        while (in.hasNext()) {
            String s = in.nextLine();
            String cmd = s.toLowerCase();
            if (cmd.equals("bye")) {
                break;
            }
            System.out.println(s);
        }
    }
}
