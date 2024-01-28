import java.util.Arrays;
import java.util.Optional;
import java.util.stream.IntStream;

public class Rutenett {
  public Celle[][] rutene;
  public int antRader;
  public int antKolonner;

  Rutenett(int antRader, int antKolonner) {
    this.antRader = antRader;
    this.antKolonner = antKolonner;
    rutene = new Celle[antRader][antKolonner];
  }

  public void settCelle(int rad, int kolonne) {
    Celle celle = new Celle();

    boolean burdeVaereLevende = Math.random() <= 0.3333;
    if (burdeVaereLevende) {
      celle.settLevende();
    }

    rutene[rad][kolonne] = celle;
  }

  public void tegnRutenett() {
    IntStream
        .range(0, antRader)
        .forEach(rad -> this.tegnRad(rad));
  }

  public void fyllMedTilfeldigeCeller() {
    IntStream
        .range(0, antRader)
        .forEach(rad -> this.fyllRadMedTilfeldigeCeller(rad));
  }

  public Celle hentCelle(int rad, int kolonne) {
    try {
      return rutene[rad][kolonne];
    } catch (ArrayIndexOutOfBoundsException e) {
      return null;
    }
  }

  public void settNaboer(int rad, int kolonne) {
    Celle celle = this.hentCelle(rad, kolonne);

    if (celle == null) {
      return;
    }

    int[][] naboPosisjoner = this.faAlleNaboCellePosisjoner(rad, kolonne);
    Celle[] naboer = this.hentCeller(naboPosisjoner);
    Arrays.asList(naboer).forEach(celle::leggTilNabo);
  }

  public int antallLevende() {
    Celle[] celler = hentAlleCeller();
    return Arrays.asList(celler)
        .stream()
        .filter(celle -> celle.erLevende())
        .toArray(Celle[]::new).length;
  }

  public void oppdater() {
    Celle[] celler = hentAlleCeller();
    Arrays.asList(celler).forEach(celle -> celle.tellLevendeNaboer());
    Arrays.asList(celler).forEach(celle -> celle.oppdaterStatus());
  }

  ///////////////////////////////////////////////////////////////////////////

  private void fyllRadMedTilfeldigeCeller(int rad) {
    IntStream
        .range(0, antKolonner)
        .forEach(kolonne -> this.settCelle(rad, kolonne));
  }

  private void tegnRad(int rad) {
    Arrays.asList(rutene[rad])
        .stream()
        .map(celle -> celle.hentStatusTegn())
        .forEach(tegn -> System.out.print(tegn));
    System.out.println();
  }

  private int[][] hentAllePar(int[] a, int[] b) {
    int aLength = a.length;
    int bLength = b.length;

    int[][] result = new int[aLength * bLength][2];
    for (int i = 0; i < aLength; i++) {
      for (int j = 0; j < bLength; j++) {
        result[i * bLength + j][0] = a[i];
        result[i * bLength + j][1] = b[j];
      }
    }

    return result;
  }

  private int[] hentNaboRader(int rad) {
    return IntStream
        .range(rad - 1, rad + 2)
        .filter(r -> r >= 0 && r < antRader)
        .toArray();
  }

  private int[] hentNaboKolonner(int kolonne) {
    return IntStream
        .range(kolonne - 1, kolonne + 2)
        .filter(k -> k >= 0 && k < antKolonner)
        .toArray();
  }

  private int[][] filtrerUtEgenPosisjon(int[][] posisjoner, int rad, int kolonne) {
    return Arrays
        .stream(posisjoner)
        .filter(posisjon -> posisjon[0] != rad || posisjon[1] != kolonne)
        .toArray(int[][]::new);
  }

  private int[][] faAlleNaboCellePosisjoner(int rad, int kolonne) {
    int[] naboRader = this.hentNaboRader(rad);
    int[] naboKolonner = this.hentNaboKolonner(kolonne);
    int[][] egenOgNaboPosisjoner = this.hentAllePar(naboRader, naboKolonner);
    int[][] naboPosisjoner = this.filtrerUtEgenPosisjon(egenOgNaboPosisjoner, rad, kolonne);

    return naboPosisjoner;
  }

  private Celle[] hentCeller(int[][] posisjoner) {
    return Arrays
        .stream(posisjoner)
        .map(posisjon -> this.hentCelle(posisjon[0], posisjon[1]))
        .filter(celle -> celle != null)
        .toArray(Celle[]::new);
  }

  public void kobleAlleCeller() {
    for (int rad = 0; rad < antRader; rad++) {
      for (int kolonne = 0; kolonne < antKolonner; kolonne++) {
        this.settNaboer(rad, kolonne);
      }
    }
  }

  private Celle[] hentAlleCeller() {
    return Arrays
        .stream(rutene)
        .flatMap(Arrays::stream)
        .toArray(Celle[]::new);

  }
}
