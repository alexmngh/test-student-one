package students.logic;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.List;
import java.util.TreeSet;

public class ManagementSystem {

  private List<Group> groups;
  private Collection<Student> students;

  // Для шаблона Singletone статическая переменная
  private static ManagementSystem instance;

  // закрытый конструктор
  private ManagementSystem() {
//    loadGroups();
//    loadStudents();
  }

  // метод getInstance - проверяет, инициализирована ли статическая
  // переменная (в случае надобности делает это) и возвращает ее
  public static synchronized ManagementSystem getInstance() {
    if (instance == null) {
      instance = new ManagementSystem();
    }
    return instance;
  }

  // Метод, который вызывается при запуске класса
  public static void main(String[] args) {
    // Этот код позволяет нам перенаправить стандартный вывод в файл
    // Т.к. на экран выводится не совсем удобочитаемая кодировка,
    // файл в данном случае более удобен
    try {
      System.setOut(new PrintStream("out.txt"));
    } catch (FileNotFoundException ex) {
      ex.printStackTrace();
      return;
    }

    ManagementSystem ms = ManagementSystem.getInstance();

    // Просмотр полного списка групп
    printString("Полный список групп");
    printString("*******************");
    List<Group> allGroups = ms.getGroups();
    if (allGroups != null)
    for (Group gi : allGroups) {
      printString(gi);
    }
    printString();

    // Просмотр полного списка студентов
    printString("Полный список студентов");
    printString("***********************");
    Collection<Student> allStudends = ms.getAllStudents();
    if (allStudends != null)
      for (Student si : allStudends) {
        printString(si);
      }
    printString();

    // Вывод списков студентов по группам
    printString("Список студентов по группам");
    printString("***************************");
    List<Group> groups = ms.getGroups();
    // Проверяем все группы
    if (groups != null)
      for (Group gi : groups) {
      printString("---> Группа:" + gi.getNameGroup());
      // Получаем список студентов для конкретной группы
      Collection<Student> students = ms.getStudentsFromGroup(gi, 2006);
      for (Student si : students) {
        printString(si);
      }
      }
    printString();

//далее
  }

  // Получить список групп
  public List<Group> getGroups() {
    return groups;
  }

  // Получить список всех студентов
  public Collection<Student> getAllStudents() {
    return students;
  }

  // Получить список студентов для определенной группы
  public Collection<Student> getStudentsFromGroup(Group group, int year) {
    Collection<Student> l = new TreeSet<Student>();
    for (Student si : students) {
      if (si.getGroupId() == group.getGroupId() && si.getEducationYear() == year) {
        l.add(si);
      }
    }
    return l;
  }





  // Этот код позволяет нам изменить кодировку
  // Такое может произойти если используется IDE - например NetBeans.
  // Тогда вы получаете просто одни вопросы, что крайне неудобно читать
  public static void printString(Object s) {
    //System.out.println(s.toString());
    try {
      System.out.println(new String(s.toString().getBytes("windows-1251"), "windows-1251"));
    } catch (UnsupportedEncodingException ex) {
      ex.printStackTrace();
    }
  }

  public static void printString() {
    System.out.println();
  }
}