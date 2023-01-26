import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Save {
    public static final String NAME = "./data/lulu.txt";
    public static File file = new File(NAME);

    public static boolean isSavePresent() {
        try {
            boolean isPresent = !(file.createNewFile());
            if (!isPresent) {
                System.out.println("Installing text file...");
            }
            return isPresent;
        } catch (IOException e) {
            System.out.println("An error occurred.");
            return false;
        }
    }

    public static void writeSave(ArrayList<String> list) {
        try {
            FileWriter myWriter = new FileWriter(NAME);
            int size = list.size();
            for (int i = 0; i < size; i ++) {
                myWriter.write(list.get(i));
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }

    public static void readSave(Lulu lulu) {
        try {
            Scanner myReader = new Scanner(file);
            System.out.println("loading...");
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                lulu.load(data);
            }
            System.out.println("loading complete.");
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
        }
    }
}
