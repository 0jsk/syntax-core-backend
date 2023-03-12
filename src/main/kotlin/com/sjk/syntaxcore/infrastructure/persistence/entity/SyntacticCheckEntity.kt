package com.sjk.syntaxcore.infrastructure.persistence.entity

import jakarta.persistence.*
import org.hibernate.Hibernate

/**
 * The `SyntacticCheck` class represents a syntactic check in the system. It has information about the score of the syntactic check and the file it was performed on.
 * The syntactic check is used to evaluate the syntax of papers submitted by students.
 *
 * @property id The unique identifier for this evaluation.
 * @property file The file associated with this evaluation.
 * @property score The score obtained from the evaluation.
 * @property criteria The criteria used to determine the score.
 */
@Entity
@Table(name = "syntactic_check")
data class SyntacticCheckEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column(nullable = false)
    val criteria: String,

    @Column(nullable = false)
    val score: Double,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as SyntacticCheckEntity

        return id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id, criteria = $criteria, score = $score)"
    }
}
