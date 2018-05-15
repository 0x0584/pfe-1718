package xml;

import java.util.Arrays;

public class XmlSet {
	private String rootTag, XmlTags[], XmlAttrs[];

	public XmlSet(String rootTag, String[] xmlTags, String[] xmlAttrs) {
		super( );
		this.XmlTags = xmlTags;
		this.XmlAttrs = xmlAttrs;
		this.rootTag = rootTag;
	}

	@Override
	public String toString( ) {
		return "XmlSet [rootTag=" + rootTag + ", XmlTags="
						+ Arrays.toString(XmlTags) + ", XmlAttrs="
						+ Arrays.toString(XmlAttrs) + "]";
	}

	public String getRootTag( ) {
		return rootTag;
	}

	public void setRootTag(String rootTag) {
		this.rootTag = rootTag;
	}

	public String[] getXmlTags( ) {
		return XmlTags;
	}

	public void setXmlTags(String[] xmlTags) {
		XmlTags = xmlTags;
	}

	public String[] getXmlAttrs( ) {
		return XmlAttrs;
	}

	public void setXmlAttrs(String[] xmlAttrs) {
		XmlAttrs = xmlAttrs;
	}

}
