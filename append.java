public static void Writer(Product product) throws JDOMException, IOException {

    Document document = null;
    Element root = null;

    File xmlFile = new File("products.xml");
    if(xmlFile.exists()) {
        // try to load document from xml file if it exist
        // create a file input stream
        FileInputStream fis = new FileInputStream(xmlFile);
        // create a sax builder to parse the document
        SAXBuilder sb = new SAXBuilder();
        // parse the xml content provided by the file input stream
        // and create a Document object
        document = sb.build(fis);
        // get the root element of the document
        root = document.getRootElement();
        fis.close();
    } else {
        // if it does not exist create a new document and new root
        document = new Document();
        root = new Element("productlist");
    }


    String prijs = String.valueOf(product.prijz);
    String naamelement = "naam";
    String categorieelement = "category";
    String descriptionelement = "description";
    Element child = new Element("product");
    child.addContent(new Element(categorieelement).setText(product.categorie));
    child.addContent(new Element("code").setText(product.code));
    child.addContent(new Element(naamelement).setText(product.naamartikel));
    child.addContent(new Element(descriptionelement).setText(product.beschrijvingartikel));
    child.addContent(new Element("price").setText(prijs));
    child.addContent(new Element("image").setText(product.imgurl));
    child.addContent(new Element("image").setText(product.imgurl2));
    child.addContent(new Element("image").setText(product.imgurl3));
    child.addContent(new Element("image").setText(product.imgurl4));
    child.addContent(new Element("image").setText(product.imgurl5));
    root.addContent(child);
    document.setContent(root);
    try {
        FileWriter writer = new FileWriter("products.xml");
        XMLOutputter outputter = new XMLOutputter();
        outputter.setFormat(Format.getPrettyFormat());
        outputter.output(document, writer);
        outputter.output(document, System.out);
        writer.close(); // close writer
    } catch (IOException e) {
        e.printStackTrace();
    }
}

//and finally a little test

public static void main(String[] args) throws JDOMException, IOException {
    Product product = null;

    product = new Product();
    product.categorie = "cat1";
    product.code = "code1";
    product.naamartikel = "naam1";
    product.beschrijvingartikel = "beschrijving1";
    product.prijz = 100d;
    product.imgurl = "http://localhost/img1.png";
    product.imgurl2 = "http://localhost/img2.png";
    product.imgurl3 = "http://localhost/img3.png";
    product.imgurl4 = "http://localhost/img5.png";
    product.imgurl5 = "http://localhost/img5.png";
    Writer(product);

    product = new Product();
    product.categorie = "cat2";
    product.code = "code2";
    product.naamartikel = "naam2";
    product.beschrijvingartikel = "beschrijving2";
    product.prijz = 200d;
    product.imgurl = "http://localhost/img21.png";
    product.imgurl2 = "http://localhost/img22.png";
    product.imgurl3 = "http://localhost/img23.png";
    product.imgurl4 = "http://localhost/img25.png";
    product.imgurl5 = "http://localhost/img25.png";
    Writer(product);

    product = new Product();
    product.categorie = "cat3";
    product.code = "code3";
    product.naamartikel = "naam3";
    product.beschrijvingartikel = "beschrijving3";
    product.prijz = 300d;
    product.imgurl = "http://localhost/img31.png";
    product.imgurl2 = "http://localhost/img32.png";
    product.imgurl3 = "http://localhost/img33.png";
    product.imgurl4 = "http://localhost/img35.png";
    product.imgurl5 = "http://localhost/img35.png";
    Writer(product);
}
