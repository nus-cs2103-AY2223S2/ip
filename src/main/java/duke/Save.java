package duke;

public class Save {
    private String saveInfo;
    private String saveLocation;
    private int saveNo;

    public Save(int saveNo, String saveInfo) {
        this.saveNo = saveNo;
        this.saveInfo = saveInfo;
        this.saveLocation = "duke" + saveNo + ".txt";
    }

    public String getSaveLocation() {
        return this.saveLocation;
    }

    public int getSaveNo() {
        return this.saveNo;
    }

    public String getSaveInfo() {
        return this.saveInfo;
    }

    @Override
    public String toString() {
        return saveNo + ". " + saveInfo;
    }
}
