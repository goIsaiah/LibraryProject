package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

class MiniProfile extends JPanel{
	private JLabel label; 
	private JPanel photo; 
	
	public MiniProfile(String text) {
		init(); 
		label = new JLabel();
		label.setText("<html>"+ text.replaceAll("\n", "<br/>") +"</html>");
	   	 setPhoto(); 
		add(photo, BorderLayout.WEST);
		add(label, BorderLayout.CENTER);
	}
	
	private void init() {
		setFont(new Font("Arial", Font.BOLD, 20));
		BorderLayout layout = new BorderLayout(); 
		layout.setHgap(5);
		setPreferredSize(new Dimension(300, 50));
		this.setLayout(layout);
	}
	
	private void setPhoto() {
		photo = new JPanel();
		ImageIcon profilePic = null;
    	try {
    		profilePic = new ImageIcon(new URL("https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_1280.png"));
    		JLabel picLabel = photoLabelConfig(profilePic);
    		photo.add(picLabel);
		} catch (MalformedURLException e1) {
		}
		
	}
	private JLabel photoLabelConfig(ImageIcon profilePic) {
	       Image scaledImage = profilePic.getImage().getScaledInstance(30, -1, Image.SCALE_SMOOTH); // scale the image
	        ImageIcon scaledIcon = new ImageIcon(scaledImage);
	        JLabel picLabel = new JLabel(scaledIcon);
	        picLabel.setPreferredSize(new Dimension(30, 30)); // set fixed size
	        picLabel.setMinimumSize(new Dimension(30, 30)); // set minimum size to prevent distortion
	        picLabel.setMaximumSize(new Dimension(30, 30)); // set maximum size to prevent stretching
	        
		return picLabel; 
	}
	
   public void setPhoto(String url)  {
	   photo.removeAll();
	   photo.revalidate();
	   photo.repaint();
		
        ImageIcon profilePic;
        try {
            profilePic = new ImageIcon(new URL(url));
            photo.add(  photoLabelConfig(profilePic) );
          
        } catch (MalformedURLException e) {
        	try {
				profilePic = new ImageIcon(new URL("https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_1280.png"));
			    photo.add(  photoLabelConfig(profilePic) );
        	} catch (MalformedURLException e1) {
			}
        }
	}
	
}