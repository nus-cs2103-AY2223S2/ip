import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String[] inputs = new String[100];
        int curr = 0;
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?\n");

        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();

        while(!str.equals("bye")) {
            if(str.equals("list")) {
                for(int i = 0; i < curr; i++) {
                    System.out.println((i + 1) + ". " + inputs[i]);
                }
            } else {
                System.out.println("added: " + str + "\n");
                inputs[curr++] = str;
            }
            str = scanner.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
