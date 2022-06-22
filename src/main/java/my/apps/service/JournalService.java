package my.apps.service;

import my.apps.db.FoodJournalRepository;
import my.apps.domain.JournalEntry;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

/**
 * @author flo
 * @since 22.06.2022.
 */
public class JournalService {

    private FoodJournalRepository repository = new FoodJournalRepository();

    public JournalEntry addEntry(Date date, String time, String meal, String food) throws SQLException, ClassNotFoundException {
        JournalEntry entry = new JournalEntry(date, time, meal, food);
        repository.insert(entry);
        return entry;
    }

    public List<JournalEntry> listJournal() throws SQLException, ClassNotFoundException {
        return repository.read();
    }
}
