import java.util.ArrayList;

public class MyList {
  private ArrayList<String> list;

  public MyList() {
    list = new ArrayList<>();
  }

  public boolean addItem(String item) {
    return list.add(item);
  }

  public String generateList() {
    StringBuilder sb = new StringBuilder();
    int i = 1;
    for (String item : list) {
      String line = String.format("  %d: %s\n", i, item);
      sb.append(line);
      i++;
    }
    return sb.toString();
  }
}
