package kude.tui;

import java.io.PrintStream;

public class Output {
    private final PrintStream stream;

    public Output(PrintStream stream) {
        this.stream = stream;
    }

    public void writeLine(String line) {
        writeLine(line, 1);
    }

    public void writeLine(String line, int indent) {
        stream.printf("<%s%s%n", "\t".repeat(indent), line);
    }

    public void writeErr(String line) {
        writeErr(line, 1);
    }

    public void writeErr(String line, int indent) {
        stream.printf("!%s%s%n", "\t".repeat(indent), line);
    }
}
