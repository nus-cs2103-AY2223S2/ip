package cbot;

import cbot.io.BadInputException;
import cbot.io.FileStuff;
import cbot.io.Parser;
import cbot.io.PoorInputException;
import cbot.io.UI;
import cbot.task.TaskList;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeParseException;

/**
 * Your very own Personal Assistant Chatbot. Use Cbot to keep track of your tasks,
 * from deadlines to events, and even mark them as you complete them!
 */
public class Cbot {
    private TaskList tl;
    private final FileStuff fs;
    private final UI ui;
    
    private static final String PATH = "data/cbot_save.txt";

    /**
     * Constructs a fresh Cbot instance.
     *
     * @param filePath Directory location (relative) of the save file.
     * @throws IOException If the save file cannot be read.
     * @see #run()
     */
    Cbot(String filePath) throws IOException {
        this.fs = new FileStuff(filePath);
        this.ui = new UI();

        try {
            this.tl = fs.loadFile();
        } catch (FileNotFoundException e) {
            fs.makeFile();
            UI.sayNewFile(filePath);
            this.tl = new TaskList();
        }
    }

    /**
     * Starts up Cbot to be used.
     *
     * @throws IOException If the save file cannot be accessed.
     */
    void run() throws IOException {
        UI.sayHi();
        
        boolean doLoop = true;
        boolean doSave = false;
        
        while (doLoop) {
            String userInput;
            Parser p;
            
            try {
                userInput = this.ui.askUser();
                p = new Parser(userInput);
                
                if (p.isBye()) {
                    doLoop = false;
                } else {
                    if (p.needSave()) {
                        doSave = true;
                    }
                    p.respond(tl);
                }
            } catch (BadInputException e) {
                UI.warnBad(e);
            } catch (PoorInputException e) {
                UI.warn(e);
            } catch (DateTimeParseException e) {
                UI.warnTime();
            }
            
            if (doSave) {
                this.fs.saveFile(tl);
                doSave = false;
            }
        }
        
        UI.sayBye();
    }

    public static void main(String[] args) throws IOException {
        new Cbot(PATH).run();
    }
}