package ru.vsu.cs.lazutkina;

import java.util.Scanner;
import java.util.Locale;

public class Main
{
    public static void main(String[] args)
    {
        Locale.setDefault(Locale.ROOT);

        double x = readX("variable x from -1 to 1");
        double epsilon = readDouble("precision e");
        int number = readInt("sequence member number n");

        double sumSequenceMembers = findSumElements(x, number);
        System.out.printf("Sum of %d members of a sequence: %f\n", number, sumSequenceMembers);

        printResult(findSumAndIteration(x, epsilon), epsilon);
        printResult(findSumAndIteration(x, epsilon / 10), epsilon / 10);

        double functionValue = calcValue(x);
        System.out.printf("Exact value of the function ln(1 + x): %f\n", functionValue);
    }

    private static double readX(String phrase)
    {
        double x = readDouble(phrase);
        while (x <= -1 || x >= 1)
        {
            System.out.println("Entered x does not belong to (-1; 1). Try again.");
            x = readDouble(phrase);
        }
        return x;
    }

    private static int readInt(String phrase)
    {
        System.out.printf("Input %s: ", phrase);
        Scanner scn = new Scanner(System.in);
        return scn.nextInt();
    }

    private static double readDouble(String phrase)
    {
        System.out.printf("Input %s: ", phrase);
        Scanner scn = new Scanner(System.in);
        return scn.nextDouble();
    }

    private static double findElement(double x, int number)
    {
        return Math.pow(x, number) / number;
    }

    private static double findSumElements(double x, int number)
    {
        double sum = 0;
        for (int n = 1; n <= number; n++)
        {
            if (n % 2 != 0)
            {
                sum += findElement(x, n);
            }
            else
            {
                sum -= findElement(x, n);
            }
        }
        return sum;
    }

    private static SumAndIteration findSumAndIteration(double x, double precision)
    {
        int iteration = 1;
        double sum = 0;

        while (!checkAccuracyCalculations(x, iteration, precision))
        {
            sum = findSumElements(x, iteration);
            iteration++;
        }
        return new SumAndIteration(sum, iteration);
    }

    private static boolean checkAccuracyCalculations(double x, int elementNumber, double precision)
    {
        return Math.abs(findSumElements(x, elementNumber + 1) - findSumElements(x, elementNumber)) <= precision;
    }

    private static double calcValue(double x)
    {
        return Math.log(x + 1);
    }

    private static void printResult(SumAndIteration sumAndIteration, double precision)
    {
        System.out.printf("The sum of the first %d elements with precision %f is %f\n",
                sumAndIteration.getIteration(), precision, sumAndIteration.getSum());
    }
}
