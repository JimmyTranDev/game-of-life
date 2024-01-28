import java.util.Arrays;

public class Celle {
  public boolean levende;
  public Celle[] naboer;
  public int antNaboer;
  public int antLevendeNaboer;

  public Celle() {
    this.levende = false;
    this.naboer = new Celle[8];
    this.antNaboer = 0;
    this.antLevendeNaboer = 0;
  }

  public void settLevende() {
    this.levende = true;
  }

  public void settDoed() {
    this.levende = false;
  }

  public char hentStatusTegn() {
    char levendeTegn = "O".toCharArray()[0];
    char doedTegn = ".".toCharArray()[0];

    return this.levende ? levendeTegn : doedTegn;
  }

  public void leggTilNabo(Celle celle) {
    if (this.antNaboer == 8) {
      throw new IllegalArgumentException("Celle har allerede 8 naboer");
    }

    this.naboer[antLevendeNaboer] = celle;
    this.antNaboer++;
  }

  public void tellLevendeNaboer() {
    this.antLevendeNaboer = (int) Arrays.asList(this.naboer)
        .stream()
        .filter(celle -> celle.levende)
        .count();
  }

  private boolean erUnderPopulert() {
    return this.antLevendeNaboer < 2;
  }

  private boolean erOvererPopulert() {
    return this.antLevendeNaboer > 3;
  }

  public void oppdaterStatus() {
    if (this.erUnderPopulert() || this.erOvererPopulert()) {
      this.settDoed();
      return;
    }

    this.settLevende();
  }
}
