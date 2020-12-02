
import java.awt.EventQueue;

public class Main {

/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					unico_accesso solo_entry_point= new unico_accesso();
					solo_entry_point.inizia_home();  
					 
					
				} catch (Exception e) {
					System.out.println("errore nella creazione");
				}
			}
		});
	}

	

}
