package testing;

import java.util.ArrayList;

import model.Diploma;
import xml.XmlFile;

public class Main {

	public static void main(String[] args) {
		XmlFile x = new XmlFile("data/hr.xml");
		ArrayList<Diploma> tmp = x.getDiplomas( );
		
	}

}
