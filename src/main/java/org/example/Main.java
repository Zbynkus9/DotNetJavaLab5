package org.example;

public class Main {
    public static void main() {
        int n = 10;
        int seed = 1;
        int lowerBound = 1;
        int upperBound = 10;
        int capacity = 15;

        Problem problem = new Problem(n, seed, lowerBound, upperBound);
        System.out.println(problem.toString());

        Result result = problem.Solve(capacity);
        System.out.println(result.toString());
    }
}
