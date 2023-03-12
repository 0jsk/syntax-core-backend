package com.sjk.syntaxcore.infrastructure.persistence.entity

import jakarta.persistence.*
import org.hibernate.Hibernate

/**
 * The `Group` class represents a group in the system. It has information about the group's name, instructor, and subject.
 * The group is the main entity in the system and is used to organize students and subjects.
 *
 * @property id The unique identifier for this group.
 * @property name The name of this group.
 * @property instructor_id The identifier of the instructors associated with this group.
 * @property subject_id The identifier of the subjects associated with this group.
 */
@Entity
@Table(name = "groups")
data class GroupEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column(nullable = false)
    val name: String,

    @ManyToMany
    @JoinTable(
        name = "groups_subject",
        joinColumns = [JoinColumn(name = "groups_id")],
        inverseJoinColumns = [JoinColumn(name = "subject_id")],
    )
    val subjects: List<SubjectEntity> = listOf(),

    @ManyToMany
    @JoinTable(
        name = "groups_instructor",
        joinColumns = [JoinColumn(name = "groups_id")],
        inverseJoinColumns = [JoinColumn(name = "instructor_id")],
    )
    val instructors: List<InstructorEntity> = listOf(),

    @ManyToMany
    @JoinTable(
        name = "groups_student",
        joinColumns = [JoinColumn(name = "groups_id")],
        inverseJoinColumns = [JoinColumn(name = "student_id")],
    )
    val students: List<StudentEntity> = listOf(),
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as GroupEntity

        return id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName +
            "(id = $id, name = $name, subjects = $subjects, instructors = $instructors, students = $students)"
    }
}
