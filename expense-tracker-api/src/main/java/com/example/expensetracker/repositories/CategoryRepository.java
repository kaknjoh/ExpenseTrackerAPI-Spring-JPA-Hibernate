package com.example.expensetracker.repositories;

import com.example.expensetracker.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Integer>, CrudRepository<Category, Integer> {

    Optional<Category> findById(Integer id);

    List<Category> findAll();

    @Override
    void deleteById(Integer integer);

    @Query(
            value="SELECT " +
                    "c.title as categoryName , SUM(t.amount) as SpentByCategory  from categories c " +
                    "JOIN transactions t ON c.category_id=t.category_id where  t.user_id=?1 group by c.title ",
            nativeQuery = true)
    List<Object[]> GetUserSpentsByCategory(Integer userId);






}
