import Command.*;

public class Leo {
    private final Command c;

    public static void main(String[] args) {
        Leo leo = new Leo();
        leo.c.readCommand();
    }

    private Leo() {
        new Greetings();
        c = new Command();
    }


}
