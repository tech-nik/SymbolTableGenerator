/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miniproject;

/**
 *
 * @author Nikhil
 */
public class Token 
{
    private String name;
    private String value;
    
    public Token(String n, String v)
    {
        name = n;
        value = v;
        
    }
    
    public String getname()
    {
        return name;
    }
    
    public String getvalue()
    {
        return value;
    }
    
}
