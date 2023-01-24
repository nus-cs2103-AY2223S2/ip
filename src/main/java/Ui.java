import java.util.Scanner;

public class Ui {

    private Storage storage;
    private TaskList tl;

    Ui () {};

    public void setContext(Storage storage, TaskList tl) {
        this.storage = storage;
        this.tl = tl;
    }

    public void acceptInput() {

        // Print welcome message
        System.out.println("Hello! I'm Duke\n What can I do for you?");

        // Create Scanner and Parser object
        Scanner sc = new Scanner(System.in);
        Parser p = new Parser(tl, storage);

        // Always ready to receive input
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            p.send(input);
        }
    }
}
