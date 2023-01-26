import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    public static void loadFile(Tasklist list) throws IOException, DukeException {
        File file = new File(System.getProperty("user.dir") + "/data/Duke.txt");
        File dir = new File(System.getProperty("user.dir") + "/data");

        // if directory has not been created, make directory
        if (! dir.exists() ) {
            System.out.println("Directory created");
            dir.mkdir();
        }

        file.createNewFile();

        Scanner sc = new Scanner(file);  //takes file's content as input, scans through files and adds items to the list

        while (sc.hasNext()) {
            String[] currLine = sc.nextLine().split(" \\| ");
            if (currLine[0].equalsIgnoreCase("T")) {
                list.add(new ToDo(strToBool(currLine[1]), currLine[2]));
            } else if (currLine[0].equalsIgnoreCase("D")) {
                list.add(new Deadline(strToBool(currLine[1]), currLine[2], currLine[3]));
            } else if (currLine[0].equalsIgnoreCase("E")) {
                list.add(new Event(strToBool(currLine[1]), currLine[2], currLine[3], currLine[4]));
            } else {
                throw new DukeException("Read Error");
            }
        }
    }
    public static boolean strToBool(String str) {
        if (str == "1") {
            return true;
        } else {
            return false;
        }
    }
    public static void saveToFile(Tasklist list) throws IOException {
        FileWriter fw = new FileWriter(System.getProperty("user.dir") + "/data/Duke.txt");
        for (int i = 0; i < list.size(); i++) {
            fw.write(list.get(i).addDivider() +  "\n");
        }
        fw.close();
    }
}

