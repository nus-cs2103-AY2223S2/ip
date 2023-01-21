import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Scanner;

class Ui {
    private Scanner in;
    private Writer out;

    public Ui() {
        in = new Scanner(System.in);
        out = new PrintWriter(System.out);
    }

    public Ui(InputStream _in, OutputStream _out) {
        in = new Scanner(_in);
        out = new PrintWriter(_out);
    }

    public String nextLine() {
        return in.nextLine().trim();
    }

    public void print(String msg, Object... args) {
        try {
            out.append(String.format(msg, args)).flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void info(String msg, Object... args) {
        try {
            out.append("\u001b[36m")
                    .append(String.format(msg, args))
                    .append('\n')
                    .append("\u001b[0m")
                    .flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void error(String msg, Object... args) {
        try {
            out.append("\u001b[31m")
                    .append(String.format(msg, args))
                    .append('\n')
                    .append("\u001b[0m")
                    .flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
