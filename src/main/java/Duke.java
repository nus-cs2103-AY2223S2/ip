import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Task[] tasks = new Task[100];
        Scanner sc= new Scanner(System.in);
        System.out.println("------------------------------------------------------------------------------");
        System.out.println("Hello! I'm Duke\n" +
                "What can I do for you?");
        System.out.println("------------------------------------------------------------------------------");
        String cur = sc.nextLine();
        int i = 0;
        while(!cur.equals("bye")) {
            if (cur.equals("list")) {
                System.out.println("------------------------------------------------------------------------------");
                for (int j = 0; tasks[j] != null && j < 100; j++ ){
                    int k = j + 1;
                    System.out.println(k +".["+ tasks[j].getStatusIcon()+"] " + tasks[j].getDescription());
                }
                System.out.println("------------------------------------------------------------------------------");
            }
            else if (cur.split(" ")[0].equals("mark")) {
                int index = Integer.valueOf(cur.split(" ")[1]);
                tasks[index-1].markAsDone();
                System.out.println("------------------------------------------------------------------------------");
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  ["+tasks[index-1].getStatusIcon()+"] " + tasks[index-1].getDescription());
                System.out.println("------------------------------------------------------------------------------");
            }
            else if (cur.split(" ")[0].equals("unmark")) {
                int index = Integer.valueOf(cur.split(" ")[1]);
                tasks[index-1].markAsUnDone();
                System.out.println("------------------------------------------------------------------------------");
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("  ["+tasks[index-1].getStatusIcon()+"] " + tasks[index-1].getDescription());
                System.out.println("------------------------------------------------------------------------------");
            }
            else {
                tasks[i] = new Task(cur);
                System.out.println("------------------------------------------------------------------------------");
                System.out.println("added:"+" "+tasks[i]);
                System.out.println("------------------------------------------------------------------------------");
                i += 1;
            }

            cur = sc.nextLine();
        }
        System.out.println("------------------------------------------------------------------------------");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("------------------------------------------------------------------------------");
    }
}
