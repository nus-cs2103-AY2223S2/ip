import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    
    
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello i'm\n" + logo);
        System.out.println("What can i do for you?");
        Scanner scan = new Scanner(System.in);
        String input = scan.next();
        ArrayList<Task> list = new ArrayList<Task>(); 
        int counter = 0;

        while (!input.equals("bye")){

            if (input.equals("mark")){
                int index = scan.nextInt();
                Task t = list.get(index - 1);
                t.markAsDone();
                System.out.println( "Nice! I've marked this task as done:\n" + t.getTask());
                input = scan.next();
                continue;
            }

            if (input.equals("unmark")){
                int index = scan.nextInt();
                Task t = list.get(index - 1);
                t.markAsNotDone();
                System.out.println( "OK, I've marked this task as not done yet:\n" + t.getTask());
                input = scan.next();
                continue;
            }

            if (input.equals("list")){
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < counter; i++) {
                    System.out.println((i + 1) + "." + list.get(i).getTask());
                }
                input = scan.next();
                continue;
            }

            
            input = input + " " + scan.next();
            list.add(new Task(input));
            counter = counter + 1;
            System.out.println(" added: " + input);
            input = scan.next();
        }

        System.out.println("Bye. Hope to see you again!");
    }
}
