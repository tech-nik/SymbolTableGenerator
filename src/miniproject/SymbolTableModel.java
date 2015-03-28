/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miniproject;


import java.util.LinkedList;
import java.util.Stack;
import java.util.Vector;
import javax.swing.table.AbstractTableModel;


/**
 *
 * @author Nikhil
 */



public class SymbolTableModel extends AbstractTableModel
{
    
    private LinkedList<Token> tokens ;
    private String[][] data;
    private final String[] columnnames;
    private Stack<String> scopestack;
    private Vector<String> variables;
    private Vector<String> structures;
    
    public SymbolTableModel(LinkedList<Token> ll)
    {
        this.columnnames = new String[]{"Name", "Type", "Size","Scope"};
        tokens = ll;
        scopestack = new Stack<>();
        scopestack.push("global");
        data = new String[40][4];
        
        variables = new Vector<>();
        structures = new Vector<>();
        
    }
    
    public Token getnexttoken(int i)
    {
        return gettoken(i+1);
    }
    
    public Token getprevioustoken(int i)
    {

        return gettoken(i-1);
    }
    
    public Token gettoken(int i)
    {
        try
        {
           
            return tokens.get(i);
        }
        catch (Exception e)
        {
            return null;
        }
        
    }
    
    public Token getpreviouskeyword(int i)
    {
       
        Token t = gettoken(i-1);
        if (t.getname().equals("KEYWORD"))
            return t;
        else 
            return getpreviouskeyword(i-1);
    }
    
    public Token getpreviousidentifier(int i)
    {
        Token t = gettoken(i-1);
        if (t.getname().equals("IDENTIFIER"))
            return t;
        else
            return getpreviousidentifier(i-1);
    }
    
      
    public void make()
    {
        int m = 0;
        int n=0;
        while (m < tokens.size())
        {
            Token x = tokens.get(m);
            if ("OP_PARA".equals(x.getvalue()))
            {
                scopestack.push(getpreviousidentifier(m).getvalue());
            }
            else if ("CL_PARA".equals(x.getvalue()))
            {
                scopestack.pop();
            }
            else if ("struct".equals(x.getvalue()))
            {
                structures.add(getnexttoken(m).getvalue());
                
                
            }

            else if ("IDENTIFIER".equals(x.getname()))
  bad:      {
                String type; 
                Token next = getnexttoken(m);

                if (!"OP_BRA".equals(next.getvalue()))
                {
                    String name = x.getvalue();
                    if (structures.contains(getprevioustoken(m).getvalue()))
                    {
                        type = "struct";
                    }
                    else
                    {                        
                        type = getpreviouskeyword(m).getvalue();
                    }
                    
                    if (getprevioustoken(m).getvalue() == "MOD")
                        break bad;
                  
                    
                    String scope = scopestack.peek();
                    String size = "";
                    switch (type)
                    {
                        case "int" :
                            size = "FOUR";
                            break;
                        case "char" :
                            size = "TWO";
                            break;
                        case "float" :
                            size = "EIGHT";
                            break;
                        case "double" :
                            size = "SIXTEEN";
                            break;
                                                             
                    }
                    
                                                         
                    if (!variables.contains(name))
                    {   variables.add(name);
                        data [n][0] = name;
                        data [n][1] = type;
                        data [n][2] = size;
                        data [n][3] = scope;
                        n++;
                    }                    
                }    
            }
            
        m++;
        }
        
    }
    

    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public int getColumnCount() {
        return columnnames.length;
    }

    @Override
    public Object getValueAt(int i, int i1) {
        return data[i][i1];
    }
    
    @Override
    public String getColumnName(int col) 
    {
        return columnnames[col];
    }
}


