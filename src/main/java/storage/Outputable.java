package storage;

import java.io.IOException;

public interface Outputable {
    void write(String data) throws IOException;
}
