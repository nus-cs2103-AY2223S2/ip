package chungus;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

import chungus.textui.TextUi;

/**
 * A task management app.
 */
public class Chungus {
    private NonBlockingUi ui;
    private TaskList tasks;
    private Storage db;
    private AtomicBoolean isRunning;

    public static final String DEFAULT_DB_PATH = System.getProperty("user.dir") + "/chungus.db";

    /**
     * This starts Chungus in the CLI, using stdin and stdout.
     * 
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        Chungus chungus = new Chungus(new TextUi(System.in, System.out), DEFAULT_DB_PATH);
        chungus.spin(() -> chungus.ui.print("chungus> "), () -> chungus.ui.print("\n"));
    }

    /**
     * A constructor for the Chungus class.
     * 
     * @param ui     Some user interface to use.
     * @param dbPath Path to a database file to read and write tasks from.
     * @throws RuntimeException For errors related to the database file.
     */
    public Chungus(NonBlockingUi ui, String dbPath) {
        this.ui = ui;
        tasks = new TaskList();

        isRunning = new AtomicBoolean(true);

        File dbFile = new File(dbPath);
        if (dbFile.exists() && !dbFile.isFile()) {
            // this means that the path represents a directory
            throw new RuntimeException(String.format("%s is not a file", dbPath));
        }
        db = new Storage(dbFile);
        try {
            if (dbFile.createNewFile()) {
                ui.info("Created a database file at %s\n", dbPath);
            } else {
                tasks = db.read();
                ui.info("Read %s task(s) from %s\n", tasks.count(), dbPath);
            }
        } catch (IOException e) {
            throw new RuntimeException(String.format("Failed to create/read db file %s", dbPath), e);
        }

        ui.info("Hello, I'm Chungus.\nWhat can I do for you?");
    }

    /**
     * Runs the Chungus app.
     * 
     * @param beforeEach A lambda to run before each command is handled.
     * @param afterEach  A lambda to run after each command is handled.
     */
    public void spin(Runnable beforeEach, Runnable afterEach) {
        ui.init(cmd -> parseAndExec(cmd), beforeEach, afterEach, isRunning);
        while (isRunning.get()) {
        }
    }

    /**
     * Wraps command parsing and handler execution with better error handling.
     * 
     * @param cmd The raw command to parse.
     */
    private void parseAndExec(String cmd) {
        try {
            Handler handler = Parser.parse(cmd);
            boolean shouldExit = handler.handle(tasks, ui, db);
            // overwrite the database after every command
            db.write(tasks);
            isRunning.set(!shouldExit);
        } catch (TaskNotFoundException e) {
            ui.error("Could not find the requested task. You currently have exactly %d %s.", tasks.count(),
                    tasks.count() == 1 ? "task" : "tasks");
        } catch (ChungusException e) {
            ui.error("Could not handle command \"%s\".\nReason: %s", cmd, e.getMessage());
        }
    }

    public void stop() {
        this.isRunning.set(false);
    }
}
