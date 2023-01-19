import java.util.Scanner;
import java.util.ArrayList;

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
        initiateToDo();
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


    public static void initiateToDo() {
        System.out.println("DukeyList: Type something and Dukey will add it to the list!! Type 'list' to view the list! Type 'bye' to exit!! ");
        ItemList itemList = new ItemList();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.next();
            if (input.equals("mark")) {
                int itemNumber = scanner.nextInt() - 1;
                itemList.mark(itemNumber);
                continue;
            }
            if (input.equals("unmark")) {
                int itemNumber = scanner.nextInt() - 1;
                itemList.unmark(itemNumber);
                continue;
            }
            input = input + scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println("DukeyList: " + BYE_MESSAGE);
                break;
            }
            if (input.equals("list")) {
                System.out.println("DukeyList:");
                itemList.readList();
                continue;
            }


            itemList.addItem(new Item(input));


        }

    }






}
