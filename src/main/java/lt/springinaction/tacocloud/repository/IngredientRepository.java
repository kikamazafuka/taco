package lt.springinaction.tacocloud.repository;

import lt.springinaction.tacocloud.tacos.Ingredient;

import java.util.Optional;

public interface IngredientRepository {

    Iterable<Ingredient> findAll();
    Optional<Ingredient> findById(int id);
    void save(Ingredient ingredient);
}
