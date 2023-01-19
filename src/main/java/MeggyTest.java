import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * For text UI testing purpose only.
 */
public class MeggyTest {
    public static void main(String[] args) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        new Meggy(new FileInputStream("text-ui-test/input.txt"), out).run();
        String actual = out.toString().replaceAll("\r", "");
        String expected = new String(new FileInputStream("text-ui-test/EXPECTED.txt").readAllBytes()).
                replaceAll("\r", "");
        System.out.println(actual.equals(expected));
    }
}
