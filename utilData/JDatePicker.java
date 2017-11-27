
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Window;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Vector;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.UIManager;

/**
 * JDatePicker - Swing Component for Picking a Date.
 * 
 * <pre>
 * 
 *   Date Created:  2/13/2004
 *  Last Modified:  2/13/2004
 *  
 * </pre>
 * 
 * <p>
 * <i>Copyright (c) 2004 Georgia Institute of Technology. All Rights Reserved.
 * </i>
 * 
 * <p>
 * <i>Permission to use, copy, modify, and distribute this software and its
 * documentation for NON-COMMERCIAL purposes and without fee is hereby granted
 * provided that this copyright notice appears in all copies. </i>
 * 
 * <p>
 * <i>GEORGIA TECH AND THE AUTHOR(S) MAKES NO REPRESENTATIONS OR WARRANTIES
 * ABOUT THE SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING
 * BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE, OR NON-INFRINGEMENT. GEORGIA TECH AND THE AUTHOR(S) SHALL
 * NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING,
 * MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS DERIVATIVES. </i>
 * 
 * @version 0.1
 * @author <a href="mailto:khai@cc.gatech.edu">Khai N. Truong </a>
 */
public class JDatePicker extends JComponent implements MouseListener {
	// ----------------------------------------------------------------------------
	// CONSTANT(S)
	// ----------------------------------------------------------------------------

	protected final static String MONTHS[] = { "Janeiro", "Fevereiro", "Março",
			"Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro",
			"Novembro", "Dezembro" };

	public static final int DEFAULT_WIDTH = 147;

	public static final int DEFAULT_HEIGHT = 20;

	// ----------------------------------------------------------------------------
	// INSTANCE VARIABLE(S)
	// ----------------------------------------------------------------------------

	protected int width = DEFAULT_WIDTH;

	protected int height = DEFAULT_HEIGHT;

	protected JPopupCalendar calendar;

	// // in java.util.Calendar format
	protected int year, month, day;

	protected String label = new String();

	protected boolean selected = false;

	// // text event listeners...in case anyone wants to know everytime the user
	// interacts with
	// // this component, register a TextListener through the addTextListener
	// method
	protected Vector listeners = new Vector();

	protected boolean enabled = true;
	
	protected Color colorDisabled = Color.white;

	// ----------------------------------------------------------------------------
	// CONSTRUCTOR(S)
	// ----------------------------------------------------------------------------

	/**
	 * Default Constructor
	 */
	public JDatePicker() {
		addMouseListener(this);

		// // setting everything to today
		GregorianCalendar gCalendar = new GregorianCalendar();
		year = gCalendar.get(Calendar.YEAR);
		month = gCalendar.get(Calendar.MONTH);
		day = gCalendar.get(Calendar.DAY_OF_MONTH);

		label = MONTHS[month] + " " + day + ", " + year;
	} // end of JDatePicker() constructor

	// ----------------------------------------------------------------------------
	// INSTANCE METHOD(S)
	// ----------------------------------------------------------------------------

	/**
	 * Add a text listener
	 * 
	 * @param l
	 *            is the TextListener to add
	 */
	public void addTextListener(TextListener l) {
		listeners.addElement(l);
	} // end of addTextListener()

	/**
	 * Remove a text listener
	 * 
	 * @param l
	 *            is the TextListener to remove
	 */
	public void removeTextListener(TextListener l) {
		listeners.removeElement(l);
	} // end of removeTextListener()

	/**
	 * Sets the date to show
	 * 
	 * @param year
	 *            is the int value year
	 * @param month
	 *            is the int value month of year (from Calendar.JANUARY to
	 *            Calendar.DECEMBER)
	 * @param day
	 *            is the int value day of month
	 */
	public void set(int year, int month, int day) {
		this.year = year;
		this.month = month;
		this.day = day;

		label = MONTHS[month] + " " + day + ", " + year;
		repaint();

		// // let all listeners know that the date has changed.
		TextEvent e = new TextEvent(this, TextEvent.TEXT_VALUE_CHANGED);
		for (int i = 0; i < listeners.size(); i++) {
			TextListener tl = (TextListener) listeners.elementAt(i);
			tl.textValueChanged(e);
		}
	} // end of set()

	/**
	 * Gets the year picked
	 * 
	 * @return int value year
	 */
	public int getYear() {
		return (this.year);
	} // end of getYear()

	public String getYearString() {
		String return_value = "" + this.year;
		int size = return_value.length();
		if (size != 4) {
			for (int i = size; i != 4; i++) {
				return_value = "0" + return_value;
			}
		}
		return return_value;
	} // end of getDayString()

	/**
	 * Gets the month picked
	 * 
	 * @return int value month
	 */
	public int getMonth() {
		return (this.month);
	} // end of getMonth()

	public String getMonthString() {
		String return_value = "" + (this.month + 1);
		if (return_value.length() == 1) {
			return_value = "0" + return_value;
		}
		return return_value;

	} // end of getDayString()

	public String getLabel() {
		return this.label;
	}

	/**
	 * Gets the day of the month picked
	 * 
	 * @return int value day
	 */
	public int getDay() {
		return (this.day);
	} // end of getDay()

	public String getDayString() {
		String return_value = "" + this.day;
		if (return_value.length() == 1) {
			return_value = "0" + return_value;
		}
		return return_value;

	} // end of getDayString()

	/**
	 * Callback method for when this component is actually added to the screen.
	 * 
	 * Important because this is when I connect the popup calendar to this
	 * widget...I can't do that until the widget is on the screen...
	 */
	public void addNotify() {
		super.addNotify();
		calendar = new JPopupCalendar(getParentWindow(), this);
	} // end of addNotify()

	/**
	 * Get reference to the parent window of this component.
	 * 
	 * @return parent Window
	 */
	public Window getParentWindow() {
		// // I traverse the windows hierarchy to find this component's parent
		// window
		// // if it's in an application, then it'll be that frame
		// // if it's in an applet, then it'll be the browser window

		// // unfortunately if it's an applet, the popup window will have "Java
		// Applet Window" label
		// // at the bottom...nothing i can do (for now)

		Window window = null;

		Component component = this;
		while (component.getParent() != null) {

			if (component instanceof Window)
				break;
			component = component.getParent();
		}

		if (component instanceof Window)
			window = (Window) component;

		return (window);
	} // end of getParentWindow()

	/**
	 * Paints the component.
	 * 
	 * @param g
	 *            is the Graphics object to paint with
	 */
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		Rectangle text_field_rect = new Rectangle(0, 0, DEFAULT_WIDTH
				- DEFAULT_HEIGHT - 1, DEFAULT_HEIGHT);
		Rectangle button_rect = new Rectangle(DEFAULT_WIDTH - DEFAULT_HEIGHT,
				0, DEFAULT_HEIGHT, DEFAULT_HEIGHT);

		if (this.enabled)
		  g2.setColor(Color.white);
		else
		  g2.setColor(this.colorDisabled);	
		
		g2.fillRect(text_field_rect.x, text_field_rect.y,
				text_field_rect.width, text_field_rect.height);

		Font font = new Font("Dialog", Font.PLAIN, 10);
		g2.setFont(font);

		FontMetrics metrics = getFontMetrics(font);
		int str_height = metrics.getHeight();
		int str_descent = metrics.getDescent();

		g2.setColor(Color.black);
		g2.drawString(this.label, 4, text_field_rect.height / 2 + str_height
				/ 2 - str_descent);

		g2.setColor(Color.gray);
		g2.draw3DRect(text_field_rect.x, text_field_rect.y,
				text_field_rect.width, text_field_rect.height, false);

		g2.translate(button_rect.x, button_rect.y);
		int w = button_rect.width;
		int h = button_rect.height;

		g2.setColor(Color.black);
		g2.drawRect(0, 0, w - 1, h - 1);
		g2.setColor(Color.lightGray);
		g2.fill3DRect(0, 0, w - 1, h - 1, !selected);

		int w_3 = (int) ((float) w / (float) 3);
		int w_3_times_2 = 2 * w_3;
		int w_2 = (int) ((float) w / (float) 2);
		int h_3 = (int) ((float) h / (float) 3);
		int h_3_times_2 = 2 * (int) h / 3;

		Polygon p = new Polygon();
		p.addPoint(w_3, h_3);
		p.addPoint(w_3_times_2, h_3);
		p.addPoint(w_2, h_3_times_2);

		g2.setColor(Color.black);
		g2.fillPolygon(p);

		g2.translate(-button_rect.x, -button_rect.y);
	} // end of paintComponent()

	/**
	 * Callback method for when mouse button is pressed
	 * 
	 * @param e
	 *            is the MouseEvent
	 */
	public void mousePressed(MouseEvent e) {
		if (enabled) {
			Rectangle button_rect = new Rectangle(DEFAULT_WIDTH
					- DEFAULT_HEIGHT, 0, DEFAULT_HEIGHT, DEFAULT_HEIGHT);

			if (button_rect.contains(e.getX(), e.getY())) {
				selected = true;
				repaint();
				// // this callback method only gets called when the "drop down"
				// button is pressed...
				// // user can activate popup window or cancel date selection
				// (depending on mode of popup window)
				calendar.toggleVisibility();
				calendar.show(year, month, day);
			}
		}
	}

	public void mouseReleased(MouseEvent e) {
		selected = false;
		repaint();
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mouseClicked(MouseEvent e) {
	}

	/**
	 * Return the preferred size of the component to the layout manager
	 * 
	 * @return preferred Dimension
	 */
	public Dimension getPreferredSize() {
		return (new Dimension(this.width, this.height));
	} // end of getPreferredSize()

	/**
	 * Return the minimum size of the component to the layout manager
	 * 
	 * @return minimum Dimension
	 */
	public Dimension getMinimumSize() {
		return (this.getPreferredSize());
	} // end of getMinimumSize()

	public void setEnabled(boolean parEnabled) {
		this.enabled = parEnabled;
	}

	/**
	 * @return Returns the colorDisabled.
	 */
	public Color getColorDisabled() {
		return colorDisabled;
	}
	/**
	 * @param colorDisabled The colorDisabled to set.
	 */
	public void setColorDisabled(Color colorDisabled) {
		this.colorDisabled = colorDisabled;
	}
	// ----------------------------------------------------------------------------
	// MAIN
	// ----------------------------------------------------------------------------

	/**
	 * Main method. Test appliation for the JDatePicker component.
	 * 
	 * @param argv
	 *            is the array of String command line arguments
	 */
	public static void main(String argv[]) {
		JFrame frame = new JFrame();
		frame.setSize(300, 300);

		frame.getContentPane().setLayout(new FlowLayout());
		frame.getContentPane().add(new JDatePicker());

		frame.setVisible(true);
	} // end of main()

} // end of JDatePicker class

