package nc.courses.ak.journal;

import java.util.Date;

/**
 *
 * @author Alexander Kovriga
 */
public interface Journal {
    
    /**
     * Adds the record {@code r} to the array.
     * 
     * @param r {@code record}
     */
    void add(Record r);
    
    /**
     * Adds all elements from {@code j} to the array.
     * 
     * @param j array of records
     */
    void add(Journal j);
    
    /**
     * Removes the record {@code r} from the array.
     * 
     * @param r {@code record}
     */
    void remove(Record r);
    
    /**
     * Returns the {@code record} at the given {@code index}.
     * 
     * @param index number of {@code record} in the array
     * @return record at the given {@code index}
     */
    Record get(int index);
    
    /**
     * Replaces the {@code record} at the given {@code index} to the {@code record}
     * 
     * @param index number of {@code record} in the array
     * @param record the {@code record} that will be set at given {@code index}
     */
    void set(int index, Record record);
    
    /**
     * Inserts the {@code record} at the given {@code index}.
     * 
     * @param index position of {@code record} in the array
     * @param record the {@code record} that will be inserted at given 
     * {@code index}
     */
    void insert(int index, Record record);
    
    /**
     * Removes the {@code record} of given {@code index}.
     * 
     * @param index number of {@code record} in the array.
     */
    void remove(int index);
    
    /**
     * Removes range of record from {@code fromIndex} to {@code toIndex} from
     * array.
     * 
     * @param fromIndex starting position of range
     * @param toIndex final position of range
     */
    void remove(int fromIndex, int toIndex);
    
    /**
     * Removes all record from the array.
     */
    void removeAll();
    
    /**
     * Returns size of array.
     * 
     * @return size of array
     */
    int size();
    
    /**
     * Returns array of filtered records by the string {@code s}.
     * 
     * @param s filter
     * @return array of filtered records
     */
    Journal filter(String s);
    
    /**
     * Returns array of filtered records from {@code fromDate} to 
     * {@code toDate}.
     * 
     * @param fromDate starting date
     * @param toDate final date
     * @return array of filtered records
     */
    Journal filter(Date fromDate, Date toDate);
    
    /**
     * Sorts array by date.
     */
    void sortByDate();
    
    /**
     * Sorts array by importance and date.
     */
    void sortByImportanceDate();
    
    /**
     * Sorts array by importance and source and date.
     */
    void sortByImportanceSourceDate();
    
    /**
     * Sorts array bye source and date.
     */
    void sortBySourceDate();
    
    /**
     * Prints all records of array to console.
     */
    void printRecords();
    
}
