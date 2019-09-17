package com.br.almoxarifado.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "tbl_token")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenGenerate {
	@Id
	@GeneratedValue
	private Long id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User userId;
	private UUID token = UUID.randomUUID();
	@Column(name = "tempo_expiracao")
	private LocalDateTime tempoExpiracao;
	@Column(name = "token_status")
	private Boolean tokenStatus = true;

}
