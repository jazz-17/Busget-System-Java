package Custom;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.event.ActionEvent;

public class GaleriaProyecto extends JFrame {

    private Object[] project;
    private CardLayout cardLayout;
    private JPanel cardWrapper;

    public GaleriaProyecto(Object[] pyto) {
        project = pyto;
        setTitle("Galería de: " + project[1] + "");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel topPanel = getTopPanel();

        cardLayout = new CardLayout();
        cardWrapper = getCardWrapper(cardLayout);

        add(topPanel, BorderLayout.NORTH);
        add(cardWrapper, BorderLayout.CENTER);

        cardLayout.show(cardWrapper, "images");

        setVisible(true);
    }

    public JPanel getTopPanel() {
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

        JButton imagesButton = new JButton("Imágenes");
        JButton videosButton = new JButton("Videos");
        imagesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardWrapper, "images");
            }
        });

        videosButton.addActionListener(e -> {
            cardLayout.show(cardWrapper, "videos");
        });
        panel.add(Box.createHorizontalGlue()); // Adds flexible space before the buttons
        panel.add(imagesButton);
        panel.add(Box.createHorizontalStrut(10)); // Fixed space between buttons
        panel.add(videosButton);
        panel.add(Box.createHorizontalGlue()); // Adds flexible space after the buttons

        return panel;
    }

    public JPanel getCardWrapper(CardLayout cardLayout) {
        JPanel panel = new JPanel();
        panel.setLayout(cardLayout);

        panel.add(getImagesTab(), "images");
        panel.add(getVideosTab(), "videos");

        return panel;
    }

    public JPanel getImagesTab() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        ImagenGaleria imagesTab = new ImagenGaleria(project);
        panel.add(imagesTab, BorderLayout.CENTER);

        return panel;
    }

    public JPanel getVideosTab() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        VideoGaleria videosTab = new VideoGaleria(project);
        panel.add(videosTab, BorderLayout.CENTER);

        return panel;
    }
}
