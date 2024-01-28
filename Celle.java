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

  public boolean erLevende() {
    return this.levende;
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

    this.naboer[antNaboer] = celle;
    this.antNaboer += 1;

    if (celle.erLevende()) {
      this.antLevendeNaboer += 1;
    }
  }

  public void tellLevendeNaboer() {
    this.antLevendeNaboer = (int) Arrays.stream(this.naboer)
        .filter(celle -> celle != null && celle.levende)
        .count();

  }

  public void oppdaterStatus() {
    if (this.levende && (this.antLevendeNaboer < 2 || this.antLevendeNaboer > 3)) {
      this.levende = false;
    } else if (!this.levende && this.antLevendeNaboer == 3) {
      this.levende = true;
    }

  }

  @Override
  public String toString() {
    return String.format(
        "Celle(levende=%s, antNaboer=%d, antLevendeNaboer=%d)",
        this.levende,
        this.antNaboer,
        this.antLevendeNaboer);
  }
}
