package duke;

import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Ui.intro();
        Scanner scan = new Scanner(System.in);
        TaskList tasks = new TaskList();

        String input = scan.nextLine();
        while(!input.equals("bye")) {
            try {
                Parser.parseRawString(input, tasks);            
            } catch(IllegalArgumentException exception) {
                Ui.invalidCommand();
            } catch(IndexOutOfBoundsException exception) {
                Ui.missingArgs();
            }
            input = scan.nextLine();
        }
        Ui.close();
        scan.close();
    }
}
