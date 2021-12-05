import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Serial;

/**
 * Project name(项目名称)：Swing文本编辑器
 * Package(包名): PACKAGE_NAME
 * Class(类名): test2
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2021/12/5
 * Time(创建时间)： 20:19
 * Version(版本): 1.0
 * Description(描述)： 无
 */

public class test2 extends JFrame
{
    @Serial
    private static final long serialVersionUID = -9077023825514749548L;
    private final JTextArea ta_showText;    //定义显示文件属性的文本域
    private final JTextArea ta_showProperty;    //定义显示文件内容的文本域

    public test2()
    {
        setTitle("文本编辑器");    //设置窗体标题
        setBounds(200, 200, 1280, 720);    //设置窗体位置和大小
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);    //设置窗体默认关闭方式
        final JMenuBar menuBar = new JMenuBar();    //创建菜单栏
        setJMenuBar(menuBar);    //把菜单栏放到窗体上
        final JMenu mn_file = new JMenu();    //创建文件菜单
        mn_file.setText("文件");    //为文件菜单设置标题
        menuBar.add(mn_file);    //把文件菜单添加到菜单栏上
        final JMenuItem mi_open = new JMenuItem();    //创建打开菜单项
        mi_open.addActionListener(new ActionListener()
        {
            //为打开菜单项添加监听器
            public void actionPerformed(final ActionEvent arg0)
            {
                openTextFile();    //调用方法，操作文件
            }
        });
        mi_open.setText("打开");    //设置打开菜单项的标题
        mn_file.add(mi_open);    //把打开菜单项添加到文件菜单
        mn_file.addSeparator();    //添加菜单分隔符
        final JMenuItem mi_exit = new JMenuItem();    //创建退出菜单项
        mi_exit.addActionListener(new ActionListener()
        {
            //为退出菜单项添加监听器
            public void actionPerformed(final ActionEvent arg0)
            {
                System.exit(0);    //退出系统
            }
        });
        mi_exit.setText("退出");    //设置退出菜单项的标题
        mn_file.add(mi_exit);    //把退出菜单项添加到文件菜单
        final JMenu mn_edit = new JMenu();    //创建编辑菜单
        mn_edit.setText("编辑");    //为编辑菜单设置标题
        menuBar.add(mn_edit);    //把编辑菜单添加到菜单栏上
        final JMenuItem mi_copy = new JMenuItem();    //创建复制菜单项
        mi_copy.setText("复制");    //设置复制菜单项的标题
        mn_edit.add(mi_copy);    //把复制菜单项添加到编辑菜单
        final JMenuItem mi_cut = new JMenuItem();    //创建剪切菜单项
        mi_cut.setText("剪切");    //设置剪切菜单项的标题
        mn_edit.add(mi_cut);    //把剪切菜单项添加到编辑菜单
        final JMenuItem mi_paste = new JMenuItem();    //创建粘贴菜单项
        mi_paste.setText("粘贴");    //设置粘贴菜单项的标题
        mn_edit.add(mi_paste);    //把粘贴菜单项添加到编辑菜单
        final JToolBar toolBar = new JToolBar();    //创建工具栏
        getContentPane().add(toolBar, BorderLayout.NORTH);    //把工具栏放到窗体上方
        final JButton btn_open = new JButton();    //创建工具按钮
        btn_open.addActionListener(new ActionListener()
        {
            //添加动作监听器
            public void actionPerformed(final ActionEvent arg0)
            {
                openTextFile();    //调用方法，操作文件
            }
        });
        btn_open.setText("  打  开  ");    //设置工具按钮的标题
        toolBar.add(btn_open);    //把工具按钮添加到工具栏上
        final JButton btn_exit = new JButton();    //创建工具按钮
        btn_exit.addActionListener(new ActionListener()
        {
            //添加动作监听器
            public void actionPerformed(final ActionEvent arg0)
            {
                System.exit(0);    //退出系统
            }
        });
        btn_exit.setText("  退  出  ");    //设置工具按钮的标题
        toolBar.add(btn_exit);    //把工具按钮添加到工具栏上
        final JTabbedPane tabbedPane = new JTabbedPane();    //创建选项卡面板
        getContentPane().add(tabbedPane, BorderLayout.CENTER);    //把选项卡面板放到窗体中央
        final JScrollPane scrollPane1 = new JScrollPane();    //创建滚动面板
        //把滚动面板放到选项卡的第一个选项页
        tabbedPane.addTab("文件的属性", null, scrollPane1, null);
        ta_showProperty = new JTextArea();    //创建文本域
        //把文本域添加到滚动面板的视图中
        scrollPane1.setViewportView(ta_showProperty);
        final JScrollPane scrollPane2 = new JScrollPane();    //创建滚动面板
        //把滚动面板放到选项卡的第二个选项页
        tabbedPane.addTab("文件的内容", null, scrollPane2, null);
        ta_showText = new JTextArea();    //创建文本域
        //把文本域添加到滚动面板的视图中
        scrollPane2.setViewportView(ta_showText);
    }

    //用于打开文件并获得文件信息的方法
    public void openTextFile()
    {
        JFileChooser fileChooser = new JFileChooser();    //创建文件选择对话框
        fileChooser.setFileFilter(new FileNameExtensionFilter("文本文件", "txt"));
        int returnValue = fileChooser.showOpenDialog(getContentPane());    //打开文件选择对话框
        if (returnValue == JFileChooser.APPROVE_OPTION)
        {
            //判断用户是否选择了文件
            File file = fileChooser.getSelectedFile();    //获得文件对象
            //获得文件的绝对路径
            ta_showProperty.append("文件的绝对路径是：" + file.getAbsolutePath() + "\n");
            //是否为隐藏文件
            ta_showProperty.append("该文件是隐藏文件吗？" + file.isHidden() + "\n");
            FileReader reader;    //声明字符流
            BufferedReader in;    //声明字符缓冲流
            try
            {
                reader = new FileReader(file);    //创建字符流
                in = new BufferedReader(reader);    //创建字符缓冲流
                String info = in.readLine();    //从文件中读取一行信息
                while (info != null)
                {
                    //判断是否读到内容
                    ta_showText.append(info + "\n");    //把读到的信息追加到文本域中
                    info = in.readLine();    //继续读下一行信息
                }
                in.close();    //关闭字符缓冲流
                reader.close();    //关闭字符流
            }
            catch (Exception ex)
            {
                ex.printStackTrace();    //输出栈踪迹
            }
        }
    }

    public static void main(String[] args)
    {
        test2 frame = new test2();
        frame.setVisible(true);
    }
}
