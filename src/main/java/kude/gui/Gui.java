package kude.gui;

import java.util.ArrayList;
import java.util.List;

import kude.processor.Ui;

/**
 * Abstract representation of a Graphical UI
 */
public class Gui implements Ui {
    private final List<String> outputLines;
    private final List<String> errorLines;

    /**
     * Initialize a new GUI
     */
    public Gui() {
        this.outputLines = new ArrayList<>();
        this.errorLines = new ArrayList<>();
    }

    @Override
    public void writeLine(String line) {
        writeLine(line, 1);
    }

    @Override
    public void writeLine(String line, int indent) {
        outputLines.add(String.format("<%s%s%n", "\t".repeat(indent), line));
    }

    @Override
    public void writeError(String line) {
        writeError(line, 1);
    }

    @Override
    public void writeError(String line, int indent) {
        errorLines.add(String.format("!%s%s%n", "\t".repeat(indent), line));
    }

    /**
     * Gets the response from this UI as a string
     */
    public String getResponse() {
        var list = outputLines;
        if (!errorLines.isEmpty()) {
            list = errorLines;
        }
        var ret = String.join("\n", list);
        outputLines.clear();
        errorLines.clear();
        return ret;
    }
}
