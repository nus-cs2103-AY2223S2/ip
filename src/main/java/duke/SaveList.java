package duke;

public class SaveList {
    private Save save1;
    private Save save2;
    private Save save3;

    public void addSave(int saveNo, Save save) {
        switch (saveNo) {
        case 1:
            save1 = save;
            break;
        case 2:
            save2 = save;
            break;
        case 3:
            save3 = save;
            break;
        }
    }

    @Override
    public String toString() {
        String save1String = save1 == null ? "" : save1 + "\n";
        String save2String = save2 == null ? "" : save2 + "\n";
        String save3String = save3 == null ? "" : save3 + "\n";


        return save1String + save2String + save3String;
    }
}
