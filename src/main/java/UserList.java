import java.util.ArrayList;
import java.util.List;

public class UserList {
    List<String> l = new ArrayList<String>(100);

    public void printList() {
        if (l.size() == 0) {
            System.out.println("You haven't added anything 0_0?");
        } else {
            int count = 1;
            for (String item : l) {
                System.out.println(count + ". " + item);
                count++;
            }
        }
    }

    public void addToList(String s) {
        l.add(s);
        System.out.println("Added to list: " + s);
    }
}


