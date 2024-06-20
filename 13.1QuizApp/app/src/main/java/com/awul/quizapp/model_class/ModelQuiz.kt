package com.awul.quizapp.model_class

data class ModelQuiz(
    val id: Int = 0,
    val question: String = "",
    val imageUrl: String = "",
    val answers: Array<String> = arrayOf(),
    val correctAnsIndex: Int = 0
)
