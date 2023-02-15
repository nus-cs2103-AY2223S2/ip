import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Duke {
    public static final String BYE_MESSAGE = "Goodbye!! Please return to Dukey again soon!! :)";

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Welcome welcome!");
        initiateDukeyList();
        //echo();
    }


    public static void echo() {
        System.out.println("Start by typing something and Dukey will echo!! Type bye to exit!! ");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                break;
            }
            System.out.println("Dukey: " + input);
            if (input.equals("Bye")) {
                System.out.println("Dukey: Did you mean 'bye'? Type 'bye' to exit!!");
            }
        }
        System.out.println("\nDukey: " + BYE_MESSAGE);
        scanner.close();

    }


    public static void initiateDukeyList() {
        //System.out.println("DukeyList: Type something and Dukey will add it to the list!! Type 'list' to view the list! Type 'bye' to exit!! ");
        ItemList.printInstruction();
        ItemList itemList = new ItemList();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("_________________________________________________________");
            System.out.print("Dukey command: ");
            String input = scanner.nextLine();
            //add a new todo
            if (input.strip().equals("todo")) {
                try {
                    itemList.addItem(ToDo.createToDo(scanner));
                } catch(DukeyException e) {
                    e.printMessage();
                }
                continue;
            }

            //add a new deadline
            if (input.strip().equals("deadline")) {
                try {
                    itemList.addItem(Deadlines.createDeadline(scanner));
                } catch (DukeyException e) {
                    e.printMessage();
                }
                continue;
            }

            //add a new event
            if (input.strip().equals("event")) {
                try {
                    itemList.addItem(Event.createEvent(scanner));
                } catch (DukeyException e) {
                    e.printMessage();
                }
                continue;
            }

            //mark
            if (input.strip().equals("mark")) {
                try {
                    itemList.mark(scanner);
                } catch (DukeyException e) {
                    e.printMessage();
                }
                continue;
            }

            //unmark
            if (input.strip().equals("unmark")) {
                try {
                    itemList.unmark(scanner);
                } catch (DukeyException e) {
                    e.printMessage();
                }
                continue;
            }

            //delete
            if (input.equals("delete")) {
                try {
                    itemList.delete(scanner);
                } catch (DukeyException e) {
                    e.printMessage();
                }
                continue;
            }

            //exit
            if (input.equals("bye")) {
                System.out.println("DukeyList: " + BYE_MESSAGE);
                break;
            }

            //list
            if (input.equals("list")) {
                itemList.readList();
                continue;
            }

            System.out.println("Error! Unknown command. Try again!");

        }
        scanner.close();

    }






}
