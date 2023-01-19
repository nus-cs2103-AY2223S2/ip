import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");

        String[] arr = new String[100];
        int nextIndex = 0;

        while (true) {
            Scanner bucky = new Scanner(System. in);
            String str = bucky.nextLine();
            if (str.equals("bye")) {
                System.out.println ("Bye. Hope to see you again soon!");
                break;
            } else if (str.equals("list")){
                for (int i = 0 ; i < nextIndex ; i++) {
                    System.out.println((i+1) + ". " + arr[i]);
                }
            } else {
                System.out.println("added: " + str);
                arr[nextIndex++] = str;
            }
        }
        
    }
}
