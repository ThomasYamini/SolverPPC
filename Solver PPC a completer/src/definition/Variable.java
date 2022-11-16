package definition;

import java.util.ArrayList;
import java.util.List;

public class Variable {
    private Domain dom; // le domaine associe

   public Variable(Domain dom) {
	   super();
	   this.dom = dom;
   }
   
   public Variable(int v) {
	   super();
	   if(v==0) {
		   Domain dom = new DomainBitSet(v,0);
		   this.dom = dom;
	   }else {
		   Domain dom = new DomainBitSet(0,v);
		   this.dom=dom;
	   }   
   }
   
   public String toString() {
	   return this.dom.toString();
	   }
    
    public Domain getDomain() {
        return this.dom;
    }
    
    // retourne vrai ssi la variable est instanciee
    public boolean isInstantiated() {
        return (this.dom.size()==1);
    }

    // retourne vrai ssi le domaine de la variable contient la valeur v
    public boolean canBeInstantiatedTo(int v) {
    	return this.dom.contains(v);
    }

    // retourne le nombre de valeurs dans le domaine de la variable
    public int getDomainSize() {
        return this.dom.size();
    }

    // supprime la valeur v du domaine de la variable
    public void remValue(int v) {
    	this.dom.remove(v);
    }

    // supprime toutes les valeurs entre min (inclus) et max (inclus)
    public void remValues(int min, int max) {
    	this.dom.remove(min, max);
    }

    // vide le domaine : supprime toutes ses valeurs
    public void remAllValues() {
    	this.dom.removeAll();
    }

    // instantie la variable a la valeur v
    public void instantiate(int v) {
    	this.dom.instantiate(v);
    }

    // retourne la plus petite valeur du domaine
    public int getInf() {
        return this.dom.firstValue();
    }

    // retourne la plus grande valeur du domaine
    public int getSup() {
        return this.dom.lastValue();
    }

    // retourne la valeur affectee a la variable ssi la variable est effectivement instanciee, sinon -1
    public int getValue() {
        if(this.isInstantiated()) {
        	return this.dom.firstValue();
        	}
        else {
        	return -1;
        	}
    }

    public boolean isEmpty() {
        return (this.getDomainSize()==0);
    }
    
    
    public void setDomain(Domain dom) {
    	this.dom = dom;
    }
    
    // fonction retournant une liste de contrainte reliées à la variable en question
    public List<Constraint> getConstraint(Csp csp){
    	
    	//initialisation de la liste à retourner
    	List<Constraint>  liste_contraintes = new ArrayList<Constraint>();
    	
    	for (Constraint contrainte : csp.getConstraints()) {
   		   for (Variable var : contrainte.getVars()) {
   			   
   			   if(this == var && ! liste_contraintes.contains(contrainte)) {
   				   liste_contraintes.add(contrainte);
   			   }
   		   }
   	   }
    	 return liste_contraintes;
    }
}