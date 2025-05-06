package kakaotechcampus.layered.service;

import kakaotechcampus.layered.dto.MemoRequestDto;
import kakaotechcampus.layered.dto.MemoResponseDto;
import kakaotechcampus.layered.entity.Memo;
import kakaotechcampus.layered.repository.MemoRepository;
import kakaotechcampus.layered.repository.MemoRepositoryImpl;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class MemoServiceImpl implements MemoService {

    private final MemoRepository memoRepository;

    public MemoServiceImpl(MemoRepository repository) {
        this.memoRepository = repository;
    }

    @Override
    public MemoResponseDto saveMemo(MemoRequestDto dto) {

        // 요청 받은 데이터로 메모 객체 생성, ID 값은 X
        Memo memo = new Memo(dto.getTitle(), dto.getContents());

        // DB 저장
        Memo savedMemo = memoRepository.saveMemo(memo);

        return new MemoResponseDto(savedMemo);
    }

    @Override
    public List<MemoResponseDto> findAllMemos() {

        return memoRepository.findAllMemos();
    }

    @Override
    public MemoResponseDto findMemobyId(Long id) {
        Memo memo = memoRepository.findMemoById(id);
        if (memo == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }

        return new MemoResponseDto(memo);
    }

    @Override
    public MemoResponseDto updateMemo(Long id, String title, String contents) {

        Memo memo = memoRepository.findMemoById(id);
        if (memo == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }

        if (title == null || contents == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "title or contents are required");
        }

        memo.update(title, contents);
        return new MemoResponseDto(memo);
    }

    @Override
    public MemoResponseDto updateTitle(Long id, String title) {

        Memo memo = memoRepository.findMemoById(id);
        if (memo == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }

        if (title == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "title or contents are required");
        }

        memo.updateTitle(title);
        return new MemoResponseDto(memo);
    }

    @Override
    public void deleteMemo(Long id) {
        Memo memo = memoRepository.findMemoById(id);
        if (memo == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }
        memoRepository.deleteMemo(id);
    }
}
