import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Pubblica extends WindowManager {

	 private JFrame nuovo;
	 private JTextField messaggio;
	 private JTextField textField_2;
	 private String name;
	 
		public Pubblica (String a)
		 {name=a;}
	 
	 
	 void createwindow() {
	 nuovo= new JFrame("utente");
	 nuovo.setTitle("UTENTE");
	 nuovo.setBounds(500, 300, 700, 450);
	 nuovo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 nuovo.getContentPane().setLayout(null);
	 nuovo.setVisible(true);
	 
	
	 //Follower
		JButton btnNewButton = new JButton("Follower");
		Image img_icona1 = new ImageIcon(this.getClass().getResource("follower.png")).getImage();
		btnNewButton.setIcon(new ImageIcon(img_icona1));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				Follower back=new Follower(name);
				back.createwindow();
				nuovo.dispose();
			}
		 });
		
		btnNewButton.setBounds(0, 315, 175, 88);
		nuovo.getContentPane().add(btnNewButton);
		
		
		//Bacheca
		JButton btnNewButton_1 = new JButton("Bacheca");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				Contenuto back=new Contenuto(name);
				back.createwindow();
				nuovo.dispose();
			}
		 });
		Image img_icona3 = new ImageIcon(this.getClass().getResource("home_utente.png")).getImage();
		btnNewButton_1.setIcon(new ImageIcon(img_icona3));
		btnNewButton_1.setBounds(173, 315, 175, 88);
		nuovo.getContentPane().add(btnNewButton_1);
		
		
		//Nel layout messaggio clicco su messaggio
		JButton btnNewButton_1_1 = new JButton("Messaggio");
		Image img_icona2 = new ImageIcon(this.getClass().getResource("messaggio.png")).getImage();
		btnNewButton_1_1.setIcon(new ImageIcon(img_icona2));
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				Utente back=new Utente(name);
				back.createwindow();
				nuovo.dispose();
			}
		 });
		btnNewButton_1_1.setBounds(345, 315, 175, 88);
		nuovo.getContentPane().add(btnNewButton_1_1);
	 
	    //
		JLabel lblNewLabel = new JLabel("Scrivi");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setBounds(51, 33, 531, 39);
		nuovo.getContentPane().add(lblNewLabel);
		
		messaggio = new JTextField();
		messaggio.setBounds(43, 71, 378, 117);
		nuovo.getContentPane().add(messaggio);
		messaggio.setColumns(10);
		

		JButton btnInvia = new JButton("Pubblica");
		btnInvia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");		
					PreparedStatement ps=conn.prepareStatement("insert into messaggi(Messaggio,utente_seguito_fk,hashtag)values(?,?,?);");
					/*Andiamo ad inserire nella tabella messaggi,il messaggio,l'utente che l'ha scritto che è di
					 * default praticamente in quanto passato dal costruttore di login e eventualmente l'hashtag             */
					ps.setString(1, messaggio.getText());
					ps.setString(2, name);
					ps.setString(3, textField_2.getText());
					String lunghezza_messaggio = messaggio.getText().toString();
					
					if(lunghezza_messaggio.length()==0)
					{
						{System.out.println("Errore,il messaggio è vuoto!!");}}
						else {
							ps.executeUpdate();
							System.out.println("Il messaggio è stato aggiunto correttamente");
						}

				}catch(Exception e1) {
					System.err.println("Got an exception! ");
				      System.err.println(e1.getMessage());
				}
		
			}});
		btnInvia.setBounds(458, 116, 97, 25);
		nuovo.getContentPane().add(btnInvia);
		
		
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
		/// Hashtag
		JLabel lblInserisciHashtag = new JLabel("inserisci hashtag");
		lblInserisciHashtag.setHorizontalAlignment(SwingConstants.CENTER);
		lblInserisciHashtag.setBounds(43, 212, 378, 32);
		nuovo.getContentPane().add(lblInserisciHashtag);
		
		textField_2 = new JTextField();
		textField_2.setBounds(51, 247, 370, 22);
		nuovo.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
	
		
		//Sfondo bianco
				JLabel label1 = new JLabel("");
				Image img2 = new ImageIcon(this.getClass().getResource("bianco.jpg")).getImage();
				label1.setIcon(new ImageIcon(img2));
				label1.setBounds(0, 0, 700, 460);
				nuovo.getContentPane().add(label1);
				
		}
	 
	 
	 
	 
	 void ClickBotton() {}
}
