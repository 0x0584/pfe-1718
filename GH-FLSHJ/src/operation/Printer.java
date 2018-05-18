package operation;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;

public class Printer implements Printable {
	final Component comp;

	public Printer(Component comp) {
		this.comp = comp;
	}

	@Override
	public int print(Graphics g, PageFormat format, int page_index) {
		if (page_index > 0) return Printable.NO_SUCH_PAGE;
		Dimension dim = comp.getSize( );/* get the bounds of the component */
		double cHeight = dim.getHeight( ), cWidth = dim.getWidth( );

		/* get the bounds of the printable area */
		double pHeight = format.getImageableHeight( );
		double pWidth = format.getImageableWidth( );

		double pXStart = format.getImageableX( );
		double pYStart = format.getImageableY( );

		double xRatio = (pWidth / cWidth), yRatio = (pHeight / cHeight);

		Graphics2D g2 = (Graphics2D) g;
		g2.translate(pXStart, pYStart);
		g2.scale(xRatio, yRatio);
		comp.paint(g2);

		return Printable.PAGE_EXISTS;
	}
}