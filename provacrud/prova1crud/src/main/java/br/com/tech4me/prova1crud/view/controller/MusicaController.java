package br.com.tech4me.prova1crud.view.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tech4me.prova1crud.model.Musica;
import br.com.tech4me.prova1crud.service.MusicaService;
import br.com.tech4me.prova1crud.shared.MusicaDto;
import br.com.tech4me.prova1crud.view.model.MusicaRequest;
import br.com.tech4me.prova1crud.view.model.MusicaResponse;

@RequestMapping("/api/musicas")
@RestController
public class MusicaController {

    @Autowired
    private MusicaService servico;
    ModelMapper mapper = new ModelMapper();


    @GetMapping
    public ResponseEntity <List<MusicaResponse>> obterMusicas() {
        
        List <MusicaDto> musicasDto = servico.obterMusicas();
        List <MusicaResponse> produtosResponse = musicasDto.stream()
        .map(p -> mapper.map(musicasDto, MusicaResponse.class))
        .collect(Collectors.toList());
        return new ResponseEntity<>(produtosResponse, HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<MusicaResponse> criarMusica (@RequestBody MusicaRequest musicarequest){
        MusicaDto musicasdto = mapper.map(musicarequest, MusicaDto.class);
        musicasdto = servico.criarMusica(musicasdto);
        MusicaResponse musicasResponse = mapper.map(musicasdto, MusicaResponse.class);
        return new ResponseEntity<>(musicasResponse, HttpStatus.CREATED);

    }
    
    @GetMapping(value = "/{id}")
    public ResponseEntity <MusicaResponse> obterMusicaPorId(@PathVariable String id){

        Optional<MusicaDto> musicasDto = servico.ObterMusicaPorId(id);

        if(musicasDto.isPresent()) {
            MusicaResponse response = mapper.map(musicasDto.get(), MusicaResponse.class);
            return new ResponseEntity<>(response, HttpStatus.FOUND);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    

    @PutMapping(value = "/{id}")
    public ResponseEntity <MusicaResponse> atualizarMusica(@PathVariable String id,@RequestBody MusicaRequest musicaRequest){

        MusicaDto musicasDto = mapper.map(musicaRequest, MusicaDto.class);
        musicasDto = servico.atualizarMusica(id, musicasDto);
        MusicaResponse response = mapper.map(musicasDto, MusicaResponse.class);
        return new ResponseEntity<>(response, HttpStatus.OK);
     }

    @DeleteMapping(value = "/{id}")
        public ResponseEntity <String> removerMusicaPorId(@PathVariable String id){
            servico.removerMusicaPorId(id);
            return new ResponseEntity<String>("Removido com sucesso!", HttpStatus.OK);
        } 

    
    @GetMapping (value = "/contagem")
    public ResponseEntity <Long> totalMusicas(){
        return new ResponseEntity<Long>(servico.totalMusicas(), HttpStatus.OK);

    }
    

 }
