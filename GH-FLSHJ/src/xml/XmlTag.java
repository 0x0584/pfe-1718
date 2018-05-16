package xml;

import java.util.Arrays;

public class XmlTag {
	private String root, XmlAttrs[];
	public static short PARENT = 0;

	public XmlTag(String root, String[] xmlAttrs) {
		super( );
		this.XmlAttrs = xmlAttrs;
		this.root = root;
	}

	@Override
	public String toString( ) {
		return "XmlTag [XmlAttrs=" + Arrays.toString(XmlAttrs) + ", content="
						+ root + "]";
	}

	public String[] getXmlAttrs( ) {
		return XmlAttrs;
	}

	public void setXmlAttrs(String[] xmlAttrs) {
		XmlAttrs = xmlAttrs;
	}

	public String getRoot( ) {
		return root;
	}

	public void setRoot(String root) {
		this.root = root;
	}

}
