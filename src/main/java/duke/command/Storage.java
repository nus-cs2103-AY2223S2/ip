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
  /**
   * The Writer.
   */
  FileWriter writer;
  /**
   * The Reader.
   */
  Scanner reader;
  /**
   * The Records.
   */
  ArrayList<String> records;
  /**
   * The Path.
   */
  String path;

  /**
   * Instantiates a new Storage.
   *
   * @param des the des
   */
  Storage(String des) {
    try {
      writer = new FileWriter(des, true);
      File store = new File(des);
      reader = new Scanner(store);
      path = des;
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
    assert index >= 0 : "wrong index";
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
    assert index >= 0 : "wrong index";
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
    assert index >= 0 : "wrong index";
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
