package Week2.src.main;

import java.util.ArrayList;
public class Search {
    static TaskList tl;
    static ArrayList<Integer> indexlist = new ArrayList<>();

    public Search(TaskList tl) {
        this.tl = tl;
    }

    public static String find(String key) {
        for(int i=0; i<tl.size(); i++) {
            Task curr = (Task) tl.get(i);
            if(curr.getContent().contains(key)) {
                indexlist.add(i);
            }
        }
        String restr = "Here are the matching tasks in your list:";
        if(!indexlist.isEmpty()) {
            for (int i = 0; i < indexlist.size(); i++) {
                String found = indexlist.get(i)+1 + "." + tl.get(i).toString();
                restr = restr + "\n" + found;
            }
        }
        return restr;
    }
}
