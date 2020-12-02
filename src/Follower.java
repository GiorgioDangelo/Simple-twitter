

	import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
	import javax.swing.JFrame;
	import javax.swing.JLabel;
	import javax.swing.JTextField;
;

	public class Follower extends WindowManager{

		 private JFrame nuovo;
		 private JTextField nome_utente;
		
		 private String name;
		 
			public Follower (String a)
			 {name=a;}
		 
		 
		 void createwindow() {
		 nuovo= new JFrame("utente");
		 nuovo.setTitle("UTENTE");
		 nuovo.setBounds(500, 300, 700, 450);
		 nuovo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 nuovo.getContentPane().setLayout(null);
		 nuovo.setVisible(true);
		 
		 
		   //Follower che entra in follower
			JButton btnNewButton = new JButton("Follower");
			Image img_icona1 = new ImageIcon(this.getClass().getResource("follower.png")).getImage();
			btnNewButton.setIcon(new ImageIcon(img_icona1));
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) 
				{
					Utente back=new Utente(name);
					back.createwindow();
					nuovo.dispose();
				}
			 });
			btnNewButton.setBounds(0, 315, 175, 88);
			nuovo.getContentPane().add(btnNewButton);
			
			
			//Bacheca
			JButton btnNewButton_1 = new JButton("Bacheca");
			Image img_icona3 = new ImageIcon(this.getClass().getResource("home_utente.png")).getImage();
			btnNewButton_1.setIcon(new ImageIcon(img_icona3));
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) 
				{
					Contenuto back=new Contenuto(name);
					back.createwindow();
					nuovo.dispose();
				}
			 });
			
			btnNewButton_1.setBounds(173, 315, 175, 88);
			nuovo.getContentPane().add(btnNewButton_1);
			
			
			
			JButton btnNewButton_1_1 = new JButton("Messaggio");
			Image img_icona2 = new ImageIcon(this.getClass().getResource("messaggio.png")).getImage();
			btnNewButton_1_1.setIcon(new ImageIcon(img_icona2));
			btnNewButton_1_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) 
				{
					Pubblica back=new Pubblica(name);
					back.createwindow();
					nuovo.dispose();
				}
			 });
			btnNewButton_1_1.setBounds(345, 315, 175, 88);
			nuovo.getContentPane().add(btnNewButton_1_1);
		 
		    //
			JLabel lblNewLabel = new JLabel("Inserisci il nome della persona che vuoi seguire");
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
			lblNewLabel.setBounds(127, 83, 531, 39);
			nuovo.getContentPane().add(lblNewLabel);
			
			nome_utente = new JTextField();
			nome_utente.setBounds(172, 135, 263, 30);
			nuovo.getContentPane().add(nome_utente);
			nome_utente.setColumns(10);
		
			
			
			    JButton btnPubblica = new JButton("Aggiungi");
			    btnPubblica.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) 
					{
						ClickBotton();
					}
				});
				btnPubblica.setBounds(245, 198, 97, 25);
				nuovo.getContentPane().add(btnPubblica);
				
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
				btnNewButton_1_1_1_1_1.setBounds(518, 315, 170, 88);
				nuovo.getContentPane().add(btnNewButton_1_1_1_1_1);
				
				
				//Sfondo bianco
				JLabel label1 = new JLabel("");
				Image img2 = new ImageIcon(this.getClass().getResource("bianco.jpg")).getImage();
				label1.setIcon(new ImageIcon(img2));
				label1.setBounds(0, 0, 700, 460);
				nuovo.getContentPane().add(label1);
				
			}
		 
		
		 
			void ClickBotton()
			{
				/*Qui l'utente può seguire una persona e se la query va a buon fine inseriamo nella
				 * tabella seguiti l'utente che viene seguito e l'utente che segue,e andiamo
				 * a verificare se l'utente che si vuole seguire esiste nella tabella dei registrati.    */
				
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
					Connection conn1=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
								
					PreparedStatement ps=conn.prepareStatement("insert into seguiti(user_seguito,user_fk1)values(?,?);");
					//controllo andiamo a creare una nuova connessione per vedere se l'utente da seguire esiste
					Statement stmt=conn1.createStatement();
					String sql="Select *from registrati where User='"+nome_utente.getText()+"'";
					ResultSet rs=stmt.executeQuery(sql);
					
					
					ps.setString(1, nome_utente.getText());
					ps.setString(2, name);
					String utente_seguito=nome_utente.getText().toString();
					if(utente_seguito.length()==0)
					{System.out.println("non è stato inserito nessun campo");}
					else if(rs.next()) {
		            ps.executeUpdate();				
						System.out.println("Hai seguito "+nome_utente.getText());
					}
					else
					{
						System.out.println("utente non trovato");
					}
					
					
				
				}catch(Exception e) {
					System.err.println("Got an exception! ");
				      System.err.println(e.getMessage());
				}
				
				
				
			}
	}


