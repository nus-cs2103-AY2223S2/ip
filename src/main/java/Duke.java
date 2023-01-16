import parser.Parser;
import ui.Ui;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Parser parser = new Parser();
        Ui.display();

        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String command = sc.nextLine();
            parser.parse(command);
            if (command.equals("bye")) {
                break;
            }
        }
        sc.close();
    }
}