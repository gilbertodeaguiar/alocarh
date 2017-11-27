import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;
import javax.swing.JComponent;

/**
 * JSmallButton - Small Button for the popup calendar...because I couldn't get JButton to do what I wanted...
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
public class JSmallButton
  extends JComponent
  implements MouseListener
{
  //----------------------------------------------------------------------------
  // CONSTRUCTOR(S)
  //----------------------------------------------------------------------------

  public static final int DEFAULT_WIDTH = 18;
  public static final int DEFAULT_HEIGHT = 18;
  
  //----------------------------------------------------------------------------
  // INSTANCE VARIABLE(S)
  //----------------------------------------------------------------------------

  protected String str = new String();
  protected boolean selected = false;

  //// action listeners
  protected Vector listeners = new Vector();

  //----------------------------------------------------------------------------
  // CONSTRUCTOR(S)
  //----------------------------------------------------------------------------

  /**
   * Constructor
   * @param str is the String value to display in the button
   */
  public JSmallButton(String str)
  {
    this.str = str;
    this.addMouseListener(this);
  } // end of JSmallButton() constructor
  
  //----------------------------------------------------------------------------
  // INSTANCE METHOD(S)
  //----------------------------------------------------------------------------

  /**
   * Callback method for mouse button being pressed
   * @param e is the MouseEvent to handle
   */
  public void mousePressed(MouseEvent e)
  {
    //// fire action event
    ActionEvent ae = new ActionEvent(this,ActionEvent.ACTION_PERFORMED,str);
    for (int i=0; i<listeners.size(); i++)
    {
      ActionListener l = (ActionListener) listeners.elementAt(i);
      l.actionPerformed(ae);
    }
    
    this.selected = ! this.selected;
    this.repaint();
  } // end of mousePressed()

  /**
   * Callback method for mouse button being released
   * @param e is the MouseEvent to handle
   */
  public void mouseReleased(MouseEvent e) 
  {
    this.selected = ! this.selected;
    this.repaint();
  }
  
  public void mouseClicked(MouseEvent e) {}
  public void mouseEntered(MouseEvent e) {}
  public void mouseExited(MouseEvent e) {}
  
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
   * Return the preferred size of the component to the layout manager
   * @return preferred Dimension
   */
  public Dimension getPreferredSize()
  {
    return(getMinimumSize());
  } // end of getPreferredSize()
        
  /**
   * Return the maximum size of the component to the layout manager
   * @return maximum Dimension
   */
  public Dimension getMaximumSize()
  {
    return(getMinimumSize());
  } // end of getMaximumSize()

  /**
   * Return the minimum size of the component to the layout manager
   * @return minimum Dimension
   */
  public Dimension getMinimumSize()
  {
    return(new Dimension(DEFAULT_WIDTH,DEFAULT_HEIGHT));
  } // end of getMinimumSize()
        
  /**
   * Paints the component
   * @param g is the Graphics object to paint with
   */
  public void paintComponent(Graphics g)
  {
    int w = getSize().width;
    int h = getSize().height;

    g.setColor(Color.black);
    g.drawRect(0,0,w-1,h-1);
    g.setColor(Color.lightGray);
    g.fill3DRect(0,0,w-1,h-1,! selected);
    
    Font font = new Font("Dialog",Font.PLAIN,10);
    FontMetrics metrics = getFontMetrics(font);

    int str_w = metrics.stringWidth(str);
    int str_h = metrics.getHeight();
    int descent = metrics.getDescent();
    
    g.setFont(font);
    g.setColor(Color.black);
    g.drawString(str,w/2-str_w/2,h/2+str_h/2-descent);
  } // end of paintComponent()
  
} // end of JSmallButton class

