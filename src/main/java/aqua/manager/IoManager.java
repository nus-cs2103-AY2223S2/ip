package aqua.manager;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

import aqua.exception.IllegalSyntaxException;
import aqua.exception.LoadException;
import aqua.exception.ProcedureExecutionException;


/** Manager of inputs and outputs. */
public class IoManager {
    /** Greeting message. */
    private static final String MESSAGE_GREETING = 
            "Konaqua~~\n(*ゝω･*)ﾉ Perfect idol gamer nekomimi super maid Minato Aqua desu!";
    /** Load success message. */
    private static final String MESSAGE_LOAD_SUCCESS =
            "I remembered all your previous tasks! Praise me (⋈◍＞◡＜◍)。✧♡";
    
    /** Syntax exception message format. */
    private static final String EXCEPTION_FORMAT_SYNTAX =
            "Hanya??\n" +
            "I do not understand because:\n" +
            "  %s\n" +
            "Gomennasai!! (╥﹏╥)";
    /** Execution exception message format. */
    private static final String EXCEPTION_FORMAT_EXECUTION = 
            "Hanya??\n" +
            "I was doing what you told me to do half way but messed up because:\n" +
            "  %s\n" +
            "Gomennasai!! (╥﹏╥)";
    /** Load exception message format. */
    private static final String EXCEPTION_FORMAT_LOAD = 
            "Hanya??\n" +
            "I was looking through my notes and could not remember your previous tasks because:\n" +
            "  %s\n" +
            "Gomennasai!! But you did not touch it right? ( ･̆-･̆)";
    /** All other exception message format. */
    private static final String EXCEPTION_FORMAT_DEATH =
            "UWAWAWA!!\n" +
            "I messed up big time...\n" +
            "  %s";
    
    private final Supplier<String> inputSupplier;
    private final Consumer<String> outputConsumer;


    public IoManager(Supplier<String> inputSupplier, Consumer<String> outputConsumer) {
        this.inputSupplier = inputSupplier;
        this.outputConsumer = outputConsumer;
    }
    

    /**
     * Reads a line from the set input stream.
     * 
     * @return a line in the set input stream.
     */
    public String readLine() {
        String msg = inputSupplier.get();
        return Optional.ofNullable(msg).orElse("");
    }
    
    
    /**
     * Formats and prints exception.
     * 
     * @param ex - the exception to print.
     */
    public void replyException(Throwable ex) {
        reply(getExceptionReply(ex));
    }


    private String getExceptionReply(Throwable ex) {
        try {
            throw ex;
        } catch (IllegalSyntaxException syntaxEx) {
            return String.format(EXCEPTION_FORMAT_SYNTAX, ex.getMessage());
        } catch (ProcedureExecutionException cmdExeEx) {
            return String.format(EXCEPTION_FORMAT_EXECUTION, ex.getMessage());
        } catch (LoadException loadEx) {
            return String.format(EXCEPTION_FORMAT_LOAD, ex.getMessage());
        } catch (Throwable deathEx) {
            deathEx.printStackTrace();
            return String.format(EXCEPTION_FORMAT_DEATH, ex.toString());
        }
    }


    /** Prints the greeting message. */
    public void greet() {
        reply(MESSAGE_GREETING);
    }


    /**
     * Prints the message to signal that tasks have successfully been loaded.
     */
    public void replyLoadSuccess() {
        reply(MESSAGE_LOAD_SUCCESS);
    }


    /**
     * Formats and prints the message.
     * 
     * @param msg - the message to print.
     */
    public void reply(String msg) {
        outputConsumer.accept(msg);
    }
}
