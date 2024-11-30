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
 * Session Bean implementation class ManagerTipos
 */
@Stateless
@LocalBean
public class ManagerTipos {
	
	@EJB
	private ManagerDAO mDAO;
	@EJB
	private ManagerAuditoria mAuditoria;
	
	@PersistenceContext
	private EntityManager em;
    
    public ManagerTipos() {
        
    }

    public List<PkmTipo> findAllTipos(){
    	return em.createNamedQuery("PkmTipo.findAll", PkmTipo.class).getResultList();
    }
    
    public void crearTipo(PkmTipo tipo, LoginDTO loginDTO) throws Exception {
    	if(tipo == null)
    		throw new Exception("Error al crear, tipo Nulo.");
    	
    	if(tipo.getNombreTipo() == null || tipo.getNombreTipo().length() < 3)
    		throw new Exception("Error al crear, Nombre no permitido.");
    	
    	// Llamar al DAO para insertar el nuevo Pokémon
        mDAO.insertar(tipo);

        // Auditoría del cambio
        mAuditoria.mostrarLog(loginDTO, getClass(), "crearPokemon", 
                              "Tipo " + tipo.getNombreTipo() + " creado exitosamente.");
    }
    
    public void actualizarTipo(PkmTipo tipo, LoginDTO loginDTO) throws Exception {
    	if(tipo == null)
    		throw new Exception("Error al crear, tipo Nulo.");
    	
    	if(tipo.getNombreTipo() == null || tipo.getNombreTipo().length() < 3)
    		throw new Exception("Error al crear, Nombre no permitido.");
    	
    	// Llamar al DAO para insertar el nuevo Pokémon
        mDAO.actualizar(tipo);

        // Auditoría del cambio
        mAuditoria.mostrarLog(loginDTO, getClass(), "actualizarTipo", 
                              "Tipo " + tipo.getNombreTipo() + " actualizado exitosamente.");
    }
    
    public void eliminarTipo(PkmTipo tipo, LoginDTO loginDTO) throws Exception {
    	if(tipo == null)
    		throw new Exception("Error al crear, tipo Nulo.");
    	
    	mDAO.eliminar(PkmTipo.class, tipo.getIdTipo());
    	// Auditoría del cambio
        mAuditoria.mostrarLog(loginDTO, getClass(), "eliminarTipo", 
                              "Tipo " + tipo.getNombreTipo() + " eliminado exitosamente.");
    }
}
