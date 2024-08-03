import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TankWarsGame extends JFrame {
    private JPanel startScreen;
    private JPanel endScreen;
    private GamePanel gamePanel;
    
    public TankWarsGame() {
        setTitle("Tank Wars");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new CardLayout());
        
        initializeStartScreen();
        initializeEndScreen();
        initializeGamePanel();
        
        add(startScreen, "StartScreen");
        add(gamePanel, "GamePanel");
        add(endScreen, "EndScreen");
        
        ((CardLayout) getContentPane().getLayout()).show(getContentPane(), "StartScreen");
    }
    
    private void initializeStartScreen() {
        startScreen = new JPanel();
        startScreen.setLayout(new BorderLayout());
        
        JButton startButton = new JButton("Start Game");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((CardLayout) getContentPane().getLayout()).show(getContentPane(), "GamePanel");
                gamePanel.startGame();
                gamePanel.requestFocusInWindow();
            }
        });
        
        startScreen.add(startButton, BorderLayout.CENTER);
    }
    
    private void initializeEndScreen() {
        endScreen = new JPanel();
        endScreen.setLayout(new BorderLayout());
        
        JButton restartButton = new JButton("Restart Game");
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((CardLayout) getContentPane().getLayout()).show(getContentPane(), "GamePanel");
                gamePanel.restartGame();
            }
        });
        
        JButton closeButton = new JButton("Close Game");
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(restartButton);
        buttonPanel.add(closeButton);
        
        endScreen.add(buttonPanel, BorderLayout.CENTER);
    }
    
    private void initializeGamePanel() {
        gamePanel = new GamePanel();
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TankWarsGame().setVisible(true));
    }
}
