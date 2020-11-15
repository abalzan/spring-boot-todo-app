package com.deloitte.todo.bootstrap;

import com.deloitte.todo.domain.Todo;
import com.deloitte.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;

@RequiredArgsConstructor
@Component
public class SetupInitialData implements CommandLineRunner {

    private final TodoRepository todoRepository;

    @Override
    public void run(String... args) throws Exception {

        Collection<Todo> todos = Arrays.asList(
                new Todo("create todo app", "Yes"),
                new Todo("add security", "Yes"),
                new Todo("add some unit tests", "Yes"),
                new Todo("full unit tests coverage", "No"));
        todoRepository.saveAll(todos);
    }
}
