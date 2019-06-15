package jeu;

import java.util.ArrayList;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

public class Reine  extends Piece {
	public Reine(boolean uneCouleur, int uneLigne, int uneColonne) {
		super(uneCouleur, uneLigne, uneColonne);
	}
	
	public Reine(boolean uneCouleur, Position unePosition) {
		this(uneCouleur, unePosition.x, unePosition.y);
	}
	
	public boolean estDeplace = false;
	

	@Override
	public void calculCoups(Echiquier e, Partie p) {
		this.calculCoups(e);
	}
	
	public void calculCoups(Echiquier e) {
		Position positionDeDepart = super.getPosition();
		Position[] vecteurPosition ={new Position(1,-1),new Position(-1,-1),new Position(1,1),new Position(-1,1),
				new Position(0,1),new Position(0,-1),new Position(1,0),new Position(-1,0)};
		
		
		for (int i =0 ; i<8; i++ ) {
			Position nouvellePosition = positionDeDepart;
			boolean estBloque  = false; 
			
			while (Position.positionValide(nouvellePosition) && estBloque == false ) {
				nouvellePosition = nouvellePosition.addition(vecteurPosition[i]);
				
				if (Position.positionValide(nouvellePosition)) {
					if (e.containsPiece(nouvellePosition)){
						estBloque = true;
					}
					
					super.addCoup(nouvellePosition);
				}
			}
		}
	}

	public String affiche() {
		boolean couleurPion = super.getCouleur(); 
		if(super.getCouleur() == Couleur.NOIR){
			return "♛";
		}
			else {
				return "♕";
			}
	}
	
	public static void main(String args[]) {
    	Echiquier e = new Echiquier(true); // Echiquier vide
    	
    	Position posFou = new Position(1,1);
    	
    	e.setPiece(posFou, new Reine(Couleur.BLANC, posFou));    	
    	e.setPiece(0,0, new Cavalier(Couleur.NOIR, 0,0));
    	
    	((Reine) e.getPiece(posFou)).calculCoups(e);
    	ArrayList<Position> a = e.getPiece(posFou).getListeCoups();
    	
    	for (Position p : a) {
    		System.out.println(p);
    	}
	}
}