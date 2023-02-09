package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.LinkedList;
import java.util.Scanner;

public class Storage {
    private final File logFile;

    /**
     * Creates an IOException
     * The message informs the user that the log file could neither be detected nor created
     *
     * @return IOException with a message
     */
    private static IOException getIoException(){
        return new IOException("Unable to detect or create log file");
    }

    /**
     * Constructs a wrapper for the log file
     *
     * @param parent location of the directory of the log file
     * @param child filename of log file
     * @throws IOException if unable to detect or create log file
     */
    public Storage (String parent, String child) throws IOException {
        File parentFolder = new File(parent);
        logFile = new File(parent, child);

        if (!parentFolder.exists()) {
            if (!parentFolder.mkdirs()) {
                throw getIoException();
            }
        }

        if (!logFile.exists()) {
            if (logFile.createNewFile()) {
                throw getIoException();
            }
        }

    }

    /**
     * Retrieves the contents of the log file
     *
     * @return linked list containing the contents of the log file
     * @throws FileNotFoundException if unable to detect log file
     */
    public LinkedList<String> retrieveContents() throws FileNotFoundException {
        LinkedList<String> contents = new LinkedList<>();
        Scanner sc = new Scanner(logFile);
        while (sc.hasNext()) {
            contents.add(sc.nextLine());
        }
        return contents;
    }

    /**
     * Rewrite the contents of the log file to match a given linked list
     *
     * @param texts linked list of strings that will overwrite the log file
     * @throws IOException if unable to detect log file
     */

    public void update(LinkedList<String> texts) throws IOException {
        PrintWriter pw = new PrintWriter(logFile);
        for (String row : texts) {
            pw.println(row);
        }
        pw.close();
    }
}
