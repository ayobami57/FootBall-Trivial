package com.example.footballtrivial

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.footballtrivial.databinding.FragmentGameBinding


class GameFragment : Fragment() {



    data class Question(
        val text: String,
        val answers: List<String>
    )

    private val questions: MutableList<Question> = mutableListOf(

        // The first answer is always the correct one, the answers are randomize before showing to text
        // All the questions have four different options of answers

        Question(text = "Which player, with 653 games, has made the most Premier League appearances?",
        answers = listOf("Gareth Barry", "Alan Shearer", "Patrick Evra", "Richard Dunne" )),
        Question(text = "Which team won the first Premier League title?",
            answers = listOf("Manchester United", "Arsenal", "Chelsea", "Aston Villa" )),
        Question(text = "Which country has won the most World Cups?",
            answers = listOf("Brazil", "Uruguay", "Germany", "France" )),
        Question(text = "Which country has appeared in three World Cup finals, but never won the competition?",
            answers = listOf("Netherlands", "Argentina", "England", "Uruguay" )),
        Question(text = "Which club has won the most Champions League titles?",
            answers = listOf("Real Madrid", "Manchester United", "Barcelona", "Bayern Munich" )),
        Question(text = "Who is the only player to win the Champions League with three different clubs?",
            answers = listOf("Clarence Seedorf", "Miroslav Klose", "Zlatan Ibrahimovich", "Francisco Gento" )),
        Question(text = "Who is the Champions League's top goalscorer of all time?",
            answers = listOf("Christiano Ronaldo", "Lionel Messi", "Karim Benzema", "Thiery Henry" )),
        Question(text = "Which player scored the fastest hat-trick in the Premier League?",
            answers = listOf("Sadio Mane", "Mohammed Salah", "Kun Aguero", "Christiano Ronaldo" )),
        Question(text = "Which club is associated with 'Galacticos'?",
            answers = listOf("Real Madrid", "Athletico Madrid", "Valencia", "Sevilla" )),
        Question(text = "Which manager was famously said to have given players 'the Hairdryer Treatment",
            answers = listOf("Sir Alex Fergusson", "Pep Guardiola", "Jose Mourinho", "Arsene Wenger" ))

    )


    lateinit var currentQuestion: Question
    lateinit var answers: MutableList<String>
    private var questionIndex = 0
    private val numQuestions = Math.min((questions.size + 1) / 2, 3)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<FragmentGameBinding>(inflater, R.layout.fragment_game, container, false)

        // shuflling and randomizing the questions
        randomizeQuestions()
        // binding this fragment class to the layout
        binding.game = this

        // setting onClickListener on the Submit button
        binding.submitButton.setOnClickListener{view: View ->
            val checkedID = binding.radioGroup.checkedRadioButtonId
            //Do nothing if nothing is checked
            if (-1 != checkedID){
                var answerIndex = 0
                when(checkedID){
                    R.id.option1_radio_button -> answerIndex =1
                    R.id.option2_radio_button -> answerIndex = 2
                    R.id.option3_radio_button -> answerIndex = 3
                }
                // the first answer in the original question is always the right answer, so if our answer matches
                // we will have a correct answer
                if(answers[answerIndex] == currentQuestion.answers[0]){
                    questionIndex++
                    // proceed to the next question
                    if (questionIndex<numQuestions){
                        currentQuestion = questions[questionIndex]
                        setQuestion()
                        binding.invalidateAll()
                    } else{
                        // means all answers are correct and the game is won, proceed to game won fragment
                        view.findNavController().navigate(R.id.action_gameFragment_to_gameWonFragment)
                    }

                } else{
                    // means answer is wrong and game is over, proceed to game over fragment
                    view.findNavController().navigate(R.id.action_gameFragment_to_gameOverFragment)
                }
            }
        }

        // Inflate the layout for this fragment
        return binding.root
    }




    // randomize the questions and set the first question
    private fun randomizeQuestions() {
        questions.shuffle()
        questionIndex = 0
        setQuestion()
    }

    // Sets the question and randomizes the answers.  This only changes the data, not the UI.
    // Calling invalidateAll on the FragmentGameBinding updates the data.
    private fun setQuestion() {
        currentQuestion = questions[questionIndex]
        // randomize the answers into a copy of the array
        answers = currentQuestion.answers.toMutableList()
        // and shuffle them
        answers.shuffle()
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.title_android_trivia_question, questionIndex + 1, numQuestions)
    }
}