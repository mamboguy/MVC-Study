package Chapter4;

public class CourseController {

    public static void main(String[] args) {
        CourseController myController = new CourseController();

        CourseModel tempModel = new CourseModel();
        tempModel.add("Computer Science", "Intro to Java", 101, 3);
        tempModel.add("Physics", "Intro to Physics", 102, 3);
        tempModel.add("Chemistry", "Covalent Bonds", 311, 3);
        tempModel.add("Mathematics", "Geometry", 251, 3);
        tempModel.add("Botany", "Herbology", 301, 3);
        tempModel.add("Zoology", "Mammals", 111, 3);
        tempModel.add("Computer Science", "Intermediate Java, pt 1", 201, 3);
        tempModel.add("Computer Science", "Intermediate Java, pt 2", 202, 3);

        //myController.addModel(tempModel);
        CourseView myView = new CourseView();
    }
}
