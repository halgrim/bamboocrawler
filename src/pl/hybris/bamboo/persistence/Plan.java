package pl.hybris.bamboo.persistence;

import org.springframework.data.annotation.Id;

/**
 * Created by i323728 on 11/10/15.
 */
public class Plan
{
    @Id
    private String id;
    private String url;
    private String parsed;

    public void setParsed(String parsed)
    {
        this.parsed = parsed;
    }

    public String getUrl()
    {
        return url;
    }

    public String getParsed()
    {
        return parsed;
    }

    public Plan(String url, String parsed)
    {
        this.url = url;
        this.parsed = parsed;
    }
}
