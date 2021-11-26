package cocoa.week4.Sketch;

import javax.swing.plaf.basic.BasicTreeUI;
import java.awt.*;
import java.awt.dnd.MouseDragGestureRecognizer;
import java.awt.event.*;

/*1. 창 크기 바꾸고 옮기면 왜 어쩔때 버튼이 뭉개질까
 * 2. 마우스 드래그 중에 어떻게 그려질지 보고 싶으면(잔상 남게 하려면) 어떻게 해야하지?
 * 3. 곡선 문제 해결*/

public class AWTSketch extends Frame {
    int x1, x2, x3, x4;
    int y1, y2, y3, y4;
    Image img = null;
    Graphics graphics = null;
    Button bt1;
    Button bt2;
    Button bt3;
    Button bt4;
    Panel panel;
    String option = "사각형";

    public static void main(String[] args) {
        new AWTSketch("Sketch test 2");
    }

    public AWTSketch(String title) {
        super(title);
        MouseHandler mouse = new MouseHandler();
        addMouseMotionListener(mouse);
        addMouseListener(mouse);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        Buttons();
        setBounds(700, 300, 700, 500);
        setVisible(true);
        /*여기 순서 때문에 에러났었네..*/
        img = createImage(this.getWidth(), this.getHeight());
        graphics = img.getGraphics();
//repaint();
    }

    @Override
    public void paint(Graphics g) {
        if (img == null) return;
        paintComponents(g);
        /*여기 순서 바꾸니 또 되네..*/
        g.drawImage(img, 0, 0, this);
    }

    @Override
    public void update(Graphics g) {
        paint(g);
    }

    public void paintComponents(Graphics g) {
        super.paintComponents(g);
        if (option.equals("사각형")) {
            graphics.drawRect(Math.min(x2, x3), Math.min(y2, y3), Math.abs(x3 - x2), Math.abs(y3 - y2));
        }
        if (option.equals("원")) {
            graphics.drawOval(Math.min(x2, x3), Math.min(y2, y3), Math.abs(x3 - x2), Math.abs(y3 - y2));
        }
        if (option.equals("곡선")) {
            System.out.println("그리는중");
            graphics.drawLine(x2, y2, x4, y4);
            x2 = x4;
            y2 = y4;

            /* 이것 때문에 쌩고생..*/
//            repaint();
        }

        if (option.equals("직선")) {
            graphics.drawLine(x2, y2, x3, y3);
        }
    }

    class MouseHandler implements MouseMotionListener, MouseListener {
        @Override
        public void mouseMoved(MouseEvent e) {
            x1 = e.getX();
            y1 = e.getY();
        }

        @Override
        public void mousePressed(MouseEvent e) {
            x2 = e.getX();
            y2 = e.getY();
            System.out.print(x2 + " 클-");

        }

        @Override
        public void mouseReleased(MouseEvent e) {
            x3 = e.getX();
            y3 = e.getY();

            System.out.println("-릭 " + x3);
            repaint();

        }

        @Override
        public void mouseDragged(MouseEvent e) {
            x4 = e.getX();
            y4 = e.getY();

            /*여기 if 문 넣으니 되네..?*/
            if (option.equals("곡선")) {
                repaint();
            }
        }

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
    }

    public void Buttons() {
        panel = new Panel(new FlowLayout(1, 20, 5));
        add(panel, BorderLayout.NORTH);
        bt1 = new Button("사각형");
        bt2 = new Button("원");
        bt3 = new Button("곡선");
        bt4 = new Button("직선");
        panel.add(bt1);
        panel.add(bt2);
        panel.add(bt3);
        panel.add(bt4);
        bt1.addActionListener(new ActionHandler());
        bt2.addActionListener(new ActionHandler());
        bt3.addActionListener(new ActionHandler());
        bt4.addActionListener(new ActionHandler());
    }

    class ActionHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Button btn = (Button) e.getSource();

            if (btn.getLabel().equals("사각형")) {

                option = btn.getLabel();
                System.out.println(btn.getLabel());
                System.out.println(option);
            } else if (btn.getLabel().equals("원")) {

                option = btn.getLabel();
                System.out.println(btn.getLabel());
                System.out.println(option);
            } else if (btn.getLabel().equals("곡선")) {

                option = btn.getLabel();
                System.out.println(btn.getLabel());
                System.out.println(option);
            } else if (btn.getLabel().equals("직선")) {
                option = btn.getLabel();
                System.out.println(btn.getLabel());
                System.out.println(option);
            }
        }
    }
}






