package com.deloitte.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.todo.domain.Todo;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

}
