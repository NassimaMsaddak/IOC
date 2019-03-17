package presentation;
 
import java.io.File;
import java.lang.reflect.Method;   // c'est la programmation par reflection ( qui peut etre faite par le framework Spring )
import java.util.Scanner;

import dao.DaoImpl;
import dao.IDao;
import metier.IMetier;
import metier.MetierImpl;

public class PresentationTest {
 

	public static void main(String[] args) { 
		
		
	// cette couche presentation n'est  pas fermer à la modification avec le premier code
	// 1 er code: ce code doit etre modifier lors d'une extension (nouvelle version "maintenance")
		
   /*  DaoImpl dao = new  DaoImpl();
    
	MetierImpl metier = new MetierImpl();
	
	metier.setDao(dao);
    
	double resultat = metier.calculer();
	
	System.out.println( resultat );    */
		
		
		
		
	
	// code fermer à la modification
	// 2 eme code : " Programmation par reflection "
	 
	try {
		
		Scanner scanner = new Scanner (new File("config.txt"));
		
		String daoClassName = scanner.next();
		String metierClassName = scanner.next();
		
		System.out.println( daoClassName );
		System.out.println(  metierClassName );
		
		
		
		// creation de l'objet dao 
		// équivalent à :     DaoImpl dao = new  DaoImpl();     (dans le premier code)
		Class cDao = Class.forName(daoClassName) ;
		IDao dao = (IDao)  cDao.newInstance();
		// System.out.println(  dao.getValue() );
		
		
		// creation de l'objet metier 
		// équivalent à :     MetierImpl metier = new MetierImpl();   (dans le premier code)
        Class cMetier = Class.forName(metierClassName) ;
		IMetier metier = (IMetier) cMetier.newInstance();
		
		// association entre les objets 
		// équivalent à :     metier.setDao(dao);     (dans le premier code)
	    
		Method m1 =  cMetier.getMethod("setDao", new Class[] {IDao.class});
		m1.invoke(metier, new Object[]{dao});
		System.out.println( metier.calculer() );
		
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	
	
	}

}
