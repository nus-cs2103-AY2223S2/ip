package command;

import leoException.NoTaskFoundException;
import storage.Storage;

public class List {

    public List(Storage s) {
        try {
            s.showList();
        } catch (NoTaskFoundException e) {
            System.out.println(e.getMessage());
        }
    }

}
