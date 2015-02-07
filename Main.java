import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.GregorianCalendar;
import java.util.ArrayList;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Write a description of class Main here.
 * 
 * Dennis Zinzi
 * v1.1
 */
public class Main extends JPanel implements ActionListener, KeyListener, WindowListener, ListSelectionListener
{
    //Buttons that appear on the Main Frame 
    private JButton add;
    private JButton modify;
    private JButton delete;
    private JButton exit;

    //The List filled with the Events
    public static JList eventList;

    //Labels that change as a different Event is selected
    private JLabel dateL;
    private JLabel typeL;
    private static JLabel dayL;
    private static JLabel monthL;
    private static JLabel yearL;
    private static JLabel typeLabel;
    private static JLabel finalDateL;
    private static JLabel fDayL;
    private static JLabel fMonthL;
    private static JLabel fYearL;
    private JLabel slash2;
    private JLabel slash1;
    private JLabel slash4;
    private JLabel slash3;
    private JLabel timeL;
    private static JLabel hourL;
    private JLabel colon;
    private static JLabel minuteL;
    private JLabel locationLabel;
    private static JLabel locationL;
    private static JLabel nameL;
    private JLabel importanceLabel;
    private static JLabel importanceL;
    private JLabel descriptionLabel;
    private static JTextArea descriptionL;

    //The Frame of the Main window
    public static JFrame main;
    //The Frame that opens as an action from the Main
    public static JFrame child;

    //Determines whether a child Frame exists
    public static boolean childExists = false;

    //Number of Events stored on the File
    public static int entries;
    //The index selected on the List
    public int index;

    //The List that references the Events
    public static ArrayList events;
    //The list model that populates the JList
    public static DefaultListModel model;
    //A Scrolable List
    private JScrollPane scrollList;

    /**
    * Constructor for Main Class
    */
    public Main() {
        //construct preComponents        
        events = new ArrayList();
        //Fill the array list
        Main.fillArrayList();
        events.toArray();

        //construct components
        add = new JButton ("Add Event");
        modify = new JButton ("Modify Event");
        delete = new JButton ("Delete Event");

        //eventList = new JList (eventListItems);
        exit = new JButton ("Exit");
        dateL = new JLabel ("Date :");
        typeLabel = new JLabel ("________________");
        dayL = new JLabel ("__");
        yearL = new JLabel ("__");
        monthL = new JLabel ("__");
        typeL = new JLabel ("Type :");
        finalDateL = new JLabel ("Final Date :");
        fDayL = new JLabel ("__");
        fMonthL= new JLabel ("__");
        fYearL = new JLabel ("__");
        slash2 = new JLabel ("/");
        slash1 = new JLabel ("/");
        slash4 = new JLabel ("/");
        slash3 = new JLabel ("/");
        timeL = new JLabel ("Time :");
        hourL = new JLabel ("__");
        colon = new JLabel (":");
        minuteL = new JLabel ("__");
        locationLabel = new JLabel ("Location :");
        locationL = new JLabel ("_________");
        importanceLabel = new JLabel ("Importance :");
        importanceL = new JLabel ("_________");
        nameL = new JLabel ("NAME");
        locationL = new JLabel ("____________");
        descriptionLabel = new JLabel ("Description :");
        descriptionL = new JTextArea ("_____________");
        descriptionL.setLineWrap(true);

        //set components properties
        finalDateL.setEnabled (false);
        fDayL.setEnabled (false);
        fMonthL.setEnabled (false);
        fYearL.setEnabled (false);
        slash4.setEnabled (false);
        slash3.setEnabled (false);
        descriptionL.setEnabled (false);

        //adjust size and set layout
        setPreferredSize (new Dimension (692, 438));
        setLayout (null);
        
        //Initialize the list model
        model = new DefaultListModel();   
        //Populate the JList with the list model
        eventList = new JList (model);
        //Make the JList scrollable
        scrollList = new JScrollPane(eventList);

        //Populate the list model with the Events in the ArrayList
        for(int i=0;i<events.size();i++){
            model.addElement(events.get(i));
        }
        //Set the List to have a fixed length
        eventList.setFixedCellWidth(90);

        //Set only selection to be single
        eventList.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);

        //add components
        add (add);
        add (modify);
        add (delete);

        //add (eventList);
        add(scrollList);
        add (exit);
        add (dateL);
        add (typeLabel);
        add (dayL);
        add (yearL);
        add (monthL);
        add (typeL);
        add (finalDateL);
        add (fDayL);
        add (fMonthL);
        add (fYearL);
        add (slash2);
        add (slash1);
        add (slash4);
        add (slash3);
        add (timeL);
        add (hourL);
        add (colon);
        add (minuteL);
        add (locationLabel);
        add (locationL);
        add (importanceLabel);
        add (importanceL);
        add (nameL);
        add (locationL);
        add (descriptionLabel);
        add (descriptionL);

        //set component bounds (only needed by Absolute Positioning)
        add.setBounds (540, 45, 130, 25);
        modify.setBounds (540, 120, 130, 25);
        delete.setBounds (540, 205, 130, 25);

        scrollList.setBounds (30, 40, 130, 360);
        exit.setBounds (540, 390, 130, 25);
        dateL.setBounds (250, 95, 40, 25);
        typeLabel.setBounds (310, 60, 100, 25);
        dayL.setBounds (310, 95, 25, 25);
        yearL.setBounds (375, 95, 50, 25);
        monthL.setBounds (345, 95, 25, 25);
        typeL.setBounds (250, 60, 40, 25);
        finalDateL.setBounds (250, 130, 75, 25);
        fDayL.setBounds (325, 130, 20, 25);
        fMonthL.setBounds (355, 130, 20, 25);
        fYearL.setBounds (385, 130, 20, 25);
        slash2.setBounds (365, 95, 10, 25);
        slash1.setBounds (335, 95, 10, 25);
        slash4.setBounds (380, 130, 10, 25);
        slash3.setBounds (345, 130, 10, 25);
        timeL.setBounds (250, 165, 40, 25);
        hourL.setBounds (310, 165, 20, 25);
        colon.setBounds (335, 165, 5, 25);
        minuteL.setBounds (345, 165, 25, 25);
        locationLabel.setBounds (250, 250, 65, 25);
        locationL.setBounds (340, 250, 85, 25);
        importanceLabel.setBounds (250, 200, 85, 25);
        importanceL.setBounds (340, 200, 85, 25);
        nameL.setBounds (300, 15, 125, 25);
        descriptionLabel.setBounds (250, 325, 100, 25);
        descriptionL.setBounds (335, 305, 100, 75);

        //add the actionlisteners for necessary items
        exit.addActionListener(this);
        add.addActionListener(this);
        modify.addActionListener(this);
        delete.addActionListener(this);

        //add Keylisteners to necessary items
        add.addKeyListener(this);
        modify.addKeyListener(this);
        delete.addKeyListener(this);
        exit.addKeyListener(this);

        //add Window Listener to this frame
        main.addWindowListener(this);

        //Set Default element selected to the first one
        eventList.setSelectedIndex(0);
        //Show the details of the first Event
        Main.showEventDetails(0);
        
        Main.today();
        
        
        //Code from 240-253 adapted from http://www.java2s.com/Tutorial/Java/0240__Swing/ListeningtoJListEventswithaListSelectionListener.htm
        ListSelectionListener listSelectionListener = new ListSelectionListener() {
                public void valueChanged(ListSelectionEvent listSelectionEvent) {
                    boolean adjust = listSelectionEvent.getValueIsAdjusting();
                    if (!adjust) {
                        JList list = (JList) listSelectionEvent.getSource();
                        int selections[] = list.getSelectedIndices();
                        Object selectionValues[] = list.getSelectedValues();
                        for (int i = 0;  i < selections.length; i++) {
                            if (i == 0) {
                                System.out.println(" Selections: ");
                            }
                            System.out.println(selections[i] + "/" + selectionValues[i] + " ");
                            System.out.println(selections.length);
                            Main.showEventDetails(selections[i]);
                        }
                    }
                }
            }
        ;
        //Add the List Selection Listener
        eventList.addListSelectionListener(listSelectionListener);

    }

    /**
    * Purpose: Determine if a child window exists
    * Pre-Condition: Child does not exist
    * Post-Condition: Set child's existence to false
    */
    public static void unsetChildExists(){
        childExists = false;
    }

    /**
    * Purpose: Fills the Jlist with the name of the Events Stored
    * Pre-Condition: The File exists, and Events are stored in it
    * Post-Condition: JList is filled and ready to use
    */
    public static void fillArrayList(){
        //Variable to determine number of events in file
        int numberOfRecords;
        //Create a file to determine amount of Events stored
        FileHandler file = new FileHandler("datafile2.dat");
        //Find number of events stored in file
        numberOfRecords = file.numberOfRecords();
        //Set the entries equal to the number of events stored
        entries = numberOfRecords;
        //Create an access to the number of Events on File
        FileHandler entry= new FileHandler("entries.dat");
        //Create variable to determine the number of entries on File
        int size;
        //Set the size to the number of Entries on File
        size = entry.readEntries(0);
        //entries = size;
        System.out.println("||||");
        System.out.println("Real Size is: "+size);
        System.out.println("||||");
        //Create null Events for latter use in the filling of the Array
        Event temp; 
        Event temp1;
        Event temp2;
        //Determine file to be read
        FileHandler readFile = new FileHandler("datafile2.dat");
        
        for(int i = 0;i<=size;i++){            
            //Read the event in position i in the File
            temp = readFile.readEvent(i);
            for(int j = 0; j<=i; j++){
                //Read the Event in position j
                temp1 = readFile.readEvent(j);
                System.out.println("HELLO ·^_^·");
                //As long as i is not 0 do
                if(i!=0){
                    //As long as j is not 0 do
                    if(j!=0){
                        //Check if Event in position i and j have the same name
                        if(!temp.name.equals(temp1.name)){
                            //If the two Events have different names check another Event prior to the one at j
                            temp2 = readFile.readEvent(j-1);
                            //If the Events i position j and j-1 have different names
                            if(!temp1.name.equals(temp2.name)){
                                System.out.println("J is "+j);
                                //Add the name of the Event at the end of the List
                                events.add(temp.name);
                                //System.out.println("J is: "+j);
                                System.out.println(temp.name);
                                //End this loop
                                break;
                            }
                            //If the Events in j and j-1 have the same name 
                            else{System.out.println("Same Name");}
                        }
                    }
                    //While j is 0
                    else if(j==0){
                        //Check if Event in position i and j have the same name
                        if(!temp.name.equals(temp1.name)){
                            //If the two Events have different names check another Event after the one at j
                            temp2 = readFile.readEvent(j+1);
                            //If the Events i position j and j+1 have different names
                            if(!temp1.name.equals(temp2.name)){
                                System.out.println("J is 0");
                                System.out.println("I is "+i);
                                //Add the name of the Event at the end of the List
                                events.add(temp.name);
                                //System.out.println("J is: "+j);
                                System.out.println(temp.name);
                                //End the loop
                                break;
                            }
                        }
                    }
                }
                //While i is 0
                else if(i==0){
                    //Add the Event
                    events.add(temp.name);
                    System.out.println(temp.name);
                    //End the loop
                    break;
                }
                //If i equates the size number
                else if(i == size){
                    //Do nothing
                }
                //If i equates the size+1
                else if(i==size+1){
                    //Do nothing
                }
                else{
                    //End loop
                    break;
                }
            }
        }

    }

    /**
    * Purpose: To run the program
    * Pre-Condition: None
    * Post-Condition: Program starts
    */
    public static void main (String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable()
            {
                public void run(){
                    //Create the GUI for the Main Class
                    createAndShowGUI();
                }
            }
        );
    }

    /**
    * Purpose: Determine today's date and time, and if and an Event is within a weeks time alert the user
    * Pre-Condition: The File exists, and Events must be stored in it
    * Post-Condition: If an event is within a weeks time, an alert is shown. Date and time are obtained
    */
    public static void today(){
        //Local varriables
        byte day;
        byte month;
        int year;
        byte hour;
        byte minute;
        byte dayOfWeek;
        String giorno="";
        
        //Create an Instance for the Gregorian Calendar class
        GregorianCalendar gregorianCalendar=new GregorianCalendar();
        //set the day equal to the day of the current real month
        day = (byte)gregorianCalendar.get(GregorianCalendar.DAY_OF_MONTH);
        //set the month equal to the current real month
        month = (byte)gregorianCalendar.get(GregorianCalendar.MONTH);
        //set the year equal to the current real year
        year = gregorianCalendar.get(GregorianCalendar.YEAR);
        //set the hour equal to the hour of the current real day on a 24 hour clock
        hour = (byte)gregorianCalendar.get(GregorianCalendar.HOUR_OF_DAY);
        //set the minute equal to the minutes of the current real hour
        minute = (byte)gregorianCalendar.get(GregorianCalendar.MINUTE);
        //set the day equal to the day of the current real week
        dayOfWeek = (byte)gregorianCalendar.get(GregorianCalendar.DAY_OF_WEEK);
        
        if(dayOfWeek==gregorianCalendar.SUNDAY){
            giorno = "Sunday";
        }
        else if(dayOfWeek==gregorianCalendar.MONDAY){
            giorno = "Monday";
        }
        else if(dayOfWeek==gregorianCalendar.TUESDAY){
            giorno = "Tuesday";
        }
        else if(dayOfWeek==gregorianCalendar.WEDNESDAY){
            giorno = "Wednesday";
        }
        else if(dayOfWeek==gregorianCalendar.THURSDAY){
            giorno = "Thursday";
        }
        else if(dayOfWeek==gregorianCalendar.FRIDAY){
            giorno = "Friday";
        }
        else if(dayOfWeek==gregorianCalendar.SATURDAY){
            giorno = "Saturday";
        }
        System.out.println(day+"/"+(month+1)+"/"+year);
        System.out.println(hour+":"+minute);
        System.out.println(giorno);
        

        //Create a file to determine amount of Events stored
        FileHandler file = new FileHandler("datafile2.dat");
        
        //Determine entries in File
        FileHandler entry= new FileHandler("entries.dat");
        //entry.writeEntries(entries,0);
        int size;
        size = entry.readEntries(0);
        //Set the entries equal to the size
        entries = size;
        //Determine file to be read
        FileHandler readFile = new FileHandler("datafile2.dat");
        //Create an Event temp for latter use
        Event temp; 

        for(int i = 0;i<=size;i++){ 
            //Temporary value to get current week day
            byte weekDay = dayOfWeek;
            //Read the Event in position i, and set Event temp with its details
            temp = readFile.readEvent(i);
            //If the date of Event temp is today
            if(temp.day==day && temp.month==month+1 && temp.year==year && temp.hour>=hour){// && temp.minute>minute){
                String newLine = System.getProperty("line.separator");
                //Alert/Remeind the user that the Event is today, and at what time
                JOptionPane.showMessageDialog(null, temp.name+" is Today"+newLine+"At "+temp.hour+":"+temp.minute, "Reminder", JOptionPane.WARNING_MESSAGE);
            }
            else if(temp.day==day+6 && temp.month==month+1 && temp.year==year){
                weekDay=(byte)((weekDay+6)%7);
                if(weekDay==gregorianCalendar.SUNDAY){
                    giorno = "Sunday";
                }
                else if(weekDay==gregorianCalendar.MONDAY){
                    giorno = "Monday";
                }
                else if(weekDay==gregorianCalendar.TUESDAY){
                    giorno = "Tuesday";
                }
                else if(weekDay==gregorianCalendar.WEDNESDAY){
                    giorno = "Wednesday";
                }
                else if(weekDay==gregorianCalendar.THURSDAY){
                    giorno = "Thursday";
                }
                else if(weekDay==gregorianCalendar.FRIDAY){
                    giorno = "Friday";
                }
                else if(weekDay==gregorianCalendar.SATURDAY){
                    giorno = "Saturday";
                }
                String newLine = System.getProperty("line.separator");
                //Alert/Remeind the user that the Event is in six days and at what time
                JOptionPane.showMessageDialog(null, temp.name+" is "+giorno+newLine+"At "+temp.hour+":"+temp.minute, "Reminder", JOptionPane.WARNING_MESSAGE);
            }
            else if(temp.day==day+5 && temp.month==month+1 && temp.year==year){
                weekDay=(byte)((weekDay+5)%7);
                if(weekDay==gregorianCalendar.SUNDAY){
                    giorno = "Sunday";
                }
                else if(weekDay==gregorianCalendar.MONDAY){
                    giorno = "Monday";
                }
                else if(weekDay==gregorianCalendar.TUESDAY){
                    giorno = "Tuesday";
                }
                else if(weekDay==gregorianCalendar.WEDNESDAY){
                    giorno = "Wednesday";
                }
                else if(weekDay==gregorianCalendar.THURSDAY){
                    giorno = "Thursday";
                }
                else if(weekDay==gregorianCalendar.FRIDAY){
                    giorno = "Friday";
                }
                else if(weekDay==gregorianCalendar.SATURDAY){
                    giorno = "Saturday";
                }
                String newLine = System.getProperty("line.separator");
                //Alert/Remeind the user that the Event is in five days and at what time
                JOptionPane.showMessageDialog(null, temp.name+" is "+giorno+newLine+"At "+temp.hour+":"+temp.minute, "Reminder", JOptionPane.WARNING_MESSAGE);
            }
            else if(temp.day==day+4 && temp.month==month+1 && temp.year==year){
                weekDay=(byte)((weekDay+4)%7);
                if(weekDay==gregorianCalendar.SUNDAY){
                    giorno = "Sunday";
                }
                else if(weekDay==gregorianCalendar.MONDAY){
                    giorno = "Monday";
                }
                else if(weekDay==gregorianCalendar.TUESDAY){
                    giorno = "Tuesday";
                }
                else if(weekDay==gregorianCalendar.WEDNESDAY){
                    giorno = "Wednesday";
                }
                else if(weekDay==gregorianCalendar.THURSDAY){
                    giorno = "Thursday";
                }
                else if(weekDay==gregorianCalendar.FRIDAY){
                    giorno = "Friday";
                }
                else if(weekDay==gregorianCalendar.SATURDAY){
                    giorno = "Saturday";
                }
                String newLine = System.getProperty("line.separator");
                //Alert/Remeind the user that the Event is in four days at what time
                JOptionPane.showMessageDialog(null, temp.name+" is "+giorno+newLine+"At "+temp.hour+":"+temp.minute, "Reminder", JOptionPane.WARNING_MESSAGE);
            }
            else if(temp.day==day+3 && temp.month==month+1 && temp.year==year){                
                weekDay=(byte)((weekDay+3)%7);
                if(weekDay==gregorianCalendar.SUNDAY){
                    giorno = "Sunday";
                }
                else if(weekDay==gregorianCalendar.MONDAY){
                    giorno = "Monday";
                }
                else if(weekDay==gregorianCalendar.TUESDAY){
                    giorno = "Tuesday";
                }
                else if(weekDay==gregorianCalendar.WEDNESDAY){
                    giorno = "Wednesday";
                }
                else if(weekDay==gregorianCalendar.THURSDAY){
                    giorno = "Thursday";
                }
                else if(weekDay==gregorianCalendar.FRIDAY){
                    giorno = "Friday";
                }
                else if(weekDay==gregorianCalendar.SATURDAY){
                    giorno = "Saturday";
                }
                String newLine = System.getProperty("line.separator");
                //Alert/Remeind the user that the Event is in three days and at what time
                JOptionPane.showMessageDialog(null, temp.name+" is "+giorno+newLine+"At "+temp.hour+":"+temp.minute, "Reminder", JOptionPane.WARNING_MESSAGE);
            }
            else if(temp.day==day+2 && temp.month==month+1 && temp.year==year){                
                weekDay=(byte)((weekDay+2)%7);
                if(weekDay==gregorianCalendar.SUNDAY){
                    giorno = "Sunday";
                }
                else if(weekDay==gregorianCalendar.MONDAY){
                    giorno = "Monday";
                }
                else if(weekDay==gregorianCalendar.TUESDAY){
                    giorno = "Tuesday";
                }
                else if(weekDay==gregorianCalendar.WEDNESDAY){
                    giorno = "Wednesday";
                }
                else if(weekDay==gregorianCalendar.THURSDAY){
                    giorno = "Thursday";
                }
                else if(weekDay==gregorianCalendar.FRIDAY){
                    giorno = "Friday";
                }
                else if(weekDay==gregorianCalendar.SATURDAY){
                    giorno = "Saturday";
                }
                String newLine = System.getProperty("line.separator");
                //Alert/Remeind the user that the Event is in two days and at what time
                JOptionPane.showMessageDialog(null, temp.name+" is "+giorno+newLine+"At "+temp.hour+":"+temp.minute, "Reminder", JOptionPane.WARNING_MESSAGE);
            }
            else if(temp.day==day+1 && temp.month==month+1 && temp.year==year){
                String newLine = System.getProperty("line.separator");
                //Alert/Remeind the user that the Event is tommorrow and at what time
                JOptionPane.showMessageDialog(null, temp.name+" is Tomorrow"+newLine+"At "+temp.hour+":"+temp.minute, "Reminder", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    /**
    * Purpose: To show the details of the Event selected on the screen
    * Pre-Condition: The File must exist, and the JList must not be empty
    * Post-Condition: The details of the selected Event are shown
    */
    public static void showEventDetails(int index){

        //Variable to determine number of events in file
        int numberOfRecords;
        //Create a file to determine amount of Events stored
        FileHandler file = new FileHandler("datafile2.dat");
        //Find number of events stored in file
        numberOfRecords = file.numberOfRecords();
        //Set the entries equal to the number of events stored
        entries = numberOfRecords;
        //Determine file to be read
        FileHandler readFile = new FileHandler("datafile2.dat");

        Event selected;
        //Set the Event selected to be the Event on File at the position at the index
        selected = readFile.readEvent(index);
        System.out.println(selected.fDay);
        //Get the details of the Event at position index in File, and set the appropriate labels and fields with the according information
        nameL.setText(selected.name);
        typeLabel.setText(selected.type);
        dayL.setText(selected.day+"");
        monthL.setText(selected.month+"");
        yearL.setText(selected.year+"");
        //If the final day is not 0, then a final date exists, and must be considered
        if(selected.fDay!=0){
            //Set the fields related with the Final Date with the appropriate information
            fDayL.setText(selected.fDay+"");
            fMonthL.setText(selected.fMonth+"");
            fYearL.setText(selected.fYear+"");
            //Set the fields related with the Final Date enabled
            finalDateL.setEnabled (true);
            fDayL.setEnabled (true);
            fMonthL.setEnabled (true);
            fYearL.setEnabled (true);
        }
        hourL.setText(selected.hour+"");
        minuteL.setText(selected.minute+"");
        locationL.setText(selected.location);
        importanceL.setText(selected.importance);
        descriptionL.setText(selected.description);

        //If no Event is selected output an Error Message
        if(index==-1){
            JOptionPane.showMessageDialog(null, "NO EVENT SELECTED", "Error!!!", JOptionPane.WARNING_MESSAGE);
            System.out.println("NO EVENT SELECTED");
        }
    }

    /**
    * Purpose: To Delete the Event at the specified position
    * Pre-Condition: The File must exist
    * Post-Condition: The Event selected is deleted
    */
    public static void deleteEvent(int deleteRecord){
        //Determine file to be read
        FileHandler readFile = new FileHandler("datafile2.dat");
        //Determine file to be written upon
        FileHandler writeFile = new FileHandler("datafile2.dat");
        System.out.println("");
        System.out.println("Entries are: "+entries);
        System.out.println("");
        //Determine the number of Records to read
        FileHandler entry = new FileHandler("entries.dat");
        //Make size variable
        int size;
        //Make updated size variable
        int sizeNow;
        //size = entry.readEntries(0);

        Event delete;
        if(entries>0){
            //If the Last Event on File is set to be deleted
            if(entries==deleteRecord+1){
                //Remove the Event at position deleteRecord
                events.remove(deleteRecord);
                events.trimToSize();
                model.removeElementAt(deleteRecord);
                //Get original size
                size = entry.readEntries(0);
                //Decrease size of File by one
                entry.writeEntries((size-1),0);
                
                System.out.println("Size is "+size);
                //Repopulate the File with the remaining Events
                for(int i = 0; i < size-1; i++){
                    delete = readFile.readEvent(i);
                    writeFile.writeEvent(delete,i);
                }
            }
            else{
                //Remove Event at position deleteRecord
                events.remove(deleteRecord);
                events.trimToSize();
                model.removeElementAt(deleteRecord);
                entries--;
                //entry.writeEntries(entries+1,0);
                System.out.println("E: "+entry.readEntries(0));
                //Get original size
                size = entry.readEntries(0);
                sizeNow = size-1;
                //Set new size to the original minus one
                entry.writeEntries(sizeNow,0);
                System.out.println("Entries are = "+entry.readEntries(0));
                //Populate the List with the Event at the position after deleteRecord, and decrease their index by one
                for(int i = deleteRecord; i<sizeNow; i++){
                    delete = readFile.readEvent(i+1);
                    writeFile.writeEvent(delete, i);
                }
                
                System.out.println("Size is "+sizeNow);
                System.out.println("entries are "+entries);
                System.out.println(entry.readEntries(0));
            }
        }
    }

    /**
    * Purpose: To create and output the GUI
    * Pre-Condition: None
    * Post-Condition: The GUI is created and shown
    */
    public static void createAndShowGUI(){
        JFrame.setDefaultLookAndFeelDecorated(true);
        main = new JFrame("Main");
        main.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        Main contentPane = new Main();
        contentPane.setOpaque(true);
        main.setContentPane(contentPane);
        main.pack();
        main.setLocationRelativeTo(null);
        main.setVisible(true);
    }

    /**
    * Purpose: To perform an action whenever a certain button is pressed
    * Pre-Condition: Buttons must exist
    * Post-Condition: Performs actions when buttons are pressed
    */
    public void actionPerformed(ActionEvent e){
        //find out the source 
        Object source = e.getSource();
        
        //If the Add button is pressed
        if(source==add){
            //If no child frame currently exists
            if(childExists == false){
                //Access the EventGUI class
                EventGUI.createAndShowGUI();
            }
            else{
                //Bring currently active child window to front
                child.toFront();
            }
        }

        //If the Modify button is pressed
        if(source==modify){
            //Determine the element selected
            index = eventList.getSelectedIndex();
            //As long as the index is not -1 (which means no Event is selected)
            if(index!=-1){
                //If no child frame currently exists
                if(childExists==false){
                    //Access the ModifyGUI class
                    ModifyGUI.createAndShowGUI(index);
                }
                else{
                    //Bring currently active child window to front
                    child.toFront();
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "NO EVENT SELECTED", "Error!!!", JOptionPane.WARNING_MESSAGE);
                System.out.println("NO EVENT SELECTED");
            }
        }

        //If the Delete button is pressed
        if(source==delete){
            //If no child frame currently exists
            if(childExists == false){
                //Determine the element selected
                index = eventList.getSelectedIndex();
                //As long as the index is not -1 (which means no Event is selected)
                if(index!=-1){
                    //Delete the desired Event at position index
                    Main.deleteEvent(index);
                }
                else{
                    JOptionPane.showMessageDialog(null, "NO EVENT SELECTED", "Error!!!", JOptionPane.WARNING_MESSAGE);
                    System.out.println("NO EVENT SELECTED");
                }
                System.out.println("");
            }
            else{
                //Bring currently active child window to front
                child.toFront();
            }
        }

        //If the Exit button is pressed
        if(source==exit){
            //If a child frame currently exists
            if(childExists==true){
                JOptionPane.showMessageDialog(null, "YOU MUST CLOSE OTHER WINDOW FIRST", "WARNING", JOptionPane.WARNING_MESSAGE);
                //Bring currently active child window to front
                child.toFront();
            }
            else{
                //exit the window
                System.exit(0);
            }
        }

    }

    /**
    * Purpose: Perform action when a certain key is pressed
    * Pre-Condition: None
    * Post-Condition: Action when key is pressed
    */
    public void keyPressed(KeyEvent e){
        //find out the source 
        Object source = e.getSource();

        //variable to check what key is being pressed
        int key = e.getKeyCode();

        //If the user types contemporarly W and the control key, the window will close
        if(key == KeyEvent.VK_W && e.isControlDown()){
            System.exit(0);
        }
    }

    public void keyReleased(KeyEvent e){

    }

    public void keyTyped(KeyEvent e){

    }

    public void windowActivated(WindowEvent e){

    } 

    public void windowClosed(WindowEvent e){
        System.out.println("closing");
        //Close the Window
        System.exit(0);
    }

       /**
    * Purpose: Closes Window if no Child exists
    * Pre-Condition: None
    * Post-Condition: window is closed
    */
    public void windowClosing(WindowEvent e){
        //If a child frame currently exists
        if(childExists==true){
            JOptionPane.showMessageDialog(null, "YOU MUST CLOSE OTHER WINDOW FIRST", "WARNING", JOptionPane.WARNING_MESSAGE);
            //Bring child window to the front
            child.toFront();
        }
        else{
            System.out.println("closing");
            //Close the window
            System.exit(0);
        }
    }

    public void windowDeactivated(WindowEvent e){ 

    }

    public void windowDeiconified(WindowEvent e){

    }

    public void windowIconified(WindowEvent e){

    }

    public void windowOpened(WindowEvent e){ 

    }

    
    public void valueChanged(ListSelectionEvent e){
        
    }
    
}
