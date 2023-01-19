import java.util.*;
public class Duke {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("____________________________________________________________");
        System.out.println("Hello hello! I'm Panav");
        System.out.println("Whatsup bro");
        System.out.println("____________________________________________________________");

        Task arr[] = new Task[100];
        String word = sc.nextLine();
        int counter = 0;
        while(word.compareTo("bye") != 0) {
            Task t = new Task(word);
            int len = word.length();
            if (word.compareTo("list") == 0) {
                System.out.println("____________________________________________________________");
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < counter; i++) {
                    System.out.println((i+1) + ". [" + arr[i].getStatusIcon() + "] " + arr[i]);
                }
                System.out.println("____________________________________________________________");
            } else if(word.charAt(0) == 'm' && Character.isDigit(word.charAt(len-1))) {
                int num = Integer.parseInt(String.valueOf(word.charAt(len-1)));
                System.out.println("____________________________________________________________");
                System.out.println("Nice! I've marked this task as done:");
                Task cur = arr[num-1];
                cur.markAsDone();
                System.out.println("[" + cur.getStatusIcon() + "] " + arr[num-1]);
                System.out.println("____________________________________________________________");
            } else if(word.charAt(0) == 'u' && Character.isDigit(word.charAt(len-1))) {
                int num = Integer.parseInt(String.valueOf(word.charAt(len-1)));
                System.out.println("____________________________________________________________");
                System.out.println("OK, I've marked this task as not done yet:");
                Task cur = arr[num-1];
                cur.markAsNotDone();
                System.out.println("[" + cur.getStatusIcon() + "] " + arr[num - 1]);
                System.out.println("____________________________________________________________");
            } else {
                System.out.println("____________________________________________________________");
                System.out.println("added: " + word);
                System.out.println("____________________________________________________________");
                arr[counter] = t;
                counter++;
            }
            word = sc.nextLine();
        }
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}
