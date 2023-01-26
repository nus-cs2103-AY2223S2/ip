package command;

import leoException.LeoException;
import storage.Storage;

public class Exit {

    public Exit(Storage s) {
        exit(s);
    }

    private void exit(Storage s) {
        try {
            s.writeToFile();
            System.out.println("Leo: Good bye, have a nice day ahead!");
            System.exit(0);
        } catch (LeoException e) {
            System.out.println(e.getMessage());
        }
    }
}
