package th.ac.utcc.cpe.nc252.templates;



/**
 *
 * @author Suparerk Manitpornsut <suparerk_man@utcc.ac.th>
 */
public class MyLinkedList {
	MyWords first;						//ref to first link on list
	
//..............................................................
	public MyLinkedList() {				//constructor
		first = null;					//no links on list yet
	}
//..............................................................
	public MyWords insertFirst(String Words, String Translations) {	//make new link
		MyWords L1 = new MyWords(Words, Translations);
		L1.next = first;				//it points to old first link
		if(first!=null) {
		first.previous = L1;
		}
		return first = L1;
	}
//..............................................................
	public MyWords find(String i) {		//search with given key(assumes non-empty list)
		MyWords current = first;		//start at 'first'
		while(!current.Words.equalsIgnoreCase(i)) {
			if(current.next == null) {	//if end of list,
				return null;			//did not find it
			} else {					//not end of list,
				current = current.next;	//goto next link
			}	
		}return current;				//found it
	}
//..............................................................
	public MyWords delete(String i) {	//delete link with given key(assumes non-empty list)
		MyWords current = first;		//search for link
		MyWords previous = first;
		while(current.Words != i) {		//while no match,
			if(current.next == null) {	//if end of list,
				return null;			//did not find it
			} else {					//not end of list,
				previous = current;		//go to next link
				current = current.next;
			}
		}								//found it
	if(current == first)				//if first link,
		first = first.next;				//change first
	else								//otherwise
		previous.next = current.next;	//bypass it
	return current;
	}
//..............................................................	
	public void displayMyLinkedList() {	//display the list
		MyWords current = first;
		while(current != null) {		//until end of list
			current.displayMyWords();	//print data
			current = current.next;		//move to next link
		}
	}
//..............................................................	

}//end class LinkList
