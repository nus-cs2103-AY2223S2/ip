package presentation.ui;

import core.exceptions.DisposableException;
import domain.entities.core.Disposable;
import domain.entities.core.StringReadable;

import java.util.Scanner;

public class SystemIn implements StringReadable, Disposable {
    public SystemIn() {
        this.scanner = new Scanner(System.in);
    }

    private final Scanner scanner;

    @Override
    public String nextLine() {
        return scanner.nextLine();
    }

    @Override
    public void dispose() {
        scanner.close();
    }
}
