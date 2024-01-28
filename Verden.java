
public class Verden {
  int genNr;
  int antRader;
  int antKolonner;
  Rutenett rutenett;

  public Verden(int antRader, int antKolonner) {
    this.genNr = 0;
    this.antRader = antRader;
    this.antKolonner = antKolonner;
    this.rutenett = new Rutenett(antRader, antKolonner);
    this.rutenett.fyllMedTilfeldigeCeller();
    this.rutenett.kobleAlleCeller();
  }

  public void tegn() {
    System.out.println("Generasjon: " + this.genNr);
    System.out.println("Antall levende celler: " + this.rutenett.antallLevende());
    this.rutenett.tegnRutenett();
  }

  public void oppdater() {
    this.rutenett.oppdater();
    this.genNr++;
  }
}
