package Command;

import Storage.Storage;

public class Mark {

    public Mark(Storage s, String command) {
        int num = extractTaskNum(command);
        s.mark(num);
    }

    public int extractTaskNum(String s) {
        String num = s.replaceAll("[^0-9]", "");
        return Integer.parseInt(num);
    }
}
