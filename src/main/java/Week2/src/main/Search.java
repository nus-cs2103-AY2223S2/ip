package Week2.src.main;

import java.util.ArrayList;
public class Search {
    static TaskList tl;
    static ArrayList<Integer> indexlist = new ArrayList<>();

    public Search(TaskList tl) {
        this.tl = tl;
    }

    public static void find(String key) {
        Duke.lining();
        for(int i=0; i<tl.size(); i++) {
            Task curr = (Task) tl.get(i);
            if(curr.getContent().contains(key)) {
                indexlist.add(i);
            }
        }
        System.out.println("Here are the matching tasks in your list:");
        if(!indexlist.isEmpty()) {
            for (int i = 0; i < indexlist.size(); i++) {
                System.out.println(indexlist.get(i)+1 + "." + tl.get(i).toString());
            }
        }
        Duke.lining();
    }
}
