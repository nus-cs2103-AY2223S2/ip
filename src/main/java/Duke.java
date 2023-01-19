import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String[] tasks = new String[100];
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
                for (int j = 0; tasks[j]!=null && j < 100; j++ ){
                    int k = j+1;
                    System.out.println(k +". "+ tasks[j]);
                }
                System.out.println("------------------------------------------------------------------------------");
            }
            else {
                tasks[i] = cur;
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
