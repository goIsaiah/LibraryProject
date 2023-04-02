package GUI;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.Timer;

import Databases.DBUser;
import Databases.DBUserInfo;
import DomainObjects.User;
import net.miginfocom.swing.MigLayout;

class FriendPanel {
    private JScrollPane pane;
    private int previousPosition;
    private Template parentTemplate;
    
    public FriendPanel() {
        pane = new JScrollPane(new FriendList());
        pane.setPreferredSize(new Dimension(300, 500));
        pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    }

    public JScrollPane getPane() {
        return pane;
    }

    class FriendList extends JPanel {
        private Timer stopwatch;

        public FriendList() {
            this.setPreferredSize(new Dimension(300, 500));
            this.setLayout(new MigLayout("wrap", "[grow, fill]", "[][][]"));
            refreshTimer();
        }

        private void refreshTimer() {
            stopwatch = new Timer(1000, refresh());
            stopwatch.setInitialDelay(0);
            stopwatch.start();
        }

        private ActionListener refresh() {
            ActionListener action = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
                        @Override
                        protected Void doInBackground() throws Exception {
                            try {
                                previousPosition = pane.getVerticalScrollBar().getValue();
                                pane.getVerticalScrollBar().setValue(previousPosition);
                                addContent();
                            } catch (SQLException e1) {
                                // e1.printStackTrace();
                            }
                            return null;
                        }
                    };
                    worker.execute();
                }
            };
            return action;
        }

        private void addContent() throws SQLException {
            DBUser user = new DBUser();
            DBUserInfo info = new DBUserInfo();
            Hashtable<Integer, String> list = user.getUserInfo();
            ArrayList<Integer> set = new ArrayList<>(list.keySet());

            // Create an off-screen JPanel to hold the new content
            JPanel offScreenPanel = new JPanel(new MigLayout("wrap", "[grow, fill]", "[][][]"));
            offScreenPanel.setOpaque(false);
            parentTemplate = (Template)SwingUtilities.getWindowAncestor(this);
            for (int i = 0; i < set.size(); i++) {
            	int user_id = set.get(i);
                MiniProfile label1 = new MiniProfile(list.get(user_id));
                label1.setPhoto(info.getPhotoUrl(user_id));
                
                label1.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                    	//TODO call a new JFrame
                    	User friend = user.getUser(user_id);
                    	parentTemplate.userF = friend; 
                    	System.out.println(friend.getUsername());
                    	parentTemplate.showPanel("fProfile");
                        System.out.println("Yay you clicked me");
                    }
                });
                offScreenPanel.add(label1);
            }

            // Set the preferred height dynamically based on the number of users
            int userCount = set.size();
            int rowHeight = 50; // You can adjust this value according to the height of a single user entry
            this.setPreferredSize(new Dimension(300, rowHeight * userCount));

            // Swap the current content with the off-screen content
            SwingUtilities.invokeLater(() -> {
                removeAll();
                add(offScreenPanel);
                revalidate();
                repaint();
            });
        }
    }
}
