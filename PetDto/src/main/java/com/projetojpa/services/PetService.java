package com.projetojpa.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetojpa.dto.PetDto;
import com.projetojpa.entities.Pet;
import com.projetojpa.repository.PetRepository;
@Service
public class PetService {
	
	private final PetRepository petRepository;
	
	@Autowired
	public PetService(PetRepository petRepository) {
		this.petRepository = petRepository;
	}
	
	public PetDto salvar(PetDto petDto) {
		Pet pet = new Pet(petDto.nome(), petDto.documento(), petDto.nascimento());
		Pet salvarPet = petRepository.save(pet);
		return new PetDto(salvarPet.getId(), salvarPet.getNome(), salvarPet.getDocumento(), salvarPet.getNascimento(), salvarPet.getCuidador());
		
	}
	
	public PetDto atualizar(Long id, PetDto petDto) {
		Pet existePet = petRepository.findById(id).orElseThrow(() -> new RuntimeException("Pet não encontrado"));
		
		existePet.setNome(petDto.nome());
		existePet.setDocumento(petDto.documento());
		existePet.setNascimento(petDto.nascimento());
		existePet.setCuidador(petDto.cuidador());
		
		
		Pet updatePet = petRepository.save(existePet);
		return new PetDto(updatePet.getId(), updatePet.getNome(), updatePet.getDocumento(), updatePet.getNascimento(), updatePet.getCuidador());
		
	}
	
	public boolean deletar(Long id) {
		Optional <Pet> existePet = petRepository.findById(id);
		if (existePet.isPresent()) {
			petRepository.deleteById(id);
			return true;
		}
		return false;
	}
	
	public List<Pet> buscarTodos() {
		return petRepository.findAll();
		}
	public Pet buscarPorId(Long id) {
		Optional <Pet> pet = petRepository.findById(id);
		return pet.orElse(null);
	}
}