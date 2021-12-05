package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements  NeighbourApiService {

    private final List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();
    private final List<Neighbour> favoritesNeighbours = new ArrayList<>(Collections.emptyList());

    /**
     * Get all my Neighbours
     * @return {@link List}
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }

    @Override
    public List<Neighbour> getFavoriteNeighbours() {
        return favoritesNeighbours;
    }

    /**
     * Get position of a Neighbour.
     * @return {position}
     */
    @Override
    public Neighbour getPositionOfNeighbour(int position) {
        return neighbours.get(position);
    }

    @Override
    public Neighbour getPositionOfFavoriteNeighbour(int position) {
        return favoritesNeighbours.get(position);
    }

    /**
     * Deletes a neighbour
     * @param neighbour
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
    }

    @Override
    public void deleteFavoriteNeighbour(Neighbour neighbour) {
        favoritesNeighbours.remove(neighbour);
    }

    /**
     * Create a neighbour
     * @param neighbour
     */
    @Override
    public void createNeighbour(Neighbour neighbour) {
        neighbours.add(neighbour);
    }

    @Override
    public void createFavoriteNeighbour(Neighbour neighbour) {
        favoritesNeighbours.add(neighbour);

    }

}
