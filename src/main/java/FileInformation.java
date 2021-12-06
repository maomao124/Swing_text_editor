import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Project name(项目名称)：Swing文本编辑器
 * Package(包名): PACKAGE_NAME
 * Class(类名): FileInformation
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2021/12/6
 * Time(创建时间)： 14:42
 * Version(版本): 1.0
 * Description(描述)： 无
 */

public class FileInformation
{
    private static JTextArea jTextArea_FileInformation;
    private static JScrollPane jScrollPane;

    public static void init()
    {
        jTextArea_FileInformation = new JTextArea(30, 60);
        jScrollPane = new JScrollPane(jTextArea_FileInformation);
        JPanel jPanel = new JPanel();
        test.setjPanel1(jPanel);
        jPanel.setLayout(new BorderLayout());
        JButton button = test.getButton4();
        jScrollPane.setBorder(new EmptyBorder(20, 100, 50, 100));
        //button.setBorder(new EmptyBorder(20, 20, 20, 20));
        jPanel.add(jScrollPane, BorderLayout.CENTER);
        JPanel jPanel2 = new JPanel();
        jPanel2.setLayout(new FlowLayout());
        jPanel2.add(button);
        jPanel.add(jPanel2, BorderLayout.SOUTH);
        button.setBackground(Color.cyan);
        button.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                back();
            }
        });
        test.getButton3().addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                display();
            }
        });
    }

    public static void display()
    {
        if (test.getFile() == null)
        {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null,
                    "还未指定文件目录！！！", "提示", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JFrame jFrame = test.getjFrame();
        jFrame.remove(test.getjPanel());
        jFrame.add(test.getjPanel1());
        test.getjPanel1().updateUI();
        jFrame.repaint();
    }

    public static void back()               //返回到原来的面板
    {
        JFrame jFrame = test.getjFrame();
        jFrame.remove(test.getjPanel1());
        jFrame.add(test.getjPanel());
        jFrame.repaint();

    }
}
