import java.awt.Font;
import java.awt.Image;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
public class Registrati extends WindowManager{
  
	
	private JFrame nuovo;   
	private JTextField user;
	private JTextField email;
	private JTextField pass;
	public Registrati() {}
 
 void createwindow()
 { 
	 //Frame
	 nuovo= new JFrame("Registrazione");
	 nuovo.setTitle("Registrazione");
	 nuovo.setBounds(500, 300, 700, 500);
	 nuovo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 nuovo.getContentPane().setLayout(null);
	 nuovo.setVisible(true);
	  
	 
	 
	 
	     //Label superiore
	    JLabel lblCreaIlTuo = new JLabel("Crea il tuo Account");
		lblCreaIlTuo.setHorizontalAlignment(SwingConstants.CENTER);
		lblCreaIlTuo.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblCreaIlTuo.setBounds(12, 63, 358, 16);
		nuovo.getContentPane().add(lblCreaIlTuo);
		
		//Label utente
		JLabel lblNomeUtente = new JLabel("Utente");
		lblNomeUtente.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNomeUtente.setBounds(136, 119, 85, 25);
		nuovo.getContentPane().add(lblNomeUtente);
		
		//Texfield utente
		user = new JTextField();
		user.setBounds(233, 122, 160, 22);
		nuovo.getContentPane().add(user);
		user.setColumns(10);
		//Label email
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(136, 172, 85, 25);
		nuovo.getContentPane().add(lblEmail);
		//Texfield email
		email = new JTextField();
		email.setBounds(233, 175, 160, 22);
		nuovo.getContentPane().add(email);
		email.setColumns(10);
		//Label password
		JLabel lblPassoword = new JLabel("Password");
		lblPassoword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPassoword.setBounds(136, 217, 85, 25);
		nuovo.getContentPane().add(lblPassoword);
		//Textfield password
		pass = new JTextField();
		pass.setBounds(233, 220, 160, 22);
		nuovo.getContentPane().add(pass);
		pass.setColumns(10);
		
		//Botton registrazione
		JButton btnRegistrati = new JButton("Registrati");
		btnRegistrati.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				ClickBotton();
			}
		});
		btnRegistrati.setBounds(222, 304, 97, 25);
		nuovo.getContentPane().add(btnRegistrati);
		
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
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
			PreparedStatement ps=conn.prepareStatement("insert into registrati(User,Email,Password)values(?,?,?);");
			ps.setString(1, user.getText());
			ps.setString(2, email.getText());
			ps.setString(3, pass.getText());
			
			//Andiamo a controllare se i campi sono vuoti o meno
			String lunghezza_user = user.getText().toString();
			String lunghezza_email=email.getText().toString();
			String lunghezza_password=pass.getText().toString();
			if (lunghezza_user.length()==0 || lunghezza_email.length()==0 || lunghezza_password.length()==0) 
			{JOptionPane.showMessageDialog(null,"Errore!Uno dei campi è vuoto..");}
						
			else {
				ps.executeUpdate();
				JOptionPane.showMessageDialog(null,"Registrazione completata");
				nuovo.dispose();
				Login log = Login.getInstance();
				log.createwindow();
				
			}
		}catch(Exception e1) {
			System.out.println(e1);
		}
	}
}
