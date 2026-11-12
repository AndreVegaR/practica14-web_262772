package practica14_262772.example.practica14_262772.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import practica14_262772.example.practica14_262772.model.Pokemon;
import practica14_262772.example.practica14_262772.repository.PokemonRepository;
import java.util.List;
import java.util.Optional;

@Service
public class PokemonService {

    @Autowired
    private PokemonRepository pokemonRepository;

    /**
     * Obtiene todos los pokemones
     *
     * @return la lista de los pokemones
     */
    public List<Pokemon> obtenerTodos() {
        return pokemonRepository.findAll();
    }

    /**
     *
     * Obtiene un pokemon por ID
     *
     * @param id
     *
     * @return el pokemon
     */
    public Optional<Pokemon> obtenerPorId(Long id) {
        return pokemonRepository.findById(id);
    }

    /**
     * Crea un pokemon y lo guarda
     *
     * @param pokemon a guardar
     *
     * @return el pokemon creado
     */
    public Pokemon crear(Pokemon pokemon) {
        return pokemonRepository.save(pokemon);
    }

    /**
     * Actualiza un pokemon por completo
     *
     * @param id
     * @param pokemon
     *
     * @return el pokemon actualizado
     */
    public Optional<Pokemon> actualizarCompleto(Long id, Pokemon pokemon) {
        return pokemonRepository.findById(id).map(pokemonExistente -> {
            pokemonExistente.setNombre(pokemon.getNombre());
            pokemonExistente.setTipo(pokemon.getTipo());
            pokemonExistente.setNivel(pokemon.getNivel());
            pokemonExistente.setAtaque(pokemon.getAtaque());
            pokemonExistente.setDefensa(pokemon.getDefensa());
            pokemonExistente.setDescripcion(pokemon.getDescripcion());
            pokemonExistente.setImagenUrl(pokemon.getImagenUrl());
            return pokemonRepository.save(pokemonExistente);
        });
    }

    /**
     * Actualiza un pokemon parcialmente
     *
     * @param id
     * @param pokemon con solo los atributos que serán actualizados
     *
     * @return el pokemon actualizado
     */
    public Optional<Pokemon> actualizarParcial(Long id, Pokemon pokemon) {
        return pokemonRepository.findById(id).map(pokemonExistente -> {
            if (pokemon.getNombre() != null) {
                pokemonExistente.setNombre(pokemon.getNombre());
            }
            if (pokemon.getTipo() != null) {
                pokemonExistente.setTipo(pokemon.getTipo());
            }
            if (pokemon.getNivel() != null) {
                pokemonExistente.setNivel(pokemon.getNivel());
            }
            if (pokemon.getAtaque() != null) {
                pokemonExistente.setAtaque(pokemon.getAtaque());
            }
            if (pokemon.getDefensa() != null) {
                pokemonExistente.setDefensa(pokemon.getDefensa());
            }
            if (pokemon.getDescripcion() != null) {
                pokemonExistente.setDescripcion(pokemon.getDescripcion());
            }
            if (pokemon.getImagenUrl() != null) {
                pokemonExistente.setImagenUrl(pokemon.getImagenUrl());
            }
            return pokemonRepository.save(pokemonExistente);
        });
    }

    /**
     * Elimina un pokemon
     *
     * @param id
     *
     * @return el pokemon eliminado
     */
    public boolean eliminar(Long id) {
        if (pokemonRepository.existsById(id)) {
            pokemonRepository.deleteById(id);
            return true;
        }
        return false;
    }
}