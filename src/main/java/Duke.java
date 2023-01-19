import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        var reader = new Scanner(System.in);
        var writer = System.out;

        writer.println("Hello, I'm Kude!");

        while (true) {
            writer.print("> ");
            var cmd = reader.nextLine();

            if (cmd.equals("bye")) {
                writer.println("< Bye!!");
                return;
            } else {
                writer.println("< " + cmd);
            }
        }
    }
}
