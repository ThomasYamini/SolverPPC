package definition;

import java.util.ArrayList;
import java.util.List;

public class ConstraintDiff implements Constraint {
	private Variable var1;
	private Variable var2;
	
	public ConstraintDiff(Variable v1, Variable v2) {
		super();
		this.var1=v1;
		this.var2=v2;
	}
	
	public List<Variable> getVars() {
		List<Variable> vars = new ArrayList<Variable>();
		vars.add(var1);
		vars.add(var2);
		return vars;
	}

	public boolean isSatisfied() {
		return (this.allInstantiated() && var1.getValue()!=var2.getValue());
	}

	public boolean isNecessary() {
		if (var1.getValue()==var2.getValue() && this.allInstantiated()) {
			return false;
		}else {
			return true;
		}
	}

	public boolean allInstantiated() {
		for (Variable e : this.getVars()) {
			if(!e.isInstantiated()) {
				return false;
			}
		}
		return true;
	}

	// retourne une liste de variables comprenant celles modifiées
	public List<Variable> filter() {
		
		//initialisation de la liste à retourner
		List<Variable> liste_var = new ArrayList<Variable>();
		
		// on ne peut agir que si la variable a été instanciée
		//si la variable est instanciée est qu'elle prend une valeur dans le domaine de l'autre variable, il faut retirer cette valeur du domaine de cette dernière
		if (this.var1.isInstantiated() && this.var2.getDomain().contains(this.var1.getValue())) {
			this.var2.remValue(this.var1.getValue());
			System.out.println("la variable "+ this.var1.getValue() +" a été retirée");
			
			// vérification de la non présence dans la variable dans la liste retournée pour ne pas effectuer la propagation trop de fois
			if(! liste_var.contains(var2)) {
				liste_var.add(var2);
			}
		}
		
		//si la variable est instanciée est qu'elle prend une valeur dans le domaine de l'autre variable, il faut retirer cette valeur du domaine de cette dernière
		if(this.var2.isInstantiated() && this.var1.getDomain().contains(this.var2.getValue())) {
			this.var1.remValue(this.var2.getValue());
			System.out.println("la variable "+ this.var2.getValue() +" a été retirée");
			
			// vérification de la non présence dans la variable dans la liste retournée pour ne pas effectuer la propagation trop de fois
			if(! liste_var.contains(var1)) {
				liste_var.add(var1);
		}
		
		}
		return liste_var;
		}
	}