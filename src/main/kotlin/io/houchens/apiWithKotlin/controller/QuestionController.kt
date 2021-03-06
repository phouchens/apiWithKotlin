package io.houchens.apiWithKotlin.controller

import io.houchens.apiWithKotlin.exception.ResourceNotFoundException
import io.houchens.apiWithKotlin.model.Question
import io.houchens.apiWithKotlin.repository.QuestionRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
class QuestionController(private val questionRepository: QuestionRepository) {

    @GetMapping("/questions")
    fun getQuestions(pageable: Pageable): Page<Question> = questionRepository.findAll(pageable)

    @PostMapping("/questions")
    fun createQuestion(@Valid @RequestBody question: Question): Question = questionRepository.save(question)

    @PutMapping("/questions/{questionId}")
    fun updateQuestion(
        @PathVariable questionId: Long, @Valid @RequestBody questionRequest: Question
    ): Question {
        return questionRepository.findById(questionId)
            .map {
                it.title = questionRequest.title
                it.description = questionRequest.description
                questionRepository.save(it)
            }
            .orElseThrow { ResourceNotFoundException("Question Not Found with question id: $questionId") }
    }

    @DeleteMapping("/questions/{questionId}")
    fun deleteQuestion(@PathVariable questionId: Long): ResponseEntity<Void> {
        return questionRepository.findById(questionId)
            .map {
                questionRepository.delete(it)
                ResponseEntity<Void>(HttpStatus.OK)
            }
            .orElseThrow { ResourceNotFoundException("Question Not Found with question id: $questionId") }
    }
}
