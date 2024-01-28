import java.util.stream.IntStream;

class GameOfLife {
  public static void main(String[] args) {
    Verden verden = new Verden(8, 12);
    IntStream.range(0, 10).forEach(i -> {
      verden.tegn();
      verden.oppdater();
    });
  }
}
