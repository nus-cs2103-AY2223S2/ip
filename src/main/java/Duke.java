import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hi! I'm Samantha\nHow can I help?");
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        String[] tasks = new String[100];
        int taskCounter = 0;
        while (!s.equals("bye")) {
            if (s.equals("list")) {
                if (tasks[0] == null) {
                    System.out.println("    You have no tasks");
                } else {
                    for (int i = 0; i < taskCounter; i++) {
                        System.out.println("    " + (i + 1) + ". " + tasks[i]);
                    }
                }
            } else {
                tasks[taskCounter] = s;
                taskCounter++;
                System.out.println("    added: " + s);
            }
            s = sc.nextLine();
        }
        System.out.println("    Bye. Hope to see you soon!");
    }
}
