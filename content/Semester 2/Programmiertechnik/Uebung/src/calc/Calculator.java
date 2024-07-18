package calc;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import expressionevaluation.Evaluator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.*;


public class Calculator extends JFrame {
    private JTextField jlInput, jlResult;
    private JPanel jpMain, jpTop, jpMiddle, jpBottom, jpCheck;
    private JButton a0Button, a1Button, a2Button, a3Button, a4Button, a5Button,
            a6Button, a7Button, a8Button, a9Button, dotButton, x10Button,
            delButton, mulButton, plusButton, ansButton, acButton, divButton,
            minButton, equButton, sinButton, cosButton,
            bracketLeftButton, bracketRightButton, powerButton, powerOfTwoButton;
    private JRadioButton radRadioButton;
    private JRadioButton degRadioButton;
    private JComboBox themeComboBox;
    private JButton tanButton;
    private JButton sqrtButton;
    private JButton piButton;
    private JButton eButton;
    private JButton rndButton;
    private JButton powerOfMin1Button;
    private String input = "";
    private Double result = null;
    private static final Random RAND = new Random();
    HashMap<String, String> regexFilter = new LinkedHashMap<>();
    ColorPalette[] themes = {
            new ColorPalette(new Color(238, 238, 238), // Default
                    new Color(0, 173, 181),
                    new Color(57, 62, 70),
                    new Color(34, 40, 49)),
            new ColorPalette(new Color(255, 251, 204), // Sunny Delight
                    new Color(255, 222, 173),
                    new Color(255, 182, 193),
                    new Color(255, 105, 97)),
            new ColorPalette(new Color(204, 255, 229),  // Mint Fresh
                    new Color(173, 235, 216),
                    new Color(127, 208, 178),
                    new Color(22, 90, 69)),
            new ColorPalette(new Color(255, 240, 245),  // Pink Harmony
                    new Color(255, 182, 193),
                    new Color(255, 105, 180),
                    new Color(255, 20, 147)),
            new ColorPalette(new Color(255, 223, 186),  // Tropical Sunset
                    new Color(255, 165, 0),
                    new Color(255, 127, 80),
                    new Color(220, 20, 60)),
            new ColorPalette(new Color(173, 216, 230),  // Ocean Breeze
                    new Color(135, 206, 235),
                    new Color(70, 130, 180),
                    new Color(25, 25, 112)),
            new ColorPalette(new Color(240, 255, 240),  // Pastel Dream
                    new Color(216, 191, 216),
                    new Color(221, 160, 221),
                    new Color(186, 85, 211)),
            new ColorPalette(new Color(204, 255, 229),  // Forest Whisper
                    new Color(144, 238, 144),
                    new Color(60, 179, 113),
                    new Color(0, 100, 0)),
            new ColorPalette(new Color(244, 164, 96),   // Earthy Tones
                    new Color(210, 105, 30),
                    new Color(139, 69, 19),
                    new Color(92, 51, 23)),
            new ColorPalette(new Color(255, 182, 193),  // Berry Burst
                    new Color(219, 112, 147),
                    new Color(199, 21, 133),
                    new Color(139, 0, 139)),
            new ColorPalette(new Color(211, 211, 211),  // Dusky Hues
                    new Color(169, 169, 169),
                    new Color(105, 105, 105),
                    new Color(47, 79, 79))};

    public Calculator() {
        // Set Dictionary
        regexFilter.put("(?<![0-9])×10", "10^");
        regexFilter.put("×10", "*10^");
        regexFilter.put(":", "/");
        regexFilter.put("x", "*");
        regexFilter.put("(?<=[0-9])\\(", "*(");
        regexFilter.put("π(?=[0-9])", "π*");
        regexFilter.put("(?<=[0-9])π", "*π");
        regexFilter.put("π", "" + Math.PI);
        regexFilter.put("e(?=[0-9])", "e*");
        regexFilter.put("(?<=[0-9])e", "*e");
        regexFilter.put("e", "" + Math.E);
        regexFilter.put("Rnd(?=[0-9])", "Rnd*");
        regexFilter.put("(?<=[0-9])Rnd", "*Rnd");
        regexFilter.put("Rnd","" + RAND.nextDouble());

        // Set default locale
        Locale.setDefault(Locale.US);
        // Mechanics
        setContentPane(jpMain);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 700);
        setLocationRelativeTo(null);

        // Design
        setTitle("Taschenrechner");
        // Icon von https://www.iconarchive.com/show/android-lollipop-icons-by-dtafalonso/Calculator-icon.html
        Image icon = new ImageIcon(getClass()
                .getResource("Calculator-icon.png")).getImage();
        setIconImage(icon);
        setTheme(themes[0]);

        // Make Textfield Transparent
        jlInput.setBackground(new Color(255, 255, 255, 0));
        jlResult.setBackground(new Color(255, 255, 255, 0));

        ActionListener operatorPressed = e -> {
            if (!jlResult.getText().isEmpty()) {
                //pressed = true;
                input = "Ans";
            }

            input += e.getActionCommand();
            jlInput.setText(input);
            jlResult.setText("");
        };

        ActionListener digitPressed = e -> {
            if (!jlResult.getText().isEmpty())
                input = "";

            input += e.getActionCommand();
            jlInput.setText(input);
            jlResult.setText("");
        };

        ActionListener calculationPressed = e -> {
            if (e.getSource() == equButton) {
                evaluate();
                return;
            }

            if (!jlResult.getText().isEmpty())
                input = "Ans";
            if (evaluate()) {
                double res = Double.parseDouble(jlResult.getText());
                switch (e.getActionCommand()) {
                    case "sin":
                        res = radRadioButton.isSelected() ? Math.sin(res)
                                : Math.sin(Math.toRadians(res));
                        jlInput.setText("Sin(" + input + ")");
                        break;
                    case "cos":
                        res = radRadioButton.isSelected() ? Math.cos(res)
                                : Math.cos(Math.toRadians(res));
                        jlInput.setText("Cos(" + input + ")");
                        break;
                    case "tan":
                        res = radRadioButton.isSelected() ? Math.tan(res)
                                : Math.tan(Math.toRadians(res));
                        jlInput.setText("Tan(" + input + ")");
                        break;
                    case "sqrt":
                        res = Math.sqrt(res);
                        jlInput.setText("√(" + input + ")");
                        break;
                }

            jlResult.setText(round(res));
            result = Double.parseDouble(jlResult.getText());
            }
        };

        ActionListener deletePressed = e -> {
            if (e.getSource() == delButton) {
                // if result is shown, delete result and do nothing
                if (!jlResult.getText().isEmpty()) {
                    jlInput.setText(input);
                    jlResult.setText("");
                    return;
                }
                // delete last character, last three if its longer
                if (!input.isEmpty()) {
                    int l = input.endsWith("×10")
                            || input.endsWith("Ans")
                            || input.endsWith("Rnd") ? 3 : 1;

                    input = input.substring(0, input.length() - l);
                    jlInput.setText(input);
                    return;
                }
            }

            // AC, which deletes everything
            input = "";
            jlInput.setText("");
            jlResult.setText("");
        };
        ActionListener themeChangedPressed = e -> {
            setTheme(themes[themeComboBox.getSelectedIndex()]);
            if (!jlResult.getForeground().equals(Color.red))
                jlResult.setForeground(themes
                        [themeComboBox.getSelectedIndex()].c.darker());
        };


        minButton.addActionListener(operatorPressed);
        mulButton.addActionListener(operatorPressed);
        plusButton.addActionListener(operatorPressed);
        divButton.addActionListener(operatorPressed);
        powerOfMin1Button.addActionListener(operatorPressed);
        powerButton.addActionListener(operatorPressed);
        powerOfTwoButton.addActionListener(operatorPressed);
        a0Button.addActionListener(digitPressed);
        a1Button.addActionListener(digitPressed);
        a2Button.addActionListener(digitPressed);
        a3Button.addActionListener(digitPressed);
        a4Button.addActionListener(digitPressed);
        a5Button.addActionListener(digitPressed);
        a6Button.addActionListener(digitPressed);
        a7Button.addActionListener(digitPressed);
        a8Button.addActionListener(digitPressed);
        a9Button.addActionListener(digitPressed);
        dotButton.addActionListener(digitPressed);
        x10Button.addActionListener(digitPressed);
        ansButton.addActionListener(digitPressed);
        rndButton.addActionListener(digitPressed);
        bracketLeftButton.addActionListener(digitPressed);
        bracketRightButton.addActionListener(digitPressed);
        piButton.addActionListener(digitPressed);
        eButton.addActionListener(digitPressed);
        delButton.addActionListener(deletePressed);
        acButton.addActionListener(deletePressed);
        sinButton.addActionListener(calculationPressed);
        cosButton.addActionListener(calculationPressed);
        tanButton.addActionListener(calculationPressed);
        sqrtButton.addActionListener(calculationPressed);
        equButton.addActionListener(calculationPressed);
        themeComboBox.addActionListener(themeChangedPressed);
    }

    public boolean evaluate() {
        if (input.isEmpty())
            return false;

        Double tmp = result;

        try {
            result = Evaluator.eval(replaceInput(input));
        } catch (Exception e) {
            result = null;
            System.err.println(e.getMessage());
        }

        jlResult.setForeground(themes
                [themeComboBox.getSelectedIndex()].c.darker());

        if (result != null) { // Success
            jlResult.setText(round(result));
            return true;
        }

        result = tmp;
        //System.out.println(input);
        jlInput.setText("");
        jlResult.setForeground(Color.red);
        jlResult.setText("Syntaxfehler");
        return false;
    }

    public String round(double d) {
        DecimalFormat df;
        if (d > 1e18 || d <= -1e17)
            df = new DecimalFormat("#.#########E0");
        else if (d < 1e-9 && d > -1e-9)
            df = new DecimalFormat("0");
        else
            df = new DecimalFormat("#.#########");

        return df.format(d);
    }

    public void setTheme(ColorPalette palette) {
        jpMain.setBackground(palette.a);
        jpTop.setBackground(palette.a);

        jlInput.setForeground(palette.d.brighter());

        for (Component c : jpMiddle.getComponents()) {
            if (c instanceof JButton) {
                c.setBackground(palette.c);
                c.setForeground(palette.a);
            }
        }

        for (Component c : jpBottom.getComponents()) {
            if (c instanceof JButton) {
                c.setBackground(palette.c);
                c.setForeground(palette.a);
            }
        }

        radRadioButton.setForeground(palette.d);
        degRadioButton.setForeground(palette.d);
    }

    private String replaceInput(String in) {
        in = in.replaceAll("Ans", result != null ? result.toString() : "");
        for (Map.Entry<String, String> entry : regexFilter.entrySet()) {
            in = in.replaceAll(entry.getKey(), entry.getValue());
        }
        return in;
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatMacLightLaf());
        } catch (Exception e) {
            System.err.println( "Failed to initialize LaF" );
        }
        new Calculator();
    }

    public static class ColorPalette {
        public Color a; // Bright
        public Color b; // Medium bright
        public Color c; // Medium dark
        public Color d; // Dark

        public ColorPalette(Color a, Color b, Color c, Color d) {
            this.a = a;
            this.b = b;
            this.c = c;
            this.d = d;
        }
    }
}