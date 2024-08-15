import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuizSwing extends JFrame implements ActionListener {
    private int score = 0;
    private int currentQuestionIndex = 0;

    private String[] questions = {
            "What is the capital of France?",
            "Which planet is known as the Red Planet?",
            "What is the largest ocean on Earth?",
            "Who wrote 'Hamlet'?",
            "What is the smallest prime number?"
    };

    private String[][] options = {
            {"A. Berlin", "B. Madrid", "C. Paris", "D. Rome"},
            {"A. Earth", "B. Mars", "C. Jupiter", "D. Venus"},
            {"A. Atlantic Ocean", "B. Indian Ocean", "C. Arctic Ocean", "D. Pacific Ocean"},
            {"A. Charles Dickens", "B. Jane Austen", "C. William Shakespeare", "D. Mark Twain"},
            {"A. 0", "B. 1", "C. 2", "D. 3"}
    };

    private char[] answers = {'C', 'B', 'D', 'C', 'C'};

    private JTextArea questionArea;
    private JRadioButton[] optionButtons;
    private JButton nextButton;
    private ButtonGroup optionsGroup;

    public QuizSwing() {
        setTitle("Quiz Application");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        questionArea = new JTextArea(5, 30);
        questionArea.setLineWrap(true);
        questionArea.setWrapStyleWord(true);
        questionArea.setEditable(false);
        add(questionArea);

        optionsGroup = new ButtonGroup();
        optionButtons = new JRadioButton[4];

        for (int i = 0; i < 4; i++) {
            optionButtons[i] = new JRadioButton();
            optionsGroup.add(optionButtons[i]);
            add(optionButtons[i]);
        }

        nextButton = new JButton("Next");
        nextButton.addActionListener(this);
        add(nextButton);

        displayQuestion();
    }

    private void displayQuestion() {
        if (currentQuestionIndex < questions.length) {
            questionArea.setText(questions[currentQuestionIndex]);
            String[] currentOptions = options[currentQuestionIndex];
            for (int i = 0; i < currentOptions.length; i++) {
                optionButtons[i].setText(currentOptions[i]);
                optionButtons[i].setSelected(false);
            }
        } else {
            showResult();
        }
    }

    private void showResult() {
        String resultMessage = "Your total score is: " + score + " out of " + questions.length + ".";
        JOptionPane.showMessageDialog(this, resultMessage);
        System.exit(0); // Exit the application
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (currentQuestionIndex < questions.length) {
            char selectedAnswer = ' ';
            for (int i = 0; i < optionButtons.length; i++) {
                if (optionButtons[i].isSelected()) {
                    selectedAnswer = (char) ('A' + i);
                    break;
                }
            }

            if (selectedAnswer == answers[currentQuestionIndex]) {
                score++;
            } else {
                JOptionPane.showMessageDialog(this, "Wrong! The correct answer is " + answers[currentQuestionIndex] + ".");
            }

            currentQuestionIndex++;
            displayQuestion();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            QuizSwing quiz = new QuizSwing();
            quiz.setVisible(true);
        });
    }
}
