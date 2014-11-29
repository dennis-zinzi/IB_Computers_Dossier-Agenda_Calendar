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
public class DeleteEvent extends JPanel implements ActionListener, WindowListener
{ 
    //instance variable
    private JButton delete;
    private JButton exit;
    private JList eventList;
    private JMenuBar menu;
    public static int entries;
    public int index;
    public static ArrayList events;
    public static DefaultListModel model;
    private JScrollPane scrollList;
    //int numberOfRecords
    
    public DeleteEvent() {
        //construct preComponents        
        events = new ArrayList();
        //Fill the array list
        DeleteEvent.fillArrayList();
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
        delete = new JButton ("Delete");
        exit = new JButton ("Exit");
        
        model = new DefaultListModel();   
        eventList = new JList (model);//events.toArray());
        scrollList = new JScrollPane(eventList);
        
        for(int i=0;i<events.size();i++){
            model.addElement(events.get(i));
        }
        
        //;(events.toArray())//eventListItems);
        eventList.setVisibleRowCount(15);
        eventList.setFixedCellWidth(90);
        
        
        menu = new JMenuBar();
        menu.add (fileMenu);
        menu.add (helpMenu);
        
        //Set only selection to be single
        eventList.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);


        //adjust size and set layout
        setPreferredSize (new Dimension (295, 332));
        setLayout (null);

        //add components
        add (delete);
        add (exit);
        add (menu);
        add (scrollList);
        //add (eventList);
        
        //set component bounds (only needed by Absolute Positioning)
        delete.setBounds (170, 250, 100, 20);
        exit.setBounds (170, 290, 100, 20);        
        menu.setBounds (0, 0, 290, 20);
        scrollList.setBounds(15, 35, 130, 280);
        //eventList.setBounds (15, 35, 130, 280);
        
        //add the actionlisteners for necessary items
        exit.addActionListener(this);
        delete.addActionListener(this);
        
        //add window listeners
        Main.child.addWindowListener(this);
    }

    public static void main (String[] args) {
        /*frame = new JFrame("DeleteEvent");
        frame.setDefaultCloseOperation (JFrame.HIDE_ON_CLOSE);
        frame.getContentPane().add (new DeleteEvent());        
        frame.pack();
        frame.setVisible (true);*/
    }
    
    public static void createAndShowGUI(){
        Main.child = new JFrame("Delete Event");
        Main.child.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        DeleteEvent childContentPane = new DeleteEvent();
        childContentPane.setOpaque(true);
        Main.child.setContentPane(childContentPane);
        
        Main.child.setLocationRelativeTo(null);
        Main.child.pack();
        Main.child.setVisible(true);
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
        //FileHandler entry = new FileHandler("entries.dat");
        int numberOfEntries;
        //FileHandler entry = new FileHandler("entries.dat");
        //numberOfEntries = entry.readEntries(0);
        
        //entries = numberOfEntries;
        //System.out.println("Entries in file are :"+entry.readEntries(0));
        System.out.println("ENTRIES: "+entries);
        //entries = numberOfEntries;*/
        Event temp; 
        
        
        for(int i = 0;i<=entries;i++){            
            temp = readFile.readEvent(i);
            events.add(i,temp.name);
            System.out.println(temp.name);
        }
        
        
    }
    
    public static void deleteEvent(int deleteRecord){
        //Determine file to be read
        FileHandler readFile = new FileHandler("datafile2.dat");
        //Determine file to be written upon
        FileHandler writeFile = new FileHandler("datafile2.dat");
        System.out.println("");
        System.out.println("Entries are: "+entries);
        System.out.println("");
        FileHandler entry= new FileHandler("entries.dat");
        int size;
        //size=entry.readEntries(0);
        
        Event delete;
        if(entries>0){
            if(entries==deleteRecord+1){
                events.remove(deleteRecord);
                events.trimToSize();
                model.removeElementAt(deleteRecord);
                entries--;
                entry.writeEntries(entries,0);
                size = entry.readEntries(0);
                System.out.println("Size is "+size);
                //FileHandler newFile = new FileHandler("datafile2.dat");
                //newFile.setLength(0);
                for(int i = 0; i < size; i++){
                    delete = readFile.readEvent(i);
                    writeFile.writeEvent(delete,i);
                }
                //entries--;
            }
            else{
                events.remove(deleteRecord);
                events.trimToSize();
                model.removeElementAt(deleteRecord);
                entries--;
                entry.writeEntries(entries,0);
                size = entry.readEntries(0);
                System.out.println("Size is "+size);
                //FileHandler newFile = new FileHandler("datafile2.dat");
                //newFile.setLength(0);
                for(int i = 0; i < size; i++){
                    delete = readFile.readEvent(i);
                    writeFile.writeEvent(delete,i);
                }
                /*
                FileHandler newFile = new FileHandler("datafile3.dat");
                newFile.setLength(0);
                for(int i = 0; i < entries; i++){
                    delete = readFile.readEvent(i);
                    newFile.writeEvent(delete,i);
                }
                /*events.remove(deleteRecord);
                events.trimToSize();
                model.removeElementAt(deleteRecord);
                for(int pos = deleteRecord; pos < entries-1; pos++){
                    delete = readFile.readEvent(pos+1);
                    writeFile.writeEvent(delete,pos);
                }
                entries--;*/
            }
        }
        //long o = 0;
        /*FileHandler deleted = new FileHandler("datafile2.dat");
        deleted.setLength();
        for(int i = 0; i<events.size(); i++){
            delete = readFile.readEvent(i);
            deleted.writeEvent(delete,i);
        }*/
        System.out.println("Entries now are: "+entries);
            
    }
    
    public void actionPerformed(ActionEvent e){
        //find out the source
        Object source = e.getSource();
        
        if(source==exit){
            Main.child.dispose();
            //System.exit(0);
        }
        
        if(source==delete){
            System.out.println("PRESSED");
            //Determine the element selected
            index = eventList.getSelectedIndex();
            System.out.println("Index is "+index);
            if(index!=-1){
                DeleteEvent.deleteEvent(index);
                //Remove element from list
                //events.remove(index);
                //events.trimToSize();
                //model.removeElementAt(index);
            }
            else{
                JOptionPane.showMessageDialog(null, "NO EVENT SELECTED", "Error!!!", JOptionPane.WARNING_MESSAGE);
                System.out.println("NO EVENT SELECTED");
            }
            //this.index = index;
        }
    }
    
    public void windowActivated(WindowEvent e){

    } 
          
    public void windowClosed(WindowEvent e){
        Main.unsetChildExists();
    }

    public void windowClosing(WindowEvent e){
        Main.unsetChildExists();
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
    