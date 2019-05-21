package com.questions.algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MudWall
{
    public int maxHeight(int[] stickPositions, int[] stickHeights)
    {
        int left, right, maxheight = 0, currentheight, j, l;

        //An array to store the height of mud segments and stick together
        int ar[] = new int[stickPositions[stickPositions.length - 1] + 1];

        for (int i = 0; i < stickHeights.length; i++)
        {
            ar[stickPositions[i]] = stickHeights[i];
        }

        //Traverse the stickPositions array in consecutive pair
        //and fill the mud segment if possible.
        for (int i = 0; i < stickPositions.length - 1; i++)
        {
            left = stickPositions[i];
            right = stickPositions[i + 1];

            //Getting the number of cells for mud segment
            l = right - left - 1;
            j = 0;

            //Find the shorter side between left and right and add the mud segment
            //next to it or before it. Add the height in the array ar.
            while (j < l)
            {
                if (ar[right] < ar[left])
                {
                    currentheight = ar[right] + 1;
                    ar[right - 1] = currentheight;
                    right--;
                }
                else
                {
                    currentheight = ar[left] + 1;
                    ar[left + 1] = currentheight;
                    left++;
                }

                j++;
                maxheight = maxheight < currentheight ? currentheight : maxheight;
            }
        }

        return maxheight;
    }

    public static void main(String args[])throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the number of elements in stickPositions");
        int n = Integer.parseInt(br.readLine());
        int stickPostions[] = new int[n];

        for (int i = 0; i < n; i++)
        {
            stickPostions[i] = Integer.parseInt(br.readLine());
        }

        System.out.println("Enter the number of elements in stickHeights");
        n = Integer.parseInt(br.readLine());
        int stickHeights[] = new int[n];

        for (int i = 0; i < n; i++)
        {
            stickHeights[i] = Integer.parseInt(br.readLine());
        }

        MudWall mudWall = new MudWall();
        System.out.println(mudWall.maxHeight(stickPostions, stickHeights));
    }
}
