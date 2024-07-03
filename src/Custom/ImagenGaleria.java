package Custom;

import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import Modelo.jnafilechooser.api.JnaFileChooser;

import java.awt.BorderLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ImagenGaleria extends JPanel {

    private Object[] project;
    private JLabel mainImage = new JLabel();
    private List<String> imagePaths;
    private int currentImageIndex = 0;
    private List<JLabel> thumbnails;

    public ImagenGaleria(Object[] pyto) {
        this.project = pyto;
        thumbnails = new ArrayList<>();
        imagePaths = new ArrayList<>();
        imagePaths.add("/storage/" + project[0] + "/images/1.jpg");
        imagePaths.add("/storage/" + project[0] + "/images/2.jpg");
        imagePaths.add("/storage/" + project[0] + "/images/3.jpg");

        this.setLayout(new BorderLayout());

        JPanel mainPanel = getMainPanel();
        JPanel thumbnailPanel = getThumbnailPanel();

        this.add(mainPanel, BorderLayout.CENTER);
        this.add(thumbnailPanel, BorderLayout.WEST);

    }

    public JPanel getMainPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setBackground(Color.WHITE);

        mainImage.setText("Select an image to view it in full size");
        mainImage.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel controlPanel = getControlPanel();

        panel.add(mainImage, BorderLayout.CENTER);
        panel.add(controlPanel, BorderLayout.SOUTH);
        return panel;
    }

    public JPanel getControlPanel() {
        JPanel panel = new JPanel() {
            @Override
            public Dimension getMinimumSize() {
                return new Dimension(getWidth(), 200);
            }

            @Override
            public Dimension getMaximumSize() {
                return new Dimension(getWidth(), 250);
            }
        };
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setBackground(Color.WHITE);

        JButton prevButton = new JButton("Anterior");
        JButton nextButton = new JButton("Siguiente");
        prevButton.addActionListener(e -> {
            currentImageIndex = (currentImageIndex - 1 + imagePaths.size()) % imagePaths.size();
            updateMainImage();
        });
        nextButton.addActionListener(e -> {
            currentImageIndex = (currentImageIndex + 1) % imagePaths.size();
            updateMainImage();
        });
        panel.add(Box.createHorizontalGlue()); // Adds flexible space before the buttons
        panel.add(prevButton);
        panel.add(Box.createHorizontalStrut(10)); // Fixed space between buttons
        panel.add(nextButton);
        panel.add(Box.createHorizontalGlue()); // Adds flexible space after the buttons

        return panel;
    }

    public JPanel getThumbnailPanel() {
        JPanel panel = new JPanel() {
            @Override
            public Dimension getMinimumSize() {
                return new Dimension(150, getHeight()); // Minimum width 100 pixels
            }

            @Override
            public Dimension getMaximumSize() {
                return new Dimension(300, getHeight()); // Maximum width 300 pixels
            }
        };

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        populateThumbnailPanel(panel);

        JScrollPane scrollPanel = new JScrollPane(panel);
        scrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        // Increase scroll speed
        JScrollBar verticalScrollBar = scrollPanel.getVerticalScrollBar();
        verticalScrollBar.setUnitIncrement(12); // Adjust this value as needed
        verticalScrollBar.setBlockIncrement(16 * 10); // Adjust this value as needed

        JPanel wraperPanel = new JPanel();
        wraperPanel.setLayout(new BorderLayout());

        JPanel addImagePanel = new JPanel();

        JButton addImageButton = new JButton("Añadir Imagen");
        addImageButton.addActionListener(e -> {
            JnaFileChooser ch = new JnaFileChooser();
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(addImageButton);
            if (frame == null || !frame.isDisplayable()) {
                System.err.println("Parent frame is not displayable.");
                return;
            }
            boolean save = ch.showOpenDialog(frame);
            if (save) {
                File file = ch.getSelectedFile();
                // Here you can handle the selected file
                String filePath = file.getAbsolutePath();
                System.out.println("Selected file path: " + filePath);

                // Assuming you want to save the image somewhere within your application folder
                try {
                    // Define the destination directory within your application's folder
                    String destinationDir = "path/to/your/application/folder"; // Replace with your actual path

                    // Construct the destination file path
                    String fileName = file.getName(); // Get the name of the file
                    Path destPath = Paths.get(destinationDir, fileName);

                    // Copy the selected file to the destination directory
                    Files.copy(file.toPath(), destPath, StandardCopyOption.REPLACE_EXISTING);

                    System.out.println("File saved to: " + destPath);

                    // Optionally, you can set an ImageIcon to display the uploaded image
                    ImageIcon icon = new ImageIcon(filePath);
                    // Display the ImageIcon somewhere in your application if needed

                } catch (IOException ex) {
                    ex.printStackTrace();
                    // Handle error
                }
            }
        });

        addImagePanel.add(addImageButton);

        wraperPanel.add(scrollPanel, BorderLayout.CENTER);
        wraperPanel.add(addImagePanel, BorderLayout.SOUTH);

        return wraperPanel;
    }

    public void populateThumbnailPanel(JPanel thumbnailPanel) {
        try {
            for (int i = 0; i < imagePaths.size(); i++) {
                String imagePath = imagePaths.get(i);
                JLabel label = new JLabel();
                ImageIcon icon = new ImageIcon(
                        new ImageIcon(getClass().getResource(imagePath)).getImage()
                                .getScaledInstance(150, 100, Image.SCALE_SMOOTH));
                label.setIcon(icon);
                label.setHorizontalAlignment(SwingConstants.CENTER);

                // Add mouse listener to handle clicks
                int index = i;
                label.addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mousePressed(java.awt.event.MouseEvent evt) {
                        System.out.println("Image clicked: " + imagePath);
                        currentImageIndex = index;
                        updateMainImage();
                    }
                });

                thumbnailPanel.add(label);
                this.thumbnails.add(label);
                thumbnailPanel.add(Box.createVerticalStrut(10));
            }
        } catch (Exception e) {
            e.toString();
            if (imagePaths.size() == 0) {
                mainImage.setText("No images found");
            } else {
                mainImage.setText("Error loading images");
            }
        }

    }

    private void updateMainImage() {
        String imagePath = imagePaths.get(currentImageIndex);
        ImageIcon icon = new ImageIcon(getClass().getResource(imagePath));
        mainImage.setText("");
        mainImage.setIcon(icon);

        // Reset border/background for all labels
        for (JLabel label : thumbnails) {
            label.setBorder(null); // Reset border
            label.setOpaque(false); // Reset background

        }
        // Set background color for the selected thumbnail
        thumbnails.get(currentImageIndex).setBorder(BorderFactory.createLineBorder(new Color(0x1e40af), 2));

    }

}
