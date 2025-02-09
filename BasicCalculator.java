import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

class BasicCalculator extends JFrame implements ActionListener
{
    private final JTextField display;
    private String s0, s1, s2;
    private final DecimalFormat df;

    BasicCalculator()
    {
        s0 = s1 = s2 = "";
        df = new DecimalFormat("#.##");
        
        setTitle("Basic Calculator");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        display = new JTextField(30);
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.BOLD, 50));
        display.setHorizontalAlignment(JTextField.RIGHT);
        add(display, BorderLayout.NORTH);
        
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4, 10, 10));
        panel.setBackground(Color.DARK_GRAY);
        
        String buttons[] = {"7", "8", "9", "/", "4", "5", "6", "*", "1", "2", "3", "-", "0", ".", "+", "="};
        Color buttonColors[] = {Color.WHITE, new Color(255, 255, 204)};
        int colorIndex = 0;

        for (String text : buttons)
        {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 20));
            button.setBackground(buttonColors[colorIndex % 2]);
            button.setForeground(Color.BLACK);
            button.setFocusPainted(false);
            button.addActionListener(this);
            panel.add(button);
            colorIndex++;
        }
        
        JButton clear = new JButton("C");
        clear.setFont(new Font("Arial", Font.BOLD, 20));
        clear.setBackground(Color.RED);
        clear.setForeground(Color.WHITE);
        clear.setFocusPainted(false);
        clear.addActionListener(this);
        
        add(panel, BorderLayout.CENTER);
        clear.setPreferredSize(new Dimension(400, 60));
        add(clear, BorderLayout.SOUTH);
        
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e)
    {
        String s = e.getActionCommand();
        if ((s.charAt(0) >= '0' && s.charAt(0) <= '9') || s.equals("."))
        {
            if (!s1.equals("")) s2 += s;
            else s0 += s;
        }
        else if (s.equals("C"))
        {
            s0 = s1 = s2 = "";
        }
        else if (s.equals("="))
        {
            double result = switch (s1)
            {
                case "+" -> Double.parseDouble(s0) + Double.parseDouble(s2);
                case "-" -> Double.parseDouble(s0) - Double.parseDouble(s2);
                case "*" -> Double.parseDouble(s0) * Double.parseDouble(s2);
                case "/" -> Double.parseDouble(s0) / Double.parseDouble(s2);
                default -> 0;
            };
            s0 = df.format(result);
            s1 = s2 = "";
        }
        else
        {
            if (s1.equals("")) s1 = s;
            else if (!s2.equals(""))
            {
                double result = switch (s1)
                {
                    case "+" -> Double.parseDouble(s0) + Double.parseDouble(s2);
                    case "-" -> Double.parseDouble(s0) - Double.parseDouble(s2);
                    case "*" -> Double.parseDouble(s0) * Double.parseDouble(s2);
                    case "/" -> Double.parseDouble(s0) / Double.parseDouble(s2);
                    default -> 0;
                };
                s0 = df.format(result);
                s1 = s;
                s2 = "";
            }
        }
        display.setText(s0 + s1 + s2);
    }
    
    public static void main(String[] args)
    {
        new BasicCalculator();
    }
}
