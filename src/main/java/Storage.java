import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private static final String PATH = "./data";
    private static final String FILENAME = "/DukeList.txt";
    private static File myFile;

    public void createFile() {
        File dir = new File(PATH);
        if (!dir.exists()) {
            dir.mkdir();
        }
        //create file if it does not already exist
        try {
            myFile = new File(PATH + FILENAME);
            if (!myFile.exists()) {
                myFile.createNewFile();
            }
        }
        catch (IOException e) {
            System.out.println(e + "\nNew storage file cannot be created.");
            e.printStackTrace();
        }
    }

    public ArrayList<Task> read() {
        ArrayList<Task> myList = new ArrayList<>();
        try {
            this.createFile();
            Scanner sc = new Scanner(myFile);
            while (sc.hasNextLine()) {
                String data = sc.nextLine();
                String[] dataArr = data.split(" \\| ");
                String type = dataArr[0];
                boolean isDone = dataArr[1].equals("1");

                if (type.equals("T")) {
                    ToDo t = new ToDo(dataArr[2], isDone);
                    myList.add(t);
                } else if (type.equals("D")) {
                    LocalDate end = Duke.parseDate(dataArr[3]);
                    Deadline d = new Deadline(dataArr[2], end, isDone);
                    myList.add(d);
                } else {
                    LocalDate start = Duke.parseDate(dataArr[3]);
                    LocalDate end = Duke.parseDate(dataArr[4]);
                    Event e = new Event(dataArr[2], start, end, isDone);
                    myList.add(e);
                }
            }
            sc.close();
        }
        catch (FileNotFoundException e) {
            System.out.println(e + "\nData file not found.");
        }
        return myList;
    }

    public void write(ArrayList<Task> myList) {
        try {
            FileWriter fw = new FileWriter(PATH + FILENAME);
            int size = myList.size();
            for (int i = 0; i < size; i++) {
                Task t = myList.get(i);
                fw.write(t.formatStore());
                fw.write("\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(e + "\nUnable to write to data file.");
            e.printStackTrace();
        }
    }
}
