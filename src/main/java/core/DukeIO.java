package core;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import exceptions.DukeException;

/**
 * Handles all text and file input output for core.Duke.
 */
public class DukeIO extends PrintWriter {

    private static final Path LOCAL_SETTINGS_DIR = Paths.get(System.getProperty("user.dir"), "data");
    private static final Path LOCAL_SAVE = Paths.get(LOCAL_SETTINGS_DIR.toString(), "data.csv");
    private final BufferedReader bf;

    /**
     * This method is the constructor for DukeIO class.
     * This object can be used for all IO purposes, primarily printing messages and reading of user input.
     */
    public DukeIO() {
        super(new BufferedOutputStream(System.out));
        bf = new BufferedReader(new InputStreamReader(System.in));
    }

    /**
     * Reads user input and returns as string.
     * @return User input as String.
     */
    public String readLn() {
        String userInput = "";
        try {
            userInput = bf.readLine().trim();
        } catch (IOException e) {
            System.err.printf("DukeIO : %s%n", e.getMessage());
        }
        return userInput;
    }

    /**
     * Prints line break.
     */
    public void lb() {
        this.println("____________________________________________________________");
    }

    /**
     * Create buffered reader for file reading.
     *
     * @param filePath The name of the file to open
     * @return Buffered Reader.
     * @throws DukeException Thrown when no file is found at filepath
     */
    private static BufferedReader readFileBR(Path filePath) throws DukeException {
        try {
            return Files.newBufferedReader(DukeIO.LOCAL_SAVE, StandardCharsets.UTF_8);
        } catch (java.nio.file.NoSuchFileException e) {
            throw new exceptions.invalid.File(filePath.toString());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    /**
     * Create buffered writer for file writing.
     * Additionally, creates the file if file has not been created before.
     *
     * @return Buffered writer
     */
    private static BufferedWriter writeFileBW() {
        if (Files.notExists(DukeIO.LOCAL_SAVE)) {
            createFile(DukeIO.LOCAL_SAVE);
        }

        try {
            return Files.newBufferedWriter(DukeIO.LOCAL_SAVE, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return null;
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
     * @throws DukeException is thrown when the save file is not found.
     */
    public static void readSave(TaskMaster tm) throws DukeException {
        BufferedReader file = readFileBR(LOCAL_SAVE);
        try {
            String curLine;
            while ((curLine = file.readLine()) != null) {
                Parser.parseSaveFile(curLine.split(","), tm);
            }
        } catch (IOException | NullPointerException e) {
            System.err.printf("SysErr %s\n", e.getMessage());
        } catch (DukeException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Write to save file everything that is stored in the given TaskMaster object.
     * @param tm the runtime TaskMaster object
     */
    public static void writeSave(TaskMaster tm) {
        BufferedWriter file = writeFileBW();
        try {
            file.write(tm.exportToCsv());
            file.close();
        } catch (IOException | NullPointerException e) {
            System.err.printf("SysErr %s\n", e.getMessage());
        }
    }
}
