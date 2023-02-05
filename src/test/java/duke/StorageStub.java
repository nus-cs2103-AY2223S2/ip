package duke;

import duke.storage.Storage;

import java.io.IOException;

public class StorageStub extends Storage {
    public StorageStub(String fp) throws IOException {
        super(fp);
    }
}
