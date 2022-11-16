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

	@Override
	public List<Variable> filter() {
		if(this.var1.getDomainSize()==0 || this.var2.getDomainSize()==0) {
			System.out.println("pas de solution");
			return null;
		}
		else {
		List<Variable> liste_var = new ArrayList<Variable>();
		
		// sinon : on ne peut agir que si la variable a été instanciée
		if (this.var1.isInstantiated() && this.var2.getDomain().contains(this.var1.getValue())) {
			this.var2.remValue(this.var1.getValue());
			System.out.println("la variable "+ this.var1.getValue() +" a été retirée");
			
			// retourne la variable qui a été modifiée
			liste_var.add(var2);
		}
		
		if(this.var2.isInstantiated() && this.var1.getDomain().contains(this.var2.getValue())) {
			this.var1.remValue(this.var2.getValue());
			System.out.println("la variable "+ this.var2.getValue() +" a été retirée");
			
			// retourne la variable qui a été modifiée
			liste_var.add(var1);
		}
		return liste_var;
		}
	}
}