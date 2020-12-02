import javax.swing.*;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Contenuto extends WindowManager{
	
	
	
	private String name;
	private int numero=0;
	private String[] arr;
	private String[] arr1;
	 
	 
	public Contenuto(String a)
	{
		
		name=a;
		try {
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
		Statement stmt=conn.createStatement();
		/*La classe contenuto è la bacheca che ha un utente,è bene ricordare anche se è un'altra classe che non è login che sappiamo
		 * sempre chi è l'utente connesso in quanto il nostro costruttore vuole la stringa del nome che ha effettuato il login.
		 * Effettuiamo questa query per verificare se l'utente che si è connesso ha dei follower che hanno scritto dei messaggi
		 * e facciamo anche il count in modo da allocare dinamicamente i nostri Label    */
		
		String sql="SELECT messaggi.Messaggio,messaggi.utente_seguito_fk FROM registrati INNER JOIN seguiti ON registrati.User=seguiti.user_fk1 INNER JOIN messaggi ON seguiti.user_seguito=messaggi.utente_seguito_fk WHERE seguiti.user_fk1='"+name+"'"; 
		String dinamica="SELECT count(*) FROM registrati INNER JOIN seguiti ON registrati.User=seguiti.user_fk1 INNER JOIN messaggi ON seguiti.user_seguito=messaggi.utente_seguito_fk WHERE seguiti.user_fk1='"+name+"'"; 
		ResultSet ok=stmt.executeQuery(dinamica);
		if(ok.next())
		{
	     numero=ok.getInt(1);
		}
		
		arr=new String[numero];
		arr1=new String[numero];
		
		int i = 0;
		int j = 0;
		ResultSet rs=stmt.executeQuery(sql);
		
		while(rs.next()) {
			arr[i++]=rs.getString(1);//Andiamo a salvare il messaggio nell'array
			arr1[j++]=rs.getString(2);//nome di chi ha scritto il messaggio
		}
		}catch(Exception e) {System.out.print(e);}
	
	}
	
	
	void createwindow()
	{
		
		/*Se il numero restituito è maggiore di 0 significa che il nostro utente
		 * avrà sicuramente dei follower e che questi ultimi hanno scritto almeno 1 messaggio*/
		if (numero>0)
		{
			
	
		 JFrame nuovo = new JFrame("MESSAGGI PUBBLICATI");
		 nuovo.setVisible(true);
		 nuovo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 nuovo.setBounds(500, 300, 700, 450);
		 
		 nuovo.getContentPane().setLayout(null);
		 
		 JPanel panel=new JPanel();
		
		 panel.setBounds(0, 105, 682, 298);
		 panel.setLayout(new GridLayout(numero,numero,-350,0));
		/*Qui allochiamo dinamicamente i Label dove vediamo si il 
		 * nome della persona che ha scritto il messaggio che il messaggio   */
		 JLabel [] labes=new JLabel[numero];
		 JLabel [] labes1=new JLabel[numero];
		 for(int i=0;i<numero;i++)
		 {
			 labes1[i]=new JLabel(arr1[i]+" ha pubblicato");
			 labes[i]=new JLabel(arr[i]); 
			 
			 panel.add(labes1[i]);
			 panel.add(labes[i]);
		 } 
        panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        nuovo.add(panel);
        
        
		//Barra scroll
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
		
	 		//Bottone notifica
				JButton btnNewButton_1_1_1_1_1 = new JButton("Notifica");
				btnNewButton_1_1_1_1_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) 
					{
						Notifica not=new Notifica(name);
						not.createwindow();
						nuovo.dispose();
				
					}});
				Image img_icona4 = new ImageIcon(this.getClass().getResource("notifica.png")).getImage();
				btnNewButton_1_1_1_1_1.setIcon(new ImageIcon(img_icona4));
				btnNewButton_1_1_1_1_1.setBounds(518, 415, 185, 88);
				nuovo.getContentPane().add(btnNewButton_1_1_1_1_1);
	 		 
	 		//Follower
				JButton btnSegui = new JButton("Follower");
				Image img_icona1 = new ImageIcon(this.getClass().getResource("follower.png")).getImage();
				btnSegui.setIcon(new ImageIcon(img_icona1));
				btnSegui.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) 
					{
						Follower amico=new Follower(name);
						amico.createwindow();
						nuovo.dispose();
					}
				});
				
				btnSegui.setBounds(0, 415, 175, 88);
				nuovo.getContentPane().add(btnSegui);
				
				//Scrivi messaggio
				JButton btnInvia = new JButton("Pubblica");
				Image img_icona2 = new ImageIcon(this.getClass().getResource("messaggio.png")).getImage();
				btnInvia.setIcon(new ImageIcon(img_icona2));
				btnInvia.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) 
					{
						Pubblica mess=new Pubblica(name);
						mess.createwindow();
						nuovo.dispose();
				
					}});
				btnInvia.setBounds(345, 415, 175, 88);
				nuovo.getContentPane().add(btnInvia);
				
				//Bacheca
				JButton btnContenuti = new JButton("");
				Image img_icona3 = new ImageIcon(this.getClass().getResource("home_utente.png")).getImage();
				btnContenuti.setIcon(new ImageIcon(img_icona3));
				btnContenuti.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) 
					{
						Utente ok =new Utente(name);
						ok.createwindow();
						nuovo.dispose();
					}
				 });
				btnContenuti.setBounds(173, 415, 175, 88);
				nuovo.getContentPane().add(btnContenuti);
			
				JPanel panel_1 = new JPanel();
				panel_1.setBounds(0, 313, 700, 225);
				panel_1.setBackground(Color.white);
				nuovo.getContentPane().add(panel_1);
		}
		else
			/*Se la query non restituisce un risultato maggiore di 0 significa o che il nostro utente non segue nessuno oppure
			 * che le persone che segue il nostro utente non hanno pubblicato nulla
			 
			 */
		{
			
			 JFrame nuovo = new JFrame("MESSAGGI PUBBLICATI");
			 nuovo.setVisible(true);
			 nuovo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			 nuovo.setBounds(500, 300, 700, 450);
			 
			    nuovo.getContentPane().setLayout(null);
				JLabel lblNewLabel = new JLabel("NON CI SONO MESSAGGI DA LEGGERE");
				lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
				lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
				lblNewLabel.setBounds(12, 145, 658, 54);
				nuovo.getContentPane().add(lblNewLabel);
				
				//Bottone notifica
				JButton btnNewButton_1_1_1_1_1 = new JButton("Notifica");
				btnNewButton_1_1_1_1_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) 
					{
						Notifica not=new Notifica(name);
						not.createwindow();
						nuovo.dispose();
				
					}});
				Image img_icona4 = new ImageIcon(this.getClass().getResource("notifica.png")).getImage();
				btnNewButton_1_1_1_1_1.setIcon(new ImageIcon(img_icona4));
				btnNewButton_1_1_1_1_1.setBounds(518, 315, 185, 88);
				nuovo.getContentPane().add(btnNewButton_1_1_1_1_1);
	 		 
	 		//Follower
				JButton btnSegui = new JButton("Follower");
				Image img_icona1 = new ImageIcon(this.getClass().getResource("follower.png")).getImage();
				btnSegui.setIcon(new ImageIcon(img_icona1));
				btnSegui.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) 
					{
						Follower amico=new Follower(name);
						amico.createwindow();
						nuovo.dispose();
					}
				});
				
				btnSegui.setBounds(0, 315, 175, 88);
				nuovo.getContentPane().add(btnSegui);
				
				//Scrivi messaggio
				JButton btnInvia = new JButton("Pubblica");
				Image img_icona2 = new ImageIcon(this.getClass().getResource("messaggio.png")).getImage();
				btnInvia.setIcon(new ImageIcon(img_icona2));
				btnInvia.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) 
					{
						Pubblica mess=new Pubblica(name);
						mess.createwindow();
						nuovo.dispose();
				
					}});
				btnInvia.setBounds(345, 315, 175, 88);
				nuovo.getContentPane().add(btnInvia);
				
				//Bacheca
				JButton btnContenuti = new JButton("");
				Image img_icona3 = new ImageIcon(this.getClass().getResource("home_utente.png")).getImage();
				btnContenuti.setIcon(new ImageIcon(img_icona3));
				btnContenuti.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) 
					{
						Utente ok =new Utente(name);
						ok.createwindow();
						nuovo.dispose();
					}
				 });
				btnContenuti.setBounds(173, 315, 175, 88);
				nuovo.getContentPane().add(btnContenuti);
				  //Sfondo bianco
				JLabel label = new JLabel("");
				Image img1 = new ImageIcon(this.getClass().getResource("bianco.jpg")).getImage();
				label.setIcon(new ImageIcon(img1));
				label.setBounds(0, 0, 700, 460);
				nuovo.getContentPane().add(label);
		}
			
				

	}


	
	//refresh
	void ClickBotton() {}
	

}
