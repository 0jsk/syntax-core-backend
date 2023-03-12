package com.sjk.syntaxcore.infrastructure.persistence.entity

import jakarta.persistence.*
import org.hibernate.Hibernate

@Entity
@Table(name = "student")
data class StudentEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @ManyToMany
    @JoinTable(
        name = "student_subject",
        joinColumns = [JoinColumn(name = "student_id")],
        inverseJoinColumns = [JoinColumn(name = "subject_id")],
    )
    val subjects: List<SubjectEntity> = listOf(),

    @ManyToMany
    @JoinTable(
        name = "student_groups",
        joinColumns = [JoinColumn(name = "student_id")],
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
        other as StudentEntity

        return id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id, subjects = $subjects, groups = $groups, user = $user)"
    }
}
