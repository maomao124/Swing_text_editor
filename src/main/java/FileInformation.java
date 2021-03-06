import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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
        jTextArea_FileInformation = new JTextArea(15, 55);
        jTextArea_FileInformation.setLineWrap(true);
        jTextArea_FileInformation.setEditable(false);
        Font font = new Font("宋体", Font.PLAIN, 22);
        jTextArea_FileInformation.setFont(font);
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
        jTextArea_FileInformation.setText("\t\t文件信息：\n\n");
        File file = test.getFile();
        DecimalFormat decimalFormat = new DecimalFormat("###.##");
        jTextArea_FileInformation.append("\t文件名称：" + file.getName());
        if (file.length() < 1048576)
        {
            jTextArea_FileInformation.append("\n\t文件大小：" + file.length() + "字节  =" +
                    decimalFormat.format((double) file.length() / 1024) + "KB");
        }
        else
        {
            jTextArea_FileInformation.append("\n\t文件大小：" + file.length() + "字节  =" +
                    decimalFormat.format((double)file.length() / 1024) + "KB  =" +
                    decimalFormat.format((double)(file.length() / 1024 / 1024)) + "MB");
        }
        jTextArea_FileInformation.append("\n\t文件相对路径：" + file.getPath());
        jTextArea_FileInformation.append("\n\t文件绝对路径：" + file.getAbsolutePath());
        if (file.canRead())
        {
            jTextArea_FileInformation.append("\n\t文件是否能读？：是");
        }
        else
        {
            jTextArea_FileInformation.append("\n\t文件是否能读？：否");
        }
        if (file.canWrite())
        {
            jTextArea_FileInformation.append("\n\t文件是否能写？：是");
        }
        else
        {
            jTextArea_FileInformation.append("\n\t文件是否能写？：否");
        }
        Date date = new Date(file.lastModified());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年 MM月 dd日   E   HH点 mm分 ss秒");
        jTextArea_FileInformation.append("\n\t最后修改时间：" + simpleDateFormat.format(date));
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
