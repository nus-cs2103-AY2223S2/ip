package Command;

import LeoException.LeoException;
import Storage.Storage;

public abstract class ListAction {

    public int extractTaskNum(String c) {
        String num = c.replaceAll("[^0-9]", "");
        return Integer.parseInt(num);
    }

}
