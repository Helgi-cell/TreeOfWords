/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package treeofwords;


import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import static treeofwords.FindBySignature.headers;


/**
 *
 * @author User
 */
public class TableSort {

    JTable jTabPeople;

    /**
     * Table for data sorting
     * @param str - данные для заголовка
     * @param x   - координата вывода таблицы
     * @param y   - координата вывода таблицы
     * @param dat - строковые данные вывода в таблицу
     */
    public  void tableSort(String str, int x, int y, String[][] dat) {

        JFrame jfrm = new JFrame(str);
        //Устанавливаем диспетчер компоновки
        jfrm.getContentPane().setLayout(new FlowLayout());
        //Устанавливаем размер окна

        jfrm.setLocation(x, y);
        jfrm.setSize(650, 800);
        //Устанавливаем завершение программы при закрытии окна
        jfrm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //Создаем новую таблицу на основе двумерного массива данных и заголовков
        jTabPeople = new JTable(dat, headers);
        //Создаем панель прокрутки и включаем в ее состав нашу таблицу
        JScrollPane jscrlp = new JScrollPane(jTabPeople);
        //Устаналиваем размеры прокручиваемой области
        jTabPeople.setPreferredScrollableViewportSize(new Dimension(600, 700));
        //Добавляем в контейнер нашу панель прокрути и таблицу вместе с ней
        jfrm.getContentPane().add(jscrlp);
        //Отображаем контейнер
        jfrm.setVisible(true);

    }
}
