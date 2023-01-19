import java.util.Scanner;
import java.util.ArrayList;
public class Duke {

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Scanner sc = new Scanner(System.in);
        toDoList toDo = new toDoList();

        // Level 1
        System.out.println("Hello I'm Duke \nWhat can I do for you?");
        String input = sc.nextLine();

        while(!input.equals("bye")) {
            //Level 2
            if(input.equals("list")) {
                toDo.printList();
            } else {
                toDo.addItem(input);
            }

            input = sc.nextLine();
        }

        sc.close();
    }
}
