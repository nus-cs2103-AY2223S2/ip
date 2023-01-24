package duke.command;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

class Storage {
    FileWriter writer;
    Scanner reader;
    ArrayList<String> records;
    String path;

    Storage(String DES) {
        try {
            writer = new FileWriter(DES, true);
            File store = new File(DES);
            reader = new Scanner(store);
            path = DES;
            records = new ArrayList<>();
        } catch (IOException e) {
            System.out.println("Invalid file Path\n");
        }
    }

    //write line by line
    void write(String input) {
        //writer.write(input);
        records.add(input);
        //writer.close();
    }

    void markAt(int index) {
        String str = records.get(index).replace("false", "true");
        records.set(index, str);
    }

    void unmarkAt(int index) {
        String str = records.get(index).replace("true", "false");
        records.set(index, str);
    }

    void deteleAt(int index) {
        records.remove(index);
    }

    //read all
    void read() {
        while (reader.hasNextLine()) {
            String data = reader.nextLine();
            records.add(data);
        }
        reader.close();

    }

    void WriteAll() {
        try {
            clearFile();
            for (String record : records) {
                writer.write(record);
                writer.write(System.lineSeparator());
            }
            writer.flush();
            writer.close();

        } catch (IOException e) {
            System.out.println("Write Error");
        }
    }

    //transfer from records to File
    void clearFile() throws IOException {
        FileWriter f1 = new FileWriter("/Users/s.f/ip/src/Data/duke.txt", false);
        PrintWriter p1 = new PrintWriter(f1, false);
        p1.flush();
        p1.close();
        p1.close();
    }

}
