package de.hhu.propra12.gruppe27.bomberman.core;
//oberklasse f�r alle levels, enth�lt methode textout um level testweise in der konsole ausgeben zu lassen, sp�ter weitere methoden wie draw(),...
//generierungsregeln f�r level sind von Level abgeleitet,
//(?)level in seperates package(?)
public abstract class Level {
int laenge;
int breite;
String name;
protected AbstractFeld laxbr[][];

public Level(int laenge, int breite) {// initialisiert l�nge breite und
// spielfeld"laxbr" (bin f�r
// umbenennung in spielfeld o.�.
// ;-))
this.laenge = laenge;
this.breite = breite;
laxbr = new AbstractFeld[laenge][breite];

}

public void textout() {// gibt level in konsole aus
for (int i = 0; i < laenge; i++) {
System.out.println(" ");
for (int j = 0; j < breite; j++) {
System.out.print(laxbr[i][j].toChar());
}

}
}

public AbstractFeld getFeld(int x, int y) {
return laxbr[x][y];
}

public void setFeld(AbstractFeld input, int x, int y) {
laxbr[x][y] = input;
}

public abstract int[] getStartposition(int spielernummer);

public int getlaenge() {
return laenge;
}

public int getbreite() {
return breite;
}

}