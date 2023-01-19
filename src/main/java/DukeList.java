/**
 * Storage for user inputs up to 100 records.
 *
 * @author JamesLiuZX
 * AY2223-S2 CS2103T
 */
import java.util.ArrayList;

public class DukeList {
  //Variables are kept private and accessed only through internal getter and setter methods.
  private static final int size = 100;
  private static ArrayList<String> records;

  //Constructor
  public DukeList() {
    records = new ArrayList<>(size);
  }

  public static void insert(String record) {
      records.add(record);
      System.out.println("added: " + record);
  }

  @Override
  public String toString() {
    String output = "";
    if (records.isEmpty()) {
      return "Please insert a task first.";
    }
    for (int i = 0; i < records.size(); i++) {
      output += String.format("%s. %s\n", i+1, records.get(i));
    }
    return Duke.format(output);
  }
}
