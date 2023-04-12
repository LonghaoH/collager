public class test {
  
  public static void main(String[] args) throws IOException, UnsupportedLookAndFeelException,
          ClassNotFoundException, InstantiationException, IllegalAccessException {
    Appendable appendable = System.out;
    CollagerView view = new CollagerViewImpl();
    CollagerController controller = new CollagerControllerImpl(appendable, view);
    controller.runScriptMode("C:\\Users\\jdhoo\\Documents\\GitHub\\collager\\res\\script.txt");
  }
}
