package meggy;

/** The class that statically launches the chatbot. */
public class Launcher {
    /** @deprecated Launcher class should not be initialized. */
    private Launcher() {
    }

    public static void main(String[] args) {
        new Meggy(System.in, System.out).run();
    }
}
