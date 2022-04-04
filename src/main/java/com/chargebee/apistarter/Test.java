package com.chargebee.apistarter;

import java.util.*;

public class Test {

    static void printPattern(int n) {
        int j, k = 0;
        for (int i = 1; i <= n; i++) {
            if (i % 2 != 0) {
                for (j = k + 1; j < k + i; j++)
                    System.out.print(j + "*");
                System.out.println(j++);
                k = j;
            } else {
                k = k + i - 1;
                for (j = k; j > k - i + 1; j--)
                    System.out.print(j + "*");
                System.out.println(j);
            }
        }
    }

    public static void main(String args[]) {
        int n = 100;
        printPattern(n);
    }

    List<Integer> findIndices(int X, List<Integer> arr, List<Integer> query_values) {

        ArrayList<Integer> indices = new ArrayList<>();
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i) == X) {
                indices.add(i + 1);
            }
        }
        ArrayList<Integer> output = new ArrayList<>();

        for (int i = 0; i < query_values.size(); i++) {
            if (indices.size() <= query_values.get(i)) {
                output.add(indices.get(query_values.get(i) - 1));
            } else {
                output.add(-1);
            }
        }

        return output;
    }
}
