package presentation.ui;

import java.util.Scanner;

import domain.entities.core.Disposable;
import domain.entities.core.StringReadable;

/**
 * The {@link StringReadable} wrapper for the {@link System#in} object.
 */
public class SystemIn implements StringReadable, Disposable {
    private final Scanner scanner;

    /**
     * Creates a new instance of the {@link SystemIn} class.
     */
    public SystemIn() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public String nextLine() {
        return scanner.nextLine();
    }

    @Override
    public boolean hasNextLine() {
        return scanner.hasNextLine();
    }

    @Override
    public void dispose() {
        scanner.close();
    }
}
