package dukes.command;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import dukes.util.*;

import java.time.format.DateTimeParseException;
import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class Command {
    protected boolean isExit;
    protected boolean isValid;
    protected String header;
    protected String body;

    public Command(boolean isExit, boolean isValid, String header, String body) {
        // Abstract class CAN have constructors, but only instantiate in sub-class
        this.isExit = isExit;
        this.isValid = isValid;
        this.header = header;
        this.body = body;
    }

    public abstract void execute(TaskList tasks, UI ui, Storage storage) throws DukeException;
    // static method cannot be abstract
    // if child method wants to throw exception, parent method must also do

    public boolean isExit() {
        return this.isExit;
    }

    public void setExit(boolean exit) {
        this.isExit = exit;
    }

    public boolean isValid() {
        return this.isValid;
    }

    public void setValid(boolean isValid) {
        this.isValid = isValid;
    }

    public String getHeader() {
        return this.header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getBody() {
        return this.body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
