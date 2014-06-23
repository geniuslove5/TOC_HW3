//F74001195 陳境圃
/*
	先把東西讀下來
	存成字串
	再來用一些字串分析和判斷即可作出答案
*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

public class TocHw3
{
	public static String readAll(Reader rd) throws IOException
        {
                StringBuilder sb = new StringBuilder();
                int cp;
                while ((cp = rd.read()) != -1) {
                sb.append((char) cp);
                }
                return sb.toString();
        }

        public static String readFromUrl(String url) throws IOException
        {
                try
		{
			InputStream is = new URL(url).openStream();
                	try
                	{
                        	BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
                        	String jsonText = readAll(rd);
				return jsonText;
                	}
                	finally
                	{
                        	is.close();
                	}
		}
		catch(IOException e)
		{
			System.out.println("找不到這個網址");
			System.exit(1);
		}
		return "";
        }
	public static int average(int[] n)
	{
		int i, sum=0;
		for(i=0;i<n.length;i++)sum+=n[i];
		return sum/n.length;
	}

	public static void main(String[] args) throws IOException
	{
		if(args.length<4)
		{
			System.out.println("number of arguements is wrong");
			return;
		}
		String str = readFromUrl(args[0]);
//		System.out.println(str);
		String city = args[1];
		String road = args[2];
		String yearmonth = "月\":";
		String money = "總價元\":";
		yearmonth=yearmonth.concat(args[3]);
		String[] strsplit = str.split("}");
		String[] citycheck = new String[1100];
		int i, j, first, countcity=0, countroad=0, countyearmonth=0;
		for(i=0;i<citycheck.length;i++)citycheck[i]=" ";
		for(i=0;i<strsplit.length;i++)
		{
			
			first = strsplit[i].indexOf(city);
			if(first==-1)continue;
			else
			{
				citycheck[countcity]=citycheck[countcity].concat(strsplit[i]);
				
//				for(i=0;i<100;i++)System.out.print(strsplit[j].charAt(first+i)); //500
//				System.out.println(citycheck[countcity]);
				countcity++;
			}
		}
		String[] roadcheck = new String[countcity];
		for(i=0;i<roadcheck.length;i++)roadcheck[i]=" ";
		for(i=0;i<countcity;i++)
		{
			first = citycheck[i].indexOf(road);
			if(first==-1)continue;
			else
			{
				roadcheck[countroad]=roadcheck[countroad].concat(citycheck[i]);
//				System.out.println(roadcheck[countroad]);
				countroad++;
			}
		}
		
		String[] yearmonthcheck = new String[countroad];
		for(i=0;i<yearmonthcheck.length;i++)yearmonthcheck[i]=" ";
                for(i=0;i<countroad;i++)
                {
                        first = roadcheck[i].indexOf(yearmonth);
                        if(first==-1)continue;
                        else
                        {
                                yearmonthcheck[countyearmonth]=yearmonthcheck[countyearmonth].concat(roadcheck[i]);
//                                System.out.println(yearmonthcheck[countyearmonth]);
                                countyearmonth++;
                        }
                }
		

		int[] moneyint = new int[countyearmonth];
		String[] moneystring = new String[countyearmonth];
		for(i=0;i<countyearmonth;i++)
		{
			String[] moneycheck = yearmonthcheck[i].split(",");
			for(j=0;j<moneycheck.length;j++)
			{
				first = moneycheck[j].indexOf(money);
                        	if(first==-1)continue;
                       	 	else
                       	 	{
					moneystring[i]=moneycheck[j].substring(first+5,moneycheck[j].length());
					moneyint[i] = Integer.valueOf(moneystring[i]);
//					System.out.println(moneyint[i]);
					break;
 	                        }
	
			}
		}
		if( moneyint.length==0 )System.out.println("找不到這個資料");
		else System.out.println(average(moneyint));	
//		System.out.println(countcity);
//		System.out.println(countroad);
//		System.out.println(countyearmonth);

  	}	
}

