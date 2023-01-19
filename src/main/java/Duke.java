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

            if(instruction[0].equals("list")) { // printing list
                toDo.printList();
            } else if (instruction[0].equals("mark")){ //marking
                toDo.changingStatus(0, Integer.parseInt(instruction[1]));

            } else if (instruction[0].equals("unmark")) { //unmarking
                toDo.changingStatus(1, Integer.parseInt(instruction[1]));
            } else { // adding into list
                System.out.println("Got it. I've added this task:");
                if(instruction[0].equals("todo")) {
                    toDo.addItem("T", instruction[1]);
                } else if (instruction[0].equals("deadline")){
                    String itemANDtime[] = instruction[1].split("/");
                    toDo.addItemDeadline("D", itemANDtime[0], itemANDtime[1]);
                } else {
                    String itemANDtime[] = instruction[1].split("/");
                    toDo.addItemEvent("E", itemANDtime[0], itemANDtime[1], itemANDtime[2]);
                }

                System.out.println("Now you have " + toDo.numberOfTask() + " tasks in the list.");

            }

            input = sc.nextLine();
        }

        sc.close();
    }
}
