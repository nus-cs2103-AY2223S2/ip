import java.util.Scanner;

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

        System.out.println("Hello I'm Duke \nWhat can I do for you?");
        String input = sc.nextLine();

        while(!input.equals("bye")) {

            String[] instruction = input.split(" ",2);

            if(instruction[0].equals("list")) {

                toDo.printList();

            } else if (instruction[0].equals("mark")){
                toDo.changingStatus(0, Integer.parseInt(instruction[1]));

            } else if (instruction[0].equals("unmark")) {
                toDo.changingStatus(1, Integer.parseInt(instruction[1]));
            } else {
                toDo.addItem(instruction[0] + " " + instruction[1]);
            }

            input = sc.nextLine();
        }

        sc.close();
    }
}
