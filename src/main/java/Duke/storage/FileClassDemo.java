package Duke.storage;

import java.io.File;

/**
 * Class for testing if a file exist in given path
 */
public class FileClassDemo {

    /**
   * Main method testing if the file exist.
   */
    public static void main(String[] args) {
        File f = new File("data/duke.txt");
        System.out.println("full path: " + f.getAbsolutePath());
        System.out.println("file exists?: " + f.exists());
        System.out.println("is Directory?: " + f.isDirectory());
    }
}

