
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;


public class Home extends WindowManager{
   
	private JFrame nuovo;

	/**
	 * Create the frame.
	 */
	public Home() {}
	
	 void createwindow() {
	 nuovo= new JFrame("home");
	 nuovo.setTitle("HOME");
	 nuovo.setBounds(500, 300, 700, 500);
	 nuovo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 nuovo.getContentPane().setLayout(null);
	 nuovo.setVisible(true);
	   
		 //Icona Twitter
	    JLabel lblBenvenuto = new JLabel("");
		Image img2 = new ImageIcon(this.getClass().getResource("twitterx64.png")).getImage();
		lblBenvenuto.setIcon(new ImageIcon(img2));
		lblBenvenuto.setBounds(300, 23, 64, 64);
		nuovo.getContentPane().add(lblBenvenuto);
		
		 //Testo accedi o registrati
		JLabel lblWelcome = new JLabel("Accedi o registrati");
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblWelcome.setBounds(12, 109, 658, 33);
		nuovo.getContentPane().add(lblWelcome);
		
		//Bottone registrati
		JButton btnRegistrati = new JButton("Registrati");
		//Image img = new ImageIcon(this.getClass().getResource("ad.png")).getImage();
		//btnRegistrati.setIcon(new ImageIcon(img));
		btnRegistrati.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				ClickBotton();
			}
		});
	
        btnRegistrati.setBounds(422, 269, 134, 35);
		nuovo.getContentPane().add(btnRegistrati);
		
		  
	     //Sfondo bianco
			JLabel label = new JLabel("");
			Image img1 = new ImageIcon(this.getClass().getResource("bianco.jpg")).getImage();
			label.setIcon(new ImageIcon(img1));
			label.setBounds(0, 0, 700, 460);
			nuovo.getContentPane().add(label);
			
		//Bottone Accedi
		JButton btnAccedi = new JButton("Accedi");
		btnAccedi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				Login log = Login.getInstance();
				log.createwindow();
				nuovo.dispose();
			}
		 });
		btnAccedi.setBounds(87, 269, 134, 35);
		nuovo.getContentPane().add(btnAccedi);
	
	}
	 @Override
		public void ClickBotton()
		{
		 WindowManager nuovo_utente=new Registrati();
		 nuovo_utente.createwindow();
	     nuovo.dispose(); //chiudere la finestra corrente
		}
}
