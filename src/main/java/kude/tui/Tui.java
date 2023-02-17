package kude.tui;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import kude.processor.Ui;

/**
 * User interface abstraction for the terminal.
 */
public class Tui implements Ui {
    private final PrintStream out;
    private final Scanner scanner;

    /**
     * Create a new terminal UI abstraction
     * @param in Input terminal stream
     * @param out Output terminal stream
     */
    public Tui(InputStream in, PrintStream out) {
        this.scanner = new Scanner(in);
        this.out = out;
    }

    /**
     * Returns the current command line of input
     */
    public String getCommandLine() {
        return scanner.nextLine();
    }

    /**
     * Writes a welcome message
     */
    public void writeWelcome() {
        out.println("<<<\tWelcome to Kude!");
    }

    /**
     * Writes a goodbye message
     */
    public void writeBye() {
        out.println("<<<\tBye");
    }

    /**
     * Writes a prompt
     */
    public void writePrompt() {
        out.print("> ");
    }

    /**
     * Writes a line of text
     */
    @Override
    public void writeLine(String line) {
        writeLine(line, 1);
    }

    /**
     * Writes a line of text with indentation
     */
    @Override
    public void writeLine(String line, int indent) {
        out.printf("<%s%s%n", "\t".repeat(indent), line);
    }

    /**
     * Writes a line of text as error
     */
    @Override
    public void writeError(String line) {
        writeError(line, 1);
    }

    /**
     * Writes a line of text as error as indentation
     */
    @Override
    public void writeError(String line, int indent) {
        out.printf("!%s%s%n", "\t".repeat(indent), line);
    }
}
