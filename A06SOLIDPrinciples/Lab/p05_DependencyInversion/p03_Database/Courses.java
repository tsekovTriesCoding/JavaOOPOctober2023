package A06SOLIDPrinciples.Lab.p05_DependencyInversion.p03_Database;

public class Courses {
    public void printAll()
    {
        Data database = new Data();
        Iterable<String> courses = database.courseNames();

        //print courses
    }

    public void printIds()
    {
        Data database = new Data();
        Iterable<Integer>coursesIds = database.courseIds();

        coursesIds.forEach(System.out::println);
    }

    public void printById(int id)
    {
        Data database = new Data();
        String course = database.getCourseById(id);

        System.out.println(course);
    }

    public void search(String substring)
    {
        Data database = new Data();
        Iterable<String> courses = database.search(substring);

        courses.forEach(System.out::println);
    }
}
