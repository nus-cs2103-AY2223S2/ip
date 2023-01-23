package aqua.manager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Optional;
import java.util.Scanner;

import aqua.exception.IllegalSyntaxException;
import aqua.exception.LoadException;
import aqua.exception.ProcedureExecutionException;

public class UiManager {
    private static String SEPARATOR =
            "____________________________________________________________";
    private static final String GREETING = 
            "Konaqua~~ Perfect idol gamer nekomimi super maid Minato Aqua desu!";
    private static final String LOAD_SUCCESS_MESSAGE =
            "I remembered all your previous tasks! Praise me";
    private static final String SYNTAX_ERROR_FORMAT =
            "Hanya??\n" +
            "I do not understand because:\n" +
            "  %s\n" +
            "Gomennasai!!";
    private static final String EXECUTION_EXCEPTION_FORMAT = 
            "Hanya??\n" +
            "I was doing what you told me to do half way but messed up because:\n" +
            "  %s\n" +
            "Gomennasai!!";
    private static final String LOAD_EXCEPTION_FORMAT = 
            "Hanya??\n" +
            "I was looking through my notes and could not remember your previous tasks because:\n" +
            "  %s\n" +
            "Gomennasai!! But you did not touch it right?";
    private static final String DEATH_EXCEPTION_FORMAT =
            "UWAWAWA!!\n" +
            "I messed up big time...\n" +
            "  %s";
    
    private final BufferedReader reader;


    public UiManager() {
        this(System.in);
    }


    public UiManager(InputStream inStream) {
        reader = new BufferedReader(new InputStreamReader(inStream));
    }
    

    public String readLine() throws IOException {
        String msg = reader.readLine();
        return Optional.ofNullable(msg).orElse("");
    }
    
    
    public void replyException(Throwable ex) {
        reply(getExceptionReply(ex));
    }


    public String getExceptionReply(Throwable ex) {
        try {
            throw ex;
        } catch (IllegalSyntaxException syntaxEx) {
            return String.format(SYNTAX_ERROR_FORMAT, ex.getMessage());
        } catch (ProcedureExecutionException cmdExeEx) {
            return String.format(EXECUTION_EXCEPTION_FORMAT, ex.getMessage());
        } catch (LoadException loadEx) {
            return String.format(LOAD_EXCEPTION_FORMAT, ex.getMessage());
        } catch (Throwable deathEx) {
            deathEx.printStackTrace();
            return String.format(DEATH_EXCEPTION_FORMAT, ex.toString());
        }
    }


    public void greet() {
        reply(getGreeting());
    }


    public String getGreeting() {
        return GREETING;
    }


    public void replyLoadSuccess() {
        reply(getLoadSuccessMessage());
    }


    public String getLoadSuccessMessage() {
        return LOAD_SUCCESS_MESSAGE;
    }


    public void reply(String msg) {
        System.out.println(formatMessage(msg));
    }


    public String formatMessage(String msg) {
        StringBuilder builder = new StringBuilder();
        try (Scanner scanner = new Scanner(msg)) {
            while (scanner.hasNextLine()) {
                builder.append(String.format("\t  %s\n", scanner.nextLine()));
            }
        }
        return String.format("\t%s\n%s\t%s\n",
            SEPARATOR, builder.toString(), SEPARATOR
        );
    }
}
