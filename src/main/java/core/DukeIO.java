package core;

import exceptions.DukeException;
import core.TaskMaster;
import exceptions.missing.File;

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
    private BufferedReader SAVE_FILE;
    private BufferedReader bf;

    public DukeIO(){
        super(new BufferedOutputStream(System.out));
        bf = new BufferedReader(new InputStreamReader(System.in));
    }

    public String readln() {
        String userInput = "";
        try{
            userInput = bf.readLine().trim();
        } catch (IOException e){
            System.err.printf("DUKEIO : %s%n", e.getMessage());
        }
        return userInput;
    }

    public void lb(){
        this.println("____________________________________________________________");
    }

    private static BufferedReader readFileBR(Path filePath) throws File {
        BufferedReader READ_FILE = null;
        try {
            READ_FILE = Files.newBufferedReader(filePath, StandardCharsets.UTF_8);
        } catch (java.nio.file.NoSuchFileException e) {
            throw new exceptions.missing.File(filePath);
        } catch (IOException e) {
            System.err.println(e);
        }
        return READ_FILE;
    }
    private static BufferedWriter writeFileBW(Path filePath) {
        BufferedWriter WRITE_FILE = null;
        if (Files.notExists(filePath)) {
            createFile(filePath);
        }

        try {
            WRITE_FILE = Files.newBufferedWriter(filePath, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.err.println(e);
        }

        return WRITE_FILE;
    }
    public static void createFile(Path filePath) {
        if (Files.notExists(filePath.getParent())) {
            try {
                Files.createDirectories(filePath.getParent());
            } catch (IOException e) {
                System.err.println(e.getCause());
            }
        }

        try {
            Files.createFile(filePath);
        } catch (IOException e) {
            System.err.println(e.getCause());
        }
    }

    public static void readSave(TaskMaster tm) throws DukeException {
        BufferedReader FILE = readFileBR(LOCAL_SAVE);
        try {
            String curLine;
            while ((curLine = FILE.readLine()) != null ) {
                Parser.parseFile(curLine.split(","), tm);
            }
        } catch (IOException e) {
            System.err.printf("SYSERR %s\n", e.getMessage());
        } catch (DukeException e) {
            System.err.println(e.getMessage());
        }
    }

    protected static void writeSave(TaskMaster tm) {
        System.out.println(LOCAL_SAVE);
        BufferedWriter FILE = writeFileBW(LOCAL_SAVE);
        try {
            System.out.println(tm.export());
            FILE.write(tm.export());
            FILE.close();
        } catch (IOException e) {
            System.err.printf("SYSERR %s\n", e.getMessage());
        }
    }

}
