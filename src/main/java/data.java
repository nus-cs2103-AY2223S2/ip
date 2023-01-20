import java.util.ArrayList;

public class data {
    ArrayList<String> history = new ArrayList<>();

    public void addHist (String input) {
        history.add(input);
        System.out.println("added: " + input);
        return;
    }

    public void printHist () {
        for (int i = 0; i < history.size(); i++) {
            System.out.println((i+1) + ". " + history.get(i));
        }
    }
}
