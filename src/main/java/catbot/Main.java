package catbot;

/**
 * Entry point.
 */
public class Main {
    public static void main(String[] args) {
        new CatBot("./data/tasklist.txt").run();
    }
}
