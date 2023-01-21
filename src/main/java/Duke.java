import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private final ArrayList<String> storage = new ArrayList<>();

    private final Scanner sc = new Scanner(System.in);

    public void greet() {
        String logo = " ____        _        \n"
        + "|  _ \\ _   _| | _____ \n"
        + "| | | | | | | |/ / _ \\\n"
        + "| |_| | |_| |   <  __/\n"
        + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo + "Hello! I'm Duke\nWhat can I do for you?");
        separator();
    }

    public void exit() {
        separator();
        System.out.println("\tBye. Hope to see you again soon!");
        separator();
        System.exit(0);
    }

    public void list() {
        separator();
        for (int i=0; i < this.storage.size(); i++) {
            int currentNumber = i+1;
            String currentList = storage.get(i);
            System.out.println("\t" + currentNumber + ". " + currentList);

        }
        separator();
    }

    public void add(String str) {
        storage.add(str);
        separator();
        System.out.println("\tadded: " + str );
        separator();
    }

    public static void separator() {
        System.out.println("---------------------------------------------------------------");
    }


    public static void main(String[] args) {
        Duke addressBook = new Duke();
        addressBook.greet();
        while (addressBook.sc.hasNextLine()) {
            String str = addressBook.sc.nextLine();
            if (str.equals("bye")) {
                addressBook.exit();
            } else if (str.equals("list")) {
                addressBook.list();
            } else {
                addressBook.add(str);
            }
        }
    }

}
