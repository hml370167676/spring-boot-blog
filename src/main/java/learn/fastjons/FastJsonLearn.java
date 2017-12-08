package learn.fastjons;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.thoughtworks.xstream.XStream;

/**
 * <p>Description:</p>
 *
 * @author minglu@toutoujinrong.com
 * @date 2017/12/6 14:32
 */
public class FastJsonLearn {

  public static void main(String[] args) {

    XStream xstream = new XStream();
    Group group = new Group();
    group.setId(0L);
    group.setName("admin");

    User guestUser = new User();
    guestUser.setId(2L);
    guestUser.setName("guest");

    User rootUser = new User();
    rootUser.setId(3L);
    rootUser.setName("root");

    group.addUser(guestUser);
    group.addUser(rootUser);

    String jsonString = JSON.toJSONString(group,true);
    System.out.println(jsonString);

    Group group1 = JSON.parseObject(jsonString,Group.class);
    Object group2 = JSON.parse(jsonString);
    JSONObject group3 = JSON.parseObject(jsonString);
//    JSONArray group4 = JSON.parseArray(jsonString);
    Object group5 = JSON.toJSON(group);

    xstream.alias("group", Group.class);
    xstream.alias("user",User.class);

    String xml = xstream.toXML(group);
    System.out.println(xml);
    System.out.println(group1);
    System.out.println(group2);
    System.out.println(group3);
//    System.out.println(group4);
    System.out.println(group5);
  }


}
