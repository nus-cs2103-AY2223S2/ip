import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

public class TaskFile {
    protected static File f = new File("data\\tasklist.txt");


    private void printFileContents(String filePath) throws FileNotFoundException {
        File f = new File("data\\tasklist.txt");
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
    }

    public static void writeToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter("data\\tasklist.txt");
        fw.write(textToAdd + System.lineSeparator());
        fw.close();
    }

    private static void appendToFile(String textToAppend) throws IOException {
        FileWriter fw = new FileWriter("data\\tasklist.txt", true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

    public static void createFile() {
        if(!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {
        File f = new File("data\\tasklist.txt");

        if(!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("full path: " + f.getAbsolutePath());
        System.out.println("file exists?: " + f.exists());
        System.out.println("is Directory?: " + f.isDirectory());
    }

}
