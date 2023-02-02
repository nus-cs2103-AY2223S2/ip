package pix;

import java.io.IOException;

import javafx.application.Platform;
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
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        MyData data = new MyData();
        Ui ui = new Ui();
        Parser parser = new Parser(data);

        if (input.equals("bye")) {
            Platform.exit();
        }

        try {
            data.loadData();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        try {
            Command parsed = parser.parse(input);
            return parsed.execute(data, ui);
        } catch (PixException e) {
            return e.getMessage();
        }
    }
}

