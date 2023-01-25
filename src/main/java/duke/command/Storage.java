package duke.command;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The type Storage.
 */
class Storage {
    FileWriter writer;
    Scanner reader;
    ArrayList<String> records;
    String path;

    /**
     * Instantiates a new Storage.
     *
     * @param DES the des
     */
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

    /**
     * Write inputs into memory.
     *
     * @param input the input
     */
    void write(String input) {
        records.add(input);
    }

    /**
     * Mark at.
     * mark index in memory
     *
     * @param index the index
     */
    void markAt(int index) {
        String str = records.get(index).replace("false", "true");
        records.set(index, str);
    }

    /**
     * Unmark at.
     * unmark index in memory
     *
     * @param index the index
     */
    void unmarkAt(int index) {
        String str = records.get(index).replace("true", "false");
        records.set(index, str);
    }

    /**
     * Detele at.
     * delete record at index
     *
     * @param index the index
     */
    void deteleAt(int index) {
        records.remove(index);
    }


    /**
     * Read.
     * Read Txt file
     */
    void read() {
        while (reader.hasNextLine()) {
            String data = reader.nextLine();
            records.add(data);
        }
        reader.close();

    }

    /**
     * Write all.
     * write into Txt file
     */
    void writeAll() {
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

    /**
     * Clear file.
     *
     * @throws IOException the io exception
     */
    void clearFile() throws IOException {
        FileWriter f1 = new FileWriter("/Users/s.f/ip/src/Data/duke.txt", false);
        PrintWriter p1 = new PrintWriter(f1, false);
        p1.flush();
        p1.close();
        p1.close();
    }

}
