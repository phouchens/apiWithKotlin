package io.houchens.apiWithKotlin.repository

import io.houchens.apiWithKotlin.Model.Question
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface QuestionRepository : JpaRepository<Question, Long>