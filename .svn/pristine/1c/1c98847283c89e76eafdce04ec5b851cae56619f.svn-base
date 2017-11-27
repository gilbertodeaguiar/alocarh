import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.Calendar;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;

/**
 * JPopupCalendar - Popup window holding the calendar and controls for navigating month & year info.
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
public class JPopupCalendar
  extends JWindow
  implements ComponentListener
           , ActionListener
{
  //----------------------------------------------------------------------------
  // INSTANCE VARIABLE(S)
  //----------------------------------------------------------------------------

  protected JSmallButton left_button = new JSmallButton("<<");
  protected JSmallButton right_button = new JSmallButton(">>");

  protected Window owner;
  protected JDatePicker parent;
  protected JCalendar calendar = new JCalendar();
  
  protected JLabel month_label = new JLabel(" September 2004 ",JLabel.CENTER);
  protected boolean showing = false;
  
  //----------------------------------------------------------------------------
  // CONSTRUCTOR(S)
  //----------------------------------------------------------------------------

  /**
   * Constructor
   * @param window is the parent window (up the interactor hierarchy) holding this widget
   * @param parent is the JDatePicer this popup window should stay connected to
   */
  public JPopupCalendar(Window window, 
                        JDatePicker parent)
  {
    super(window);
    this.owner = window;
    this.parent = parent;
    
    setSize(JCalendar.DEFAULT_WIDTH,JCalendar.DEFAULT_HEIGHT+JSmallButton.DEFAULT_HEIGHT + 4);
    

    month_label.setFont(new Font("Dialog",Font.PLAIN,10));
    month_label.setForeground(Color.white);

    JPanel panel = new JPanel();
    panel.setBackground(Color.blue);
    panel.setLayout(new BorderLayout());

    JPanel west_panel = new JPanel();
    west_panel.setBackground(Color.blue);
    west_panel.setLayout(new FlowLayout());
    west_panel.add(left_button);

    JPanel east_panel = new JPanel();
    east_panel.setBackground(Color.blue);
    east_panel.setLayout(new FlowLayout());
    east_panel.add(right_button);

    panel.add(BorderLayout.WEST,west_panel);
    panel.add(month_label);
    panel.add(BorderLayout.EAST,east_panel);
    
    getContentPane().setLayout(new BorderLayout());
    getContentPane().add(BorderLayout.NORTH,panel);
    getContentPane().add(BorderLayout.CENTER,calendar);

    this.showing = false;
    this.setVisible(false);
    
    calendar.addActionListener(this);
    
    left_button.addActionListener(this);
    right_button.addActionListener(this);
  } // end of JPopupCalendar() constructor
  
  //----------------------------------------------------------------------------
  // INSTANCE METHOD(S)
  //----------------------------------------------------------------------------

  /**
   * Callback method for when an action event occurs
   * @param e is the ActionEvent
   */
  public void actionPerformed(ActionEvent e)
  {
    int month = calendar.getMonth();
    int year = calendar.getYear();

    Object source = e.getSource();
    
    //// the left button (move back a month)
    if (source == left_button)
    {
      if (month == Calendar.JANUARY)
      {
        month = Calendar.DECEMBER;
        year--;
      }
      else
      {
        month--;
      }
      
      show(year,month,-1);
    }
    //// the right button (move forward a month)
    else if (source == right_button)
    {
      if (month == Calendar.DECEMBER)
      {
        month = Calendar.JANUARY;
        year++;
      }
      else
      {
        month++;
      }
      
      show(year,month,-1);
    }
    //// the calendar itself (a day has been selected, therefor go away)
    else if (source == calendar)
    {
      //// set the date and disappear
      month = calendar.getMonth();
      year = calendar.getYear();
      int day = calendar.getDay();

      parent.set(year,month,day);
      this.setVisibleState(false);
    }
  } // end of actionPerformed()
  
  /**
   * Show a particular date
   * @param year is the int value year
   * @param month is the int value month of year (from Calendar.JANUARY to Calendar.DECEMBER)
   * @param day is the int value day of month
   */
  public void show(int year,
                   int month,
                   int day)
  {
    String label = JDatePicker.MONTHS[month] + " " + year;
    month_label.setText(label);
    
    calendar.show(year,month);
    calendar.selectDay(day);
    repaint();
  } // end of show()
  
  /**
   * Paints the popup window...
   * @param g is the Graphics object to paint with
   */
  public void paint(Graphics g)
  {
    super.paint(g);
    
    g.setColor(Color.black);
    g.drawRect(0,0,getSize().width-1,getSize().height-1);
  } // end of paint()

  /**
   * Callback method for when the widget first appears on the screen.
   */
  public void addNotify()
  {
    // this is important because I want to physically connect this popup window to the JDatePicker
    // I can only register the component listener when that widget itself is added to the screen
    // so I had to go thorugh addNotify
    
    super.addNotify();
    owner.addComponentListener(this);
  } // end of addNotify()

  /**
   * Call back method for when a comopnent is moved
   * @param e is the COmponentEvent specficying that a component has been moved
   */
  public void componentMoved(ComponentEvent e) 
  {
    //// this is actually monitoring the JDatePicker component...since this widget is
    //// interested in its movement, I decided to implement this here..
    
    try
    {
      this.setLocation(parent.getLocationOnScreen().x,parent.getLocationOnScreen().y + parent.getSize().height);
    }
    catch(Exception exception)
    {
      //exception.printStackTrace();
    }
  }

  public void componentHidden(ComponentEvent e) {}
  public void componentResized(ComponentEvent e) {}
  public void componentShown(ComponentEvent e) {}
  
  /**
   * Toggles the Visibility of the popup calendar
   */
  public void toggleVisibility()
  {
    this.setLocation(parent.getLocationOnScreen().x,parent.getLocationOnScreen().y + parent.getSize().height);

    if (showing)
    {
      this.showing = false;
      this.setVisible(false);
      this.transferFocus();
    }
    else
    {
      this.showing = true;
      this.setVisible(true);
      this.requestFocus();
    }
  } // end of toggleVisibility()
  
  
  /**
   * Sets the Visibility of the popup calendar
   * @param state is a boolean specifying the visible state of the popup calendar
   */
  public void setVisibleState(boolean state)
  {
    this.setLocation(parent.getLocationOnScreen().x,parent.getLocationOnScreen().y + parent.getSize().height);

    if (! state)
    {
      this.showing = false;
      this.setVisible(false);
      this.transferFocus();
    }
    else
    {
      this.showing = true;
      this.setVisible(true);
      this.requestFocus();
    }
  } // end of setVisibleState()
  
} // end of JPopupCalendar class



