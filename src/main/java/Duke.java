import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        var reader = new Scanner(System.in);
        var writer = System.out;
        var items = new ArrayList<String>();

        writer.println("Hello, I'm Kude!");

        while (true) {
            writer.print("> ");
            var cmd = reader.nextLine();

            if (cmd.equals("list")) {
                for (int i = 0; i < items.size(); i++) {
                    writer.println("< " + (i + 1) + ". " + items.get(i));
                }
            }
            else if (cmd.equals("bye")) {
                writer.println("< Bye!!");
                return;
            } else {
                items.add(cmd);
                writer.println("< added: " + cmd);
            }
        }
    }
}
