package yappy.backend;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import yappy.backend.Storage;
import yappy.exception.YappyException;
import yappy.task.*;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class StorageTest {

    private File tempFile;
    private Storage storage;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     *
     * @throws IOException
     */
    @BeforeEach
    public void setUp() throws IOException {
        // Create a temporary file
        tempFile = File.createTempFile("Yappy", ".txt");
        tempFile.deleteOnExit();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            writer.write("T|false|Buy milk\n");
            writer.write("D|true|Submit report|2025-08-31 23:59\n");
            writer.write("E|false|Meeting|2025-08-30 10:00|2025-08-30 11:00\n");
        }

        storage = new Storage(tempFile.getAbsolutePath());
    }

    @Test
    public void testLoadTask_fileExists_success() throws YappyException {
        ArrayList<Task> tasks = storage.loadTask();

        // Verify size
        assertEquals(3, tasks.size());

        // Verify first task is a ToDo
        Task t1 = tasks.get(0);
        assertInstanceOf(ToDo.class, t1);
        assertEquals("Buy milk", t1.getDescription());
        assertFalse(t1.isCompleted());

        // Verify second task is a Deadline
        Task t2 = tasks.get(1);
        assertInstanceOf(Deadline.class, t2);
        assertEquals("Submit report", t2.getDescription());
        assertTrue(t2.isCompleted());
        assertEquals(LocalDateTime.parse("2025-08-31 23:59", FORMATTER), ((Deadline) t2).getByDate());

        // Verify third task is an Event
        Task t3 = tasks.get(2);
        assertInstanceOf(Event.class, t3);
        assertEquals("Meeting", t3.getDescription());
        assertFalse(t3.isCompleted());
        assertEquals(LocalDateTime.parse("2025-08-30 10:00", FORMATTER), ((Event) t3).getFromDate());
        assertEquals(LocalDateTime.parse("2025-08-30 11:00", FORMATTER), ((Event) t3).getToDate());
    }

    @Test
    public void testLoadTask_fileNotFound_exceptionThrown() {
        // Create Storage with a file that doesn't exist
        Storage storageMissing = new Storage("nonexistent_file.txt");

        YappyException exception = assertThrows(YappyException.class, storageMissing::loadTask);
        assertEquals("File not found", exception.getMessage());
    }
}
