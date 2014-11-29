import java.io.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.EOFException;

/**
 * A class that creates and accesses a Random Access File with the Events.
 * 
 * Dennis Zinzi
 * v1.0
 */
public class FileHandler
{   
   // instance variables
   //Name of File to be accessed
   static String fileName;
   //Max Size of an Event thar can be stored
   public int byteSizeOfRecord;
   //Number of Events on accessed File
   public static long numberOfRecords;
 
    /**
     * Constructor for objects of class FileHandler
     */
    public FileHandler(String fileName)
    {
        // initialise instance variables
        this.fileName=fileName;
        byteSizeOfRecord = 161;
    }

    /**
    * Purpose: Write the Event in the designated position in the File
    * Pre-Condition: The Event to be stored must exist
    * Post-Condition: The Event is stored in the designated position in the File
    */
    public void writeEvent(Event ev, long filePosition){
        try{
            System.out.println("Creating Record + in file handler");
            System.out.println(byteSizeOfRecord);

            //Access or Create the Random Access File where the event will be stored
            RandomAccessFile dataFile = new RandomAccessFile(new File(fileName),"rw");
        
                //Determine the position to store the details of the Event
                dataFile.seek(filePosition*byteSizeOfRecord);
                dataFile.writeUTF(ev.name);
                dataFile.seek(filePosition*byteSizeOfRecord+22);
                dataFile.writeUTF(ev.type);
                dataFile.seek(filePosition*byteSizeOfRecord+35);
                dataFile.writeByte(ev.day);
                dataFile.seek(filePosition*byteSizeOfRecord+36);
                dataFile.writeByte(ev.month);
                dataFile.seek(filePosition*byteSizeOfRecord+37);
                dataFile.writeInt(ev.year);
                dataFile.seek(filePosition*byteSizeOfRecord+41);
                dataFile.writeBoolean(ev.occurrence);
                dataFile.seek(filePosition*byteSizeOfRecord+42);
                dataFile.writeByte(ev.fDay);
                dataFile.seek(filePosition*byteSizeOfRecord+43);
                dataFile.writeByte(ev.fMonth);
                dataFile.seek(filePosition*byteSizeOfRecord+44);
                dataFile.writeInt(ev.fYear);
                dataFile.seek(filePosition*byteSizeOfRecord+48);
                dataFile.writeByte(ev.hour);
                dataFile.seek(filePosition*byteSizeOfRecord+49);
                dataFile.writeByte(ev.minute);
                dataFile.seek(filePosition*byteSizeOfRecord+50);
                dataFile.writeUTF(ev.location);
                dataFile.seek(filePosition*byteSizeOfRecord+70);
                dataFile.writeUTF(ev.importance);
                dataFile.seek(filePosition*byteSizeOfRecord+87);
                dataFile.writeUTF(ev.description);        
        
                //Close the File
                dataFile.close();            
        }
            
        catch(IOException e){
            //If there are problems Writing on the File output the message
            System.out.println("Problem writing file");
            //Show where in the Program the Error is occurring 
            e.printStackTrace();
        }
        System.out.println(ev.name);
    }
    
    /**
    * Purpose: To access the Event in the designated position in the File
    * Pre-Condition: The File must exist, the position in the File must not be empty
    * Post-Condition: The Event is returned
    */
    public Event readEvent(int filePosition){
        System.out.println("Reading File");
        //Event that will be defined with the details in the File at filePosition
        Event read;
        //initialise local variables
        String name = "";
        String type = "";
        byte day = 0;
        byte month = 0;
        int year = 0;
        boolean occurrence = false;
        byte fDay = 0;
        byte fMonth = 0;
        int fYear = 0;
        byte hour = 0;
        byte minute = 0;
        String location = "";
        String importance = "";
        String description = "";
        
        //make dummy varriable x to equate 0 byte
        byte x = 0;
            try{
                //Access the Random Access File where the event is stored
                RandomAccessFile dataFile = new RandomAccessFile(new File(fileName),"r");
                
                //Determine the position where the details of the Event are stored
                dataFile.seek(filePosition*byteSizeOfRecord);
                name = dataFile.readUTF();
                dataFile.seek(filePosition*byteSizeOfRecord+22);
                type = dataFile.readUTF();
                dataFile.seek(filePosition*byteSizeOfRecord+35);
                day = dataFile.readByte();
                dataFile.seek(filePosition*byteSizeOfRecord+36);
                month = dataFile.readByte();
                dataFile.seek(filePosition*byteSizeOfRecord+37);
                year = dataFile.readInt();
                dataFile.seek(filePosition*byteSizeOfRecord+41);
                occurrence = dataFile.readBoolean();
                dataFile.seek(filePosition*byteSizeOfRecord+42);
                fDay = dataFile.readByte();
                dataFile.seek(filePosition*byteSizeOfRecord+43);
                fMonth = dataFile.readByte();
                dataFile.seek(filePosition*byteSizeOfRecord+44);
                fYear = dataFile.readInt();
                dataFile.seek(filePosition*byteSizeOfRecord+48);
                hour = dataFile.readByte();
                dataFile.seek(filePosition*byteSizeOfRecord+49);
                minute = dataFile.readByte();
                dataFile.seek(filePosition*byteSizeOfRecord+50);
                location = dataFile.readUTF();
                dataFile.seek(filePosition*byteSizeOfRecord+70);                
                importance = dataFile.readUTF();
                dataFile.seek(filePosition*byteSizeOfRecord+87);
                description = dataFile.readUTF();
                
                //Close the File
                dataFile.close();
               

            }
            catch(EOFException e){
                //If the end of the File is reached output the message
                System.out.println("End of File Reached");
                //Show where in the Program the Error is occurring 
                e.printStackTrace();
            }         
            catch(IOException e){
                //If there are problems Writing on the File output the message
                System.out.println("Problem Reading the File");
                //Show where in the Program the Error is occurring 
                e.printStackTrace();               
            }
        //Set the details obtained to the parameters of the Event read
        read = new Event(name, type, day, month, year, occurrence, fDay, fMonth, fYear, hour, minute, location, importance, description);    
        //Return Event read
        return read;
    }
    
    public void writeEntries(int entry, int filePosition){
        try{
            //Access or Create the Random Access File where the entries will be stored
            RandomAccessFile dataFile = new RandomAccessFile(new File(fileName),"rw");
            
            //Determine the position to write the entries in
            dataFile.seek(filePosition);
            dataFile.writeInt(entry);
            
            //Close File
            dataFile.close();
        }
        catch(IOException e){
            //Show where the problem writing the File exists
            e.printStackTrace();
        }
        System.out.println(entry);
    }
    
    public int readEntries(int filePosition){
        //Initialise local variable
        int entry = 0;
        try{
            //Access  the Random Access File where the entries are stored
            RandomAccessFile dataFile = new RandomAccessFile(new File(fileName),"r");
                
            //Determine the position to write the entries in
            dataFile.seek(filePosition);
            entry = dataFile.readInt();
            
            //Close File
            dataFile.close();
        }
        catch(IOException e){
            //Show where the problem reading the File lies
            e.printStackTrace();
        }
        //Return entry
        return entry;
    }
    
    /**
    * Purpose: To determine the amount of Events stored in the File
    * Pre-Condition: The File must exist
    * Post-Condition: The number of Events is returned
    */
    public int numberOfRecords(){
        
        try{
            //Access  the Random Access File where the Events are stored
            RandomAccessFile dataFile = new RandomAccessFile(new File("datafile2.dat"),"r");
            //Get the length of the File
            numberOfRecords = dataFile.length();
            //Close File
            dataFile.close();
            System.out.println("Records in file: "+numberOfRecords);
        
        }
        catch(IOException e){
            System.out.println("Problem Calculating Size of File");
            //If there are problems set number of records equal to zero
            numberOfRecords = 0;
            //Show where the problem lies
            e.printStackTrace();
        }        
        //Return the number of Events stored by dividing the total size of File with the amount of data one Event occupies
        return (int)numberOfRecords/byteSizeOfRecord;
    }

}
