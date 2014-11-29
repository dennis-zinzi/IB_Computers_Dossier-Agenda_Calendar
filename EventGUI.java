import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.io.*;
import java.io.IOException;
import java.util.GregorianCalendar;

/**
 * Class where an event is created via a GUI.
 * 
 * Dennis Zinzi
 * 
 * v1.0
 */

public class EventGUI extends JPanel implements ActionListener, KeyListener, ItemListener, WindowListener {
    //Buttons that Appear on the frame
    private JButton exit;
    private JButton create;
    //Menu components
    private JMenuBar menu;
    private JMenuItem printItem;
    private JMenuItem exitItem;
    private JMenuItem contentsItem;
    private JMenuItem aboutItem;
    //Instance Variables
    private JLabel nameLabel;
    private JTextField name;
    private JLabel typeLabel;
    private JLabel dateLabel;
    private JComboBox type;
    private JTextField day;
    private JLabel slash1;
    private JLabel slash2;
    private JTextField month;
    private JTextField year;
    private JCheckBox occurrence;
    private JCheckBox finalDate;
    private JTextField fDay;
    private JTextField fMonth;
    private JTextField fYear;
    private JLabel fSlash1;
    private JLabel fSlash2;
    private JLabel timeLabel;
    private JTextField hour;
    private JLabel colon;
    private JTextField minute;
    private JLabel locationLabel;
    private JTextField location;
    private JLabel importanceLabel;
    private JComboBox importance;
    private JLabel briefLabel;
    private JTextArea description;
    
    //Variable to determine number of Events on File
    public int entries;
    /**
    *  Constructor of Class to create a new Event
    */
    public EventGUI() {
        //construct preComponents
        JMenu fileMenu = new JMenu ("File");
        printItem = new JMenuItem ("Print");
        fileMenu.add (printItem);
        exitItem = new JMenuItem ("Exit");
        fileMenu.add (exitItem);
        JMenu helpMenu = new JMenu ("Help");
        contentsItem = new JMenuItem ("Contents");
        helpMenu.add (contentsItem);
        aboutItem = new JMenuItem ("About");
        helpMenu.add (aboutItem);
        String[] typeItems = {"Anniversary", "Appointment", "Assignment", "Conference", "Meeting", "Memo", "Holiday", "Interview", "Lesson"};
        String[] importanceItems = {"Optional", "Idle   ", "Normal", "Important", "Extreme", "Life or Death"};

        //construct components
        exit = new JButton ("Exit");
        create = new JButton ("Create");
        menu = new JMenuBar();
        menu.add (fileMenu);
        menu.add (helpMenu);
        nameLabel = new JLabel ("Name:");
        name = new JTextField (5);
        typeLabel = new JLabel ("Type:");
        dateLabel = new JLabel ("Date:");
        type = new JComboBox (typeItems);
        day = new JTextField (5);
        slash1 = new JLabel ("/");
        slash2 = new JLabel ("/");
        month = new JTextField (5);
        year = new JTextField (5);
        occurrence = new JCheckBox ("Annual Occurrence");
        finalDate = new JCheckBox ("Final Date:");
        fDay= new JTextField (5);
        fMonth = new JTextField (5);
        fYear = new JTextField (5);
        fSlash1 = new JLabel ("/");
        fSlash2 = new JLabel ("/");
        timeLabel = new JLabel ("Time:");
        hour = new JTextField (5);
        colon = new JLabel (":");
        minute = new JTextField (5);
        locationLabel = new JLabel ("Location:");
        location = new JTextField (5);
        importanceLabel = new JLabel ("Importance:");
        importance = new JComboBox (importanceItems);
        briefLabel = new JLabel ("Description:");
        description = new JTextArea (5, 5);
        description.setLineWrap(true);
        //set components properties
        fDay.setEnabled (false);
        fMonth.setEnabled (false);
        fYear.setEnabled (false);

        //adjust size and set layout
        setPreferredSize (new Dimension (366, 507));
        setLayout (null);

        //add components
        add (exit);
        add (create);
        add (menu);
        add (nameLabel);
        add (name);
        add (typeLabel);
        add (dateLabel);
        add (type);
        add (day);
        add (slash1);
        add (slash2);
        add (month);
        add (year);
        add (occurrence);
        add (finalDate);
        add (fDay);
        add (fMonth);
        add (fYear);
        add (fSlash1);
        add (fSlash2);
        add (timeLabel);
        add (hour);
        add (colon);
        add (minute);
        add (locationLabel);
        add (location);
        add (importanceLabel);
        add (importance);
        add (briefLabel);
        add (description);

        //set component bounds (only needed by Absolute Positioning)
        exit.setBounds (25, 470, 100, 25);
        create.setBounds (260, 470, 100, 25);
        menu.setBounds (0, 0, 425, 25);
        nameLabel.setBounds (80, 50, 55, 25);
        name.setBounds (145, 50, 150, 25);
        typeLabel.setBounds (80, 95, 55, 25);
        dateLabel.setBounds (80, 140, 55, 25);
        type.setBounds (145, 95, 150, 25);
        day.setBounds (145, 140, 35, 30);
        slash1.setBounds (180, 140, 25, 30);
        slash2.setBounds (230, 140, 25, 30);
        month.setBounds (190, 140, 35, 30);
        year.setBounds (240, 140, 50, 30);
        occurrence.setBounds (145, 175, 155, 25);
        finalDate.setBounds (35, 210, 100, 25);
        fDay.setBounds (145, 210, 35, 30);
        fMonth.setBounds (190, 210, 35, 30);
        fYear.setBounds (240, 210, 50, 30);
        fSlash1.setBounds (180, 210, 25, 30);
        fSlash2.setBounds (230, 210, 25, 30);
        timeLabel.setBounds (65, 255, 50, 25);
        hour.setBounds (145, 255, 35, 30);
        colon.setBounds (185, 255, 20, 25);
        minute.setBounds (190, 255, 35, 30);
        locationLabel.setBounds (65, 300, 70, 25);
        location.setBounds (145, 300, 145, 25);
        importanceLabel.setBounds (65, 340, 100, 25);
        importance.setBounds (145, 340, 145, 25);
        briefLabel.setBounds (65, 380, 100, 25);
        description.setBounds (145, 380, 150, 75);
        
        //add the actionlisteners for necessary items
        exit.addActionListener(this);
        finalDate.addActionListener(this);
        exitItem.addActionListener(this);
        create.addActionListener(this);
        
        
        //add Keylisteners to necessary items
        name.addKeyListener(this);
        type.addKeyListener(this);
        day.addKeyListener(this);
        month.addKeyListener(this);
        year.addKeyListener(this);
        occurrence.addKeyListener(this);
        finalDate.addKeyListener(this);
        fDay.addKeyListener(this);
        fMonth.addKeyListener(this);
        fYear.addKeyListener(this);
        minute.addKeyListener(this);
        hour.addKeyListener(this);
        location.addKeyListener(this);
        importance.addKeyListener(this);
        description.addKeyListener(this);
        create.addKeyListener(this);
        
        //add window listener
        Main.child.addWindowListener(this);
        
    }

    /**
    * Purpose: To create and output the GUI
    * Pre-Condition: None
    * Post-Condition: The GUI is created and shown
    */
    public static void createAndShowGUI(){
        Main.child = new JFrame("New Event");
        Main.child.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        EventGUI childContentPane = new EventGUI();
        childContentPane.setOpaque(true);
        Main.child.setContentPane(childContentPane);
        
        Main.child.setLocationRelativeTo(null);
        Main.child.pack();
        Main.child.setVisible(true);
        
    }
    
    public void actionPerformed(ActionEvent e){
        //find out the source
        Object source = e.getSource();
        
        //variable to check whether the final date checkbox is selected
        boolean check = finalDate.isSelected();
        
        //If the checkbox to set a final date is selected allow the user to input the date
        if(check==true){
            fDay.setEnabled (true);
            fMonth.setEnabled (true);
            fYear.setEnabled (true);
        }
        //If the checkbox to set the final date is not selected don't allow the user to input, and cancel existing input
        else{
            fDay.setEnabled (false);
            fDay.setText("");
            fMonth.setEnabled (false);
            fMonth.setText("");
            fYear.setEnabled (false);
            fYear.setText("");
        }
        
        //If the Create button is pressed
        if(source == create){
            //Get the text in the name field, and check if it is empty
            String nm = name.getText();
            //If the textfield is blank, then output a warning message to the user
            if(nm.equals("")){
                JOptionPane.showMessageDialog(null, "EMPTY NAME FIELD", "Error!!!", JOptionPane.WARNING_MESSAGE);
            }
            //If the text inputted is longer than 22 characters truncate it to a max of 22
            if(nm.length()>22){
                nm = nm.substring(0,22);
            }
            
            //Get the element number selected from dropdown menu
            int numT = 0;
            numT = type.getSelectedIndex();
            //Set Conditions to what choice it corresponds to
            String nType = "";
            if(numT == 0){
                nType = "Anniversary";
            }
            else if(numT == 1){
                nType = "Appointment";
            }
            else if(numT == 2){
                nType = "Assignment";
            }
            else if(numT == 3){
                nType = "Conference";
            }
            else if(numT == 4){
                nType = "Meeting";
            }
            else if(numT == 5){
                nType = "Memo";
            }
            else if(numT == 6){
                nType = "Holiday";
            }
            else if(numT == 7){
                nType = "Interview";
            }
            else if(numT == 8){
                nType = "Lesson";
            }
            
            
            //Get the text in the day jTextField
            String d = day.getText();
            //Determine if text field is blank
            if(d.equals("")){
                JOptionPane.showMessageDialog(null, "EMPTY DAY FIELD", "Error!!!", JOptionPane.WARNING_MESSAGE);
            }
            //Initialize variable to assume value inputted in day textfield
            byte nDay = 0;
            //Parse the String into a byte to be able to determine a feasible value for the day
            try{
                //Get the value that the user inputted
                nDay = Byte.valueOf(d);
                //If the value is not within these limits, then a warning message is shown
                if(1>nDay || nDay >31){
                    JOptionPane.showMessageDialog(null, "NOT A VALID DAY", "Error!!!", JOptionPane.WARNING_MESSAGE);
                }
            }
            catch(NumberFormatException ex){
                //If abnormal data is inputted then a warning message will be shown
                if(!d.equals("")){
                    day.setText("");
                    JOptionPane.showMessageDialog(null, "NOT A VALID DAY", "Error!!!", JOptionPane.WARNING_MESSAGE);
                }
            }
            
            //Get the text in the month jTextField
            String m = month.getText();
            //Determine if text field is blank
            if(m.equals("")){
                JOptionPane.showMessageDialog(null, "EMPTY MONTH FIELD", "Error!!!", JOptionPane.WARNING_MESSAGE);
            }
            //Initialize variable to assume value inputted in day textfield
            byte nMonth = 0;
            //Parse the String into a byte to be able to determine a feasable value for the month
            try{
                //Get the value the user inputted
                nMonth = Byte.valueOf(m);
                //If the value is not within these limits, then a warning message is shown
                if(0>nMonth || nMonth >12){
                    JOptionPane.showMessageDialog(null, "NOT A VALID MONTH", "Error!!!", JOptionPane.WARNING_MESSAGE);
                }
            }
            catch(NumberFormatException ex){
                //If abnormal data is inputted then a warning message will be shown
                if(!m.equals("")){
                    month.setText("");
                    JOptionPane.showMessageDialog(null, "NOT A VALID MONTH", "Error!!!", JOptionPane.WARNING_MESSAGE);
                }
            }
         
            if(nMonth==2 && nDay>28){
                day.setText("");
                JOptionPane.showMessageDialog(null, "NOT A VALID DAY", "Error!!!", JOptionPane.WARNING_MESSAGE);
            }
            
            //Get the text in the year jTextField
            String y = year.getText();
            //Determine if text field is blank
            if(y.equals("")){
                JOptionPane.showMessageDialog(null, "EMPTY YEAR FIELD", "Error!!!", JOptionPane.WARNING_MESSAGE);
            } 
            //Initialize variable to assume value inputted in day textfield
            int nYear = 0;
            //Create a Gregorian Calendar instance
            GregorianCalendar gregorianCalendar = new GregorianCalendar();
            //Declare current year varialble
            int currentYear;
            //Get the current real year
            currentYear = gregorianCalendar.get(GregorianCalendar.YEAR);
            //Parse the String into a byte to be able to determine a feasable value for the year
            try{
                //Get the value the user inputted
                nYear = Integer.valueOf(y);
                if(currentYear > nYear){
                    JOptionPane.showMessageDialog(null, "NOT A VALID YEAR", "Error!!!", JOptionPane.WARNING_MESSAGE);
                }
            }
            catch(NumberFormatException ex){
                //If abnormal data is inputted then a warning message will be shown
                if(!y.equals("")){
                    year.setText("");
                    JOptionPane.showMessageDialog(null, "NOT A VALID YEAR", "Error!!!", JOptionPane.WARNING_MESSAGE);
                }
            }

           //Get the text in the hour jTextField
            String h = hour.getText();
            //Determine if text field is blank
            if(h.equals("")){
                JOptionPane.showMessageDialog(null, "EMPTY HOUR FIELD", "Error!!!", JOptionPane.WARNING_MESSAGE);
            }   
            //Initialize variable to assume value inputted in day textfield
            byte nHour = 0;
            //Parse the String into a byte to be able to determine a feasable value for the year
            try{
                //Get the value the user inputted
                nHour = Byte.valueOf(h);
                //If the value is not within these limits, then a warning message is shown
                if(0>nHour || nHour >= 24){
                    JOptionPane.showMessageDialog(null, "NOT A VALID HOUR", "Error!!!", JOptionPane.WARNING_MESSAGE);
                }
            }
            catch(NumberFormatException ex){
                //If abnormal data is inputted then a warning message will be shown
                if(!h.equals("")){
                    hour.setText("");
                    JOptionPane.showMessageDialog(null, "NOT A VALID HOUR", "Error!!!", JOptionPane.WARNING_MESSAGE);
                }
            }
            
            //Get the text in the minute jTextField
            String min = minute.getText();
            //Determine if text field is blank
            if(min.equals("")){
                JOptionPane.showMessageDialog(null, "EMPTY MINUTE FIELD", "Error!!!", JOptionPane.WARNING_MESSAGE);
            }   
            //Initialize variable to assume value inputted in day textfield
            byte nMin = 0;
            //Parse the String into a byte to be able to determine a feasable value for the year
            try{
                //Get the value the user inputted
                nMin = Byte.valueOf(min);
                //If the value is not within these limits, then a warning message is shown
                if(0>nMin || nMin >= 60){
                    JOptionPane.showMessageDialog(null, "NOT A VALID MINUTE", "Error!!!", JOptionPane.WARNING_MESSAGE);
                }
            }
            catch(NumberFormatException ex){
                //If abnormal data is inputted then a warning message will be shown
                if(!min.equals("")){
                    minute.setText("");
                    JOptionPane.showMessageDialog(null, "NOT A VALID MINUTE", "Error!!!", JOptionPane.WARNING_MESSAGE);
                }
            }
            
            //Create varriable to check if occurrence is checked
            boolean everyYear;// = true;
            //Determine if the occurrence box is checked
            everyYear = occurrence.isSelected();
            
            //Get text in location field
            String loc = location.getText();
            //If the text inputted is longer than 20 characters truncate it to a max of 20
            if(loc.length()>20){
                loc = loc.substring(0,20);
            }
            //Get the element number selected from dropdown menu
            int numI = 0;
            numI = importance.getSelectedIndex();
            //Set Conditions to what choice it corresponds to
            String nImportance = "";
            if(numI == 0){
                nImportance = "Optional";
            }
            else if(numI == 1){
                nImportance = "Idle";
            }
            else if(numI == 2){
                nImportance = "Normal";
            }
            else if(numI == 3){
                nImportance = "Important";
            }
            else if(numI == 4){
                nImportance = "Extreme";
            }
            else if(numI == 5){
                nImportance = "Life or Death";
            }
            
            //Get the text in Description field
            String des = description.getText();
            //If the text inputted is longer than 75 characters truncate it to a max of 75
            if(des.length()>75){
                JOptionPane.showMessageDialog(null, "LETTER LIMIT REACHED, PLEASE DECREASE WORD COUNT IN DESCRIPTION", "Error!!!", JOptionPane.WARNING_MESSAGE);
                description.setText(des.substring(0,75));
                des = des.substring(0,75);
            }
            
            //If all previous conditions are met, then proceed to store the Event on the File
            if(!nm.equals("") && (0<nDay && nDay <=31) && (0<nMonth && nMonth <= 12) && (currentYear <= nYear) && (0<nHour && nHour <= 24) && (0<=nMin && nMin < 60)){
               
                
                //Set default value of Final day and month to zero
                byte x = 0;
                
                int numberOfRecords;                
                
                //Create a new Event with the appropriate fields
                Event newEvent = new Event(nm, nType, nDay, nMonth, nYear, everyYear, x, x, 0, nHour, nMin, loc, nImportance, des);
                newEvent.occurrence = everyYear;    
                
                FileHandler entry = new FileHandler("entries.dat");
                System.out.println("Now: "+entry.readEntries(0));
                int size;
                size = entry.readEntries(0);
                entry.writeEntries((size+1),0);    
                //Create a file to store the Events in
                FileHandler sizeOfFile = new FileHandler("datafile2.dat");
                //Get the number of events in the file
                numberOfRecords = sizeOfFile.numberOfRecords();
                //Set the number of entries equal to the number of records and add one as it starts counting from 0 in file
                entries = numberOfRecords+1;
                //Create the file to write the events in
                FileHandler writeFile = new FileHandler("datafile2.dat");               
                //Store the Event in the file
                writeFile.writeEvent(newEvent, (size+1));//entries);
                //Increment the entries in the file by one
                //entries++;
                //Main.eventList.add(newEvent.name);
                //Add Event to List in Main
                Main.model.addElement(newEvent.name);
                
                
                System.out.println("");
                
                System.out.println("After: "+(size+1));

                System.out.println("Event Created");
                    
                //Close window once the Event has been created
                Main.child.dispose();
                System.out.println(entries);
                
                System.out.println("");
                
                    System.out.println(newEvent.name);
                    System.out.println(newEvent.type);
                    System.out.println(newEvent.day);
                    System.out.println(newEvent.month);
                    System.out.println(newEvent.year);
                    System.out.println(newEvent.occurrence);
                    System.out.println(newEvent.hour);
                    System.out.println(newEvent.minute);                    
                    System.out.println(newEvent.location);
                    System.out.println(newEvent.importance);
                    System.out.println(newEvent.description);
                    
                System.out.println("Event Created");        
                    
            }
            else{
                System.out.println("Did not create");
            }
        }
        
        //If the Create button is pressed while the Final Date Option is selected
        if(source == create && check==true){
            //Get the text in the name field, and check if it is empty
            String nm = name.getText();
            //Check if the textfield is blank
            if(nm.equals("")){
                JOptionPane.showMessageDialog(null, "EMPTY NAME FIELD", "Error!!!", JOptionPane.WARNING_MESSAGE);
            }
            //If the length of the name exceeds 22 then truncate to a maximum length of 22
            if(nm.length()>22){
                nm = nm.substring(0,22);
            }
            
            //Get the element number selected from dropdown menu
            int numT = 0;
            numT = type.getSelectedIndex();
            //Set Conditions to what choice it corresponds to
            String nType = "";
            if(numT == 0){
                nType = "Anniversary";
            }
            else if(numT == 1){
                nType = "Appointment";
            }
            else if(numT == 2){
                nType = "Assignment";
            }
            else if(numT == 3){
                nType = "Conference";
            }
            else if(numT == 4){
                nType = "Meeting";
            }
            else if(numT == 5){
                nType = "Memo";
            }
            else if(numT == 6){
                nType = "Holiday";
            }
            else if(numT == 7){
                nType = "Interview";
            }
            else if(numT == 8){
                nType = "Lesson";
            }
            
            //Get the text in the day jTextField
            String d = day.getText();
            //Determine if text field is blank
            if(d.equals("")){
                JOptionPane.showMessageDialog(null, "EMPTY DAY FIELD", "Error!!!", JOptionPane.WARNING_MESSAGE);
            }
            //Initialize variable to assume value inputted in day textfield
            byte nDay = 0;
            //Parse the String into a byte to be able to determine a feasable value for the day
            try{
                //Get the value the user inputted
                nDay = Byte.valueOf(d);
                //If the value is not within these limits, then a warning message is shown
                if(0>nDay || nDay >31){
                    JOptionPane.showMessageDialog(null, "NOT A VALID DAY", "Error!!!", JOptionPane.WARNING_MESSAGE);
                }
            }
            catch(NumberFormatException ex){
                //If abnormal data is inputted then a warning message will be shown
                if(!d.equals("")){
                    day.setText("");
                    JOptionPane.showMessageDialog(null, "NOT A VALID DAY", "Error!!!", JOptionPane.WARNING_MESSAGE);
                }
            }
            
            //Get the text in the month jTextField
            String m = month.getText();
            //Determine if text field is blank
            if(m.equals("")){
                JOptionPane.showMessageDialog(null, "EMPTY MONTH FIELD", "Error!!!", JOptionPane.WARNING_MESSAGE);
            }
            //Initialize variable to assume value inputted in day textfield
            byte nMonth = 0;
            //Parse the String into a byte to be able to determine a feasable value for the month
            try{
                //Get the value the user inputted
                nMonth = Byte.valueOf(m);
                //If the value is not within these limits, then a warning message is shown
                if(0>nMonth || nMonth >12){
                    JOptionPane.showMessageDialog(null, "NOT A VALID MONTH", "Error!!!", JOptionPane.WARNING_MESSAGE);
                }
            }
            catch(NumberFormatException ex){
                //If abnormal data is inputted then a warning message will be shown
                if(!m.equals("")){
                    month.setText("");
                    JOptionPane.showMessageDialog(null, "NOT A VALID MONTH", "Error!!!", JOptionPane.WARNING_MESSAGE);
                }
            }
         
            //Get the text in the year jTextField
            String y = year.getText();
            //Determine if text field is blank
            if(y.equals("")){
                JOptionPane.showMessageDialog(null, "EMPTY YEAR FIELD", "Error!!!", JOptionPane.WARNING_MESSAGE);
            } 
            //Initialize variable to assume value inputted in day textfield
            int nYear = 0;
            int currentYear;
            //Create a Gregorian Calendar instance
            GregorianCalendar gregorianCalendar = new GregorianCalendar();
            //Determine the current real year
            currentYear = gregorianCalendar.get(GregorianCalendar.YEAR);
            
            //Parse the String into a byte to be able to determine a feasable value for the year
            try{
                //Get the value the user inputted
                nYear = Integer.valueOf(y);                
                if(currentYear > nYear){
                    JOptionPane.showMessageDialog(null, "NOT A VALID YEAR", "Error!!!", JOptionPane.WARNING_MESSAGE);
                }
            }
            catch(NumberFormatException ex){
                //If abnormal data is inputted then a warning message will be shown
                if(!y.equals("")){
                    year.setText("");
                    JOptionPane.showMessageDialog(null, "NOT A VALID YEAR", "Error!!!", JOptionPane.WARNING_MESSAGE);
                }
            }

           //Get the text in the hour jTextField
            String h = hour.getText();
            //Determine if text field is blank
            if(h.equals("")){
                JOptionPane.showMessageDialog(null, "EMPTY HOUR FIELD", "Error!!!", JOptionPane.WARNING_MESSAGE);
            }   
            //Initialize variable to assume value inputted in day textfield
            byte nHour = 0;
            //Parse the String into a byte to be able to determine a feasable value for the year
            try{
                //Get the value the user inputted
                nHour = Byte.valueOf(h);
                //If the value is not within these limits, then a warning message is shown
                if(0>nHour || nHour >= 24){
                    JOptionPane.showMessageDialog(null, "NOT A VALID HOUR", "Error!!!", JOptionPane.WARNING_MESSAGE);
                }
            }
            catch(NumberFormatException ex){
                //If abnormal data is inputted then a warning message will be shown
                if(!h.equals("")){
                    hour.setText("");
                    JOptionPane.showMessageDialog(null, "NOT A VALID HOUR", "Error!!!", JOptionPane.WARNING_MESSAGE);
                }
            }
            
            //Get the text in the minute jTextField
            String min = minute.getText();
            //Determine if text field is blank
            if(min.equals("")){
                JOptionPane.showMessageDialog(null, "EMPTY MINUTE FIELD", "Error!!!", JOptionPane.WARNING_MESSAGE);
            }   
            //Initialize variable to assume value inputted in day textfield
            byte nMin = 0;            
            //Parse the String into a byte to be able to determine a feasable value for the year
            try{
                //Get the value the user inputted
                nMin = Byte.valueOf(min);
                //If the value is not within these limits, then a warning message is shown
                if(0>nMin || nMin >= 60){
                    JOptionPane.showMessageDialog(null, "NOT A VALID MINUTE", "Error!!!", JOptionPane.WARNING_MESSAGE);
                }
            }
            catch(NumberFormatException ex){
                //If abnormal data is inputted then a warning message will be shown
                if(!min.equals("")){
                    minute.setText("");
                    JOptionPane.showMessageDialog(null, "NOT A VALID MINUTE", "Error!!!", JOptionPane.WARNING_MESSAGE);
                }
            }
            
            //Create varriable to check if occurrence is checked
            boolean everyYear;// = true;
            //Determine if the occurrence box is checked
            everyYear = occurrence.isSelected();
            
            //Get the text in location field
            String loc = location.getText();
            //If the text inputted is longer than 20 characters truncate it to a max of 20
            if(loc.length()>20){
                loc = loc.substring(0,20);
            }
            //determine if an Event occurrs every year
            boolean checks = occurrence.isSelected();
            String occ;
            if(checks==true){
                occ = "Yes";
            }
            else if(checks==false){
                occ = "No";
            }
            
            //Get the element number selected from dropdown menu
            int numI = 0;
            numI = type.getSelectedIndex();
            //Set Conditions to what choice it corresponds to
            String nImportance = "";
            if(numI == 0){
                nImportance = "Optional";
            }
            else if(numI == 1){
                nImportance = "Idle";
            }
            else if(numI == 2){
                nImportance = "Normal";
            }
            else if(numI == 3){
                nImportance = "Important";
            }
            else if(numI == 4){
                nImportance = "Extreme";
            }
            else if(numI == 5){
                nImportance = "Life or Death";
            }
            
            //Get the text in Description field
            String des = description.getText();
            //If the text inputted is longer than 75 characters truncate it to a max of 75
            if(des.length()>75){
                JOptionPane.showMessageDialog(null, "LETTER LIMIT REACHED, PLEASE DECREASE WORD COUNT IN DESCRIPTION", "Error!!!", JOptionPane.WARNING_MESSAGE);
                description.setText(des.substring(0,75));
            }
            
            //Get the text in the final day jTextField
            String fd = fDay.getText();
            //Determine if text field is blank
            if(fd.equals("")){
                JOptionPane.showMessageDialog(null, "EMPTY FINAL DAY FIELD", "Error!!!", JOptionPane.WARNING_MESSAGE);
            }
            //Initialize variable to assume value inputted in day textfield
            byte nfDay = 0;
            //Parse the String into a byte to be able to determine a feasable value for the final day
            try{
                //Get the value the user inputted
                nfDay = Byte.valueOf(fd);
                //If the value is not within these limits, then a warning message is shown
                if(0>nfDay || nfDay >31){
                    JOptionPane.showMessageDialog(null, "NOT A VALID FINAL DAY", "Error!!!", JOptionPane.WARNING_MESSAGE);
                }
            }
            //Causes a NumberFormatException since the value is out of range
            catch(NumberFormatException ex){
                //If abnormal data is inputted then a warning message will be shown
                if(!fd.equals("")){
                    fDay.setText("");
                    JOptionPane.showMessageDialog(null, "NOT A VALID FINAL DAY", "Error!!!", JOptionPane.WARNING_MESSAGE);
                }
            }
        
            //Get the text in the final month jTextField
            String fm = fMonth.getText();
            //Determine if text field is blank
            if(fm.equals("")){
                JOptionPane.showMessageDialog(null, "EMPTY FINAL MONTH FIELD", "Error!!!", JOptionPane.WARNING_MESSAGE);
            }
            //Initialize variable to assume value inputted in day textfield
            byte nfMonth = 0;
            //Parse the String into a byte to be able to determine a feasable value for the final month
            try{
                //Get the value the user inputted
                nfMonth = Byte.valueOf(fm);
                //If the value is not within these limits, then a warning message is shown
                if(0>nfMonth || nfMonth >31){
                    JOptionPane.showMessageDialog(null, "NOT A VALID FINAL MONTH", "Error!!!", JOptionPane.WARNING_MESSAGE);
                }
            }
            catch(NumberFormatException ex){
                //If abnormal data is inputted then a warning message will be shown
                if(!fm.equals("")){
                    fMonth.setText("");
                    JOptionPane.showMessageDialog(null, "NOT A VALID FINAL MONTH", "Error!!!", JOptionPane.WARNING_MESSAGE);
                }
            }
                     
            //Get the text in the final year jTextField
            String fy = fYear.getText();
            //Determine if text field is blank
            if(fy.equals("")){
                JOptionPane.showMessageDialog(null, "EMPTY FINAL YEAR FIELD", "Error!!!", JOptionPane.WARNING_MESSAGE);
            }   
            //Initialize variable to assume value inputted in day textfield
            int nfYear = 0;
            //Parse the String into a byte to be able to determine a feasable value for the final year
            try{
                //Get the value the user inputted
                nfYear = Integer.valueOf(fy);
                if(nYear>nfYear){ //&& nYear>2013
                    JOptionPane.showMessageDialog(null, "NOT A VALID FINAL YEAR", "Error!!!", JOptionPane.WARNING_MESSAGE);
                }
            }
            catch(NumberFormatException ex){
                //If abnormal data is inputted then a warning message will be shown
                if(!fy.equals("")){
                    fYear.setText("");
                    JOptionPane.showMessageDialog(null, "NOT A VALID FINAL YEAR", "Error!!!", JOptionPane.WARNING_MESSAGE);
                }
            }
            
            if(!nm.equals("") && (0<nDay && nDay <31) && (0<nMonth && nMonth < 13)  && (currentYear <= nYear) && (0<nHour && nHour < 25) && (0<nMin && nMin <= 60) && (0<nfDay && nfDay <=31) && (0<nfMonth && nfMonth <13) && (nYear<nfYear)){
                
                int numberOfRecords;                
                
                //Create a new Event with the appropriate fields
                Event newEvent = new Event(nm, nType, nDay, nMonth, nYear, everyYear, nfDay, nfMonth, nfYear, nHour, nMin, loc, nImportance, des);
                newEvent.occurrence = everyYear;
                //Determine number of Events stored on File
                FileHandler entry = new FileHandler("entries.dat");
                System.out.println("Now: "+entry.readEntries(0));
                int size;
                //Set size variable to number of Events on File
                size = entry.readEntries(0);
                //Increment number of Events on File by 1
                entry.writeEntries((size+1),0);    
                //Create a file to store the Events in
                FileHandler sizeOfFile = new FileHandler("datafile2.dat");
                //Get the number of events in the file
                numberOfRecords = sizeOfFile.numberOfRecords();
                //Set the number of entries equal to the number of records and add one as it starts counting from 0 in file
                entries = numberOfRecords+1;
                //Create the file to write the events in
                FileHandler writeFile = new FileHandler("datafile2.dat");               
                //Store the Event in the file
                writeFile.writeEvent(newEvent, size);//entries);
                //Increment the entries in the file by one
                //entries++;
                System.out.println(newEvent.fDay);
                
                System.out.println("");
                
                System.out.println("After: "+(size+1));
                
                System.out.println("Event Created");
                //Add Event to List in Main
                Main.model.addElement(newEvent.name);
                
                //Close Window upon creation of the Event
                Main.child.dispose();
                    
            }
            else{
                System.out.println("Did not create");
            }
        }
           
        //If the user clicks on the exit button or exit option in the Menu, close the window
        if(source==exit || source==exitItem){
            Main.child.dispose();
        }
    }
    
    public void itemStateChanged(ItemEvent e){
        if(e.getStateChange() == ItemEvent.SELECTED){
            int nr;
            nr = type.getSelectedIndex();
        }
    }
    
    public void keyPressed(KeyEvent e){
        //find out the source 
        Object source = e.getSource();
        
        //variable to check what key is being pressed
        int key = e.getKeyCode();
        
        //If the user types contemporarly W and the control key, the window will close
        if(key == KeyEvent.VK_W && e.isControlDown()){
            Main.child.dispose();
        }
        
    }
    
    public void keyReleased(KeyEvent e){
        
    }
    
    public void keyTyped(KeyEvent e){
        
    }
        
    public void windowActivated(WindowEvent e){
    
    }
          
    public void windowClosed(WindowEvent e){
        //Set the existance of a child frame to false
        Main.unsetChildExists();
    }

    public void windowClosing(WindowEvent e){
        //Set the existance of a child frame to false
        Main.unsetChildExists();
    }

    public void windowDeactivated(WindowEvent e){ 

    }
    
    public void windowDeiconified(WindowEvent e){

    }
    
    public void windowIconified(WindowEvent e){
        
    }

    public void windowOpened(WindowEvent e){ 
        //Set the existance of a child frame true
        Main.childExists=true;
    }
    
    }
