package practica14_262772.example.practica14_262772.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import practica14_262772.example.practica14_262772.model.Pokemon;
import practica14_262772.example.practica14_262772.service.PokemonService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pokemones")
public class PokemonController {

    @Autowired
    private PokemonService pokemonService;

    /**
     * Obtiene todos los pokemones
     *
     * @return la lista de los pokemones
     */
    @GetMapping
    public List<Pokemon> obtenerTodos() {
        return pokemonService.obtenerTodos();
    }

    /**
     * Obtiene un pokemon por ID
     *
     * @param id
     *
     * @return el pokemon
     */
    @GetMapping("/{id}")
    public Optional<Pokemon> obtenerPorId(@PathVariable Long id) {
        return pokemonService.obtenerPorId(id);
    }

    /**
     * Crea un pokemon y lo guarda
     *
     * @param pokemon a guardar
     *
     * @return el pokemon creado
     */
    @PostMapping
    public Pokemon crear(@Valid @RequestBody Pokemon pokemon) {
        return pokemonService.crear(pokemon);
    }

    /**
     * Actualiza un pokemon por completo
     *
     * @param id
     * @param pokemon
     *
     * @return el pokemon actualizado
     */
    @PutMapping("/{id}")
    public Optional<Pokemon> actualizarCompleto(@PathVariable Long id, @Valid @RequestBody Pokemon pokemon) {
        return pokemonService.actualizarCompleto(id, pokemon);
    }

    /**
     * Actualiza un pokemon parcialmente
     *
     * @param id
     * @param pokemon con solo los atributos que serán actualizados
     *
     * @return el pokemon actualizado
     */
    @PatchMapping("/{id}")
    public Optional<Pokemon> actualizarParcial(@PathVariable Long id, @RequestBody Pokemon pokemon) {
        return pokemonService.actualizarParcial(id, pokemon);
    }

    /**
     * Elimina un pokemon
     *
     * @param id
     *
     * @return el pokemon eliminado
     */
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        pokemonService.eliminar(id);
    }
}