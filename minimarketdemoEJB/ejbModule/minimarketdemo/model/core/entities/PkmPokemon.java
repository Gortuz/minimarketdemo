package minimarketdemo.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the pkm_pokemon database table.
 * 
 */
@Entity
@Table(name="pkm_pokemon")
@NamedQuery(name="PkmPokemon.findAll", query="SELECT p FROM PkmPokemon p")
public class PkmPokemon implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
//	@SequenceGenerator(name="PKM_POKEMON_IDPOKEMON_GENERATOR", sequenceName="PKM_POKEMON_ID_POKEMON_SEQ")
//	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PKM_POKEMON_IDPOKEMON_GENERATOR")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_pokemon", unique=true, nullable=false)
	private Integer idPokemon;

	@Column(nullable=false)
	private Integer ataque;

	@Column(name="ataque_especial", nullable=false)
	private Integer ataqueEspecial;

	@Column(nullable=false)
	private Integer defensa;

	@Column(name="defensa_especial", nullable=false)
	private Integer defensaEspecial;

	@Column(nullable=false)
	private Integer hp;

	@Column(name="nombre_pokemon",unique=true, nullable=false, length=100)
	private String nombrePokemon;

	@Column(nullable=false)
	private Integer velocidad;

	//bi-directional many-to-one association to PkmTipo
	@ManyToOne
	@JoinColumn(name="id_tipo1", nullable=false)
	private PkmTipo pkmTipo1;

	//bi-directional many-to-one association to PkmTipo
	@ManyToOne
	@JoinColumn(name="id_tipo2")
	private PkmTipo pkmTipo2;

	public PkmPokemon() {
	}

	public Integer getIdPokemon() {
		return this.idPokemon;
	}

	public void setIdPokemon(Integer idPokemon) {
		this.idPokemon = idPokemon;
	}

	public Integer getAtaque() {
		return this.ataque;
	}

	public void setAtaque(Integer ataque) {
		this.ataque = ataque;
	}

	public Integer getAtaqueEspecial() {
		return this.ataqueEspecial;
	}

	public void setAtaqueEspecial(Integer ataqueEspecial) {
		this.ataqueEspecial = ataqueEspecial;
	}

	public Integer getDefensa() {
		return this.defensa;
	}

	public void setDefensa(Integer defensa) {
		this.defensa = defensa;
	}

	public Integer getDefensaEspecial() {
		return this.defensaEspecial;
	}

	public void setDefensaEspecial(Integer defensaEspecial) {
		this.defensaEspecial = defensaEspecial;
	}

	public Integer getHp() {
		return this.hp;
	}

	public void setHp(Integer hp) {
		this.hp = hp;
	}

	public String getNombrePokemon() {
		return this.nombrePokemon;
	}

	public void setNombrePokemon(String nombrePokemon) {
		this.nombrePokemon = nombrePokemon;
	}

	public Integer getVelocidad() {
		return this.velocidad;
	}

	public void setVelocidad(Integer velocidad) {
		this.velocidad = velocidad;
	}

	public PkmTipo getPkmTipo1() {
		return this.pkmTipo1;
	}

	public void setPkmTipo1(PkmTipo pkmTipo1) {
		this.pkmTipo1 = pkmTipo1;
	}

	public PkmTipo getPkmTipo2() {
		return this.pkmTipo2;
	}

	public void setPkmTipo2(PkmTipo pkmTipo2) {
		this.pkmTipo2 = pkmTipo2;
	}

}