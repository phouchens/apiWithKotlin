package io.houchens.apiWithKotlin.repository

import io.houchens.apiWithKotlin.model.Answer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AnswerRepository : JpaRepository<Answer, Long> {
    fun findByQuestionId(questionId: Long?): List<Answer>
}