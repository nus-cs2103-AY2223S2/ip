package wessy;

import java.util.Map;
import java.util.HashMap;

public enum CmdType {
    LIST("list"),
    BYE("bye"),
    MARK("mark"),
    UNMARK("unmark"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    DELETE("delete"),
    FIND("find"),
    CLEAR("clear");

    private final String cmd;
    private static final Map<String, CmdType> COMMANDS = new HashMap<>();

    CmdType(String str) {
        this.cmd = str;
    }

    static {
        COMMANDS.put("bye", CmdType.BYE);
        COMMANDS.put("list", CmdType.LIST);
        COMMANDS.put("todo", CmdType.TODO);
        COMMANDS.put("deadline", CmdType.DEADLINE);
        COMMANDS.put("event", CmdType.EVENT);
        COMMANDS.put("mark", CmdType.MARK);
        COMMANDS.put("unmark", CmdType.UNMARK);
        COMMANDS.put("delete", CmdType.DELETE);
        COMMANDS.put("find", CmdType.FIND);
        COMMANDS.put("clear", CmdType.CLEAR);
    }

    public static CmdType getCmdType(String str) {
        return COMMANDS.get(str);
    }

    @Override
    public String toString() {
        return cmd;
    }

    public int len() {
        return cmd.length();
    }
}
