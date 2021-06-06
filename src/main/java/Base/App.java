/*
 *  UCF COP3330 Summer 2021 Assignment 1 Solutions
 *  Copyright 2021 Kevin Schiffli
 */
package Base;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Scanner;

public class App {
    static Scanner in = new Scanner(System.in);

    public static void main( String[] args )
    {
        BigDecimal price = new BigDecimal ("1.00");
        String order = getData("order amount");
        String state = getData("state");
        BigDecimal orderQuant = stringToBigDecimal(order);
        String output = calcTotal (orderQuant, state, price);
        printOutput(output);
    }
    public static String getData(String phrase)
    {
        System.out.printf( "What is the %s? ", phrase);
        return in.nextLine();
    }

    public static BigDecimal stringToBigDecimal(String number)
    {
        BigDecimal result = new BigDecimal(number);
        return result;
    }

    public static String calcTotal(BigDecimal orderQuant, String state, BigDecimal price)
    {
        DecimalFormat decFormat = new DecimalFormat("#,###.00");
        BigDecimal total = new BigDecimal(String.valueOf(orderQuant.multiply(price)));
        if (state.equals("Wisconsin") || state.equals("WI"))
        {
            BigDecimal taxRateWI = new BigDecimal ("0.050");
            String county = getData("county");
            if (county.equals("Eau Claire"))
            {
                BigDecimal EauClaireAddedTax = new BigDecimal ("0.005");
                taxRateWI = taxRateWI.add(EauClaireAddedTax);
            }
            else if (county.equals("Dunn"))
            {
                BigDecimal DunnAddedTax = new BigDecimal ("0.004");
                taxRateWI = taxRateWI.add(DunnAddedTax);
            }
            BigDecimal subtotal = total;
            BigDecimal tax = total.multiply(taxRateWI);
            tax = tax.round(new MathContext(10, RoundingMode.CEILING));
            total = total.add(tax);
            total = total.round(new MathContext(10, RoundingMode.CEILING));
            return "The subtotal is $" + decFormat.format(subtotal) +"\nThe tax is $" + decFormat.format(tax) +
                    "\nThe total is $" + decFormat.format(total);
        }
        else if (state.equals("Illinois") || state.equals("IL"))
        {
            BigDecimal taxRateIL = new BigDecimal ("0.080");
            BigDecimal subtotal = total;
            BigDecimal tax = total.multiply(taxRateIL);
            tax = tax.round(new MathContext(10, RoundingMode.CEILING));
            total = total.add(tax);
            total = total.round(new MathContext(10, RoundingMode.CEILING));
            return "The subtotal is $" + decFormat.format(subtotal) +"\nThe tax is $" + decFormat.format(tax) +
                    "\nThe total is $" + decFormat.format(total);

        }

        total = total.round(new MathContext(10, RoundingMode.CEILING));
        return "The total is $" + decFormat.format(total);
    }

    public static void printOutput (String output)
    {
        System.out.println(output);
    }
}
