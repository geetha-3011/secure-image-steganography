package ui;

import core.Steganography;
import util.ValidationUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class Main {

    private JFrame frame;
    private JLabel imageLabel;
    private BufferedImage selectedImage;
    private JLabel statusLabel;

    // 🎨 Corporate Blue Theme
    private final Color BG = new Color(245, 247, 250);
    private final Color PANEL = new Color(255, 255, 255);
    private final Color BTN = new Color(30, 64, 175);
    private final Color BTN_HOVER = new Color(29, 78, 216);
    private final Color TXT_TITLE = new Color(15, 23, 42);
    private final Color TXT = new Color(71, 85, 105);

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main().createMainFrame());
    }

    // ================= HOME PAGE =================
    public void createMainFrame() {
        frame = new JFrame("Secure Image Steganography System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(BG);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(PANEL);
        panel.setBorder(BorderFactory.createEmptyBorder(40, 80, 40, 80));

        JLabel title = new JLabel("Secure Image Steganography System");
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setForeground(TXT_TITLE);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton embedBtn = new JButton("Embed Confidential Data");
        JButton extractBtn = new JButton("Extract Confidential Data");

        styleButton(embedBtn);
        styleButton(extractBtn);

        Dimension btnSize = new Dimension(260, 45);
        embedBtn.setMaximumSize(btnSize);
        extractBtn.setMaximumSize(btnSize);

        embedBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        extractBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        embedBtn.addActionListener(e -> openEncodeFrame());
        extractBtn.addActionListener(e -> openDecodeFrame());

        panel.add(title);
        panel.add(Box.createVerticalStrut(30));
        panel.add(embedBtn);
        panel.add(Box.createVerticalStrut(20));
        panel.add(extractBtn);

        frame.add(panel, BorderLayout.CENTER);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // ================= ENCODE PAGE =================
    private void openEncodeFrame() {
        JFrame encodeFrame = new JFrame("Embed Confidential Data into Image");
        encodeFrame.getContentPane().setBackground(BG);
        encodeFrame.setLayout(new BorderLayout());

        JPanel panel = createFormPanel();

        JButton selectImageBtn = new JButton("Select Cover Image");
        styleButton(selectImageBtn);
        selectImageBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        imageLabel = new JLabel();
        imageLabel.setPreferredSize(new Dimension(220, 220));
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextArea messageArea = new JTextArea(3, 25);
        styleTextArea(messageArea, "Confidential Text");

        JTextField keyField = new JTextField(20);
        styleTextField(keyField, "Encryption Key");

        JButton embedBtn = new JButton("Securely Embed and Save");
        styleButton(embedBtn);
        embedBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        statusLabel = new JLabel("Status: Ready");
        statusLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        statusLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        selectImageBtn.addActionListener(e -> chooseImage(imageLabel));

        embedBtn.addActionListener(e -> {
            try {
                statusLabel.setText("Status: Embedding...");
                ValidationUtils.validateInputs(selectedImage, messageArea.getText(), keyField.getText());

                BufferedImage encoded = Steganography.encode(
                        selectedImage,
                        messageArea.getText(),
                        keyField.getText()
                );

                JFileChooser chooser = new JFileChooser();
                chooser.setSelectedFile(new File("secure_image.png"));

                if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                    ImageIO.write(encoded, "png", chooser.getSelectedFile());
                    statusLabel.setText("Status: Completed");
                    JOptionPane.showMessageDialog(null, "Data successfully embedded.");
                    encodeFrame.dispose();
                    frame.setVisible(true);
                }
            } catch (Exception ex) {
                statusLabel.setText("Status: Failed");
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        });

        panel.add(selectImageBtn);
        panel.add(Box.createVerticalStrut(15));
        panel.add(imageLabel);
        panel.add(Box.createVerticalStrut(15));
        panel.add(messageArea);
        panel.add(Box.createVerticalStrut(10));
        panel.add(keyField);
        panel.add(Box.createVerticalStrut(15));
        panel.add(embedBtn);
        panel.add(Box.createVerticalStrut(10));
        panel.add(statusLabel);

        encodeFrame.add(panel, BorderLayout.CENTER);
        encodeFrame.pack();
        encodeFrame.setLocationRelativeTo(null);
        encodeFrame.setVisible(true);
    }

    // ================= DECODE PAGE =================
    private void openDecodeFrame() {
        JFrame decodeFrame = new JFrame("Extract Confidential Data from Image");
        decodeFrame.getContentPane().setBackground(BG);
        decodeFrame.setLayout(new BorderLayout());

        JPanel panel = createFormPanel();

        JButton selectImageBtn = new JButton("Select Stego Image");
        styleButton(selectImageBtn);
        selectImageBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        imageLabel = new JLabel();
        imageLabel.setPreferredSize(new Dimension(220, 220));
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField keyField = new JTextField(20);
        styleTextField(keyField, "Encryption Key");

        JTextArea resultArea = new JTextArea(3, 25);
        styleTextArea(resultArea, "Extracted Confidential Text");
        resultArea.setEditable(false);

        JButton extractBtn = new JButton("Extract Secure Data");
        styleButton(extractBtn);
        extractBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        statusLabel = new JLabel("Status: Ready");
        statusLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        statusLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        selectImageBtn.addActionListener(e -> chooseImage(imageLabel));

        extractBtn.addActionListener(e -> {
            try {
                statusLabel.setText("Status: Extracting...");
                ValidationUtils.validateKey(keyField.getText());

                String message = Steganography.decode(selectedImage, keyField.getText());
                resultArea.setText(message);

                statusLabel.setText("Status: Completed");
                JOptionPane.showMessageDialog(null, "Data successfully extracted.");

                decodeFrame.dispose();
                frame.setVisible(true);
            } catch (Exception ex) {
                statusLabel.setText("Status: Failed");
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        });

        panel.add(selectImageBtn);
        panel.add(Box.createVerticalStrut(15));
        panel.add(imageLabel);
        panel.add(Box.createVerticalStrut(15));
        panel.add(keyField);
        panel.add(Box.createVerticalStrut(10));
        panel.add(extractBtn);
        panel.add(Box.createVerticalStrut(15));
        panel.add(resultArea);
        panel.add(Box.createVerticalStrut(10));
        panel.add(statusLabel);

        decodeFrame.add(panel, BorderLayout.CENTER);
        decodeFrame.pack();
        decodeFrame.setLocationRelativeTo(null);
        decodeFrame.setVisible(true);
    }

    // ================= HELPERS =================
    private JPanel createFormPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(PANEL);
        panel.setBorder(BorderFactory.createEmptyBorder(30, 60, 30, 60));
        return panel;
    }

    private void styleButton(JButton btn) {
        btn.setBackground(BTN);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    private void styleTextField(JTextField field, String title) {
        field.setBorder(BorderFactory.createTitledBorder(title));
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setForeground(TXT);
        field.setMaximumSize(new Dimension(400, 55));
    }

    private void styleTextArea(JTextArea area, String title) {
        area.setBorder(BorderFactory.createTitledBorder(title));
        area.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        area.setForeground(TXT);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setMaximumSize(new Dimension(400, 90));
    }

    private void chooseImage(JLabel label) {
        JFileChooser chooser = new JFileChooser();
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            try {
                selectedImage = ImageIO.read(chooser.getSelectedFile());
                label.setIcon(new ImageIcon(
                        selectedImage.getScaledInstance(220, 220, Image.SCALE_SMOOTH)
                ));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Unable to load image file.");
            }
        }
    }
}
