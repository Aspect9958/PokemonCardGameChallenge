package dev.lpa;

import java.util.Random;

public enum Pokemon {

    CHARIZARD, GENGAR, BULBASAUR, EEVEE, ARCANINE, MEWTWO, PIKACHU, RAYQUAZA, DRAGONITE,
    SNORLAX, TYRANITAR, GRENINJA, MEW, SQUIRTLE, PSYDUCK, VENASAUR, CHARMANDER;


    public static Pokemon getRandomPokemon() {

        Random random = new Random();
        Pokemon[] pokemons = Pokemon.values();

        return pokemons[random.nextInt(0, 17)];
    }

    public static pokemonCards getPokemonValues(Pokemon pokemon) {

        Pokemon pokemon1 = getRandomPokemon();
        if (pokemon.equals(CHARIZARD)) {
            return new pokemonCards("Charizard", 280, 130, 320);
        } else if (pokemon.equals(GENGAR)) {
            return new pokemonCards("Gengar", 130, 50, 110);
        } else if (pokemon.equals(BULBASAUR)) {
            return new pokemonCards("Bulbasaur", 70, 10, 20);
        } else if (pokemon.equals(EEVEE)) {
            return new pokemonCards("Evee", 70, 10, 20);
        } else if (pokemon.equals(ARCANINE)) {
            return new pokemonCards("Arcanine", 130, 10, 150);
        } else if (pokemon.equals(MEWTWO)) {
            return new pokemonCards("Mewtwo", 280, 50, 160);
        } else if (pokemon.equals(PIKACHU)) {
            return new pokemonCards("Pikachu", 190, 50, 150);
        } else if (pokemon.equals(RAYQUAZA)) {
            return new pokemonCards("Rayquaza", 230, 40, 270);
        } else if (pokemon.equals(DRAGONITE)) {
            return new pokemonCards("Dragonite", 230, 60, 160);
        } else if (pokemon.equals(SNORLAX)) {
            return new pokemonCards("Snorlax", 130, 60, 120);
        } else if (pokemon.equals(TYRANITAR)) {
            return new pokemonCards("Tryanitar", 180, 40, 230);
        } else if (pokemon.equals(GRENINJA)) {
            return new pokemonCards("Greninja", 230, 50, 110);
        } else if (pokemon.equals(MEW)) {
            return new pokemonCards("Mew", 250, 50, 140);
        } else if (pokemon.equals(SQUIRTLE)) {
            return new pokemonCards("Squirtle", 70, 10, 20);
        } else if (pokemon.equals(PSYDUCK)) {
            return new pokemonCards("Psyduck", 70, 10, 20);
        } else if (pokemon.equals(VENASAUR)) {
            return new pokemonCards("Venasaur", 220, 80, 220);
        } else if (pokemon.equals(CHARMANDER)) {
            return new pokemonCards("Charmander", 50, 10, 30);
        }
        return null;
    }

}

