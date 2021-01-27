import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class Slideshow extends JFrame{

    // Declaring UI components
    final private JPanel  cbPanel, imagePanel, sliderPanel, buttonPanel, actionPanel;
    final private JComboBox cb;
    final private JLabel picLabel;
    final private JSlider sizeSlider;
    final private JButton buttons[];
    final private Container container;

    // 2D array to store images read from the files
    BufferedImage images[][] = new BufferedImage[3][3];

    String choices[];
    // 2D array to store colors for image background
    Color colors[][] = {{Color.LIGHT_GRAY, Color.YELLOW, Color.BLACK},
                        {Color.BLUE, Color.DARK_GRAY, Color.GREEN},
                        {Color.CYAN, Color.MAGENTA, Color.RED}};

    String imgPath = "./resources/";
    int pos = 0, ctr = 0, i;
    int defaultSliderValue = 200;
    double ratio;


    public Slideshow(){

        super ("Slide shows");

        // Initializing the values of the arrays
        choices = new String[]{"Animals", "Flower", "Food"};
        buttons = new JButton[]{ new JButton("Animal Background Color"),
                                 new JButton("Flower Background Color"),
                                 new JButton("Food Background Color") };

        // Reading images from the respective files and populating into the 2D array
        try{
            for(i = 0; i < 3; i++){
                images[0][i] = ImageIO.read(new File(imgPath+"animals/"+i+".jpg"));
                images[1][i] = ImageIO.read(new File(imgPath+"flowers/"+i+".jpg"));
                images[2][i] = ImageIO.read(new File(imgPath+"food/"+i+".jpg"));
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }

        // Initializing all the UI components
        container = getContentPane();
        container.setLayout(new BorderLayout());

        imagePanel = new JPanel();
        buttonPanel = new JPanel();
        sliderPanel = new JPanel();
        cbPanel = new JPanel();
        actionPanel = new JPanel();
        actionPanel.setLayout(new BorderLayout());

        cb = new JComboBox<>(choices);
        picLabel = new JLabel();
        sizeSlider = new JSlider( SwingConstants.HORIZONTAL);

        // Calling setUpGUI() function to prepare the GUI interface
        setUpGUI();
        // Calling the evenHandling() function that handles all the interaction
        eventsHandling();

    }

    /*
     * Function Name    :   setUpGUI
     * Parameters       :   None
     * Returns          :   None
     * Description      :   Creates the user interface that consist of Drop down menu, Image panel,
     *                      Size slider and 3 background color change buttons
     */
    public void setUpGUI(){

        // Configuring drop down menu and adding to a panel
        cb.setVisible(true);
        cb.setFocusable(false);
        cbPanel.add(cb);

        // Configuring Image area
        // Setting default image (1st element of the 2D buffered image array)
        picLabel.setIcon(new ImageIcon(new ImageIcon(images[0][pos]).getImage().getScaledInstance(400, 200, 1)));
        imagePanel.add(picLabel);
        // Setting default background color (1st color of the 2D color array)
        imagePanel.setBackground(colors[0][0]);
        /*
         * setFocusable() is set true only for Image panel so that is always in focus which enables
         * key press ('n' and 'p') on the keyboard to be used only for changing the image all the time
         */
        imagePanel.setFocusable(true);

        // Configuring the slider with min, max and tick values
        sizeSlider.setMajorTickSpacing( 10 );
        sizeSlider.setMinimum(defaultSliderValue);
        sizeSlider.setMaximum(500);
        sizeSlider.setPaintTicks( true );
        sizeSlider.setFocusable(false);
        sliderPanel.add(sizeSlider);

        // Setting the layout and border for the button Panel
        buttonPanel.setLayout(new GridLayout(1, buttons.length, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        // Adding all the buttons to the button panel using loop
        for (i = 0; i < buttons.length; i++){
            buttons[i].setFocusable(false);
            buttonPanel.add(buttons[i]);
        }

        // Combining the slider and button panel to a single panel for easier layout design
        actionPanel.add(sliderPanel, BorderLayout.NORTH);
        actionPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Adding the 3 panels to the container
        container.add(cbPanel, BorderLayout.NORTH);
        container.add(imagePanel, BorderLayout.CENTER);
        container.add(actionPanel, BorderLayout.SOUTH);

        // Setting the size and visibility of the window
        setSize( 720, 480 );
        setVisible( true );
    }

    /*
     * Function Name    :   eventsHandling
     * Parameters       :   None
     * Returns          :   None
     * Description      :   Getting the user interaction and changing the contents on the screen accordingly
     *                      that includes changing the category of slideshow, traversing through the image collection,
     *                      changing size fo image and changing the background color of the images
     */
    public void eventsHandling(){

        // Item listener for drop down menu
        cb.addItemListener(
                itemEvent -> {
                    if(itemEvent.getStateChange() == ItemEvent.SELECTED) {
                        // Setting the image according to the category and it's 1st image
                        refreshImage(pos = 0, picLabel, cb.getSelectedIndex(), defaultSliderValue);
                        // Setting default background color for that category
                        imagePanel.setBackground(colors[cb.getSelectedIndex()][ctr = 0]);
                        // Resetting the slide value
                        sizeSlider.setValue(defaultSliderValue);
                    }
                   // System.out.println(cb.getSelectedIndex());
                }
        );

        // Listening to the changes in slider values
        sizeSlider.addChangeListener(
                changeEvent -> {
                    // Refreshing the image accroding to the slider value
                    refreshImage(pos, picLabel, cb.getSelectedIndex(), sizeSlider.getValue());
                   // System.out.println(ratio);
                }
        );

        

        // Adding mouse click detection (e.getButton() = 1 for left click and 2 for right click)
        imagePanel.addMouseListener(
                new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (e.getButton() == 1 && pos > 0)
                            // Decreasing the index position by 1 if left click (moving to previous image)
                            pos--;
                        else if (e.getButton() == 3 && pos < 2)
                            // Increasing the index position by 1 if right click (moving to next image)
                            pos++;
                        // Refreshing the image according to the changed position
                        refreshImage(pos, picLabel, cb.getSelectedIndex(), defaultSliderValue);
                        // Resetting the slider value
                        sizeSlider.setValue(defaultSliderValue);
                    }
                }
        );

        // Adding Key press detection ('p' -> previous image, 'n' -> next image )
        imagePanel.addKeyListener(
                new KeyAdapter() {
                    @Override
                    public void keyPressed(KeyEvent e) {
                        if(e.getKeyChar() == 'n' && pos < 2)
                            // Increasing the pos index by 1
                            pos++;
                        else if(e.getKeyChar() == 'p' && pos > 0)
                            // Decreasing the pos index by 1
                            pos--;
                        // Refreshing the image according to the changed position
                        refreshImage(pos, picLabel, cb.getSelectedIndex(), defaultSliderValue);
                        // Resetting the slider value
                        sizeSlider.setValue(defaultSliderValue);
                    }
                }
        );

        // Adding Button click and changing the background accordingly for all the buttons using loop
        for (i = 0; i < buttons.length; i++){
            // Initializing local variable to access inside the lambda function
            int j = i;
            buttons[i].addActionListener(
                    actionEvent -> {
                        // Checking whether the button corresponds to the current category
                        if( j == cb.getSelectedIndex()){
                            /*
                             * if condition to check if end of array is reached so that color can
                             * be set back to the 1st one (behaves as circular loop)
                             */
                            if (ctr < 2)
                                ++ctr;
                            else
                                ctr = 0;
                            // Refreshing the background colour according to the changed counter value
                            imagePanel.setBackground(colors[cb.getSelectedIndex()][ctr]);
                        }
                    }
            );
        }

    }

    /*
     * Function Name    :   refreshImage
     * Parameters       :   position of image in the category - pos(int),
     *                      Image Label - picLabel (JLabel),
     *                      Dropdown menu selected index - cbIndex (int),
     *                      Value of size Slider - sliderValue (int)
     * Returns          :   None
     * Description      :   1. Calculates the ratio for the corresponding image
     *                      2. Resets the the picLabel with the image according to the position and category, resized
     *                         using slider value (The image ratio is preserved by multiplying with calculated ratio)
     */
    public void refreshImage(int pos, JLabel picLabel, int cbIndex, int sliderValue){
        // Calculating the ratio by dividing width by height of the image
        ratio = 1 + images[cbIndex][pos].getWidth()/images[cbIndex][pos].getHeight();
        //  Resizing with sliderValue and ratio of the image at pos in the selected category
        Image resizedImage = new ImageIcon(images[cbIndex][pos]).getImage().getScaledInstance((int)(sliderValue*Math.ceil(ratio)),sliderValue, 1);
        // Setting the resized image to the picLabel
        picLabel.setIcon(new ImageIcon(resizedImage));
    }

    public static void main(String args[]){

        Slideshow application = new Slideshow();

        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
