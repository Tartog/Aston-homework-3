package com.company;

import java.util.*;
import java.util.stream.Collectors;

public class PuttingIntoPractice {

    public static void main(String... args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        List<Transaction> list1 = transactions.stream()
                .filter(transaction -> transaction.getYear() == 2011)
                .sorted(Comparator.comparingInt(Transaction::getYear))
                .collect(Collectors.toList());

        System.out.println("Транзакции за 2011 год, отсортированные по сумме (от большей к меньшей):");
        System.out.println(list1.toString());

        List<String> list2 = transactions.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .distinct()
                .collect(Collectors.toList());
        System.out.println("\nСписок неповторяющихся городов, в которых работают трейдеры:");
        System.out.println(list2);

        List<Trader> list3 = transactions.stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getTrader)
                .distinct()
                .sorted(Comparator.comparing(Trader::getName))
                .collect(Collectors.toList());

        System.out.println("\nСписок всех трейдеров из Кембриджа, отсортированный по именам:");
        System.out.println(list3);

        String traders = transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .sorted(String::compareTo)
                .collect(Collectors.joining(" "));

        System.out.println("\nСтрока со всеми именами трейдеров, отсортированными в алфавитном порядке:");
        System.out.println(traders);

        boolean traderMilan = transactions.stream()
                .anyMatch(transaction -> transaction.getTrader().getCity().equals("Milan"));

        if (traderMilan){System.out.println("\nЕсть по краней мере 1 трейдер из Милана");}
        else { System.out.println("\nНет трейдеров из Милана"); }

        Map<String,Integer> sumTransactionsCambridge = transactions.stream()
                .filter(city -> city.getTrader().getCity().equals("Cambridge"))
                .collect(Collectors.groupingBy(transaction -> transaction.getTrader().getName(),
                        Collectors.summingInt(Transaction::getValue)));

        System.out.println("\nСуммы всех транзакций трейдеров из Кембриджа:");
        System.out.println(sumTransactionsCambridge);

        int maxTransaction = transactions.stream()
                .map(Transaction::getValue)
                .max(Integer::compareTo)
                .get();

        System.out.println("\nМаксимальная сумма среди всех транзакций: " + maxTransaction);

        int minTransaction = transactions.stream()
                .map(Transaction::getValue)
                .min(Integer::compareTo)
                .get();

        System.out.println("\nМинимальная сумма среди всех транзакций: " + minTransaction);
    }
}
