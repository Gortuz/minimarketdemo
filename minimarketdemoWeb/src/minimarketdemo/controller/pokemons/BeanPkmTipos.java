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
import minimarketdemo.model.core.entities.PkmTipo;
import minimarketdemo.model.pkm.managers.ManagerTipos;


@Named
@SessionScoped
public class BeanPkmTipos implements Serializable {
	@Inject
	private BeanSegLogin beanSeagLogin;
	@EJB
	private ManagerTipos managerTipos;
	
	private PkmTipo nuevoTipo, elegidoTipo;
	private List<PkmTipo> listaTipos;
	
	public BeanPkmTipos() {
		
	}
	
	@PostConstruct
	public void inicializar() {
		listaTipos = managerTipos.findAllTipos();
		nuevoTipo = new PkmTipo();
	}

	public void actionCrearTipo() {
		try {
			managerTipos.crearTipo(nuevoTipo, beanSeagLogin.getLoginDTO());
			JSFUtil.crearMensajeINFO("Tipo creado.");
			nuevoTipo = new PkmTipo();
			listaTipos = managerTipos.findAllTipos();
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void actionActualizarTipo() {
		try {
			managerTipos.actualizarTipo(elegidoTipo, beanSeagLogin.getLoginDTO());
			JSFUtil.crearMensajeINFO("Tipo actualizado.");
			listaTipos = managerTipos.findAllTipos();
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void actionEliminarTipo() {
		try {
			managerTipos.eliminarTipo(elegidoTipo, beanSeagLogin.getLoginDTO());
			JSFUtil.crearMensajeINFO("Tipo eliminado.");
			listaTipos = managerTipos.findAllTipos();
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public PkmTipo getNuevoTipo() {
		return nuevoTipo;
	}

	public void setNuevoTipo(PkmTipo nuevoTipo) {
		this.nuevoTipo = nuevoTipo;
	}

	public PkmTipo getElegidoTipo() {
		return elegidoTipo;
	}

	public void setElegidoTipo(PkmTipo elegidoTipo) {
		this.elegidoTipo = elegidoTipo;
	}

	public List<PkmTipo> getListaTipos() {
		return listaTipos;
	}

	public void setListaTipos(List<PkmTipo> listaTipos) {
		this.listaTipos = listaTipos;
	}

	public String actionMenuPokemon() {
		return "pokemon?faces-redirect=true";
	}
	
	public String actionMenuTiposPokemon() {
		listaTipos = managerTipos.findAllTipos();
		return "pkmTipos?faces-redirect=true";
	}
	
	
}
