package io.houchens.apiWithKotlin.controller

import io.houchens.apiWithKotlin.exception.ResourceNotFoundException
import io.houchens.apiWithKotlin.model.Answer
import io.houchens.apiWithKotlin.repository.AnswerRepository
import io.houchens.apiWithKotlin.repository.QuestionRepository
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
class AnswerController(
    private val answerRepository: AnswerRepository,
    private val questionRepository: QuestionRepository
) {

    @GetMapping("/questions/{questionId}/answers")
    fun getAnswerByQuestionId(@PathVariable questionId: Long): List<Answer> =
        answerRepository.findByQuestionId(questionId)

    @PostMapping("/questions/{questionId}/answers")
    fun addAnswer(@PathVariable questionId: Long, @Valid @RequestBody answer: Answer): Answer {
        return questionRepository.findById(questionId)
            .map {
                answer.question = it
                answerRepository.save(answer)
            }.orElseThrow { ResourceNotFoundException("Could not find question.") }
    }

    @PutMapping("/questions/{questionId}/answers/{answerId}")
    fun updateAnswer(
        @PathVariable questionId: Long, @PathVariable answerId: Long, @Valid @RequestBody answerRequest: Answer
    ): Answer {
        if (!questionRepository.existsById(questionId)) throw ResourceNotFoundException("Could not find question $questionId")
        return answerRepository.findById(answerId)
            .map {
                it.text = answerRequest.text
                answerRepository.save(it)
            }.orElseThrow { ResourceNotFoundException("Could not find answer") }
    }

    @DeleteMapping("/questions/{questionId}/answers/{answerId}")
    fun deleteAnswer(
        @PathVariable questionId: Long, @PathVariable answerId: Long
    ): ResponseEntity<Void> {
        if (!questionRepository.existsById(questionId)) throw ResourceNotFoundException("Question not found with id $questionId")

        return answerRepository.findById(answerId)
            .map {
                answerRepository.delete(it)
                ResponseEntity<Void>(HttpStatus.OK)
            }
            .orElseThrow { ResourceNotFoundException("Answer not found with id $answerId") }
    }
}