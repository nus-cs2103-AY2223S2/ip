import java.io.*;

/**
 * The text-based UI used by the chatbot.
 * */
public class UI implements Closeable{
    private final PrintStream out;
    /**
     * @param out Non-null. Customizable output.
     * */
    public UI(OutputStream out) {
        this.out = out instanceof PrintStream ? (PrintStream) out : new PrintStream(out);
    }
    /**
     * Displays {@code String.valueOf(obj)} on UI.
     * */
    public void disp(Object obj){
        out.print(obj);
    }
    /**
     * Displays {@code String.valueOf(obj)} on UI and creates a new line.
     * */
    public void dispLn(Object obj){
        out.println(obj);
    }

    @Override
    public void close(){
        out.close();
    }
}
