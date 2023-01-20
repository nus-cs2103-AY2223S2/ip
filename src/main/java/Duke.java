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
            //echo(input);

            switch (input) {
                case "bye":
                    echo(input);
                    break loop;

                case "list":
                    store.read();
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
    int size;

    memory() {
        store = new ArrayList<>(100);
        size = 100;
        slots++;
    }

    memory(int size) {
        store = new ArrayList<>(size);
        slots++;
    }

    void write(String input) {
        store.add(input);
        System.out.println("added: " + input);
    }

    void read() {
        for (int x = 0; x < store.size(); x++)
            System.out.println(x + 1 + ". " + store.get(x));

    }

}
