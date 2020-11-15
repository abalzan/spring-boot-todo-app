package com.deloitte.todo.service;

import com.deloitte.todo.domain.Todo;
import com.deloitte.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TodoService {
    private final TodoRepository todoRepository;

    public List<Todo> findAll() {
        return todoRepository.findAll();
    }

    public void save(Todo todo) {
        todoRepository.save(todo);
    }

    public void deleteById(long id) {
        todoRepository.deleteById(id);
    }

    public Todo findById(long id) {
        return todoRepository.findById(id).orElse(new Todo());
    }
}
