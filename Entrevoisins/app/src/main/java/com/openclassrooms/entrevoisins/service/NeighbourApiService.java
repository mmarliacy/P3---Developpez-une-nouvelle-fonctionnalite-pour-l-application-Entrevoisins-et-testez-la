package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.List;


/**
 * Neighbour API client
 */
public interface NeighbourApiService {

    /**
     * Get all my Neighbours
     * @return {@link List}
     */
    List<Neighbour> getNeighbours();

    List<Neighbour> getFavoriteNeighbours();

    /**
     * Get position of a Neighbour.
     * @return {position}
     */
    Neighbour getPositionOfNeighbour(int position);

    Neighbour getPositionOfFavoriteNeighbour(int position);

    /**
     * Deletes a neighbour
     * @param neighbour
     */
    void deleteNeighbour(Neighbour neighbour);

    void deleteFavoriteNeighbour(Neighbour neighbour);

    /**
     * Create a neighbour
     * @param neighbour
     */
    void createNeighbour(Neighbour neighbour);

    void createFavoriteNeighbour(Neighbour neighbour);
}


