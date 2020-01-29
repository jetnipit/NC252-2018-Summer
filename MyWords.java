package th.ac.utcc.cpe.nc252.templates;

/**
 *
 * @author Suparerk Manitpornsut <suparerk_man@utcc.ac.th>
 */
public class MyWords {
	public String Words;				//data item (key)
	public String Translations;			//data item
	public long lData;					//(doubly)link list
	public MyWords next;				//next link in list
	public MyWords previous;			//(doubly)previous link in list

//..............................................................
	public MyWords(String words, String translations) {	//constructor
		Words = words;
		Translations = translations;		
	}
//..............................................................
	public void displayMyWords() {			//display ourself
		System.out.print(Words + "   " + Translations + "\n");
	}
//..............................................................
	@Override
	public String toString() {
		return Words + "   " + Translations;
	}
	public String getWords() {
		return Words;
	}
	public void setWords(String words) {
		Words = words;
	}
	public String getTranslations() {
		return Translations;
	}
	public void setTranslations(String translations) {
		Translations = translations;
	}

}
