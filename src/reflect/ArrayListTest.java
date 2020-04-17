package reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.ArrayList;


public class ArrayListTest {

    public static void main(String[] args){
        try {
//            testList();
            testArray();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    public static void testList() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List<User> userList = new ArrayList<>();
        userList.add(new User("张三",23,"男"));
        userList.add(new User("小红",32,"女"));

        Class<? extends List> listClass = userList.getClass();
        Method addMethod = listClass.getDeclaredMethod("add",Object.class);
        addMethod.invoke(userList,1234);
        System.out.print(userList);
        for (int i = 0; i < userList.size(); i++) {
            System.out.print(userList.get(i));
        }

        ArrayList list = new ArrayList();
        list.add(new User("张三",23,"男"));
        list.add("123");
    }

    public static void testArray() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List<User> userList= new ArrayList<>();
        userList.add(new User("3",3,"3"));
        Method setMethod = userList.getClass().getDeclaredMethod("set",int.class,Object.class);
        setMethod.invoke(userList,0,new User("小红",23,"女"));
        System.out.print(userList);
    }


}
