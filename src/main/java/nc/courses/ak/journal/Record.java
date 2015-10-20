package nc.courses.ak.journal;

import nc.courses.ak.exceptions.IllegalEventFormatJournalException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The class represents record of event. It contains date, time, importance of
 * event, name of event source and message from source.
 * 
 * @author Alexander Kovriga
 */
public class Record {

    /**
     * Represents types of importance.
     */
    protected static enum Importance {

        NORMAL(1, "."),
        WARNING(2, "!"),
        ERROR(3, "!!!"),
        CRITICAl_ERROR(4, "!!!!!");

        private final int value;
        private final String display;

        /**
         * The {@code Importance} constructor of importance.
         * 
         * @param value numeric equivalent of importance
         * @param display string equivalent of importance
         */
        Importance(int value, String display) {
            this.value = value;
            this.display = display;
        }

        /**
         * Returns the {@code value}.
         * 
         * @return numeric equivalent of importance
         */
        public int getValue() {
            return value;
        }
        
        /**
         * Returns the {@code importance}.
         * 
         * @param str string equivalent of importance
         * @return type of importance
         */
        public static Importance getImportance(String str) {
            for (Importance imp : Importance.values()) {
                if (imp.toString().equals(str)) {
                    return imp;
                }
            }
            return null;
        }

        @Override
        public String toString() {
            return display;
        }
    };

    private final Date date;
    private final Importance importance;
    private final String source;
    private final String message;

    /**
     * The {@code Record} constructor.
     * 
     * @param date the date of event occurrence
     * @param importance the level of importance of event
     * @param source the source who generate event
     * @param message the description of event
     */
    public Record(Date date, int importance, String source,
            String message) {
        this.date = date;
        this.importance = Importance.values()[importance - 1];
        this.source = source;
        this.message = message;
    }

    /**
     * The {@code Record} constructor.
     * 
     * @param event string equivalent of event in format: 
     * yyyy-MM-dd HH:mm:ss <importance> <source> <message>
     * @throws IllegalEventFormatJournalException when the event format is 
     * invalid
     */
    public Record(String event) throws IllegalEventFormatJournalException {
        String[] eventBlocks = event.split("\\s+");
        if (eventBlocks.length < 5) {
            throw new IllegalEventFormatJournalException("Wrong format of "
                    + "event.");
        }

        try {
            date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(
                    eventBlocks[0] + " " + eventBlocks[1]);
        } catch (ParseException pe) {
            throw new IllegalEventFormatJournalException("Wrong format of "
                    + "date or/and time.");
        }

        Pattern p = Pattern.compile(".|!|!!!|!!!!!");
        Matcher m = p.matcher(eventBlocks[2]);
        if (!m.matches()) {
            throw new IllegalEventFormatJournalException("Wrong format of "
                    + "importance.");
        }
        importance = Importance.getImportance(eventBlocks[2]);
        
        p = Pattern.compile("\\w+");
        m = p.matcher(eventBlocks[3]);
        if (!m.matches()) {
            throw new IllegalEventFormatJournalException("Wrong format of "
                    + "source.");
        }
        source = eventBlocks[3];
        
        String tmp = "";
        for (int i = 4; i < eventBlocks.length; ++i) {
            tmp += " " + eventBlocks[i];
        }
        p = Pattern.compile(".+");
        m = p.matcher(tmp);
        if (!m.matches()) {
            throw new IllegalEventFormatJournalException("Wrong format of "
                    + "message.");
        }
        message = tmp;
    }

    /**
     * Returns the {@code date}.
     * 
     * @return date of event
     */
    public Date getDate() {
        return (Date) date.clone();
    }

    /**
     * Returns the {@code importance}.
     * 
     * @return importance of event
     */
    public int getImportance() {
        return importance.getValue();
    }

    /**
     * Returns the {@code source}.
     * 
     * @return source of event
     */
    public String getSource() {
        return source;
    }

    /**
     * Returns the {@code message}.
     * 
     * @return message of event
     */
    public String getMessage() {
        return message;
    }

    @Override
    public boolean equals(Object obj) {
        return this.getDate().equals(((Record) obj).getDate())
                && this.getImportance() == ((Record) obj).getImportance()
                && this.getSource().equals(((Record) obj).getSource())
                && this.getMessage().equals(((Record) obj).getMessage());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.date);
        hash = 79 * hash + Objects.hashCode(this.importance);
        hash = 79 * hash + Objects.hashCode(this.source);
        hash = 79 * hash + Objects.hashCode(this.message);
        return hash;
    }

    @Override
    public String toString() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date) + " "
                + String.format("%-5s", importance) + " " + source + " " + 
                message;
    }

}
