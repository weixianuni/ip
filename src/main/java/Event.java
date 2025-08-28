import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 */
public class Event extends Task {

    LocalDate from;
    LocalDate to;

    public Event(String description, Boolean isCompleted, LocalDate from, LocalDate to) {
        super(description, isCompleted);
        this.from = from;
        this.to = to;
    }

    @Override
    public String fileString() {
        return "E|" + this.isCompleted + "|" + this.description + "|" + this.from + "|" + this.to + "\n";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + from.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " to: " + to.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}

