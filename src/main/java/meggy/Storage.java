package meggy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

import meggy.exception.Function;
import meggy.exception.MeggyException;
import meggy.task.UserTask;

/** Save cross-session data in file. */
public class Storage {
    /** From "command" in a data line to the type of {@link UserTask} to be created. */
    public static final Map<String, Function<String, UserTask>> DATA_ENTRY_TO_TASK = Map.of(
            Resource.CMD_TODO, Util.TODO_NEW,
            Resource.CMD_DDL, Util.DDL_NEW,
            Resource.CMD_EVENT, Util.EVENT_NEW
    );
    public final File dataFile;

    /**
     * @param dataFile Non-null. The data file to read from and write to. If not exist, it will be created upon first
     *                 write operation.
     */
    public Storage(File dataFile) {
        this.dataFile = dataFile;
        assert dataFile != null;
    }

    /**
     * Write the content of the entire {@code tasks} list into data file. Creates data file if it did not previously
     * exist.
     *
     * @param tasks Non-null. The task list to take snapshot.
     * @throws MeggyException If file IO throws {@link IOException}.
     */
    public void save(ArrayList<UserTask> tasks) throws MeggyException {
        final FileWriter fw;
        try {
            dataFile.createNewFile();
            fw = new FileWriter(dataFile, false);
        } catch (IOException e) {
            throw new MeggyException(Resource.ERR_FILE_WRITE + Resource.ERR_IO);
        } catch (SecurityException e) {
            throw new MeggyException(Resource.ERR_FILE_WRITE + Resource.ERR_NO_FILE_ACCESS);
        }
        try {
            for (UserTask t : tasks) {
                fw.write(t.recreateCmd() + '\n');
            }
            fw.flush();
        } catch (IOException e) {
            throw new MeggyException(Resource.ERR_FILE_WRITE + Resource.ERR_IO);
        } finally {
            try {
                fw.close();
            } catch (IOException ignored) {
            } // We can't do anything if file close fails.
        }
    }

    /**
     * Reads content of file into the {@code tasks} list. If the file does not exist, process is skipped. Otherwise, all
     * lines with syntax error are skipped.
     * <p>
     * All file {@link IOException}s are ignored as if the file did not exist.
     *
     * @param tasks Non-null. The task list to load in data.
     */
    public void load(ArrayList<UserTask> tasks) {
        assert tasks != null;
        final Scanner fileIn;
        try {
            fileIn = new Scanner(dataFile);
        } catch (FileNotFoundException e) {
            return;
        }
        while (fileIn.hasNextLine()) {
            final Parser.JobAndArg<UserTask> jobAndArg = Parser.parseJobAndArg(DATA_ENTRY_TO_TASK, fileIn.nextLine());
            final Function<String, UserTask> taskNew = jobAndArg.job;
            if (taskNew != null) { // Command recognized
                try {
                    tasks.add(taskNew.apply(jobAndArg.args));
                } catch (MeggyException ignored) {
                } // If parsing fails for at a line, that line is skipped.
            }
        }
        fileIn.close();
    }
}
