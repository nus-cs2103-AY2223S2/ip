public class Duke {
    final static String FILE_NAME = "save.txt";
    final static String SAVE_DIRECTORY = "data";

    public static void main(String[] args) {
        new Chatbot(SAVE_DIRECTORY, FILE_NAME).run();
    }


}
