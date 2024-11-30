package minimarketdemo.controller.pokemons;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import minimarketdemo.controller.JSFUtil;
import minimarketdemo.controller.seguridades.BeanSegLogin;
import minimarketdemo.model.core.entities.PkmPokemon;
import minimarketdemo.model.core.entities.PkmTipo;
import minimarketdemo.model.core.entities.SegUsuario;
import minimarketdemo.model.pkm.managers.ManagerPokemons;
import minimarketdemo.model.pkm.managers.ManagerTipos;

@Named
@SessionScoped
public class BeanPkmPokemons implements Serializable {
	@Inject
	private BeanSegLogin beanSeagLogin;
	@EJB
	private ManagerPokemons mPokemons;
	@EJB
	private ManagerTipos mPkmTipos;
	
	private List<PkmPokemon> listaPokemons;
	
	private PkmPokemon nuevoPokemon;
	private PkmPokemon eleccionPokemon;
	private List<PkmTipo> listaPkmTipos;
	
	private int tipo1, tipo2;
	
	public BeanPkmPokemons() {
		
	}
	
	@PostConstruct
	public void inicializar() {
		listaPokemons = mPokemons.findAllPokemons();
	}

	public void actionListenerActualizarPokemon() {
		try {
			mPokemons.actualizarPokemon(eleccionPokemon, beanSeagLogin.getLoginDTO(), tipo1, tipo2);
			JSFUtil.crearMensajeINFO("Pokémon actualizado.");
			listaPokemons = mPokemons.findAllPokemons();
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void actionListenerCrearPokemon() {
		try {
			mPokemons.crearPokemon(nuevoPokemon, beanSeagLogin.getLoginDTO(), tipo1, tipo2);
			JSFUtil.crearMensajeINFO("Pokémon creado.");
			listaPokemons = mPokemons.findAllPokemons();
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
		nuevoPokemon = new PkmPokemon();
	}
	
	public void actionListenerEliminarPokemon() {
		try {
			mPokemons.eliminarPokemon(eleccionPokemon.getIdPokemon(), beanSeagLogin.getLoginDTO());
			JSFUtil.crearMensajeINFO("Pokémon eliminado.");
			listaPokemons = mPokemons.findAllPokemons();
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
			
		}
	}
	
	public String actionMenuPokemon() {
		listaPokemons = mPokemons.findAllPokemons();
		return "pokemon?faces-redirect=true";
	}
	
	public String actionMenuCrearPokemon() {
		nuevoPokemon = new PkmPokemon();
		listaPkmTipos = mPkmTipos.findAllTipos();
		tipo1 = -1;
		tipo2 = -1;
		
		return "pokemon_nuevo?faces-redirect=true";
	}
	
	public String actionMenuActualizarPokemon() {
		listaPkmTipos = mPkmTipos.findAllTipos();
		tipo1 = eleccionPokemon.getPkmTipo1().getIdTipo();
		tipo2 = -1;
		if (eleccionPokemon.getPkmTipo2() != null) 
			tipo2 = eleccionPokemon.getPkmTipo2().getIdTipo();
		
		return "pokemon_edicion?faces-redirect=true";
	}
	
	public String actionMenuTiposPokemon() {
		listaPkmTipos = mPkmTipos.findAllTipos();
		return "pkmTipos?faces-redirect=true";
	}
	
	public List<PkmPokemon> getListaPokemons() {
		return listaPokemons;
	}

	public void setListaPokemons(List<PkmPokemon> listaPokemons) {
		this.listaPokemons = listaPokemons;
	}
	
	public String getFormattedId(Integer id) {
	    return String.format("%03d", id);
	}

	public PkmPokemon getNuevoPokemon() {
		return nuevoPokemon;
	}

	public void setNuevoPokemon(PkmPokemon nuevoPokemon) {
		this.nuevoPokemon = nuevoPokemon;
	}

	public PkmPokemon getEleccionPokemon() {
		return eleccionPokemon;
	}

	public void setEleccionPokemon(PkmPokemon edicionPokemon) {
		this.eleccionPokemon = edicionPokemon;
	}
	
	
	public List<PkmTipo> getListaPkmTipos() {
		return listaPkmTipos;
	}

	public void setListaPkmTipos(List<PkmTipo> listaPkmTipos) {
		this.listaPkmTipos = listaPkmTipos;
	}

	public int getTipo1() {
		return tipo1;
	}

	public void setTipo1(int tipo1) {
		this.tipo1 = tipo1;
	}

	public int getTipo2() {
		return tipo2;
	}

	public void setTipo2(int tipo2) {
		this.tipo2 = tipo2;
	}

	
	
}
