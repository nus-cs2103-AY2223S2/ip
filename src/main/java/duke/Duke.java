package duke;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import java.util.Scanner;

import gui.DialogBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import storage.Storage;
import parser.Parser;
import tasklist.TaskList;
import ui.Ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Region;
import javafx.scene.control.Label;

public class Duke {

    Storage storage;
    public Ui ui;
    TaskList list;
    Parser parser;
    final static String DEFAULT_PATH = System.getProperty("user.dir") + File.separator + "data" + File.separator + "duke.txt";

    public Duke(String path) throws IOException {
        this.ui = new Ui();
        this.storage = new Storage(path);
        this.list = this.storage.load();
        this.parser = new Parser();
    }

    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage(DEFAULT_PATH);
        this.list = this.storage.load();
        this.parser = new Parser();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return this.parser.parse(input, this.ui, this.list, this.storage);
    }


}






