package definition;

import java.util.ArrayList;
import java.util.List;

//import resolution.SearchV1;
import resolution.SearchV2;				//a décommenter pour utiliser les fonctions backtrack

public class Main {
	public static void main(String[]args) {
		
		//initialisation des domaines
		Domain x1 = new DomainBitSet(2,6);
		Domain x2 = new DomainBitSet(4,7);
		Domain x3 = new DomainBitSet(6,9);
		
		//initialisation des variables
		Variable a = new Variable(x1);
		Variable b = new Variable(x2);
		Variable c = new Variable(x3);
		
		//ajout des variables dans une liste pour pouvoir initialiser le csp plus tard
		ArrayList<Variable> variables = new ArrayList<Variable>();
		variables.add(a);
		variables.add(b);
		variables.add(c);
		
		//initialisation des contraintes et ajout dans une liste pour pouvoir initialiser le csp plus tard
		List<Constraint> cons = new ArrayList<Constraint>();
		ConstraintInfEgal cinfegal1 = new ConstraintInfEgal(b,a);
		ConstraintInfEgal cinfegal2 = new ConstraintInfEgal(c,b);
		cons.add(cinfegal1);
		cons.add(cinfegal2);
		
		
		//initialisation du csp
		Csp test = new Csp(variables, cons);

		//initialisation de la durée pour la résolution 
		long tempsDebut = System.nanoTime();
		
		//différentes fonctions utilisées

		//SearchV1.bruteForceSearch(test);
		//SearchV1.generateAndTest(test);
		//SearchV2.backtrack1(test);
		//SearchV2.backtrack2(test);
		SearchV2.backtrack3(test);
		
		
		long tempsFin = System.nanoTime();
		
		//détermination de la durée de résolution
		double seconds = (tempsFin - tempsDebut) / 1e9;
		
		System.out.println(seconds);
	}
		
		/*étude statistique (ici bruteforce mais la fonction devra être changée pour étudier les autres 
		 * façons de résoudre le problème)
		 */
	
		/*int i =0;
		while (i<100) {
			Random obj = new Random();
		    int nbr1 = obj.nextInt(10);
	        int nbr2 = obj.nextInt(10);
	        List<Integer> TimeList = new ArrayList<Integer>();
	        
	        if(nbr1 >nbr2) {
	        	List<String> list = new ArrayList<String>();
	        	for (int j=0; j<nbr2; j++) {
	        		int nbr3 = obj.nextInt(2);
	        		if(nbr3==0) {
	        			list.add("ConstraintInfEgal");
	        		}
	        		if(nbr3==1) {
	        			list.add("ConstraintDiff");
	        		}
	        		if(nbr3==2) {
	        			list.add("ConstraintInf");
	        		}
	        	}
	        	Csp nouvcsp = CreateCsp(nbr1, nbr2, list);
	        	long tempsDebut = System.nanoTime();
	        	SearchV1.bruteForceSearch(nouvcsp);
	        	long tempsFin = System.nanoTime();
	    		double seconds = (tempsFin - tempsDebut);
	    		TimeList.add((int)seconds);
	        	}
			i++;
		}
	}
	
	public static Csp CreateCsp(int nbvar, int nbconstr, List<String> type) {
		ArrayList<Variable> variables = new ArrayList<Variable>();
		for (int i=0; i<nbvar; i++) {
			Random obj = new Random();
		    int nbr1 = obj.nextInt(10);
	        int nbr2 = obj.nextInt(10);
			Domain x1 = new DomainBitSet(nbr1,nbr2);
			variables.add(new Variable(x1));
	         }
	
		//le nombre de variables est > au nombre de contraintes.
		List<Constraint> cons = new ArrayList<Constraint>();
		for (int i=0; i<nbconstr; i++) {
			String typeconstr = type.get(i);
			Random obj = new Random();
		    int nbr1 = obj.nextInt(10);
	        int nbr2 = obj.nextInt(10);
			Domain x1 = new DomainBitSet(nbr1,nbr2);
			variables.add(new Variable(x1));
			if (typeconstr.equals("ConstraintInfEgal")) {
				cons.add(new ConstraintInfEgal(variables.get(i),variables.get(i+1)));
			}
			if (typeconstr.equals("ConstraintDiff")) {
				cons.add(new ConstraintDiff(variables.get(i),variables.get(i+1)));
			}
			if (typeconstr.equals("ConstraintInf")) {
				cons.add(new ConstraintInf(variables.get(i),variables.get(i+1)));
			}
			
		}
		return new Csp(variables, cons);
	}	
}*/
}
	