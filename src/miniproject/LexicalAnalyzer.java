/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miniproject;

import java.util.Arrays;
import java.util.LinkedList;

/**
 *
 * @author Nikhil
 */
public class LexicalAnalyzer  //didnt implement constant tokens!!!
{
    private String code;
    private int index;
    private String lexeme;
    private LinkedList<Token> tokens;
    private LinkedList <String> keywords;
    
    public LexicalAnalyzer(String sc)
    {
        code = sc;
        index = 0;
        lexeme = "";
        tokens = new LinkedList<>();
        keywords = new LinkedList<>();
        String k = "auto,break,bool,case,char,const,continue,default,do,double,else,enum,extern,float,for,goto,if,int,long,register,return,short,signed,sizeof,static,struct,switch,typedef,union,unsigned,void,volatile,while";
        String [] kw = k.split(",");
        keywords.addAll(Arrays.asList(kw));
        //System.err.println("LexicalAnalyzer created");
        
    }
    
    public void addToken(String n)
    { 
        Token t = new Token(n, lexeme);
        //if (!tokens.contains(t))
        //{    
            tokens.add(t);
            lexeme= "";
            System.out.println("token added : <"+t.getname()+","+t.getvalue()+">" );
        //}
    }
    
    public void addToken(String n, String v )
    {
        Token t = new Token(n, v);
        //if (!tokens.contains(t))
       // { 
            tokens.add(t);
            lexeme="";
         System.out.println("token added : <"+t.getname()+","+t.getvalue()+">" );
    
        //}
    }
    
    public boolean iskeyword()
    {
        if (keywords.contains(lexeme))
                return true;
        else 
            return false;
    }
    
    /*scan characters from left to right...if current char is alphanumeric,add it to current lexeme and increment input pointer...
     * if its not alphanumeric, check if lexeme is a keyword..if yes create a keyword token else create a identifier token..reset lexeme...
     * add current character to lexeme, if its space,increment index..if its special character,create special character token and increment index
     * 
     */
       
    public LinkedList<Token> analyse()
    {
        while (index < code.length())
        {
            char currentchar = code.charAt(index);
            if ((Character.isLetter(currentchar)) || (Character.isDigit(currentchar)))
            {
                lexeme+=currentchar;  
                index++;
                //System.out.println(lexeme);
            }
            
            else
            {
                char nextchar;
                
                if(iskeyword())
                    addToken("KEYWORD");   
                else
                {
                    try
                    {
                        int xyz = Integer.parseInt(lexeme);
                        addToken("CONSTANT");
                    }
                    
                    catch(Exception e)
                    {    
                        if (!lexeme.isEmpty())
                        addToken("IDENTIFIER");
                    }
                }   
                
              
                //lexeme+=currentchar;   // is this required??
                
                free : switch (currentchar)
                {
                    case '(' :
                        addToken("SYMBOL","OP_BRA");    
                        break;
                    case ')' :
                        addToken("SYMBOL","CL_BRA");
                        break;
                    case '{' :
                        addToken("SYMBOL","OP_PARA");
                        break;
                    case '}' :
                        addToken("SYMBOL","CL_PARA");
                        break;
                    case '[' :
                        addToken("SYMBOL","OP_SQBRA");
                        break;
                    case ']'  :
                        addToken("SYMBOL","CL_SQBRA");
                        break;
                    case ':' :
                        addToken("SYMBOL","COLON");
                        break;
                    case ';' :
                        addToken("SYMBOL","SEMI_COL");
                        break;
                    case '#' :
                        addToken("SYMBOL","HASH");
                        break;
                    case '.' :
                        addToken("SYMBOL", "DOT");
                        break;
                    case '=' :
                        index++;
                        nextchar = code.charAt(index);
                        if (nextchar == '=')
                        {
                            addToken("RELOP", "DE");
                            break free;
                        }
                        else
                        {
                            index--;
                            addToken("ASSIGNOP","EQ");
                            break free;
                        }
                    case '!' :
                        index++;
                        nextchar = code.charAt(index);
                        if (nextchar == '=')
                        {
                            addToken("RELOP", "NE");
                            break free;
                        }
                        else
                        {
                            index--;
                            addToken("LOGOP","NOT");
                            break free;
                        }
                    case '>' :
                        index++;
                        nextchar = code.charAt(index);
                        if (nextchar == '=')
                        {
                            addToken("RELOP", "GE");
                            break free;
                        }
                        else
                        {
                            index --;
                            addToken("RELOP","GT");
                            break free;
                        }
                    case '<' :
                        index++;
                        nextchar = code.charAt(index);
                        if (nextchar == '=')
                        {
                            addToken("RELOP","LE");
                            break free;
                        }
                        else
                        {
                            index--;
                            addToken("RELOP","LT");
                            break free;
                        }
                    case '&' :
                        index++;
                        nextchar = code.charAt(index);
                        if (nextchar == '&')
                        {
                            addToken("LOGOP","AND");
                        }
                        break free;
                    case '|' :
                        index++;
                        nextchar = code.charAt(index);
                        if (nextchar == '|')
                        {
                            addToken("LOGOP","OR");
                        }
                        break free;
                    case '+' :
                        index++;
                        nextchar = code.charAt(index);
                        if (nextchar == '=')
                        {
                            addToken("ASSIGNOP","PLE");
                            break free;
                        }
                        else if (nextchar == '+')
                        {
                            addToken("AROP","PLPL");
                            break free;
                        }
                        else 
                        {
                            index--;
                            addToken("AROP","PL");
                            break free;
                        }
                    case '-' :
                        index++;
                        nextchar = code.charAt(index);
                        if (nextchar == '=')
                        {
                            addToken("ASSIGNOP","MINE");
                            break free;
                        }
                        else if (nextchar == '-')
                        {
                            addToken("AROP", "MINMIN");
                        }
                        else
                        {
                            index--;
                            addToken("AROP","MIN");
                            break free;
                        }
                    case '*' :
                        index++;
                        nextchar = code.charAt(index);
                        if (nextchar == '=')
                        {
                            addToken("ASSIGNOP","MULE");
                            break free;
                        }
                        else if (nextchar == '/')
                        {
                            addToken("COMMENT","END" );
                            break free;
                        }
                        else
                        {
                            index--;
                            addToken("AROP","MUL");
                            break free;
                        }
                    case '/' :
                        index++;
                        nextchar = code.charAt(index);
                        if (nextchar == '=')
                        {
                            addToken("ASSIGNOP","DIVE");
                            break free;
                        }
                        else if (nextchar == '*')
                        {
                            addToken("COMMENT","BEGIN");
                            break free;
                        }
                        else
                        {
                            index--;
                            addToken("AROP","DIV");
                            break free;
                        }
                    case '%' :
                        index++;
                        nextchar = code.charAt(index);
                        if (nextchar == '=')
                        {
                            addToken("ASSIGNOP","MODE");
                            break free;
                        }
                        else
                        {
                            index--;
                            addToken("AROP","MOD");
                            break free;
                        }
                    default :
                        //`index++;
                }
                
               
            index++; 
            }
            
        }
        
       //System.err.println("Analyse() completed");
        return tokens;
    }
    
    
}
