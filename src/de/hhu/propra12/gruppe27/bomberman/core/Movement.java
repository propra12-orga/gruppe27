package de.hhu.propra12.gruppe27.bomberman.core;


//Es fehlt nocht ein KeyListener. Er sollte auf das Spielfeld fokusiert sein, dann kann ich diesen hier einbinden.
//Hier wird im Grunde bei jeder Tasteneingabe abgefragt, ob das genannte Feld frei ist. Wenn es frei ist, wird die variable posx oder posy ver�ndert.
//Da das Spielfeld sowieso st�ndig neu gezeichnet wird sollte das ver�ndern der Positionsvariable reichen.

/*


	public static void move(char taste){
		
		switch (taste){
		case 'W': if(laxbr[Player.posx][Player.posy-1] == true){Player.posy--;} break;
		case 'A': if(laxbr[Player.posx-1][Player.posy] == true){Player.posx--;} break;
		case 'S': if(laxbr[Player.posx][Player.posy+1] == true){Player.posy++;} break;
		case 'D': if(laxbr[Player.posx+1][Player.posy] == true){Player.posx++;} break;
		default: break;
		}
	}
}

*/