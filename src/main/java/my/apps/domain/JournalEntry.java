package my.apps.domain;

import java.sql.Date;

/**
 * @author flo
 * @since 19/02/2017.
 */
public class JournalEntry {

    private Date date;
    private String time;
    private String meal;
    private String food;

    public JournalEntry(Date date, String time, String meal, String food) {
        this.date = date;
        this.time = time;
        this.meal = meal;
        this.food = food;
    }

    @Override
    public String toString() {
        return "JournalEntry{" +
                "date=" + date +
                ", time='" + time + '\'' +
                ", meal='" + meal + '\'' +
                ", food='" + food + '\'' +
                '}';
    }

    public Date getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getMeal() {
        return meal;
    }

    public String getFood() {
        return food;
    }
}
