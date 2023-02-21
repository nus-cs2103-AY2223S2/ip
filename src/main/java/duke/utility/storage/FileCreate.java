package duke.utility.storage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


/**
 * Represents a <code>FilecReate</code> object that contains methods to create files and folders
 * with the path specified.
 * 
 * 
 * @author Brian Quek
 */
public class FileCreate {

    /**
     * Creates a file based on the given file path.
     * 
     * @param path the file path.
     */
    public static void createFile(Path path) {
        try {
            Files.createFile(path);
            new File(path.toString());
        } catch (IOException e) {
            System.out.println("Do not have permission to create folder.");
        }
    }


    /**
     * Creates a folder based on the given path in the paraemter.
     * 
     * @param path the folder path
     */
    public static void createFolder(Path path) {
        try {
            Files.createDirectories(path);
        } catch (IOException e) {
            System.out.println("Do not have permission to create folder.");
        }
    }
}
