import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
public class Utente extends WindowManager{
	
	private JFrame nuovo;
	private String name;
	
	
	public Utente (String a)
	 {name=a;
	
	 }

	void createwindow()
	{
		//Frame
		 nuovo= new JFrame("utente");
		 nuovo.setTitle("UTENTE");
		 nuovo.setBounds(500, 300, 700, 450);
		 nuovo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 nuovo.getContentPane().setLayout(null);
		 nuovo.setVisible(true);
		 
		    JLabel lblNewLabel = new JLabel("Ciao "+name+",come ti senti oggi?");
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 19));
			lblNewLabel.setBounds(29, 80, 575, 37);
			nuovo.getContentPane().add(lblNewLabel);
			
			
			
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
			btnContenuti.setBounds(173, 315, 175, 88);
			nuovo.getContentPane().add(btnContenuti);
		
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
			
			//Bottone notifca
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
			JLabel label = new JLabel("");
			Image img1 = new ImageIcon(this.getClass().getResource("bianco.jpg")).getImage();
			label.setIcon(new ImageIcon(img1));
			label.setBounds(0, 0, 700, 460);
			nuovo.getContentPane().add(label);
			//Bottone per tornare all'home
			JButton come_back = new JButton("");
			Image img_icona = new ImageIcon(this.getClass().getResource("icona_home.png")).getImage();
			come_back.setIcon(new ImageIcon(img_icona));
			come_back.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) 
				{
					ClickBotton();
				}
			 });
			come_back.setBounds(573, 13, 64, 64);
			nuovo.getContentPane().add(come_back);
		
			
			
			
			}
	
			

	
	void ClickBotton()
	{	Home back=new Home();
	back.createwindow();
	nuovo.dispose();}

	
			}
