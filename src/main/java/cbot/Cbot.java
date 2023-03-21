package cbot;

import java.io.FileNotFoundException;
import java.time.format.DateTimeParseException;

import cbot.command.BadInputException;
import cbot.command.PoorInputException;
import cbot.io.Parser;
import cbot.io.Talker;
import cbot.task.TaskList;
import cbot.util.FileStuff;

/**
 * Your very own Personal Assistant Chatbot. Use Cbot to keep track of your tasks,
 * from deadlines to events, and even mark them as you complete them!
 */
public class Cbot {
    private static final String PATH = "data/cbot_save.txt";

    private TaskList tl;
    private final FileStuff fs;
    private boolean isBye;
    private boolean prevWasBad;

    /**
     * Constructs a fresh Cbot instance.
     */
    public Cbot() {
        this.fs = new FileStuff(PATH);
        this.isBye = false;

        try {
            this.tl = fs.loadFile();
        } catch (FileNotFoundException e) {
            fs.makeFile();
            this.tl = new TaskList();
        }
    }

    /**
     * Returns true if the BYE Command has been called.
     *
     * @return true if BYE has been called.
     */
    public boolean isBye() {
        return this.isBye;
    }

    /**
     * Returns true if the previous Command threw an exception.
     *
     * @return true if an exception was just thrown.
     */
    public boolean isBad() {
        return this.prevWasBad;
    }

    /**
     * Returns Cbot's greeting.
     *
     * @return Cbot's greeting.
     * @see Talker#sayHi()
     */
    public static String sayHi() {
        return Talker.sayHi();
    }

    /**
     * Processes the input against the current list of tasks.
     *
     * @param input The full user-given command.
     * @return Cbot's response to the input.
     * @see Parser
     */
    public String getResponse(String input) {
        assert !this.isBye : "Cbot should have terminated already";

        try {
            Parser p = new Parser(input);

            if (p.isBye()) {
                this.isBye = true;
            }

            String output = p.respond(tl);
            this.prevWasBad = false;

            if (p.needSave()) {
                this.fs.saveFile(tl);
            }

            return output;
        } catch (BadInputException e) {
            this.prevWasBad = true;
            return Talker.warnBad(e);
        } catch (PoorInputException e) {
            this.prevWasBad = true;
            return Talker.warn(e);
        } catch (DateTimeParseException e) {
            this.prevWasBad = true;
            return Talker.warnTime();
        }
    }

    private void hug(Object obj) {
        // cbot gives obj a hug :)
    }
}
