# Nieograniczony Problem Plecakowy w Java

Projekt zrealizowany w ramach laboratorium z przedmiotu Platformy Programistyczne .NET i Java.

## Opis projektu
1. **Generator instancji (Konsola):** Losowe generowanie parametrów przedmiotów (waga, wartość) na podstawie zadanego ziarna (seed).
2. **Algorytm optymalizacyjny:** Aplikacja konsolowa rozwiązująca nieograniczony problem plecakowy z wykorzystaniem algorytmu zachłannego (aproksymacyjnego).
3. **Testy jednostkowe:** Weryfikacja logiki biznesowej aplikacji oraz poprawności wyników za pomocą frameworka JUnit 5.

## 📷 Sugerowane zrzuty ekranu (do umieszczenia w repozytorium)

* **Na ocenę 3.0:** Zrzut ekranu z konsoli pokazujący listę pomyślnie wygenerowanych przedmiotów (np. 10 elementów z wagą i wartością z odpowiedniego przedziału).
* **Na ocenę 4.0:** Zrzut ekranu z konsoli pokazujący pełne działanie programu: wygenerowaną listę, a pod nią ostateczny wynik działania algorytmu (wybrane przedmioty, sumaryczna waga i wartość) – *odpowiednik Rysunku 3 z instrukcji*.
* **Na ocenę 5.0:** Zrzut ekranu okna narzędziowego testów w IntelliJ (Run tool window) pokazujący zielone ptaszki przy wszystkich 4 zaliczonych testach jednostkowych klasy `ProblemTest`.

## 🏗 Struktura Projektu i Kluczowe Klasy

### Moduł problemu plecakowego
* **`Item`**: Klasa reprezentująca pojedynczy przedmiot (zawiera ID, wagę oraz wartość). Posiada przeciążoną metodę `toString()`.
* **`Problem`**: Główna klasa logiczna generatora.
    * `Konstruktor`: Wykorzystuje klasę `Random` i podane ziarno do wygenerowania listy obiektów `Item` mieszczących się w zadanym zakresie.
    * `Solve(int capacity)`: Implementacja algorytmu zachłannego. Sortuje przedmioty po stosunku wartości do wagi i dobiera je do ustalonej pojemności plecaka.
* **`Result`**: Klasa przechowująca rozwiązanie. Zawiera listę wybranych przedmiotów, sumaryczną wagę, wartość oraz własne formatowanie tekstu.

### Moduł testowy
* **`ProblemTest`**: Klasa wykorzystująca framework JUnit 5. Zawiera metody testowe weryfikujące skrajne przypadki (np. pusty plecak), poprawne generowanie instancji oraz poprawność działania algorytmu dla z góry znanych danych wejściowych.

## Kluczowe fragmenty kodu

1. Zad 1 (Generator instancji na ocenę 3.0)

```java
public Problem(int n, int seed, int lowerBound, int upperBound) {
    this.n = n;
    this.seed = seed;
    this.lowerBound = lowerBound;
    this.upperBound = upperBound;
    this.items = new ArrayList<>();

    Random random = new Random(seed);
    int range = upperBound - lowerBound + 1;

    for (int i = 0; i < n; i++) {
        int weight = random.nextInt(range) + lowerBound;
        int value = random.nextInt(range) + lowerBound;
        items.add(new Item(i, weight, value));
    }
}
```
Zad 2 (Algorytm zachłanny na ocenę 4.0)
```Java
public Result Solve(int capacity) {
    // Kopiowanie i sortowanie przedmiotów malejąco po opłacalności (wartość/waga)
    List<Item> sortedItems = new ArrayList<>(items);
    sortedItems.sort((i1, i2) -> {
        double ratio1 = (double) i1.value / i1.weight;
        double ratio2 = (double) i2.value / i2.weight;
        return Double.compare(ratio2, ratio1); 
    });

    Result result = new Result();
    int currentCapacity = capacity;

    // Zachłanne dobieranie przedmiotów do plecaka
    for (Item item : sortedItems) {
        while (currentCapacity >= item.weight) {
            result.addItem(item);
            currentCapacity -= item.weight;
        }
    }

    return result;
}
```
Zad 3 (Przykładowy test jednostkowy JUnit 5 na ocenę 5.0)
```Java
@Test
public void testSpecificInstanceResult() {
    // Arrange: Inicjalizacja problemu z parametrami z instrukcji
    Problem problem = new Problem(10, 1, 1, 10);
    
    // Act: Rozwiązanie problemu dla plecaka o pojemności 15
    Result result = problem.Solve(15);
    
    // Assert: Weryfikacja oczekiwanej wagi i wartości (zgodnie z Rysunkiem 3)
    assertEquals(15, result.totalWeight);
    assertEquals(33, result.totalValue);
}
```