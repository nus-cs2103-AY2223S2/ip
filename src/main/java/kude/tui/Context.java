package kude.tui;

import kude.DukeException;
import kude.models.Item;
import kude.models.ItemList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

public class Context {
    private final Parser parser;
    private final ItemList items;
    private final Output output;
    private final Processor processor;
    private final DateTimeFormatter parseDateTimeFormat;

    public Context(Parser parser, ItemList items, Output output, Processor processor) {
        this.parser = parser;
        this.items = items;
        this.output = output;
        this.processor = processor;
        this.parseDateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    }

    public Parser getParser() {
        return parser;
    }

    public ItemList getItems() {
        return items;
    }

    public Output getOutput() {
        return output;
    }

    public void notifyAdded(Item item) {
        notifyMutated();
        output.writeLine("Added " + item);
        output.writeLine("List now contains " + items.list().count() + " items");
    }

    public void notifyDeleted(Item item) {
        notifyMutated();
        output.writeLine("Deleted " + item);
        output.writeLine("List now contains " + items.list().count() + " items");
    }

    public void notifyMutated() {
        processor.saveItems();
    }

    public String getArg(String provideName) {
        return parser.getArg().orElseThrow(() -> new DukeException("Provide " + provideName));
    }

    public Integer getIndexArg() {
        var arg = getArg("index");
        var index = parseInt(arg)
                .orElseThrow(() -> new DukeException("Index must be integer"));
        return index - 1;
    }

    public String getNamedArg(String name, String provideName) {
        return parser.getNamedArg(name).orElseThrow(() -> new DukeException("Provide " + provideName));
    }

    public LocalDateTime getNamedDateTimeArg(String name, String provideName) {
        return parseDateTime(getNamedArg(name, provideName)).orElseThrow(() ->
                new DukeException("Invalid format for " + provideName + ". Use `2023-02-25 23:00`"));
    }

    public Item getItem(int index) {
        return items.get(index).orElseThrow(() -> new DukeException("Invalid index"));
    }

    private Optional<Integer> parseInt(String s) {
        try {
            return Optional.of(Integer.parseInt(s));
        } catch (NumberFormatException nfe) {
            return Optional.empty();
        }
    }

    private Optional<LocalDateTime> parseDateTime(String s) {
        try {
            return Optional.of(LocalDateTime.parse(s, parseDateTimeFormat));
        } catch (DateTimeParseException nfe) {
            return Optional.empty();
        }
    }
}
