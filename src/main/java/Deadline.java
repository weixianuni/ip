import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 */
public class Deadline extends Task {
    LocalDate by;

    public Deadline(String description, Boolean isCompleted, LocalDate by) {
        super(description, isCompleted);
        this.by = by;
    }

    @Override
    public String fileString() {
        return "D|" + this.isCompleted + "|" + this.description + "|" + this.by + "\n";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
