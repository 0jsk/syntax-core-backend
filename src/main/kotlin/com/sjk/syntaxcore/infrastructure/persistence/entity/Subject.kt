package com.sjk.syntaxcore.infrastructure.persistence.entity

import jakarta.persistence.*
import org.hibernate.Hibernate

/**
 * The `Subject` class represents a subject in the system. It has information about the subject's name and the groups it is associated with.
 * The subject is used to organize groups by the topics they are covering.
 *
 * @property id The unique identifier for this subject.
 * @property name The name of this subject.
 */
@Entity
@Table(name = "subject")
data class Subject(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column(nullable = false)
    val name: String,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as Subject

        return id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id, name = $name)"
    }
}
