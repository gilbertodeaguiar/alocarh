import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import javax.swing.JLabel;

/**
 * JDateLabel - A Label for showing/selecting dates...(because I couldn't get a JLabel to behave the way I wanted it to)
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
public class JDateLabel
  extends JLabel
{
  //----------------------------------------------------------------------------
  // CONSTANT(S)
  //----------------------------------------------------------------------------

  public static final Color SELECTED_BACKGROUND_COLOR = Color.blue;
  public static final Color SELECTED_FOREGROUND_COLOR = Color.white;
  
  //----------------------------------------------------------------------------
  // INSTANCE VARIABLE(S)
  //----------------------------------------------------------------------------

  protected Color unselected_color = Color.white;
  protected boolean selected = false;

  //----------------------------------------------------------------------------
  // CONSTRUCTOR(S)
  //----------------------------------------------------------------------------

  /**
   * Constructor
   * @param str is the String label
   * @param alignment is int value specifying how to draw the String
   */
  public JDateLabel(String str,
                    int alignment)
  {
    super(str,alignment);
  } // end of JDateLabel() constructor
  
  //----------------------------------------------------------------------------
  // INSTANCE METHOD(S)
  //----------------------------------------------------------------------------

  /**
   * Sets the background color
   * @param background is the Color for the background
   */
  public void setBackground(Color background)
  {
    super.setBackground(background);
    unselected_color = background;
  } // end of setBackground()
  
  /**
   * Sets the select state of the date label
   * @param state is the boolean select state
   */
  public void select(boolean state)
  {
    this.selected = state;
    repaint();
  } // end of select()
  
  /**
   * Gets the select state of the date label
   * @return the boolean select state
   */
  public boolean isSelected()
  {
    return(this.selected);
  } // end of isSelected()
  
  /**
   * Paints the component
   * @param g is the Graphics object to paint with
   */
  public void paintComponent(Graphics g)
  {
    int width = getSize().width;
    int height = getSize().height;
    
    Color background_color = getBackground();
    Color foreground_color = getForeground();

    if (selected)
    {
      background_color = SELECTED_BACKGROUND_COLOR;
      foreground_color = SELECTED_FOREGROUND_COLOR;
    }
    
    g.setColor(background_color);
    g.fillRect(0,0,width,height);
    
    g.setColor(foreground_color);
    g.setFont(getFont());
    
    FontMetrics metrics = getFontMetrics(getFont());
    String str = getText();
    
    int str_width = metrics.stringWidth(str);
    int str_height = metrics.getHeight();
    int str_descent = metrics.getDescent();

    g.drawString(str,width/2-str_width/2,height/2+str_height/2-str_descent);

  } // end of paintComponent()

} // end of JDateLabel class

