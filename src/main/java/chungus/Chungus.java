package chungus;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Chungus {
    private Ui ui;
    private TaskList tasks;
    private Storage db;
    private boolean isRunning;

    private static final String defaultDbPath = System.getProperty("user.dir") + "/chungus.db";

    public static void main(String[] args) {
        new Chungus(System.in, System.out, defaultDbPath).spin();
    }

    public Chungus(InputStream in, OutputStream out, String dbPath) {
        ui = new Ui(in, out);
        tasks = new TaskList();

        isRunning = true;

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

        ui.info("Hello, I'm Chungus.");
        ui.info("What can I do for you?\n");
    }

    public void spin() {
        while (isRunning) {
            ui.print("chungus> ");

            String cmd = ui.nextLine();
            parseAndExec(cmd);

            ui.print("\n"); // add an extra line
        }
    }

    private void parseAndExec(String cmd) {
        try {
            Handler handler = Parser.parse(cmd);
            boolean shouldExit = handler.handle(tasks, ui, db);
            // overwrite the database after every command
            db.write(tasks);
            isRunning = !shouldExit;
        } catch (TaskNotFoundException e) {
            ui.error("Could not find the requested task. You currently have exactly %d %s", tasks.count(),
                    tasks.count() == 1 ? "task" : "tasks");
            ui.error("Reason: %s", e.getMessage());
        } catch (ChungusException e) {
            ui.error("Could not handle command \"%s\".", cmd);
            ui.error("Reason: %s", e.getMessage());
        }
    }
}
