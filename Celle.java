import java.util.Arrays;

public class Celle {
  private String status;
  private Celle[] naboer;
  private int antNaboer;
  private int antLevendeNaboer;

  public Celle() {
    this.status = "doed";
    this.naboer = new Celle[8];
    this.antNaboer = 0;
    this.antLevendeNaboer = 0;
  }

  public void settLevende() {
    this.status = "levende";
  }

  public void settDoed() {
    this.status = "doed";
  }

  public boolean erLevende() {
    return this.status == "levende";
  }

  public char hentStatusTegn() {
    char levendeTegn = "O".toCharArray()[0];
    char doedTegn = ".".toCharArray()[0];

    return this.erLevende() ? levendeTegn : doedTegn;
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
        .filter(celle -> celle.erLevende())
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
