package com.projetojpa.dto;

public record PetDto(Long id, String nome, String documento, 
		String nascimento, String cuidador) {

}
