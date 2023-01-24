package util;

import java.io.*;
import java.util.Scanner;;

public class ReadFileData {
    private static final String FILEPATH = "src/main/java/data/UserTasks.txt";

    public static void printFileContents() throws FileNotFoundException {
        File file = new File(FILEPATH);
        Scanner s = new Scanner(file); // create a Scanner using the File as the source
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
    }

    public static void main(String[] args) {
        try {
            printFileContents();
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
    }

}
