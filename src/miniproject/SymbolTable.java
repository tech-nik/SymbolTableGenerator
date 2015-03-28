/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miniproject;

import java.util.LinkedList;
import java.util.Vector;
import javax.swing.JTable;

/**
 *
 * @author Nikhil
 */

//creates a JTable based on the tablemodel passed as argument

public class SymbolTable extends JTable
{
    
    public SymbolTable(SymbolTableModel x)
    {
        super(x);
       // System.err.println("SymbolTable created");
        
    }
}
