import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;


/**
 * Project name(项目名称)：Swing文本编辑器
 * Package(包名): PACKAGE_NAME
 * Class(类名): test
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2021/12/5
 * Time(创建时间)： 20:38
 * Version(版本): 1.0
 * Description(描述)： 无
 */

public class test
{
    private static JFrame jFrame;
    private static JPanel jPanel;
    private static JPanel jPanel1;    //文件信息面板
    private JLabel label = new JLabel("所选文件路径：");
    private JTextField jTextField = new JTextField(25);
    private JButton button = new JButton("浏览");
    private JButton button1 = new JButton("保存");
    private JButton button_save_file = new JButton("另存为");
    private JButton button2 = new JButton("编辑模式");
    private JButton button3 = new JButton("文件信息");
    private static JButton button4 = new JButton("<-返回");

    boolean isEditable = true;
    private static File file;
    private JLabel label2 = new JLabel("欢迎使用文件编辑器", JLabel.CENTER);      //状态位

    public test()
    {
        jFrame = new JFrame("文件选择测试-文件编辑器");
        jFrame.setSize(1280, 720);
        jFrame.setLocation(1920 / 2 - 640, 1080 / 2 - 360);
        jFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        jPanel = new JPanel();
        JTextArea jTextArea = new JTextArea(720 / 28, 1280 / 11);
        JScrollPane jScrollPane = new JScrollPane();
        Font font = new Font("宋体", Font.PLAIN, 19);
        jTextArea.setLineWrap(true);
        jTextArea.setFont(font);
        button.setBackground(Color.cyan);
        button1.setBackground(Color.cyan);
        button2.setBackground(Color.green);
        jScrollPane.setViewportView(jTextArea);
        jTextArea.setEditable(isEditable);
        jPanel.add(label);
        jPanel.add(jTextField);
        jPanel.add(button);
        jPanel.add(button1);
        jPanel.add(button_save_file);
        button_save_file.setBackground(Color.cyan);
        jPanel.add(button2);
        button3.setBackground(Color.cyan);
        jPanel.add(button3);
        //jPanel.add(jTextArea);
        jPanel.add(jScrollPane);
        label2.setPreferredSize(new Dimension(800, 30));
        jPanel.add(label2);
        jFrame.add(jPanel);
        jFrame.setVisible(true);
        jFrame.addWindowListener(new WindowListener()
        {
            @Override
            public void windowOpened(WindowEvent e)
            {

            }

            @Override
            public void windowClosing(WindowEvent e)
            {
                if (jTextArea.getText().length() == 0)
                {
                    System.exit(1);
                }
                String fileMD5 = null;
                String testAreaMD5 = null;
                if (file != null)
                {
                    label2.setText("请稍后，正在计算MD5值...");
                    fileMD5 = MD5.getFileMD5(file.getAbsolutePath());
                    testAreaMD5 = MD5.getMD5(jTextArea.getText());
                    label2.setText("MD5值计算完成");
                }
                if (file == null)
                {
                    int result;
                    Toolkit.getDefaultToolkit().beep();
                    result = JOptionPane.showConfirmDialog(null, "文本还未保存！ 是否退出？",
                            "退出提示", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
                    if (result == JOptionPane.YES_OPTION)
                    {
                        System.exit(1);
                    }
                    else if (result == 1)
                    {
                        label2.setText("取消退出");
                    }
                    else
                    {
                        label2.setText("关闭会话框，取消退出");
                    }
                }
                else if (fileMD5.equals(testAreaMD5))         //MD5值相同,直接退出
                {
                    System.exit(1);
                }
                else if (fileMD5 == null || testAreaMD5 == null)
                {
                    int result;
                    Toolkit.getDefaultToolkit().beep();
                    result = JOptionPane.showConfirmDialog(null,
                            "无法计算MD5值！ 是否退出？\n文件MD5：" + fileMD5 + "\n文本域MD5:" + testAreaMD5,
                            "退出提示", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
                    if (result == JOptionPane.YES_OPTION)
                    {
                        System.exit(1);
                    }
                    else if (result == 1)
                    {
                        label2.setText("取消退出");
                    }
                    else
                    {
                        label2.setText("关闭会话框，取消退出");
                    }
                }
                else
                {
                    int result;
                    Toolkit.getDefaultToolkit().beep();
                    result = JOptionPane.showConfirmDialog(null,
                            "文本还有一部分未保存！ 是否退出？\n文件MD5：" + fileMD5 + "\n文本域MD5:" + testAreaMD5,
                            "退出提示", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
                    if (result == JOptionPane.YES_OPTION)
                    {
                        System.exit(1);
                    }
                    else if (result == 1)
                    {
                        label2.setText("取消退出");
                    }
                    else
                    {
                        label2.setText("关闭会话框，取消退出");
                    }
                }
            }

            @Override
            public void windowClosed(WindowEvent e)
            {

            }

            @Override
            public void windowIconified(WindowEvent e)
            {

            }

            @Override
            public void windowDeiconified(WindowEvent e)
            {

            }

            @Override
            public void windowActivated(WindowEvent e)
            {

            }

            @Override
            public void windowDeactivated(WindowEvent e)
            {

            }
        });
        jFrame.addComponentListener(new ComponentListener()
        {
            @Override
            public void componentResized(ComponentEvent e)
            {
                int width = jFrame.getWidth();
                int height = jFrame.getHeight();
                if (height <= 500)
                {
                    jTextArea.setRows(height / 30);
                    jTextArea.setColumns(width / 11);
                }
                else if (height > 1000)
                {
                    jTextArea.setRows(height / 26);
                    jTextArea.setColumns(width / 11);
                }
                else
                {
                    jTextArea.setRows(height / 28);
                    jTextArea.setColumns(width / 11);
                }
            }

            @Override
            public void componentMoved(ComponentEvent e)
            {

            }

            @Override
            public void componentShown(ComponentEvent e)
            {

            }

            @Override
            public void componentHidden(ComponentEvent e)
            {

            }
        });
        jFrame.addWindowStateListener(new WindowStateListener()
        {
            @Override
            public void windowStateChanged(WindowEvent e)
            {
                int width = jFrame.getWidth();
                int height = jFrame.getHeight();
                jTextArea.setRows(height / 27);
                jTextArea.setColumns(width / 11);
            }
        });
        jTextArea.addCaretListener(new CaretListener()
        {
            @Override
            public void caretUpdate(CaretEvent e)
            {
                try
                {
                    int pos = jTextArea.getCaretPosition();
                    //获取行数
                    int lineOfC = 0;
                    lineOfC = jTextArea.getLineOfOffset(pos) + 1;
                    //获取列数
                    int col = pos - jTextArea.getLineStartOffset(lineOfC - 1) + 1;
                    //System.out.println("当前光标位置:" + lineOfC + "行," + col + "列");
                    label2.setText("当前光标位置：第" + lineOfC + "行,第" + col + "列");
                }
                catch (BadLocationException e1)
                {
                    System.out.println("无法获取光标位置");
                    label2.setText("无法获取光标位置");
                    //e1.printStackTrace();
                }
            }
        });
        button_save_file.addActionListener(new ActionListener()
        {                                               //另存为
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (jTextArea.getText().length() == 0)
                {
                    label2.setText("文本域为空,没必要保存");
                    return;
                }
                JFileChooser jFileChooser = new JFileChooser(".");
                int result = jFileChooser.showSaveDialog(null);
                if (result == JFileChooser.APPROVE_OPTION)
                {
                    File file = jFileChooser.getSelectedFile();
                    if (test.file == null)
                    {
                        test.file = file;
                        jTextField.setText(test.file.getAbsolutePath());
                    }
                    FileWriter fileWriter = null;
                    try                                  //文件流打开，文件读写
                    {
                        fileWriter = new FileWriter(file);
                        fileWriter.write(jTextArea.getText());
                        label2.setText("保存成功");
                    }
                    catch (FileNotFoundException e1)      //文件未找到
                    {
                        Toolkit.getDefaultToolkit().beep();
                        System.err.println("文件未找到！！！  " + "\n错误内容：" + e.toString());
                    }
                    catch (Exception e1)                  //其它异常
                    {
                        Toolkit.getDefaultToolkit().beep();
                        e1.printStackTrace();
                    }
                    finally
                    {
                        try                              //关闭流
                        {
                            if (fileWriter != null)
                            {
                                fileWriter.close();
                            }
                        }
                        catch (NullPointerException e1)    //空指针异常
                        {
                            Toolkit.getDefaultToolkit().beep();
                            System.err.println("文件已经被关闭，无法再次关闭！！！");
                        }
                        catch (Exception e1)              //其它异常
                        {
                            Toolkit.getDefaultToolkit().beep();
                            e1.printStackTrace();
                        }
                    }
                }
                else
                {
                    Toolkit.getDefaultToolkit().beep();
                    label2.setText("未成功保存！！！");
                }
            }
        });
        button2.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (isEditable)
                {
                    button2.setText("只读模式");
                    button2.setBackground(Color.yellow);
                    isEditable = false;
                    jTextArea.setEditable(false);
                    label2.setText("当前为只读模式");
                }
                else
                {
                    button2.setText("编辑模式");
                    button2.setBackground(Color.green);
                    isEditable = true;
                    jTextArea.setEditable(true);
                    label2.setText("当前为编辑模式");
                }
            }
        });
        button.addActionListener(new ActionListener()               //浏览
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (jTextArea.getText().length() != 0)
                {
                    //todo:是否追加
                    String[] selection = {"文件数据插入到文本域的后面", "使用文件里的数据替换文本域里的数据"};
                    Toolkit.getDefaultToolkit().beep();
                    int result;
                    result = JOptionPane.showOptionDialog(null, "文本域数据不为空！请选择更新模式！"
                            , "警告", 0, 0, null, selection, 0);
                    if (result == 0)
                    {
                        label2.setText("从第" + (jTextArea.getText().length() - 1) + "个位置插入文件数据");
                    }
                    else if (result == 1)
                    {
                        jTextArea.setText("");
                        label2.setText("文本域原来的数据已丢失");
                    }
                    else             //按到了关闭按钮
                    {
                        label2.setText("取消操作");
                        return;
                    }
                }
                JFileChooser jFileChooser = new JFileChooser(".");
                int result = jFileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION)
                {
                    jTextField.setText(jFileChooser.getSelectedFile().toString());
                    file = jFileChooser.getSelectedFile();


                    FileReader fileReader = null;
                    try                                  //文件流打开，文件读写
                    {
                        fileReader = new FileReader(file);
                        char[] buffer = new char[1024];
                        int count = 0;
                        while ((count = fileReader.read(buffer)) != -1)
                        {
                            jTextArea.append(new String(buffer, 0, count));
                            //System.out.println(new String(buffer, 0, count));
                        }

                    }
                    catch (FileNotFoundException e1)      //文件未找到
                    {
                        Toolkit.getDefaultToolkit().beep();
                        System.err.println("文件未找到！！！  " + "\n错误内容：" + e.toString());
                    }
                    catch (Exception e1)                  //其它异常
                    {
                        Toolkit.getDefaultToolkit().beep();
                        e1.printStackTrace();
                    }
                    finally
                    {
                        try                              //关闭流
                        {
                            if (fileReader != null)
                            {
                                fileReader.close();
                            }
                        }
                        catch (NullPointerException e1)    //空指针异常
                        {
                            Toolkit.getDefaultToolkit().beep();
                            System.err.println("文件已经被关闭，无法再次关闭！！！");
                        }
                        catch (Exception e1)              //其它异常
                        {
                            Toolkit.getDefaultToolkit().beep();
                            e1.printStackTrace();
                        }
                    }
                }
                else
                {
                    Toolkit.getDefaultToolkit().beep();
                    label2.setText("未选择文件！！！");
                }
            }
        });
        button1.addActionListener(new ActionListener()
        {                                                           //保存
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (jTextArea.getText().length() == 0)
                {
                    label2.setText("文本域为空,没必要保存");
                    return;
                }
                if (test.file == null)
                {
                    JFileChooser jFileChooser = new JFileChooser(".");
                    int result = jFileChooser.showSaveDialog(null);
                    if (result == JFileChooser.APPROVE_OPTION)
                    {
                        File file = jFileChooser.getSelectedFile();
                        test.file = file;
                        jTextField.setText(test.file.getAbsolutePath());
                        FileWriter fileWriter = null;
                        try                                  //文件流打开，文件读写
                        {
                            fileWriter = new FileWriter(file);
                            fileWriter.write(jTextArea.getText());
                            label2.setText("保存成功");
                        }
                        catch (FileNotFoundException e1)      //文件未找到
                        {
                            Toolkit.getDefaultToolkit().beep();
                            System.err.println("文件未找到！！！  " + "\n错误内容：" + e.toString());
                        }
                        catch (Exception e1)                  //其它异常
                        {
                            Toolkit.getDefaultToolkit().beep();
                            e1.printStackTrace();
                        }
                        finally
                        {
                            try                              //关闭流
                            {
                                if (fileWriter != null)
                                {
                                    fileWriter.close();
                                }
                            }
                            catch (NullPointerException e1)    //空指针异常
                            {
                                Toolkit.getDefaultToolkit().beep();
                                System.err.println("文件已经被关闭，无法再次关闭！！！");
                            }
                            catch (Exception e1)              //其它异常
                            {
                                Toolkit.getDefaultToolkit().beep();
                                e1.printStackTrace();
                            }
                        }
                    }
                    else
                    {
                        Toolkit.getDefaultToolkit().beep();
                        label2.setText("未成功保存！！！");
                    }
                }
                else
                {
                    FileWriter fileWriter = null;
                    try                                  //文件流打开，文件读写
                    {
                        fileWriter = new FileWriter(test.file);
                        fileWriter.write(jTextArea.getText());
                        label2.setText("保存成功");
                    }
                    catch (FileNotFoundException e1)      //文件未找到
                    {
                        Toolkit.getDefaultToolkit().beep();
                        System.err.println("文件未找到！！！  " + "\n错误内容：" + e.toString());
                    }
                    catch (Exception e1)                  //其它异常
                    {
                        Toolkit.getDefaultToolkit().beep();
                        e1.printStackTrace();
                    }
                    finally
                    {
                        try                              //关闭流
                        {
                            if (fileWriter != null)
                            {
                                fileWriter.close();
                            }
                        }
                        catch (NullPointerException e1)    //空指针异常
                        {
                            Toolkit.getDefaultToolkit().beep();
                            System.err.println("文件已经被关闭，无法再次关闭！！！");
                        }
                        catch (Exception e1)              //其它异常
                        {
                            Toolkit.getDefaultToolkit().beep();
                            e1.printStackTrace();
                        }
                    }
                }
            }
        });
    }

    public static void main(String[] args)
    {
        new test();
    }
}
