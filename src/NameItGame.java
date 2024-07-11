import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class NameItGame extends JFrame implements ActionListener {
    private JLabel promptLabel, letterLabel, scoreLabel;
    private JTextField inputField;
    private JButton submitButton, newLetterButton;
    private JComboBox<String> categoryComboBox;
    private String currentLetter;
    private int score;

    public NameItGame() {
        setTitle("Name It Game");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(6, 1, 10, 10));
        mainPanel.setBackground(new Color(240, 248, 255)); // Light blue background

        // Top panel for category selection
        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(173, 216, 230)); // Light blue background
        promptLabel = new JLabel("Select the category:");
        promptLabel.setFont(new Font("Arial", Font.BOLD, 16));
        String[] categories = {"Country", "Sport", "Movie", "Animal", "Car"};
        categoryComboBox = new JComboBox<>(categories);
        categoryComboBox.setFont(new Font("Arial", Font.PLAIN, 14));
        topPanel.add(promptLabel);
        topPanel.add(categoryComboBox);

        // Letter display
        letterLabel = new JLabel();
        letterLabel.setFont(new Font("Arial", Font.BOLD, 24));
        letterLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Input field
        inputField = new JTextField();
        inputField.setFont(new Font("Arial", Font.PLAIN, 18));

        // Buttons
        submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Arial", Font.BOLD, 16));
        submitButton.setBackground(new Color(60, 179, 113)); // Medium sea green background
        submitButton.setForeground(Color.WHITE);
        newLetterButton = new JButton("New letter and new category");
        newLetterButton.setFont(new Font("Arial", Font.BOLD, 16));
        newLetterButton.setBackground(new Color(30, 144, 255)); // Dodger blue background
        newLetterButton.setForeground(Color.WHITE);

        submitButton.addActionListener(this);
        newLetterButton.addActionListener(this);

        // Score label
        scoreLabel = new JLabel("Score: 0");
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 18));
        scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Adding components to main panel
        mainPanel.add(topPanel);
        mainPanel.add(letterLabel);
        mainPanel.add(inputField);
        mainPanel.add(submitButton);
        mainPanel.add(newLetterButton);
        mainPanel.add(scoreLabel);

        // Adding main panel to frame
        add(mainPanel, BorderLayout.CENTER);

        generateNewLetterAndCategory();
    }

    private void generateNewLetterAndCategory() {
        Random rand = new Random();
        currentLetter = String.valueOf((char) (rand.nextInt(26) + 'A'));
        letterLabel.setText("Letter: " + currentLetter);

        int categoryIndex = rand.nextInt(categoryComboBox.getItemCount());
        categoryComboBox.setSelectedIndex(categoryIndex);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            String input = inputField.getText().trim().toUpperCase();
            if (input.startsWith(currentLetter)) {
                score++;
                scoreLabel.setText("Score: " + score);
                JOptionPane.showMessageDialog(this, "Correct! Your score is now " + score);
            } else {
                JOptionPane.showMessageDialog(this, "Incorrect. The name must start with " + currentLetter);
                score = 0;
                scoreLabel.setText("Score: " + score);
            }
            inputField.setText("");
            generateNewLetterAndCategory();
        } else if (e.getSource() == newLetterButton) {
            score = 0;
            scoreLabel.setText("Score: " + score);
            generateNewLetterAndCategory();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            NameItGame game = new NameItGame();
            game.setVisible(true);
        });
    }
}
