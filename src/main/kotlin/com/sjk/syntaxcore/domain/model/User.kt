package com.sjk.syntaxcore.domain.model

import com.sjk.syntaxcore.domain.vo.Role

data class User(
    val id: Long,
    val email: String,
    val password: String,
    val role: Role,
)
