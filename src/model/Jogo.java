package model;

import java.time.LocalDateTime;

public class Jogo {
	private Campeonato campeonato;
	private Time timeMandante;
	private Time timeVisitante;
	private LocalDateTime dataJogo;
	private String estadio;
	
	public Jogo(Campeonato campeonato, Time timeMandante, Time timeVisitante, LocalDateTime dataJogo, String estadio) {
		this.campeonato = campeonato;
		this.timeMandante = timeMandante;
		this.timeVisitante = timeVisitante;
		this.dataJogo = dataJogo;
		this.estadio = estadio;
	}

	public Time getTimeMandante() {
		return timeMandante;
	}

	public void setTimeMandante(Time timeMandante) {
		this.timeMandante = timeMandante;
	}

	public Time getTimeVisitante() {
		return timeVisitante;
	}

	public void setTimeVisitante(Time timeVisitante) {
		this.timeVisitante = timeVisitante;
	}

	public LocalDateTime getDataJogo() {
		return dataJogo;
	}

	public void setDataJogo(LocalDateTime dataJogo) {
		this.dataJogo = dataJogo;
	}

	public String getEstadio() {
		return estadio;
	}

	public void setEstadio(String estadio) {
		this.estadio = estadio;
	}
	
	
	@Override
	public String toString() {
	    return String.format("%s vs %s - Data: %s, Estádio: %s",
	            timeMandante.getNome(),
	            timeVisitante.getNome(),
	            dataJogo,
	            estadio);
	}
}
