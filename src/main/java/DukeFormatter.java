public class DukeFormatter {
    private static String INDENT = "    ";
    private static String LINE = "____________________________________________________________";

    private static void line() {
        System.out.println(INDENT + LINE);
    }

    public static void section(String inputs) {
        //Start
        line();

        String[] lines = inputs.split("\n");
        for (String line: lines) {
            System.out.println(INDENT + line + "\n");
        }
        //End
        line();
        System.out.println("\n");
    }
}
