package duke;

import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    public static void main(String[] args) {
        Ui.intro();
        Scanner scan = new Scanner(System.in);
        TaskList tasks = new TaskList();

        String input = scan.nextLine();
        while(!input.equals("bye")) {
            try {
                Parser.parseRawString(input, tasks);            
            } catch(IllegalArgumentException e) {
                Ui.invalidCommand();
            } catch(IndexOutOfBoundsException e) {
                Ui.missingArgs();
            }
            input = scan.nextLine();
        }
        Ui.close();
        scan.close();
    }
}
