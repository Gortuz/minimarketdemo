package minimarketdemo.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the pkm_tipo database table.
 * 
 */
@Entity
@Table(name="pkm_tipo")
@NamedQuery(name="PkmTipo.findAll", query="SELECT p FROM PkmTipo p")
public class PkmTipo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
//	@SequenceGenerator(name="PKM_TIPO_IDTIPO_GENERATOR", sequenceName="PKM_TIPO_ID_TIPO_SEQ")
//	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PKM_TIPO_IDTIPO_GENERATOR")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_tipo", unique=true, nullable=false)
	private Integer idTipo;

	@Column(name="nombre_tipo", nullable=false, length=50)
	private String nombreTipo;

	//bi-directional many-to-one association to PkmPokemon
	@OneToMany(mappedBy="pkmTipo1")
	private List<PkmPokemon> pkmPokemons1;

	//bi-directional many-to-one association to PkmPokemon
	@OneToMany(mappedBy="pkmTipo2")
	private List<PkmPokemon> pkmPokemons2;

	public PkmTipo() {
	}

	public Integer getIdTipo() {
		return this.idTipo;
	}

	public void setIdTipo(Integer idTipo) {
		this.idTipo = idTipo;
	}

	public String getNombreTipo() {
		return this.nombreTipo;
	}

	public void setNombreTipo(String nombreTipo) {
		this.nombreTipo = nombreTipo;
	}

	public List<PkmPokemon> getPkmPokemons1() {
		return this.pkmPokemons1;
	}

	public void setPkmPokemons1(List<PkmPokemon> pkmPokemons1) {
		this.pkmPokemons1 = pkmPokemons1;
	}

	public PkmPokemon addPkmPokemons1(PkmPokemon pkmPokemons1) {
		getPkmPokemons1().add(pkmPokemons1);
		pkmPokemons1.setPkmTipo1(this);

		return pkmPokemons1;
	}

	public PkmPokemon removePkmPokemons1(PkmPokemon pkmPokemons1) {
		getPkmPokemons1().remove(pkmPokemons1);
		pkmPokemons1.setPkmTipo1(null);

		return pkmPokemons1;
	}

	public List<PkmPokemon> getPkmPokemons2() {
		return this.pkmPokemons2;
	}

	public void setPkmPokemons2(List<PkmPokemon> pkmPokemons2) {
		this.pkmPokemons2 = pkmPokemons2;
	}

	public PkmPokemon addPkmPokemons2(PkmPokemon pkmPokemons2) {
		getPkmPokemons2().add(pkmPokemons2);
		pkmPokemons2.setPkmTipo2(this);

		return pkmPokemons2;
	}

	public PkmPokemon removePkmPokemons2(PkmPokemon pkmPokemons2) {
		getPkmPokemons2().remove(pkmPokemons2);
		pkmPokemons2.setPkmTipo2(null);

		return pkmPokemons2;
	}

}