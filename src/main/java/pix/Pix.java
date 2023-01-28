package pix;

import java.io.IOException;
import java.util.Scanner;

import pix.commands.Command;
import pix.data.MyData;
import pix.exceptions.PixException;
import pix.parser.Parser;
import pix.ui.Ui;

/**
 * Main Pix class which runs the logic of Pix.
 */
public class Pix {
    /**
     * Main function which runs Pix's logic.
     *
     * @param args Arguments provided.
     */
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
            } catch (PixException e) {
                System.out.println(e.getMessage());
            }
            if (command.equals("bye")) {
                break;
            }
        }
        sc.close();
    }
}

