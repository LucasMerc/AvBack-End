package br.com.tech4me.prova1crud.service;

import java.util.List;
import java.util.Optional;

import br.com.tech4me.prova1crud.model.Musica;
import br.com.tech4me.prova1crud.shared.MusicaDto;

public interface MusicaService {

    MusicaDto criarMusica(MusicaDto musica);
    List<MusicaDto> obterMusicas();
    Optional <MusicaDto> ObterMusicaPorId(String id);
    MusicaDto atualizarMusica(String id, MusicaDto musica);
    String removerMusicaPorId(String id);
    void removerTodasAsMusicas(String id);
    Long totalMusicas();
}
