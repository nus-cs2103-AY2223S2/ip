package features;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

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
     * Loads facts from the data/facts.txt file into the facts ArrayList.
     * @throws DukeException Thrown if the data/facts.txt file is not found.
     */
    public void loadFacts() throws DukeException {
        Ui ui = new Ui();
        File dukeFactsPath = new File("data/facts.txt");
        try {
            Scanner fileScan = new Scanner(dukeFactsPath);
            while (fileScan.hasNextLine()) {
                String fileLine = fileScan.nextLine();
                this.facts.add(fileLine);
            }
        } catch (FileNotFoundException err) {
            throw new DukeException(ui.formatLogicError("I can't find facts.txt!"));
        }
    }

    /**
     * Returns a random fact from the loaded facts ArrayList.
     * @return A random fact from the facts ArrayList.
     */
    public String getFact() {
        Random randomInt = new Random();
        return this.facts.get(randomInt.nextInt(this.facts.size()));
    }
}
