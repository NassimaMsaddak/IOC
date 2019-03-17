 
package metier;

import dao.IDao;

public class MetierImpl implements IMetier {
	
    private IDao dao = null ;  //l'initialisation est obligatoire (NB: l'attribue est une interface )
    
    // On aura besoin uniquement de "set" de l'interface
	public void setDao(IDao dao) {
		this.dao = dao;
	}


	public double calculer() {
		
		return  ( dao.getValue() ) * 6;
	}

}
