import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public class Printer {
    private static String bar = "____________________________________________________________";
    private static String indent = "    ";
    private static PrintStream dummyStream = new PrintStream(new OutputStream() {
        @Override
        public void write(int b) throws IOException {
            // Empty to hide prints while reading from file
        }
    });
    private static PrintStream defaultStream = System.out;
    public static void printBar() {
        System.out.println(indent + bar);
    }
    public static void printText(String text) {
        System.out.println(indent + text);
    }
    public static void printRecords(Record record) {
        String[] strArr = record.toString().split("\n");
        for (int i = 0; i < strArr.length; i++) {
            printText(indent + (i+1) + ". " + strArr[i]);
        }
    }
    public static void printException(SundayException e) {
        printBar();
        printText(e.getMessage());
        printBar();
    }
    public static void setDummyStream() {
        System.setOut(dummyStream);
    }
    public static void setDefaultStream() {
        System.setOut(defaultStream);
    }
}
