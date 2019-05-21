package com.questions.algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;

public class OfficeParty
{
    private static final double pi = 3.14159265359;

    //A helper function to check if it is possible to cut slices of area 'ar'
    //from cakes such that every guest gets a piece.
    private boolean isPossible(double[] area, double ar, int guests)
    {
        double current = area[0];
        int n = guests, i = 0;

        //Cut slices from the current cake, area[i] and decrease the slice area
        //from current. If it is not possible to cut anymore slice, move on to the next cake.
        while (n > 0)
        {
            current = current - ar;

            if (current < 0)
            {
                i++;

                if (i == area.length)
                    return false;

                current = area[i];
            }
            else
                n--;
        }

        return true;
    }

    public String largestPiece(int[] radii, int guests)
    {
        double high = 0.0;
        double area[] = new double[radii.length];

        for (int i = 0; i < radii.length; i++)
        {
            area[i] = pi * radii[i] * radii[i];
            high = high < area[i] ? area[i] : high;
        }

        double low = 0.0, middle;

        //Taking area of the largest cake as the upper limit and zero as the lower limit
        //do a binary search of the area for which it is possible to feed the guests.
        //Maximise this area.
        while (low < high)
        {
            middle = (low + high) / 2.0;

            if (isPossible(area, middle, guests))
            {
                low = middle;
            }
            else
            {
                high = middle - 0.0001;
            }
        }

        DecimalFormat df = new DecimalFormat("#.####");
        return df.format(high);
    }

    public static void main(String args[])throws IOException
    {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the number of elements in radii");
        int n = Integer.parseInt(bufferedReader.readLine());
        int[] radii = new int[n];

        for (int i = 0; i < n; i++)
        {
            radii[i] = Integer.parseInt(bufferedReader.readLine());
        }

        System.out.println("Enter the number of guests");
        int guests = Integer.parseInt(bufferedReader.readLine());
        OfficeParty officeParty = new OfficeParty();
        System.out.println(officeParty.largestPiece(radii, guests));

    }
}
