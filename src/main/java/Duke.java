import java.util.Scanner;

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
        System.out.println("DukeyList: Type something and Dukey will add it to the list!! Type 'list' to view the list! Type 'bye' to exit!! ");
        ItemList itemList = new ItemList();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.next();

            //add a new todo
            if (input.equals("todo")) {
                itemList.addItem(new ToDo(scanner.nextLine()));
                continue;
            }

            //add a new deadline
            if (input.equals("deadline")) {
                String[] details = scanner.nextLine().split("/");
                itemList.addItem(new Deadlines(details[0], details[1]));
                continue;
            }

            //add a new event
            if (input.equals("event")) {
                String[] details = scanner.nextLine().split("/");
                itemList.addItem(new Event(details[0], details[1], details[2]));
                continue;
            }

            //mark
            if (input.equals("mark")) {
                int itemNumber = scanner.nextInt() - 1;
                itemList.mark(itemNumber);
                continue;
            }

            //unmark
            if (input.equals("unmark")) {
                int itemNumber = scanner.nextInt() - 1;
                itemList.unmark(itemNumber);
                continue;
            }

            //exit
            if (input.equals("bye")) {
                System.out.println("DukeyList: " + BYE_MESSAGE);
                break;
            }

            //list
            if (input.equals("list")) {
                System.out.println("DukeyList:");
                itemList.readList();
                continue;
            }

        }

    }






}
