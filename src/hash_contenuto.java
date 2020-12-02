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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class hash_contenuto extends WindowManager{
    private String name;
    private JFrame nuovo;
    private String valori_distinti[];
    private String utenti[];
    private int numero=0;
    
    
	public hash_contenuto(String a)
	{
	name=a;
	
	try {
		
		//Qui,tramite il nome dell'hashtag passato nel costruttore vado a fare una select 
		//selezionado tutti i messaggi contente quel messaggio
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
		Statement stmt=conn.createStatement();
		String conta_messaggi="Select COUNT(Messaggio) from messaggi WHERE hashtag='"+name+"'";
		ResultSet conta_=stmt.executeQuery(conta_messaggi);
		if(conta_.next())
		{
	     numero=conta_.getInt(1);
		}
	    int i=0;
	    int j=0;
		valori_distinti=new String[numero];
		utenti=new String[numero];
		String messaggi="Select Messaggio,utente_seguito_fk from messaggi WHERE hashtag='"+name+"'";
	    ResultSet mess=stmt.executeQuery(messaggi);
	    while(mess.next())
		{
		 valori_distinti[i++]=mess.getString(1);
		 utenti[j++]=mess.getString(2);
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
		 panel.setLayout(new GridLayout(numero,0,-200,0));
		
		 JLabel [] labes=new JLabel[numero];
	
		 for(int i=0;i<numero;i++)
		 {
			
			 labes[i]=new JLabel(utenti[i]+":          "+ name +" "+ valori_distinti[i]); 
	
			 panel.add(labes[i]);
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
