package com.awul.quizapp.fragment

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.awul.quizapp.replaceFragment
import com.awul.quizapp.databinding.FragmentQuizBinding
import com.awul.quizapp.model_class.ModelQuiz
import java.io.InputStream


class QuizFragment : Fragment() {
    private lateinit var binding: FragmentQuizBinding

    private val quizData = arrayOf(
        ModelQuiz(
            id = 0,
            question = "What is a database??",
            imageUrl = "",
            answers = arrayOf(
                "A. A collection of files",
                "B. A collection of tables",
                "C. A collection of software",
                "D. A collection of web pages"

            ),
            correctAnsIndex = 1
        ),
        ModelQuiz(
            id = 1,
            question = "Which of the following is a relational database management system (RDBMS)?",
            imageUrl = "",
            answers = arrayOf(
                "A. MongoDB",
                "B. SQLite",
                "C. Redis",
                "D. Cassandra"
                   ),
            correctAnsIndex = 1
        ),
        ModelQuiz(
            id = 2,
            question = "What is SQL used for in the context of databases?",
            imageUrl = "",
            answers = arrayOf(
                "A. Software Development",
                "B. System Administration",
                "C. Database Querying and Manipulation",
                "D. Networking"
            ),
            correctAnsIndex = 2
        ),
        ModelQuiz(
            id = 3,
            question = "What does the acronym “ACID” stand for in the context of database transactions?",
            imageUrl = "",
            answers = arrayOf(
                "A. Atomicity, Consistency, Isolation, Durability",
                "B. Association, Concurrency, Inheritance, Data",
                "C. Aggregation, Constraints, Indexing, Dependencies",
                "D. Access, Control, Integrity, Distribution"
            ),
            correctAnsIndex = 0
        ),
        ModelQuiz(
            id = 4,
            question = "Which term is used to describe the unique key in a database table?",
            imageUrl = "",
            answers = arrayOf(
                "A. Prime Key",
                "B. Core Key",
                "C. Master Key",
                "D. Primary Key"
            ),
            correctAnsIndex = 3
        ),
        ModelQuiz(
            id = 5,
            question = "What is normalization in the context of databases?",
            imageUrl = "",
            answers = arrayOf(
                "A. The process of organizing data to minimize redundancy",
                "B. The process of encrypting data for security",
                "C. The process of creating backup copies of data",
                "D. The process of sorting data in ascending order"
            ),
            correctAnsIndex = 0
        ),
        ModelQuiz(
            id = 6,
            question = "Which SQL clause is used to filter the results of a query to a specific condition?",
            imageUrl = "",
            answers = arrayOf(
                "A. ORDER BY",
                "B. GROUP BY",
                "C. WHERE",
                "D. HAVING"
            ),
            correctAnsIndex = 2
        ),
        ModelQuiz(
            id = 7,
            question = "What is the purpose of a foreign key in a relational database?",
            imageUrl = "",
            answers = arrayOf(
                "A. It uniquely identifies each record in a table",
                "B. It ensures that the values in a column are unique",
                "C. It establishes a link between two tables",
                "D. It enforces data integrity rules"
            ),
            correctAnsIndex = 2
        ),
        ModelQuiz(
            id = 8,
            question = "What is the difference between a primary key and a unique key?",
            imageUrl = "",
            answers = arrayOf(
                "A. There is no difference; the terms are used interchangeably",
                "B. A table can have multiple primary keys, but only one unique key",
                "C. A unique key allows NULL values, while a primary key does not",
                "D. A table can have only one primary key, but multiple unique keys"
            ),
            correctAnsIndex = 3
        ),
        ModelQuiz(
            id = 9,
            question = "What is SQL used for in the context of databases?",
            imageUrl = "",
            answers = arrayOf(
                "A. To add new records to a table",
                "B. To retrieve data from a table",
                "C. To update existing records in a table",
                "D. To delete records from a table"
            ),
            correctAnsIndex = 1
        )
    )
    private var rightAns = 0
    private var mcqIndex = 0
    private var rightAnsCount = 0
    private var isTimeUp = false
    private var isAnsButtonClicked = false

    private val countDownTimer = object : CountDownTimer(20000, 1000) {
        override fun onTick(p0: Long) {
            val sec: Int = (p0 / 1000).toInt()
            binding.tvTimer.text = "00:%02d".format(sec)
            isTimeUp = false
        }

        override fun onFinish() {
            binding.tvTimer.text = "Time's up 🛑"
            isTimeUp = true
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentQuizBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setQuestion(mcqIndex)



        binding.btn1.setOnClickListener {
            btnClickOperation(0, it)
        }
        binding.btn2.setOnClickListener {
            btnClickOperation(1, it)
        }
        binding.btn3.setOnClickListener {
            btnClickOperation(2, it)
        }
        binding.btn4.setOnClickListener {
            btnClickOperation(3, it)
        }

        binding.btnNextPag.setOnClickListener {

            mcqIndex = mcqIndex + 1


            if (mcqIndex < quizData.count()) {
                setQuestion(mcqIndex)
            } else {

                val bundle = Bundle().apply {
                    putInt("TOTAL_QUES", quizData.count())
                    putInt("TAOTAL_CORRECT", rightAnsCount)
                }


                replaceFragment(ResultFragment(), requireActivity(), bundle)
            }


        }


    }



    @SuppressLint("ResourceAsColor")
    private fun btnClickOperation(ansIndex: Int, clickedView: View?) {

        if (isAnsButtonClicked) {
            return
        }

        if (isTimeUp) {
            Toast.makeText(activity, "Time's up", Toast.LENGTH_SHORT).show()
            return
        }

        val ansButtons = arrayOf(
            binding.btn1,
            binding.btn2,
            binding.btn3,
            binding.btn4
        )

        ansButtons.forEach {
//            it.background.setTint(color.primary_my)
           // it.background.setTint(Color.rgb(133,109,142))
            it.background.setTint(Color.LTGRAY)
        }


        if (ansIndex != -1) {

            isAnsButtonClicked = true
            countDownTimer.cancel()

            if (ansIndex == rightAns) { // Ans Correct

                rightAnsCount += 1

               // clickedView?.background?.setTint(color.green_my)
                clickedView?.background?.setTint(Color.GREEN)

            } else { // Ans Wrong

                ansButtons.forEachIndexed { index, materialButton ->

                    if (index == ansIndex) {
                        //materialButton.background.setTint(color.red_my)
                        materialButton.background.setTint(Color.RED)
                    }
                    if (index == rightAns) {
                       // materialButton.background.setTint(color.green_my)
                        materialButton.background.setTint(Color.GREEN)
                    }

                }

            }
        }





    } // btnClickOperation

    private fun setQuestion(qIndex: Int) {

        isAnsButtonClicked = false

        btnClickOperation(-1, null)

        val q = quizData[qIndex]

        binding.tvQuestion.text = q.question

        if (q.imageUrl.isEmpty()) {
            binding.ivThumb.visibility = View.GONE
        } else {
            binding.ivThumb.visibility = View.VISIBLE

            try {

                val inputStream: InputStream? = activity?.assets?.open("img/${q.imageUrl}")
                val d: Drawable? = Drawable.createFromStream(inputStream, null)
                binding.ivThumb.setImageDrawable(d)

            } catch (e: Exception) { e.printStackTrace() }

        }



        if (q.answers.count() > 0) {
            binding.btn1.visibility = View.VISIBLE
            binding.btn1.text = q.answers[0]
        } else {
            binding.btn1.visibility = View.GONE
        }
        if (q.answers.count() > 1) {
            binding.btn2.visibility = View.VISIBLE
            binding.btn2.text = q.answers[1]
        } else {
            binding.btn2.visibility = View.GONE
        }
        if (q.answers.count() > 2) {
            binding.btn3.visibility = View.VISIBLE
            binding.btn3.text = q.answers[2]
        } else {
            binding.btn3.visibility = View.GONE
        }
        if (q.answers.count() > 3) {
            binding.btn4.visibility = View.VISIBLE
            binding.btn4.text = q.answers[3]
        } else {
            binding.btn4.visibility = View.GONE
        }


        rightAns = q.correctAnsIndex

        countDownTimer.start()

    } // setQuestion






}