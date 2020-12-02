import java.awt.Color;

import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
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
import javax.swing.SwingConstants;


public class Notifica extends WindowManager {

	private JFrame nuovo;
	 
	private String name;
	private String arr[];
	private int numero=0;
	 
		public Notifica (String a)
		 {	name=a;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
				Statement stmt=conn.createStatement();
	/*Poichè ci siamo passati il nome dell'utente attualmente connesso nel costruttore siamo chi è l'utente in questione,
	 * andiamo a fare una count nella tabella notifica per vedere se ci sono persone associate all'utente attualmente collegato
	 * se ci sono voci significa che l'utente attualmente collegato aveva dei follower che sono stati eliminati dal sistema da
	 * un'amminstratore e verrà notificato,è importante fare il count in modo da allocare gli array dinamicamente              */
				String dinamica="SELECT count(*) FROM notifica WHERE utente_notificato='"+name+"'"; 
				String notifica="SELECT utente_eliminato FROM notifica WHERE utente_notificato='"+name+"'"; 
				ResultSet ok=stmt.executeQuery(dinamica);
				if(ok.next())
				{
			     numero=ok.getInt(1);
				}
				
				arr=new String[numero];
				
				
				int i = 0;
				ResultSet rs=stmt.executeQuery(notifica);
				while(rs.next()) {
					arr[i++]=rs.getString(1);//utenti eliminati da notificare all'utente che ha effettuato il login
					
				}
				}catch(Exception e) {System.out.print(e);}}
	 
	 
	 void createwindow() {
		 
		 /*Se la query ritorna un numero >0 significa che sono stati eliminati degli utenti che il nostro utente
		  * seguiva e il che significa che va notificato    */
		 if(numero>0) {
			 
		
		 
	 nuovo= new JFrame("Notifica");
	 nuovo.setTitle("Notifica");
	 nuovo.setBounds(500, 300, 700, 450);
	 nuovo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 nuovo.getContentPane().setLayout(null);
	 nuovo.setVisible(true);
	 

	 JPanel panel=new JPanel();
		
	 panel.setBounds(0, 105, 682, 298);
	 panel.setLayout(new GridLayout(numero,0,-200,0));
	
	 JLabel [] labes=new JLabel[numero];
	 /*Si andrà a creare in maniera dinamica una serie di etichette per quanti sono gli utenti eliminati che era
	  * seguiti dal nostro utente */
	 for(int i=0;i<numero;i++)
	 {
		
		 labes[i]=new JLabel("L'utente "+arr[i]+" è stato eliminato dal sistema! "); 
		 
		
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
				Contenuto ok =new Contenuto(name);
				ok.createwindow();
				nuovo.dispose();
			}
		 });
		btnContenuti.setBounds(173, 415, 175, 88);
		nuovo.getContentPane().add(btnContenuti);
		
		//Bottone notifica
		JButton btnNewButton_1_1_1_1_1 = new JButton("Notifica");
		Image img_icona4 = new ImageIcon(this.getClass().getResource("notifica.png")).getImage();
		 btnNewButton_1_1_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				Utente amico=new Utente(name);
				amico.createwindow();
				nuovo.dispose();
			}
		});
		btnNewButton_1_1_1_1_1.setIcon(new ImageIcon(img_icona4));
		btnNewButton_1_1_1_1_1.setBounds(518, 415, 185, 88);
		nuovo.getContentPane().add(btnNewButton_1_1_1_1_1);
		//Sfondo bianco
		JLabel label1 = new JLabel("");
		Image img2 = new ImageIcon(this.getClass().getResource("bianco.jpg")).getImage();
		label1.setIcon(new ImageIcon(img2));
		label1.setBounds(0, 0, 700, 460);
		nuovo.getContentPane().add(label1);
		 }
		 
		 /*Se la query restituisce un numero che non è maggiore di 0 significa che non c'è nulla da notificare
		  * dunque poichè il nostro sistema lavora in maniera dimanica significa che il nostro array avrà dimensione nulla 
		  * in quanto gli viene passato il numero di utenti eliminato,quindi per non far si che si generi un errore andremo
		  * a creare un altro layer dove leggeremo solo che non ci sono notifiche */
		 else
		 {
			 JFrame nuovo = new JFrame("Notifica");
			 nuovo.setVisible(true);
			 nuovo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			 nuovo.setBounds(500, 300, 700, 450);
			 
			    nuovo.getContentPane().setLayout(null);
				JLabel lblNewLabel = new JLabel("NON CI SONO NOTIFICHE");
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
	 
	 
	 void ClickBotton() {}
}