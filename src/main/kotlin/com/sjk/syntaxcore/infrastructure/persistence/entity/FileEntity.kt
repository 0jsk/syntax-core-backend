package com.sjk.syntaxcore.infrastructure.persistence.entity

import jakarta.persistence.*
import org.hibernate.Hibernate

/**
 * The `File` class represents a file in the system. It has information about the file's name, author, and content.
 * The file is used to store papers submitted by students.
 *
 * @property id The unique identifier for this file.
 * @property name The name of this file.
 * @property data The binary data stored for this file.
 * @property author The identifier of the author of this file.
 * @property group The identifier of the group associated with this file.
 */
@Entity
@Table(name = "file")
data class FileEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column(nullable = false)
    val name: String,

    @Column(nullable = false)
    val data: ByteArray,

    @ManyToOne
    @JoinColumn(name = "author_id")
    var author: StudentEntity? = null,

    @ManyToOne
    @JoinColumn(name = "groups_id")
    val group: GroupEntity,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as FileEntity

        return id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id, name = $name, author = $author, group = $group)"
    }
}
