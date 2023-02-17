package meggy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import meggy.exception.Consumer;
import meggy.exception.MeggyException;


/** Save cross-session data in file. */
public class Storage {
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
    public void save(TaskList tasks) throws MeggyException {
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
            fw.write(tasks.recreateCmds());
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
     * @param parser Non-null. The function that parses next line and make changes to the list.
     * @throws MeggyException If file has wrong format. File is potentially not a task list record.
     */
    public void load(Consumer<String> parser) throws MeggyException {
        assert parser != null;
        final Scanner fileIn;
        try {
            fileIn = new Scanner(dataFile);
        } catch (FileNotFoundException e) {
            return;
        }
        boolean hasErr = false;
        while (fileIn.hasNextLine()) {
            try {
                parser.accept(fileIn.nextLine());
            } catch (MeggyException e) {
                hasErr = true;
            }
        }
        fileIn.close();
        if (hasErr) {
            throw new MeggyException(Util.ERROR_WRONG_FILE_0 + dataFile.getAbsolutePath() + Util.ERROR_WRONG_FILE_1);
        }
    }
}
