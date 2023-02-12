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
    CLEAR("clear");

    private static final Map<String, CmdType> COMMANDS = new HashMap<>();
    static {
        COMMANDS.put("bye", CmdType.BYE);
        COMMANDS.put("list", CmdType.LIST);
        COMMANDS.put("todo", CmdType.TODO);
        COMMANDS.put("deadline", CmdType.DEADLINE);
        COMMANDS.put("event", CmdType.EVENT);
        COMMANDS.put("mark", CmdType.MARK);
        COMMANDS.put("unmark", CmdType.UNMARK);
        COMMANDS.put("delete", CmdType.DELETE);
        COMMANDS.put("clear", CmdType.CLEAR);
    }

    private final String cmd;

    CmdType(String str) {
        this.cmd = str;
    }

    public static CmdType getCmdType(String str) {
        return COMMANDS.get(str);
    }

    @Override
    public String toString() {
        return cmd;
    }

    public int getStrLength() {
        return cmd.length();
    }
}
