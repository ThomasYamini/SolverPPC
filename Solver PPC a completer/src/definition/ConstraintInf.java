package definition;

import java.util.ArrayList;
import java.util.List;

public class ConstraintInf implements Constraint {
	private Variable var1;
	private Variable var2;
	
	public ConstraintInf(Variable v1, Variable v2) {
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
		return (this.allInstantiated() && var1.getValue()<var2.getValue());
	}

	public boolean isNecessary() {
		return (var1.getValue()<var2.getValue() && var1.getInf()<var2.getSup());
	}

	public boolean allInstantiated() {
		for (Variable e : this.getVars()) {
			if(!e.isInstantiated()) {
				return false;
			}
		}
		return true;
	}

	@Override
	public List<Variable> filter() {
		if(this.var1.getDomainSize()==0 || this.var2.getDomainSize()==0) {
			System.out.println("pas de solution");
			return null;
		}
		else {
		
		List<Variable> liste_var = new ArrayList<Variable>();
		
		for (int j : this.var1.getDomain()) {
			if (j>=this.var2.getSup()) {
				this.var1.remValue(j);
				System.out.println("la variable "+ j +" a été retirée");
				
				// retourne la variable qui a été modifiée
				liste_var.add(var1);
			}
		}
		for (int k : this.var2.getDomain()) {
			if (k<=this.var2.getInf()) {
				this.var2.remValue(k);
				System.out.println("la variable "+ k +" a été retirée");
				
				// retourne la variable qui a été modifiée
				liste_var.add(var2);
			}
		}
		return liste_var;
		}
		}
}
