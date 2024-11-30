package minimarketdemo.model.pkm.managers;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import minimarketdemo.model.auditoria.managers.ManagerAuditoria;
import minimarketdemo.model.core.entities.PkmPokemon;
import minimarketdemo.model.core.entities.PkmTipo;
import minimarketdemo.model.core.managers.ManagerDAO;
import minimarketdemo.model.seguridades.dtos.LoginDTO;

/**
 * Session Bean implementation class ManagerPokemons
 */
@Stateless
@LocalBean
public class ManagerPokemons {
	@EJB
	private ManagerDAO mDAO;
	
	@PersistenceContext
	private EntityManager em;
	@EJB
	private ManagerAuditoria mAuditoria;
	
    public ManagerPokemons() {
        
    }

    public List<PkmPokemon> findAllPokemons(){
    	return em.createNamedQuery("PkmPokemon.findAll", PkmPokemon.class).getResultList();
    }
    
    public void eliminarPokemon(int idPokemon, LoginDTO loginDTO) throws Exception {
        // Validación del ID del Pokémon
        if (idPokemon <= 0) {
            throw new Exception("El ID del Pokémon no es válido.");
        }

        // Buscar el Pokémon en la base de datos
        PkmPokemon pokemon = (PkmPokemon) mDAO.findById(PkmPokemon.class, idPokemon);
        if (pokemon == null) {
            throw new Exception("El Pokémon con ID " + idPokemon + " no existe.");
        }

        // Llamar al DAO para eliminar el Pokémon
        mDAO.eliminar(PkmPokemon.class, idPokemon);

        // Auditoría del cambio
        mAuditoria.mostrarLog(loginDTO, getClass(), "eliminarPokemon", 
                              "Pokémon " + pokemon.getNombrePokemon() + " eliminado exitosamente.");
    }
    
    public void crearPokemon(PkmPokemon pokemon, LoginDTO loginDTO, int tipo1, int tipo2) throws Exception {
        // Validación de que el objeto Pokémon no sea nulo
        if (pokemon == null) {
            throw new Exception("El Pokémon no puede ser nulo.");
        }

        // Validación del nombre del Pokémon
        if (pokemon.getNombrePokemon() == null || pokemon.getNombrePokemon().isEmpty()) {
            throw new Exception("El nombre del Pokémon no puede estar vacío.");
        }

        // Validación de la vida (HP), debe ser un número positivo
        if (pokemon.getHp() <= 0) {
            throw new Exception("El HP del Pokémon debe ser un valor mayor a 0.");
        }

        // Validación del ataque, debe ser un número positivo
        if (pokemon.getAtaque() <= 0) {
            throw new Exception("El ataque del Pokémon debe ser un valor mayor a 0.");
        }

        // Validación de la defensa, debe ser un número positivo
        if (pokemon.getDefensa() <= 0) {
            throw new Exception("La defensa del Pokémon debe ser un valor mayor a 0.");
        }

        // Validación de la velocidad, debe ser un número positivo
        if (pokemon.getVelocidad() <= 0) {
            throw new Exception("La velocidad del Pokémon debe ser un valor mayor a 0.");
        }

        // Validación del ataque especial, debe ser un número positivo
        if (pokemon.getAtaqueEspecial() <= 0) {
            throw new Exception("El ataque especial del Pokémon debe ser un valor mayor a 0.");
        }

        // Validación de la defensa especial, debe ser un número positivo
        if (pokemon.getDefensaEspecial() <= 0) {
            throw new Exception("La defensa especial del Pokémon debe ser un valor mayor a 0.");
        }

        
     // Validación del tipo de Pokémon (si se requiere)
        if (tipo1 < 1) {
            throw new Exception("El Pokémon debe tener al menos un tipo.");
        }
        
        if (tipo1 == tipo2) {
            throw new Exception("El Pokémon no puede tener 2 tipos iguales.");
        }
        
        
        PkmTipo Ptipo1, Ptipo2;
        Ptipo1 = new PkmTipo();
        Ptipo1.setIdTipo(tipo1);
        pokemon.setPkmTipo1(Ptipo1);
        
        if(tipo2 > 0) {
        	Ptipo2 = new PkmTipo();
        	Ptipo2.setIdTipo(tipo2);
            pokemon.setPkmTipo2(Ptipo2);

        }
        
        
        // Llamar al DAO para insertar el nuevo Pokémon
        mDAO.insertar(pokemon);

        // Auditoría del cambio
        mAuditoria.mostrarLog(loginDTO, getClass(), "crearPokemon", 
                              "Pokémon " + pokemon.getNombrePokemon() + " creado exitosamente.");
    }

    public void actualizarPokemon(PkmPokemon pokemon, LoginDTO loginDTO, int tipo1, int tipo2) throws Exception {
    	// Validación de que el objeto Pokémon no sea nulo
        if (pokemon == null) {
            throw new Exception("El Pokémon no puede ser nulo.");
        }

        // Validación del nombre del Pokémon
        if (pokemon.getNombrePokemon() == null || pokemon.getNombrePokemon().isEmpty()) {
            throw new Exception("El nombre del Pokémon no puede estar vacío.");
        }

        // Validación de la vida (HP), debe ser un número positivo
        if (pokemon.getHp() <= 0) {
            throw new Exception("El HP del Pokémon debe ser un valor mayor a 0.");
        }

        // Validación del ataque, debe ser un número positivo
        if (pokemon.getAtaque() <= 0) {
            throw new Exception("El ataque del Pokémon debe ser un valor mayor a 0.");
        }

        // Validación de la defensa, debe ser un número positivo
        if (pokemon.getDefensa() <= 0) {
            throw new Exception("La defensa del Pokémon debe ser un valor mayor a 0.");
        }

        // Validación de la velocidad, debe ser un número positivo
        if (pokemon.getVelocidad() <= 0) {
            throw new Exception("La velocidad del Pokémon debe ser un valor mayor a 0.");
        }

        // Validación del ataque especial, debe ser un número positivo
        if (pokemon.getAtaqueEspecial() <= 0) {
            throw new Exception("El ataque especial del Pokémon debe ser un valor mayor a 0.");
        }

        // Validación de la defensa especial, debe ser un número positivo
        if (pokemon.getDefensaEspecial() <= 0) {
            throw new Exception("La defensa especial del Pokémon debe ser un valor mayor a 0.");
        }

        
     // Validación del tipo de Pokémon (si se requiere)
        if (tipo1 < 1) {
            throw new Exception("El Pokémon debe tener al menos un tipo.");
        }
        
        if (tipo1 == tipo2) {
            throw new Exception("El Pokémon no puede tener 2 tipos iguales.");
        }
        
        
        PkmTipo Ptipo1, Ptipo2;
        Ptipo1 = new PkmTipo();
        Ptipo1.setIdTipo(tipo1);
        pokemon.setPkmTipo1(Ptipo1);
        
        if(tipo2 > 0) {
        	Ptipo2 = new PkmTipo();
        	Ptipo2.setIdTipo(tipo2);
            pokemon.setPkmTipo2(Ptipo2);

        }
        

        // Llamar al DAO para actualizar el Pokémon
        mDAO.actualizar(pokemon);
        
        // Auditoría del cambio
        mAuditoria.mostrarLog(loginDTO, getClass(), "actualizarPokemon", 
                              "Pokémon " + pokemon.getNombrePokemon() + " actualizado exitosamente.");
    }

}
