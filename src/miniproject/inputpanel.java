/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miniproject;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author Nikhil
 */
public class inputpanel extends JPanel implements MouseListener
{
    private JScrollPane scroll;;
    private JTextArea txt;

    
    public inputpanel()
    {
        
        txt = new JTextArea("Enter the code here!");
        txt.setSize(550,500);
        txt.addMouseListener(this);
        scroll = new JScrollPane(txt);
        
        setLayout(new BorderLayout());

        add(scroll,BorderLayout.CENTER);

       // System.err.println("inputpanel created");
       
                
    }
    
    public String getcode()
    {
        String code = txt.getText().replaceAll("/\\*.*\\*/","");
        code = code.replaceAll("#.*h>" ,"");
        System.err.println("input is : "+code);
        return code;
    }
    
    public void reset()
    {
        txt.setText("");
    }

    
    @Override
    public void mouseClicked(MouseEvent me) 
    {
        
    }

    @Override
    public void mousePressed(MouseEvent me) 
    {
        
    }

    @Override
    public void mouseReleased(MouseEvent me) 
    {
        
    }

    @Override
    public void mouseEntered(MouseEvent me) 
    {
        if("Enter the code here!".equals(txt.getText()))
                 txt.setText("");
        
    }

    @Override
    public void mouseExited(MouseEvent me) 
    {
        if ((txt.getText().length() == 0))
        {
            txt.setText("Enter the code here!");
        }
        
    }
    
    
}
