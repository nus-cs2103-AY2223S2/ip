package kude.tui;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Ui {
    private final PrintStream out;
    private final Scanner scanner;

    public Ui(InputStream in, PrintStream out) {
        this.scanner = new Scanner(in);
        this.out = out;
    }

    public String getCommandLine() {
        return scanner.nextLine();
    }

    public void writeWelcome() {
        out.println("<<<\tWelcome to Kude!");
    }

    public void writeBye() {
        out.println("<<<\tBye");
    }

    public void writePrompt() {
        out.print("> ");
    }

    public void writeLine(String line) {
        writeLine(line, 1);
    }

    public void writeLine(String line, int indent) {
        out.printf("<%s%s%n", "\t".repeat(indent), line);
    }

    public void writeErr(String line) {
        writeErr(line, 1);
    }

    public void writeErr(String line, int indent) {
        out.printf("!%s%s%n", "\t".repeat(indent), line);
    }
}
