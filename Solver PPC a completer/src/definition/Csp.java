package definition;

import java.util.List;

public class Csp {

    List<Variable> vars; // l'ensemble des variables du CSP. Note: les domaines sont connus au travers des variables
    List<Constraint> cons; // l'ensemble des contraintes du CSP

    public Csp(List<Variable> vars, List<Constraint> cons) {
    	this.vars = vars;
    	this.cons = cons;
    }
    
    public List<Variable> getVars() {
        return vars;
    }
    
    public List<Constraint> getConstraints() {
	return this.cons;
    }

    // retourne la premiere variable non instanciee du csp
    public Variable nextVarToInstantiate() {
    	for (Variable x : this.vars) {
    		if(!x.isInstantiated()) {
    			return x;
    		}
    	}return null;
    }
    
    // retourne vrai ssi toutes les variables sont instanciées
    public boolean allInstanciated() {
    	for (Variable x : this.vars) {
    		if(!x.isInstantiated()) {
    			return false;
    		}
    	}
    	return true;
    }

    // retourne vrai ssi le CSP possède (au moins) une solution : 
    // l'ensemble des contraintes du CSP est vérifié
    // ATTENTION : ce n'est pas la seule condition

    public boolean hasSolution() {
    	for (Constraint x : this.cons) {
    			if(!x.isSatisfied()) {
    				return false;
    			}
    		}
    		return true;
    	}
    
    public String toString() {
    	return ""+this.getVars();
    	}
}
