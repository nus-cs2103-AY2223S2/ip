package kude;

import kude.models.Deadline;
import kude.models.Event;
import kude.models.Todo;
import kude.tui.Processor;
import kude.models.ItemList;

public class Duke {

    public static void main(String[] args) {
        var storage = new Storage("./duke.txt");
        var processor = new Processor(System.in, System.out, storage);
        processor.run();
    }
}
