package aqua.manager;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

import aqua.exception.IllegalSyntaxException;
import aqua.exception.LoadException;
import aqua.exception.ProcedureExecutionException;
import aqua.util.Kaomoji;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/** Manager of inputs and outputs. */
public class IoManager {
    private static final String PATH_CSS = "/graphic/style.css";

    /** Greeting message. */
    private static final String MESSAGE_GREETING = String.join("\n",
            "Konaqua~~",
            Kaomoji.CUTE_TOSS + " Perfect idol gamer nekomimi super maid Minato Aqua desu!");
    /** Load success message. */
    private static final String MESSAGE_LOAD_SUCCESS =
            "I remembered all your previous tasks! Praise me " + Kaomoji.HEART_PASS;

    /** Syntax exception message format. */
    private static final String EXCEPTION_FORMAT_SYNTAX = String.join("\n",
            "Hanya??",
            "I do not understand because:",
            "  %s",
            "Gomennasai!! " + Kaomoji.CRY);
    /** Execution exception message format. */
    private static final String EXCEPTION_FORMAT_EXECUTION = String.join("\n",
            "Hanya??",
            "I was doing what you told me to do half way but messed up because:",
            "  %s",
            "Gomennasai!! " + Kaomoji.CRY);
    /** Load exception message format. */
    private static final String EXCEPTION_FORMAT_LOAD = String.join("\n",
            "Hanya??",
            "I was looking through my notes and could not remember your previous tasks because:",
            "  %s",
            "Gomennasai!! But you did not touch it right? " + Kaomoji.THINKING);
    /** All other exception message format. */
    private static final String EXCEPTION_FORMAT_DEATH = String.join("\n",
            "UWAWAWA!!",
            "I messed up big time...",
            "  %s");

    private final Supplier<String> inputSupplier;
    private final Consumer<String> outputConsumer;


    /**
     * Constructs an IoManager from the specified parameters.
     *
     * @param inputSupplier - the supplier to get user input from.
     * @param outputConsumer - the consumer to display messages to the user.
     */
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
     * Displays information about the given exception.
     *
     * @param ex - the exception to display.
     */
    public void replyException(Throwable ex) {
        reply(getExceptionReply(ex));
    }


    /**
     * Gets the formatted information String of the given exception.
     *
     * @param ex - the exception whose information is to be formed.
     */
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


    public void popup(Parent root) {
        Platform.runLater(() -> showPopup(root));
    }


    private void showPopup(Parent root) {
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(this.getClass().getResource(PATH_CSS).toString());
        stage.setScene(scene);
        stage.show();
    }
}
