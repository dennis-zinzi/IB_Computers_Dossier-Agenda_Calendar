import java.io.*;

/**
 * A class that defines an Event to be used and saved
 * 
 * Dennis Zinzi 
 * v1.0
 */
public class Event  
{
    // instance variables
    public String name;
    public String type;
    public byte day;
    public byte month;
    public int year;
    public byte fDay;
    public byte fMonth;
    public int fYear;
    public byte hour;
    public byte minute;
    public String location;
    public boolean occurrence;
    public String importance;
    public String description;

    /**
     * Constructor for objects of class Event
     */
    public Event(String name, String type, byte day, byte month, int year, boolean ocurrence, byte fDay, byte fMonth, int fYear, byte hour, byte minute, String location, String importance, String description)
    {
        // initialise instance variables
        this.name = name;
        this.type = type;
        this.day = day;
        this.month = month;
        this.year = year;
        this.occurrence = occurrence;
        this.fDay = fDay;
        this.fMonth = fMonth;
        this.fYear = fYear;
        this.hour = hour;
        this.minute = minute;
        this.location = location;        
        this.importance = importance;
        this.description = description;
     
    }

}