import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        Scanner Obj = new Scanner(System.in);
        String input = "";
        while (input.equals("bye") == false) {
            input = Obj.nextLine();
            if (input.equals("bye") == true) {
                break;
            }
            System.out.println(input);
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
