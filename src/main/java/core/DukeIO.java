package core;

import exceptions.DukeException;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.charset.StandardCharsets;

public class DukeIO extends PrintWriter {

    // To look into placing into Document/Appdata like locations instead of this directory
    private final static Path LOCAL_SETTINGS_DIR = Paths.get(System.getProperty("user.dir"), "data");
    private final static Path LOCAL_SAVE = Paths.get(LOCAL_SETTINGS_DIR.toString(), "data.csv");
    private final BufferedReader bf;

    /**
     * Constructor for DukeIO class.
     * This object can be used for all IO purposes, primarily printing messages and reading of user input.
     */
    public DukeIO(){
        super(new BufferedOutputStream(System.out));
        bf = new BufferedReader(new InputStreamReader(System.in));
    }

    /**
     * Reads user input and returns as string.
     * @return User input as String.
     */
    public String readLn() {
        String userInput = "";
        try{
            userInput = bf.readLine().trim();
        } catch (IOException e){
            System.err.printf("DukeIO : %s%n", e.getMessage());
        }
        return userInput;
    }

    /**
     * Prints line break.
     */
    public void lb(){
        this.println("____________________________________________________________");
    }

    /**
     * Create buffered reader for file reading.
     *
     * @return Buffered Reader
     */
    private static BufferedReader readFileBR() {
        BufferedReader READ_FILE = null;
        try {
            READ_FILE = Files.newBufferedReader(DukeIO.LOCAL_SAVE, StandardCharsets.UTF_8);
        } catch (java.nio.file.NoSuchFileException e) {
            System.err.println("File you were trying to read does not exist!");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return READ_FILE;
    }

    /**
     * Create buffered writer for file writing.
     * Additionally, creates the file if file has not been created before.
     *
     * @return Buffered writer
     */
    private static BufferedWriter writeFileBW() {
        BufferedWriter WRITE_FILE = null;
        if (Files.notExists(DukeIO.LOCAL_SAVE)) {
            createFile(DukeIO.LOCAL_SAVE);
        }

        try {
            WRITE_FILE = Files.newBufferedWriter(DukeIO.LOCAL_SAVE, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        return WRITE_FILE;
    }

    /**
     * Creates file for writing if it does not exist.
     * @param filePath The path to create the file at.
     */
    private static void createFile(Path filePath) {
        if (Files.notExists(filePath.getParent())) {
            try {
                Files.createDirectories(filePath.getParent());
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }

        try {
            Files.createFile(filePath);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Read save file and populates the given TaskMaster object.
     * @param tm the runtime TaskMaster object
     */
    protected static void readSave(TaskMaster tm) {
        try {
            BufferedReader FILE = readFileBR();
            String curLine;
            while ((curLine = FILE.readLine()) != null ) {
                Parser.parseSaveFile(curLine.split(","), tm);
            }
        } catch (IOException e) {
            System.err.printf("SysErr %s\n", e.getMessage());
        } catch (DukeException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Write to save file everything that is stored in the given TaskMaster object.
     * @param tm the runtime TaskMaster object
     */
    protected static void writeSave(TaskMaster tm) {
        System.out.println(LOCAL_SAVE);
        BufferedWriter FILE = writeFileBW();
        try {
            System.out.println(tm.export());
            FILE.write(tm.export());
            FILE.close();
        } catch (IOException e) {
            System.err.printf("SysErr %s\n", e.getMessage());
        }
    }
}
