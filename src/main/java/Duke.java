import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        Scanner Obj = new Scanner(System.in);
        String input = "";
        String task[] = new String[100];
        int counter = 0;
        while (input.equals("bye") == false) {
            input = Obj.nextLine();
            if (input.equals("bye") == true) {
                break;
            }
            if (input.equals("list") == false) {
                System.out.println("added: " + input);
                task[counter] = input;
                counter++;
            }
            if (input.equals("list") == true) {
                for (int i = 0; i < counter; i++) {
                    System.out.println(i + 1 + ". " + task[i]);
                }
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
