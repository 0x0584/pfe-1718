package app.utils;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

public class Printer implements Printable {
	private final Component comp;
	public final static int A4_STD_WIDTH = 733, A4_STD_HEIGHT = 2073;

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

	public static void doPrint(Component cmp) {
		PrinterJob pjob = PrinterJob.getPrinterJob( );
		PageFormat preformat = pjob.defaultPage( );
		preformat.setOrientation(PageFormat.PORTRAIT);
		preformat.getPaper( ).setSize(A4_STD_WIDTH, A4_STD_HEIGHT);
		
		PageFormat postformat = pjob.pageDialog(preformat);

		// if user does not hit cancel then print.
		if (preformat != postformat) {
			// Set print component
			pjob.setPrintable(new Printer(cmp), postformat);
			if (pjob.printDialog( )) {
				try {
					pjob.print( );
				} catch (PrinterException e1) {
					e1.printStackTrace( );
					System.err.println(e1.getMessage( ));
				}
			}
		}
	}
}