package resolution;

import java.util.List;
import java.util.Stack;

import definition.Constraint;
import definition.Csp;
import definition.Domain;
import definition.Variable;

public class SearchV2 {

    //---------------------------------------------------------------------------------------------------
    // bruteForceSearch : on génère toutes les instanciations possibles :
    // aucune contrainte : toute instanciation complète est une solution
    //---------------------------------------------------------------------------------------------------

	public static void bruteForceSearch(Csp csp) {
        if (csp.allInstanciated()) {
            // traitement du cas où une instanciation est complète
        	System.out.println("All good " + csp.toString());
        }
        
        else {
            Variable y = csp.nextVarToInstantiate();
            //System.out.println("Variable : " + y);
            Domain domain = y.getDomain();
            Domain domainclone = y.getDomain().clone();
            //System.out.println("Domain : " + domainclone);
           
            for (int val : domain) {
            	//System.out.println("Val : " + val);
                y.instantiate(val);
                bruteForceSearch(csp);
            }
            y.setDomain(domainclone);
        }
    }
    //---------------------------------------------------------------------------------------------------
    // generateAndTest : on ajoute un test pour vérifier si une instanciation complète est une solution
    // Note : si le csp n'a aucune contrainte, c'est le même comportement que bruteForceSearch
    //---------------------------------------------------------------------------------------------------

	public static void generateAndTest(Csp csp) {
        if (csp.allInstanciated()) {
            if (csp.hasSolution()) {
                // traitement du cas où une solution a été trouvée :
                // instanciation complète ET vérification que c'est une solution
            	System.out.println("All good " + csp.toString());
            }
        }
        else {
            Variable y = csp.nextVarToInstantiate();
            Domain domain = y.getDomain();
            Domain domainclone = y.getDomain().clone();
            
            
            for (int val : domain) {
                y.instantiate(val);
                generateAndTest(csp);
            }
            y.setDomain(domainclone);
        }
    }


    //---------------------------------------------------------------------------------------------------
    // backtrack : on n'attend pas que l'instanciation soit complète pour vérifier qu'elle est correcte
    // (elle peut mener à une solution)
    //---------------------------------------------------------------------------------------------------

    public static void backtrack1(Csp csp) {
        boolean ok = true;
        
        // ok = check()
        // (check : test de l'instanciation courante)
        List<Constraint>  contraintes = csp.getConstraints();
        for (Constraint c : contraintes) {
        	if(c.allInstantiated()) {
        		if (!c.isSatisfied()) {
        		ok=false;
        		}
        	}
        }
        
        if (ok) {
        	System.out.println(csp.toString());
            if (csp.allInstanciated()) {
            	if (csp.hasSolution()) {
            		System.out.println("All good " + csp.toString());
                }
            }
            else {
            	System.out.println(csp.toString());
            	Variable y = csp.nextVarToInstantiate();
                Domain domainclone = y.getDomain().clone();
                
                for (int val : domainclone) {
                    y.instantiate(val);
                    backtrack1(csp);
                }
                y.setDomain(domainclone);
                }
            }
        }
    

    public static void backtrack2(Csp csp) {
        boolean ok = true;

        List<Constraint>  contraintes = csp.getConstraints();
        for (Constraint c : contraintes) {
        		if (!c.isNecessary()) {
        			ok=false;
        		}
        }
        // ok = check()
        // (check : test de l'instanciation courante)

        if (ok) {
        	System.out.println(csp.toString());
            if (csp.allInstanciated()) {
            	if (csp.hasSolution()) {
            		System.out.println("All good " + csp.toString());
                }
            }
            else {
            	Variable y = csp.nextVarToInstantiate();
                Domain domainclone = y.getDomain().clone();
                
                for (int val : domainclone) {
                    y.instantiate(val);
                    backtrack2(csp);
                }
                y.setDomain(domainclone);
                }
            }
        }

    //---------------------------------------------------------------------------------------------------
    // pour le tp3
    //---------------------------------------------------------------------------------------------------

    public static void propagation(Csp csp, Variable var_i){

 	  Stack<Variable> pile = new Stack<Variable>();
 	  pile.push(var_i);

 	   while(pile != null) {
 		   Variable var = pile.pop();
 		   for (Constraint contrainte : var.getConstraint(csp)) {
 			   List<Variable> new_variables = contrainte.filter();
 			   if(new_variables != null) {
 				  for (Variable new_var : new_variables) {
 					 pile.push(new_var);
 					  }
 					  }
 				  }
 			   }
 		   }
 	   
    
    

    public static void backtrack3(Csp csp) {
        boolean ok = true;

        List<Constraint>  contraintes = csp.getConstraints();
        for (Constraint c : contraintes) {
        		if (!c.isNecessary()) {
        			ok=false;
        		}
        }
        // ok = check()
        // (check : test de l'instanciation courante)

        if (ok) {
        	System.out.println(csp.toString());
            if (csp.allInstanciated()) {
            	if (csp.hasSolution()) {
            		System.out.println("All good " + csp.toString());
                }
            }
            else {
            	Variable y = csp.nextVarToInstantiate();
                Domain domainclone = y.getDomain().clone();
                
                for (int val : domainclone) {
                    y.instantiate(val);
                    propagation(csp, y);
                    backtrack2(csp);
                }
                y.setDomain(domainclone);
                }
            }
        }
    }
