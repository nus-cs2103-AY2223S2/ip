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
            String[] spStg = s.split(" ", 2);

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
                String firstWord = spStg[0];
                String rest = spStg[1];
                if (firstWord.equals("todo")) {
                    list[index] = new Todo(rest);
                }
                else if (firstWord.equals("deadline")) {
                    String[] sppStg = rest.split("/by");
                    list[index] = new Deadline(sppStg[0], sppStg[1]);
                }
                else if (firstWord.equals("event")) {
                    String[] sppStg = rest.split("/from|/to");
                    list[index] = new Event(sppStg[0], sppStg[1], sppStg[2]);
                }
                System.out.println("Got it. I've added this task:");
                System.out.println(list[index]);
                index++;
                System.out.println("Now you have " + index + " tasks in the list.");
            }
        }
    }
}
