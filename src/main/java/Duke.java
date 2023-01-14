import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("---\n hi i'm Duke! what's up? \n---");

        while(sc.hasNext()) {
            String command = sc.nextLine();
            if (command.equals("bye")) {
                System.out.println("---\n bye! see u soon! :-) \n---");
                System.exit(0);
            } else {
                System.out.println("---\n" + command + "\n---");
            }
        }
    }
}
