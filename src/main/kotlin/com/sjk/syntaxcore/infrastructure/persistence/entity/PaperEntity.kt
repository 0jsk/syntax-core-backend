package com.sjk.syntaxcore.infrastructure.persistence.entity

import jakarta.persistence.*
import org.hibernate.Hibernate

/**
 * This entity class is mapped to the `paper` table in the database, and represents a paper which is submitted to the system.
 * It contains information about the paper, such as the title, the abstract, the publication date, and the path to the file.
 *
 * @property id the unique identifier for this paper.
 * @property title the title of the paper.
 * @property abstract the abstract of the paper.
 * @property publicationDate the publication date of the paper.
 * @property filePath the path to the file of the paper.
 * @property group the group of the paper.
 * @property subject the subject of the paper.
 * @property instructor the instructor of the paper.
 * @property student the student who submitted the paper.
 */
@Entity
@Table(name = "paper")
data class PaperEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column(nullable = false)
    val title: String,

    @Column(name = "abstract", nullable = false)
    val abstract: String,

    @ManyToOne
    @JoinColumn(name = "subject_id", referencedColumnName = "id", nullable = false)
    val subject: SubjectEntity,

    @ManyToOne
    @JoinColumn(name = "instructor_id", referencedColumnName = "id", nullable = false)
    val instructor: InstructorEntity,

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id", nullable = false)
    val student: StudentEntity,

    @OneToOne
    @JoinColumn(name = "file_id", referencedColumnName = "id", nullable = false)
    val file: FileEntity,

    @OneToMany
    @JoinColumn(name = "syntactic_check_id", referencedColumnName = "id", nullable = false)
    val checks: List<SyntacticCheckEntity> = listOf(),
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as PaperEntity

        return id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName +
            "(id = $id, title = $title, abstract = $abstract, subject = $subject, instructor = $instructor, student = $student, file = $file, checks = $checks)"
    }
}
