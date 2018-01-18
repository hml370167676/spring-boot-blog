package learn.fastjons;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.NoTypePermission;
import com.thoughtworks.xstream.security.NullPermission;
import com.thoughtworks.xstream.security.PrimitiveTypePermission;
import java.io.StringReader;
import java.util.Collection;

/**
 * <p>Description:</p>
 *
 * @author minglu@toutoujinrong.com
 * @date 2017/12/6 17:47
 */
public class XStreamLearn {


  public static void main(String[] args) {
    XStream xstream = new XStream();
    // clear out existing permissions and set own ones
    xstream.addPermission(NoTypePermission.NONE);
    // allow some basics
    xstream.addPermission(NullPermission.NULL);
    xstream.addPermission(PrimitiveTypePermission.PRIMITIVES);
    xstream.allowTypeHierarchy(Collection.class);
    // allow any type from the same package
    xstream.allowTypesByWildcard(new String[]{
        XStreamLearn.class.getPackage().getName() + ".*"
    });
    Person bean = new Person("jack", 20);
    //序列化
    xstream.alias("person", Person.class);//为类名节点重命名,否则为类全称
    String xml = xstream.toXML(bean);

    System.out.println(xml);
    //反序列化
    bean = (Person) xstream.fromXML(xml);
    System.out.println(bean);

   /* xstream = new XStream(new JettisonMappedXmlDriver());//设置Json解析器
    xstream.setMode(XStream.NO_REFERENCES);//设置reference模型,不引用
    //Json序列化
    String json = xstream.toXML(bean);
    System.out.println(json);
    //Json反序列化
    bean = (Person) xstream.fromXML(json);
    System.out.println(bean);*/

    String a = "sss";
    String b = "sss";

    String c = new String("sss");

    System.out.println(a == b);
    System.out.println(a == c);
    System.out.println(a.equals(c));

    /**
     * 使用XStream别名注解
     */
    Group group = new Group();
    User user1 = new User();
    user1.setName("Tony");
    user1.setId(1L);
    User user2 = new User();
    user2.setName("Simba");
    user2.setId(2L);
    group.addUser(user1);
    group.addUser(user2);
    //xstream使用注解转换,否则注解无用，节点展示为类全称
    xstream.autodetectAnnotations(true);//自动检测注解
//    xstream.processAnnotations(group.getClass());//应用Group类的注解
    xml = xstream.toXML(group);
    System.out.println(xml);



  }

}
