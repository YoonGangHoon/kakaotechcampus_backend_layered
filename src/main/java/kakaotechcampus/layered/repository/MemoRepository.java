package kakaotechcampus.layered.repository;

import kakaotechcampus.layered.dto.MemoResponseDto;
import kakaotechcampus.layered.entity.Memo;

import java.util.List;

public interface MemoRepository {

    Memo saveMemo(Memo memo);

    List<MemoResponseDto> findAllMemos();

    Memo findMemoById(Long id);

    void deleteMemo(Long id);
}
