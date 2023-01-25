import data.MyData;
import exceptions.DukeException;
import parser.Parser;
import commands.Command;
import ui.Ui;

import java.io.IOException;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        MyData data = new MyData();
        Ui ui = new Ui();
        try {
            data.loadData();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        Parser parser = new Parser(data);
        Ui.display();

        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String command = sc.nextLine();
            try {
                Command parsed = parser.parse(command);
                parsed.execute(data, ui);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
            if (command.equals("bye")) {
                break;
            }
        }
        sc.close();
    }
}