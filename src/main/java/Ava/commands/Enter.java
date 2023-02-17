package Ava.commands;

import Ava.Storage;
import Ava.TaskList;
import Ava.exceptions.CannotCreateDirectory;
import Ava.exceptions.CannotReadFromFile;
import Ava.exceptions.DateTimeNotParsed;

/**
 * Exits the Program
 */
public class Enter implements AvaCommand {
    private static final String INTRO_MESSAGE = "Heya! I am Ava.";
    private static final String GREET = "Nice to meet you!!";
    private static final String ASK_MESSAGE = "How can I help?";

    /**
     *
     * @param t a TaskList object
     * @param s a Storage object
     * @return False to indicate program has stopped running
     */
    @Override
    public boolean run(TaskList t, Storage s) throws CannotCreateDirectory,CannotReadFromFile, DateTimeNotParsed {
            t.retreiveStorage(s);
            return true;
    };


    @Override
    public String output(String formatSpace){

        return INTRO_MESSAGE + "\n" + GREET + "\n" + ASK_MESSAGE;
    }
}
