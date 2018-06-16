package model;

import java.util.Date;
import java.util.Iterator;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.jdom2.Element;

import app.EmployeeType;
import app.Period;
import app.SearchField;
import app.utils.DateUtil;
import app.utils.XmlFile;

public final class Modeling {

	public static DefaultTableModel getUpliftModel(Employee empl) {
		DefaultTableModel model = new DefaultTableModel( );

		for (String str : new String[] {
						"التاريخ", "الرقم الإستدلالي",

						"الرتبة", "السلم",
		}) {
			model.addColumn(str);
		}

		for (Uplift u : XmlFile.getUplifts(
			XmlFile.getEmployee(empl.getEmployeeReference( )))) {
			model.addRow(new String[] {
							DateUtil.parseDate(u.getDate( )), u.getIndice( ),
							"" + u.getRank( ), "" + u.getGrade( )
			});
		}

		return model;
	}

	public static TableModel getDiplomasModel(Employee empl) {
		DefaultTableModel model = new DefaultTableModel( );

		for (String str : new String[] {
						"تاريخ الحصول عليها", "المؤسسة",

						"الميزة", "الشهادات",
		}) {
			model.addColumn(str);
		}

		for (Diploma d : XmlFile.getDiplomas(
			XmlFile.getEmployee(empl.getEmployeeReference( )))) {
			model.addRow(new String[] {
							d.getSession( ), d.getInstitue( ),
							d.getMention( ).toString( ), d.getTitle( )
			});
		}

		return model;
	}

	public static TableModel getMedicalModel(Employee empl) {
		DefaultTableModel model = new DefaultTableModel( );
		for (String col : new String[] {
						"من", "إلى", "عدد الأيام", "عطلة"
		}) {
			model.addColumn(col);
		}
		for (MedicalCertif c : XmlFile.getMedicalCertifs(
			XmlFile.getEmployee(empl.getEmployeeReference( )))) {
			Date from = c.getFrom( );
			Date to = DateUtil.add(
				Period.ONE_DAY, c.getFrom( ), c.getNumberOfDays( ));
			model.addRow(new String[] {
							DateUtil.parseDate(from), DateUtil.parseDate(to),
							"" + c.getNumberOfDays( ), c.getPeriod( )
			});
		}

		return model;
	}

	public static DefaultTableModel getDefaultModel(String text, SearchField sf,
		EmployeeType t) {
		DefaultTableModel model = Modeling.getDefaulModelColumns( );
		Iterator<Element> ifoo; // temporary iterators
		Element foo; // temporary elements
		// String scale = null, echlon = null;
		boolean filter = !(sf == null || text.compareTo("") == 0);

		// loop over the employee
		ifoo = new XmlFile( ).getRoot( ).getChildren( ).iterator( );
		while (ifoo.hasNext( )) {
			foo = ifoo.next( );

			// skip unwanted elements
			if (filter && !foo.getChild(sf.getParent( ))
							.getChildTextTrim(sf.getXmlTag( )).toLowerCase( )
							.contains(text.toLowerCase( ))) {
				continue;
			}

			// skip employee based on filter
			if (t == EmployeeType.Normal && foo.getAttributeValue("department")
							.compareTo("nil") != 0) {
				continue; // this means that this is a professor
			} else if (t == EmployeeType.Prof
							&& foo.getAttributeValue("department")
											.compareTo("nil") == 0) {
				continue; // this means that this is a normal one
			}

			// add row to model
			model.addRow(new String[] {
							foo.getAttributeValue("reference"),
							foo.getChild("administrative")
											.getChildTextTrim("cin"),
							foo.getChild("personal").getChildTextTrim("name"),
							foo.getChild("personal")
											.getChildTextTrim("familyname"),
							// scale, echlon, String.format(
							// "%d", foo.getChildren("diplomas").size( ))
			});
		}

		return model;
	}

	public static DefaultTableModel getDefaultModel(EmployeeType t) {
		return (DefaultTableModel) getDefaultModel(null, null, t);
	}

	public static DefaultTableModel getDefaulModelColumns( ) {
		DefaultTableModel model = new DefaultTableModel( );

		model.addColumn("ر.ت.");
		model.addColumn("ب.ت.و.");
		model.addColumn("الإسم");
		model.addColumn("النسب");
		// model.addColumn("السلم");
		// model.addColumn("الرتبة");
		// model.addColumn("عدد الشواهد");

		return model;
	}

	public static TableModel getRepaymentModel(Employee empl) {
		DefaultTableModel model = new DefaultTableModel( );
		for (String col : new String[] {
						"عطلة", "عدد الأيام", "المعوضة", "الباقي"
		}) {
			model.addColumn(col);
		}
		for (Repayment r : empl.getRepayments( )) {
			model.addRow(new String[] {
							r.getPeriod( ), "" + r.getNumberOfDays( ),
							"" + r.getRepayedDays( ), "" + r.getRest( )
			});
		}

		return model;
	}

}
