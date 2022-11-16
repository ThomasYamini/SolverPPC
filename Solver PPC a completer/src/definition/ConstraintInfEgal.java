package definition;

import java.util.ArrayList;
import java.util.List;

public class ConstraintInfEgal implements Constraint {
	private Variable var1;
	private Variable var2;
	
	public ConstraintInfEgal(Variable v1, Variable v2) {
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

	@Override
	public boolean isSatisfied() {
		return (this.allInstantiated() && var1.getValue()<=var2.getValue());
	}

	@Override
	public boolean isNecessary() {
		return (var1.getValue()<=var2.getValue() && var1.getInf()<=var2.getSup());
	}

	@Override
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
		
		for (int j : this.var1.getDomain()) {
			
			//vérification d'une première contrainte sur la borne sup du domaine de la variable2
			if (j>this.var2.getSup()) {
				
				//suppression de la valeur dans le domaine de la variable1
				this.var1.remValue(j);
				System.out.println("la variable "+ j +" a été retirée de "+ "var1");
				
				// vérification de la non présence dans la variable dans la liste retournée pour ne pas effectuer la propagation trop de fois
				if(! liste_var.contains(var1)) {
					liste_var.add(var1);
				}
		}
		}
		for (int k : this.var2.getDomain()) {
			
			//vérification d'une première contrainte sur la borne inf du domaine de la variable1
			if (k<this.var1.getInf()) {
				
				//suppression de la valeur dans le domaine de la variable2
				this.var2.remValue(k);
				System.out.println("la variable "+ k +" a été retirée de "+ "var2");
				
				// vérification de la non présence dans la variable dans la liste retournée pour ne pas effectuer la propagation trop de fois
				if(! liste_var.contains(var2)) {
					liste_var.add(var2);
				}
			}
			}
		
		return liste_var;
		}
	}
