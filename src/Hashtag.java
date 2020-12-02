import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.*;

public class Hashtag extends WindowManager{
       private JFrame nuovo;
       private String[] valori_distinti;

       private int numero=0;
       private int numero_1=0;
	
	public Hashtag()
	{	try {
		
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
		Statement stmt=conn.createStatement();
		/*Andiamo a contare tutti gli hashtag distinti che esistono   */
		String conta_hashtag="SELECT COUNT(DISTINCT hashtag) FROM messaggi WHERE hashtag NOT IN (SELECT hashtag FROM messaggi WHERE hashtag='') ";
		
		ResultSet conta=stmt.executeQuery(conta_hashtag);
		if(conta.next())
		{
	     numero=conta.getInt(1);
		}
	
		valori_distinti=new String[numero];
		//Qui invece andiamo a selezionarli ed inserirli nell'array valori_distinti
		String hashtag_distinti="SELECT DISTINCT hashtag FROM messaggi WHERE hashtag NOT IN (SELECT hashtag FROM messaggi WHERE hashtag='') ";
		ResultSet hash=stmt.executeQuery(hashtag_distinti);
		int i=0;
		
		while(hash.next())
		{
		 valori_distinti[i++]=hash.getString(1);
		 
		}
		
		String dimensione_messaggi="SELECT COUNT(Messaggio) FROM messaggi WHERE hashtag NOT IN (SELECT hashtag FROM messaggi WHERE hashtag='') ";
		ResultSet conta_1=stmt.executeQuery(dimensione_messaggi);
		if(conta_1.next())
		{
	     numero_1=conta_1.getInt(1);
		}
	    

		
	    
		
		
		}catch(Exception e) {System.out.print(e);}
	}
	
	
void createwindow()
{
	 nuovo= new JFrame("Hashtag");
	 nuovo.setTitle("Hashtag");
	 nuovo.setBounds(500, 300, 700, 500);
	 nuovo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 nuovo.getContentPane().setLayout(null);
	 nuovo.setVisible(true);
	 
		
	 JPanel panel=new JPanel();
	
	 panel.setBounds(0, 105, 682, 298);
	 panel.setLayout(new GridLayout(numero_1,0,-200,0));
	
	/*   Qui andiamo a creare un array di bottoni dove ogni bottone avrà un nome dell'hashtag            */
	 JButton [] labes1=new JButton[numero_1];
	 
	 for(int i=0;i<valori_distinti.length;i++)
	 {
		 labes1[i]=new JButton(valori_distinti[i]);
		 labes1[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) 
				{
			//String buttontext mi restituisce il nome del bottone cliccato in quel momento e passarlo
		    // Al costruttore della classe hash_contenuto,cosi con il nome dell'hashtag,farò una select e andrò a
					//restituire tutti i messaggi che hanno quell'hashtag
			String buttonText = ((JButton) e.getSource()).getText();
			hash_contenuto back=new hash_contenuto(buttonText);
		
			back.createwindow();
			nuovo.dispose();
				}
			 });
		 
		 panel.add(labes1[i]);
		
	 } 
    panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
    nuovo.add(panel);
    
    
	//
	 JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(0, 0, 700, 350);
       
        JPanel contentPane = new JPanel(null);
        contentPane.setPreferredSize(new Dimension(700, 500));
         contentPane.add(scrollPane);
         nuovo.setContentPane(contentPane);
         nuovo.pack(); 
 		 panel.setBackground(Color.white);
	 
	 
	 
	 
	 
	 
 		//Bottone per tornare all'home
 		JButton come_back = new JButton("");
 		Image img_icona = new ImageIcon(this.getClass().getResource("back.png")).getImage();
 		come_back.setIcon(new ImageIcon(img_icona));
 		come_back.addActionListener(new ActionListener() {
 			public void actionPerformed(ActionEvent e) 
 			{
 				Amministratore back=new Amministratore();
 				back.createwindow();
 				nuovo.dispose();
 			}
 		 });
 		come_back.setOpaque(false);
 		come_back.setBorderPainted(false);
 		come_back.setContentAreaFilled(false);
 		come_back.setBorder(null);
 		come_back.setBounds(0, 435, 700, 64);
 		nuovo.getContentPane().add(come_back);
 		
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	//Sfondo bianco
			JLabel label1 = new JLabel("");
			Image img2 = new ImageIcon(this.getClass().getResource("bianco.jpg")).getImage();
			label1.setIcon(new ImageIcon(img2));
			label1.setBounds(0, 0, 700, 550);
			nuovo.getContentPane().add(label1);
			
		
}



void ClickBotton()
{
	
}
}
