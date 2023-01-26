import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Scanner;

public class FileSaver {
    private String path;

    public FileSaver(String path) {
        this.path = path;
    }

    public String[] load() {
        String[] arr = new String[100];
        try {
            File myFile = new File(this.path);
            myFile.getParentFile().mkdirs();
            if (myFile.exists()) {
                Scanner sc = new Scanner(myFile);
                int pointer = 0;
                while (sc.hasNext()) {
                    arr[pointer] = sc.nextLine();
                    pointer++;
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
        return arr;
    }

    public void overwrite(String[] arr) {

        try {
            File myFile = new File(this.path);
            myFile.getParentFile().mkdirs();
            FileWriter myWriter = new FileWriter(myFile);
            if (myFile.createNewFile()) {
                for (int i = 0; i < arr.length; i++) {
                    if (arr[i] == null) {
                        break;
                    }
                    myWriter.write(arr[i].toString());
                    myWriter.write("\n");
                }
                myWriter.close();
            } else {
                new FileWriter(this.path, false).close();
                for (int i = 0; i < arr.length; i++) {
                    if (arr[i] == null) {
                        break;
                    }
                    myWriter.write(arr[i].toString());
                    myWriter.write("\n");
                }
                myWriter.close();
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }
}