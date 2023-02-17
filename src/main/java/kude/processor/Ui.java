package kude.processor;

/**
 * User interface abstraction
 */
public interface Ui {
    void writeLine(String line);
    void writeLine(String line, int indent);
    void writeError(String line);
    void writeError(String line, int indent);
}
