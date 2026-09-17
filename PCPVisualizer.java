import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class PCPVisualizer extends JFrame {

    JTextField currentStateField, readField, writeField, moveField, nextStateField;
    DefaultTableModel tableModel;
    JPanel dominoPanel;

    ArrayList<String[]> transitions = new ArrayList<>();

    public PCPVisualizer() {

        setTitle("PCP Undecidability Mapper");
        setSize(1000, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(6, 2, 5, 5));

        currentStateField = new JTextField();
        readField = new JTextField();
        writeField = new JTextField();
        moveField = new JTextField();
        nextStateField = new JTextField();

        inputPanel.add(new JLabel("Current State"));
        inputPanel.add(currentStateField);

        inputPanel.add(new JLabel("Read Symbol"));
        inputPanel.add(readField);

        inputPanel.add(new JLabel("Write Symbol"));
        inputPanel.add(writeField);

        inputPanel.add(new JLabel("Move (L/R)"));
        inputPanel.add(moveField);

        inputPanel.add(new JLabel("Next State"));
        inputPanel.add(nextStateField);

        JButton addButton = new JButton("Add Transition");
        JButton generateButton = new JButton("Generate PCP");

        inputPanel.add(addButton);
        inputPanel.add(generateButton);

        add(inputPanel, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(
                new String[]{"ID", "Current", "Read", "Write", "Move", "Next"}, 0);

        JTable table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        dominoPanel = new JPanel();
        dominoPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));
        dominoPanel.setBackground(Color.WHITE);

        JScrollPane dominoScroll = new JScrollPane(dominoPanel);
        dominoScroll.setPreferredSize(new Dimension(1000, 250));

        add(dominoScroll, BorderLayout.SOUTH);

        addButton.addActionListener(e -> addTransition());
        generateButton.addActionListener(e -> generatePCP());

        setVisible(true);
    }

    private void addTransition() {

        String cs = currentStateField.getText();
        String read = readField.getText();
        String write = writeField.getText();
        String move = moveField.getText();
        String ns = nextStateField.getText();

        int id = transitions.size() + 1;

        transitions.add(new String[]{
                cs, read, write, move, ns
        });

        tableModel.addRow(new Object[]{
                "T" + id, cs, read, write, move, ns
        });

        currentStateField.setText("");
        readField.setText("");
        writeField.setText("");
        moveField.setText("");
        nextStateField.setText("");
    }

    private void generatePCP() {

        dominoPanel.removeAll();

        dominoPanel.add(createDomino("$", "$1"));

        for (int i = 1; i <= transitions.size(); i++) {

            String binary = Integer.toBinaryString(i);

            dominoPanel.add(
                    createDomino(binary,
                            binary + "#")
            );
        }

        dominoPanel.add(createDomino("#", "ε"));

        dominoPanel.revalidate();
        dominoPanel.repaint();
    }

    private JPanel createDomino(String top, String bottom) {

        JPanel domino = new JPanel();
        domino.setPreferredSize(new Dimension(100, 120));
        domino.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        domino.setLayout(new GridLayout(2, 1));

        JLabel topLabel = new JLabel(top, SwingConstants.CENTER);
        JLabel bottomLabel = new JLabel(bottom, SwingConstants.CENTER);

        topLabel.setFont(new Font("Arial", Font.BOLD, 22));
        bottomLabel.setFont(new Font("Arial", Font.BOLD, 22));

        domino.add(topLabel);
        domino.add(bottomLabel);

        return domino;
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() ->
                new PCPVisualizer());
    }
}