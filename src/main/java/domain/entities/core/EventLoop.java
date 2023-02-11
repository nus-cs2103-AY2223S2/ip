package domain.entities.core;

import core.exceptions.DisposableException;

/**
 * This shall serve as the base for implementing an event loop. An event loop
 * loops forever unless the executable in it returns ExitStatus.terminate.
 * Also, when the loop terminates, it will call the dispose method, which
 * will dispose the things that are registered to the disposables.
 */
public abstract class EventLoop implements Disposable {
    /**
     * The root executable that shall be executed in the loop.
     */
    private final Commandable rootCommandable;
    /**
     * The StringReadable that shall be used for providing the next line of
     * input to the event loop.
     */
    private final StringReadable reader;
    /**
     * The writer for writing the error messages.
     */
    private final Writable errorWriter;

    public EventLoop(Commandable rootCommandable, StringReadable reader,
                     Writable errorWriter) {
        this.rootCommandable = rootCommandable;
        this.reader = reader;
        this.errorWriter = errorWriter;
    }

    /**
     * Returns the tokens from the string.
     *
     * @param string the string to get the tokens from.
     */
    public static String[] getTokens(String string) {
        return string.trim().split(" ");
    }

    /**
     * Starts the event loop.
     */
    public void run() {
        while (reader.hasNextLine()) {
            String command = reader.nextLine();
            ExitStatus status = rootCommandable.execute(getTokens(command));
            if (status == ExitStatus.terminate) {
                break;
            }
        }
        dispose();
    }

    public ExitStatus runWithCommand(String command) {
        return rootCommandable.execute(getTokens(command));
    }

    @Override
    public void dispose() {
        if (rootCommandable instanceof Disposable) {
            try {
                ((Disposable) rootCommandable).dispose();
            } catch (DisposableException e) {
                errorWriter.writeln("Failed to dispose the root " +
                        "executable: " + e.getMessage());
            }
        }
        if (reader instanceof Disposable) {
            try {
                ((Disposable) reader).dispose();
            } catch (DisposableException e) {
                errorWriter.writeln("Failed to close the reader: "
                        + e.getMessage());
            }
        }
    }
}
