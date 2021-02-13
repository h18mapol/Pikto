package piktoproject.pikto.login.facebook;

import org.apache.commons.lang3.StringEscapeUtils;
import piktoproject.pikto.login.ApiBinding;

import java.nio.charset.Charset;
import java.util.List;

public class Facebook extends ApiBinding {

    public String accessToken;

    private static final String GRAPH_API_BASE_URL = "https://graph.facebook.com/v2.12";

    public Facebook(String accessToken) {
        super(accessToken);
        this.accessToken = accessToken;
        System.out.println(accessToken);
        System.out.println(getEmail().getEmail());
    }

    public Profile getProfile() {
        return restTemplate.getForObject(GRAPH_API_BASE_URL + "/me", Profile.class);
    }

    public Email getEmail() {
        return restTemplate.getForObject("https://graph.facebook.com/v2.12/me?fields=email&access_token="+accessToken, Email.class);
    }

    public List<Post> getFeed() {
        return restTemplate.getForObject(GRAPH_API_BASE_URL + "/me/feed", Feed.class).getData();
    }

}
