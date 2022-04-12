package br.com.tech4me.prova1crud.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.tech4me.prova1crud.model.Musica;

public interface MusicaRepository extends MongoRepository<Musica , String> {

    
}
