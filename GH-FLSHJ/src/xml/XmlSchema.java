package xml;

import java.util.ArrayList;

import model.AdministrativeStatus;
import model.Diploma;
import model.Person;
import model.Uplift;

public class XmlSchema {
	private ArrayList<XmlTag> xtags;

	public XmlSchema() {
		xtags = new ArrayList<XmlTag>( );

	}

	public XmlSchema(ArrayList<XmlTag> tags) {
		super( );
		this.xtags = tags;

	}

	public static <T> XmlSchema initSet(T xmlObject) {
		XmlSchema xset = new XmlSchema( );

		if (xmlObject instanceof Diploma) {
			xset.xtags.add(new XmlTag("diplomas", new String[] {
							"mention"
			}));
			xset.xtags.add(new XmlTag("institute", new String[] {
							"city"
			}));
			xset.xtags.add(new XmlTag("session", null));
		} else if (xmlObject instanceof Person) {
			xset.xtags.add(new XmlTag("emplyee", new String[] {
							"department"
			}));
			xset.xtags.add(new XmlTag("name", null));
			xset.xtags.add(new XmlTag("familyname", null));
			xset.xtags.add(new XmlTag("birth", null));
			xset.xtags.add(new XmlTag("nationality", new String[] {
							"ma"
			}));
			xset.xtags.add(new XmlTag("birthplace", null));
			xset.xtags.add(new XmlTag("address", null));
			xset.xtags.add(new XmlTag("phone", null));
			xset.xtags.add(new XmlTag("state", new String[] {
							"married"
			}));
			xset.xtags.add(new XmlTag("partner", new String[] {
							"name", "job"
			}));
			xset.xtags.add(new XmlTag("children", new String[] {
							"number"
			}));
		} else if (xmlObject instanceof AdministrativeStatus) {
			// TODO: finish this
		} else if (xmlObject instanceof Uplift) {
			// TODO: finish this
		}

		return xset;
	}

	public ArrayList<XmlTag> getXmlTags( ) {
		return xtags;
	}

	public void setXmlTags(ArrayList<XmlTag> xmlTags) {
		xtags = xmlTags;
	}

}
