package resolution;


import definition.Csp;
import definition.Domain;
import definition.Variable;

public class SearchV1 {
    
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
            
            Domain domainclone = y.getDomain().clone();
            Domain domainclone2 = y.getDomain().clone();
            //System.out.println("Domain : " + domainclone);
           
            for (int val : domainclone) {
            	//System.out.println("Val : " + val);
                y.instantiate(val);
                bruteForceSearch(csp);
            }
            y.setDomain(domainclone2);
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
            Domain domainclone = y.getDomain().clone();
            Domain domainclone2 = y.getDomain().clone();
           
            
            for (int val : domainclone) {
                y.instantiate(val);
                generateAndTest(csp);
            }
            y.setDomain(domainclone2);
        }
    }

}
