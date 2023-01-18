public class WelcomeUI {
    /*
     * logo credit: https://patorjk.com/software/taag/#p=testall&h=2&v=3&f=3D-ASCII&t=RubiRyo
     * dog image credit: ChatGPT
     * */
    protected String logo = "\n" +
            " ____  __ __ ____  ____ ____  __ __  ___  \n" +
            "|    \\|  |  |    \\|    |    \\|  |  |/   \\ \n" +
            "|  D  )  |  |  o  )|  ||  D  )  |  |     |\n" +
            "|    /|  |  |     ||  ||    /|  ~  |  O  |\n" +
            "|    \\|  :  |  O  ||  ||    \\|___, |     |\n" +
            "|  .  \\     |     ||  ||  .  \\     |     |\n" +
            "|__|\\_|\\__,_|_____|____|__|\\_|____/ \\___/ \n" +
            "                                          \n";
    protected String dogImage = "|\\___/|\n" +
            "| o o |\n" +
            " \\   /\n" +
            "  \\ /\n";

    protected String introMessage = "Woof! I'm RubiRyo the Japanese Spitz!\n" +
            "Give me a command or type menu to see the commands I know!";

    @Override
    public String toString() {
        return logo + introMessage;
    }
}
