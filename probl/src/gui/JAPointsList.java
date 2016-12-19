package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import datasource.SemiFileDS;
import geometry.Point;
import probl.App;

public class JAPointsList extends JPanel{
	private final static String defaultFile = "ds.txt";
	private JList<Point> formPointList = new JList<>();
	private JButton showDialogButton;
	
	private File selectedSourceFile = new File(defaultFile);
	private List<Point> selectedPoints = formPointList.getSelectedValuesList();
	
	public JAPointsList() {
	    setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setBorder(BorderFactory.createTitledBorder("Points"));
   
        showDialogButton = new JButton("Open");
	    
	    formPointList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
	    formPointList.setLayoutOrientation(JList.HORIZONTAL_WRAP);  
	    
	    this.add(new JScrollPane(formPointList));
	    this.add(showDialogButton);
	    
	    showDialogButton.addActionListener(new ActionListener()
	    {
	      public void actionPerformed(ActionEvent e)
	      {
	    	//Handle open button action.
	    	    if (e.getSource() == showDialogButton) {
	    	    	JFileChooser fc = new JFileChooser(selectedSourceFile);
	    	        int returnVal = fc.showOpenDialog(getParent());

	    	        if (returnVal == JFileChooser.APPROVE_OPTION) {
	    	        	App.log.info(this.getClass(), "Opening: " + selectedSourceFile.getName() + ".");
	    	        	try {
							App.ds = new SemiFileDS(selectedSourceFile);
							Point[] pointArray = new Point[App.ds.getFormPoints().size()];
							formPointList.setListData(App.ds.getFormPoints().toArray(pointArray));
						} catch (IOException e1) {
							App.log.info(this.getClass(), e1.getMessage());							
						}
	    	        	selectedSourceFile = fc.getSelectedFile();        	            
	    	        } else {
	    	        	App.log.info(this.getClass(), "Open command cancelled by user." + "\n");
	    	        }
	    	   }
	      }
	    });	    
	}

	public File getSelectedSourceFile() {
		return selectedSourceFile;
	}

	public List<Point> getSelectedPoints() {
		return selectedPoints;
	}	   
	
}