package com.iza;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        LinkedList<String> placesToVisit = new LinkedList<String>();
        addInOrder(placesToVisit, "Karpacz");
        addInOrder(placesToVisit, "Gdynia");
        addInOrder(placesToVisit, "Sopot");
        addInOrder(placesToVisit, "Zakopane");
        addInOrder(placesToVisit, "Karpacz");
        addInOrder(placesToVisit, "Hel");
        addInOrder(placesToVisit, "Krotoszyn");
        addInOrder(placesToVisit, "Cieszyn");
        addInOrder(placesToVisit, "Zamosc");
        addInOrder(placesToVisit, "Poznan");
        printList(placesToVisit);

        visit(placesToVisit);
    }

    //    private static boolean addInOrder(LinkedList<String> linkedList, String newCity) {
    private static void printList(LinkedList<String> linkedList) {
        Iterator<String> i = linkedList.iterator();               //.iterator(); ta sama rola co petla for() --> import java.util.Iterator
        while (i.hasNext()) {
            System.out.println("Now visiting " + i.next());      //hasNext - checks if it's something linked do current item
        }                                                       //i.next() - it moves to next value/entry and returns current value before moves on
        System.out.println("===========================");
    }

    private static boolean addInOrder(LinkedList<String> linkedList, String newCity) {      //we will use a list iterator
        ListIterator<String> stringListIterator = linkedList.listIterator();                //tworzac automatycznie przechodzimy do pierwszego elementu

        while (stringListIterator.hasNext()) {                                              // dzieki while - przejdziemy przez wszystkie elementy (entries)
            int comparison = stringListIterator.next().compareTo(newCity);                  // next() --> move to the next record, .compareTo(newCity) --> city we're looking for in search; returns number - 0: match found - we don't want to add anything
            if (comparison == 0) {
                // equal - don't add
                System.out.println(newCity + " is already included as a destination");
                return false;                                                      // wasn't successfully added
            } else if (comparison > 0) {                                           // new item should appear before this one
                // new city should appear before this one
                // Brisbane --> Adelaide . Problem --> we used .next() >> already move to the next one (which returns current entry but also moves to the next one, so we need to move back
                stringListIterator.previous();                  // only list iterator has that ability to move back to the previous record
                stringListIterator.add(newCity);                // it's going to be added before current entry
                return true;
            } else {
                // move on to the next city
                return true;
            }
        }
        stringListIterator.add(newCity);
        return true;
    }

    private static void visit(LinkedList cities) {
        Scanner skaner = new Scanner(System.in);
        boolean quit = false;                       //like in previous -> if we want to exit we have to set that to true;
        boolean goingForward = true;                // variable to track the direction, we need to test for that
        ListIterator<String> listIterator = cities.listIterator();

        if (cities.isEmpty()){
            System.out.println("No cities in the itenerary");
            return;
        }else {
            System.out.println("Now visiting " + listIterator.next());
            printMenu();
        }

        while (!quit) {
            int action = skaner.nextInt();
            skaner.nextLine();
            switch (action) {

                case 0:
                    System.out.println("Vacation is over");
                    quit = true;
                    break;

                case 1:
                    if(!goingForward) {                  // user decided to go back
                        if(listIterator.hasNext()){
                            listIterator.next();
                        }
                        goingForward = true;
                    }
                    if (listIterator.hasNext()) {
                        System.out.println("Now visiting " + listIterator.next());
                    }else {
                        System.out.println("Reached the end of the list.");
                        goingForward = false;
                    }
                    break;

                case 2:
                    if(goingForward) {
                        if(listIterator.hasPrevious()){
                            listIterator.previous();
                        }
                        goingForward = false;
                    }
                    if (listIterator.hasPrevious()) {
                        System.out.println("Now vistising " + listIterator.previous());
                    }else{
                        System.out.println("We are at the start of the list");
                        goingForward = true;
                    }
                    break;

                case 3:
                    printMenu();
                    break;
            }
        }
    }

    private static void printMenu(){
        System.out.println("Available actions: \npress");
        System.out.println("0 - to quit\n" +
                            "1 - go to next city\n" +
                            "2 - go to previous city\n" +
                            "3 - to print menu options");
    }
}