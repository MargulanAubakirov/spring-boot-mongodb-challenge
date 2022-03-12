package com.example.springbootmongodbchallenge.service;

import com.example.springbootmongodbchallenge.model.Expense;
import com.example.springbootmongodbchallenge.repository.ExpenseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    public void addExpense(Expense expense){
        expenseRepository.insert(expense);
    }

    public void updateExpense(Expense expense){
        Expense savedExpense = expenseRepository.findById(expense.getId())
                .orElseThrow(()->new RuntimeException(String.format("Cannot Find Expense by ID %s", expense.getId())));
        savedExpense.setExpenseName(expense.getExpenseName());
        savedExpense.setExpenseCategory(expense.getExpenseCategory());
        savedExpense.setExpenseAmount(expense.getExpenseAmount());
        expenseRepository.save(savedExpense);
    }

    public List<Expense> getAllExpense(){
        return expenseRepository.findAll();
    }

    public Expense getExpenseByName(String name){
        return expenseRepository.findExpenseByName(name)
                .orElseThrow(()->new RuntimeException(String.format("Cannot Find Expense by Name %s", name)));
    }

    public void deleteExpense(String id){
        expenseRepository.deleteById(id);
    }

}
