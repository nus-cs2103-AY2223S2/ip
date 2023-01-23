package kude;

import kude.tui.Processor;
import kude.tui.Ui;

public class Duke {

    public static void main(String[] args) {
        var storage = new Storage("./duke.txt");
        var ui = new Ui(System.in, System.out);
        var processor = new Processor(ui, storage);
        processor.run();
    }
}
