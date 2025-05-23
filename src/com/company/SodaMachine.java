package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class SodaMachine
{
    private static int money;

    /// <summary>
    /// This is the starter method for the machine
    /// </summary>
    public void Start()
    {
        var inventory = new ArrayList<Soda>();
        inventory.add(new Soda("coke", 5));
        inventory.add(new Soda("sprite", 3));
        inventory.add(new Soda("fanta", 3));

        while (true)
        {
            System.out.println("\n\nAvailable commands:");
            System.out.println("insert (money) - Money put into money slot");
            System.out.println("order (coke, sprite, fanta) - Order from machines buttons");
            System.out.println("sms order (coke, sprite, fanta) - Order sent by sms");
            System.out.println("recall - gives money back");
            System.out.println("-------");
            System.out.println("Inserted money: " + money);
            System.out.println("-------\n\n");


            String input;
            Scanner scanner = new Scanner(System.in);
            input = scanner.nextLine();

            if (input.startsWith("insert"))
            {
                //Add to credit
                money += Integer.parseInt(input.split(" ")[1]);
                System.out.println("Adding " + Integer.parseInt(input.split(" ")[1]) + " to credit");
            }
            if (input.startsWith("order"))
            {
                // split string on space
                var csoda = input.split(" ")[1];
                //Find out witch kind
                switch (csoda)
                {
                    case "coke":
                        var coke = inventory.get(0);
                        if (coke.Name.equals(csoda) && money > 19 && coke.Nr > 0)
                        {
                            System.out.println("Giving coke out");
                            money -= 20;
                            System.out.println("Giving " + money + " out in change");
                            money = 0;
                            coke.Nr--;
                        }
                        else if (coke.Name.equals(csoda) && coke.Nr == 0)
                        {
                            System.out.println("No coke left");
                        }
                        else if (coke.Name.equals(csoda))
                        {
                            System.out.println("Need " + (20 - money) + " more");
                        }

                        break;
                    case "fanta":
                        var fanta = inventory.get(2);
                        if (fanta.Name.equals(csoda) && money > 14 && fanta.Nr >= 0)
                        {
                            System.out.println("Giving fanta out");
                            money -= 15;
                            System.out.println("Giving " + money + " out in change");
                            money = 0;
                            fanta.Nr--;
                        }
                        else if (fanta.Name.equals(csoda) && fanta.Nr == 0)
                        {
                            System.out.println("No fanta left");
                        }
                        else if (fanta.Name.equals(csoda))
                        {
                            System.out.println("Need " + (15 - money) + " more");
                        }

                        break;
                    case "sprite":
                        var sprite = inventory.get(1);
                        if (sprite.Name.equals(csoda) && money > 14 && sprite.Nr > 0)
                        {
                            System.out.println("Giving sprite out");
                            money -= 15;
                            System.out.println("Giving " + money + " out in change");
                            money = 0;
                            sprite.Nr--;
                        }
                        else if (sprite.Name.equals(csoda) && sprite.Nr == 0)
                        {
                            System.out.println("No sprite left");
                        }
                        else if (sprite.Name.equals(csoda))
                        {
                            System.out.println("Need " + (15 - money) + " more");
                        }
                        break;
                    default:
                        System.out.println("No such soda");
                        break;
                }
            }
            if (input.startsWith("sms order"))
            {
                var csoda = input.split(" ")[2];
                //Find out witch kind
                switch (csoda)
                {
                    case "coke":
                        if (inventory.get(0).Nr > 0)
                        {
                            System.out.println("Giving coke out");
                            inventory.get(0).Nr--;
                        }
                        break;
                    case "sprite":
                        if (inventory.get(1).Nr > 0)
                        {
                            System.out.println("Giving sprite out");
                            inventory.get(1).Nr--;
                        }
                        break;
                    case "fanta":
                        if (inventory.get(2).Nr > 0)
                        {
                            System.out.println("Giving fanta out");
                            inventory.get(2).Nr--;
                        }
                        break;
                }

            }

            if (input.equals("recall"))
            {
                //Give money back
                System.out.println("Returning " + money + " to customer");
                money = 0;
            }

        }
    }
}
