package Custom;

import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.Color;

public class VideoGaleria extends JPanel {

    private Object[] project;
    private JLabel mainVideo = new JLabel();
    private List<String> videoPaths;
    private int currentVideoIndex = 0;
    private List<JLabel> thumbnails;

    public VideoGaleria(Object[] pyto) {

        project = pyto;
        thumbnails = new ArrayList<>();
        videoPaths = new ArrayList<>();
        videoPaths.add("/storage/" + project[0] + "/videos/1.jpg");
        videoPaths.add("/storage/" + project[0] + "/videos/2.jpg");
        videoPaths.add("/storage/" + project[0] + "/videos/3.jpg");

        this.setLayout(new BorderLayout());

        JPanel mainPanel = getMainPanel();
        JScrollPane thumbnailPanel = getThumbnailPanel();

        this.add(mainPanel, BorderLayout.CENTER);
        this.add(thumbnailPanel, BorderLayout.WEST);

    }

    public JPanel getMainPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setBackground(Color.WHITE);

        mainVideo.setText("Select a video to view it in full size");
        mainVideo.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel controlPanel = getControlPanel();

        panel.add(mainVideo, BorderLayout.CENTER);
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

        JButton prevButton = new JButton("Previous");
        JButton nextButton = new JButton("Next");
        prevButton.addActionListener(e -> {
            currentVideoIndex = (currentVideoIndex - 1 + videoPaths.size()) % videoPaths.size();
            updateMainImage();
        });
        nextButton.addActionListener(e -> {
            currentVideoIndex = (currentVideoIndex + 1) % videoPaths.size();
            updateMainImage();
        });
        panel.add(Box.createHorizontalGlue()); // Adds flexible space before the buttons
        panel.add(prevButton);
        panel.add(Box.createHorizontalStrut(10)); // Fixed space between buttons
        panel.add(nextButton);
        panel.add(Box.createHorizontalGlue()); // Adds flexible space after the buttons

        return panel;
    }

    public JScrollPane getThumbnailPanel() {
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

        return scrollPanel;
    }

    public void populateThumbnailPanel(JPanel thumbnailPanel) {
        try {
            for (int i = 0; i < videoPaths.size(); i++) {
                String imagePath = videoPaths.get(i);
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
                        currentVideoIndex = index;
                        updateMainImage();
                    }
                });

                thumbnailPanel.add(label);
                this.thumbnails.add(label);
                thumbnailPanel.add(Box.createVerticalStrut(10));
            }
        } catch (Exception e) {
            e.toString();
            if (videoPaths.size() == 0) {
                mainVideo.setText("No images found");
            } else {
                mainVideo.setText("Error loading images");
            }
        }

    }

    private void updateMainImage() {
        String imagePath = videoPaths.get(currentVideoIndex);
        ImageIcon icon = new ImageIcon(getClass().getResource(imagePath));
        mainVideo.setText("");
        mainVideo.setIcon(icon);

        // Reset border/background for all labels
        for (JLabel label : thumbnails) {
            label.setBorder(null); // Reset border
            label.setOpaque(false); // Reset background

        }
        // Set background color for the selected thumbnail
        thumbnails.get(currentVideoIndex).setBorder(BorderFactory.createLineBorder(new Color(0x1e40af), 2));

    }
}
