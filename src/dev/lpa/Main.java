package dev.lpa;

import java.util.*;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter USERNAME #1:");
        String username1 = sc.nextLine().toUpperCase();
        System.out.println("Enter USERNAME #2");
        String username2 = sc.nextLine().toUpperCase();
        System.out.println("-".repeat(200));

        pokemonCards.pause();
        System.out.println(username1 + "'s DECK:" + " ".repeat(150) + username2 + "'s DECK:");
        List<pokemonCards> deck1 = new java.util.ArrayList<>(pokemonCards.Deck());
        List<pokemonCards> deck2 = new java.util.ArrayList<>(pokemonCards.Deck());
        printDecks(deck1, deck2);

        pokemonCards.pause();
        System.out.println("-".repeat(200));
        System.out.println("""
                MENU: 
                (S) Shuffle Deck
                (R) Reverse Deck
                (RP) Replace value in Deck
                (F) Fill deck with one pokemon
                (SR) Sort
                (P) Play""");

        String input = sc.nextLine();

        switch (input.toUpperCase()) {

            case "M" :

            case "S":
                System.out.println("Shuffling deck...");
                pokemonCards.pause();
                Collections.shuffle(deck1);
                Collections.shuffle(deck2);
                printDecks(deck1, deck2);
                break;
            case "R":
                System.out.println("Reversing deck...");
                pokemonCards.pause();
                Collections.reverse(deck1);
                Collections.reverse(deck2);
                printDecks(deck1, deck2);
                break;
            case "RP":
                System.out.println("-".repeat(200));
                System.out.println("Enter which number you want to replace in 1st deck:");
                int answer = sc.nextInt();
                System.out.println("Enter what you want to replace it with(name,hp,attack,super):");
                String a = sc.next();
                String[] array = a.split(",");
                pokemonCards pokemonCard = new pokemonCards(array[0], Integer.parseInt(array[1]), Integer.parseInt(array[2]), Integer.parseInt(array[3]));
                Collections.replaceAll(deck1, deck1.get(answer - 1), pokemonCard);
                Collections.replaceAll(deck2, deck1.get(answer - 1), pokemonCard);
                System.out.println("Replacing...");
                pokemonCards.pause();
                System.out.println("-".repeat(200));
                printDecks(deck1, deck2);
                break;

            case "F":
                System.out.println("Which deck (1, or 2): ");
                int deck = sc.nextInt();
                System.out.println("What pokemon do you want to fill your deck with(name,hp,attack,super):");
                String value = sc.next();
                String[] arr = value.split(",");
                if (deck == 1) {
                    Collections.fill(deck1, new pokemonCards(arr[0], Integer.parseInt(arr[1]), Integer.parseInt(arr[2]), Integer.parseInt(arr[3])));
                } else if (deck == 2) {
                    Collections.fill(deck2, new pokemonCards(arr[0], Integer.parseInt(arr[1]), Integer.parseInt(arr[2]), Integer.parseInt(arr[3])));
                }
                printDecks(deck1, deck2);
                break;

            case "SR":

                var sortingAlgorithm = Comparator.comparing(pokemonCards::hp).thenComparing(pokemonCards::attack2).thenComparing(pokemonCards::attack1);

                Collections.sort(deck1, sortingAlgorithm);
                Collections.sort(deck2, sortingAlgorithm);
                Collections.reverse(deck1);
                Collections.reverse(deck2);

                printDecks(deck1, deck2);
                break;

            case "P":
                System.out.println("-".repeat(250));
                System.out.println("BATTLE HAS STARTED between " + username1 + " and " + username2 + "!");
                System.out.println("-".repeat(250));
                Turns(username1, username2, deck1, deck2);
        }
    }

    public static void Turns(String username1, String username2, List<pokemonCards> deck1, List<pokemonCards> deck2) {
        Scanner sc = new Scanner(System.in);

        pokemonCards pokemon1;
        pokemonCards pokemon2;
        String attack1;
        String attack2;

        System.out.println("-".repeat(100));
        System.out.println("TURN " + 1 + " ".repeat(150) + "SCREEN:");
        System.out.println("-".repeat(100));
        System.out.println(username1 + " pick pokemon to send out:");
        int firstPokemon1 = sc.nextInt() - 1;
        pokemon1 = deck1.get(firstPokemon1);
        System.out.println("-".repeat(100));
        System.out.println(username1 + " sent out " + pokemon1.name() + " ".repeat(100) + pokemonCards.getImage(pokemon1));
        System.out.println("-".repeat(100));

        System.out.println(username2 + " pick pokemon to send out: ");
        int firstPokemon2 = sc.nextInt() - 7;
        pokemon2 = deck2.get(firstPokemon2);
        System.out.println("-".repeat(100));
        System.out.println(username2 + " sent out " + pokemon2.name() + " ".repeat(100) + pokemonCards.getImage(pokemon2));
        System.out.println("-".repeat(100));


        for (int i = 2; i < 50; i++) {

            System.out.println("-".repeat(100));
            System.out.println("TURN " + i + " ".repeat(150) + "SCREEN:");
            System.out.println("-".repeat(100));

            System.out.println(username1 + " Enter which attack you want to do (b) base (s) super: ");
            attack1 = sc.next();
            System.out.println(" ".repeat(150) + username2 + "'s pokemon:");
            pokemon2 = pokemon2.newHp(attackType(attack1, pokemon1, pokemon2, 1));
            System.out.println(pokemon2.name() + " took " + (attack1.equalsIgnoreCase("B") ? pokemon1.attack1() : pokemon1.attack2()) +
                    " Damage from " + pokemon1.name() + " ".repeat(100) + pokemonCards.getImage(pokemon2));
            System.out.println("-".repeat(100));

            if (pokemon2.hp() <= 0) {

                deck2.set(firstPokemon2, new pokemonCards("DEAD", 0, 0, 0));
                System.out.println("-".repeat(250));
                printDecks(deck1, deck2);
                System.out.println("-".repeat(250));
                firstPokemon2 = pokemonDeath(pokemon2, username2) - 7;
                pokemon2 = deck2.get(firstPokemon2);
                System.out.println(username2 + " sent out " + pokemon2.name() + " ".repeat(100) + pokemonCards.getImage(pokemon2));

                if (victory(deck2, username1)) {
                    System.out.println("-".repeat(250));
                    System.out.println();
                    System.out.println(username1 + " WON THE BATTLE");
                    System.out.println();
                    System.out.println("-".repeat(250));
                    break;
                }

            }

            System.out.println(username2 + " Enter which attack you want to do (b) base (s) super: ");
            attack2 = sc.next();
            System.out.println(" ".repeat(150) + username1 + "'s pokemon:");
            pokemon1 = pokemon1.newHp(attackType(attack2, pokemon1, pokemon2, 2));
            System.out.println(pokemon1.name() + " took " + (attack2.equalsIgnoreCase("B") ? pokemon2.attack1() : pokemon2.attack2()) + " Damage from " + pokemon2.name() + " ".repeat(100) + pokemonCards.getImage(pokemon1));
            System.out.println("-".repeat(100));

            if (pokemon1.hp() <= 0) {

                deck1.set(firstPokemon1, new pokemonCards("DEAD", 0, 0, 0));
                System.out.println("-".repeat(250));
                printDecks(deck1, deck2);
                System.out.println("-".repeat(250));
                firstPokemon1 = pokemonDeath(pokemon1, username1) - 1;
                pokemon1 = deck1.get(firstPokemon1);
                System.out.println(username1 + " sent out " + pokemon1.name() + " ".repeat(100) + pokemonCards.getImage(pokemon1));

                if (victory(deck1, username2)) {
                    System.out.println("-".repeat(250));
                    System.out.println();
                    System.out.println(username2 + " WON THE BATTLE");
                    System.out.println();
                    System.out.println("-".repeat(250));
                    break;
                }
            }

        }
    }

    public static boolean victory(List<pokemonCards> deck, String userName) {

        int v = 1;
        for (int i = 0; i < deck.size(); i++) {
            if (deck.get(i).equals(new pokemonCards("DEAD", 0, 0, 0))) {
                v++;
            }
        }

        if (v == 7) {
            return true;
        }
        return false;
    }

    public static int pokemonDeath(pokemonCards pokemon, String username) {

        Random random = new Random();
        Scanner sc = new Scanner(System.in);
        String[] deaths = {"Died by gang nigger rape", "Died by fentynal overdose", "Died by indian smell"};

        System.out.println(pokemon.name() + " " + deaths[random.nextInt(0, 2)]);
        System.out.println("-".repeat(100));

        System.out.println(username + " Enter new pokemon number:");

        return sc.nextInt();
    }

    public static int attackType(String attack, pokemonCards first, pokemonCards second, int userNameNumber) {

        int hp1;
        int hp2;
        int hp3;
        int hp4;

        if (userNameNumber == 1) {

            if (attack.equalsIgnoreCase("B")) {

                hp1 = second.hp() - first.attack1();
                return hp1;

            } else if (attack.equalsIgnoreCase("S")) {

                hp2 = second.hp() - first.attack2();
                return hp2;
            }


        } else if (userNameNumber == 2) {

            if (attack.equalsIgnoreCase("B")) {

                hp3 = first.hp() - second.attack1();
                return hp3;

            } else if (attack.equalsIgnoreCase("S")) {

                hp4 = first.hp() - second.attack2();
                return hp4;
            }
        }
        return 0;
    }

    public static void printDecks(List<?> deck1, List<?> deck2) {

        for (int i = 0; i < deck2.size(); i++) {

            int k = i + 1;
            int j = i + 7;

            System.out.println("(" + k + ")" + deck1.get(i) + " ".repeat(50) + "(" + j + ")" + deck2.get(i));
        }
    }
}
