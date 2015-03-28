/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miniproject;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


/**
 *
 * @author Nikhil
 */

//structures have to be declared explicitly....functions with arguments not allowed

public class Main extends JFrame implements ActionListener
{
    private inputpanel inputpanel;
    private outputpanel outputpanel;
    private JButton submit;
    private SymbolTable symboltable;
    private SymbolTableModel model;
        
    public Main()
    {
        super("Symbol Table Generator");
        inputpanel = new inputpanel();
        model = null;
        symboltable = null;
        submit = new JButton("Submit");
        submit.addActionListener(this);
        outputpanel = new outputpanel();
      
        
        this.setSize(1150, 650);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        inputpanel.setBounds(20,20,550,500);
        this.add(inputpanel);
        
        outputpanel.setBounds(600, 20, 550, 500);
        this.add(outputpanel);
        
        submit.setBounds(530,540,120,60);
        this.add(submit);
        
        
        
        this.setVisible(true);
        
        
        
    }
    
    public static void main(String [] args)
    {
        Main m = new Main();
    }

   
    
    
    
    @Override
    public void actionPerformed(ActionEvent ae) 
    {
        JButton btn = (JButton)ae.getSource();
        if ("Submit".equals(btn.getText()))
        {
            String code = inputpanel.getcode();
            if ((code.length()== 0) || (code.equals("Enter the code here!")) )
            {
                JOptionPane.showMessageDialog(this,"Oops! looks like you forgot to enter code!" );
            
            }
            else
            {
                LexicalAnalyzer la = new LexicalAnalyzer(code);
                LinkedList<Token> tokens = la.analyse();
                model = new SymbolTableModel(tokens);
                model.make();
                symboltable = new SymbolTable(model);
                outputpanel.showtable(symboltable);
                submit.setText("Reset");
                revalidate();
                repaint();
                
            }
            
        
        }
        else if ("Reset".equals(btn.getText()))
        {
            inputpanel.reset();
            outputpanel.reset();
            symboltable = null;
            model = null;
            submit.setText("Submit");
            revalidate();
            repaint();
        }
       
        
    }
    
}
