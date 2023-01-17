package Command;

import LeoException.NoTaskFoundException;
import Storage.Storage;

public class List {

    public List(Storage s) {
        try {
            s.showList();
        } catch (NoTaskFoundException e) {
            System.out.println(e.getMessage());
        }
    }

}
