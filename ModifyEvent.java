import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.io.*;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JScrollPane;
import javax.swing.JList;

/**
 * Class where a saved events properties are changed
 * 
 * Dennis Zinzi 
 * v1.0
 */
public class ModifyEvent extends JPanel implements ActionListener, WindowListener
{ 
    //instance variable
    private JButton select;
    private JButton exit;
    private JList eventList;
    private JMenuBar menu;
    public static int entries;
    public int index;
    public static ArrayList events;
    public DefaultListModel model;
    private JScrollPane scrollList;
    //private String[] dummyList;
    public static JFrame child;
    public static boolean childExists = false;
    
    public ModifyEvent() {
        //construct preComponents        
        events = new ArrayList();
        //Fill the array list
        ModifyEvent.fillArrayList();
        events.toArray();
       
        JMenu fileMenu = new JMenu ("File");
        JMenuItem printItem = new JMenuItem ("Print");
        fileMenu.add (printItem);
        JMenuItem exitItem = new JMenuItem ("Exit");
        fileMenu.add (exitItem);
        JMenu helpMenu = new JMenu ("Help");
        JMenuItem contentsItem = new JMenuItem ("Contents");
        helpMenu.add (contentsItem);
        JMenuItem aboutItem = new JMenuItem ("About");
        helpMenu.add (aboutItem);
        
        //construct components
        select = new JButton ("Select");
        exit = new JButton ("Exit");
        
        model = new DefaultListModel();   
        eventList = new JList (model);//events.toArray());
        scrollList = new JScrollPane(eventList);
        //index = eventList.getSelectedIndex();
        
        for(int i=0;i<events.size();i++){
            model.addElement(events.get(i));
        }
        
        //;(events.toArray())//eventListItems);
        eventList.setVisibleRowCount(15);
        eventList.setFixedCellWidth(90);
        
        
        //JPanel panel = new JPanel();
        //panel.add(new JScrollPane(eventList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));
        //setContentPane(panel);
        
        
        menu = new JMenuBar();
        menu.add (fileMenu);
        menu.add (helpMenu);
        
        eventList.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
        //eventList.setVerticalScrollBar(scrollList); 


        //adjust size and set layout
        setPreferredSize (new Dimension (295, 332));
        setLayout (null);

        //add components
        add (select);
        add (exit);
        //add (eventList);
        add (menu);
        add (scrollList);

        //set component bounds (only needed by Absolute Positioning)
        select.setBounds (170, 250, 100, 20);
        exit.setBounds (170, 290, 100, 20);
        //eventList.setBounds (15, 35, 130, 280);
        menu.setBounds (0, 0, 290, 20);
        scrollList.setBounds(15, 35, 130, 280);
        
        //add the actionlisteners for necessary items
        exit.addActionListener(this);
        select.addActionListener(this);
        
        //add Window Listener
        Main.child.addWindowListener(this);
    }

    public static void main (String[] args) {
        /*frame = new JFrame("ModifyEvent");
        frame.setDefaultCloseOperation (JFrame.HIDE_ON_CLOSE);
        frame.getContentPane().add (new ModifyEvent());        
        frame.pack();
        frame.setVisible (true);*/
    }
    
    public static void unsetChildExists(){
        childExists = false;
    }
    
    public static void fillArrayList(){
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
        
        Event temp; 
        
        for(int i = 0;i<=entries;i++){            
            temp = readFile.readEvent(i);
            events.add(i,temp.name);
            System.out.println(temp.name);
        }
        
    }
    
    public static void createAndShowGUI(){
        Main.child = new JFrame("Modify Event");
        Main.child.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        ModifyEvent childContentPane = new ModifyEvent();
        childContentPane.setOpaque(true);
        Main.child.setContentPane(childContentPane);
        
        Main.child.setLocationRelativeTo(null);
        Main.child.pack();
        Main.child.setVisible(true);
    }
    
    public void actionPerformed(ActionEvent e){
        //find out the source
        Object source = e.getSource();
        
        if(source==exit){
            //System.exit(0);
            
            if(childExists==true){
                JOptionPane.showMessageDialog(null, "YOU MUST CLOSE OTHER WINDOW FIRST", "WARNING", JOptionPane.WARNING_MESSAGE);
                child.toFront();
            }
            else{
                System.out.println("closing");
                Main.child.dispose();
                //System.exit(0);
            }
        }
        
        if(source==select){
            //Determine the element selected
            //int index;
            index = eventList.getSelectedIndex();
            if(index!=-1){
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
        
                Event modify;
                modify = readFile.readEvent(index);
                System.out.println(modify.name+" is the name");
                if(childExists==false){
                    childExists = true;
                    ModifyGUI.createAndShowGUI(index);
                }
                else{
                    child.toFront();
                }
                /*
                JFrame child = new JFrame ("Modify Event");
                child.getContentPane().add (new ModifyGUI());
                child.pack();
                child.setVisible (true);
                frame.setEnabled(false);*/
            }
            else{
                JOptionPane.showMessageDialog(null, "NO EVENT SELECTED", "Error!!!", JOptionPane.WARNING_MESSAGE);
                System.out.println("NO EVENT SELECTED");
            }
            //this.index = index;
        }
    }
    
    public int getSelectedIndex(){
        //int index;
        index = eventList.getSelectedIndex();
        return index;
    }
    
    public void windowActivated(WindowEvent e){
        //Main.childExists=true;
    } 
          
    public void windowClosed(WindowEvent e){
        Main.unsetChildExists();
        //Main.childExists=false;
        
    }

    public void windowClosing(WindowEvent e){
        Main.unsetChildExists();
        if(childExists==true){
            JOptionPane.showMessageDialog(null, "YOU MUST CLOSE OTHER WINDOW FIRST", "WARNING", JOptionPane.WARNING_MESSAGE);
        }
        else{
            System.out.println("closing");
            System.exit(0);
        }
        //Main.childExists=false;
    }

    public void windowDeactivated(WindowEvent e){ 

    }
    
    public void windowDeiconified(WindowEvent e){

    }
    
    public void windowIconified(WindowEvent e){
        
    }

    public void windowOpened(WindowEvent e){ 
        Main.childExists=true;
    }
}






/*
    public static void load(){
        //Variable to determine number of events in file
        int numberOfRecords;
        //Create a file to determine amount of Events stored
        FileHandler file = new FileHandler("datafile2.dat");
        //Find number of events stored in file
        numberOfRecords = file.numberOfRecords();
        //Set the entries equal to the number of events stored
        entries = numberOfRecords;
        System.out.println(entries);
        //Determine file to be read
        FileHandler readFile = new FileHandler("datafile2.dat");
        
        Event temp;    
        for(int i = 0;i<=entries;i++){
            temp = readFile.readEvent(i);
            System.out.println("Name of Event is "+temp.name);
            System.out.println("The type of Event is " + temp.type);
            System.out.println("Occurs on the date "+temp.day+"/"+temp.month+"/"+temp.year);
            System.out.println("Every Year Occurrence is "+temp.occurrence);
            if(temp.fDay != 0){
                System.out.println("The final date is "+temp.fDay+"/"+temp.fMonth+"/"+temp.fYear);
            }
            else{
                System.out.println("There is no final date");
            }
            System.out.println("The Event occurs at time "+temp.hour+":"+temp.minute);
            if(!temp.location.equals("")){
                System.out.println("The Event is located at "+temp.location);
            }
            else{
                System.out.println("The location of the event is not specified");
            }
            System.out.println("The importance of such Event is "+temp.importance);
            if(!temp.discription.equals("")){
                System.out.println("A brief discription of such event is: "+temp.discription);
            }
            else{
                System.out.println("");
            }
        }
        
    }*/