import java.util.Scanner;
public class Duke {
    private static int index;
    private static Task[] list = new Task[100];

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");

        while(true){
            Scanner reader = new Scanner(System.in);  // Reading from System.in
            System.out.println("Enter a Command!: ");
            String s = reader.nextLine();
            String[] spStg = s.split(" ");

            if(spStg[0].equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }

            else if(spStg[0].equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < index; i++) {
                    System.out.println(i+1 + ". " + list[i].toString());
                }
            }

            else if(spStg[0].equals("mark")) {
                int i =  Integer.parseInt(spStg[1]) - 1;
                list[i].mark();
                System.out.println("Nice! I've marked this task as done:\n" + list[i]);
            }

            else if(spStg[0].equals("unmark")) {
                int i =  Integer.parseInt(spStg[1]) - 1;
                list[i].unmark();
                System.out.println("OK, I've marked this task as not done yet:\n" + list[i]);
            }

            else {
                list[index] = new Task(s);
                index++;
                System.out.println("added: " + s);
            }
        }
    }
}
