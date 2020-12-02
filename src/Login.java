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

public class Login extends WindowManager{
     
	private static Login instance;
	private JFrame nuovo;
    private  JTextField user;
	private JTextField pass;
	
	
private Login() {}
	
	public static Login getInstance()
	{
		if (instance==null)
		{
			instance=new Login();
		}
		return instance;
	}

	
	void createwindow() {
		 nuovo= new JFrame("login");
		 nuovo.setTitle("LOGIN");
		 nuovo.setBounds(500, 300, 700, 500);
		 nuovo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 nuovo.getContentPane().setLayout(null);
		 nuovo.setVisible(true);
		 
			// Label sopra
			JLabel lblAutenticazione = new JLabel("Autenticazione");
			lblAutenticazione.setFont(new Font("Tahoma", Font.PLAIN, 17));
			lblAutenticazione.setHorizontalAlignment(SwingConstants.CENTER);
			lblAutenticazione.setBounds(12, 53, 658, 25);
			nuovo.getContentPane().add(lblAutenticazione);
		 
		    //Label Utente
		    JLabel lblNomeUtente = new JLabel("Utente");
			lblNomeUtente.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblNomeUtente.setBounds(136, 119, 85, 25);
			nuovo.getContentPane().add(lblNomeUtente);
			
			//Texfield inserito il nome utente
			user = new JTextField();
			user.setBounds(233, 122, 160, 22);
			nuovo.getContentPane().add(user);
			user.setColumns(10);
			
			//Label password
			JLabel lblPassword = new JLabel("Password");
			lblPassword.setBounds(136, 172, 85, 25);
			nuovo.getContentPane().add(lblPassword);
			
			//Texfield inserito la password
			pass = new JTextField();
			pass.setBounds(233, 175, 160, 22);
			nuovo.getContentPane().add(pass);
		    pass.setColumns(10);
			
		    //Bottone login
			JButton accesso = new JButton("Login");
			accesso.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) 
				{
					ClickBotton();
				}
			});
			accesso.setBounds(233, 283, 135, 37);
			nuovo.getContentPane().add(accesso);
			
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
			Statement stmt=conn.createStatement();
			/* Verifico se il nome e password immessi esistono nella tabella dei registrati,
			 * se esiste apro la classe utente o amministratore  */
			String sql="Select *from registrati where User='"+user.getText()+"'and Password='"+pass.getText().toString()+"'";
			ResultSet rs=stmt.executeQuery(sql);
			String query;
			if(rs.next())
			{
			/* Anche l'amministratore è registrato nella tabella registrati,quindi quando un utente inserisce i suoi dati
			 * si verifica prima se nella tabella registrati esiste quello utente,se esiste
			 * si va a fare un altro controllo se quell'utente esiste nella tabella amministratore
			 * se è vero effettua l'accesso come amministratore sennò come utente   */
			query="Select *from ammistratore where User='"+user.getText()+"'and Password='"+pass.getText().toString()+"'";
			ResultSet amm=stmt.executeQuery(query);
			if(amm.next()) {
				JOptionPane.showMessageDialog(null, "benvenuto admin "+user.getText());
				Amministratore admin=new Amministratore();
				admin.createwindow();
				
				nuovo.dispose();
			}
			else {
				System.out.println("Benvenuto utente "+user.getText());
			Utente us=new Utente(user.getText());
			us.createwindow();
			
			
			nuovo.dispose(); }
			}
				
			else 
				JOptionPane.showMessageDialog(null, "login fallito");
			conn.close();
		}catch(Exception e) {System.out.print(e);}
		
}
}