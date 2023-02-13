package features;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Random;

/**
 * Generates random facts when prompted.
 */
public class Trivia {
    protected ArrayList<String> facts;

    /**
     * Constructor for a Trivia instance.
     * @throws DukeException Thrown if an error occurs.
     */
    public Trivia() throws DukeException {
        this.facts = new ArrayList<>();
        loadFacts();
    }

    /**
     * Loads facts from the facts/facts.txt file into the facts ArrayList.
     * @throws DukeException Thrown if the facts/facts.txt file is not found.
     */
    public void loadFacts() throws DukeException {
        InputStream dukeFactStream = new Trivia().getFileFromResourceAsStream();
        transferFactsFromStream(dukeFactStream);
    }

    /**
     * Returns a random fact from the loaded facts ArrayList.
     * @return A random fact from the facts ArrayList.
     */
    public String getFact() {
        Random randomInt = new Random();
        return this.facts.get(randomInt.nextInt(this.facts.size()));
    }

    private InputStream getFileFromResourceAsStream() throws DukeException {
        Ui ui = new Ui();
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("/facts/facts.txt");
        if (inputStream == null) {
            throw new DukeException(ui.formatLogicError("I can't find facts.txt!"));
        } else {
            return inputStream;
        }
    }

    private void transferFactsFromStream(InputStream is) throws DukeException {
        Ui ui = new Ui();
        try {
            InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
            BufferedReader factReader = new BufferedReader(streamReader);
            String factLine = factReader.readLine();
            while (!factLine.equals("")) {
                this.facts.add(factLine);
                factLine = factReader.readLine();
            }
        } catch (IOException ex) {
            throw new DukeException(ui.formatLogicError("error getting facts from facts.txt!"));
        }
    }
}
