import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        String greeting = "Hello! I'm Alpha Beast What can I do for you?";
        greeting(greeting);
        memory store = new memory();


        loop:
        while (true) {
            String input = sc.nextLine();
            String[] tokens = input.split(" ");
            //echo(input);
            switch (tokens[0]) {
                case "bye":
                    echo(input);
                    break loop;

                case "list":
                    //store.read();
                    store.readBoth();
                    break;
                case "mark":
                    store.mark(Integer.parseInt(tokens[1]) - 1);
                    break;

                case "unmark":
                    store.unmark(Integer.parseInt(tokens[1]) - 1);
                    break;

                default:
                    store.write(input);
            }


        }
    }

    static void greeting(String message) {
        System.out.println(message);
    }

    static void echo(String input) {
        if (input.equals("bye"))
            System.out.println("Bye. Hope to see you again soon!\n");
        else
            System.out.println(input);
    }

}

class memory {

    static int slots = 0;
    ArrayList<String> store;
    ArrayList<Boolean> checkBox;
    int size;

    memory() {
        store = new ArrayList<>(100);
        checkBox = new ArrayList<>(100);
        size = 100;
        slots++;
    }

    memory(int size) {
        store = new ArrayList<>(size);
        checkBox = new ArrayList<>(size);
        slots++;
    }

    void write(String input) {
        store.add(input);
        checkBox.add(false);
        System.out.println("added: " + input);

    }

    void read() {
        for (int x = 0; x < store.size(); x++)
            System.out.println(x + 1 + ". " + store.get(x));

    }

    void readBoth() {
        for (int x = 0; x < store.size(); x++) {
            if (checkBox.get(x))
                System.out.println(x + 1 + ". " + "[X] " + store.get(x));
            else
                System.out.println(x + 1 + ". " + "[] " + store.get(x));
        }
    }

    void mark(int number) {
        checkBox.set(number, true);
        System.out.println("Nice! I've marked this task as done:\n" +
                "[X] " + store.get(number));
    }

    void unmark(int number) {
        checkBox.set(number, false);
        System.out.println("OK, I've marked this task as not done yet:\n" +
                "[] " + store.get(number));
    }


}
