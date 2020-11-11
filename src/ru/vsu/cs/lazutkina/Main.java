package ru.vsu.cs.lazutkina;

import java.util.Scanner;
import java.util.Locale;

public class Main
{
    public static void main(String[] args)
    {
        Locale.setDefault(Locale.ROOT);

        double x = readDouble("Enter variable x from -1 to 1: ");
        while (!checkDomainX(x))
        {
            x = readDouble("Entered x does not belong to (-1; 1). Try again: ");
        }
        double epsilon = readDouble("Enter precision e: ");
        int number = readInt("Enter sequence member number n: ");

        double sumSequenceMembers = findSumElements(x, number);
        System.out.printf("The sum of %d members of a sequence is %f\n", number, sumSequenceMembers);

        SumAndIteration sumWithEpsilon = findSumAndIteration(x, epsilon);
        printResultWithPrecision(sumWithEpsilon, epsilon);

        SumAndIteration sumWithEpsilonDivided10 = findSumAndIteration(x, epsilon / 10);
        printResultWithPrecision(sumWithEpsilonDivided10, epsilon / 10);

        double functionValue = calcValue(x);
        System.out.printf("Exact value of the function ln(1 + x) is %f\n", functionValue);
    }

    private static int readInt(String phrase)
    {
        System.out.print(phrase);
        Scanner scn = new Scanner(System.in);
        return scn.nextInt();
    }

    private static double readDouble(String phrase)
    {
        System.out.print(phrase);
        Scanner scn = new Scanner(System.in);
        return scn.nextDouble();
    }

    private static boolean checkDomainX(double x)
    {
        return x >= -1 && x <= 1;
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

        while (Math.abs(findElement(x, iteration)) > precision)
        {
            if (iteration % 2 != 0)
            {
                sum += findElement(x, iteration);
            }
            else
            {
                sum -= findElement(x, iteration);
            }
            iteration++;
        }
        return new SumAndIteration(sum, iteration);
    }

    private static double calcValue(double x)
    {
        return Math.log(x + 1);
    }

    private static void printResultWithPrecision(SumAndIteration sumAndIteration, double precision)
    {
        System.out.printf("The sum of the first %d elements with precision %f is %f\n",
                sumAndIteration.getIteration(), precision, sumAndIteration.getSum());
    }
}
