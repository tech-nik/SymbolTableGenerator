/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miniproject;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author Nikhil
 */
public class outputpanel extends JPanel
{
    private SymbolTable st;
    private JScrollPane sc;
    
    public void showtable (SymbolTable s)
    {
        st = s;
        sc = new JScrollPane(st);
        add (sc);
        setVisible(true);
        
    }
    public void reset ()
    {
        remove(sc);
        sc = null;
        setVisible(false);
    }
    
    
}
