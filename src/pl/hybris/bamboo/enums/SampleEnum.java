package pl.hybris.bamboo.enums;

/**
 * Created by i323728 on 2/3/16.
 */
public enum SampleEnum
{
	ONE("http://www.google.com"), TWO("http://www.bing.com");

	private final String url;

	private SampleEnum(String url)
	{
		this.url = url;
	}

	public String getURL()
	{
		return url;
	}

}
