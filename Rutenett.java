import java.util.Arrays;
import java.util.Optional;
import java.util.stream.IntStream;

public class Rutenett {
  private Celle[][] rutene;
  private int antRader;
  private int antKolonner;

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

  private void fyllRadMedTilfeldigeCeller(int rad) {
    IntStream
        .range(0, antKolonner)
        .forEach(kolonne -> this.settCelle(rad, kolonne));
  }

  public void fyllMedTilfeldigeCeller() {
    IntStream
        .range(0, antRader)
        .forEach(rad -> this.fyllRadMedTilfeldigeCeller(rad));
  }

  public Optional<Celle> hentCelle(int rad, int kolonne) {
    try {
      return Optional.of(rutene[rad][kolonne]);
    } catch (ArrayIndexOutOfBoundsException e) {
      return null;
    }
  }

  private void tegnRad(int rad) {
    Arrays.asList(rutene[rad])
        .stream()
        .map(celle -> celle.hentStatusTegn())
        .forEach(tegn -> System.out.print(tegn));
  }

  public void tegnRutenett() {
    IntStream
        .range(0, antRader)
        .forEach(rad -> this.tegnRad(rad));
  }

  private int[][] faAllePar(int[] a, int[] b) {
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

  private int[] faNaboRader(int rad) {
    return IntStream
        .range(rad - 1, rad + 2)
        .filter(r -> r >= 0 && r < antRader)
        .toArray();
  }

  private int[] faNaboKolonner(int kolonne) {
    return IntStream
        .range(kolonne - 1, kolonne + 2)
        .filter(k -> k >= 0 && k < antKolonner)
        .toArray();
  }

  private int[][] filtrerUtEgenPosisjon(int[][] par, int rad, int kolonne) {
    return Arrays
        .stream(par)
        .filter(p -> p[0] != rad || p[1] != kolonne)
        .toArray(int[][]::new);
  }

  public int[][] faAlleNaboCellePosisjoner(int rad, int kolonne) {
    int[] naboRad = faNaboRader(rad);
    int[] naboKolonne = faNaboKolonner(kolonne);
    int[][] naboOgEgenPosisjoner = this.faAllePar(naboRad, naboKolonne);
    int[][] naboPosisjoner = filtrerUtEgenPosisjon(naboOgEgenPosisjoner, rad, kolonne);

    return naboPosisjoner;
  }

  public void settNaboer(int rad, int kolonne) {
  }

}
