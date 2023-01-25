package Duke;

import java.io.*;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import Command.Command;
import Exception.*;

public class Duke {
    private Storage storage;
    private DukeList dukeList;
    private Ui ui;

    public Duke (String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.dukeList = storage.retrieveList();
    }

    public void run() {
        Parser parser = new Parser(this.dukeList, this.storage);
        boolean isExit = false;
        while(!isExit) {
            try {
                String stringCommand = ui.readCommand();
                Command c = parser.parse(stringCommand);
                c.execute();
                isExit = c.isExit();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public static void main(String[] args) throws EmptyDescriptionException, UnknownInputException {
        new Duke("./data/Duke.Duke.DukeList.ser").run();
    }
}

