package br.com.tech4me.prova1crud.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tech4me.prova1crud.model.Musica;
import br.com.tech4me.prova1crud.repository.MusicaRepository;
import br.com.tech4me.prova1crud.shared.MusicaDto;

@Service
public class MusicaServiceImpl implements MusicaService {

    @Autowired
    private MusicaRepository repositorio;
    ModelMapper mapper = new ModelMapper();

    @Override
    public MusicaDto criarMusica(MusicaDto musicaDto) {

        Musica musica = mapper.map(musicaDto, Musica.class);

        musica =  repositorio.save(musica);

        MusicaDto musicadto = mapper.map(musica, MusicaDto.class);

        return musicadto;

    }

    @Override
    public List<MusicaDto> obterMusicas() {
        List<Musica> musicas = repositorio.findAll();

        List<MusicaDto> musicasdto = musicas.stream()
        .map(p -> mapper.map(p, MusicaDto.class))
        .collect(Collectors.toList());

        return musicasdto;

    }

    @Override
    public Optional <MusicaDto> ObterMusicaPorId(String id) {

        Optional <Musica> musicas = repositorio.findById(id);

        if(musicas.isPresent()){

            MusicaDto musicasdto = mapper.map(musicas.get(), MusicaDto.class);
            return Optional.of(musicasdto);
        }
            return Optional.empty();
    }

    @Override
    public MusicaDto atualizarMusica(String id, MusicaDto musicadto){

        Musica musica = mapper.map(musicadto, Musica.class);

        musica.setId(id);

        musica = repositorio.save(musica);

        MusicaDto dto = mapper.map(musica, MusicaDto.class);

        return dto;
            
    }

    @Override
    public String removerMusicaPorId(String id) {
            repositorio.deleteById(id);
            return String.format("MUSICA DELETADA COM SUCESSO");
        
    }

    @Override
    public void removerTodasAsMusicas(String id) {
            repositorio.deleteAll();
        
    }

    @Override
    public Long totalMusicas() {
        return repositorio.count();
    }


        
        
    }
    

