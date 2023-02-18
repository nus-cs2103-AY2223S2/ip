package duke;

/**
 * Save class encapsulating a
 * save object
 */
public class Save {
    private String saveInfo;
    private String saveLocation;
    private int saveNo;

    /**
     * Constructor method for the save object
     *
     * @param saveNo Index of the save
     * @param saveInfo Message included with the save
     */
    public Save(int saveNo, String saveInfo) {
        this.saveNo = saveNo;
        this.saveInfo = saveInfo;
        this.saveLocation = "duke" + saveNo + ".txt";
    }

    public int getSaveNo() {
        return this.saveNo;
    }

    @Override
    public String toString() {
        return saveNo + ". " + saveInfo;
    }
}
