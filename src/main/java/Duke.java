import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");

        Task[] arr = new Task[100];
        int nextIndex = 0;

        while (true) {
            Scanner bucky = new Scanner(System. in);
            String str = bucky.nextLine();
            String s[] = str.split(" ", 2);
            String firstWord = s[0];

            if (str.equals("bye")) {
                System.out.println ("Bye. Hope to see you again soon!");
                break;
            } else if (str.equals("list")) {
                for (int i = 0 ; i < nextIndex ; i++) {
                    System.out.println((i+1) + ". [" + arr[i].getStatusIcon() + "] " + arr[i]);
                }
            } else if (firstWord.equals("mark")) {
                int num = Integer.parseInt(s[1]) - 1;
                arr[num].markAsDone();
                System.out.println("Nice! I've marked this task as done:\n[" + arr[num].getStatusIcon() + "] " + arr[num]);
            } else if (firstWord.equals("unmark")) {
                int num = Integer.parseInt(s[1]) - 1;
                arr[num].unmark();
                System.out.println("OK, I've marked this task as not done yet:\n[" + arr[num].getStatusIcon() + "] " + arr[num]);
            } else {
                System.out.println("added: " + str);
                arr[nextIndex++] = new Task(str);
            }
        }
        
    }
}
