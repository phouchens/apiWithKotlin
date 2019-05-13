package io.houchens.apiWithKotlin.Model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.SequenceGenerator
import javax.persistence.Table
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Entity
@Table(name = "question")
data class Question(
    @Id
    @GeneratedValue(generator = "question_generator")
    @SequenceGenerator(name = "question_generator", sequenceName = "question_sequence", initialValue = 1000)
    private var id: Long? = null,

    @NotBlank
    @Size(min = 3, max = 100)
    var title: String? = null,

    @Column(columnDefinition = "text")
    var description: String? = null
) : AuditModel()