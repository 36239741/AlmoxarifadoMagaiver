package com.br.almoxarifado.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.almoxarifado.entity.Telefone;

@Repository
public interface TelefoneRepository extends JpaRepository<Telefone, Long	> {

}
