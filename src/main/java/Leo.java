import command.Command;
import leoException.LeoException;
import storage.Storage;

public class Leo {

    public static void main(String[] args) {
        new Leo();
    }

    private Leo() {
        try {
            Storage storage = new Storage("ip/data/leo.txt");
            new Greetings();
            Command c = new Command(storage);
            c.readCommand();
        } catch (LeoException e) {
            System.out.println(e.getMessage());
        }
    }


}
