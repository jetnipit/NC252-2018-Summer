package th.ac.utcc.cpe.nc252.templates;

import javax.swing.JFileChooser;

//import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SimpleDictionary extends javax.swing.JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public MyLinkedList myll;
	public File PathGlo;
	public String keyWord;
	public String keyWordf = null;
	
	public SimpleDictionary() {
        initComponents();

    }
//...............................................................................
    public void btnLoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));

        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            // User already chose a file. 
            File selectedFile = fileChooser.getSelectedFile();
            // TODO Add more code to load all lines in the file into a selected data structure
            
            this.initDictionary(selectedFile);
            
            // When loading has been done, enable other fbuttons
            this.btnNext.setEnabled(true);
            this.btnPrevious.setEnabled(true);
            this.btnSearch.setEnabled(true);
            this.btnSort.setEnabled(true);
            }
        }//GEN-LAST:event_btnLoadActionPerformed
//...............................................................................
    public void initDictionary(File PathFile) {
        // Read xlsx file, containing list of words and their corresponding translation.
    	try {
    		myll = new MyLinkedList();
    		String line;
    		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(PathFile), "UTF-8"));
    		while ((line = br.readLine()) != null) {
    			String Words = line.substring(0, line.indexOf(","));
    			String Translations = line.substring(line.indexOf(",")+1);
    			myll.insertFirst(Words, Translations);
 			}
			br.close();
			System.out.println("\n===== btnLoad (insertFirst) =====");
			myll.displayMyLinkedList();
    	} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    	}     	
    	PathGlo = PathFile; 
    }
//...............................................................................
    private void tfdSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfdSearchActionPerformed
    	keyWord = tfdSearch.getText();
    	this.txaTranslation.setText(keyWord);
    }//GEN-LAST:event_tfdSearchActionPerformed
//...............................................................................    
    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
    	keyWord = tfdSearch.getText();
    	MyWords f = myll.find(keyWord);
		System.out.println("\n===== btnSearch (find) =====");
    	if(f != null) {
    		this.txaTranslation.setText(f.toString());
			System.out.println("Found = "+f.toString());
    		keyWordf = f.Words;
    	} else {
			this.txaTranslation.setText("Can not find.");
			System.out.println("Can not find.");
		}			
    }//GEN-LAST:event_btnSearchActionPerformed
//...............................................................................
    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
		System.out.println("\n===== btnNext =====");
    	MyWords f1 = myll.find(keyWordf); //current
    	if(f1 == null) {
    		this.txaTranslation.setText("Please enter word in field.");
    		System.out.println("Please enter word in field.");
    	} else {
    		MyWords next = f1.next;
    		keyWordf = next.Words;
    		this.txaTranslation.setText(next.toString());
    		System.out.println(next.toString());
    	}
    }//GEN-LAST:event_btnNextActionPerformed
//...............................................................................
    private void btnPreviousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreviousActionPerformed
    	MyWords f2 = myll.find(keyWordf);
    	System.out.println("\n===== btnPrevious =====");
    	if(f2 == null) {
    		this.txaTranslation.setText("Please enter word in field.");
    		System.out.println("Please enter word in field.");
    	} else {
    		MyWords previous = f2.previous;
    		keyWordf = previous.Words;
    		this.txaTranslation.setText(previous.toString());
    		System.out.println(previous.toString());
    	}	
    }//GEN-LAST:event_btnPreviousActionPerformed
//...............................................................................
    public void sort1() {
    	File file = PathGlo;
	    try {
	    	myll = new MyLinkedList();
	    	String line;
	    	BufferedReader br = new BufferedReader(new FileReader(file));
	    	while ((line = br.readLine()) != null) {
	    		String Words = line.substring(0, line.indexOf(","));
	    		String Translations = line.substring(line.indexOf(",")+1);
	    		myll.insertFirst(Words, Translations);
	 		}
			br.close();
			System.out.println("\n===== List in initDictionary() =====");
			myll.displayMyLinkedList();
	    } catch (IOException e) {
	    		// TODO Auto-generated catch block
	    		e.printStackTrace();
	    }     	
    }
//...............................................................................
    public void sort0() {
    	File file = PathGlo;
		List<String> myList = new ArrayList<String>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;
			while ((line = br.readLine()) != null) {
				myList.add(line);
			}
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String[] myArr = new String[myList.size()];
		myList.toArray(myArr); 
   	
    	String sortedArray[] = insertionSort(myArr, myArr.length); 
		System.out.println("\n===== btnSort (insertionSort) =====");
		for(int i = 0; i < sortedArray.length; i++) {
//    		this.txaTranslation.setText(sortedArray[i]);
    		System.out.println(sortedArray[i]);
    	}
		String ss = Arrays.toString(sortedArray);
		this.txaTranslation.setText(ss);
    }

    public static String[] insertionSort(String array[], int f) {
    	String temp;
    	for(int out = 0; out < f; out++) {
   			for(int in = out+1; in < f; in++) {
   				if(array[out].compareToIgnoreCase(array[in]) > 0) {
   					temp = array[out];
   					array[out] = array[in];
   					array[in] = temp;
   				}
   			}
   		}
   		return array;
   	}
//...............................................................................
    private void btnSortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSortActionPerformed
    	sort0();
    }//GEN-LAST:event_btnSortActionPerformed
//...............................................................................
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblSearch = new javax.swing.JLabel();
        tfdSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnPrevious = new javax.swing.JButton();
        txaContent = new javax.swing.JScrollPane();
        txaTranslation = new javax.swing.JTextArea();
        lblTranslation = new javax.swing.JLabel();
        btnSort = new javax.swing.JButton();
        btnLoad = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Simple Dictionary");

        lblSearch.setText("Find What:");

        tfdSearch.setToolTipText("Enter your word here.");
        tfdSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfdSearchActionPerformed(evt);
            }
        });

        btnSearch.setText("Search");
        btnSearch.setEnabled(false);
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        btnNext.setText("Next");
        btnNext.setEnabled(false);
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        btnPrevious.setText("Previous");
        btnPrevious.setEnabled(false);
        btnPrevious.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPreviousActionPerformed(evt);
            }
        });

        txaTranslation.setColumns(50);
        txaTranslation.setRows(5);
        txaTranslation.setText("translation");
        txaTranslation.setEnabled(false);
        txaContent.setViewportView(txaTranslation);

        lblTranslation.setText("Translation:");

        btnSort.setText("Sort");
        btnSort.setEnabled(false);
        btnSort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSortActionPerformed(evt);
            }
        });

        btnLoad.setText("Load");
        btnLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoadActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(lblTranslation)
                    .add(lblSearch))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(tfdSearch)
                            .add(layout.createSequentialGroup()
                                .add(btnSearch, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(btnLoad, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 68, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(btnSort, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 68, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, btnPrevious, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(btnNext, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .add(txaContent))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(lblSearch)
                    .add(tfdSearch, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(btnNext))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(btnSearch)
                    .add(btnPrevious)
                    .add(btnSort)
                    .add(btnLoad))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(txaContent, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
                    .add(layout.createSequentialGroup()
                        .add(lblTranslation)
                        .add(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
//...............................................................................
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            javax.swing.UIManager.LookAndFeelInfo[] installedLookAndFeels=javax.swing.UIManager.getInstalledLookAndFeels();
            for (int idx=0; idx<installedLookAndFeels.length; idx++)
                if ("Nimbus".equals(installedLookAndFeels[idx].getName())) {
                    javax.swing.UIManager.setLookAndFeel(installedLookAndFeels[idx].getClassName());
                    break;
                }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SimpleDictionary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SimpleDictionary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SimpleDictionary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SimpleDictionary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SimpleDictionary().setVisible(true);
            }
        });
    }
    // Declare more class variables here
    //private String translation = "You just pressed Enter.";
    //private MyLinkedList dict;
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLoad;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrevious;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnSort;
    private javax.swing.JLabel lblSearch;
    private javax.swing.JLabel lblTranslation;
    private javax.swing.JTextField tfdSearch;
    private javax.swing.JScrollPane txaContent;
    private javax.swing.JTextArea txaTranslation;
}
