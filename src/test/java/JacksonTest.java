import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import irepdata.model.User;
import irepdata.views.JSONViews;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by Gvozd on 04.12.2016.
 */
public class JacksonTest {

    @Test
    @SuppressWarnings("resource")
    public void mapPOJOToJSON(){
        User user = new User();
        user.setLogin("Login");
        user.setPassword("Password");
        user.setId(34L);
        user.setEnabled(true);
        user.setUsername("васяпупкин");

        ObjectMapper mapper = new ObjectMapper();

        try {
            String managerView = mapper.writerWithView(JSONViews.Admin.class).writeValueAsString(user);
            System.out.println(managerView);

            User managerStaff = mapper.readerWithView(JSONViews.List.class).forType(User.class).readValue(managerView);
            System.out.println(managerStaff);

        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
