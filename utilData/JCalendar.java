import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * JCalendar - calendar interactor
 * 
 * <pre>
 *  Date Created:  2/13/2004
 * Last Modified:  2/13/2004
 * </pre>
 *
 * <p><i>
 * Copyright (c) 2004 Georgia Institute of Technology. All Rights Reserved.
 * </i>
 *
 * <p><i>
 * Permission to use, copy, modify, and distribute this software
 * and its documentation for NON-COMMERCIAL purposes and without
 * fee is hereby granted provided that this copyright notice
 * appears in all copies.
 * </i>
 *
 * <p><i>
 * GEORGIA TECH AND THE AUTHOR(S) MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT 
 * THE SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT
 * LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE, OR NON-INFRINGEMENT.  GEORGIA TECH AND THE AUTHOR(S)  
 * SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF
 * USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS DERIVATIVES.
 * </i>
 * 
 * @version 0.1
 * @author  <a href="mailto:khai@cc.gatech.edu">Khai N. Truong</a>
 */
public class JCalendar
  extends JPanel
  implements MouseListener
{
  //----------------------------------------------------------------------------
  // CONSTANT(S)
  //----------------------------------------------------------------------------
  
  public static final String[] LABELS = {"D","S","T","Q","Q","S","S"};

  public static final int DEFAULT_WIDTH = JDatePicker.DEFAULT_WIDTH;
  public static final int DEFAULT_HEIGHT = JDatePicker.DEFAULT_WIDTH;
  
  //----------------------------------------------------------------------------
  // INSTANCE VARIABLE(S)
  //----------------------------------------------------------------------------

  protected Vector cells = new Vector();
  
  protected int month = Calendar.FEBRUARY; // for lack of a better value to initialize to
  protected int year = 2004;

  //// action listeners (to know that a date has been picked)
  protected Vector listeners = new Vector();
  
  //----------------------------------------------------------------------------
  // CONSTRUCTOR(S)
  //----------------------------------------------------------------------------

  /**
   * Constructor
   */
  public JCalendar()
  {
    this.setBackground(Color.white);
    this.setLayout(new GridLayout(7,7));

    Font font = new Font("Dialog",Font.PLAIN,12);
    
    for (int i=0; i<LABELS.length; i++)
    {
      JDateLabel l = new JDateLabel(LABELS[i],JLabel.CENTER);
      l.setFont(font);
      l.setForeground(Color.lightGray);
      l.setBackground(Color.gray);
      
      add(l);
    }
    
    for (int i=0; i<7-1; i++)
    {
      for (int j=0; j<7; j++)
      {
        JDateLabel l = new JDateLabel(new String(),JLabel.CENTER);
        l.setFont(font);
        l.setForeground(Color.black);
        l.setBackground(Color.white);
        l.addMouseListener(this);

        add(l);
        
        cells.addElement(l);
      }
    }
    
    show(year,month);
  } // end of JCalendar()

  //----------------------------------------------------------------------------
  // INSTANCE METHOD(S)
  //----------------------------------------------------------------------------

  /**
   * Add an action listener
   * @param l is the ActionListener
   */
  public void addActionListener(ActionListener l)
  {
    listeners.addElement(l);
  } // end of addActionListener()

  /**
   * Remove an action listener
   * @param l is the ActionListener
   */
  public void removeActionListener(ActionListener l)
  {
    listeners.removeElement(l);
  } // end of removeActionListener()

  /**
   * Gets the year picked
   * @return int value year
   */
  public int getYear()
  {
    return(this.year);
  } // end of getYear()
  
  /**
   * Gets the month picked
   * @return int value month
   */
  public int getMonth()
  {
    return(this.month);
  } // end of getMonth()
  
  /**
   * Gets the day of the month picked
   * @return int value day
   */
  public int getDay()
  {
    int day = -1;
    
    //// traverse through cells and find what was selected...
    for (int i=0; i<cells.size(); i++)
    {
      JDateLabel l = (JDateLabel) cells.elementAt(i);
      if (l.isSelected())
      {
        day = Integer.parseInt(l.getText());
        break;
      }
    }
    
    return(day);
  } // end of getDay()

  /**
   * Method for selecting a day on the interactor.
   * @param day is the int value day to select
   */
  public void selectDay(int day)
  {
    for (int i=0; i<cells.size(); i++)
    {
      JDateLabel l = (JDateLabel) cells.elementAt(i);
      l.select(false);

      if (l.getText().length() > 0)
      {
        if (day == Integer.parseInt(l.getText()))
          l.select(true);
      }
    }
  } // end of selectDay()
  
  /**
   * Sets the year and month to show in the interactor
   * @param year is the int value year
   * @param month is the int value month (from Calendar.JANUARY to Calendar.DECEMBER)
   */
  public void show(int year,
                   int month)
  {
    this.year = year;
    this.month = month;
    
    for (int i=0; i<cells.size(); i++)
    {
      JDateLabel l = (JDateLabel) cells.elementAt(i);
      l.setText(new String());
      l.select(false);
    }
    
    GregorianCalendar calendar = new GregorianCalendar();
    calendar.set(Calendar.YEAR,year);
    calendar.set(Calendar.MONTH,month);
    calendar.set(Calendar.DAY_OF_MONTH,1);

    int actual_maximum_days = calendar.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
    int first_day_of_month = calendar.get(GregorianCalendar.DAY_OF_WEEK);

    boolean started = false;
    boolean ended = false;
    
    int count = 1;

    for (int i=0; i<cells.size(); i++)
    {
      if (i >= (first_day_of_month - 1))
        started = true;
      
      if (started && (! ended)) 
      {
        JDateLabel l = (JDateLabel) cells.elementAt(i);
        l.setText(new Integer(count).toString());
        
        count++;
      }
      
      if (count > actual_maximum_days)
        ended = true;
    }
  } // end of show()
  
  /**
   * Callback method for when mouse button is pressed
   * @param e is the MouseEvent
   */
  public void mousePressed(MouseEvent e)
  {
    JDateLabel l = (JDateLabel) e.getSource();
    if (l.getText().length() == 0)
    {
      l.select(false);
    }
    else
    {
      for (int i=0; i<cells.size(); i++)
      {
        JDateLabel l2 = (JDateLabel) cells.elementAt(i);
        l2.select(false);
      }

      l.select(true);

      ActionEvent ae = new ActionEvent(this,ActionEvent.ACTION_PERFORMED,(month+1) + "/" + l.getText() + "/" + year);
      for (int i=0; i<listeners.size(); i++)
      {
        ActionListener al = (ActionListener) listeners.elementAt(i);
        al.actionPerformed(ae);
      }
    }

    getParent().getParent().repaint();
  } // end of mousePressed()

  public void mouseReleased(MouseEvent e) {}
  public void mouseClicked(MouseEvent e) {}
  public void mouseEntered(MouseEvent e) {}
  public void mouseExited(MouseEvent e) {}
  
  /**
   * Return the preferred size of the component to the layout manager
   * @return preferred Dimension
   */
  public Dimension getPreferredSize()
  {
    return(getMinimumSize());
  } // end of getPreferredSize()

  /**
   * Return the minimum size of the component to the layout manager
   * @return minimum Dimension
   */
  public Dimension getMinimumSize()
  {
    return(new Dimension(DEFAULT_WIDTH,DEFAULT_HEIGHT));
  } // end of getMinimumSize()
  
  //----------------------------------------------------------------------------
  // MAIN
  //----------------------------------------------------------------------------

  /**
   * Main method.  Test appliation for the JCalendar component.
   * @param argv is the array of String command line arguments
   */
  public static void main(String argv[])
  {
    JFrame frame = new JFrame();
    frame.setSize(300,300);
    
    frame.getContentPane().setLayout(new FlowLayout());
    frame.getContentPane().add(new JCalendar());
    
    frame.setVisible(true);
  } // end of main()
  
} // end of JCalendar class



