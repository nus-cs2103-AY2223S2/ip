package domain.usecases;

import static org.mockito.ArgumentMatchers.anyString;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import domain.entities.core.ExitStatus;
import domain.entities.core.Writable;

@ExtendWith(MockitoExtension.class)
public class ByeUsecaseTest {
    @Test
    public void execute_invocation_shouldWriteToWriter() {
        final Writable writer = Mockito.mock(Writable.class);
        final ByeUsecase usecase = new ByeUsecase(writer);
        usecase.execute(new String[0]);
        Mockito.verify(writer, Mockito.times(1)).writeln(anyString());
    }

    @Test
    public void execute_invocation_shouldReturnTerminate() {
        final Writable writer = Mockito.mock(Writable.class);
        final ByeUsecase usecase = new ByeUsecase(writer);
        final ExitStatus status = usecase.execute(new String[0]);
        assert status == ExitStatus.terminate;
    }
}
