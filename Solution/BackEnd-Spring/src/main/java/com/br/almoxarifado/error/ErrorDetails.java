package com.br.almoxarifado.error;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.FieldError;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Builder
@NoArgsConstructor
@Getter
@AllArgsConstructor
public class ErrorDetails {
	private String title;
	private Integer status;
	private String detail;
	private LocalDateTime timeStemp;
	private String developerMessage;
    private List<String> fields ;
}
