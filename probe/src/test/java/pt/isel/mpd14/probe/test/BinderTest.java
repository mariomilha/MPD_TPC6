package pt.isel.mpd14.probe.test;

import pt.isel.mpd14.probe.test.model.OtherStudent;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import junit.framework.Assert;
import junit.framework.TestCase;
import pt.isel.mpd14.probe.Binder;
import pt.isel.mpd14.probe.BindField;
import pt.isel.mpd14.probe.BindFieldNonNull;
import pt.isel.mpd14.probe.BindNonNull;
import pt.isel.mpd14.probe.BindProp;
import pt.isel.mpd14.probe.BindPropNonNull;
import pt.isel.mpd14.probe.test.model.Student;
import pt.isel.mpd14.probe.test.model.StudentDto;

/**
 * Unit test for Binder class.
 */
public class BinderTest extends TestCase{
    
    final static SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    
    public void test_bind_student_to_studentDto() throws Exception
    {
        /*
         Arrange
        */
        Student s1 = new Student(31531, sdf.parse("05-6-1994"), "Jose Cocacola", null);
        Map<String, Object> s1fields = Binder.getFieldsValues(s1);
        /*
          Act
        */
        StudentDto s2 = new Binder(new BindField()).bindTo(StudentDto.class, s1fields);
        System.out.println(s2);
        
        Assert.assertEquals(s1.id, s2.id);
        Assert.assertEquals(s1.getName(), s2.name);
        Assert.assertEquals(null, s2.birthDate);

    }
    
    public void test_bind_fields_filter_null_values_1() throws Exception
    {
        /*
         Arrange
        */
        Map<String, Object> v = new HashMap<>();
        v.put("name", null);
        v.put("id", 657657);
        v.put("birthDate", "4-5-1997");
        /*
          Act
        */
        StudentDto s2 = new Binder(new BindFieldNonNull())
                .bindTo(StudentDto.class, v);
        System.out.println(s2);
        /*
          Assert
        */
        Assert.assertEquals(v.get("id"), s2.id);
        Assert.assertEquals("DEFAULT NAME", s2.name);
        Assert.assertEquals(v.get("birthDate"), s2.birthDate);

    }
    
    public void test_bind_fields_filter_null_values_2() throws Exception
    {
        /*
         Arrange
        */
        Map<String, Object> v = new HashMap<>();
        v.put("name", null);
        v.put("id", 657657);
        v.put("birthDate", "4-5-1997");
        /*
          Act
        */
        StudentDto s2 = new Binder(new BindNonNull(new BindField()))
                .bindTo(StudentDto.class, v);
        System.out.println(s2);
        /*
          Assert
        */
        Assert.assertEquals(v.get("id"), s2.id);
        Assert.assertEquals("DEFAULT NAME", s2.name);
        Assert.assertEquals(v.get("birthDate"), s2.birthDate);

    }
    
    public void test_bind_prop_filter_null_values_1() throws Exception
    {
        /*
         Arrange
        */
        Map<String, Object> v = new HashMap<>();
        v.put("name", "ola");
        v.put("id", null);
        v.put("birthDate", "4-5-1997");
        /*
          Act
        */
        OtherStudent s2 = new Binder(new BindPropNonNull())
                .bindTo(OtherStudent.class, v);
        System.out.println(s2);
        /*
          Assert
        */
        Assert.assertEquals(-1, s2.id);
        Assert.assertEquals(v.get("name"), s2.getName());
        Assert.assertEquals(v.get("birthDate"), s2.getBirthDate());

    }
    
    public void test_bind_prop_filter_null_values_2() throws Exception
    {
        /*
         Arrange
        */
        Map<String, Object> v = new HashMap<>();
        v.put("name", "ola");
        v.put("id", null);
        v.put("birthDate", "4-5-1997");
        /*
          Act
        */
        OtherStudent s2 = new Binder(new BindNonNull(new BindProp()))
                .bindTo(OtherStudent.class, v);
        System.out.println(s2);
        /*
          Assert
        */
        Assert.assertEquals(-1, s2.id);
        Assert.assertEquals(v.get("name"), s2.getName());
        Assert.assertEquals(v.get("birthDate"), s2.getBirthDate());

    }
    
    public void test_bind_to_student_properties() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ParseException{
        /*
        Arrange
        */
        Map<String, Object> v = new HashMap<>();
        v.put("name", "Maria josefina");
        v.put("id", 657657);
        v.put("birthdate", sdf.parse("4-5-1997"));
        /*
        Act
        */
        Student s = new Binder(new BindProp()).bindTo(Student.class, v);
        /*
        Assert
        */
        Assert.assertEquals(v.get("name"), s.getName());
        Assert.assertEquals(0, s.id); // id is not set because it is not a property
        Assert.assertEquals(v.get("birthdate"), s.getBirthDate());    
    }
    
    public void test_bind_to_student_properties_and_fields() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ParseException{
        /*
        Arrange
        */
        Map<String, Object> v = new HashMap<>();
        v.put("name", "Maria josefina");
        v.put("id", 657657);
        v.put("birthdate", sdf.parse("4-5-1997"));
        /*
        Act
        */
        Student s = new Binder(new BindProp(), new BindField()).bindTo(Student.class, v);
        /*
        Assert
        */
        Assert.assertEquals(v.get("name"), s.getName());
        Assert.assertEquals(v.get("id"), new Integer(s.id));
        Assert.assertEquals(v.get("birthdate"), s.getBirthDate());    
    }
    
    
}
