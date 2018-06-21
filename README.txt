Anas Rchid						      05/12/2018





1 Intoduction
═════════════

  \vspace*{\fill}
	Pour obtenir ce besoin, j'avais la responsabilité de
	développer un environnement pour gérer les différents
	taches décrites en [cahier des charges]

	Pour realiser cela, il y a deux parties. Stockage des
	données et l'application bureau. Pour la première, j'ai
	choisi /XML/; un langage markup écrit dans un fichier
	texte. Tout simplement parce qu'il est simple à utiliser
	et/ou modifier ainsi qu'il gratuit. Et pour la deuxième,
	j'ai développé une application en /Java/, car il est un
	langage Oriente-Objet qui facilite le processus de
	développement.

  \vspace*{\fill}


[cahier des charges] cf. section 2


2 Cahier des Charges
════════════════════

  \vspace*{\fill}
	D'après son nom, /Service des ressources humaines/ est un
	service qui est responsable de la gestion des employées et
	fonctionnaires, leurs diplômes et grades, ainsi que donner
	des attestations du travail et des autorisations de congé,
	suivi d'absence, rémunération du travail les jours fériés
	et finalement une notation annuelle.

  Donc, on en déduit que le /cahier des charges/ est le suivant :

  ⁃ Implémenter un système de gestion des employées/fonctionnaires
  ⁃ Gérer les diplômes et les grades
  ⁃ Suivi des grades
  ⁃ Suivi d'absence
  ⁃ Suivi de rémunération du travail les fériés
  ⁃ Générer des attestations de travail
  ⁃ Générer des autorisations de congé
  ⁃ Générer des notations annuelle pour
  \vspace*{\fill}


3 Conception
════════════

3.1 Stockage des données en XML
───────────────────────────────

  Les données sont stockées dans un fichier `XML', `data/xml/hr.xml'
  puisqu'il est lisible à la fois par la machine et l'humain. Au
  suivant, les règles du gestion est schéma général du fichier.


3.1.1 Règles de gestion du fichier `XML'
╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌

  Le root-tag est `<Employee>' et qui contient plusieurs tags de type
  `<employee>' qui représente des employées. Chaque tag `<employee>'
  contient un seul tag `<personal>' et un seul tag `<administrative>'
  qui peut contient `0' ou plusieurs tags `<uplift>'. Le tag
  `<employee>' peut aussi avoir `0' ou plusieurs tags de type
  `<diploma>', `<medicalcertif>' et `<repayment>'.

  Voici la signification de chaque tag des tags déclaré ci-dessus:

  `<Employee>'
	le root-tag, qui contient les tags `employee'
  `<employee>'
	contient tout les information d'un employée particulier est il
	avait deux attributes:

	*reference* identifiant du employée

	*departement* département du employée. Certains employées
	 n'appartient à aucun département. Ce sont des /fonctionnaire/
  `<personal>'
	contient des informations personnelle comme le /nom/, /prénom/,
	/date de naissance/, etc.
  `<administrative>'
	contient des informations administrative comme le /SOM/, /CIN/,
	etc.
  `<uplift>'
	contient les informations des avancements dans le grade, /date/,
	/indice/, /échelon/ et /échelle/. Ce tag avais un seul attribut.

	*id* identifiant du avancement par rapport au avancement
	 précédant
  `<diploma>'
	contient les informations sur les diplômes, /titre/, /mention/,
	/institue/ et /session/. Ce tag a aussi un seul attribue, ainsi
	que l'enfant `<title>'.

	*id* identifiant du diplôme

	*mention* la mention du diplôme (dans le tag `<title>')
  `<medicalcertif>'
	contient les informations sur certification médical, /date du
	certification/, /durée/ et /la période/.

	*id* identifiant du certification médical.
  `<repayment>'
	contient les informations sur les remboursements, /la période/,
	/nombre des jours à rembourser/ et /nombre des jours déjà
	remboursé/

	*id* identifiant du remboursement


3.1.2 Schéma général du fichier
╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌

  ┌────
  │  1	<Employee>			<!--root-->
  │  2	  <employee reference="" department="">
  │  3
  │  4	    <notes />			 <!--les notes sur l'employé-->
  │  5
  │  6	    <personal>			 <!--les informations personnelles-->
  │  7	    </personal>
  │  8
  │  9	    <administrative>		<!--les informations administrative-->
  │ 10	      <uplift id="" state="">	<!--les informations d'avancement-->
  │ 11	      </uplift>
  │ 12
  │ 13	      <uplift id="" state="" />	<!--nous pouvons avoir plus-->
  │ 14	    </administrative>
  │ 15
  │ 16	    <diplomas id="">		<!--les infomration du diplôme-->
  │ 17	    </diplomas>
  │ 18	    <diplomas id="" />		<!--nous pouvons avoir plus-->
  │ 19
  │ 20	    <medicalcertif id="">	<!--information du certificat médical-->
  │ 21	    </medicalcertif>
  │ 22	    <medicalcertif id="" />	<!--nous pouvons avoir plus-->
  │ 23
  │ 24	    <repayment id="">		<!--information du remboursement-->
  │ 25	    </repayment>
  │ 26	    <repayment id="" />		<!--nous pouvons avoir plus-->
  │ 27
  │ 28	  </employee>
  │ 29	</Employee>
  └────
  Programme 1 : Schéma général `XML' du fichier `data/xml/hr.xml'


3.2 Les Paquets system et leurs Classes
───────────────────────────────────────

  Le code source de l'application est divisé en 4 paquets pricipales:

  [`model']
	contient les différentes classe pour mobilisé les donnée en
	objet
  [`app']
	contient les différentes énumération utilisées dans
	l'application. Ce paquet contient aussi [`app.utils'], qui
	contient des utilitaires utiles pour le développement, notamment
	la gestion du [fichier XML].
  [`wins']
	contient des interfaces graphiques, y compris celles qui sont
	responsables des opérations CRUD normales qui existent dans
	[`wins.crud']
  [`views']
	contient des pages générées pour l'impression.

  \vspace*{\fill}
  [./diags/Overview.png]

  \vspace*{\fill}


[`model'] cf. section 3.2.1

[`app'] cf. section 3.2.2

[`app.utils'] cf. section 3.2.3

[fichier XML] cf. section 3.1

[`wins'] cf. section 4.1.1

[`wins.crud'] cf. section 4.1.2

[`views'] cf. section 4.1.3

3.2.1 Paquet `model'
╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌

  Ce paquet contient les modèles de l'application, ce sont des classes
  `Java' pour modéliser les informations stockées dans le fichier XML
  [`data/xml/hr.xml'].

  [./diags/OverviewOnClasses.png]

  Les classes implémentent les méthodes abstrais [`getElement()'],
  [`add()'], [`update()'] et [`remove()'] dans la classe générique
  [`XmlElement']. Ces méthodes sont responsables de la selection,
  l'ajout, la mise à jour et la suppression du tag correspondant à
  l'objet concerné dans le [fichier xml].

  Voici la classe mère de toutes les classes, [`XmlElement'], qui
  contient en addition, une chaine de caractères qui représente le
  référence de l'employé, c.-à-d. L'identifient

  ┌────
  │  1	import org.jdom2.Element;
  │  2
  │  3	public abstract class XmlElement<T> {
  │  4	    public abstract boolean add();
  │  5	    public abstract boolean update(T updated);
  │  6	    public abstract boolean remove();
  │  7	    public abstract Element getElement();
  │  8
  │  9	    /* référence du employée */
  │ 10	    protected String empl_ref;
  │ 11	    public String getEmployeeReference( ) {
  │ 12		return empl_ref;
  │ 13	    }
  │ 14
  │ 15	    public void setEmployeeReference(String ref) {
  │ 16		this.empl_ref = ref;
  │ 17	    }
  │ 18	}
  └────
  Programme 2 : Extrais du classe générique `XmlElement' du paquet
  [`app.utils']

  Les méthodes [`add()'], [`update()'] et [`remove()'] de [`XmlElement']
  retournent une valeur booléen, qui signifie est ce que l'opération a
  réussie ou non. Tandis que [`getElement()'] retourne le tag [`XML']
  correspondant a l'objet.

  La raison pour laquelle la classe est générique, c'est que
  [`update()'] doit l'être. La méthode [`update()'] prend un variable de
  type `T', ce type est décrit avec un héritage du classe
  [`XmlElement'].

  Par exemple, [`update()'] dans la classe [`Diploma'] est la suivant :

  ┌────
  │ 1  public class Diploma exstends XmlElement<Diploma> {
  │ 2	   /* les attributs du classe */
  │ 3
  │ 4	   @Override
  │ 5	   public boolean update(Diploma updated) {
  │ 6	/* process la mise à jour */
  │ 7	   }
  │ 8  }
  └────
  Programme 3 : Extrais du classe [`Diploma'] qui montre l'héritage de
  [`XmlElement']


[`data/xml/hr.xml'] cf. section 3.1

[`getElement()'] cf. programme 2

[`add()'] cf. programme 2

[`update()'] cf. programme 2

[`remove()'] cf. programme 2

[`XmlElement'] cf. programme 2

[fichier xml] cf. section 3.1

[`app.utils'] cf. section 3.2.3

[`XML'] cf. programme 1

[`Diploma'] cf. figure 2


3.2.2 Paquet `app'
╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌

  Le paquet `app' contient que les énumérations, décrites dans le figure
  3.

  [./diags/OverviewOnApp.png]


  `Cadre'
	représente les cadres possibles pour un employée/fonctionnaire
  `Mention'
	les mentions possibles pour un diplôme, utilisés dans
	[`DilpomaCrud.java']
  `Period'
	les différentes périodes utilisées dans l'application
  `Holiday'
	utilisé dans [`MainWin.java'] pour la génération du congé
  `SearchField'
	utilisé pour la recherche dans [`MainWin.java']
  `EmployeeType'
	utilisé pour filtrer les employée et fonctionnaire dans
	[`MainWin.java']
  `Files'
	contient des énumérations qui concernent les différents fichiers
	`XML' utilisé.


[`DilpomaCrud.java'] cf. section 4.1.2.3

[`MainWin.java'] cf. section 4.1.1.1


3.2.3 Paquet `app.utils'
╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌

  Alors, ce paquet contient des classes important pour l'application. Le
  diagramme des classes dans la figure 4 explique les différents
  relations entre ces classes et les classes de système de la
  d'interface graphique du `Java'.

  La classe [`Printer'] est responsable de l'impression d'un
  `Component', la classe des composants graphiques, avec l'aide de la
  méthode `static doPrint()' qui prend un `Component' comme paramètre.

  [./diags/OverviewAppUtils0.png]

  Aussi, [`Printer'] a une implémentation de la méthode abstrait
  [`print()'] de l'interface `Printable', `doPrint()' faire un appel à
  cette méthode avec l'aide des autres classes du paquet
  `java.awt.print' comme `PrinterJob' et `PageFormat'.

	/Note:/ Principalement, L'appelle de la méthode
	[`print()'] se fait par la classe `PrinterJob()' du paquet
	`java.awt.print.PrinterJob'. La méthode utilise dans
	l'application c'est `static doPrint()'.

  Au suivant, un extrait du classe [`Printer'] qui montre
  l'implémentation de la méthode [`print()'].

  ┌────
  │  1	import java.awt.Component;
  │  2	import java.awt.Dimension;
  │  3	import java.awt.Graphics;
  │  4	import java.awt.Graphics2D;
  │  5	import java.awt.print.PageFormat;
  │  6	import java.awt.print.Printable;
  │  7
  │  8	public class Printer implements Printable {
  │  9	    /**
  │ 10	     * méthode abstrait dans l'interface java.awt.Printable, l'appelle
  │ 11	     * se fait par la classe java.awt.PrinterJob
  │ 12	     *
  │ 13	     * @param g une graphique du classe java.awt.Graphics
  │ 14	     * @param format formatage de la page à imprimer
  │ 15	     * @param page_index l'index de la page
  │ 16	     *
  │ 17	     * @return intègre qui représente l'état de l'impression
  │ 18	     */
  │ 19	    @Override
  │ 20	    public int print(Graphics g, PageFormat format, int page_index) {
  │ 21		/* vérification du page, c'est un protocole de l'interface */
  │ 22		if (page_index > 0) return Printable.NO_SUCH_PAGE;
  │ 23
  │ 24		/* prend les dimensions du composant */
  │ 25		Dimension dim = comp.getSize( );
  │ 26		double cHeight = dim.getHeight( ), cWidth = dim.getWidth( );
  │ 27
  │ 28		/* initialization de demention du la zone d'impression */
  │ 29		double pHeight = format.getImageableHeight( );
  │ 30		double pWidth = format.getImageableWidth( );
  │ 31		double pXStart = format.getImageableX( );
  │ 32		double pYStart = format.getImageableY( );
  │ 33
  │ 34		/* la difference entre la taille du composant et la taille du
  │ 35		 * page pour mettre le composant à l'échelle du page */
  │ 36		double xRatio = (pWidth / cWidth), yRatio = (pHeight / cHeight);
  │ 37
  │ 38		/* mettre le composant à jour avec la page à imprimer en utilisent
  │ 39		 * la methode java.awt.Component.paint() */
  │ 40		Graphics2D g2 = (Graphics2D) g;
  │ 41		g2.translate(pXStart, pYStart);
  │ 42		g2.scale(xRatio, yRatio);
  │ 43		comp.paint(g2);
  │ 44		return Printable.PAGE_EXISTS;
  │ 45	    }
  │ 46	}
  └────
  Programme 4 : Extrais de la classe `Printer' qui montre
  l'implémentation de la méthode abstrait `print()' de l'interface
  `java.awt.Printable'
  On revient à la figure 4, la classe [`JTableCellListener'] est
  responsable à réagir avec une modification qui passe au niveau des
  cellules d'un `JTable'. Cette classe est à l'écoute des modifications
  apportées aux données de la table via `TableCellEditor' du paquet
  `javax.swing.table' avec l'aide du interface `PropertyChangeListener'
  du paquet `java.beans'. Donc, doit implémenter la méthode
  [`propertyChange()'] de l'interface mentionnée.


  ┌────
  │  1	import java.awt.event.ActionEvent;
  │  2
  │  3	import java.beans.PropertyChangeEvent;
  │  4	import java.beans.PropertyChangeListener;
  │  5
  │  6	import javax.swing.Action;
  │  7	import javax.swing.JTable;
  │  8	import javax.swing.SwingUtilities;
  │  9
  │ 10	public class JTableCellListener implements PropertyChangeListener, Runnable {
  │ 11	    private JTable table;
  │ 12	    private Action action;
  │ 13	    private int row;
  │ 14	    private int column;
  │ 15	    private Object oldValue;
  │ 16	    private Object newValue;
  │ 17
  │ 18	   /**
  │ 19	    * @param table la table concerne
  │ 20	    * @param action l'action a invoquée
  │ 21	    */
  │ 22	    public JTableCellListener(JTable table, Action action) {
  │ 23		this.table = table;
  │ 24		this.action = action;
  │ 25
  │ 26		/* ajouter cette classe à la table pour l'invoquée */
  │ 27		this.table.addPropertyChangeListener(this);
  │ 28	    }
  │ 29	}
  └────
  Programme 5 : Extrait du classe `JTableListener'

  Lorsque l'édition est démarrée, la valeur de la cellule est
  enregistrée. Lorsque l'édition est arrêtée, la nouvelle valeur est
  enregistrée en tant que `Object'. Lorsque l'ancienne et la nouvelle
  valeur sont différentes, l'action fournie est invoquée. La classe doit
  appeller des classes selon l'état de l'édition, alors on doit aussi
  implémenter la méthode [`run()'] dans l'interface `Runnable'. Au
  suivant un extrait du classe [`JTableCellListener']. Par la suit, des
  extraits du classe et ses méthodes.

  La classe possède un constricteur privé qui prend en paramètre la
  table, numéro du ligne et colonne, et les deux valeurs, l'ancien et
  nouvelle. Ce constricteur est utilisé dans la méthode
  [`processEditingStopped()'] pour créer une sauvegarde de la cellule
  concernée.

  ┌────
  │ 30	/**
  │ 31	 * Créé une copie du JTableCellListener avec une sauvegarde des
  │ 32	 * anciennes/nouvelles données ainsi que la ligne et la colonne
  │ 33	 *
  │ 34	 * @param row la ligne de la cellule modifiée
  │ 35	 * @param column la colonne de la cellule modifiée
  │ 36	 * @param oldValue l'ancienne valeur de la cellule modifiée
  │ 37	 * @param newValue nouvelle valeur de la cellule modifiée
  │ 38	 */
  │ 39	private JTableCellListener(JTable table, int row, int column,
  │ 40				   Object oldValue, Object newValue) {
  │ 41	    this.table = table;
  │ 42	    this.row = row;
  │ 43	    this.column = column;
  │ 44	    this.oldValue = oldValue;
  │ 45	    this.newValue = newValue;
  │ 46	}
  │ 47
  │ 48	/**
  │ 49	 * Implémentation de l'interface PropertyChangeListener
  │ 50	 *
  │ 51	 * @param e l'événement génère par le système
  │ 52	 */
  │ 53	@Override
  │ 54	public void propertyChange(PropertyChangeEvent e) {
  │ 55	    /* tester si l'évent vient d'après la classe TableCellEditor */
  │ 56	    if ("tableCellEditor".equals(e.getPropertyName( ))) {
  │ 57		/* si la table est en coure de la modification */
  │ 58		if (table.isEditing( )) processEditingStarted( );
  │ 59		/* si l’edition est terminée */
  │ 60		else processEditingStopped( );
  │ 61	    }
  │ 62	}
  └────
  Programme 6 : L'implémentation du `propertyChange()' de l'interface
  `Printable' dans la classe [`JTableListener']

  La méthode [`JTable.isEditing()'] indique l'état booléen du table, la
  valeur `true' indique que la table est en une modification actif,
  alors on fait un appel à [`processEditingStarted()']. Lorsque la
  méthode retourne la valeur `false', on fait un appel au
  [`processEditingStopped()'], qui est responsable de la vérification
  est ce que la valeur de la cellule a été modifie ou non par la
  comparaison entre [`oldValue'] et [`newValue'] dans la classe
  [`JTableCellListener']. Si les valeurs sont différentes, on fait un
  appel à l'action du classe.

  ┌────
  │ 63	/**
  │ 64	 * annoncer le démarrage du processus d'édition de cellule
  │ 65	 */
  │ 66	private void processEditingStarted( ) {
  │ 67	    SwingUtilities.invokeLater(this);
  │ 68	}
  │ 69
  │ 70	/**
  │ 71	 * vérifier la cellule concernée
  │ 72	 */
  │ 73	private void processEditingStopped( ) {
  │ 74	    /* sauvegardée la nouvelle valeur */
  │ 75	    newValue = table.getModel( ).getValueAt(row, column);
  │ 76
  │ 77	    /* si la nouvelle valeur est différente a l'ancienne valeur, alors */
  │ 78	    if (!newValue.equals(oldValue)) {
  │ 79		JTableCellListener tcl;
  │ 80		ActionEvent event;
  │ 81
  │ 82		tcl = new JTableCellListener(getTable( ), getRow( ), getColumn( ),
  │ 83					     getOldValue( ), getNewValue( ));
  │ 84		event = new ActionEvent(tcl, ActionEvent.ACTION_PERFORMED, "");
  │ 85
  │ 86		action.actionPerformed(event); /* exécuter l'action */
  │ 87	    }
  │ 88	}
  └────
  Programme 7 : Les fonctions `processEditingStarted()' et
  `processEditingStopped()' utilisées dans la méthode
  [`propertyChange()']

  ┌────
  │ 89	@Override
  │ 90	public void run( ) {
  │ 91	    row = table.convertRowIndexToModel(table.getEditingRow( ));
  │ 92	    column = table.convertColumnIndexToModel(table.getEditingColumn( ));
  │ 93	    oldValue = table.getModel( ).getValueAt(row, column);
  │ 94	    newValue = null;
  │ 95	}
  └────
  Programme 8 : L'implémentation du `run()' du classe [`JTableListener']

  Et finalement, il reste que l'implémentation de la méthode [`run()'],
  qui est été appelée avec le protocole [`SwingUtilities.invokeLater()']
  dans [`processEditingStarted()']. Le rôle de cet appel est de
  récupérer la valeur actuelle de la cellule.

  \vspace*{\fill}
	/Note: Le but du création de cette classe/
	[`JTableCellListener'] /c'est que dans/ `Java' /il n'y a
	pas d'un listener native, ou par défaut qui suit l'état
	des cellules d'un/ `JTable'.
  \vspace*{\fill}

  [./diags/OverviewAppUtils1.png]
  La classe [`DateUtil'] dans la figure 5 est utilisé pour la
  manipulation des dates, et la conversion des dates de/vers `String'
  avec l'aide du classe system [`SimpleDateFormat']. Pour les dates,
  j'ai choisi un format standard, `YYYY-MM-DD', pour touts les dates
  dans le projet.

  ┌────
  │  1	import java.text.ParseException;
  │  2	import java.text.SimpleDateFormat;
  │  3	import java.util.Date;
  │  4
  │  5	public class DateUtil {
  │  6	    private SimpleDateFormat fmt;
  │  7
  │  8	    public DateUtil() {
  │  9		fmt = new SimpleDateFormat("yyyy-MM-dd");
  │ 10	    }
  │ 11	}
  └────
  Programme 9 : Extrais de la classe `DateUtil'

  La classe [`DateUtil'] contient une définition d'une méthode, [`static
  parseDate()'], qui a [une surcharge] pour la conversion de `Date' vers
  `String' et vice versa

  ┌────
  │ 12	/**
  │ 13	 * Convertir une date vers une chaine des caractères
  │ 14	 */
  │ 15	public static String parseDate(Date date) {
  │ 16	    try {
  │ 17		return new DateUtil( ).fmt.format(date);
  │ 18	    } catch (ParseException e) {
  │ 19		System.err.println(e.getMessage( ));
  │ 20		return new DateUtil( ).fmt.format(new Date( ));
  │ 21	    }
  │ 22	}
  └────
  Programme 10 : La méthode `parseDate()' de la classe [`DateUtil']

  La classe aussi contient la méthode [`static diffDays()'] pour
  calculer le nombre des jours entre deux dates, avec une aide de la
  méthode [`static diff()']

  ┌────
  │ 1  /* voir la méthode diff() */
  │ 2  public static long diffDays(Date from, Date to) {
  │ 3	   return TimeUnit.MILLISECONDS.toDays(diff(from, to));
  │ 4  }
  └────
  Programme 11 : La définition de la méthode `diffDays()' de la classe
  [`DateUtil']
  ┌────
  │  1	/**
  │  2	 * Convertir une chaine des caractères vers une Date
  │  3	 */
  │  4	public static Date parseDate(String str) {
  │  5	    Date d;
  │  6
  │  7	    try {
  │  8		d = new DateUtil( ).fmt.parse(str);
  │  9	    } catch (ParseException e) {
  │ 10		System.err.println(e.getMessage( ));
  │ 11		d = new Date( );
  │ 12	    }
  │ 13
  │ 14	    return d;
  │ 15	}
  └────
  Programme 12 : La surcharge de la méthode `parseDate()' de la classe
  [`DateUtil']

  ┌────
  │ 1  /**
  │ 2	* Calculé la différence de nombre des seconds
  │ 3	* entre les deux dates
  │ 4	*/
  │ 5  public static long diff(Date from, Date to) {
  │ 6	   if (from != null && to != null) {
  │ 7	return to.getTime( ) - from.getTime( );
  │ 8	   } else return 0;
  │ 9  }
  └────
  Programme 13 : La définition de la méthode `diff()' du classe
  [`DateUtil']

  Dans la figure 5 aussi, il y a la classe [`XmlFile'], la couche /DAO/
  de l'application. Elle est responsable a tout interaction avec le
  [fichier `XML'].

  Avec l'aide de `static updateOrCreate()' on peut faire une mise a jour
  a une valeur d'un tag dans le fichier `XML'.

  ┌────
  │  1	public static void updateOrCreate(Element el, String node, String value) {
  │  2	    Element foo = el.getChild(node);
  │  3
  │  4	    if (foo == null) {
  │  5		el.addContent(new Element(node).addContent(value));
  │  6		writeXml(el.getDocument( ));
  │  7	    } else {
  │  8		foo.setText(value);
  │  9	    }
  │ 10	}
  └────

  ┌────
  │  1	import java.io.File;
  │  2	import java.io.IOException;
  │  3
  │  4	import org.jdom2.Document;
  │  5	import org.jdom2.Element;
  │  6	import org.jdom2.JDOMException;
  │  7	import org.jdom2.input.SAXBuilder;
  │  8
  │  9	import app.Files;
  │ 10
  │ 11	public class XmlFile {
  │ 12	    private String filepath;
  │ 13	    private File file;
  │ 14	    private Document doc;
  │ 15	    private Element root;
  │ 16
  │ 17	    public XmlFile(Files file) {
  │ 18		setFilePath(file.getFilePath( ));
  │ 19	    }
  │ 20
  │ 21	    /**
  │ 22	     * A chaque fois on change l'emplacement du fichier, on doit
  │ 23	     * initialiser le Document XML ainsi que la racine du document
  │ 24	     *
  │ 25	     * @param filepath l'emplacement du fichier
  │ 26	     */
  │ 27	    public void setFilePath(String filepath) {
  │ 28		try {
  │ 29		    this.file = new File(this.filepath = filepath);
  │ 30		    if (file.exists( )) {
  │ 31			this.doc = new SAXBuilder( ).build(this.file);
  │ 32			this.root = doc.getRootElement( );
  │ 33		    } else {
  │ 34			this.doc = new Document( );
  │ 35			this.root = new Element("Employee");
  │ 36		    }
  │ 37		} catch (JDOMException | IOException e) {
  │ 38		    System.out.println(e.getMessage( ));
  │ 39		}
  │ 40	    }
  │ 41
  │ 42	}
  └────
  Programme 14 : Extrait de la classe `XmlFile' du paquet `app.utils'
  Et on peut aussi sauvegarder les changements dans le fichier avec la
  méthode `static write Xml()'.

  ┌────
  │  1	/**
  │  2	 * Écrire une instance de la classe Document dans un fichier XML
  │  3	 *
  │  4	 * @param doc le Document XML
  │  5	 * @param f les fichiers systèmes de l'application
  │  6	 *
  │  7	 * @return true si l'écriture est successive
  │  8	 */
  │  9	public static boolean writeXml(Document doc, Files f) {
  │ 10	    try {
  │ 11		XMLOutputter xmlout = new XMLOutputter( );
  │ 12		xmlout.setFormat(Format.getPrettyFormat( ));
  │ 13		xmlout.output(doc, new FileWriter(f.getFilePath( )));
  │ 14		System.err.println("success " + f.getFilePath( ));
  │ 15		return true;
  │ 16	    } catch (IOException e) {
  │ 17		System.err.println(e.getMessage( ));
  │ 18		return false;
  │ 19	    }
  │ 20	}
  └────
  Programme 15 : Définition de la méthode `writeXml' de la classe
  [`XmlFile']

  La classe [`XmlFile'] est basée sur un paquet `Java' appelée [`JDOM'],
  ce dernier contient des classes important comme :

  `Element'
	la représentation des tags XML en objet
  `Document'
	la représentation du document XMl en objet
  `SAXBuilder'
	pour initialiser les instances `Document'
  `XMLOutputter'
	pour l'écriture de `Document' et fichier réal


[`Printer'] cf. programme 4

[`print()'] cf. programme 4

[`JTableCellListener'] cf. programme 5

[`propertyChange()'] cf. programme 6

[`run()'] cf. programme 8

[`processEditingStopped()'] cf. programme 7

[`JTableListener'] cf. programme 5

[`JTable.isEditing()'] cf. programme 6

[`processEditingStarted()'] cf. programme 7

[`oldValue'] cf. programme 5

[`newValue'] cf. programme 5

[`SwingUtilities.invokeLater()'] cf. programme 7

[`DateUtil'] cf. programme 9

[`SimpleDateFormat'] cf. programme 9

[`static parseDate()'] cf. programme 10

[une surcharge] cf. programme 12

[`static diffDays()'] cf. programme 11

[`static diff()'] cf. programme 13

[`XmlFile'] cf. programme 14

[fichier `XML'] cf. section 3.1

[`JDOM'] http://jdom.org/


4 L'Interface Graphique
═══════════════════════

4.1 La relation entre les différents Paquets graphiques
───────────────────────────────────────────────────────

  \vspace*{\fill}
  [./diags/OverviewOnWinsAnd.png] \vspace*{\fill}


4.1.1 Paquet `wins'
╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌

◊ 4.1.1.1 La fenêtre principale `MainWin'

  Dans la partie supérieure de la fenêtre, on voit des cases à cocher
  pour générer l'élément spécifié (voir la section de [`views']), une
  zone du texte avec la sélection d'un attribue pour la recherche, c'est
  un [`SearchField']. Au centre, il y a une table qui montre les
  informations des employés/fonctionnaires déjà éxistants.

  En bas, il y a des boutons pour afficher le [dossier do chaque
  personne], afficher la fenêtre de [suivi des remboursements], afficher
  suivi les [certificats médicaux], et [suivi des avancements de
  grade]. En bas à droite, il y a deux boutons, une pour actualiser et
  une pour générer [la page correspondant] aux cases cocher en haut,
  ainsi que deux autres cases à cocher pour la sélection de type de la
  personne affichée dans la table.

  [./diags/MainWin.png]


  [`views'] cf. section 4.1.3

  [`SearchField'] cf. section 3.2.2

  [dossier do chaque personne] cf. section 4.1.2.1

  [suivi des remboursements] cf. section 4.1.2.2

  [certificats médicaux] cf. section 4.1.2.4

  [suivi des avancements de grade] cf. section 4.1.1.2

  [la page correspondant] cf. section 4.1.3


◊ 4.1.1.2 La fenêtre de suivi des avancements de grade `UpliftsWin'

  Dans la figure 8, on voit que la fenêtre contient deux parties :

  En haut
	Les avancements à venir dans la durée sélectionnée avec l'aide
	de la boîte combo. On peut aussi afficher les informations de la
	personne avec le bouton à droite.
  En bas
	Les avancements que leur temps est venu et en attente d'une
	confirmation. On peut confirmer par le bouton à droite.

	[./diags/UpliftsWin.png]

	Après la comfirmation d'un avancement, une [fenetre] s'ouvert
	pour ajouter les informations du avancement.


  [fenetre] cf. section 4.1.2.5


4.1.2 Paquet `wins.crud'
╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌

	/Toutes les formes suivantes avaient le même princip des
	données du texte (ou combos) qui permet de la modification
	de employé éxistant déjà, l'ajout d'un nouveau pour la
	suppression d'un ancien./


◊ 4.1.2.1 Gestion des Employés `InfoCrud'

  C'est la fenêtre qui permet de changer les informations de chaque
  employé ou fonctionnaire. En haut, on voit la place de l'image. Au
  centre, on voit les zones de textes pour la modification des
  informations divisées en trois sections: les informations personnelles,
  administrative et autres (qui contient des informations additionnelle
  comme le nom et prénom en arabe).

  Pour l'image, le programme lit une image qui doit exister dans le
  répertoire `data/imgs' et contient le même CIN de l'employé concerné.

  [./diags/InfoWin.png]


◊ 4.1.2.2 Gestion des Remboursements

  [./diags/repay.png]


◊ 4.1.2.3 Gestion des Diplômes

  [./diags/dips.png]


◊ 4.1.2.4 Gestion des Certificats Médicaux

  [./diags/certifs.png]


◊ 4.1.2.5 Gestion des Grades

  [./diags/upliftsmod.png]


4.1.3 Paquet `views'
╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌

  Ce paquet est intialisé par les informations du employé, voici un
  example:

  [./diags/att.png]


5 Dépendances
═════════════

  JDOM
	[http://jdom.org/] Bibliothèque `Java' pour analyser XML
  WebLaF
	[http://weblookandfeel.com/] Bibliothèque `Java' pour améliorer
	l'apparence
