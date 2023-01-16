package Command;

import Storage.Storage;

public class Unmark {

    public Unmark(Storage s, String command) {
        int num = extractTaskNum(command);
        s.unmark(num);
    }

    public int extractTaskNum(String s) {
        String num = s.replaceAll("[^0-9]", "");
        return Integer.parseInt(num);
    }
}
