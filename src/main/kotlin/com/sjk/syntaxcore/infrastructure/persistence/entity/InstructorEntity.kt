package com.sjk.syntaxcore.infrastructure.persistence.entity

import jakarta.persistence.*
import org.hibernate.Hibernate

/**
 * The `Instructor` class represents an instructor in the system. It has information about the instructor's name and the groups they are associated with.
 * The instructor is used to associate groups with individuals who will be responsible for grading papers.
 *
 * @property id The unique identifier for this instructor.
 * @property first_name The first name of this instructor.
 * @property last_name The last name of this instructor.
 * @property groups The groups this instructor is teaching.
 */
@Entity
@Table(name = "instructor")
data class InstructorEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @ManyToMany
    @JoinTable(
        name = "instructor_subject",
        joinColumns = [JoinColumn(name = "instructor_id")],
        inverseJoinColumns = [JoinColumn(name = "subject_id")],
    )
    val subjects: List<SubjectEntity> = listOf(),

    @ManyToMany
    @JoinTable(
        name = "instructor_groups",
        joinColumns = [JoinColumn(name = "instructor_id")],
        inverseJoinColumns = [JoinColumn(name = "groups_id")],
    )
    val groups: List<GroupEntity> = listOf(),

    @OneToOne(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val user: UserEntity,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as InstructorEntity

        return id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id, subjects = $subjects, groups = $groups, user = $user)"
    }
}
