package kude;

import kude.tui.Processor;
import kude.models.ItemList;

public class Duke {

    public static void main(String[] args) {
        var items = new ItemList();
        var processor = new Processor(System.in, System.out, items);
        processor.run();
    }
}
