package exercise.service;

import exercise.dto.BookCreateDTO;
import exercise.dto.BookDTO;
import exercise.dto.BookUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.BookMapper;
import exercise.repository.AuthorRepository;
import exercise.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    // BEGIN
    @Autowired
    BookRepository bookRepository;

    @Autowired
    BookMapper bookMapper;

    @Autowired
    AuthorRepository authorRepository;

    public List<BookDTO> getAll() {
        return bookRepository.findAll()
                .stream()
                .map(bookMapper::map)
                .toList();
    }

    public BookDTO create(BookCreateDTO dto) {
        var book = bookMapper.map(dto);
        bookRepository.save(book);
        return bookMapper.map(book);
    }

    public BookDTO findById(Long id) {
        var book = bookRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Not Found: " + id));
        return bookMapper.map(book);
    }

    public BookDTO update(BookUpdateDTO updateDTO, long id) {
        var book = bookRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Not Found: " + id));
        bookMapper.update(updateDTO, book);
        bookRepository.save(book);
        return bookMapper.map(book);
    }

    public void delete(long id) {
        bookRepository.deleteById(id);
    }
    // END
}
