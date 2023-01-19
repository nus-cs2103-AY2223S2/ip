import java.util.Scanner;

/** Duke chat bot.
 * @author Hee Jia Yuan
 */
public class Duke {
    /**
     * Runs the main chat program.
     * @param args
     */
    public static void main(String[] args) {
        greet();
        Scanner sc = new Scanner(System.in);
        int storedItemsCount = 0;
        String[] storedItems = new String[100];

        while (sc.hasNextLine()) {
            String userInput = sc.nextLine();
            if (userInput.equals("bye")) {
                respond("Goodbye! Have a nice day ahead.\n");
                break;
            } if (userInput.equals("list")) {
                listItems(storedItems, storedItemsCount);
            } else {
                respond("Added: " + userInput);
                storeItems(storedItems, storedItemsCount, userInput);
                storedItemsCount++;
            }
        }
    }

    /**
     * Greets the user.
     */
    public static void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "\n" + "How may I be of assistance today? :)");
    }

    /**
     * Responds to the user, given a message.
     * @param message A String which will be the bot's response message.
     */
    public static void respond(String message) {
        String topDivider = "~~~~~~~~~~~~~~~~o~~~~~~~~~~~~~~~~\n" + "Duke's Response: \n";
        String botDivider = "\n~~~~~~~~~~~~~~~~o~~~~~~~~~~~~~~~~";
        System.out.println(topDivider + "\n" + message + botDivider);
    }

    /**
     * Lists all the items stored in the array.
     * @param storedItems The array storing the items.
     * @param count The number of items stored in the array,
     */
    public static void listItems(String[] storedItems, int count) {
        String topDivider = "~~~~~~~~~~~~~~~~o~~~~~~~~~~~~~~~~\n" + "Duke's Response: \n";
        String botDivider = "\n~~~~~~~~~~~~~~~~o~~~~~~~~~~~~~~~~";
        System.out.println(topDivider);

        for (int i = 0; i < count; i++) {
            int itemNumber = i + 1 ;
            System.out.println(itemNumber + ") " + storedItems[i]);
        }

        System.out.println(botDivider);
    }

    /**
     * Stores a new item in storage.
     * @param storedItems The storage array.
     * @param storedItemsCount Number of items currently stored in the array.
     * @param item The new item to store in the array.
     */
    public static void storeItems(String[] storedItems, int storedItemsCount, String item) {
        storedItems[storedItemsCount] = item;
    }

}
