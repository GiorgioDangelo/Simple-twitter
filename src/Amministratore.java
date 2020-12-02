import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Amministratore extends WindowManager
{
	private JFrame nuovo;
	private String arr[];
	private JTextField nome_utente;
	
	/**
	 * Create the frame.
	 */
	public Amministratore() {}
	
	 void createwindow() {
		 //Frame
	 nuovo= new JFrame("admin");
	 nuovo.setTitle("Ammistratore");
	 nuovo.setBounds(500, 300, 700, 500);
	 nuovo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 nuovo.getContentPane().setLayout(null);
	 nuovo.setVisible(true);
	  //Icona amministratore
	    JLabel label = new JLabel("");
		Image img1 = new ImageIcon(this.getClass().getResource("ad.png")).getImage();
		label.setIcon(new ImageIcon(img1));
		label.setBounds(280, 20, 72, 72);
		nuovo.getContentPane().add(label);
		
		
		//Label1
		JLabel lblNewLabel = new JLabel("Inserisci il nome utente da eliminare");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 119, 670, 32);
		nuovo.getContentPane().add(lblNewLabel);
		//Inserire il nome da eliminare
		nome_utente = new JTextField();
		nome_utente.setBounds(136, 172, 370, 22);
		nuovo.getContentPane().add(nome_utente);
		nome_utente.setColumns(10);
		
		//Bottone per elimare l'utente
		JButton btnConferma = new JButton("Elimina");
		btnConferma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				ClickBotton();
			}
		});
		
		btnConferma.setBounds(262, 216, 100, 30);
		nuovo.getContentPane().add(btnConferma);
		
		
		
		//Bottone cerca
		JButton btnRicerca = new JButton("Ricerca");
		Image img_icona2 = new ImageIcon(this.getClass().getResource("hashtag.png")).getImage();
		btnRicerca.setIcon(new ImageIcon(img_icona2));
		btnRicerca.addActionListener(new ActionListener() {
			//Apre la finestra per gli hashtag
			public void actionPerformed(ActionEvent e) 
			{
				Hashtag hash=new Hashtag();
				hash.createwindow();
				nuovo.dispose();
			}
		 });
		btnRicerca.setBounds(0, 380, 682, 64);
		btnRicerca.setOpaque(false);
		btnRicerca.setBorderPainted(false);
		btnRicerca.setContentAreaFilled(false);
		btnRicerca.setBorder(null);
		nuovo.getContentPane().add(btnRicerca);
		
		//Sfondo bianco
		JLabel label1 = new JLabel("");
		Image img2 = new ImageIcon(this.getClass().getResource("bianco.jpg")).getImage();
		label1.setIcon(new ImageIcon(img2));
		label1.setBounds(0, 0, 700, 460);
		nuovo.getContentPane().add(label1);
		
		//Bottone per tornare all'home
		JButton come_back = new JButton("");
		Image img_icona = new ImageIcon(this.getClass().getResource("icona_home.png")).getImage();
		come_back.setIcon(new ImageIcon(img_icona));
		come_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				Home back=new Home();
				back.createwindow();
				nuovo.dispose();
			}
		 });
		come_back.setBounds(573, 13, 64, 64);
		nuovo.getContentPane().add(come_back);
}
	 

	void ClickBotton() {
		try {
			
			//Andiamo ad eseguire il collegamento con il database
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
			
			//Questa query andrà ad eliminare l'utente dalla tabella registrati che inseriamo nel textfield nome_utente
		    String sql="delete from registrati where User='"+nome_utente.getText().toString()+"'";
		    PreparedStatement preparedStmt=conn.prepareStatement(sql);
		    Statement stmt=conn.createStatement();
			int rs=preparedStmt.executeUpdate();
			 /*se la query è andata a buon fine restituisce 1 in che significa che è stato trovato l'utente da eliminare */
			if(rs==1) {
				
				JOptionPane.showMessageDialog(null, "Utente "+nome_utente.getText()+" eliminato");
				
				
				//Eliminato l'utente andremo ad notificare nel nostro database tutti gli utente che seguivano l'utente eliminato.
				//Si va a effettuare una query dove selezioniamo user_fk1 ossia gli utenti che seguivano l'utente eliminato
				String notifica="SELECT user_fk1 from seguiti where user_seguito='"+nome_utente.getText()+"'";
				//E vado anche a contare quanti sono gli utenti se estistono
				String result_number="SELECT count(*) from seguiti where user_seguito='"+nome_utente.getText()+"'";
				
				int i=0;
				int numero=0;
				ResultSet risultato=stmt.executeQuery(result_number);
				
				if(risultato.next())
				{
			     numero=risultato.getInt(1);
			
				//Se esistono utenti che seguivano tale persona allora andiamo avanti
				if(numero>0)
				{
					arr=new String[numero];	
					ResultSet utente_da_notificare=stmt.executeQuery(notifica);
					//andremo a salvare in una stringa tutti gli utenti che seguivano la persona eliminata
				while(utente_da_notificare.next()) {
				
					arr[i++]=utente_da_notificare.getString(1);//utente da notificare	
					}
				//Qui andiamo ad inserire nella tabella notifica gli utenti che seguivano la persona eliminata,dunque per
				//Ogni utente inseriamo il suo nome e la persona eliminata,in modo che nella sottoclasse Notifica andremo a fare
				//Una select di questa tabella
				PreparedStatement ps=conn.prepareStatement("insert into notifica(utente_notificato,utente_eliminato)values(?,?);");
				for (int j=0;j<arr.length;j++)
				{
				
				ps.setString(1,arr[j]);
				ps.setString(2,nome_utente.getText());
				ps.executeUpdate();

				}
				//Eliminato l'utente dal sistema ovviamente si devono eliminare anche il follower dagli utenti
				String elimina="delete from seguiti where user_seguito='"+nome_utente.getText().toString()+"'";
				 PreparedStatement elimana_tt=conn.prepareStatement(elimina);
				 elimana_tt.executeUpdate();
				 
			
			}}}
		//Se non viene trovato l'utente apparirà questo messaggio.
			else	
			{
				JOptionPane.showMessageDialog(null, "Utente non trovato");
			}
				
		
		}catch(Exception e1) {
			System.out.println(e1);
		}

	
	}
}