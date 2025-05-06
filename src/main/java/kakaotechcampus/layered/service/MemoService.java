package kakaotechcampus.layered.service;

import kakaotechcampus.layered.dto.MemoRequestDto;
import kakaotechcampus.layered.dto.MemoResponseDto;

import java.util.List;

public interface MemoService {

    MemoResponseDto saveMemo(MemoRequestDto dto);

    List<MemoResponseDto> findAllMemos();

    MemoResponseDto findMemobyId(Long id);

    MemoResponseDto updateMemo(Long id, String title, String contents);

    MemoResponseDto updateTitle(Long id, String title);

    void deleteMemo(Long id);
}
